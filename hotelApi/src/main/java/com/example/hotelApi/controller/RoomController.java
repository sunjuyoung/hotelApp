package com.example.hotelApi.controller;

import com.example.hotelApi.dto.HotelDTO;
import com.example.hotelApi.dto.RoomDTO;
import com.example.hotelApi.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/room")
public class RoomController {

    private final RoomService roomService;


    @PostMapping(value = "/{id}")
    public ResponseEntity<String> createRoom(@RequestBody RoomDTO roomDTO, @PathVariable("id")Long hotelId){
        String room = roomService.createRoom(roomDTO,hotelId);
        return ResponseEntity.ok().body(room);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateRoom(@RequestBody RoomDTO roomDTO,@PathVariable("id")Long id){
        roomService.updateRoom(roomDTO,id);
        return ResponseEntity.ok().body("success");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id")Long id){
        roomService.deleteRoom(id);
        return ResponseEntity.ok().body("success");
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable("id")Long id){
        RoomDTO roomDTO = roomService.getRoom(id);
        return ResponseEntity.ok().body(roomDTO);
    }
    @GetMapping(value = "/hotel/{id}")
    public ResponseEntity<List<RoomDTO>> getRooms(@PathVariable("id")Long hotelId){
        List<RoomDTO> roomDTO = roomService.getRooms(hotelId);
        return ResponseEntity.ok().body(roomDTO);
    }

    @PostMapping(value = "/image")
    public ResponseEntity<?> uploadImage (@RequestParam("image")MultipartFile file) throws IOException {
        String s = roomService.uploadImage(file);
        return ResponseEntity.ok().body(s);
    }

    @GetMapping(value = "/image/{fileName}")
    public ResponseEntity<?> getImage(@PathVariable("fileName")String fileName) {
        byte[] image = roomService.getImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
}
