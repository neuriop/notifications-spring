package com.example.usernotifs.services;

import com.example.usernotifs.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RequestService {
    @Autowired
    private RestTemplate restTemplate;

    public List<Notification> getAllNotifs(){
        String url = "http://localhost:8085/notifications/all";
        ParameterizedTypeReference<List<Notification>> responseType = new ParameterizedTypeReference<List<Notification>>() {};
        ResponseEntity<List<Notification>> response = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        return response.getBody();
    }

    public Notification getNotif(int id){
        String url = "http://localhost:8085/notfications/users/" + id;
        ResponseEntity<Notification> response = restTemplate.exchange(url, HttpMethod.GET, null, Notification.class);
        return response.getBody();
    }

    public void putNotif(Notification notification){
        String url = "http://localhost:8085/notfications/users/" + notification.getUserid() + "/put";
        restTemplate.put(url, notification);
    }

}
