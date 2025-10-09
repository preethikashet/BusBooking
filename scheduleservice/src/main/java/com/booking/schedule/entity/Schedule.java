package com.booking.schedule.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer scheduleid;
    @Column
    @NotNull(message = "busid should not be null")
    public Integer busid;

    @NotNull(message = "price should not be null")
    @Positive(message = "Price must be greater than 0")
    public Integer price;

    @NotNull(message = "routeid should not be null")
    public Integer routeid;

    @NotNull(message = "driverid should not be null")
    public Integer driverid;

    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    @FutureOrPresent(message = "Arrival time must be in the future or present")
    public LocalDateTime arrivaltime;

    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    @FutureOrPresent(message = "Departure time must be in the future or present")
    public LocalDateTime departuretime;

//    @NotNull
//    public Integer vendorid;

}
