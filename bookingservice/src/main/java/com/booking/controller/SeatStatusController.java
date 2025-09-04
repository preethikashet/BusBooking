package com.booking.controller;


import com.booking.entity.BookingEntity;
import com.booking.entity.SeatStatusEntity;
import com.booking.service.BookingService;
import com.booking.service.SeatStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seatstatus")
public class SeatStatusController {
    @Autowired
    private SeatStatusService seatStatusService ;

    @PostMapping("/add")
    public String addseatstatus(@RequestBody SeatStatusEntity seatStatusEntity){
        return seatStatusService.addseats(seatStatusEntity);
    }
    @GetMapping("/data")
    public ResponseEntity<List<SeatStatusEntity>> getseats() {
        return ResponseEntity.ok(seatStatusService.getAllSeats());
    }

}
