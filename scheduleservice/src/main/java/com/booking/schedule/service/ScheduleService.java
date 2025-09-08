package com.booking.schedule.service;

import com.booking.schedule.client.BusClient;
//import com.booking.schedule.client.VendorClient;

import com.booking.schedule.client.SeatClient;

import com.booking.schedule.entity.Schedule;
//import com.booking.schedule.client.BusClient;
//import com.booking.schedule.client.RouteClient;
import com.booking.schedule.repository.Schedulerepository;
import com.netflix.discovery.converters.Auto;
import org.example.dto.BookingRequestDTO;
import org.example.dto.BookingResponseDTO;
import org.example.dto.BusRequestDTO;
import org.example.dto.BusResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    public Schedulerepository schedulerepository;
    @Autowired
    public BusClient busClient;

    @Autowired
    public SeatClient seatClient;

//    public VendorClient vendorClient;
//    public RouteClient routeClient;


    public String addSchedule(Schedule schedule)
    {
        if(!busClient.getBusById(schedule.getBusid()))
            throw new RuntimeException("Bus with given busid doesnot exists");
//        if(!routeClient.checkRouteExists(schedule.getRouteid()))
//            throw new RuntimeException("Route with thos route id doesnot exists");
        schedulerepository.save(schedule);
        return "Schedule added";
    }

    public List<Schedule> getSchedules()
    {
        return schedulerepository.findAll();
    }


    public List<BusResponseDTO> getBusDetails()
    {
        List<Integer> busids = new ArrayList<>();
        BusRequestDTO busRequestDTO = new BusRequestDTO(busids);
        List<BusResponseDTO> busResponseDTOS=busClient.getbuses(busRequestDTO);
        return busResponseDTOS;

    }

    public List<BookingResponseDTO> getRemainingSeats()
    {
        Date dt = new Date();
        List<Integer> busids = new ArrayList<>();
        BookingRequestDTO bookingRequestDTO =new BookingRequestDTO();
        bookingRequestDTO.setScheduledate(dt);
        bookingRequestDTO.setBusids(busids);
        return seatClient.getRemainingSeats(bookingRequestDTO);


    }


//    public List<BusResponseDTO>  getBusDetails(){
//
//    }

//    public List<ScheduleDetails> getScheduleDetails()
//    {
//
//    }









}
