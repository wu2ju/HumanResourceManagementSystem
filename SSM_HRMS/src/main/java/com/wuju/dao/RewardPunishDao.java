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
    int getRewardPunishCountByeId(int eId);
    List<RewardPunish> getAllRewardPunishByLimit(HashMap map);
    int getAllRewardPunishCount();
    RewardPunish getRewardPunishByeIdAndRpTimeAndRpRecord(HashMap map);
    // ���Ա��������Ϊ�򿨵õ��Ľ��ͼ�¼
    List<RewardPunish> getAllRewardPunishs();
}