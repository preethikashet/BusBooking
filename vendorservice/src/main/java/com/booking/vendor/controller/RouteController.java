package com.booking.vendor.controller;


import com.booking.vendor.entity.Route;
import com.booking.vendor.service.RouteService;
import org.example.dto.RouteRequestDTO;
import org.example.dto.vendor.VendorIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/get")
    public ResponseEntity<List<Route>> getRoute() {
        return ResponseEntity.ok(routeService.getRoute());
    }

    /**
     * @param route
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Route> addRoute(@RequestBody Route route){
        routeService.addRoute(route);

        return ResponseEntity.ok(route);

    }

    /**
     *
     * @param routeRequestDTO
     * @return
     */
    @PostMapping("/getrouteid")
    public ResponseEntity<Integer> getRoute(@RequestBody RouteRequestDTO routeRequestDTO){
        return ResponseEntity.ok(routeService.getRouteId(routeRequestDTO.source, routeRequestDTO.destination));
    }

    @PostMapping("/getroutebyid")
    public ResponseEntity<List<Route>> getBusByVendorIdRoute(@RequestBody VendorIdDTO vendorIdDTO) {
        List<Route> buses = routeService.getRouteByVendor(vendorIdDTO.getVendorid());
        return ResponseEntity.ok(buses);
    }



}
