package com.example.usernotifs.services;

import com.example.usernotifs.models.AdvertiseRequest;
import com.example.usernotifs.models.Course;
import com.example.usernotifs.models.Notification;
import com.example.usernotifs.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class NotificationService {
    private List<Notification> notificationsCache = new ArrayList<>();
    @Autowired
    private RequestService requestService;

    private void fetchNotifs(){
        notificationsCache.addAll(requestService.getAllNotifs());
    }

    public Notification getNotifFromCache(int id){
        Optional<Notification> result = notificationsCache.stream()
                .filter(notification -> notification.getUserid() == id)
                .findAny();
        return result.orElse(null);
    }

//    public void advertiseAll(AdvertiseRequest request){
//        Set<Integer> userid = Arrays.stream(request.getUserid()).boxed().collect(Collectors.toSet());
//        for (Notification notification : notificationsCache) {
//            if (userid.contains(notification.getUserid())){
//                notification.putMessage(request.getMessage());
//            }
//        }
//    }

    public void registerUser(int userid){
        User user = requestService.getUser(userid);
        String message = "Welcome, " + user.getName() + " " + user.getSurname() + ", to our course service! Choose course to register.";
        Notification notification = new Notification(userid);
        notification.putMessage(message);
        notificationsCache.add(notification);
    }

    public void registerUser(int userid, int courseid){
        User user = requestService.getUser(userid);
        Course course = requestService.getCourse(courseid);
        String message = "Hello, "+ user.getName() + " " + user.getSurname() +", you have been registered to " + course.getName();
        for (Notification notification : notificationsCache) {
            if (userid == notification.getUserid()){
                notification.putMessage(message);
                return;
            }
        }
    }

    public void sendMessage(int userid, String message){
        for (Notification notification : notificationsCache) {
            if (userid == notification.getUserid()){
                notification.putMessage(message);
                return;
            }
        }
    }

    public void advertiseAll(int id){
        Course course = requestService.getCourse(id);
        String message = "New " + course.getName() + " is now available: " + course.getDescription();
        for (Notification notification : notificationsCache) {
            notification.putMessage(message);
        }
    }


}
