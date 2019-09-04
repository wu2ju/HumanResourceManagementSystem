package com.wuju.biz;

import com.wuju.model.Notification;

public interface NotificationBiz {
    boolean addNotification(Notification nt);
    boolean updateNotification(Notification nt);
    Notification getNotificationByuId(int uId);
    Notification getNotificationByuIdAndNtState(int uId, int ntState);
}
