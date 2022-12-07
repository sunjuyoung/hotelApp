package com.example.hotelApi.service;

import com.example.hotelApi.domain.Hotel;
import com.example.hotelApi.dto.HotelDTO;
import com.example.hotelApi.dto.file.UploadFileDTO;

public interface HotelService {
    String createHotel(HotelDTO hotelDTO);

    HotelDTO getHotel(Long id);

    void deleteHotel(Long id);

    void updateHotel(HotelDTO hotelDTO, Long id);

    void addImage(UploadFileDTO uploadFileDTO,Long id);


}
