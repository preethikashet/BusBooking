package com.booking.service;


import com.booking.client.TransactionRequestFromUser;
import com.booking.entity.BookingEntity;
import com.booking.entity.SeatStatusEntity;
import com.booking.repository.BookingRepository;
import com.booking.repository.SeatStatusRepository;
import org.example.dto.TransactionResponseDTO;
import org.example.dto.UserBookBusRequestDTO;
import org.example.dto.UserBookBusResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BookingService {
    @Autowired
    public BookingRepository bookingRepository;

    @Autowired
    public TransactionRequestFromUser trans;

    @Autowired
    public SeatStatusRepository seatStatusRepository;

    public String addbooking(BookingEntity bookingEntity)
    {
        bookingRepository.save(bookingEntity);
        return "done";
    }

    public List<BookingEntity> getAllBookings(){
        return bookingRepository.findAll();
    }


    public Object bookbus (UserBookBusRequestDTO requestDTO){

        TransactionResponseDTO transres = trans.makeTransaction(requestDTO);
        if (transres == null){
            return "Payment not succesfull";
        }

        BookingEntity booking = new BookingEntity();
        booking.setBookid(new Random().nextInt(100));
        booking.setUserid(transres.getUserid());
        booking.setTransactionid(transres.getTransactionid());
        booking.setScheduleid(transres.getScheduleid());
        bookingRepository.save(booking);

        SeatStatusEntity seat = seatStatusRepository.findByBusId(transres.getBusid());
        seat.setOccupiedseats(seat.getOccupiedseats() + 1);
        seatStatusRepository.save(seat);


        UserBookBusResponseDTO response = new UserBookBusResponseDTO();
        response.setUsername(transres.getUsername());
        response.setPrice(transres.getPrice());
        response.setBusnumber(transres.getBusnumber());
        response.setArrival(transres.getArrival());
        response.setDeparture(transres.getDeparture());
        response.setDrivernumber(transres.getDrivernumber());
        response.setVendorname(transres.getVendorname());


        return response;
    }
//    public UserBookBusResponseDTO bookbus(UserBookBusRequestDTO requestDTO) {
//        TransactionResponseDTO transres = trans.makeTransaction(requestDTO);
//
//        if (transres == null) {
//            // Payment failed, don't proceed with booking or seat update
//            throw new RuntimeException("Payment failed. Booking not created.");
//            // OR return a custom error response DTO instead of throwing:
//            // return null; // and handle null in controller
//        }
//
//        BookingEntity booking = new BookingEntity();
//        booking.setBookid(new Random().nextInt(100));
//        booking.setUserid(transres.getUserid());
//        booking.setTransactionid(transres.getTransactionid());
//        booking.setScheduleid(transres.getScheduleid());
//        bookingRepository.save(booking);
//
//        SeatStatusEntity seat = seatStatusRepository.findByBusId(transres.getBusid());
//        seat.setOccupiedseats(seat.getOccupiedseats() + 1);
//        seatStatusRepository.save(seat);
//
//        UserBookBusResponseDTO response = new UserBookBusResponseDTO();
//        response.setUsername(transres.getUsername());
//        response.setPrice(transres.getPrice());
//        response.setBusnumber(transres.getBusnumber());
//        response.setArrival(transres.getArrival());
//        response.setDeparture(transres.getDeparture());
//        response.setDrivernumber(transres.getDrivernumber());
//        response.setVendorname(transres.getVendorname());
//
//        return response;
//    }
}



