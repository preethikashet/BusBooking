package com.booking.controller;

import com.booking.entity.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController("/api/user")
public class UserController {
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
}
