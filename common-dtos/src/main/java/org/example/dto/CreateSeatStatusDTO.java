package org.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateSeatStatusDTO {
        private int seatstatusId;
        @JsonFormat(pattern = "dd-MM-yyyy")
        private LocalDate date;
        private Integer busId;
        private Integer totalseats;
}
