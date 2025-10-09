package com.booking.vendor.service;


import com.booking.vendor.entity.Route;
import com.booking.vendor.repository.RouteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {
    @Autowired
    public RouteDAO routeDAO;

    public Route addRoute(Route route){

        return routeDAO.save(route);
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
