package com.bhank.webapp.dao;

import java.util.List;

import com.bhank.webapp.exception.BusinessException;
import com.bhank.webapp.model.Employee;

public interface EmployeeDAO {
	//create
	Employee createEmployee(Employee e) throws BusinessException;
	
	//update
	Employee updateEmployee(Employee e) throws BusinessException;
	
	//read
	List<Employee> selectAllEmployees() throws BusinessException;
	Employee selectEmployeeByNameAndPassword(String firstName, String password) throws BusinessException;
	Employee selectEmployeeById(String id) throws BusinessException;
	
	//delete
	Employee deleteEmployee(Employee e) throws BusinessException;
}
