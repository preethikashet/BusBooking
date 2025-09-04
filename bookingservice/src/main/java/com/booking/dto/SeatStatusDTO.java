package com.booking.dto;
import lombok.*;

import java.util.Date;
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class SeatStatusDTO {
    private int seatstatusid;
    private Date date;
    private int busid;
    private int totalseats;
    private int occupiedseats;
}
