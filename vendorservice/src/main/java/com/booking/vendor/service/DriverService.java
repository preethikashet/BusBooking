package com.booking.vendor.service;


import com.booking.vendor.entity.Driver;
import com.booking.vendor.repository.DriverDAO;
import org.example.dto.DriverResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {
    @Autowired
    public DriverDAO driverDAO;

    public Driver addDriver(Driver driver){

        return  driverDAO.save(driver);
    }

    public List<Driver> getDriver(){
        return driverDAO.findAll();
    }

    public String deleteDriver(Integer id) {
        driverDAO.deleteById(id);
        return "deleted";
    }


    public List<DriverResponseDTO> getDriverDetails(List<Integer> driverids) {
        List<Driver> drivers = driverDAO.findByDriveridIn(driverids);

        // Use streams and BeanUtils to convert each Driver to a DriverRequestDTO
        return drivers.stream()
                .map(driver -> {
                    DriverResponseDTO dto = new DriverResponseDTO();
                    BeanUtils.copyProperties(driver, dto);
                    dto.setDrivernumber(driver.phoneno);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<Driver> getDriverByVendor(Integer vendorid) {
        return driverDAO.findByVendorid(vendorid);
    }
}
