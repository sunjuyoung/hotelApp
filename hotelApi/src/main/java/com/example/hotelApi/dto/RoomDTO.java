package com.example.hotelApi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RoomDTO {

    private String title;

    private String description;

    private int price;

    private int maxPeople;

    private LocalDate availableDate;


    private byte[] profilePicture;


}
