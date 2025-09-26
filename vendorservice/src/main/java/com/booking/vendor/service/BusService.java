package com.booking.vendor.service;


import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Vendor;
import com.booking.vendor.repository.BusDAO;
import com.booking.vendor.repository.VendorDAO;
import org.example.dto.BusRequestDTO;


import org.example.dto.BusResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BusService {
    @Autowired
    public BusDAO busDAO;

    @Autowired
    public VendorDAO vendorDAO;

    public String addBus(Bus bus){
        busDAO.save(bus);
        return "done";
    }

    public List<Bus> getBus(){
        return busDAO.findAll();
    }

    public List<BusResponseDTO> getBuses(BusRequestDTO busRequestDTO){
        // Fetch all buses with the given bus IDs
        List<Bus> buses = busDAO.findByBusidIn(busRequestDTO.busids);

        // Extract vendor IDs directly from the list of buses
        List<Integer> vendorIds = buses.stream()
                .map(Bus::getVendorid)
                .distinct()
                .collect(Collectors.toList());

        // Fetch all vendors at once
        Map<Integer, String> vendorNames = vendorDAO.findAllById(vendorIds).stream()
                .collect(Collectors.toMap(Vendor::getVendorid, Vendor::getVendorname));

        // Map each bus to a BusResponseDTO in one step
        return buses.stream()
                .map(bus -> {
                    BusResponseDTO dto = new BusResponseDTO();
                    dto.setBusid(bus.getBusid());
                    dto.setVendorname(vendorNames.get(bus.getVendorid()));
                    dto.setBusno(bus.getBusnumber());
                    dto.setTotalseats(bus.getTotalseats());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<Bus> getBusByVendor(Integer vendorId){
        return busDAO.findByVendorid(vendorId);
    }

    public Boolean getBusById(Integer busid)
    {
        return busDAO.existsById(busid);
    }
//    public Optional<Bus> getBusById(Integer busid)
//    {
//        return busDAO.findById(busid);
//    }

}
