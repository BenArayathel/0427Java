package com.bhank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bhank.dao.EmployeeDAO;
import com.bhank.dbutil.OracleConnection;
import com.bhank.exception.BusinessException;
import com.bhank.main.Main;
import com.bhank.model.Customer;
import com.bhank.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public Employee createEmployee(Employee e) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "{call CREATEEMPLOYEE(?,?,?,?)}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(2, e.getName());
			callableStatement.setString(3, e.getPassword());
			callableStatement.setDate(4, new java.sql.Date(e.getDob().getTime()));

			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);

			callableStatement.execute();

			e.setId(callableStatement.getString(1));

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
		return e;
	}

	@Override
	public Employee updateEmployee(Employee e) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> selectAllEmployees() throws BusinessException {
		List<Employee> employeeList = new ArrayList<>();
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "{select * from employee}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			ResultSet resultSet = callableStatement.executeQuery();
			while(resultSet.next()) {
				Employee e = new Employee();
				e.setId(resultSet.getString("id"));
				e.setName(resultSet.getString("name"));
				e.setPassword(resultSet.getString("password"));
				e.setDob(resultSet.getDate("dob"));
				employeeList.add(e);
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
			throw new BusinessException("Internal error occured contact SYSADMIN");
		}
		return employeeList;
	}

	@Override
	public Employee selectEmployeeByNameAndPassword(String employeeName, String employeePassword) throws BusinessException {
		Employee employee = new Employee();
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "Select id,dob from employee where name= \'"+employeeName+"\' and password=\'"+employeePassword+"\'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
			employee.setId(resultSet.getString("id"));
			employee.setName(employeeName);
			employee.setPassword(employeePassword);
			employee.setDob(resultSet.getDate("dob"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			Main.logger.error("Employee DAO failed to select employee by name \""+employeeName+"\" and password \""+employeePassword+"\"");
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		Main.logger.info("Employee DAO successfully selected employee by name \""+employeeName+"\" and password \""+employeePassword+"\" from database");
		return employee;
	}

	@Override
	public Employee selectEmployeeById(String id) throws BusinessException {
		Employee employee = new Employee();
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "Select id,name,password,dob from customer where id= \'"+id+"\'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
			employee.setId(resultSet.getString("id"));
			employee.setName("name");
			employee.setPassword("password");
			employee.setDob(resultSet.getDate("dob"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		return employee;
	}

	@Override
	public Employee deleteEmployee(Employee e) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
