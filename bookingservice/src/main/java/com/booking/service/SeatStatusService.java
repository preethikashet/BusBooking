package com.booking.service;

import com.booking.entity.BookingEntity;
import com.booking.entity.SeatStatusEntity;
import com.booking.repository.BookingRepository;
import com.booking.repository.SeatStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatStatusService {
    @Autowired
    public SeatStatusRepository seatStatusRepository;

    public String addseats(SeatStatusEntity seatStatusEntity)
    {
        seatStatusRepository.save(seatStatusEntity);
        return "done";
    }

    public List<SeatStatusEntity> getAllSeats(){
        return seatStatusRepository.findAll();
    }

}
