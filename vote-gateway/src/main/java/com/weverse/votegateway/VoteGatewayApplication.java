package com.weverse.votegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.weverse.votegateway.config.properties")
public class VoteGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(VoteGatewayApplication.class, args);
	}

}
