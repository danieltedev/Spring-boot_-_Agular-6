package com.dtel.springrestfull.springrestfullapi.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dtel.springrestfull.springrestfullapi.config.property.DtelApiProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TokenResource
 */
@RestController
@RequestMapping("/tokens")
public class TokenResource {

    @Autowired
    private DtelApiProperty dtelApiProperty;

    @DeleteMapping("/revoke")
    public void revoke(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(dtelApiProperty.getSeguranca().isEnableHttps());
        cookie.setPath(servletRequest.getContentType() + "/oauth/token");
        cookie.setMaxAge(0);
        servletResponse.addCookie(cookie);
        servletResponse.setStatus(HttpStatus.NO_CONTENT.value());
    }
}