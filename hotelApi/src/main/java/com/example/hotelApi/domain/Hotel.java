package com.example.hotelApi.domain;

import com.example.hotelApi.dto.HotelDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@ToString(exclude = "photos")
public class Hotel{

    @Id
    @GeneratedValue
    @Column(name = "hotel_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private HotelType hotelType;

    private String name;

    private String city;

    private String title;

    private String description;

    private String address;

    private String distance;

    @OneToMany(mappedBy = "hotel"
            ,cascade = CascadeType.ALL
    ,orphanRemoval = true)
    private List<HotelPhoto> photos = new ArrayList<>();

    private int rating;

    private boolean featured;

    private int price;


    public void addImage(String fileName,String uuid){
        HotelPhoto hotelPhoto = HotelPhoto.builder()
                .hotel(this)
                .fileName(fileName)
                .uuid(uuid)
                .build();
        this.photos.add(hotelPhoto);
    }


    public void updateHotel(HotelDTO hotelDTO){
        this.price = hotelDTO.getPrice();
        this.address = hotelDTO.getAddress();
        this.city = hotelDTO.getCity();
        this.distance =hotelDTO.getDistance();
        this.rating = hotelDTO.getRating();
        this.name = hotelDTO.getName();

    }





}
