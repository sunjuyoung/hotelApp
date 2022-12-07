package com.example.hotelApi.repository;

import com.example.hotelApi.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
