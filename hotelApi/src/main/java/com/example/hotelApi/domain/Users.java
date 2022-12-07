package com.example.hotelApi.domain;

import com.example.hotelApi.dto.SignUpDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@Getter
@Entity
public class Users {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false,unique = true)
    private String nickname;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;


    protected Users(){
    }


    public Users(SignUpDTO signUpDTO){
        this.nickname = signUpDTO.getNickname();
        this.email = signUpDTO.getEmail();
        this.password = signUpDTO.getPassword();
    }
}
