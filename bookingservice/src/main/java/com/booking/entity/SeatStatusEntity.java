package com.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="seatstatustable")
public class SeatStatusEntity {
    @Id
    private int seatstatusid;
    @Column
    private Date date;
    private int busid;
    private int totalseats;
    private int occupiedseats;

}
