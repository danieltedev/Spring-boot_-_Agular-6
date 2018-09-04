package com.dtel.springrestfull.springrestfullapi.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

/**
 * ResourceCreateEvent
 */
public class ResourceCreateEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;
    
    private HttpServletResponse response;
    private Long id;

    public ResourceCreateEvent(Object source, HttpServletResponse response, Long id) {
        super(source);
        this.response = response;
        this.id = id;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the response
     */
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}