package com.bhank.service.impl;

import java.util.Date;
import java.util.List;

import com.bhank.dao.impl.EmployeeDAOImpl;
import com.bhank.exception.BusinessException;
import com.bhank.main.Main;
import com.bhank.model.Employee;
import com.bhank.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
	EmployeeDAOImpl dao=new EmployeeDAOImpl();

	@Override
	public Employee createEmployee(Employee employee) throws BusinessException {
		if (employee == null) {
			throw new BusinessException("Employee object was not created");
		} else if (!isValidName(employee.getName())) {
			throw new BusinessException("Employee name is invalid");
		} else if (!isValidPassword(employee.getPassword())) {
			throw new BusinessException("Employee password is invalid");
		} else {
			Main.logger.info("Employee service successfully created employee \""+employee+"\"");
			employee = dao.createEmployee(employee);
		}
		return employee;
	}
	
	public static Date isValidDob(String dob) throws BusinessException {
		return CustomerServiceImpl.isValidDob(dob);
	}

	private boolean isValidPassword(String password) {
		boolean b = false;
		if (password.matches("[a-zA-Z0-9]{8,20}")) {
			b = true;
		}
		return b;
	}

	private boolean isValidName(String name) {
		boolean b = false;
		if (name.matches("[a-zA-Z ]{1,20}")) {
			b = true;
		}
		return b;
	}

	@Override
	public Employee updateEmployee(Employee e) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> selectAllEmployees() throws BusinessException {
		return dao.selectAllEmployees();
	}

	@Override
	public Employee selectEmployeeByNameAndPassword(String employeeName, String employeePassword) throws BusinessException {
		Employee employee = null;
		if(employeeName==null ||employeePassword==null) {
			Main.logger.error("Employee service failed to selected employee by name and password because name or password was null");
			throw new BusinessException("Name or password is null");
		} else if (!isValidName(employeeName)) {
			Main.logger.error("Employee service failed to selected employee by name and password due to invalid name \""+employeeName+"\"");
			throw new BusinessException("Name is invalid");
		} else if (!isValidPassword(employeePassword)) {
			Main.logger.error("Employee service failed to selected employee by name and password due to invalid password \""+employeePassword+"\"");
			throw new BusinessException("password is invalid");
		} else {
			Main.logger.info("Employee service successfully selected employee by name \""+employeeName+"\" and password \""+employeePassword+"\"");
			employee=dao.selectEmployeeByNameAndPassword(employeeName, employeePassword);
		}
		
		return employee;
	}

	@Override
	public Employee selectEmployeeById(String employeeId) throws BusinessException {
		Employee employee=null;
		if(employeeId==null) {
			Main.logger.error("Employee service failed to select employee by id because id was null");
			throw new BusinessException("id is null");
		} else {
			Main.logger.info("Employee service successfully selected employee by id \""+employeeId+"\"");
			employee=dao.selectEmployeeById(employeeId);
		}
		return employee;
	}

	@Override
	public void deleteEmployee(Employee e) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	

}
