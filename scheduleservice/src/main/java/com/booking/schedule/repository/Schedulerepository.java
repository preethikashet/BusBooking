package com.booking.schedule.repository;

import com.booking.schedule.entity.Schedule;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface Schedulerepository extends JpaRepository<Schedule,Integer> {
    @Query("SELECT s FROM Schedule s WHERE s.routeid = :routeid AND DATE(s.departuretime) = :bookingDate")
    List<Schedule> findBusIdsByRouteAndDate(@Param("routeid") Integer routeid, @Param("bookingDate") LocalDate bookingDate);

}
