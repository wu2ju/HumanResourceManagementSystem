package com.wuju.biz;

import com.wuju.model.Company;

public interface CompanyBiz {
    Company getCompanyByName(String cpName);
    Company getCompanyById(int cpId);
    boolean updateCompany(Company c);
}
