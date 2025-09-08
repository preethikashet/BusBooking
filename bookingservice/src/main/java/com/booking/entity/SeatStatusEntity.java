package com.booking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Integer busId;
    private Integer totalseats;
    private Integer occupiedseats;

    @Transient
    @JsonIgnore
    public Integer getRemainingSeats() {
        int remSeats = totalseats - occupiedseats;
        if (remSeats > 0) {
            return remSeats;
        }
        return 0;
    }

}


