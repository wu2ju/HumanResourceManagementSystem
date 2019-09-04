package com.wuju.biz.bizImpl;

import com.wuju.biz.NotificationBiz;
import com.wuju.dao.NotificationDao;
import com.wuju.model.Notification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class NotificationBizImpl implements NotificationBiz {
    @Resource
    private NotificationDao notificationDao;

    @Override
    public boolean addNotification(Notification nt) {
        return notificationDao.addNotification(nt);
    }

    @Override
    public boolean updateNotification(Notification nt) {
        return notificationDao.updateNotification(nt);
    }

    @Override
    public Notification getNotificationByuId(int uId) {
        return notificationDao.getNotificationByuId(uId);
    }

    @Override
    public Notification getNotificationByuIdAndNtState(int uId, int ntState) {
        HashMap<String,Integer> map = new HashMap<>();
        map.put("uId",uId);
        map.put("ntState",ntState);
        return notificationDao.getNotificationByuIdAndNtState(map);
    }
}
