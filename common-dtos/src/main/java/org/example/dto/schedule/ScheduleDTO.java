package org.example.dto.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ScheduleDTO {

    public Integer scheduleid;

    public Integer busid;

    public Integer price;

    public Integer routeid;

    public Integer driverid;

    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")

    public LocalDateTime arrivaltime;

    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")

    public LocalDateTime departuretime;

}
