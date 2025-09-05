package com.booking.vendor.controller;


import com.booking.vendor.entity.Vendor;
import com.booking.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @GetMapping("/get")
    public ResponseEntity<List<Vendor>> getVendor() {
        return ResponseEntity.ok(vendorService.getVendor());
    }


    @PostMapping("/add")
    public String addVendor(@RequestBody Vendor vendor){
        return vendorService.addVendor(vendor);

    }


}
