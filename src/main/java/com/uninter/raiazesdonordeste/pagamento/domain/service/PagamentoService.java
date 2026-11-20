package com.uninter.raiazesdonordeste.pagamento.domain.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.RequestOptions;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import com.uninter.raiazesdonordeste.pagamento.api.model.dto.request.PagamentoPostDto;
import com.uninter.raiazesdonordeste.pagamento.api.model.dto.request.PagamentoUpdateDto;
import com.uninter.raiazesdonordeste.pagamento.api.model.dto.response.CheckoutSessionResponse;
import com.uninter.raiazesdonordeste.pagamento.api.model.dto.response.PagamentoMaxDto;
import com.uninter.raiazesdonordeste.pagamento.api.model.dto.response.PagamentoResumoDto;
import com.uninter.raiazesdonordeste.pagamento.api.model.mapper.PagamentoMapper;
import com.uninter.raiazesdonordeste.pagamento.domain.model.Pagamento;
import com.uninter.raiazesdonordeste.pagamento.domain.repository.PagamentoRepository;
import com.uninter.raiazesdonordeste.pagamento.exceptions.PagamentoNotFoundException;
import com.uninter.raiazesdonordeste.pedido.domain.model.ItemPedido;
import com.uninter.raiazesdonordeste.pedido.domain.model.Pedido;
import com.uninter.raiazesdonordeste.pedido.domain.model.StatusPedido;
import com.uninter.raiazesdonordeste.pedido.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PagamentoService {
    private static final Logger log = LoggerFactory.getLogger(PagamentoService.class);
    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper pagamentoMapper;
    private final PedidoRepository pedidoRepository;
    private final String stripeSecretKey;
    private final String stripeWebhookSecret;
    private final String checkoutSuccessUrl;
    private final String checkoutCancelUrl;

    public PagamentoService(PagamentoRepository pagamentoRepository, PagamentoMapper pagamentoMapper,
                            PedidoRepository pedidoRepository,
                            @Value("${stripe.secret-key:}") String stripeSecretKey,
                            @Value("${stripe.webhook-secret:}") String stripeWebhookSecret,
                            @Value("${stripe.checkout.success-url}") String checkoutSuccessUrl,
                            @Value("${stripe.checkout.cancel-url}") String checkoutCancelUrl) {
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoMapper = pagamentoMapper;
        this.pedidoRepository = pedidoRepository;
        this.stripeSecretKey = stripeSecretKey;
        this.stripeWebhookSecret = stripeWebhookSecret;
        this.checkoutSuccessUrl = checkoutSuccessUrl;
        this.checkoutCancelUrl = checkoutCancelUrl;
    }

    @Transactional(readOnly = true)
    public List<PagamentoResumoDto> findAll() { return pagamentoMapper.toCollectionModel(pagamentoRepository.findAll()); }

    @Transactional(readOnly = true)
    public PagamentoMaxDto findById(Long id) { return pagamentoMapper.toModel(getPagamentoOrElseThrow(id)); }

    @Transactional
    public PagamentoMaxDto save(PagamentoPostDto request) {
        Pagamento pagamento = pagamentoMapper.toEntity(request);
        return pagamentoMapper.toModel(pagamentoRepository.save(pagamento));
    }

    @Transactional
    public PagamentoMaxDto update(Long id, PagamentoUpdateDto request) {
        Pagamento pagamento = getPagamentoOrElseThrow(id);
        pagamento.setPedidoId(request.pedidoId());
        pagamento.setStatus(request.status());
        pagamento.setGetwayExterno(request.getwayExterno());
        pagamento.setReferencialExterna(request.referencialExterna());
        pagamento.setDataHoraAtualizacao(request.dataHoraAtualizacao());
        return pagamentoMapper.toModel(pagamento);
    }

    @Transactional
    public void delete(Long id) { getPagamentoOrElseThrow(id); pagamentoRepository.deleteById(id); }

    @Transactional
    public CheckoutSessionResponse createCheckoutSession(Long pedidoId) {
        requireConfigured(stripeSecretKey, "STRIPE_SECRET_KEY");
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido nao encontrado com id: " + pedidoId));
        List<ItemPedido> items = pedidoRepository.findItemsByPedidoId(pedidoId);
        if (items.isEmpty()) throw new IllegalArgumentException("Pedido nao possui itens para pagamento");

        SessionCreateParams.Builder sessionBuilder = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setClientReferenceId(pedido.getId().toString())
                .setSuccessUrl(successUrlWithSessionId())
                .setCancelUrl(checkoutCancelUrl)
                .putMetadata("pedido_id", pedido.getId().toString());
        for (ItemPedido item : items) {
            long amountInCents = Math.round(item.getPrecoUnitario() * 100);
            if (amountInCents <= 0 || item.getQuantidade() <= 0) {
                throw new IllegalArgumentException("Item de pedido possui preco ou quantidade invalida");
            }
            SessionCreateParams.LineItem.PriceData.ProductData productData =
                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                            .setName(item.getProduto().getNome()).build();
            SessionCreateParams.LineItem.PriceData priceData =
                    SessionCreateParams.LineItem.PriceData.builder().setCurrency("brl")
                            .setUnitAmount(amountInCents).setProductData(productData).build();
            sessionBuilder.addLineItem(SessionCreateParams.LineItem.builder()
                    .setQuantity(item.getQuantidade()).setPriceData(priceData).build());
        }
        try {
            Session session = Session.create(sessionBuilder.build(), RequestOptions.builder()
                    .setApiKey(stripeSecretKey)
                    .setIdempotencyKey("pedido-" + pedidoId + "-" + UUID.randomUUID())
                    .build());
            Pagamento pagamento = pagamentoRepository.findByReferencialExterna(session.getId())
                    .orElseGet(Pagamento::new);
            pagamento.setPedidoId(pedidoId);
            pagamento.setStatus("PENDENTE");
            pagamento.setGetwayExterno("STRIPE");
            pagamento.setReferencialExterna(session.getId());
            pagamento.setDataHoraAtualizacao(new Date());
            pagamentoRepository.save(pagamento);
            return new CheckoutSessionResponse(session.getId(), session.getUrl());
        } catch (StripeException exception) {
            throw new IllegalStateException("Nao foi possivel criar a sessao de pagamento Stripe", exception);
        }
    }

    @Transactional
    public void handleWebhook(String payload, String signature) {
        requireConfigured(stripeWebhookSecret, "STRIPE_WEBHOOK_SECRET");
        final Event event;
        try {
            event = Webhook.constructEvent(payload, signature, stripeWebhookSecret);
        } catch (Exception exception) {
            log.warn("Webhook Stripe rejeitado: assinatura ou payload invalido");
            throw new IllegalArgumentException("Assinatura do webhook Stripe invalida", exception);
        }

        log.info("Webhook Stripe recebido: id={}, tipo={}", event.getId(), event.getType());
        if (!"checkout.session.completed".equals(event.getType())) return;

        Session session = event.getDataObjectDeserializer().getObject()
                .map(Session.class::cast)
                .orElseGet(() -> {
                    try {
                        return (Session) event.getDataObjectDeserializer().deserializeUnsafe();
                    } catch (Exception exception) {
                        throw new IllegalArgumentException("Evento Stripe sem sessao desserializavel", exception);
                    }
                });
        if (!"paid".equalsIgnoreCase(session.getPaymentStatus())) {
            log.info("Sessao Stripe {} concluida sem pagamento confirmado: status={}",
                    session.getId(), session.getPaymentStatus());
            return;
        }

        Pagamento pagamento = pagamentoRepository.findByReferencialExterna(session.getId())
                .orElseGet(() -> criarPagamentoDoWebhook(session));
        if ("APROVADO".equals(pagamento.getStatus())) return;
        pagamento.setStatus("APROVADO");
        pagamento.setDataHoraAtualizacao(new Date());
        pagamentoRepository.save(pagamento);
        pedidoRepository.findById(pagamento.getPedidoId()).ifPresent(pedido -> {
            pedido.setStatus(StatusPedido.CONFIRMADO);
            pedidoRepository.save(pedido);
        });
    }

    private Pagamento criarPagamentoDoWebhook(Session session) {
        String pedidoId = session.getMetadata().get("pedido_id");
        if (pedidoId == null || pedidoId.isBlank()) {
            pedidoId = session.getClientReferenceId();
        }
        if (pedidoId == null || pedidoId.isBlank()) {
            throw new IllegalArgumentException("Webhook Stripe sem pedido_id");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setPedidoId(Long.valueOf(pedidoId));
        pagamento.setGetwayExterno("STRIPE");
        pagamento.setReferencialExterna(session.getId());
        pagamento.setStatus("PENDENTE");
        pagamento.setDataHoraAtualizacao(new Date());
        return pagamentoRepository.save(pagamento);
    }

    private void requireConfigured(String value, String variableName) {
        if (value == null || value.isBlank()) throw new IllegalStateException(variableName + " nao configurada");
    }

    private String successUrlWithSessionId() {
        if (checkoutSuccessUrl.contains("{CHECKOUT_SESSION_ID}")) return checkoutSuccessUrl;
        return checkoutSuccessUrl + (checkoutSuccessUrl.contains("?") ? "&" : "?")
                + "session_id={CHECKOUT_SESSION_ID}";
    }

    private Pagamento getPagamentoOrElseThrow(Long pagamentoId) {
        return pagamentoRepository.findById(pagamentoId)
                .orElseThrow(() -> new PagamentoNotFoundException("Pagamento nao encontrado com id: " + pagamentoId));
    }
}
