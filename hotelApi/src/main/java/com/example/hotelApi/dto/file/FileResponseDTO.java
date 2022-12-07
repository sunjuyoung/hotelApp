package com.example.hotelApi.dto.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
@Builder
public class FileResponseDTO {

    private String uuid;
    private String fileName;
    private boolean img;

    public String getLink(){
        return uuid+"_"+fileName;
    }
}
