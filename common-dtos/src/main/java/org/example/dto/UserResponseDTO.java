package org.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    public LocalDateTime arrival;
    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    public LocalDateTime departure;
    public Integer scheduleid;
    public Integer price;
}
