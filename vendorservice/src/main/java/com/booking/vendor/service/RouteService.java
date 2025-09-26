package com.booking.vendor.service;


import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Route;
import com.booking.vendor.repository.BusDAO;
import com.booking.vendor.repository.RouteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    public Integer getRouteId(String src, String dest){
         return routeDAO.findIdBySrcAndDest(src, dest);

    }


    public List<Route> getRouteByVendor(Integer vendorid) {
        return routeDAO.findByVendorid(vendorid);
    }
}
