package com.booking.schedule.client;

import org.example.dto.BusResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name="vendorservice",url="localhost:8084/api/bus")
public interface VendorClient {
    @PostMapping("/getbuses")
    public List<BusResponseDTO> getBus(List<Integer> l);
}
