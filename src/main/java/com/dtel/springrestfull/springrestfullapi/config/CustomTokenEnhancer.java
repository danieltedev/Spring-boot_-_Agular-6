package com.dtel.springrestfull.springrestfullapi.config;

import java.util.HashMap;
import java.util.Map;

import com.dtel.springrestfull.springrestfullapi.security.UsuarioDetalhe;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 * CustomTokenEnhancer
 */
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();
        UsuarioDetalhe usuarioDetalhe = (UsuarioDetalhe) authentication.getPrincipal();
		additionalInfo.put("name", usuarioDetalhe.getUsuario().getNome());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
	}

    
}