package com.booking.vendor.controller;


import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Driver;
import com.booking.vendor.entity.Route;
import com.booking.vendor.service.DriverService;
import com.booking.vendor.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/get")
    public ResponseEntity<List<Driver>> getDriver() {
        return ResponseEntity.ok(driverService.getDriver());
    }


    @PostMapping("/add")
    public String addDriver(@RequestBody Driver driver){
        return driverService.addDriver(driver);

    }

    @DeleteMapping("/delete/{id}")
    public String deleteDriver(@PathVariable Integer id)
    {
        return driverService.deleteDriver(id);
    }


}
