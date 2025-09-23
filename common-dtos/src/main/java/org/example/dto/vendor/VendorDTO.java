package org.example.dto.vendor;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {
    public Integer vendorid;
    public String vendorname;
    public String phoneno;
}
