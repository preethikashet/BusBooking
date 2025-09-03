package com.booking.vendor.repository;

import com.booking.vendor.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VendorDAO extends JpaRepository<Vendor, Integer> {
}
