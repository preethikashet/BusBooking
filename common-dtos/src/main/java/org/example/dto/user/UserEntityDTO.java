package org.example.dto.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntityDTO {
    private Integer userid;
    private String uname;
    private String gender;
    private String email;
    private String phno;
    private int age;
}
