package com.booking.vendor.controller;


import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Route;
import com.booking.vendor.service.BusService;
import com.booking.vendor.service.RouteService;
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


    @PostMapping("/add")
    public String addRoute(@RequestBody Route route){
        return routeService.addRoute(route);

    }


}
