package com.example.hotelApi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpDTO {

    private String nickname;
    private String email;
    private String password;
}
