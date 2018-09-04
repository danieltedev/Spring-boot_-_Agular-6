package com.dtel.springrestfull.springrestfullapi.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import com.dtel.springrestfull.springrestfullapi.event.ResourceCreateEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * ResourceCreateListener
 */
@Component
public class ResourceCreateListener implements ApplicationListener<ResourceCreateEvent> {

	@Override
	public void onApplicationEvent(ResourceCreateEvent event) {
        HttpServletResponse response = event.getResponse();
        Long id = event.getId();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
            .buildAndExpand(id).toUri();
        response.setHeader("Location", uri.toASCIIString());
	}

    
}