package com.booking.vendor.controller;


import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Vendor;
import com.booking.vendor.service.BusService;
import com.booking.vendor.service.VendorService;
import org.example.dto.BusRequestDTO;
import org.example.dto.BusResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bus")
public class BusController {

    @Autowired
    private BusService busService;


    @GetMapping("/get")
    public ResponseEntity<List<Bus>> getBus() {
        return ResponseEntity.ok(busService.getBus());
    }

    /**
     *
     * @param bus
     * @return
     */
    @PostMapping("/add")
    public String addBus(@RequestBody Bus bus){
        return busService.addBus(bus);

    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/getbyid/{id}")
    public Boolean getBusById(@PathVariable Integer id)
    {
        return busService.getBusById(id);
    }

    @PostMapping("/getbuses")
    public ResponseEntity<List<BusResponseDTO>> getbuses(@RequestBody BusRequestDTO busRequestDTO){
        return ResponseEntity.ok(busService.getBuses(busRequestDTO));
    }




}
