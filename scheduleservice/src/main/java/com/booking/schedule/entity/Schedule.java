package com.booking.schedule.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "scheduletable")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Schedule {
    @Id
    public Integer scheduleid;

    @Column
    public Integer busid;
    public Integer price;
    public Integer routeid;
    public Integer driverid;
    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    public LocalDateTime arrivaltime;
    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    public LocalDateTime departuretime;

}
