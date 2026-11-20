package com.uninter.raiazesdonordeste.cliente.domain.service;

import com.uninter.raiazesdonordeste.cliente.api.model.dto.request.ClientePostDto;
import com.uninter.raiazesdonordeste.cliente.api.model.dto.request.ClienteUpdateDto;
import com.uninter.raiazesdonordeste.cliente.api.model.dto.response.ClienteMaxDto;
import com.uninter.raiazesdonordeste.cliente.api.model.dto.response.ClienteResumoDto;
import com.uninter.raiazesdonordeste.cliente.api.model.mapper.ClienteMapper;
import com.uninter.raiazesdonordeste.cliente.domain.model.Cliente;
import com.uninter.raiazesdonordeste.cliente.domain.repository.ClienteRepository;
import com.uninter.raiazesdonordeste.cliente.exceptions.ClienteNotFoundException;
import com.uninter.raiazesdonordeste.fidelidade.api.app.CampanhaFidelidadeFacade;
import com.uninter.raiazesdonordeste.fidelidade.api.app.ContaFidelidadeFacade;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.CampanhaFidelidadeResumoDto;
import com.uninter.raiazesdonordeste.fidelidade.api.model.dto.response.ContaFidelidadeMaxDto;
import com.uninter.raiazesdonordeste.fidelidade.exceptions.ContaFidelidadeNotFoundException;
import com.uninter.raiazesdonordeste.lgpd.api.app.ConsentimentoLgpdFacade;
import com.uninter.raiazesdonordeste.lgpd.api.model.dto.response.ConsentimentoLgpdResumoDto;
import com.uninter.raiazesdonordeste.pedido.api.app.PedidoFacade;
import com.uninter.raiazesdonordeste.pedido.api.model.dto.response.PedidoResumoDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final PedidoFacade pedidoFacade;
    private final ContaFidelidadeFacade contaFidelidadeFacade;
    private final ConsentimentoLgpdFacade consentimentoLgpdFacade;
    private final CampanhaFidelidadeFacade campanhaFidelidadeFacade;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper,
                          PedidoFacade pedidoFacade, ContaFidelidadeFacade contaFidelidadeFacade,
                          ConsentimentoLgpdFacade consentimentoLgpdFacade,
                          CampanhaFidelidadeFacade campanhaFidelidadeFacade) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.pedidoFacade = pedidoFacade;
        this.contaFidelidadeFacade = contaFidelidadeFacade;
        this.consentimentoLgpdFacade = consentimentoLgpdFacade;
        this.campanhaFidelidadeFacade = campanhaFidelidadeFacade;
    }

    @Transactional(readOnly = true)
    public List<ClienteResumoDto> findAll() {
        return clienteMapper.toCollectionModel(clienteRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ClienteMaxDto findById(Long id) {
        return toMaxDto(getClienteOrElseThrow(id));
    }

    @Transactional
    public ClienteMaxDto save(ClientePostDto request) {
        Cliente cliente = clienteRepository.save(clienteMapper.toEntity(request));
        return toMaxDto(cliente);
    }

    @Transactional
    public ClienteMaxDto update(Long id, ClienteUpdateDto request) {
        Cliente cliente = getClienteOrElseThrow(id);
        clienteMapper.update(cliente, request);
        return toMaxDto(cliente);
    }

    @Transactional
    public void delete(Long id) {
        getClienteOrElseThrow(id);
        clienteRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<PedidoResumoDto> findPedidos(Long clienteId) {
        getClienteOrElseThrow(clienteId);
        return pedidoFacade.findPedidosDoCliente(clienteId);
    }

    @Transactional(readOnly = true)
    public ContaFidelidadeMaxDto findContaFidelidade(Long clienteId) {
        getClienteOrElseThrow(clienteId);
        return contaFidelidadeFacade.findByClienteId(clienteId)
                .orElseThrow(() -> new ContaFidelidadeNotFoundException(
                        "ContaFidelidade nao encontrada para o cliente de id: " + clienteId));
    }

    @Transactional(readOnly = true)
    public List<ConsentimentoLgpdResumoDto> findConsentimentosLgpd(Long clienteId) {
        getClienteOrElseThrow(clienteId);
        return consentimentoLgpdFacade.findConsentimentosDoCliente(clienteId);
    }

    @Transactional(readOnly = true)
    public List<CampanhaFidelidadeResumoDto> findCampanhasFidelidade(Long clienteId) {
        getClienteOrElseThrow(clienteId);
        return campanhaFidelidadeFacade.findCampanhasDoCliente(clienteId);
    }

    private ClienteMaxDto toMaxDto(Cliente cliente) {
        carregarRelacionamentos(cliente);
        return new ClienteMaxDto(
                cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getDataNascimento(), cliente.getCpf(),
                cliente.getPedidosIds(),
                cliente.getContaFidelidadeId(),
                cliente.getConsentimentosLgpdIds(),
                cliente.getCampanhasFidelidadeIds()
        );
    }

    private void carregarRelacionamentos(Cliente cliente) {
        Long clienteId = cliente.getId();
        cliente.setPedidosIds(pedidoFacade.findPedidosDoCliente(clienteId).stream()
                .map(pedido -> pedido.id())
                .toList());
        cliente.setContaFidelidadeId(contaFidelidadeFacade.findByClienteId(clienteId)
                .map(conta -> conta.id())
                .orElse(null));
        cliente.setConsentimentosLgpdIds(consentimentoLgpdFacade.findConsentimentosDoCliente(clienteId).stream()
                .map(consentimento -> consentimento.id())
                .toList());
        cliente.setCampanhasFidelidadeIds(campanhaFidelidadeFacade.findCampanhasDoCliente(clienteId).stream()
                .map(campanha -> campanha.id())
                .toList());
    }

    private Cliente getClienteOrElseThrow(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente nao encontrado com id: " + id));
    }
}
