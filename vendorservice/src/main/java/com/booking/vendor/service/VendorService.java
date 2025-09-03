package com.booking.vendor.service;


import com.booking.vendor.entity.Vendor;
import com.booking.vendor.repository.VendorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {
    @Autowired
    public VendorDAO vendorDAO;

    public String addVendor(Vendor vendor){
        vendorDAO.save(vendor);
        return "done";
    }

    public List<Vendor> getVendor(){
        return vendorDAO.findAll();
    }

}
