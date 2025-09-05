package com.booking.schedule.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "scheduletable")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    public Integer scheduleid;

    @Column
    public Integer busid;
    public Integer routeid;
    public Integer driverid;
    public LocalDateTime arrivaltime;
    public LocalDateTime departuretime;
}
