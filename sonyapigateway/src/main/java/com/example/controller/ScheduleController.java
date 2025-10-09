package com.example.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.dto.UserResponseDTO;
import org.example.dto.schedule.ScheduleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})

public class ScheduleController {


    @Autowired
    ScheduleClientService scheduleClientService;
    @PostMapping("/addschedule")
    public ResponseEntity<ScheduleDTO> addS(@RequestBody ScheduleDTO schedule){

        return scheduleClientService.addS(schedule);
    }

}

@FeignClient(name = "scheduleservice",contextId = "ScheduleClientService",path = "/api/schedule")
interface ScheduleClientService{
    @PostMapping("/addschedule")
    public ResponseEntity<ScheduleDTO> addS(@RequestBody ScheduleDTO schedule);


    }
