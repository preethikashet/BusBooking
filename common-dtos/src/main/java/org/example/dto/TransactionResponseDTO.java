package org.example.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {
    public Integer userid;
    public Integer vendorid;
    public Integer busid;
    public Integer price;
    public Integer transactionid;
    public Integer scheduleid;
    public String vendorname;
    public String busname;
    public LocalDateTime arrival;
    public LocalDateTime departure;
    public String busnumber;
    public String drivernumber;
}
