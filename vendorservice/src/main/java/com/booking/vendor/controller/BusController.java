package com.booking.vendor.controller;


import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Vendor;
import com.booking.vendor.service.BusService;
import com.booking.vendor.service.VendorService;
import org.example.dto.BusRequestDTO;
import org.example.dto.BusResponseDTO;
import org.example.dto.vendor.BusDTO;
import org.example.dto.vendor.VendorIdDTO;
import org.springframework.beans.BeanUtils;
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
     * @param busDTO
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Bus> addBus(@RequestBody BusDTO busDTO){
        Bus bus = new Bus();
        bus.setBusnumber(busDTO.getBusnumber());
        bus.setTotalseats(busDTO.getTotalseats());
        bus.setVendorid(busDTO.getVendorid());
        System.out.println(bus.getBusid());
         busService.addBus(bus);
         return ResponseEntity.ok(bus);
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

    /**
     *
     * @param vendorIdDTO
     * @return
     */
    @PostMapping("/getbusesbyid")
    public ResponseEntity<List<Bus>> getBusByVendorId(@RequestBody VendorIdDTO vendorIdDTO) {
        List<Bus> buses = busService.getBusByVendor(vendorIdDTO.getVendorid());
        return ResponseEntity.ok(buses);
    }

    @PostMapping("/getbuses")
    public ResponseEntity<List<BusResponseDTO>> getbuses(@RequestBody BusRequestDTO busRequestDTO){
        return ResponseEntity.ok(busService.getBuses(busRequestDTO));
    }




}
