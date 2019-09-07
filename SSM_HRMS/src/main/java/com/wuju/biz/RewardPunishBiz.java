package com.wuju.biz;

import com.wuju.model.Page;
import com.wuju.model.RewardPunish;

import java.util.List;

public interface RewardPunishBiz {
    boolean addRewardPunish(RewardPunish rp);
    boolean updateRewardPunish(RewardPunish rp);
    boolean delRewardPunish(int rpId);
    RewardPunish getRewardPunishById(int rpId);
    Page<RewardPunish> getRewardPunishMonthByLimit(int month, int pageNo);
    Page<RewardPunish> getRewardPunishByeIdAndLimit(int eId, int pageNo);
    Page<RewardPunish> getRewardPunishMonthByeIdAndLimit(int month, int eId, int pageNo);
    Page<RewardPunish> getAllRewardPunishByLimit(int pageNo);
    RewardPunish getRewardPunishByeIdAndRpTimeAndRpRecord(int eId, int rpRecord);
    // ���Ա��������Ϊ�򿨵õ��Ľ��ͼ�¼
    List<RewardPunish> getAllRewardPunishs();
}
