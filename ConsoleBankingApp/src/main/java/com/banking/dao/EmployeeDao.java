package com.banking.dao;

import com.banking.exception.BusinessException;
import com.banking.models.Employee;

public interface EmployeeDao {

    Employee getEmployeeByLogin(Employee employee) throws BusinessException;
}
