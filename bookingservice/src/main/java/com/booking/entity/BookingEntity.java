package com.booking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;


@Entity
@Data
@Table(name="bookingtable")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookid;
    @Column

    private Integer transactionid;
    private Integer userid;
    private Integer scheduleid;

}
