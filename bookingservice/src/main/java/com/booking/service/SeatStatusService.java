package com.booking.service;

import com.booking.entity.SeatStatusEntity;
import com.booking.repository.SeatStatusRepository;
import org.example.dto.BookingRequestDTO;
import org.example.dto.BookingResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatStatusService {
    @Autowired
    public SeatStatusRepository seatStatusRepository;

    public String addseats(SeatStatusEntity seatStatusEntity)
    {
        seatStatusRepository.save(seatStatusEntity);
        return "done";
    }

   public List<BookingResponseDTO> getRemainingSeats(BookingRequestDTO requestDTO){
       List<SeatStatusEntity> seatStatusEntityList =seatStatusRepository.findByBusIdInAndDate(requestDTO.getBusids(),requestDTO.getScheduledate());

           return seatStatusEntityList.stream()
                   .filter(seatStatusEntity -> seatStatusEntity.getRemainingSeats()>0).map(seatStatusEntity -> {
                   BookingResponseDTO responseDTO=new BookingResponseDTO();
                   responseDTO.busid=seatStatusEntity.getBusId();
                   responseDTO.remainingseats=seatStatusEntity.getRemainingSeats();
                   return responseDTO;
               }).collect(Collectors.toList());
   }

   public List<SeatStatusEntity> getAll(){
        return seatStatusRepository.findAll();
   }

}
