package com.booking.vendor.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seattable")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Seat {
    @Id
    public Integer seatid;
    @Column
    public String seatnumber;
    public Integer busid;
}
