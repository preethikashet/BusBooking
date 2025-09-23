package org.example.dto.vendor;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteDTO {

    public Integer routeid;

    public String src;

    public String dest;

    public Integer distance;
    public Integer vendorid;

}
