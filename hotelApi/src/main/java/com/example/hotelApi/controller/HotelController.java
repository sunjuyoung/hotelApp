package com.example.hotelApi.controller;


import com.example.hotelApi.domain.HotelPhoto;
import com.example.hotelApi.dto.HotelDTO;
import com.example.hotelApi.dto.file.UploadFileDTO;
import com.example.hotelApi.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.Deflater;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    private final HotelService hotelService;
    @Value("${com.example.hotelApi.path}")
    private String uploadPath;

    @PostMapping
    public ResponseEntity<String> createHotel(@RequestBody HotelDTO hotelDTO){
        String hotelName = hotelService.createHotel(hotelDTO);
        return ResponseEntity.ok().body(hotelName);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateHotel(@RequestBody HotelDTO hotelDTO,@PathVariable("id")Long id){
         hotelService.updateHotel(hotelDTO,id);
        return ResponseEntity.ok().body("success");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable("id")Long id){
        hotelService.deleteHotel(id);
        return ResponseEntity.ok().body("success");
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<HotelDTO> getHotel(@PathVariable("id")Long id){
        HotelDTO hotel = hotelService.getHotel(id);
        return ResponseEntity.ok().body(hotel);
    }

    @PostMapping(value = "/upload/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(UploadFileDTO uploadFileDTO,
                                         @PathVariable("id")Long id)  {
        hotelService.addImage(uploadFileDTO,id);
        return null;
    }

    @GetMapping(value = "/view/{fileName}")
    public ResponseEntity<Resource> viewFile(@PathVariable String fileName){
        Resource resource = new FileSystemResource(uploadPath+ File.separator+fileName);
        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }


}
