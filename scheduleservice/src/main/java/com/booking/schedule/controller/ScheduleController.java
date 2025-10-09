package com.booking.schedule.controller;

import com.booking.schedule.entity.Schedule;
import com.booking.schedule.service.ScheduleService;
import feign.Param;
import jakarta.validation.Valid;
import org.example.dto.RouteRequestDTO;
import org.example.dto.UserResponseDTO;
import org.example.dto.schedule.ScheduleDTO;
import org.hibernate.annotations.GenericGenerator;

import org.example.dto.BookingResponseDTO;
import org.example.dto.BusResponseDTO;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    @Autowired
    public ScheduleService scheduleService;

    /**
     *
     * @param schedule
     * @return
     */
    @PostMapping("/addschedule")
    public ResponseEntity<Schedule> addS(@RequestBody ScheduleDTO schedule){
        Schedule sch = new Schedule();
        System.out.println(sch.scheduleid);
        sch.setBusid(schedule.busid);
        sch.setRouteid(schedule.routeid);
        sch.setArrivaltime(schedule.arrivaltime);
        sch.setDeparturetime(schedule.departuretime);
        sch.setDriverid(schedule.driverid);
        sch.setPrice(schedule.price);
        scheduleService.addSchedule(sch);
        return ResponseEntity.ok(sch);
    }

    @GetMapping("/getSchedule")
    public ResponseEntity<List<Schedule>> getSchedules()
    {
        return ResponseEntity.ok(scheduleService.getSchedules());
    }


    /**
     *
     * @return
     */
    @GetMapping("/test")
    public String test() {
        return "Test/test";
    }



   @GetMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> searchBus(@RequestParam String src,@RequestParam String dst,@RequestParam String bookingdate)
   {
     return  ResponseEntity.ok(scheduleService.getSchedules(src, dst, bookingdate));

   }







}
