package com.bookApp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class BookAppApiConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookAppApiConfigServerApplication.class, args);
	}

}
