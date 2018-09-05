package com.dtel.springrestfull.springrestfullapi;

import com.dtel.springrestfull.springrestfullapi.config.property.DtelApiProperty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DtelApiProperty.class)
public class SpringRestfullApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestfullApiApplication.class, args);
	}
}
