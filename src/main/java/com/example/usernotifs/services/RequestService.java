package com.example.usernotifs.services;

import com.example.usernotifs.models.Course;
import com.example.usernotifs.models.Notification;
import com.example.usernotifs.models.User;
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
        String url = "http://localhost:8085/notifications/get";
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

    public void saveNotifs(List<Notification> notifications){
        String url = "http://localhost:8085/notifications/save";
        restTemplate.put(url, notifications);
    }

    // request to course by id;
    public Course getCourse(int id){
        String url = "http://localhost:8081/courses/getbyid/" + id;
        ResponseEntity<Course> response = restTemplate.getForEntity(url, Course.class);
        return response.getBody();
    }

    // request to get all users
    public List<User> getAllUsers(){
        String url = "http://localhost:8083/users/";
        ParameterizedTypeReference<List<User>> responseType = new ParameterizedTypeReference<List<User>>() {};
        ResponseEntity<List<User>> response = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        return response.getBody();
    }


    // request user by id
    public User getUser(int id){
        String url = "http://localhost:8083/users/" + id;
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
        return response.getBody();
    }
}
