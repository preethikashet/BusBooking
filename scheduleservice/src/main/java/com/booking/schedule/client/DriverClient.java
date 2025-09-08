package com.booking.schedule.client;

import org.example.dto.DriverRequestDTO;
import org.example.dto.DriverResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(name="vendorservice", url="/api/driver")
public interface DriverClient {
    @PostMapping("/getdrivers")
    ResponseEntity<List<DriverResponseDTO>> getDriverDetails(DriverRequestDTO driverRequestDTO);



}
