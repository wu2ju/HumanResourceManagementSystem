package com.wuju.biz.bizImpl;

import com.wuju.biz.NotificationBiz;
import com.wuju.dao.NotificationDao;
import com.wuju.model.Notification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NotificationBizImpl implements NotificationBiz {
    @Resource
    private NotificationDao notificationDao;

    @Override
    public boolean addNotification(Notification nt) {
        return notificationDao.addNotification(nt);
    }

    @Override
    public Notification getNotificationByuId(int uId) {
        return notificationDao.getNotificationByuId(uId);
    }
}
