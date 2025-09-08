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
    @Query("SELECT * FROM Schedule s WHERE s.route.id = :routeid AND DATE(s.arrivaltime) = :bookingDate")
    List<Schedule> findBusIdsByRouteAndDate(@Param("routeid") Integer routeId, @Param("bookingDate") LocalDate bookingDate);

}
