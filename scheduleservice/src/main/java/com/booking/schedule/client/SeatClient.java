package com.booking.schedule.client;

import org.example.dto.BookingRequestDTO;
import org.example.dto.BookingResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="busbooking",url="localhost:8081/api/seatstatus")
public interface SeatClient {
    @PostMapping("/getseatstatusforids")
    public List<BookingResponseDTO> getRemainingSeats(@RequestBody BookingRequestDTO requestDTO);

    }
