package com.booking.vendor.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    public Integer routeid;
    @Column
    public String src;
    @Column
    public String dest;
    @Column
    public Integer distance;
    public Integer vendorid;

}
