package com.example.controller;

import org.example.dto.vendor.BusDTO;
import org.example.dto.vendor.DriverDTO;
import org.example.dto.vendor.RouteDTO;
import org.example.dto.vendor.VendorIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/bus/getbusesbyid")
    public ResponseEntity<List<BusDTO>> getBusByVendorId(@RequestBody VendorIdDTO vendorIdDTO) {
        return vendorClient.getBusByVendorId(vendorIdDTO);
    }

    @PostMapping("/driver/getdriverbyid")
    public ResponseEntity<List<DriverDTO>> getBusByVendorIdDriver(@RequestBody VendorIdDTO vendorIdDTO){
        return  vendorClient.getBusByVendorIdDriver(vendorIdDTO);
    }

    @PostMapping("/route/getroutebyid")
    public ResponseEntity<List<RouteDTO>> getBusByVendorIdRoute(@RequestBody VendorIdDTO vendorIdDTO) {
        return vendorClient.getBusByVendorIdRoute(vendorIdDTO);
    }


}



