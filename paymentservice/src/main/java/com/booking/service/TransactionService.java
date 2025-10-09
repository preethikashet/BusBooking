package com.booking.service;

import ch.qos.logback.core.joran.spi.ElementSelector;
import com.booking.entity.TransactionEntity;
import com.booking.repository.TransactionRepository;
import org.example.dto.TransactionResponseDTO;
import org.example.dto.UserBookBusRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class TransactionService {
    @Autowired
    public TransactionRepository transactionRepository;

//    @Autowired
//    public ResponseToBookingClient responseToBookingClient;

    public Random random = new Random();

    public String addtransaction(TransactionEntity transactionEntity) {
        transactionRepository.save(transactionEntity);
        return "done";
    }

    public List<TransactionEntity> getallpayments() {

        return transactionRepository.findAll();
    }

    public TransactionResponseDTO makeTransaction(UserBookBusRequestDTO requestDTO) {
        int bookingStatus = 1;

        TransactionEntity entity = new TransactionEntity();
        entity.setTransactionid(random.nextInt(100000));
        entity.setUserid(requestDTO.getUserid());
        entity.setPrice(requestDTO.getUserResponseDTO().getPrice());
        entity.setVendorname(requestDTO.getUserResponseDTO().getVendorname());
        entity.setTimestamp(LocalDateTime.now());
        entity.setNotificationstatus(bookingStatus);

        transactionRepository.save(entity);

        String message;
        TransactionResponseDTO response = new TransactionResponseDTO();

        response.setTransactionid(entity.getTransactionid());
        response.setUserid(entity.getUserid());
        response.setPrice(entity.getPrice());
        response.setScheduleid(requestDTO.getUserResponseDTO().getScheduleid());
        response.setVendorname(entity.getVendorname());
        response.setBusid(requestDTO.getUserResponseDTO().getBusid());
        response.setScheduleid(requestDTO.getUserResponseDTO().getScheduleid());
        response.setBusnumber(requestDTO.getUserResponseDTO().getBusnumber());
        response.setArrival(requestDTO.getUserResponseDTO().getArrival());
        response.setDeparture(requestDTO.getUserResponseDTO().getDeparture());
        response.setDrivernumber(requestDTO.getUserResponseDTO().getDrivernumber());
        response.setUsername(requestDTO.getUsername());


        if(bookingStatus!=1)
//        {
            response=null;
//          message= "Booking successful !! Transaction Id :" +entity.getTransactionid();
//           responseToBookingClient.sendNotification(entity.getUserid(),message);
//
//        }
//        else
//        {
//          message= "Booking failed !! Please try again later";
//          responseToBookingClient.sendNotification(entity.getUserid(),message);
//        }
//        System.out.println("Booking Status : " +message);

        return  response;
    }

}
