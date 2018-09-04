package com.dtel.springrestfull.springrestfullapi.service;

import java.net.URI;

import com.dtel.springrestfull.springrestfullapi.model.Categoria;
import com.dtel.springrestfull.springrestfullapi.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * CategoriaService
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Long id) throws NoHandlerFoundException {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
        return categoriaRepository.findById(id).orElseThrow(() -> new NoHandlerFoundException("GET", uri.toASCIIString(), new HttpHeaders()));
    }
}