package com.gomawa.gomawa;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 어노테이션
 */
@SpringBootApplication
public class GomawaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GomawaApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
