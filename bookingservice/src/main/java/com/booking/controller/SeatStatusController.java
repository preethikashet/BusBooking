package com.booking.controller;



import com.booking.entity.SeatStatusEntity;

import com.booking.service.SeatStatusService;

import org.example.dto.BookingRequestDTO;
import org.example.dto.BookingResponseDTO;
import org.example.dto.CreateSeatStatusDTO;
import org.springframework.beans.BeanUtils;
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
     * @param createSeatStatusDTO
     * @return
     */

    @PostMapping("/add")
    public String addseatstatus(@RequestBody CreateSeatStatusDTO createSeatStatusDTO){
        SeatStatusEntity seatStatusEntity = new SeatStatusEntity();
        BeanUtils.copyProperties(createSeatStatusDTO, seatStatusEntity);
        // creating occupied seats as zero
        seatStatusEntity.setOccupiedseats(0);
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

    /**
     *
     * @return
     */
    @GetMapping ("/getseats")
    public ResponseEntity<List<SeatStatusEntity>> getseats(){
        return ResponseEntity.ok(seatStatusService.getAll());
    }

}
