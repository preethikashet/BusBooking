package com.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableFeignClients
@SpringBootApplication
public class ScheduleserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleserviceApplication.class, args);
	}

}
