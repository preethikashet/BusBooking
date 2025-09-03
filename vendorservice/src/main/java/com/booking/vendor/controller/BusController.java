package com.booking.vendor.controller;


import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Vendor;
import com.booking.vendor.service.BusService;
import com.booking.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping("/data")
    public ResponseEntity<List<Bus>> getBus() {
        return ResponseEntity.ok(busService.getBus());
    }


    @PostMapping("/add")
    public String addVendor(@RequestBody Bus bus){
        return busService.addBus(bus);

    }


}
