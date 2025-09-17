package org.example.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserBookBusRequestDTO {
    public Integer userid;
    public UserResponseDTO userResponseDTO;

}