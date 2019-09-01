package com.wuju.biz;

import com.wuju.model.Notification;

public interface NotificationBiz {
    boolean addNotification(Notification nt);
    Notification getNotificationByuId(int uId);
}
