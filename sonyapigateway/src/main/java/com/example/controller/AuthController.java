package com.example.controller;

import com.example.dto.LoginRequest;
import com.example.dto.RegisterRequest;
import com.example.entity.User;
//import com.example.service.KPIService;
import com.example.service.UserService;
import com.example.util.JwtUtil;
import jakarta.validation.Valid;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000/",methods = {RequestMethod.GET,RequestMethod.PUT, RequestMethod.POST})
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

//    @Autowired
//    private KPIService kpiService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtil.generateToken(authentication);

            User user = (User) authentication.getPrincipal();

            Map<String, Object> response = new HashMap<>();
            response.put("token", jwt);
            response.put("id", user.getId());
            response.put("email", user.getEmail());
            response.put("role", user.getRole());
            response.put("expiresIn", jwtUtil.getExpirationDateFromToken(jwt).getTime());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Invalid email or password");
            return ResponseEntity.status(401).body(error);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            if (registerRequest.getId() != null && userService.existsById(registerRequest.getId())) {
                return ResponseEntity.badRequest().body(Map.of("message", "User ID already exists"));
            }

            if (userService.existsByEmail(registerRequest.getEmail())) {
                return ResponseEntity.badRequest().body(Map.of("message", "Email already exists"));
            }

            User user = (registerRequest.getId() != null) ?
                    new User(
                            registerRequest.getId(),
                            registerRequest.getFirstName(),
                            registerRequest.getLastName(),
                            registerRequest.getEmail(),
                            registerRequest.getPassword(),
                            registerRequest.getTeamName()
                    ) :
                    new User(
                            registerRequest.getFirstName(),
                            registerRequest.getLastName(),
                            registerRequest.getEmail(),
                            registerRequest.getPassword(),
                            registerRequest.getTeamName()
                    );

            User savedUser = userService.createUser(user);
//            kpiService.initializeDefaultKPIs(savedUser);

            return ResponseEntity.ok(Map.of(
                    "message", "User registered successfully",
                    "id", savedUser.getId()
            ));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }

    }


}