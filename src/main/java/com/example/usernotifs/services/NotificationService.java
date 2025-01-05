package com.example.usernotifs.services;

import com.example.usernotifs.models.AdvertiseRequest;
import com.example.usernotifs.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class NotificationService {
    private List<Notification> notificationsCache = new ArrayList<>();
//    @Autowired
//    private RequestService requestService;

//    private void fetchNotifs(){
//        notificationsCache.addAll(requestService.getAllNotifs());
//    }

    public Notification getNotifFromCache(int id){
        Optional<Notification> result = notificationsCache.stream()
                .filter(notification -> notification.getUserid() == id)
                .findAny();
        return result.orElse(null);
    }

    public void advertiseAll(AdvertiseRequest request){
        Set<Integer> userid = Arrays.stream(request.getUserid()).boxed().collect(Collectors.toSet());
        notificationsCache = notificationsCache.stream()
                .map(notification -> {
                    if (userid.contains(notification.getUserid())) {
                        Map<Integer, String> updatedMessages = notification.getNotificationMessages();
                        updatedMessages.put(updatedMessages.size(), request.getMessage());
                        return new Notification(notification.getUserid(), updatedMessages);
                    } else {
                        return notification;
                    }
                })
                .collect(Collectors.toList());
    }


}
