package com.booking.vendor.service;


import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Vendor;
import com.booking.vendor.repository.BusDAO;
import com.booking.vendor.repository.VendorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {
    @Autowired
    public BusDAO busDAO;

    public String addBus(Bus bus){
        busDAO.save(bus);
        return "done";
    }

    public List<Bus> getBus(){
        return busDAO.findAll();
    }

}
