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
    // trState=2 ��������ѵ
    Page<Train> getTrainByeIdAndLimit(int eId, int pageNo);
    Page<Train> getAllTrainsByLimit(int pageNo);
    // ��ҳ��ѯ���ܴ��ж�����飬rownum��������ÿһ����ѵ��¼ֻ��һ����ѵ����
}
