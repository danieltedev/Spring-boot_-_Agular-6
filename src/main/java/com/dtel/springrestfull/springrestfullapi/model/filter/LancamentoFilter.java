package com.dtel.springrestfull.springrestfullapi.model.filter;

import java.time.LocalDate;

import com.dtel.springrestfull.springrestfullapi.model.Lancamento;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * LancamentoFilter
 */
public class LancamentoFilter extends Lancamento {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVencimentoDe;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVencimentoAte;

    /**
     * @return the dataVencimentoAte
     */
    public LocalDate getDataVencimentoAte() {
        return dataVencimentoAte;
    }

    /**
     * @param dataVencimentoAte the dataVencimentoAte to set
     */
    public void setDataVencimentoAte(LocalDate dataVencimentoAte) {
        this.dataVencimentoAte = dataVencimentoAte;
    }

    /**
     * @return the dataVencimentoDe
     */
    public LocalDate getDataVencimentoDe() {
        return dataVencimentoDe;
    }

    /**
     * @param dataVencimentoDe the dataVencimentoDe to set
     */
    public void setDataVencimentoDe(LocalDate dataVencimentoDe) {
        this.dataVencimentoDe = dataVencimentoDe;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}