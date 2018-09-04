package com.dtel.springrestfull.springrestfullapi.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * CorsFilter
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    private String originPermitida = "*";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        servletResponse.setHeader("Access-Control-Allow-Origin", originPermitida);
        servletResponse.setHeader("Access-Control-Allow-Credential", "true");
        // && originPermitida.equals(servletRequest.getHeader("Origin"))
        if ("OPTIONS".equals(servletRequest.getMethod())) {
            servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
            servletResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
            servletResponse.setHeader("Access-Control-Max-Age","3600");

            servletResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }
	}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

	@Override
	public void destroy() {
		
	}

}