package com.cube.pod;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.ccube.pod.resource.ExotelController;


@Configuration
public class JerseyConfiguration extends ResourceConfig {

	public JerseyConfiguration() {
		register(ExotelController.class);

	}

}
