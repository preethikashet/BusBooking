package com.booking.vendor.repository;

import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DriverDAO extends JpaRepository<Driver, Integer> {
    List<Driver> findByDriveridIn(List<Integer> ids);

}
