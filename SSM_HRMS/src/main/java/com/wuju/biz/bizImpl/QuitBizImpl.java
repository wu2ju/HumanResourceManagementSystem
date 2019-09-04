package com.wuju.biz.bizImpl;

import com.wuju.biz.QuitBiz;
import com.wuju.dao.QuitDao;
import com.wuju.model.Quit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QuitBizImpl implements QuitBiz {
    @Resource
    private QuitDao quitDao;

    @Override
    public boolean addQuit(Quit quit) {
        return quitDao.addQuit(quit);
    }
}
