package com.example.hotelApi.dto;

import com.example.hotelApi.domain.Users;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@Getter
public class CurrentUserDTO extends User {

    private Users users;

    public CurrentUserDTO(Users users) {
        super(users.getNickname(), users.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.users = users;
    }
}
