package com.booking.vendor.service;


import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Driver;
import com.booking.vendor.repository.BusDAO;
import com.booking.vendor.repository.DriverDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    @Autowired
    public DriverDAO driverDAO;

    public String addDriver(Driver driver){
        driverDAO.save(driver);
        return "done";
    }

    public List<Driver> getDriver(){
        return driverDAO.findAll();
    }

    public String deleteDriver(Integer id) {
        driverDAO.deleteById(id);
        return "deleted";
    }
}
