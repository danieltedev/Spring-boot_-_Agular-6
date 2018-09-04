package com.dtel.springrestfull.springrestfullapi.repository;

import com.dtel.springrestfull.springrestfullapi.model.Lancamento;
import com.dtel.springrestfull.springrestfullapi.repository.lancamento.LancamentoRepositoryQuery;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * LancamentoRepository
 */
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}