package com.wuju.dao;

import com.wuju.model.Train;

import java.util.HashMap;
import java.util.List;

public interface TrainDao {
    boolean addTrain(Train train);
    boolean updateTrain(Train train);
    boolean delTrain(int trId);
    Train getTrainById(int trId);
//    Train getTrainByIdOne(int trId); //外连接之后就取消了
    List<Train> getAllTrains();
    List<Train> getAllTrainsByLimit(HashMap map);
    // 加了One表示不进行多表联查


//    List<Train> getAllTrainsOne();

    // 分页查询不能带有多表联查，rownum计数导致每一个培训记录只有一个培训对象
    List<Train> getAllTrainsByLimitOne(HashMap map);
    int getAllTrainsCount();
    //因为一个培训中不可能有重复的员工，所以找出来的培训不可能重复，用多表联查加分页没有关系
    List<Train> getTrainByeIdAndTrStateAndLimit(HashMap map);
    int getTrainCountByeIdAndTrState(HashMap map);

    List<Train> getTrainByeIdAndLimit(HashMap map);
    int getTrainCountByeId(int eId);
}
