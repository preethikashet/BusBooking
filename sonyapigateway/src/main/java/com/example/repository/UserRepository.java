package com.example.repository;


//import com.example.entity.User;
import com.example.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface UserRepository extends JpaRepository<Vendor, Integer> {
    Optional<Vendor> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsById(Integer id);
}