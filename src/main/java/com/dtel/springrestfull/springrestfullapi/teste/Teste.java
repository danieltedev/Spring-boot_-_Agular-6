package com.dtel.springrestfull.springrestfullapi.teste;

import com.dtel.springrestfull.springrestfullapi.model.Lancamento;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * teste
 */
public class Teste {

    public static void main(String[] args) {
        System.out.println(Lancamento.class.getDeclaredFields()[7].getName());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));
    }
}