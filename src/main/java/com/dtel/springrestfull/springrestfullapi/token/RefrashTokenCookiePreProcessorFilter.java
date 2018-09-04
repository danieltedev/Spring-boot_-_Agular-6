package com.dtel.springrestfull.springrestfullapi.token;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * RefrashTokenCookiePreProcessorFilter
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefrashTokenCookiePreProcessorFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        
        if ("/oauth/token".equalsIgnoreCase(servletRequest.getRequestURI())
                && "refresh_token".equals(servletRequest.getParameter("grant_type"))
                && servletRequest.getCookies() != null) {
            String refreshToken = Arrays.asList(servletRequest.getCookies())
                .stream().filter(c -> c.getName().equals("refreshToken"))
                .map(c -> c.getValue()).findFirst().orElse(null);
            servletRequest = new MyServletRequestWrapper(servletRequest, refreshToken);
        }

        chain.doFilter(servletRequest, response);
	}

	@Override
	public void destroy() {
		
	}

    static class MyServletRequestWrapper extends HttpServletRequestWrapper {
    
        private String refreshToken;

        public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
            super(request);

            this.refreshToken = refreshToken;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
            map.put("refresh_token", new String[]{this.refreshToken});
            map.setLocked(true);
            return map;
        }
    }
}