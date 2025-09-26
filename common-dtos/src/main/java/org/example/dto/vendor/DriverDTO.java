package org.example.dto.vendor;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverDTO {

    public Integer driverid;

    public String drivername;

    public String phoneno;
    public Integer vendorid;
}
