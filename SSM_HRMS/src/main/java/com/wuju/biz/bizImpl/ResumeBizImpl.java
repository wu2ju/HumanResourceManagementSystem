package com.wuju.biz.bizImpl;

import com.wuju.biz.ResumeBiz;
import com.wuju.dao.ResumeDao;
import com.wuju.model.Resume;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResumeBizImpl implements ResumeBiz {
    @Resource
    private ResumeDao resumeDao;

    @Override
    public List<Resume> getResumeByuId(int uId) {
        return resumeDao.getResumeByuId(uId);
    }

    @Override
    public Resume getResumeById(int rId) {
        return resumeDao.getResumeById(rId);
    }

    @Override
    public boolean addResume(Resume resume) {
        return resumeDao.addResume(resume);
    }

    @Override
    public boolean updateResume(Resume resume) {
        return resumeDao.updateResume(resume);
    }

    @Override
    public boolean delResume(int rId) {
        return resumeDao.delResume(rId);
    }
}
