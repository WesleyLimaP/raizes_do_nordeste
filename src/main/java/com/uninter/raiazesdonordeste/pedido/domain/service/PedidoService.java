package com.uninter.raiazesdonordeste.pedido.domain.service;

import com.uninter.raiazesdonordeste.cardapio.domain.repository.ProdutoRepository;
import com.uninter.raiazesdonordeste.cardapio.exceptions.ProdutoNotFoundException;
import com.uninter.raiazesdonordeste.core.exceptions.EntityNotRelatedException;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.request.ItemPedidoPostDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.request.ItemPedidoUpdateDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.request.PedidoPostDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.request.PedidoUpdateDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.response.ItemPedidoMaxDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.response.ItemPedidoResumoDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.response.PedidoMaxDto;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.response.PedidoResumoDto;
import com.uninter.raiazesdonordeste.pedido.api.model.mapper.ItemPedidoMapper;
import com.uninter.raiazesdonordeste.pedido.api.model.mapper.PedidoMapper;
import com.uninter.raiazesdonordeste.pedido.domain.model.ItemPedido;
import com.uninter.raiazesdonordeste.pedido.domain.model.Pedido;
import com.uninter.raiazesdonordeste.pedido.domain.model.StatusPedido;
import com.uninter.raiazesdonordeste.pedido.domain.repository.PedidoRepository;
import com.uninter.raiazesdonordeste.pedido.exceptions.PedidoNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoMapper pedidoMapper;
    private final ItemPedidoMapper itemPedidoMapper;
    private final ApplicationEventPublisher eventPublisher;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, PedidoMapper pedidoMapper, ItemPedidoMapper itemPedidoMapper, ApplicationEventPublisher eventPublisher) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoMapper = pedidoMapper;
        this.itemPedidoMapper = itemPedidoMapper;
        this.eventPublisher = eventPublisher;
    }

    @Transactional(readOnly = true)
    public List<PedidoResumoDto> findAll(Long unidadeId, Long clienteId) {
        return pedidoMapper.toCollectionModel(pedidoRepository.findByUnidadeIdAndClienteId(unidadeId, clienteId));
    }

    @Transactional(readOnly = true)
    public PedidoMaxDto findById(Long id) {
        return pedidoMapper.toModel(getPedidoOrElseThrow(id));
    }

    @Transactional
    public PedidoMaxDto save(PedidoPostDto request) {
        Pedido pedido = pedidoMapper.toEntity(request);
        pedido.setStatus(StatusPedido.CRIADO);
        pedido = pedidoRepository.save(pedido);
        return pedidoMapper.toModel(pedido);
    }

    @Transactional
    public PedidoMaxDto update(Long id, PedidoUpdateDto request) {
        Pedido pedido = getPedidoOrElseThrow(id);
        pedido.setCanal(request.canal());
        pedido.setDataHora(request.dataHora());
        pedido.setClienteId(request.clienteId());
        pedido.setUnidadeId(request.unidadeId());
        return pedidoMapper.toModel(pedido);
    }

    @Transactional
    public PedidoMaxDto aguardarPagamento(Long id) {
        Pedido pedido = getPedidoOrElseThrow(id);
        pedido.setEventPublisher(eventPublisher);
        pedido.aguardarPagamento();
        return pedidoMapper.toModel(pedido);
    }

    @Transactional
    public PedidoMaxDto iniciarPreparo(Long id) {
        Pedido pedido = getPedidoOrElseThrow(id);
        pedido.setStatus(StatusPedido.EM_PREPARO);
        return pedidoMapper.toModel(pedido);
    }

    @Transactional
    public PedidoMaxDto pronto(Long id) {
        Pedido pedido = getPedidoOrElseThrow(id);
        pedido.setStatus(StatusPedido.PRONTO);
        return pedidoMapper.toModel(pedido);
    }

    @Transactional
    public PedidoMaxDto cancelar(Long id) {
        Pedido pedido = getPedidoOrElseThrow(id);
        pedido.setEventPublisher(eventPublisher);
        pedido.cancelar();
        return pedidoMapper.toModel(pedido);
    }

    @Transactional
    public PedidoMaxDto entregar(Long id) {
        Pedido pedido = getPedidoOrElseThrow(id);
        pedido.setEventPublisher(eventPublisher);
        pedido.entregar();
        return pedidoMapper.toModel(pedido);
    }

    @Transactional
    public void delete(Long id) {
        getPedidoOrElseThrow(id);
        pedidoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ItemPedidoResumoDto> findItems(Long pedidoId) {
        getPedidoOrElseThrow(pedidoId);
        return itemPedidoMapper.toCollectionModel(pedidoRepository.findItemsByPedidoId(pedidoId));
    }

    @Transactional(readOnly = true)
    public ItemPedidoMaxDto findItemById(Long pedidoId, Long itemPedidoId) {
        getPedidoOrElseThrow(pedidoId);
        return itemPedidoMapper.toModel(getItemRelatedOrElseThrow(pedidoId, itemPedidoId));
    }

    @Transactional
    public ItemPedidoMaxDto saveItem(Long pedidoId, ItemPedidoPostDto request) {
        getPedidoOrElseThrow(pedidoId);
        getProdutoOrElseThrow(request.produtoId());
        ItemPedido itemPedido = itemPedidoMapper.toEntity(request, pedidoId);
        itemPedido = pedidoRepository.saveItem(itemPedido);
        return itemPedidoMapper.toModel(itemPedido);
    }

    @Transactional
    public ItemPedidoMaxDto updateItem(Long pedidoId, Long itemPedidoId, ItemPedidoUpdateDto request) {
        getPedidoOrElseThrow(pedidoId);
        ItemPedido itemPedido = getItemRelatedOrElseThrow(pedidoId, itemPedidoId);
        itemPedido.setPrecoUnitario(request.precoUnitario());
        itemPedido.setQuantidade(request.quantidade());
        return itemPedidoMapper.toModel(itemPedido);
    }

    @Transactional
    public void deleteItem(Long pedidoId, Long itemPedidoId) {
        Pedido pedido = getPedidoOrElseThrow(pedidoId);
        ItemPedido item = getItemRelatedOrElseThrow(pedidoId, itemPedidoId);
        pedido.getItems().removeIf(i -> i.getId().equals(itemPedidoId));
        pedidoRepository.deleteItem(item);
    }

    private Pedido getPedidoOrElseThrow(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido nao encontrado com id: " + pedidoId));
    }

    private ItemPedido getItemRelatedOrElseThrow(Long pedidoId, Long itemPedidoId) {
        return pedidoRepository.findItemById(itemPedidoId, pedidoId)
                .orElseThrow(() -> new EntityNotRelatedException("nenhum item do pedido com id: " + itemPedidoId + " encontrado em pedido de id: " + pedidoId, pedidoId, itemPedidoId));
    }

    private void getProdutoOrElseThrow(Long produtoId) {
        if (!produtoRepository.existsById(produtoId)) {
            throw new ProdutoNotFoundException("Produto nao encontrado com id: " + produtoId);
        }
    }

}
