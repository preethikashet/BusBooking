package com.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;


@Entity
@Data
@Table(name="bookingtable")
public class BookingEntity {
    @Id
    @NotNull(message="Book ID cannot be Null")
    private Integer bookid;
    @Column

    private Integer transactionid;
    private Integer userid;
    private Integer scheduleid;
    private String bookingstatus;
}
