package com.booking.vendor.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    public Integer driverid;
    @Column
    public String drivername;
    @Column
    public String phoneno;
    @Column
    public Integer vendorid;
}
