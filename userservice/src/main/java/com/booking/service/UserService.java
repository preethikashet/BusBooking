package com.booking.service;

import com.booking.client.ScheduleClient;

import com.booking.entity.UserEntity;
import com.booking.repository.UserRepository;
import org.example.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    public ScheduleClient scheduleClient;
    @Autowired
    public UserRepository userRepository;

    public List<UserResponseDTO> searchBus(String src, String dst, String bookingDate)
    {
        return scheduleClient.searchBus(src, dst, bookingDate);
    }

    public String addUser(UserEntity userEntity)
    {
        userRepository.save(userEntity);
        return "User added";
    }

    public List<UserEntity> getUsers()
    {
        return userRepository.findAll();
    }

}
