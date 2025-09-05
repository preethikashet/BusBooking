package com.booking.repository;

import com.booking.dto.BookingDTO;
import com.booking.entity.BookingEntity;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Integer> {
     List<BookingEntity> findAll();
}
