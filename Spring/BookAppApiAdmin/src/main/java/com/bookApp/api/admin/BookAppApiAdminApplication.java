package com.bookApp.api.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BookAppApiAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookAppApiAdminApplication.class, args);
	}

}
