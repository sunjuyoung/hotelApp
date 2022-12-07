package com.example.hotelApi.repository;

import com.example.hotelApi.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

    Optional<Users> findByNickname(String username);
}
