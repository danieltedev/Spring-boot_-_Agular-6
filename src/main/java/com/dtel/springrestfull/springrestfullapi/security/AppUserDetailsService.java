package com.dtel.springrestfull.springrestfullapi.security;

import java.util.Collection;
import java.util.stream.Collectors;

import com.dtel.springrestfull.springrestfullapi.model.Usuario;
import com.dtel.springrestfull.springrestfullapi.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * AppUserDetailsService
 */
@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha inválidos!"));

        return new UsuarioDetalhe(usuario, getPermissoes(usuario));
    }

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
        return usuario.getPermissoes().stream()
            .map(p ->  new SimpleGrantedAuthority(p.getDescricao()))
            .collect(Collectors.toList());
	}
}