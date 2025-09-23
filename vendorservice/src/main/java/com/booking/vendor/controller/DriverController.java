package com.booking.vendor.controller;


import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Driver;
import com.booking.vendor.entity.Route;
import com.booking.vendor.service.DriverService;
import com.booking.vendor.service.RouteService;
import org.example.dto.DriverRequestDTO;
import org.example.dto.DriverResponseDTO;
import org.example.dto.vendor.VendorIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    /**
     *
     * @return
     */
    @GetMapping("/get")
    public ResponseEntity<List<Driver>> getDriver() {
        return ResponseEntity.ok(driverService.getDriver());
    }

    /**
     *
     * @param driver
     * @return
     */
    @PostMapping("/add")
    public String addDriver(@RequestBody Driver driver){
        return driverService.addDriver(driver);

    }

    @DeleteMapping("/delete/{id}")
    public String deleteDriver(@PathVariable Integer id)
    {
        return driverService.deleteDriver(id);
    }

    /**
     *
     * @param driverRequestDTO
     * @return
     */
    @PostMapping("/getdrivers")
    public ResponseEntity<List<DriverResponseDTO>> getDriver(@RequestBody DriverRequestDTO driverRequestDTO){
        return ResponseEntity.ok(driverService.getDriverDetails(driverRequestDTO.driverids));
    }

    @PostMapping("/getdriverbyid")
    public ResponseEntity<List<Driver>> getBusByVendorIdDriver(@RequestBody VendorIdDTO vendorIdDTO) {
        List<Driver> buses = driverService.getDriverByVendor(vendorIdDTO.getVendorid());
        return ResponseEntity.ok(buses);
    }




}
