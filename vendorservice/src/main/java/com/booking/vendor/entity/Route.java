package com.booking.vendor.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "routetable")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer routeid;
    @Column
    @NotNull(message = "Source cannot be null")
    @Size(min = 1, max = 20, message = "Source must be between 1 and 20 characters")
    public String src;

    @Column
    @NotNull(message = "Destination cannot be null")
    @Size(min = 1, max = 20, message = "Destination must be between 1 and 20 characters")
    public String dest;

    @Column
    @NotNull(message = "Distance cannot be null")
    @Min(value = 1, message = "Distance must be greater than or equal to 1")
    public Integer distance;

    @Column
    @NotNull(message = "Vendor id cannot be null")
    @Min(value = 1, message = "Vendor ID must be greater than or equal to 1")
    public Integer vendorid;

}
