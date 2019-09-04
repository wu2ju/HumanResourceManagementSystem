package com.wuju.dao;

import com.wuju.model.Train;
import com.wuju.model.TrainObject;

import java.util.HashMap;
import java.util.List;

public interface TrainObjectDao {
    boolean addTrainObject(TrainObject to);
    boolean updateTrainObject(TrainObject to);
    boolean updateTrainObjectToState(TrainObject to);
    boolean delTrainObject(int toId);
    boolean delTrainObjectByTrId(int trId);
    TrainObject getTrainObjectById(int toId);
    TrainObject getTrainObjectByeIdAndTrId(HashMap map);
    TrainObject getTrainObjectByeIdAndTrIdAndToState(HashMap map);
    List<TrainObject> getTrainObjectBytrId(int trId);
    List<TrainObject> getTrainObjectByeIdAndToState(HashMap map);
}
