package com.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="bookingtable")
public class BookingEntity {
    @Id
    private int bookid;
    @Column
    private int transactionid;
    private int userid;
    private int scheduleid;
    private String bookingstatus;
}
