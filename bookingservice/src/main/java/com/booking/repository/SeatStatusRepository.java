package com.booking.repository;


import com.booking.entity.BookingEntity;
import com.booking.entity.SeatStatusEntity;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface SeatStatusRepository extends JpaRepository<SeatStatusEntity,Integer> {
    List<SeatStatusEntity> findByBusIdInAndDate(List<Integer> busId,  Date date);
}
