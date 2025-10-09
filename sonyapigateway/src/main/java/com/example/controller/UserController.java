package com.example.controller;

import org.example.dto.UserDTO;
import org.example.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/auth/test")
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class UserController {
    @Autowired
    private UserClientService userClientService;

    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> searchBus(@RequestParam String src, @RequestParam String dst, @RequestParam String bookingDate){
        System.out.println("reached search"+src+" "+dst+" "+bookingDate);

       return userClientService.searchBus(src,dst,bookingDate);
    }

    @PostMapping("/book")
    public ResponseEntity<Object> bookBus(@RequestBody UserDTO userDTO)
    {
        return userClientService.bookBus(userDTO);
    }

@FeignClient(name = "userservice",contextId = "UserClientService",path = "/api/user")
interface UserClientService{
    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> searchBus(@RequestParam String src, @RequestParam String dst, @RequestParam String bookingDate);

    @PostMapping("/book")
    public ResponseEntity<Object> bookBus(@RequestBody UserDTO userDTO);
}