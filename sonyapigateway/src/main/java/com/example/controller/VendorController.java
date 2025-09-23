package com.example.controller;

import org.example.dto.vendor.BusDTO;
import org.example.dto.vendor.DriverDTO;
import org.example.dto.vendor.RouteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})

public class VendorController {

    @Autowired
    VendorClient vendorClient;

    @PostMapping("/bus/add")
    public String addBus(@RequestBody BusDTO bus){
        return vendorClient.addBus(bus);
    }

    @PostMapping("/route/add")
    public String addRoute(@RequestBody RouteDTO route){
        return vendorClient.addRoute(route);
    }

    @PostMapping("/driver/add")
    public String addDriver(@RequestBody DriverDTO driver){
        return vendorClient.addDriver(driver);
    }


}



