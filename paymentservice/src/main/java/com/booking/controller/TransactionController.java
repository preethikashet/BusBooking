package com.booking.controller;

import com.booking.entity.TransactionEntity;
import com.booking.service.TransactionService;
import org.example.dto.TransactionResponseDTO;
import org.example.dto.UserBookBusRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    /**
     *
     * @param transactionEntity
     * @return
     */
    @PostMapping("/add")
    public  String addtransaction(@RequestBody TransactionEntity transactionEntity){
        return transactionService.addtransaction(transactionEntity);
    }

    /**
     *
     * @return
     */
    @GetMapping("/data")
    public ResponseEntity<List<TransactionEntity>> getpayment() {
        return ResponseEntity.ok(transactionService.getallpayments());
    }

    /**
     *
     * @param requestDTO
     * @return
     */
    @PostMapping("/processBooking")
    public TransactionResponseDTO processBooking(@RequestBody UserBookBusRequestDTO requestDTO)
    {
        return transactionService.processBooking(requestDTO);
    }

}
