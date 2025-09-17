package com.booking.schedule.client;

import org.example.dto.BusRequestDTO;
import org.example.dto.BusResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="vendorservice",contextId = "BusClient", path="/api/bus")
public interface BusClient {
    @GetMapping("/getbyid/{id}")
    Boolean getBusById(@PathVariable("id") Integer id);


    @PostMapping ("/getbuses")
    ResponseEntity<List<BusResponseDTO>> getbuses(@RequestBody BusRequestDTO busRequestDTO);
    

}


