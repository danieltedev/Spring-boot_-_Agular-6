package com.dtel.springrestfull.springrestfullapi.repository.specification;

import com.dtel.springrestfull.springrestfullapi.model.Lancamento;
import com.dtel.springrestfull.springrestfullapi.model.filter.LancamentoFilter;

import org.springframework.data.jpa.domain.Specification;

/**
 * LancamentoSpecA
 */
public class LancamentoSpec {

    private LancamentoFilter lancamentoFilter;

    public LancamentoSpec(LancamentoFilter lancamentoFilter) {
        this.lancamentoFilter = lancamentoFilter;
    }

    private Specification<Lancamento> descricao() {
        return (root, query, builder) -> builder.like(root.get("descricao"), lancamentoFilter.getDescricao());
    }

    private Specification<Lancamento> dataVencimento() {
        return (root, query, builder) -> builder.equal(root.get("dataVencimento"),
                lancamentoFilter.getDataVencimento());
    }

    private Specification<Lancamento> dataVencimentoDe() {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("dataVencimento"),
                lancamentoFilter.getDataVencimentoDe());
    }

    private Specification<Lancamento> dataVencimentoAte() {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("dataVencimento"),
                lancamentoFilter.getDataVencimentoAte());
    }

    private Specification<Lancamento> dataPagamento() {
        return (root, query, builder) -> builder.equal(root.get("dataPagamento"), lancamentoFilter.getDataPagamento());
    }

    private Specification<Lancamento> observacao() {
        return (root, query, builder) -> builder.like(root.get("observacao"), lancamentoFilter.getObservacao());
    }

    public Specification<Lancamento> biuld() {
        return Specification.where(descricao()).or(dataVencimento()).or(dataVencimentoDe()).or(dataVencimentoAte())
                .or(dataPagamento()).or(observacao());
    }
}