package com.dtel.springrestfull.springrestfullapi.security;

import java.util.Collection;

import com.dtel.springrestfull.springrestfullapi.model.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * UsuarioDetalhe
 */
public class UsuarioDetalhe extends User {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    public UsuarioDetalhe(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getEmail(), usuario.getSenha(), authorities);
        this.usuario = usuario;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }
}