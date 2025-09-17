package com.booking.schedule.service;

import com.booking.schedule.client.BusClient;
//import com.booking.schedule.client.VendorClient;

import com.booking.schedule.client.DriverClient;
import com.booking.schedule.client.RouteClient;
import com.booking.schedule.client.SeatClient;

import com.booking.schedule.entity.Schedule;
//import com.booking.schedule.client.BusClient;
//import com.booking.schedule.client.RouteClient;
import com.booking.schedule.repository.Schedulerepository;

import org.example.dto.*;
import org.example.dto.UserResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    public Schedulerepository schedulerepository;
    @Autowired
    public BusClient busClient;
    @Autowired
    public DriverClient driverClient;

    @Autowired
    public SeatClient seatClient;

//    public VendorClient vendorClient;
    @Autowired
    public RouteClient routeClient;


    public String addSchedule(Schedule schedule)
    {
//        if(!busClient.getBusById(schedule.getBusid()))
//            throw new RuntimeException("Bus with given busid doesnot exists");

        //creating seat status
        CreateSeatStatusDTO dto = new CreateSeatStatusDTO();

        //coping data
        dto.setSeatstatusId(schedule.getScheduleid());
        dto.setDate(LocalDate.from(schedule.getDeparturetime()));
        dto.setBusId(schedule.getBusid());
        //getting total seat from vendorclient
        dto.setTotalseats(Objects.requireNonNull(busClient.getbuses(new BusRequestDTO(List.of(1))).getBody()).get(0).getTotalseats());
        //calling seatClient to save
        seatClient.addseatstatus( dto);

        //creating schedule
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
         Integer routeid = routeClient.getRoute(routeRequestDTO).getBody();
         System.out.println(routeid);
         return routeid;
     }


     public List<UserResponseDTO> getSchedules(String src, String dest, String date){
         RouteRequestDTO routeRequestDTO = new RouteRequestDTO(src, dest);
         Integer routeid = getRoute(routeRequestDTO);
         LocalDate bookingDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
         List<Schedule> scheduleList = schedulerepository.findBusIdsByRouteAndDate(routeid,bookingDate);
         System.out.println("length "+scheduleList.size());
         //get all bus details
         List<Integer> allbusids = scheduleList.stream().map(Schedule::getBusid).toList();



         //checking if there are free seats
         List<BookingResponseDTO> bookingResponseDTOList=
                 getRemainingSeats(
                         new BookingRequestDTO(
                                bookingDate,
                                 allbusids
                         ));

         List<Integer> bookingbusids = bookingResponseDTOList.stream().map(BookingResponseDTO::getBusid).toList();
         List<Schedule> filteredSchedules = scheduleList.stream()
                 .filter(schedule -> bookingbusids.contains(schedule.busid))  // Check if busId is present in the response DTO
                 .toList();


         System.out.println("length filtered "+filteredSchedules.size());



         //get all driver details
         List<Integer> busids = bookingResponseDTOList.stream().map(BookingResponseDTO::getBusid).toList();
         List<Integer> driverids = filteredSchedules.stream().map(Schedule::getDriverid).toList();
         BusRequestDTO busRequestDTO = new BusRequestDTO(busids);
         List<BusResponseDTO> busResponseDTOList = getBusDetails(busRequestDTO);
         DriverRequestDTO driverRequestDTO = new DriverRequestDTO(driverids);
         List<DriverResponseDTO> driverResponseDTOList = getDriverDetails(driverRequestDTO);

         Map<Integer, BusResponseDTO> busMap = busResponseDTOList.stream()
                 .collect(Collectors.toMap(b -> b.busid, b -> b));

         Map<Integer, DriverResponseDTO> driverMap = driverResponseDTOList.stream()
                 .collect(Collectors.toMap(d -> d.driverid, d -> d));

         Map<Integer, BookingResponseDTO> bookingMap = bookingResponseDTOList.stream()
                 .collect(Collectors.toMap(b -> b.busid, b -> b));

         // 10. Construct UserResponseDTO
         List<UserResponseDTO> userResponseList = filteredSchedules.stream()
                 .map(schedule -> {
                     BusResponseDTO bus = busMap.get(schedule.getBusid());
                     DriverResponseDTO driver = driverMap.get(schedule.getDriverid());
                     BookingResponseDTO booking = bookingMap.get(schedule.getBusid());

                     UserResponseDTO dto = new UserResponseDTO();
                     dto.busnumber = bus.busno;
                     dto.vendorname = bus.vendorname;
                     dto.drivernumber = driver.drivernumber;
                     dto.remainingseats = booking.remainingseats;
                     dto.arrival = schedule.getArrivaltime();
                     dto.departure = schedule.getDeparturetime();
                     dto.scheduleid = schedule.getScheduleid();
                     dto.price = schedule.getPrice();
                     dto.busid=bus.busid;
                     return dto;
                 })
                 .toList();

         return userResponseList;


     }

    private List<DriverResponseDTO> getDriverDetails(DriverRequestDTO driverRequestDTO) {
       return driverClient.getDriverDetails(driverRequestDTO).getBody();

    }


}
