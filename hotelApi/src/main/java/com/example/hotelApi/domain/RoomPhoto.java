package com.example.hotelApi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomPhoto {


    @Id @GeneratedValue
    private Long id;

    private String name;
    private String type;

    @Lob
    private byte[] imageData;


}
