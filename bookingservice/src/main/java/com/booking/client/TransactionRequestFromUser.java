package com.booking.client;

import org.example.dto.TransactionResponseDTO;
import org.example.dto.UserBookBusRequestDTO;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "paymentservice",path = "/api/payment")
public interface TransactionRequestFromUser {
    @PostMapping("/makeTransaction")
    TransactionResponseDTO makeTransaction(@RequestBody UserBookBusRequestDTO request);
}

