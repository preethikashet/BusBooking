package com.booking.controller;

import com.booking.client.BookingClient;
import com.booking.entity.UserEntity;
import com.booking.service.UserService;
import org.apache.http.HttpStatus;
import org.example.dto.UserBookBusRequestDTO;
import org.example.dto.UserBookBusResponseDTO;
import org.example.dto.UserDTO;
import org.example.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RequestMapping("/api/user")
@RestController
public class UserController {
    @Autowired
    public BookingClient bookingClient;
    @Autowired
    public UserService userService;
    /**
     *
     * @param userEntity
     * @return
     */
//    @PostMapping("/register")
//    public ResponseEntity<String> addbooking(@RequestBody UserEntity userEntity){
////        return bookingService.addbooking(bookingEntity);
//        return ResponseEntity.ok("user created");
//    }
    /**
     *
     * @param bookingDate
     * @return
     */
    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> searchBus(@RequestParam String src, @RequestParam String dst, @RequestParam String bookingDate)
    {
        List<UserResponseDTO> buses = userService.searchBus(src, dst, bookingDate);
        System.out.println("reached");
        return ResponseEntity.ok(buses);
    }

    /**
     *
     * @param userEntity
     * @return
     */
    @PostMapping("/adduser")
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity userEntity)
    {
        return ResponseEntity.ok(userService.addUser(userEntity));
    }

    /**
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/book")
    public ResponseEntity<Object> bookBus(@RequestBody UserDTO userDTO)
    {
        List<UserEntity> users = userService.getUsers();
        UserEntity user = users.stream().filter(u->u.getEmail().equals(userDTO.getEmail())).findFirst().orElse(null);
        if(user == null) return ResponseEntity.badRequest().body("User with this email id doesnot exist");
        else{

        UserBookBusRequestDTO userBookBusRequestDTO = new UserBookBusRequestDTO();
//        UserDTO userDTO = new UserDTO();
        userBookBusRequestDTO.setPhno(user.getPhno());
        userBookBusRequestDTO.setEmail(user.getEmail());
        userBookBusRequestDTO.setAge(user.getAge());
        userBookBusRequestDTO.setUsername(user.getUname());
        userBookBusRequestDTO.setUserid(user.getUserid());
        userBookBusRequestDTO.setGender(user.getGender());
        userBookBusRequestDTO.setUserResponseDTO(userDTO.getUserResponseDTO());

        return ResponseEntity.ok(bookingClient.bookBus(userBookBusRequestDTO));

        }



//        return null;
    }
}
