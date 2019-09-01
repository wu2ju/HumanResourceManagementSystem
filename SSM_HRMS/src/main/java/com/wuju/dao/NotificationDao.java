package com.wuju.dao;

import com.wuju.model.Notification;
import com.wuju.model.User;

import java.util.List;

public interface NotificationDao {
    boolean addNotification(Notification nt);
    Notification getNotificationByuId(int uId);
}
