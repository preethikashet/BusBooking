package com.booking.vendor.service;


import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Vendor;
import com.booking.vendor.repository.BusDAO;
import com.booking.vendor.repository.VendorDAO;
import org.example.dto.BusRequestDTO;
import org.example.dto.BusResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<Bus> buses = busDAO.findByBusidIn(busRequestDTO.busids);

        List<Integer> vendorIds = buses.stream()
                .map(Bus::getVendorid)
                .distinct()
                .toList();

        List<Vendor> vendors = vendorDAO.findAllById(vendorIds);

        Map<Integer, String> names = vendors.stream()
                .collect(Collectors.toMap(Vendor::getVendorid, Vendor::getVendorname));

        List<BusResponseDTO> busResponse = new ArrayList<>();
        for(Bus bus: buses){
            BusResponseDTO busResponseDTO = new BusResponseDTO();
            busResponseDTO.setBusid(bus.getBusid());
            busResponseDTO.setVendorname(names.get(bus.getBusid()));
            busResponseDTO.setBusno(bus.getBusnumber());
            busResponse.add(busResponseDTO);
        }
        return busResponse;

    }

//    public Optional<Bus> getBusById(Integer busid)
//    {
//        return busDAO.findById(busid);
//    }

}
