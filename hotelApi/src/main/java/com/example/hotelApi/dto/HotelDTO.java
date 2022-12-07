package com.example.hotelApi.dto;

import com.example.hotelApi.domain.HotelType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelDTO {

    private String name;

    private HotelType hotelType;

    private String city;

    private String title;

    private String description;

    private String address;

    private String distance;


    private List<String> images = new ArrayList<>();

    private int rating;

    private boolean featured;

    private int price;
}
