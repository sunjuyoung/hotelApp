package com.example.hotelApi.service;

import com.example.hotelApi.domain.Hotel;
import com.example.hotelApi.domain.HotelPhoto;
import com.example.hotelApi.dto.HotelDTO;
import com.example.hotelApi.dto.file.UploadFileDTO;
import com.example.hotelApi.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.Deflater;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    public String createHotel(HotelDTO hotelDTO) {
        Hotel hotel = modelMapper.map(hotelDTO, Hotel.class);
        hotelRepository.save(hotel);
        return hotelDTO.getName();
    }

    @Override
    public HotelDTO getHotel(Long id) {
        Hotel hotel = hotelRepository.findHotelAndPhotosById(id).orElseThrow(() -> new IllegalArgumentException());
        HotelDTO hotelDTO = modelMapper.map(hotel, HotelDTO.class);
        if(hotel.getPhotos().size()>0){
            hotel.getPhotos().forEach(hotelPhoto -> {
               hotelDTO.getImages().add(hotelPhoto.getLink());
            });
        }
        return hotelDTO;
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public void updateHotel(HotelDTO hotelDTO, Long id) {
        Hotel hotel = hotelRepository.findById(id).get();
        hotel.updateHotel(hotelDTO);
    }

    @Value("${com.example.hotelApi.path}")
    private String uploadPath;

    @Override
    public void addImage(UploadFileDTO uploadFileDTO,Long id) {
        Hotel hotel = hotelRepository.findById(id).get();
        if(uploadFileDTO.getFiles() !=null){
            uploadFileDTO.getFiles().forEach(multipartFile -> {
                String originName = multipartFile.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid+"_"+originName);
                hotel.addImage(originName,uuid);
                hotelRepository.save(hotel);
                try {
                    multipartFile.transferTo(savePath);
                    //이미지 파일확인
                    if(Files.probeContentType(savePath).startsWith("image")){

                    }
                }catch (IOException e){
                    log.info(e);
                }

            });


        }
    }

}
