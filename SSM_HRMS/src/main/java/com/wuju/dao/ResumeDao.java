package com.wuju.dao;

import com.wuju.model.Resume;

import java.util.List;

public interface ResumeDao {
    boolean addResume(Resume resume);
    boolean updateResume(Resume resume);
    boolean delResume(int rId);
    Resume getResumeById(int rId);
    List<Resume> getResumeByuId(int uId);
}
