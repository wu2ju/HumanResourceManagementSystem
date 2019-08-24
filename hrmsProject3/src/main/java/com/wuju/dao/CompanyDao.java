package com.wuju.dao;

import com.wuju.model.Company;

public interface CompanyDao {
    Company getCompanyByName(String cpName);
}
