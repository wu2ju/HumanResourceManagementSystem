package com.wuju.biz.bizImpl;

import com.wuju.biz.CompanyBiz;
import com.wuju.dao.CompanyDao;
import com.wuju.model.Company;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CompanyBizImpl implements CompanyBiz {
    @Resource
    private CompanyDao companyDao;

    public Company getCompanyByName(String cpName) {
        return companyDao.getCompanyByName(cpName);
    }
}
