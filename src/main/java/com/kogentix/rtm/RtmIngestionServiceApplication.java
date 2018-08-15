package com.kogentix.rtm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class RtmIngestionServiceApplication {
	private final Logger LOGGER = LoggerFactory.getLogger(RtmIngestionServiceApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(RtmIngestionServiceApplication.class, args);

	}
}
