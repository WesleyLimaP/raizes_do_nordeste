package com.uninter.raiazesdonordeste.pagamento.domain.service;

import com.uninter.raiazesdonordeste.pagamento.api.model.dto.request.PagamentoPostDto;
import com.uninter.raiazesdonordeste.pagamento.api.model.dto.request.PagamentoUpdateDto;
import com.uninter.raiazesdonordeste.pagamento.api.model.dto.response.PagamentoMaxDto;
import com.uninter.raiazesdonordeste.pagamento.api.model.dto.response.PagamentoResumoDto;
import com.uninter.raiazesdonordeste.pagamento.api.model.mapper.PagamentoMapper;
import com.uninter.raiazesdonordeste.pagamento.domain.model.Pagamento;
import com.uninter.raiazesdonordeste.pagamento.domain.repository.PagamentoRepository;
import com.uninter.raiazesdonordeste.pagamento.exceptions.PagamentoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private PagamentoMapper pagamentoMapper;

    @Transactional(readOnly = true)
    public List<PagamentoResumoDto> findAll() {
        return pagamentoMapper.toCollectionModel(pagamentoRepository.findAll());
    }

    @Transactional(readOnly = true)
    public PagamentoMaxDto findById(Long id) {
        return pagamentoMapper.toModel(getPagamentoOrElseThrow(id));
    }

    @Transactional //todo criar facade para obter pedido
    public PagamentoMaxDto save(PagamentoPostDto request) {
        Pagamento pagamento = pagamentoMapper.toEntity(request);
        pagamento = pagamentoRepository.save(pagamento);
        return pagamentoMapper.toModel(pagamento);
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
    public void delete(Long id) {
        getPagamentoOrElseThrow(id);
        pagamentoRepository.deleteById(id);
    }

    private Pagamento getPagamentoOrElseThrow(Long pagamentoId) {
        return pagamentoRepository.findById(pagamentoId)
                .orElseThrow(() -> new PagamentoNotFoundException("Pagamento nao encontrado com id: " + pagamentoId));
    }
}
