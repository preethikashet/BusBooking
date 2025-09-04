package com.booking.controller;

import com.booking.entity.BookingEntity;
import com.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/add")
    public  String addbooking(@RequestBody BookingEntity bookingEntity){
        return bookingService.addbooking(bookingEntity);
    }

    @GetMapping("/data")
    public ResponseEntity<List<BookingEntity>> getbooking() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
}
