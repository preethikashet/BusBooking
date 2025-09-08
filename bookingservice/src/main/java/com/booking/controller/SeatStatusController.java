package com.booking.controller;


import com.booking.entity.BookingEntity;
import com.booking.entity.SeatStatusEntity;
import com.booking.service.BookingService;
import com.booking.service.SeatStatusService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.dto.BookingRequestDTO;
import org.example.dto.BookingResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seatstatus")
public class SeatStatusController {
    @Autowired
    private SeatStatusService seatStatusService ;

    /**
     *
     * @param seatStatusEntity
     * @return
     */
    @PostMapping("/add")
    public String addseatstatus(@RequestBody SeatStatusEntity seatStatusEntity){
        return seatStatusService.addseats(seatStatusEntity);
    }

    /**
     *
     * @param requestDTO
     * @return
     */
    @PostMapping("/getseatstatusforids")
    public ResponseEntity<List<BookingResponseDTO>> getRemainingSeats(@RequestBody BookingRequestDTO requestDTO){
        List<BookingResponseDTO> bookingResponseDTOList = seatStatusService.getRemainingSeats(requestDTO);
        return ResponseEntity.ok(seatStatusService.getRemainingSeats(requestDTO));
    }

}
