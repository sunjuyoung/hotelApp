package com.example.hotelApi.service;

import com.example.hotelApi.domain.Hotel;
import com.example.hotelApi.domain.Room;
import com.example.hotelApi.domain.RoomPhoto;
import com.example.hotelApi.dto.HotelDTO;
import com.example.hotelApi.dto.RoomDTO;
import com.example.hotelApi.repository.HotelRepository;
import com.example.hotelApi.repository.RoomPhotoRepositpry;
import com.example.hotelApi.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final ModelMapper modelMapper;
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final RoomPhotoRepositpry roomPhotoRepositpry;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        RoomPhoto save = roomPhotoRepositpry.save(RoomPhoto.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(compressImage(file.getBytes()))
                .build());
        if(save != null){
            return "file upload success"+ file.getOriginalFilename();
        }
        return null;
    }
    @Override
    public byte[] getImage(String name) {
        Optional<RoomPhoto> roomPhoto = roomPhotoRepositpry.findByName(name);
        byte[] image = decompressImage(roomPhoto.get().getImageData());
        return image;
    }

    public  byte[] compressImage(byte[] data) {

        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
        }
        return outputStream.toByteArray();
    }
    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception exception) {
        }
        return outputStream.toByteArray();
    }

    @Override
    public String createRoom(RoomDTO roomDTO, Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).get();
        Room room = Room.builder()
                .availableDate(roomDTO.getAvailableDate())
                .description(roomDTO.getDescription())
                .hotel(hotel)
                .maxPeople(roomDTO.getMaxPeople())
                .price(roomDTO.getPrice())
                .title(roomDTO.getTitle())
                .title(roomDTO.getTitle())
                .build();
        roomRepository.save(room);
        return null;
    }

    @Override
    public void updateRoom(RoomDTO roomDTO, Long id) {
        Room room = roomRepository.findById(id).get();
        room.updateRoom(roomDTO);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public RoomDTO getRoom(Long id) {
        Room room = roomRepository.findById(id).get();
        RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);
        return roomDTO;
    }

    @Override
    public List<RoomDTO> getRooms(Long hotelId) {
        return null;
    }
}
