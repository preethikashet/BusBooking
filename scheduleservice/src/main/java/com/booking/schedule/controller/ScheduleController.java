package com.booking.schedule.controller;

import com.booking.schedule.entity.Schedule;
import com.booking.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schdeule")
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






}
