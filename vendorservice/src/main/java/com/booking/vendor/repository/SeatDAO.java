package com.booking.vendor.repository;

import com.booking.vendor.entity.Bus;
import com.booking.vendor.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SeatDAO extends JpaRepository<Seat, Integer> {

}
