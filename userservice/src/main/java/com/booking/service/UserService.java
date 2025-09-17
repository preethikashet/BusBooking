package com.booking.service;

import com.booking.client.ScheduleClient;

import org.example.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    public ScheduleClient scheduleClient;

    public List<UserResponseDTO> searchBus(String src, String dst, String bookingDate)
    {
        return scheduleClient.searchBus(src, dst, bookingDate);
    }

}
