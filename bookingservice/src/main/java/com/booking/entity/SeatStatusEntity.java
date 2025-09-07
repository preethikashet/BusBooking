package com.booking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
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
    public Integer getRemainingSeats(){
        return totalseats-occupiedseats ;
    }

}
