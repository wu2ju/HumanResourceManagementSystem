package com.wuju.biz;

import com.wuju.model.Train;
import com.wuju.model.TrainObject;

public interface TrainObjectBiz {
    boolean addTrainObject(TrainObject to);
    boolean updateTrainObjectToState(TrainObject to);
    boolean delTrainObject(int toId);
    TrainObject getTrainObjectById(int toId);
    TrainObject getTrainObjectByeIdAndTrId(int eId, int trId);
    TrainObject getTrainObjectByeIdAndTrIdAndToState(int eId, int trId, int toState);
}
