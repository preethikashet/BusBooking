package org.example.dto.vendor;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeatDTO {

    public Integer seatid;

    public String seatnumber;
    public Integer busid;
}
