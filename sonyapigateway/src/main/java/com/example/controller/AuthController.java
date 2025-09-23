package com.example.controller;

import com.example.dto.LoginRequest;
import com.example.dto.VendorDTO;
import com.example.entity.Vendor;
import com.example.service.UserService;
import com.example.util.JwtUtil;
import jakarta.validation.Valid;
import org.example.dto.UserResponseDTO;
import org.example.dto.vendor.BusDTO;
import org.example.dto.vendor.DriverDTO;
import org.example.dto.vendor.RouteDTO;
import org.example.dto.vendor.VendorIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private VendorClient vendorClient;


    // -------------------------
    // ✅ LOGIN API
    // -------------------------
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            // Set authentication context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String jwt = jwtUtil.generateToken(authentication);

            // Get vendor details
            Vendor vendor = (Vendor) authentication.getPrincipal();

            // Prepare response
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("token", jwt);
            response.put("id", vendor.getVendorid());
            response.put("vendorname", vendor.getVendorname());
            response.put("email", vendor.getEmail());
            response.put("expiresIn", jwtUtil.getExpirationDateFromToken(jwt).getTime());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid email or password"));
        }
    }

    /**
     *
     * @param vendor
     * @return
     */
    // -------------------------
    // ✅ REGISTER API
    // -------------------------
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Vendor vendor) {
        try {
            // Check if vendor ID already exists
            if (vendor.getVendorid() != null && userService.existsById(vendor.getVendorid())) {
                return ResponseEntity.badRequest().body(Map.of("message", "Vendor ID already exists"));
            }

            // Check if email already exists
            if (userService.existsByEmail(vendor.getEmail())) {
                return ResponseEntity.badRequest().body(Map.of("message", "Email already exists"));
            }

            vendorClient.addVendor(new VendorDTO(vendor.getVendorid(), vendor.getVendorname(), vendor.getPhoneno()));


            // Save vendor
            Vendor savedVendor = userService.createUser(vendor);

            return ResponseEntity.ok(Map.of(
                    "message", "Vendor registered successfully",
                    "id", savedVendor.getVendorid(),
                    "email", savedVendor.getEmail()
            ));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}



@FeignClient(name = "vendorservice",contextId = "VendorClient",path = "/api")
interface VendorClient{
    @PostMapping("/vendor/add")
    public String addVendor(@RequestBody VendorDTO vendor) ;

    @PostMapping("/bus/add")
    public String addBus(@RequestBody BusDTO bus);

    @PostMapping("/route/add")
    public String addRoute(@RequestBody RouteDTO route);

        @PostMapping("/driver/add")
    public String addDriver(@RequestBody DriverDTO driver);

    @PostMapping("/bus/getbusesbyid")
    public ResponseEntity<List<BusDTO>> getBusByVendorId(@RequestBody VendorIdDTO vendorIdDTO) ;

    @PostMapping("/driver/getdriverbyid")
    public ResponseEntity<List<DriverDTO>> getBusByVendorIdDriver(@RequestBody VendorIdDTO vendorIdDTO) ;

    @PostMapping("/route/getroutebyid")
    public ResponseEntity<List<RouteDTO>> getBusByVendorIdRoute(@RequestBody VendorIdDTO vendorIdDTO) ;




}



