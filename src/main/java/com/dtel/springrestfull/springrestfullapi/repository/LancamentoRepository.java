package com.dtel.springrestfull.springrestfullapi.repository;

import com.dtel.springrestfull.springrestfullapi.model.Lancamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * LancamentoRepository
 */
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, JpaSpecificationExecutor<Lancamento> {

}