package com.wuju.dao;

import com.wuju.model.CheckIn;
import com.wuju.model.RewardPunish;

import java.util.HashMap;
import java.util.List;

public interface RewardPunishDao {
    boolean addRewardPunish(RewardPunish rp);
    boolean updateRewardPunish(RewardPunish rp);
    boolean delRewardPunish(int rpId);
    RewardPunish getRewardPunishById(int rpId);
    List<RewardPunish> getRewardPunishByeIdAndLimit(HashMap map);
    List<RewardPunish> getRewardPunishMonthByLimit(HashMap map);
    List<RewardPunish> getRewardPunishMonthByeIdAndLimit(HashMap map);
    int getRewardPunishCountMonthByeId(HashMap map);
    int getRewardPunishCountMonth(int month);
    int getRewardPunishCountByeId(int eId);
    List<RewardPunish> getRewardPunishMonthByeId(int eId);
    List<RewardPunish> getAllRewardPunishByLimit(HashMap map);
    int getAllRewardPunishCount();
    RewardPunish getRewardPunishByeIdAndRpTimeAndRpRecord(HashMap map);
    // ���Ա��������Ϊ�򿨵õ��Ľ��ͼ�¼
    List<RewardPunish> getAllRewardPunishs();
}
