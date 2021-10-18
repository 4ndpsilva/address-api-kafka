package com.maddytec.addressapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AddressApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(AddressApiApplication.class, args);
	}
}