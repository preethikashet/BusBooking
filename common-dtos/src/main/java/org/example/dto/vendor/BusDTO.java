package org.example.dto.vendor;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusDTO {

    public Integer busid;
    public String busnumber;
    public Integer totalseats;
    public Integer vendorid;
}
