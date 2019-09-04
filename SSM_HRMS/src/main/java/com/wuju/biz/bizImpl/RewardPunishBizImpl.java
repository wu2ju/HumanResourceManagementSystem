package com.wuju.biz.bizImpl;

import com.wuju.biz.RewardPunishBiz;
import com.wuju.dao.RewardPunishDao;
import com.wuju.model.Page;
import com.wuju.model.RewardPunish;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class RewardPunishBizImpl implements RewardPunishBiz {
    @Resource
    private RewardPunishDao rewardPunishDao;

    @Override
    public boolean addRewardPunish(RewardPunish rp) {
        return rewardPunishDao.addRewardPunish(rp);
    }

    @Override
    public boolean updateRewardPunish(RewardPunish rp) {
        return rewardPunishDao.updateRewardPunish(rp);
    }

    @Override
    public boolean delRewardPunish(int rpId) {
        return rewardPunishDao.delRewardPunish(rpId);
    }

    @Override
    public RewardPunish getRewardPunishById(int rpId) {
        return rewardPunishDao.getRewardPunishById(rpId);
    }

    @Override
    public Page<RewardPunish> getRewardPunishByeIdAndLimit(int eId, int pageNo) {
        Page page=new Page<>();
        int totalRows = rewardPunishDao.getRewardPunishCountByeId(eId);
        HashMap<String,Object> map = new HashMap<>();
        map.put("eId",eId);
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<RewardPunish> rewardPunishes = rewardPunishDao.getRewardPunishByeIdAndLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(rewardPunishes);
        return page;
    }

    @Override
    public Page<RewardPunish> getAllRewardPunishByLimit(int pageNo) {
        Page page=new Page<>();
        int totalRows = rewardPunishDao.getAllRewardPunishCount();
        HashMap<String,Object> map = new HashMap<>();
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<RewardPunish> rewardPunishes = rewardPunishDao.getAllRewardPunishByLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(rewardPunishes);
        return page;
    }

    @Override
    public RewardPunish getRewardPunishByeIdAndRpTimeAndRpRecord(int eId, int rpRecord) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("eId",eId);
        map.put("rpRecord",rpRecord);
        return rewardPunishDao.getRewardPunishByeIdAndRpTimeAndRpRecord(map);
    }

    @Override
    public List<RewardPunish> getAllRewardPunishs() {
        return rewardPunishDao.getAllRewardPunishs();
    }
}
