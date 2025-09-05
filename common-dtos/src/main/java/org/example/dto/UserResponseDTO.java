package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponseDTO {
    public String busnumber;
    public String drivernumber;
    public Integer remainingseats;
    public String vendorname;
    public LocalDateTime arrival;
    public LocalDateTime departure;
}
