package com.booking.vendor.repository;

import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BusDAO extends JpaRepository<Bus, Integer> {
    List<Bus> findByBusidIn(List<Integer> ids);

}
