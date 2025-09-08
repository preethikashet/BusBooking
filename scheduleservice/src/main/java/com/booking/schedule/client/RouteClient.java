package com.booking.schedule.client;

import org.example.dto.RouteRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="vendorservice",contextId = "RouteClient", path="/api/route")
public interface RouteClient {
    @PostMapping ("/getrouteid")
    ResponseEntity<Integer> getRoute(@RequestBody RouteRequestDTO routeRequestDTO);
}
