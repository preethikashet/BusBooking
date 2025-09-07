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

    @GetMapping("/test")
    public String bustest(){
        return "Inside test";
    }
    @GetMapping("/get")
    public ResponseEntity<List<Bus>> getBus() {
        return ResponseEntity.ok(busService.getBus());
    }


    @PostMapping("/add")
    public String addBus(@RequestBody Bus bus){
        return busService.addBus(bus);

    }

    @GetMapping("/getbyid/{id}")
    public Boolean getBusById(@PathVariable Integer id)
    {
        return busService.getBusById(id);
    }


}
