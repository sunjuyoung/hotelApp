package com.example.hotelApi.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor@NoArgsConstructor
@ToString(exclude = "hotel")
public class Image {

    @Id @GeneratedValue
    @Column(name = "hotelPhoto_id")
    private String id;

    private String fileName;

    private int ord;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;


}
