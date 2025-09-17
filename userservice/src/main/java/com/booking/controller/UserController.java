package com.booking.controller;

import com.booking.entity.UserEntity;
import com.booking.service.UserService;
import org.example.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping
@RestController("/api/user")
public class UserController {

    @Autowired
    public UserService userService;
    /**
     *
     * @param userEntity
     * @return
     */
    @PostMapping("/register")
    public  String addbooking(@RequestBody UserEntity userEntity){
//        return bookingService.addbooking(bookingEntity);
         return "User not yet added";
    }
    /**
     *
     * @param src
     * @param dst
     * @param bookingDate
     * @return
     */
    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> searchBus(@RequestParam String src, @RequestParam String dst, @RequestParam String bookingDate)
    {
        List<UserResponseDTO> buses = userService.searchBus(src, dst, bookingDate);
        return ResponseEntity.ok(buses);
    }
}
