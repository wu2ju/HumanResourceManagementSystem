package com.wuju.biz;

import com.wuju.model.Resume;

import java.util.List;

public interface ResumeBiz {
    List<Resume> getResumeByuId(int uId);
    Resume getResumeById(int rId);
    boolean addResume(Resume resume);
    boolean updateResume(Resume resume);
    boolean delResume(int rId);
}
