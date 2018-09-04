package com.dtel.springrestfull.springrestfullapi.teste;

import com.dtel.springrestfull.springrestfullapi.model.Lancamento;

/**
 * teste
 */
public class Teste {

    public static void main(String[] args) {
        System.out.println(Lancamento.class.getDeclaredFields()[7].getName());
    }
}