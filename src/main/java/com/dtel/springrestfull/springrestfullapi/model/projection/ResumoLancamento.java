package com.dtel.springrestfull.springrestfullapi.model.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import com.dtel.springrestfull.springrestfullapi.model.Lancamento;
import com.dtel.springrestfull.springrestfullapi.model.TipoLancamento;

/**
 * ResumoLancamento
 */
public class ResumoLancamento {

    private Long id;
    private String descricao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
    private String observacao;
    private TipoLancamento tipo;
    private String categoria;
    private String pessoa;

    public ResumoLancamento(Lancamento lancamento) {
        this.id = lancamento.getId();
        this.descricao = lancamento.getDescricao();
        this.dataVencimento = lancamento.getDataVencimento();
        this.dataPagamento = lancamento.getDataPagamento();
        this.valor = lancamento.getValor();
        this.observacao = lancamento.getObservacao();
        this.tipo = lancamento.getTipo();
        this.categoria = Objects.isNull(lancamento.getCategoria()) ? null : lancamento.getCategoria().getNome();
        this.pessoa = Objects.isNull(lancamento.getPessoa()) ? null : lancamento.getPessoa().getNome();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the dataVencimento
     */
    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    /**
     * @param dataVencimento the dataVencimento to set
     */
    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    /**
     * @return the dataPagamento
     */
    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    /**
     * @param dataPagamento the dataPagamento to set
     */
    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    /**
     * @return the valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the tipo
     */
    public TipoLancamento getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the pessoa
     */
    public String getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }
}