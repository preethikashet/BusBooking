package com.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="transactiontable")
public class TransactionEntity {
    @Id
    private int transactionid;
    @Column
    private int userid;
    private int price;
    private LocalDateTime timestamp;
//    private int vendorid;
    private String vendorname;
    private int notificationstatus;

}
