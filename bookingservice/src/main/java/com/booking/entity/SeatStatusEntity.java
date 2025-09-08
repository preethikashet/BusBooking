package com.booking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="seatstatustable")
public class SeatStatusEntity {
    @Id
    private int seatstatusId;
    @Column
    private Date date;
    private Integer busId;
    private Integer totalseats;
    private Integer occupiedseats;

    @Transient
    public Integer getRemainingSeats() {
        int remSeats = totalseats - occupiedseats;
        if (remSeats > 0) {
            return remSeats;
        }
        return 0;
    }

}


