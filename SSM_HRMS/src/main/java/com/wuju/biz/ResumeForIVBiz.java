package com.wuju.biz;

import com.wuju.model.ResumeForIV;

import java.util.List;

public interface ResumeForIVBiz {
    boolean addResumeForIV(ResumeForIV resume);
    boolean delResumeForIV(int rId);
    ResumeForIV getResumeForIVById(int rId);
    List<ResumeForIV> getResumeForIVByuId(int uId);
}
