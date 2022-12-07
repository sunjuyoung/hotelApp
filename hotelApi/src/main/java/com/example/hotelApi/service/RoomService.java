package com.example.hotelApi.service;

import com.example.hotelApi.domain.RoomPhoto;
import com.example.hotelApi.dto.HotelDTO;
import com.example.hotelApi.dto.RoomDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RoomService {
    String createRoom(RoomDTO roomDTO, Long hotelId);

    void updateRoom(RoomDTO roomDTO, Long id);

    void deleteRoom(Long id);

    RoomDTO getRoom(Long id);

    List<RoomDTO> getRooms(Long hoteId);

    String uploadImage(MultipartFile file) throws IOException;

    byte[] getImage(String name);
}
