package com.booking.vendor.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bustable")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer busid = 0;

    @Column
    @NotNull(message = "Bus number cannot be null")
    @Size(min = 1, max = 20, message = "Bus number must be between 1 and 20 characters")
    public String busnumber;

    @NotNull(message = "Total seats cannot be null")
    @Min(value = 1, message = "Total seats must be at least 1")
    public Integer totalseats;

    @NotNull(message = "Vendor ID cannot be null")
    @Min(value = 1, message = "Vendor ID must be greater than or equal to 1")
    public Integer vendorid;
}
