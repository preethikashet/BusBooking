package com.booking.schedule.repository;

import com.booking.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Schedulerepository extends JpaRepository<Schedule,Integer> {
}
