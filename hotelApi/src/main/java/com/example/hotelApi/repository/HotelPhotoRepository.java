package com.example.hotelApi.repository;

import com.example.hotelApi.domain.HotelPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelPhotoRepository extends JpaRepository<HotelPhoto,String> {
}
