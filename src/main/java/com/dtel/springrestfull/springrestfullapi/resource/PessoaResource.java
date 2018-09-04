package com.dtel.springrestfull.springrestfullapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.dtel.springrestfull.springrestfullapi.event.ResourceCreateEvent;
import com.dtel.springrestfull.springrestfullapi.model.Pessoa;
import com.dtel.springrestfull.springrestfullapi.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PessoaResource
 */
@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private ApplicationEventPublisher publisher;

    private Pessoa pessoa;
    
    @GetMapping
    public List<Pessoa> listar() {
        return repository.findAll();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        setPessoa(repository.save(pessoa));
        publisher.publishEvent(new ResourceCreateEvent(this, response, getPessoa().getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(getPessoa());
    }

    /**
     * @return the pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}