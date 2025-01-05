package com.example.usernotifs.controllers;

import com.example.usernotifs.models.AdvertiseRequest;
import com.example.usernotifs.models.Notification;
import com.example.usernotifs.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationController {
    @Autowired private NotificationService notificationService;

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<Notification> getUserNotifications(@PathVariable int userid){
        return ResponseEntity.ok(notificationService.getNotifFromCache(userid));
    }

    // any message
    @PutMapping(value = "/users/{id}/send")
    public ResponseEntity<String> sendNotifications(@PathVariable int userid, @RequestParam String message){
        notificationService.sendMessage(userid, message);
        return ResponseEntity.ok("Success");
    }


    // called by enrollment when user registered
    @PutMapping(value = "/users/{id}/register")
    public ResponseEntity<String> sendNotifications(@PathVariable int userid, @RequestParam int courseid){
        notificationService.registerUser(userid, courseid);
        return ResponseEntity.ok("Success");
    }

    // called when user registered
    // welcoming new user
    @PostMapping(value = "/users/{id}/new")
    public ResponseEntity<String> sendNotifications(@PathVariable int userid){
        notificationService.registerUser(userid);
        return ResponseEntity.ok("Success");
    }

    // called by enrollment when multpile users registered
//    @PutMapping(value = "/advertise")
//    public ResponseEntity<String> advertiseByIds(@RequestBody AdvertiseRequest request){
//        return ResponseEntity.ok();
//    }

    // called by course when new course created
    @PutMapping(value = "/advertise/all")
    public ResponseEntity<String> advertiseByIds(@RequestParam int courseid){
        notificationService.advertiseAll(courseid);
        return ResponseEntity.ok("Success");
    }
}
