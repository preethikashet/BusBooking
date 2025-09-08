package com.booking;

import org.example.dto.BookingRequestDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableFeignClients
@EnableWebMvc
public class BookingserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookingserviceApplication.class, args);

	}

}
