package com.wuju.biz.bizImpl;

import com.wuju.biz.TrainObjectBiz;
import com.wuju.dao.TrainObjectDao;
import com.wuju.model.TrainObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class TrainObjectBizImpl implements TrainObjectBiz {
    @Resource
    private TrainObjectDao trainObjectDao;

    @Override
    public boolean addTrainObject(TrainObject to) {
        return trainObjectDao.addTrainObject(to);
    }

    @Override
    public boolean updateTrainObjectToState(TrainObject to) {
        return trainObjectDao.updateTrainObjectToState(to);
    }

    @Override
    public boolean delTrainObject(int toId) {
        return trainObjectDao.delTrainObject(toId);
    }

    @Override
    public TrainObject getTrainObjectById(int toId) {
        return trainObjectDao.getTrainObjectById(toId);
    }

    @Override
    public TrainObject getTrainObjectByeIdAndTrId(int eId, int trId) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("eId",eId);
        map.put("trId",trId);
        return trainObjectDao.getTrainObjectByeIdAndTrId(map);
    }

    @Override
    public TrainObject getTrainObjectByeIdAndTrIdAndToState(int eId, int trId, int toState) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("eId",eId);
        map.put("trId",trId);
        map.put("toState",toState);
        return trainObjectDao.getTrainObjectByeIdAndTrIdAndToState(map);
    }
}
