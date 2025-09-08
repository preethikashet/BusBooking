package com.booking.schedule.service;

import com.booking.schedule.client.BusClient;
//import com.booking.schedule.client.VendorClient;

import com.booking.schedule.client.RouteClient;
import com.booking.schedule.client.SeatClient;

import com.booking.schedule.entity.Schedule;
//import com.booking.schedule.client.BusClient;
//import com.booking.schedule.client.RouteClient;
import com.booking.schedule.repository.Schedulerepository;
import com.netflix.discovery.converters.Auto;
import org.example.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    public Schedulerepository schedulerepository;
    @Autowired
    public BusClient busClient;
    @Autowired
    public DriverClient

    @Autowired
    public SeatClient seatClient;

//    public VendorClient vendorClient;
    @Autowired
    public RouteClient routeClient;


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


    public List<BusResponseDTO> getBusDetails(BusRequestDTO busRequestDTO)
    {
        return busClient.getbuses(busRequestDTO).getBody();
    }


    public List<BookingResponseDTO> getRemainingSeats(BookingRequestDTO bookingRequestDTO)
    {
        return seatClient.getRemainingSeats(bookingRequestDTO);


    }

     public Integer getRoute(RouteRequestDTO routeRequestDTO)
     {
         return routeClient.getRoute(routeRequestDTO).getBody();
     }


     public List<UserResponseDTO> getSchedules(String src, String dest, String date){
         RouteRequestDTO routeRequestDTO = new RouteRequestDTO(src,dest);
         Integer routeid = getRoute(routeRequestDTO);
         LocalDate bookingDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-mm-yyyy"));
         List<Schedule> scheduleList = schedulerepository.findBusIdsByRouteAndDate(routeid,bookingDate);

         //get all bus details
         List<Integer> allbusids = scheduleList.stream().map(Schedule::getBusid).toList();



         //checking if there are free seats
         List<BookingResponseDTO> bookingResponseDTOList=
                 getRemainingSeats(
                         new BookingRequestDTO(
                                 Date.from(bookingDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                 allbusids
                         ));

         List<Schedule> filteredSchedules = scheduleList.stream()
                 .filter(schedule -> bookingResponseDTOList.contains(schedule.getBusid()))  // Check if busId is present in the response DTO
                 .toList();





         //get all driver details
         List<Integer> busids = bookingResponseDTOList.stream().map(BookingResponseDTO::getBusid).toList();
         List<Integer> driverids = filteredSchedules.stream().map(Schedule::getDriverid).toList();
         BusRequestDTO busRequestDTO = new BusRequestDTO(busids);
         List<BusResponseDTO> busResponseDTOList = getBusDetails(busRequestDTO);
         DriverRequestDTO driverRequestDTO = new DriverRequestDTO(driverids);
         List<DriverResponseDTO> driverResponseDTOList = getDriverDetails(driverRequestDTO)

        return null;


     }

    private List<DriverResponseDTO> getDriverDetails(DriverRequestDTO driverRequestDTO) {
       return dri

    }


}
