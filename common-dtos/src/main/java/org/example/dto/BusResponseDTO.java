package org.example.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BusResponseDTO {
    public String busno;
    public Integer busid;
    public Integer totalseats;
    public String vendorname;

}
