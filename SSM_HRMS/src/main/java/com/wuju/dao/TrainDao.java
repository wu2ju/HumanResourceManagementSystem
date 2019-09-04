package com.wuju.dao;

import com.wuju.model.Train;

import java.util.HashMap;
import java.util.List;

public interface TrainDao {
    boolean addTrain(Train train);
    boolean updateTrain(Train train);
    boolean delTrain(int trId);
    Train getTrainById(int trId);
//    Train getTrainByIdOne(int trId); //������֮���ȡ����
    List<Train> getAllTrains();
    List<Train> getAllTrainsByLimit(HashMap map);
    // ����One��ʾ�����ж������


//    List<Train> getAllTrainsOne();

    // ��ҳ��ѯ���ܴ��ж�����飬rownum��������ÿһ����ѵ��¼ֻ��һ����ѵ����
    List<Train> getAllTrainsByLimitOne(HashMap map);
    int getAllTrainsCount();
    //��Ϊһ����ѵ�в��������ظ���Ա���������ҳ�������ѵ�������ظ����ö������ӷ�ҳû�й�ϵ
    List<Train> getTrainByeIdAndTrStateAndLimit(HashMap map);
    int getTrainCountByeIdAndTrState(HashMap map);

    List<Train> getTrainByeIdAndLimit(HashMap map);
    int getTrainCountByeId(int eId);
}
