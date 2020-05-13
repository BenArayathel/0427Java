package com.banking.dao.impl;

import com.banking.dao.EmployeeDao;
import com.banking.dbutil.OracleConnection;
import com.banking.exception.BusinessException;
import com.banking.models.Customer;
import com.banking.models.Employee;

import java.sql.*;
import org.apache.log4j.*;

public class EmployeeDaoImpl implements EmployeeDao {

    final static Logger loggy = Logger.getLogger(EmployeeDaoImpl.class);
    @Override
    public Employee getEmployeeByLogin(Employee employee) throws BusinessException {
        Employee emp = null;
        try (Connection connection = OracleConnection.getConnection()) {
            String sql = "Select id, username from employee where username = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, employee.getUsername());
            ps.setString(2, employee.getPassword());

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                emp = new Employee();

                emp.setId(resultSet.getString("id"));
                emp.setUsername(resultSet.getString("username"));
                loggy.info("You have been logged in. \nWelcome " + emp.getUsername());
                return emp;
            } else {
                throw new BusinessException("Username " + employee.getUsername() + " does not exist");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new BusinessException("Internal Error, please don't panic.");
        }
    }

}
