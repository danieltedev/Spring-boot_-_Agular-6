package com.dtel.springrestfull.springrestfullapi.resource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.dtel.springrestfull.springrestfullapi.event.ResourceCreateEvent;
import com.dtel.springrestfull.springrestfullapi.model.Lancamento;
import com.dtel.springrestfull.springrestfullapi.model.filter.LancamentoFilter;
import com.dtel.springrestfull.springrestfullapi.model.projection.ResumoLancamento;
import com.dtel.springrestfull.springrestfullapi.repository.LancamentoRepository;
import com.dtel.springrestfull.springrestfullapi.repository.specification.LancamentoSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * LancamentoResource
 */
@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public Page<Lancamento> pesquisar(LancamentoFilter filter, Pageable pageable) {
        return lancamentoRepository.findAll(new LancamentoSpec(filter).biuld(), pageable);
    }

    @GetMapping("/resumo")
    public Page<ResumoLancamento> resumo(LancamentoFilter filter, Pageable pageable) {
        return lancamentoRepository.findAll(new LancamentoSpec(filter).biuld(), pageable).map(ResumoLancamento::new);
    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);
        publisher.publishEvent(new ResourceCreateEvent(this, response, lancamentoSalvo.getId()));
        return ResponseEntity.status(HttpStatus.OK).body(lancamentoSalvo);
    }

}