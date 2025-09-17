package org.example.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserBookBusResponseDTO {
    public String username;
    public String vendorname;
    public String Busname;
    public String busnumber;
    public String drivernumber;
    public LocalDateTime arrival;
    public LocalDateTime departure;
    public Integer price;

}
