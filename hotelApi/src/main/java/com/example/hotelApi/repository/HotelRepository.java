package com.example.hotelApi.repository;

import com.example.hotelApi.domain.Hotel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel,Long> {

    @EntityGraph(attributePaths = {"photos"})
    Optional<Hotel> findHotelAndPhotosById(Long id);
}
