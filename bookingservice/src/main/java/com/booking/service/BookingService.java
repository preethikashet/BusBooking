package com.booking.service;

import com.booking.dto.BookingDTO;
import com.booking.entity.BookingEntity;
import com.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookingService {
    @Autowired
    public BookingRepository bookingRepository;

    public String addbooking(BookingEntity bookingEntity)
    {
        bookingRepository.save(bookingEntity);
        return "done";
    }

    public List<BookingEntity> getAllBookings(){
        return bookingRepository.findAll();
    }
}
