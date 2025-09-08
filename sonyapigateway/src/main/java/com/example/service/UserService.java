package com.example.service;

import com.example.entity.Vendor;
import com.example.entity.Vendor;
import com.example.exceptions.ResourceNotFoundException;
import com.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Searching for user with email: {}", username);

        return userRepository.findByEmail(username)
                .orElseThrow(() -> {
                    logger.error("User not found with email: {}", username);
                    return new UsernameNotFoundException("User not found with email: " + username);
                });
    }

    public List<Vendor> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Vendor> findById(Integer id) {
        return userRepository.findById(id);
    }

    public Vendor updateUser(Integer id, Vendor userDetails) {
        Vendor vendor = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Update fields that are allowed to be changed
        vendor.setVendorname(userDetails.getVendorname());
        vendor.setEmail(userDetails.getEmail());
        vendor.setPhoneno(userDetails.getPhoneno());

        return userRepository.save(vendor);
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public Vendor createUser(Vendor user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsById(Integer id) {
        return userRepository.existsById(id);
    }
}