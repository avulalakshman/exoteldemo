package com.ccube.pod;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.ccube.pod.resource.ExotelController;

@ApplicationPath("/exotel")
@Configuration
public class JerseyConfiguration extends ResourceConfig {

	public JerseyConfiguration() {
		register(ExotelController.class);
		
	}

}
