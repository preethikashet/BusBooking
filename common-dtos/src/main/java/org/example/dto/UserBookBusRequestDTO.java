package org.example.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserBookBusRequestDTO {
    public Integer userid;
    public String username;
    public String gender;
    public String email;
    public String phno;
    public int age;
    public UserResponseDTO userResponseDTO;

}