package com.bookApp.api.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookAppApiUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookAppApiUsersApplication.class, args);
	}

}
