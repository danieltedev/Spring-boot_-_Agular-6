package com.dtel.springrestfull.springrestfullapi.repository;

import java.util.Optional;

import com.dtel.springrestfull.springrestfullapi.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UsuarioRepository
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);
}