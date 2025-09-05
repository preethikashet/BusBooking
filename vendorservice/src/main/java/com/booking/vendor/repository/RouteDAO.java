package com.booking.vendor.repository;

import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RouteDAO extends JpaRepository<Route, Integer> {

}
