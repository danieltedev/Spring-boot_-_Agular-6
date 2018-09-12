package com.dtel.springrestfull.springrestfullapi.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.dtel.springrestfull.springrestfullapi.event.ResourceCreateEvent;
import com.dtel.springrestfull.springrestfullapi.model.Lancamento;
import com.dtel.springrestfull.springrestfullapi.repository.LancamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * LancamentoResource
 */
@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public Page<Lancamento> pesquisar(Lancamento lancamento, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("descricao", m -> m.contains());
        return lancamentoRepository.findAll(Example.of(lancamento, matcher), pageable);
    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);
        publisher.publishEvent(new ResourceCreateEvent(this, response, lancamentoSalvo.getId()));
        return ResponseEntity.status(HttpStatus.OK).body(lancamentoSalvo);
    }

}