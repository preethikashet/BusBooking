package com.booking.dto;

import lombok.*;

@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookingDTO {
    private int bookid;
    private int transactionid;
    private int userid;
    private int scheduleid;
    private String bookingstatus;
}
