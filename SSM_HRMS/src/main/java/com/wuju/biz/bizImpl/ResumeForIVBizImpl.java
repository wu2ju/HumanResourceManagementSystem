package com.wuju.biz.bizImpl;

import com.wuju.biz.ResumeForIVBiz;
import com.wuju.dao.ResumeForIVDao;
import com.wuju.model.ResumeForIV;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class ResumeForIVBizImpl implements ResumeForIVBiz {
    @Resource
    private ResumeForIVDao resumeForIVDao;

    @Override
    public boolean addResumeForIV(ResumeForIV resume) {
        return resumeForIVDao.addResumeForIV(resume);
    }

    @Override
    public boolean delResumeForIV(int rId) {
        return resumeForIVDao.delResumeForIV(rId);
    }

    @Override
    public boolean updateResumeForIV(int rState, int rId) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("rState",rState);
        map.put("rId",rId);
        return resumeForIVDao.updateResumeForIV(map);
    }

    @Override
    public ResumeForIV getResumeForIVById(int rId) {
        return resumeForIVDao.getResumeForIVById(rId);
    }

    @Override
    public List<ResumeForIV> getResumeForIVByuId(int uId) {
        return resumeForIVDao.getResumeForIVByuId(uId);
    }
}
