package com.wuju.dao;

import com.wuju.model.ResumeForIV;

import java.util.List;

public interface ResumeForIVDao {
    boolean addResumeForIV(ResumeForIV resume);
    boolean delResumeForIV(int rId);
    ResumeForIV getResumeForIVById(int rId);
    List<ResumeForIV> getResumeForIVByuId(int uId);
}
