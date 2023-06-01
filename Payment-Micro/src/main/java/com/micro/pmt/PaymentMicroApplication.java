package com.micro.pmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentMicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentMicroApplication.class, args);
	}

}
