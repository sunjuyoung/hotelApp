package com.example.hotelApi.repository;

import com.example.hotelApi.domain.RoomPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomPhotoRepositpry extends JpaRepository<RoomPhoto,Long> {

    Optional<RoomPhoto> findByName(String fileName);
}
