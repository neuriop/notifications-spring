package com.example.usernotifs.services;


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
    private Set<Integer> uniqueCoursesCache = new HashSet<>();
    @Autowired
    private RequestService requestService;

    private void fetchNotifs() {
        List<Notification> notifs;
        if ((notifs = requestService.getAllNotifs()) != null) {
            notificationsCache = notifs;
        }
    }

    public List<Notification> getNotificationsCache(){
        fetchNotifs();
        return notificationsCache;
    }

    private void saveNotifs() {
        requestService.saveNotifs(notificationsCache);
    }


    public void scheduledCourseNotification() {
        List<User> users = requestService.getAllUsers();
        Set<Integer> setu = users.stream().map(user -> (int) user.getId()).collect(Collectors.toSet());
        if (!setu.isEmpty()) {
            setu.forEach(this::registerUser);
        }
        List<Course> course = requestService.getAllCourse();
        Set<Integer> sets = course.stream().map(Course::getId).collect(Collectors.toSet());
        sets.removeAll(uniqueCoursesCache);
        if (!sets.isEmpty()) {
            sets.forEach(this::advertiseAll);
        }
        uniqueCoursesCache.addAll(sets);
        saveNotifs();
    }

    public Notification getNotifFromCache(int id) {
        fetchNotifs();
        Optional<Notification> result = notificationsCache.stream()
                .filter(notification -> notification.getUserid() == id)
                .findAny();
        return result.orElse(null);
    }

    public void registerUser(int userid) {
        fetchNotifs();
        User user = requestService.getUser(userid);
        String message = "Welcome, " + user.getName() + " " + user.getSurname() + ", to our course service! Choose course to register.";
        Notification notification = new Notification(userid);
        notification.putMessage(message);
        notificationsCache.add(notification);
        saveNotifs();
    }

    public void registerUser(int userid, int courseid) {
        fetchNotifs();
        User user = requestService.getUser(userid);
        Course course = requestService.getCourse(courseid);
        String message = "Hello, " + user.getName() + " " + user.getSurname() + ", you have been registered to " + course.getName();
        for (Notification notification : notificationsCache) {
            if (userid == notification.getUserid()) {
                notification.putMessage(message);
                saveNotifs();
                return;
            }
        }

    }

    public void sendMessage(int userid, String message) {
        fetchNotifs();
        for (Notification notification : notificationsCache) {
            if (userid == notification.getUserid()) {
                notification.putMessage(message);
                saveNotifs();
                return;
            }
        }

    }

    public void advertiseAll(int id) {
//        Course course = requestService.getCourse(id);
        String message = "New " + id + " is now available: ";
        for (Notification notification : notificationsCache) {
            notification.putMessage(message);
        }
    }


}
