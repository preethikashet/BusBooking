package com.booking.vendor.repository;

import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RouteDAO extends JpaRepository<Route, Integer> {
    @Query("SELECT e.routeid FROM Route e WHERE e.src = :src AND e.dest = :dest")
    Integer findIdBySrcAndDest(@Param("src") String src, @Param("dest") String dest);

    List<Route> findByVendorid(Integer id);




}
