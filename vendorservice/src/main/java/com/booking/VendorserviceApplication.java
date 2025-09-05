package com.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VendorserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendorserviceApplication.class, args);
	}

}
