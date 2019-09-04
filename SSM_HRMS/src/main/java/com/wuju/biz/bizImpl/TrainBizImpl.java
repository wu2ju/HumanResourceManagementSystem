package com.wuju.biz.bizImpl;

import com.wuju.biz.TrainBiz;
import com.wuju.dao.TrainDao;
import com.wuju.dao.TrainObjectDao;
import com.wuju.model.Employee;
import com.wuju.model.Page;
import com.wuju.model.Train;
import com.wuju.model.TrainObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class TrainBizImpl implements TrainBiz {
    @Resource
    private TrainDao trainDao;
    @Resource
    private TrainObjectDao trainObjectDao;

    @Override
    public boolean addTrain(Train train) {
        // �����ѵ��ͬʱ������ѵ������ӵ���һ����ѵ�������
        if (train.getTrBegin().before(new Date())){
            return false;
        }
        if (train.getTrEnd().before(train.getTrBegin())){
            return false;
        }
        List<Employee> employees = train.getEmployees();
        trainDao.addTrain(train);
        if (employees != null && employees.size() > 0){
            for (Employee e : employees) {
                trainObjectDao.addTrainObject(new TrainObject(e, train, 1));
            }
        }
        return true;
    }

    @Override
    public boolean updateTrain(Train train) {
        if (train.getTrBegin().before(new Date())){
            return false;
        }
        if (train.getTrEnd().before(train.getTrBegin())){
            return false;
        }
        return trainDao.updateTrain(train);
    }

    @Override
    public boolean delTrain(int trId) {
        Train train = trainDao.getTrainById(trId);
        if (train == null){
            return false;
        }
        trainObjectDao.delTrainObjectByTrId(trId);
        return trainDao.delTrain(trId);
    }

    @Override
    public Train getTrainById(int trId) {
        return trainDao.getTrainById(trId);
        //ʹ�������Ӳ�ѯ֮�󣬲��������м�¼�����ҳ�����Ϊnull
        /*Train train = trainDao.getTrainById(trId);
        if (train == null){
            return trainDao.getTrainByIdOne(trId);
        }
        return train;*/
    }

    @Override
    public List<Train> getAllTrains() {
        return null;
//        ������
//        return trainDao.getAllTrains();
//        ������
        /*List<Train> trains = trainDao.getAllTrains();
        List<Train> trainsOne = trainDao.getAllTrainsOne();
        if (trains == null || trains.size() == 0){
            // ������鲻һ���ܲ鵽����ô����ʾ����
            return trainsOne;
        }
        if (trainsOne != null && trainsOne.size() > 0){
            for (Train train : trainsOne) {
                for (Train train1 : trains) {
                    if (train.getTrId() .equals(train1.getTrId())){
                        train.setEmployees(train1.getEmployees());
                    }
                }
            }
            return trainsOne;
        }
        return null;*/
    }

    @Override
    public Page<Train> getTrainByeIdAndTrStateAndLimit(int eId, int trState, int pageNo) {
        Page page=new Page<>();
        HashMap<String,Object> map = new HashMap<>();
        map.put("eId",eId);
        map.put("trState",trState);
        int totalRows = trainDao.getTrainCountByeIdAndTrState(map);
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
//        List<Train> trains = trainDao.getAllTrainsByLimit(map);
        //��������ٸ�ֵ
        List<Train> trains1 = new ArrayList<>();
        List<Train> trains = trainDao.getAllTrains();
        List<Train> trainsOne = trainDao.getTrainByeIdAndTrStateAndLimit(map);
        trains1 = getTrains(trains1, trains, trainsOne);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(trains1);
        return page;
    }

    @Override
    public Page<Train> getTrainByeIdAndLimit(int eId, int pageNo) {
        Page page=new Page<>();
        int totalRows = trainDao.getTrainCountByeId(eId);
        HashMap<String,Object> map = new HashMap<>();
        map.put("eId",eId);
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        //��������ٸ�ֵ
        List<Train> trains1 = new ArrayList<>();
        List<Train> trains = trainDao.getAllTrains();
        List<Train> trainsOne = trainDao.getTrainByeIdAndLimit(map);
        trains1 = getTrains(trains1, trains, trainsOne);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(trains1);
        return page;
    }

    private List<Train> getTrains(List<Train> trains1, List<Train> trains, List<Train> trainsOne) {
        if (trains == null || trains.size() == 0){
            // ������鲻һ���ܲ鵽����ô����ʾ����
            trains1 = trainsOne;
        } else {
            if (trainsOne != null && trainsOne.size() > 0){
                for (Train train : trainsOne) {
                    for (Train train1 : trains) {
                        if (train.getTrId() .equals(train1.getTrId())){
                            train.setEmployees(train1.getEmployees());
                        }
                    }
                }
                trains1 = trainsOne;
            }
        }
        return trains1;
    }

    @Override
    public Page<Train> getAllTrainsByLimit(int pageNo) {
        Page page=new Page<>();
        int totalRows = trainDao.getAllTrainsCount();
        HashMap<String,Object> map = new HashMap<>();
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
//        List<Train> trains = trainDao.getAllTrainsByLimit(map);
        //��������ٸ�ֵ
        List<Train> trains1 = new ArrayList<>();
        List<Train> trains = trainDao.getAllTrains();
        List<Train> trainsOne = trainDao.getAllTrainsByLimitOne(map);
        trains1 = getTrains(trains1, trains, trainsOne);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(trains1);
        return page;
    }


    // ��ҳ�������Ӷ������һ��ʹ�ã���ѵ��¼�����ظ�
    /*@Override
    public Page<Train> getAllTrainsByLimit(int pageNo) {
        Page page=new Page<>();
        int totalRows = trainDao.getAllTrainsCount();
        HashMap<String,Object> map = new HashMap<>();
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<Train> trains = trainDao.getAllTrainsByLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(trains);
        return page;
    }*/
}
