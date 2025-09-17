package com.booking.controller;

import com.booking.client.TransactionRequestFromUser;
import com.booking.entity.BookingEntity;
import com.booking.service.BookingService;
import org.example.dto.TransactionResponseDTO;
import org.example.dto.UserBookBusRequestDTO;
import org.example.dto.UserBookBusResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/book")
public class BookingController {
    @Autowired
    private BookingService bookingService;



    @Autowired
    private TransactionRequestFromUser transactionRequestFromUser;

    @PostMapping("/add")
    public  String addbooking(@RequestBody BookingEntity bookingEntity){
        return bookingService.addbooking(bookingEntity);
    }

    @GetMapping("/data")
    public ResponseEntity<List<BookingEntity>> getbooking() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PostMapping("/bookBus")
    public ResponseEntity<UserBookBusResponseDTO> bookBus(@RequestBody UserBookBusRequestDTO request) {
        UserBookBusResponseDTO response = bookingService.bookbus(request);
        return ResponseEntity.ok(response);
    }



}
