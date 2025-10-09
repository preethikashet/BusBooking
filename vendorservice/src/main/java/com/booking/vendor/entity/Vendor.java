package com.booking.vendor.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "vendortable")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
    @Id
    public Integer vendorid;
    @Column
    @NotNull(message = "Vendor name cannot be null")
    public String vendorname;
    @Column
    @NotNull(message = "Vendor phone no cannot be null")
    @Size(min = 1, max = 10, message = "phone number should be 10 digits")
    public String phoneno;

}
