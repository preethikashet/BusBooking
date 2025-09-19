package com.booking.client;

import org.example.dto.UserBookBusRequestDTO;
import org.example.dto.UserBookBusResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="busbooking", path="/api/book")
public interface BookingClient {
    @PostMapping("/bookBus")
    public UserBookBusResponseDTO bookBus(@RequestBody UserBookBusRequestDTO request);

    }
