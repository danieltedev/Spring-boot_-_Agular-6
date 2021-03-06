package com.dtel.springrestfull.springrestfullapi.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dtel.springrestfull.springrestfullapi.config.property.DtelApiProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * RefreshTokenPostProcessor
 */
@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {

    @Autowired
    private DtelApiProperty dtelApiProperty;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getMethod().getName().equals("postAccessToken");
    }

    @Override
	public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {

        HttpServletRequest servletRequest = ((ServletServerHttpRequest)request).getServletRequest();
        HttpServletResponse servletResponse = ((ServletServerHttpResponse)response).getServletResponse();

        String refreshToken = body.getRefreshToken().getValue();
        addRefreshTokenInCookie(refreshToken, servletRequest, servletResponse);

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;
        removeRefreshTokenInBody(token);
        
        return body;
    }

    private void removeRefreshTokenInBody(DefaultOAuth2AccessToken token) {
        token.setRefreshToken(null);
    }

    private void addRefreshTokenInCookie(String refreshToken, HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(dtelApiProperty.getSeguranca().isEnableHttps()); 
        refreshTokenCookie.setPath(servletRequest.getContextPath() + "/oauth/token");
        refreshTokenCookie.setMaxAge(2595000);
        servletResponse.addCookie(refreshTokenCookie);
    }

}