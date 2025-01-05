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

    @PutMapping(value = "/users/{id}/send")
    public ResponseEntity<String> sendNotifications(@PathVariable int userid, @RequestBody String notification){
        return ResponseEntity.ok();
    }

    @GetMapping(value = "/users/{userid}/notifications/{notifid}")
    public ResponseEntity<String> getNotificationById(@PathVariable int userid, @PathVariable int notifid){
        return ResponseEntity.ok();
    }

    @PutMapping(value = "/advertise")
    public ResponseEntity<String> advertiseByIds(@RequestBody AdvertiseRequest request){
        return ResponseEntity.ok();
    }

    @PutMapping(value = "/advertise/all")
    public ResponseEntity<String> advertiseByIds(@RequestBody String message){
        return ResponseEntity.ok();
    }
}
