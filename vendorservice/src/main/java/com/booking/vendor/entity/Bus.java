package com.booking.vendor.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    public Integer busid;

    @Column
    public String busnumber;
    public Integer totalseats;
    public Integer vendorid;
}
