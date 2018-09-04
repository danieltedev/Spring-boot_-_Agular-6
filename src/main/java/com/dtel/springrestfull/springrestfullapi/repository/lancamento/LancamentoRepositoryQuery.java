package com.dtel.springrestfull.springrestfullapi.repository.lancamento;

import com.dtel.springrestfull.springrestfullapi.model.Lancamento;
import com.dtel.springrestfull.springrestfullapi.repository.filter.LancamentoFilter;
import com.dtel.springrestfull.springrestfullapi.repository.projection.ResumoLancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * LancamentoRepositoryQuery
 */
public interface LancamentoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable);
    public Page<ResumoLancamento> resumo(LancamentoFilter filter, Pageable pageable);
}