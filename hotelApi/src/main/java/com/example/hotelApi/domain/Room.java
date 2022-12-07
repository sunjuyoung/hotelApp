package com.example.hotelApi.domain;

import com.example.hotelApi.dto.RoomDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Room {

    @Id
    @GeneratedValue
    @Column(name = "room_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;

    private String title;

    private String description;

    private int price;

    private int maxPeople;

    private LocalDate availableDate;



    public void createRoom(RoomDTO roomDTO){

    }

    public void updateRoom(RoomDTO roomDTO){
        this.title = roomDTO.getTitle();
        this.description =roomDTO.getDescription();
        this.maxPeople = roomDTO.getMaxPeople();
        this.price = roomDTO.getPrice();

    }

}
