package com.example.hotelApi.service;

import com.example.hotelApi.domain.Users;
import com.example.hotelApi.dto.CurrentUserDTO;
import com.example.hotelApi.dto.SignUpDTO;
import com.example.hotelApi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("userdetailservice.......");
        Optional<Users> users = userRepository.findByNickname(username);
        Users account = users.orElseThrow(()-> new UsernameNotFoundException("not found user"));
        CurrentUserDTO userDTO = new CurrentUserDTO(account);
        return userDTO;
    }

    public void signUp(SignUpDTO signUpDTO) {
        signUpDTO.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        Users users = new Users(signUpDTO);
        userRepository.save(users);
    }
}
