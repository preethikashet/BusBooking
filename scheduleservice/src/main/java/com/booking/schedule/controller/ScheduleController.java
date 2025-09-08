package com.booking.schedule.controller;

import com.booking.schedule.entity.Schedule;
import com.booking.schedule.service.ScheduleService;
import org.example.dto.BookingResponseDTO;
import org.example.dto.BusResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    @Autowired
    public ScheduleService scheduleService;

    @PostMapping("/addschedule")
    public String addS(@RequestBody Schedule schedule){
        return scheduleService.addSchedule(schedule);
    }

    @GetMapping("/getSchedule")
    public ResponseEntity<List<Schedule>> getSchedules()
    {
        return ResponseEntity.ok(scheduleService.getSchedules());
    }

    @GetMapping("/getBuses")
    public ResponseEntity<List<BusResponseDTO>> getBuses()
   {
       return ResponseEntity.ok(scheduleService.getBusDetails());
   }

   @GetMapping("/getR")
    public ResponseEntity<List<BookingResponseDTO>> getRemainingSeats()
   {
       return ResponseEntity.ok(scheduleService.getRemainingSeats());
   }






}
