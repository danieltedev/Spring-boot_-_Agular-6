package com.dtel.springrestfull.springrestfullapi.repository;

import com.dtel.springrestfull.springrestfullapi.model.Lancamento;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * LancamentoRepository
 */
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}