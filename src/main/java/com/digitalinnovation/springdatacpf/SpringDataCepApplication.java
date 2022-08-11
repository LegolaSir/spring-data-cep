package com.digitalinnovation.springdatacpf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class SpringDataCepApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataCepApplication.class, args);
	}

}
