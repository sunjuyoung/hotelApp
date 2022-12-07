package com.example.hotelApi.controller;

import com.example.hotelApi.dto.SignUpDTO;
import com.example.hotelApi.dto.file.FileResponseDTO;
import com.example.hotelApi.dto.file.UploadFileDTO;
import com.example.hotelApi.service.AuthService;
import com.example.hotelApi.valid.SignUpValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;
    private final SignUpValidation signUpValidation;

    @InitBinder("signUpDTO")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(signUpValidation);
    }


    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpDTO signUpDTO, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        authService.signUp(signUpDTO);
        return ResponseEntity.ok().body("success");
    }





}
