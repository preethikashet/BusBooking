package com.booking.vendor.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drivertable")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer driverid;
    @Column
    @NotNull(message = "Driver name cannot be null")
    public String drivername;
    @Column
    @NotNull(message = "Phone no cannot be null")
    @Size(min = 1, max = 12, message = "phone number should be 10 digits")
    public String phoneno;

    @Column
    @NotNull(message = "Needs a vendor ID")
    @Min(value = 1, message = "Vendor ID must be greater than or equal to 1")
    public Integer vendorid;
}
