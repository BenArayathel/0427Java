package com.banking.service;

import com.banking.exception.BusinessException;
import com.banking.models.Customer;
import com.banking.models.Employee;

public interface EmployeeService {

    Employee employeeLogin(Employee employee) throws BusinessException;
}
