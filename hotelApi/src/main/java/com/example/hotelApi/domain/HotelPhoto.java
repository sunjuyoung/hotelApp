package com.example.hotelApi.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelPhoto {

    @Id
    @Column(name = "hotelPhoto_id")
    private String uuid;

    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;

    public String getLink(){
        return uuid+"_"+fileName;
    }


}
