package com.wuju.dao;

import com.wuju.model.Notification;
import com.wuju.model.User;

import java.util.HashMap;
import java.util.List;

public interface NotificationDao {
    boolean addNotification(Notification nt);
    boolean updateNotification(Notification nt);
    Notification getNotificationByuId(int uId);
    Notification getNotificationByuIdAndNtState(HashMap map);
}
