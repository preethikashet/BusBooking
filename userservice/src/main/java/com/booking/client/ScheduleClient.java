package com.booking.client;

import org.example.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="scheduleservice",contextId = "ScheduleClient", path="/api/schedule")
public interface ScheduleClient {

    @GetMapping("/search")
    List<UserResponseDTO> searchBus(@RequestParam("src") String src, @RequestParam("dst") String dst, @RequestParam("bookingdate") String bookingDate);

}
