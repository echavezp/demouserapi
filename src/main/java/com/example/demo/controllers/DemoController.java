package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.services.GetUserInfo;
import com.example.demo.models.Response;
import org.springframework.http.HttpStatus;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/demo")
public class DemoController {
    private final GetUserInfo service;

    public DemoController(@Autowired GetUserInfo service) {
        this.service = service;
    }    

    @GetMapping(path = "/")
    public ResponseEntity<Object> getInformation() {
        try {
            List<Response> usersResponse = service.getUserInfo();
            return new ResponseEntity<>(usersResponse, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }
}