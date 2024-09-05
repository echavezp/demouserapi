package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Response {
    @JsonProperty("id")
    public String uid;
    @JsonProperty("nombre")
    public String name;
    @JsonProperty("apellido")
    public String lastName;
    @JsonProperty("correo")
    public String email;
}   