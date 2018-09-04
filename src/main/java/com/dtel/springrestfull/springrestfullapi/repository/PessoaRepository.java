package com.dtel.springrestfull.springrestfullapi.repository;

import com.dtel.springrestfull.springrestfullapi.model.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PessoaRepository
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}