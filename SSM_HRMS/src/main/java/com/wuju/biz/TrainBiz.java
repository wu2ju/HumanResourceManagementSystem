package com.wuju.biz;

import com.wuju.model.Page;
import com.wuju.model.Train;

import java.util.List;

public interface TrainBiz {
    boolean addTrain(Train train);
    boolean updateTrain(Train train);
    boolean delTrain(int trId);
    Train getTrainById(int trId);
    List<Train> getAllTrains();

    Page<Train> getTrainByeIdAndTrStateAndLimit(int eId, int trState, int pageNo);
    // trState=2 发布的培训
    Page<Train> getTrainByeIdAndLimit(int eId, int pageNo);
    Page<Train> getAllTrainsByLimit(int pageNo);
    // 分页查询不能带有多表联查，rownum计数导致每一个培训记录只有一个培训对象
}
