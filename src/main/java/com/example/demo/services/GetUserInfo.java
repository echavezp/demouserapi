package com.example.demo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import java.util.logging.Logger;

import com.example.demo.models.Response;

@Service
public class GetUserInfo {
    private static Logger logger = Logger.getLogger("InfoLogging");
    public List<Response> getUserInfo() {
        ArrayList<Response> listUserResponse = new ArrayList<>();
        try {
            RestTemplate restTemplate = new RestTemplate();            
            ResponseEntity<String> responseJson = null;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<String> entity = new HttpEntity<>("", headers);

            responseJson = restTemplate.exchange("https://randomuser.me/api/?results=15000", HttpMethod.GET, entity, String.class);
            JSONObject jsonObject= new JSONObject(responseJson.getBody());
            jsonObject.getJSONArray("results").forEach(key -> {
                JSONObject jsonKeyObject= new JSONObject(key.toString());
                Response userResponse = new Response();
                userResponse.setEmail(jsonKeyObject.get("email").toString());
                userResponse.setName(jsonKeyObject.getJSONObject("name").get("first").toString());
                userResponse.setLastName(jsonKeyObject.getJSONObject("name").get("last").toString());
                userResponse.setUid(jsonKeyObject.getJSONObject("login").get("uuid").toString());
                listUserResponse.add(userResponse);
            });
            return listUserResponse;
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            return listUserResponse;
        }
    }
}