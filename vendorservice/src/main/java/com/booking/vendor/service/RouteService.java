package com.booking.vendor.service;


import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Route;
import com.booking.vendor.repository.BusDAO;
import com.booking.vendor.repository.RouteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {
    @Autowired
    public RouteDAO routeDAO;

    public String addRoute(Route route){
        routeDAO.save(route);
        return "done";
    }

    public List<Route> getRoute(){
        return routeDAO.findAll();
    }

}
