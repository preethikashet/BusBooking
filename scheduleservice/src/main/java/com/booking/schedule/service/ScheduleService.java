package com.booking.schedule.service;

import com.booking.schedule.entity.Schedule;
import com.booking.schedule.client.BusClient;
import com.booking.schedule.client.RouteClient;
import com.booking.schedule.repository.Schedulerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    public Schedulerepository schedulerepository;
    public BusClient busClient;
    public RouteClient routeClient;


    public String addSchedule(Schedule schedule)
    {
        if(!busClient.checkBusExists(schedule.getBusid()))
            throw new RuntimeException("Bus with given busid doesnot exists");
        if(!routeClient.checkRouteExists(schedule.getRouteid()))
            throw new RuntimeException("Route with thos route id doesnot exists");
        schedulerepository.save(schedule);
        return "Schedule added";
    }

    public List<Schedule> getSchedules()
    {
        return schedulerepository.findAll();
    }



}
