package com.bankofben.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.models.Employee;
import com.bankofben.models.Payment;
import com.bankofben.models.Request;
import com.bankofben.models.Transaction;
import com.bankofben.models.Transfer;
import com.bankofben.models.User;

public class BankOfBenDAO implements BankOfBenDAOInterface {

	@Override
	public Customer createCustomer(User user) throws BusinessException {
		
		Customer customer = null;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "{call createcustomer(?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement cs = connection.prepareCall(sqlCall);
			
			// Tell SQL Procedure there should be an out parameter first of type VARCHAR
			cs.registerOutParameter(1, java.sql.Types.VARCHAR);
			
			//Tell SQL Procedure there should be the following parameters
			cs.setString(2, user.getFirstName());
			cs.setString(3, user.getMiddleName());
			cs.setString(4, user.getLastName());
			cs.setString(5, user.getMomsMaidenName());
			cs.setDate(6, new java.sql.Date(user.getDob().getTime()));
			cs.setLong(7, user.getSsn());
			cs.setString(8, user.getEmail());
			cs.setLong(9, user.getPhoneNumber());
			cs.setString(10, user.getUsername());
			cs.setString(11, user.getPassword());
			
			boolean customerCreated = cs.execute();
			if (customerCreated) {
				customer = new Customer(user.getFirstName(), user.getMiddleName(), user.getLastName(),
						user.getMomsMaidenName(), user.getDob(), user.getSsn(), user.getEmail(),
						user.getPhoneNumber(), user.getUsername(), user.getPassword(), cs.getString(1), true);
			} else {
				throw new BusinessException("Internal database error. Customer could not be created. "
						+ "Please contact your SYSADMIN");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return customer;
	}

	@Override
	public Account createAccount(Account account) throws BusinessException {
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "{call createaccount(?,?,?,?)}";
			CallableStatement cs = connection.prepareCall(sqlCall);
			
			// Tell SQL Procedure there should be an out parameter first of type VARCHAR
			cs.registerOutParameter(1, java.sql.Types.NUMERIC);
			cs.registerOutParameter(4, java.sql.Types.NUMERIC);
			
			//Tell SQL Procedure there should be the following parameters
			cs.setDouble(2, account.getBalance());
			cs.setString(3, account.getCustomerId());
			
			if (customerIdExists(account.getCustomerId())) {
				boolean accountCreated = cs.execute();
				if (accountCreated) {
					account = new Account(cs.getLong("Account Number"), cs.getDouble("Balance"), cs.getString("Customer ID"));
				} else {
					throw new BusinessException("Internal database error. Account could not be created. "
							+ "Please contact your SYSADMIN");
				}
			} else {
				throw new BusinessException("Could not create account. The customer id "+account.getCustomerId()+" does not exist. "
						+ "Please ensure the customer has applied for the account first.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return account;
	}

	@Override
	public Customer getCustomerById(String customerId) throws BusinessException {
		
		Customer customer = null;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_customers WHERE \"Customer ID\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, customerId);
			
			ResultSet rset = ps.executeQuery();
			if (rset.next()) {
				customer = new Customer(rset.getString("First Name"), rset.getString("Middle Name"),
						rset.getString("Last Name"), rset.getString("Mom's Maiden Name"), rset.getDate("Date of Birth"),
						rset.getLong("Social Security Number"), rset.getString("Email"), rset.getLong("Phone Number"),
						rset.getString("Username"), rset.getString("Password"), rset.getString("Customer ID"),
						rset.getBoolean("Application Pending"));
			} else {
				throw new BusinessException("Customer ID "+customerId+" does not exist.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return customer;
	}

	@Override
	public Customer getCustomerByUsername(String username) throws BusinessException {
		
		Customer customer = null;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_customers WHERE \"Username\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, username);
			
			ResultSet rset = ps.executeQuery();
			if (rset.next()) {
				customer = new Customer(rset.getString("First Name"), rset.getString("Middle Name"),
						rset.getString("Last Name"), rset.getString("Mom's Maiden Name"), rset.getDate("Date of Birth"),
						rset.getLong("Social Security Number"), rset.getString("Email"), rset.getLong("Phone Number"),
						rset.getString("Username"), rset.getString("Password"), rset.getString("Customer ID"),
						rset.getBoolean("Application Pending"));
			} else {
				throw new BusinessException("Customer Username "+username+" does not exist.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return customer;
	}

	@Override
	public Customer getCustomerByEmail(String email) throws BusinessException {
		
		Customer customer = null;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_customers WHERE \"Eamil\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, email);
			
			ResultSet rset = ps.executeQuery();
			if (rset.next()) {
				customer = new Customer(rset.getString("First Name"), rset.getString("Middle Name"),
						rset.getString("Last Name"), rset.getString("Mom's Maiden Name"), rset.getDate("Date of Birth"),
						rset.getLong("Social Security Number"), rset.getString("Email"), rset.getLong("Phone Number"),
						rset.getString("Username"), rset.getString("Password"), rset.getString("Customer ID"),
						rset.getBoolean("Application Pending"));
			} else {
				throw new BusinessException("Customer email "+email+" does not exist.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomersOrderedBy(String columnName) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomersOrderedBy(int columnIndex) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getCustomersByColumn(String columnName, String columnValue) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getCustomersByColumn(int columnIndex, String columnValue) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Customer> getCustomersByApplicationPendingStatus(boolean applicationPending) throws BusinessException {
		
		List<Customer> customers = new ArrayList<>();
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_customers WHERE \"Application Pending\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setBoolean(1, applicationPending);
			
			ResultSet rset = ps.executeQuery();

			while (rset.next()) {
				customers.add(new Customer(rset.getString("First Name"), rset.getString("Middle Name"),
						rset.getString("Last Name"), rset.getString("Mom's Maiden Name"),
						rset.getDate("Date of Birth"), rset.getLong("Social Security Number"),
						rset.getString("Email"), rset.getLong("Phone Number"), rset.getString("Username"),
						rset.getString("Password"), rset.getString("Customer ID"),
						rset.getBoolean("Application Pending")));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return customers;
	}

	@Override
	public Employee getEmployeeById(String employeeId) throws BusinessException {
		
		Employee employee = null;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_employees WHERE \"Employee ID\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, employeeId);
			
			ResultSet rset = ps.executeQuery();
			if (rset.next()) {
				employee = new Employee(rset.getString("First Name"), rset.getString("Middle Name"),
						rset.getString("Last Name"), rset.getString("Mom's Maiden Name"), rset.getDate("Date of Birth"),
						rset.getLong("Social Security Number"), rset.getString("Email"), rset.getLong("Phone Number"),
						rset.getString("Username"), rset.getString("Password"), rset.getString("Employee ID"),
						rset.getString("Designation"), rset.getBoolean("Can Hire"));
			} else {
				throw new BusinessException("Customer ID "+employeeId+" doesn't exist.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return employee;
	}

	@Override
	public Employee getEmployeeByUsername(String username) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployeeByEmail(String email) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployeesOrderedBy(String columnName) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployeesOrderedBy(int columnIndex) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getEmployeesByColumn(String columnName, String columnValue) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getEmployeesByColumn(int columnIndex, String columnValue) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccountByAccountNumber(Long accountNumber) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllAccounts() throws BusinessException {		
		List<Account> accounts = new ArrayList<>();
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_accounts";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			
			ResultSet rset = ps.executeQuery();
			
			while (rset.next()) {
				accounts.add(new Account(rset.getLong("Account Number"), rset.getDouble("Balance"),
						rset.getString("Customer ID")));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return accounts;
	}

	@Override
	public List<Account> getAllAccountsOrderedBy(String columnName) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllAccountsOrderedBy(int columnIndex) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountsForCustomerId(String customerId) throws BusinessException {		
		List<Account> accounts = new ArrayList<>();
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_accounts WHERE \"Customer ID\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, customerId);
			
			ResultSet rset = ps.executeQuery();
			
			while (rset.next()) {
				accounts.add(new Account(rset.getLong("Account Number"), rset.getDouble("Balance"),
						rset.getString("Customer ID")));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return accounts;
	}

	@Override
	public List<Account> getAccountsByBalanceComparison(String balanceComparison) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomerFirstLame(String firstName, String customerId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomerMiddleName(String middleName, String customerId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomerLastName(String lastName, String customerId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomerMomsMaidenName(String momsMaidenName, String customerId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomerDateOfBirth(Date dob, String customerId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomerSocialSecurityNumber(long ssn, String customerId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomerSocialSecurityNumber(String ssn, String customerId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomerPhoneNumber(long phoneNumber, String customerId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomerPhoneNumber(String phoneNumber, String customerId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomerUsername(String username, String customerId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomerPassword(String password, String customerId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Customer updateCustomerApplicationPending(boolean applicationPending, String customerId) throws BusinessException {
		updateCustomerApplicationPending_returnNothing(applicationPending, customerId);
		return getCustomerById(customerId);
	}
	
	public void updateCustomerApplicationPending_returnNothing(boolean applicationPending, String customerId) throws BusinessException {
		try(Connection connection = OracleDbConnection.getConnection()){
			String sqlCall = "UPDATE bankofben_customers SET \"Application Pending\"=? WHERE \"Customer ID\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setBoolean(1, applicationPending);
			ps.setString(2, customerId);
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated<1) {
				throw new BusinessException("Internal database error. Could not update application status for "
						+customerId+". Contact your SYSADMIN");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
	}

	@Override
	public Employee updateEmployeeFirstLame(String firstName, String employeeId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployeeMiddleName(String middleName, String employeeId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployeeLastName(String lastName, String employeeId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployeeMomsMaidenName(String momsMaidenName, String employeeId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployeeDateOfBirth(Date dob, String employeeId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployeeSocialSecurityNumber(long ssn, String employeeId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployeeSocialSecurityNumber(String ssn, String employeeId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployeePhoneNumber(long phoneNumber, String employeeId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployeePhoneNumber(String phoneNumber, String employeeId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployeeUsername(String username, String employeeId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployeePassword(String password, String employeeId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployeeDesignation(String designation, String employeeId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Employee updateEmployeeSupervisor(String supervisorId, String employeeId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;	
	}

	@Override
	public Account updateAccountBalance(double balance, long accountNumber) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account updateAccountCustomerId(int customerId, long accountNumber) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(String customerId) throws BusinessException {
		try(Connection connection = OracleDbConnection.getConnection()){
			String sqlCall = "DELETE bankofben_customers WHERE \"Customer ID\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, customerId);
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated<1) {
				throw new BusinessException("Internal database error. Could not remove customer information for "
						+customerId+". Contact your SYSADMIN.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
	}

	@Override
	public void deleteEmployee(String employeeId) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount(String accountId) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee createEmployee(User user, String designation, Employee supervisor) throws BusinessException {
		
		Employee employee = null;
		
		if (supervisor.canHire()) {
			
			try(Connection connection = OracleDbConnection.getConnection()){
				
				// TODO NOT DONE YET!!!!! MAKE CREATEEMPLOYEE
				String sqlCall = "{call createemployee(?,?,?,?,?,?,?,?,?,?,?)}";
				CallableStatement cs = connection.prepareCall(sqlCall);
				
				// Tell SQL Procedure there should be an out parameter first of type VARCHAR
				cs.registerOutParameter(1, java.sql.Types.VARCHAR);
				
				//Tell SQL Procedure there should be the following parameters
				cs.setString(2, user.getFirstName());
				cs.setString(3, user.getMiddleName());
				cs.setString(4, user.getLastName());
				cs.setString(5, user.getMomsMaidenName());
				cs.setDate(6, new java.sql.Date(user.getDob().getTime()));
				cs.setLong(7, user.getSsn());
				cs.setString(8, user.getEmail());
				cs.setLong(9, user.getPhoneNumber());
				cs.setString(10, user.getUsername());
				cs.setString(11, user.getPassword());
				cs.setString(12,  designation);
				cs.setString(13, supervisor.getId());
				
				boolean employeeCreated = cs.execute();
				if (employeeCreated) {
					employee = new Employee(user.getFirstName(), user.getMiddleName(), user.getLastName(),
							user.getMomsMaidenName(), user.getDob(), user.getSsn(), user.getEmail(),
							user.getPhoneNumber(), user.getUsername(), user.getPassword(), cs.getString(1),
							designation, false);
				} else {
					throw new BusinessException("Internal database error. Customer could not be created. "
							+ "Please contact your SYSADMIN.");
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
			}
		} else {
			throw new BusinessException("You do not have the proper credentials to hire new employees. "
					+ "Please contact your supervisor if you believe this is incorrect or if you have a hiring recommendation.");
		}
		
		return employee;
	}
	
	public boolean customerIdExists(String customerId) throws BusinessException {
		
		boolean exists = false;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_customers WHERE \"Customer ID\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, customerId);
			
			ResultSet rset = ps.executeQuery();
			exists = rset.isBeforeFirst();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return exists;
	}
	
	public boolean employeeIdExists(String employeeId) throws BusinessException {
		
		boolean exists = false;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_employees WHERE \"Employee ID\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, employeeId);
			
			ResultSet rset = ps.executeQuery();
			exists = rset.isBeforeFirst();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return exists;
	}
	
	public boolean customerUsernameExists(String username) throws BusinessException {
		
		boolean exists = false;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_customers WHERE \"Username\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, username);
			
			ResultSet rset = ps.executeQuery();
			exists = rset.isBeforeFirst();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return exists;
	}
	
	public boolean employeeUsernameExists(String username) throws BusinessException {
		
		boolean exists = false;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_employees WHERE \"Username\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, username);
			
			ResultSet rset = ps.executeQuery();
			exists = rset.isBeforeFirst();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return exists;
	}
	
	public boolean customerEmailExists(String email) throws BusinessException {
		
		boolean exists = false;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_customers WHERE \"Email\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, email);
			
			ResultSet rset = ps.executeQuery();
			exists = rset.isBeforeFirst();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return exists;
	}
	
	public boolean employeeEmailExists(String email) throws BusinessException {
		
		boolean exists = false;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_employees WHERE \"Email\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, email);
			
			ResultSet rset = ps.executeQuery();
			exists = rset.isBeforeFirst();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return exists;
	}

	public boolean customerSsnExists(long ssn) throws BusinessException {
		
		boolean exists = false;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_customers WHERE \"Social Security Number\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setLong(1, ssn);
			
			ResultSet rset = ps.executeQuery();
			exists = rset.isBeforeFirst();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return exists;
	}

	public boolean employeeSsnExists(long ssn) throws BusinessException {
		
		boolean exists = false;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_employees WHERE \"Social Security Number\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setLong(1, ssn);
			
			ResultSet rset = ps.executeQuery();
			exists = rset.isBeforeFirst();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return exists;
	}

	public Customer loginCustomer(String username, String password) throws BusinessException {
		
		Customer customer = null;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_customers WHERE \"Username\"=? AND \"Password\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rset = ps.executeQuery();
			if (rset.next()) {
				// true if there is one record
				if(rset.next()) {
					// true if there is more than one record
					throw new BusinessException("Internal database error. Found multiple customers with these "
							+ "credentials. Please contact your SYSADMIN.");
				} else {
					// true if there is only one record
					customer = new Customer(rset.getString("First Name"), rset.getString("Middle Name"),
							rset.getString("Last Name"), rset.getString("Mom's Maiden Name"),
							rset.getDate("Date of Birth"), rset.getLong("Social Security Number"),
							rset.getString("Email"), rset.getLong("Phone Number"), rset.getString("Username"),
							rset.getString("Password"), rset.getString("Customer ID"),
							rset.getBoolean("Application Pending"));
				}
			} else {
				throw new BusinessException("Customer does not exist with these credentials. "
						+ "Please register these credentials as a new customer and apply for an account.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return customer;
	}

	public Employee loginEmployee(String username, String password) throws BusinessException{
		
		Employee employee = null;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_employees WHERE \"Username\"=? AND \"Password\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rset = ps.executeQuery();
			if (rset.next()) {
				// true if there is one record
				if(rset.next()) {
					// true if there is more than one record
					throw new BusinessException("Internal database error. Found multiple employees with these "
							+ "credentials. Please contact your SYSADMIN.");
				} else {
					// true if there is only one record
					employee = new Employee(rset.getString("First Name"), rset.getString("Middle Name"),
							rset.getString("Last Name"), rset.getString("Mom's Maiden Name"),
							rset.getDate("Date of Birth"), rset.getLong("Social Security Number"),
							rset.getString("Email"), rset.getLong("Phone Number"), rset.getString("Username"),
							rset.getString("Password"), rset.getString("Employee ID"), rset.getString("Designation"),
							rset.getBoolean("Can Hire"));
				}
			} else {
				throw new BusinessException("Employee does not exist with these credentials. "
						+ "Please contact the Bank of Ben to become an employee.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return employee;
	}
	
	public boolean paymentExists(String paymentId) throws BusinessException {
		
		boolean exists = false;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_payments WHERE \"Payment ID\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, paymentId);
			
			ResultSet rset = ps.executeQuery();
			exists = rset.isBeforeFirst();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return exists;
	}
	
	public boolean requestExists(String requestId) throws BusinessException {
		
		boolean exists = false;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_requests WHERE \"Request ID\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, requestId);
			
			ResultSet rset = ps.executeQuery();
			exists = rset.isBeforeFirst();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return exists;
	}
	
	public boolean transferExists(String transferId) throws BusinessException {
		return paymentExists(transferId) || requestExists(transferId);
	}

	@Override
	public Payment createPayment(Payment payment) throws BusinessException {
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "{call createpayment(?,?,?,?,?,?)}";
			CallableStatement cs = connection.prepareCall(sqlCall);
			
			// Tell SQL Procedure there should be an out parameter first of type VARCHAR
			cs.registerOutParameter(1, java.sql.Types.VARCHAR);
			
			//Tell SQL Procedure there should be the following parameters
			cs.setString(2, payment.getInitUserId());
			cs.setLong(3, payment.getPayingAccountNumber());
			cs.setLong(4, payment.getReceivingAccountNumber());
			cs.setBoolean(5, payment.isPending());
			
			boolean paymentCreated = cs.execute();
			if (paymentCreated) {
				payment = new Payment(cs.getString("Payment ID"), cs.getString("Initiator's ID"), cs.getBoolean("Pending"),
						cs.getLong("Paying Account Number"), cs.getLong("Receiving Account Number"), cs.getDouble("Amount"));
			} else {
				throw new BusinessException("Internal database error. Payment could not be created. "
						+ "Please contact your SYSADMIN");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return payment;
	}

	@Override
	public Request createRequest(Request request) throws BusinessException {
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "{call createrequest(?,?,?,?,?,?)}";
			CallableStatement cs = connection.prepareCall(sqlCall);
			
			// Tell SQL Procedure there should be an out parameter first of type VARCHAR
			cs.registerOutParameter(1, java.sql.Types.VARCHAR);
			
			//Tell SQL Procedure there should be the following parameters
			cs.setString(2, request.getInitUserId());
			cs.setLong(3, request.getRequestorAccountNumber());
			cs.setLong(4, request.getSoughtAccountNumber());
			cs.setBoolean(5, request.isPending());
			
			boolean paymentCreated = cs.execute();
			if (paymentCreated) {
				request = new Request(cs.getString("Request ID"), cs.getString("Initiator's ID"), cs.getBoolean("Pending"),
						cs.getLong("Requestor Account Number"), cs.getLong("Sought Account Number"), cs.getDouble("Amount"));
			} else {
				throw new BusinessException("Internal database error. Request could not be created. "
						+ "Please contact your SYSADMIN");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return request;
	}

	@Override
	public Transfer createTransfer(Transfer transfer) throws BusinessException {
		if (transfer instanceof Payment) {
			return createPayment((Payment) transfer);
		} else if (transfer instanceof Request) {
			return createRequest((Request) transfer);
		} else {
			throw new BusinessException("Cannot create transfer. Too little information. Input should specify if payment or request.");
		}
	}

	@Override
	public Payment getPaymentById(String paymentId) throws BusinessException {
		
		Payment payment = null;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_payments WHERE \"Payment ID\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, paymentId);
			
			ResultSet rset = ps.executeQuery();
			if (rset.next()) {
				payment = new Payment(rset.getString("Payment ID"), rset.getString("Initiator's ID"),
						rset.getBoolean("Pending"), rset.getLong("Paying Account Number"),
						rset.getLong("Receiving Account Number"), rset.getDouble("Amount"));
			} else {
				throw new BusinessException("Customer ID "+paymentId+" does not exist.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return payment;
	}

	@Override
	public List<Payment> getAllPayments() throws BusinessException {
		
		List<Payment> payments = new ArrayList<>();
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_payments";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			
			ResultSet rset = ps.executeQuery();

			while (rset.next()) {
				payments.add(new Payment(rset.getString("Payment ID"), rset.getString("Initiator's ID"),
						rset.getBoolean("Pending"), rset.getLong("Paying Account Number"),
						rset.getLong("Receiving Account Number"), rset.getDouble("Amount")));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return payments;
	}
	
	public List<Payment> getAllPaymentsWithPayingAccountNumber(long payingAccountNumber) throws BusinessException {
		
		List<Payment> payments = new ArrayList<>();
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_payments WHERE \"Paying Account Number\" = ?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setLong(1, payingAccountNumber);
			
			ResultSet rset = ps.executeQuery();

			while (rset.next()) {
				payments.add(new Payment(rset.getString("Payment ID"), rset.getString("Initiator's ID"),
						rset.getBoolean("Pending"), rset.getLong("Paying Account Number"),
						rset.getLong("Receiving Account Number"), rset.getDouble("Amount")));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return payments;
	}
	
	public List<Payment> getAllPaymentsWithReceivingAccountNumber(long receivingAccountNumber) throws BusinessException {
		
		List<Payment> payments = new ArrayList<>();
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_payments WHERE \"Receiving Account Number\" = ?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setLong(1, receivingAccountNumber);
			
			ResultSet rset = ps.executeQuery();

			while (rset.next()) {
				payments.add(new Payment(rset.getString("Payment ID"), rset.getString("Initiator's ID"),
						rset.getBoolean("Pending"), rset.getLong("Paying Account Number"),
						rset.getLong("Receiving Account Number"), rset.getDouble("Amount")));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return payments;
	}

	@Override
	public List<Payment> getAllPaymentsByColumn(String columnName, String columnValue) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Payment> getAllPaymentsByColumn(int columnIndex, String columnValue) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Payment> getAllPaymentsWithInitId(String initId) throws BusinessException {
		
		List<Payment> payments = new ArrayList<>();
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_payments WHERE \"Initiator's ID\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, initId);
			
			ResultSet rset = ps.executeQuery();

			while (rset.next()) {
				payments.add(new Payment(rset.getString("Payment ID"), rset.getString("Initiator's ID"),
						rset.getBoolean("Pending"), rset.getLong("Paying Account Number"),
						rset.getLong("Receiving Account Number"), rset.getDouble("Amount")));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return payments;
	}

	@Override
	public List<Payment> getAllPaymentsWithPendingStatus(boolean isPending) throws BusinessException {
		
		List<Payment> payments = new ArrayList<>();
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_payments WHERE \"Pending\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setBoolean(1, isPending);
			
			ResultSet rset = ps.executeQuery();

			while (rset.next()) {
				payments.add(new Payment(rset.getString("Payment ID"), rset.getString("Initiator's ID"),
						rset.getBoolean("Pending"), rset.getLong("Paying Account Number"),
						rset.getLong("Receiving Account Number"), rset.getDouble("Amount")));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return payments;
	}

	@Override
	public Request getRequestById(String requestId) throws BusinessException {
		
		Request request = null;
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_requests WHERE \"Request ID\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, requestId);
			
			ResultSet rset = ps.executeQuery();
			if (rset.next()) {
				request = new Request(rset.getString("Payment ID"), rset.getString("Initiator's ID"),
						rset.getBoolean("Pending"), rset.getLong("Requestor Account Number"),
						rset.getLong("Sought Account Number"), rset.getDouble("Amount"));
			} else {
				throw new BusinessException("Customer ID "+requestId+" does not exist.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return request;
	}

	@Override
	public List<Request> getAllRequests() throws BusinessException {
		
		List<Request> requests = new ArrayList<>();
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_payments";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			
			ResultSet rset = ps.executeQuery();

			while (rset.next()) {
				requests.add(new Request(rset.getString("Payment ID"), rset.getString("Initiator's ID"),
						rset.getBoolean("Pending"), rset.getLong("Paying Account Number"),
						rset.getLong("Receiving Account Number"), rset.getDouble("Amount")));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return requests;
	}

	@Override
	public List<Request> getAllRequestsByColumn(String columnName, String columnValue) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Request> getAllRequestsByColumn(int columnIndex, String columnValue) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Request> getAllRequestsWithInitId(String initId) throws BusinessException {
		
		List<Request> requests = new ArrayList<>();
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_requests WHERE \"Initiator's ID\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, initId);
			
			ResultSet rset = ps.executeQuery();

			while (rset.next()) {
				requests.add(new Request(rset.getString("Payment ID"), rset.getString("Initiator's ID"),
						rset.getBoolean("Pending"), rset.getLong("Paying Account Number"),
						rset.getLong("Receiving Account Number"), rset.getDouble("Amount")));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return requests;
	}

	@Override
	public List<Request> getAllRequestsWithPendingStatus(boolean isPending) throws BusinessException {
		
		List<Request> requests = new ArrayList<>();
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_requests WHERE \"Pending\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setBoolean(1, isPending);
			
			ResultSet rset = ps.executeQuery();

			while (rset.next()) {
				requests.add(new Request(rset.getString("Payment ID"), rset.getString("Initiator's ID"),
						rset.getBoolean("Pending"), rset.getLong("Paying Account Number"),
						rset.getLong("Receiving Account Number"), rset.getDouble("Amount")));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return requests;
	}
	
	public List<Request> getAllRequestsWithRequestorAccountNumber(long requestorAccountNumber) throws BusinessException {
		
		List<Request> requests = new ArrayList<>();
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_requests WHERE \"Requestor Account Number\" = ?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setLong(1, requestorAccountNumber);
			
			ResultSet rset = ps.executeQuery();

			while (rset.next()) {
				requests.add(new Request(rset.getString("Payment ID"), rset.getString("Initiator's ID"),
						rset.getBoolean("Pending"), rset.getLong("Requestor Account Number"),
						rset.getLong("Sought Account Number"), rset.getDouble("Amount")));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return requests;
	}
	
	public List<Request> getAllRequestsWithSoughtAccountNumber(long soughtAccountNumber) throws BusinessException {
		
		List<Request> requests = new ArrayList<>();
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_requests WHERE \"Sought Account Number\" = ?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setLong(1, soughtAccountNumber);
			
			ResultSet rset = ps.executeQuery();

			while (rset.next()) {
				requests.add(new Request(rset.getString("Payment ID"), rset.getString("Initiator's ID"),
						rset.getBoolean("Pending"), rset.getLong("Requestor Account Number"),
						rset.getLong("Sought Account Number"), rset.getDouble("Amount")));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return requests;
	}

	@Override
	public List<Transfer> getAllTransfers() throws BusinessException {
		List<Payment> payments = getAllPayments();
		List<Request> requests = getAllRequests();
		List<Transfer> transfers = new ArrayList<>();
		for (Transfer t : payments) {
			transfers.add(t);
		}
		for (Transfer t : requests) {
			transfers.add(t);
		}
		return transfers;
	}

	@Override
	public List<Transfer> getAllTransfersWithInitId(String initId) throws BusinessException {
		List<Payment> payments = getAllPaymentsWithInitId(initId);
		List<Request> requests = getAllRequestsWithInitId(initId);
		List<Transfer> transfers = new ArrayList<>();
		for (Transfer t : payments) {
			transfers.add(t);
		}
		for (Transfer t : requests) {
			transfers.add(t);
		}
		return transfers;
	}

	@Override
	public List<Transfer> getAllTransfersWithPendingStatus(boolean isPending) throws BusinessException {
		List<Payment> payments = getAllPaymentsWithPendingStatus(isPending);
		List<Request> requests = getAllRequestsWithPendingStatus(isPending);
		List<Transfer> transfers = new ArrayList<>();
		for (Transfer t : payments) {
			transfers.add(t);
		}
		for (Transfer t : requests) {
			transfers.add(t);
		}
		return transfers;
	}

	@Override
	public Payment updatePaymentPendingStatus(boolean isPending, String paymentId) throws BusinessException {
		updatePaymentPendingStatus_returnNothing(isPending, paymentId);
		return getPaymentById(paymentId);
	}
	
	public void updatePaymentPendingStatus_returnNothing(boolean isPending, String paymentId) throws BusinessException {
		try(Connection connection = OracleDbConnection.getConnection()){
			String sqlCall = "UPDATE bankofben_payments SET \"Pending\"=? WHERE \"Payment ID\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setBoolean(1, isPending);
			ps.setString(2, paymentId);
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated<1) {
				throw new BusinessException("Internal database error. Could not update pending status for "
						+paymentId+". Contact your SYSADMIN");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
	}

	@Override
	public Request updateRequestPendingStatus(boolean isPending, String requestId) throws BusinessException {
		updateRequestPendingStatus_returnNothing(isPending, requestId);
		return getRequestById(requestId);
	}
	
	public void updateRequestPendingStatus_returnNothing(boolean isPending, String requestId) throws BusinessException {
		try(Connection connection = OracleDbConnection.getConnection()){
			String sqlCall = "UPDATE bankofben_requests SET \"Pending\"=? WHERE \"Request ID\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setBoolean(1, isPending);
			ps.setString(2, requestId);
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated<1) {
				throw new BusinessException("Internal database error. Could not update pending status for "
						+requestId+". Contact your SYSADMIN");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
	}

	@Override
	public Transfer updateTransferPendingStatus(boolean isPending, String transferId) throws BusinessException {
		Transfer t = null;
		if (paymentExists(transferId)) {
			t = updatePaymentPendingStatus(isPending, transferId);
		} else if (requestExists(transferId)) {
			t = updateRequestPendingStatus(isPending, transferId);
		} else {
			throw new BusinessException("No payment or request exists with ID "+transferId);
		}
		return t;
	}
	
	public void updateTransferPendingStatus_returnNothing(boolean isPending, String transferId) throws BusinessException {
		if (paymentExists(transferId)) {
			updatePaymentPendingStatus_returnNothing(isPending, transferId);
		} else if (requestExists(transferId)) {
			updateRequestPendingStatus_returnNothing(isPending, transferId);
		} else {
			throw new BusinessException("No payment or request exists with ID "+transferId);
		}
	}

	public List<Payment> getAllPaymentsInvolvingCustomer(Customer customer) throws BusinessException {
		List<Account> customerAccounts = getAccountsForCustomerId(customer.getId());
		List<Payment> customerPayments = new ArrayList<>();
		for (Account ca : customerAccounts) {
			List<Payment> accountPayments = getAllPaymentsInvolvingAccount(ca);
			for (Payment p : accountPayments) {
				customerPayments.add(p);
			}
		}
		return customerPayments;
	}

	public List<Payment> getAllPaymentsInvolvingAccount(Account ca) throws BusinessException {
		List<Payment> paymentsToAccount = getAllPaymentsWithPayingAccountNumber(ca.getAccountNumber());
		List<Payment> paymentsFromAccount = getAllPaymentsWithReceivingAccountNumber(ca.getAccountNumber());
		return Stream.concat(paymentsToAccount.stream(), paymentsFromAccount.stream()).collect(Collectors.toList());
	}

	public List<Payment> getAllPaymentsInvolvingCustomerWithPendingStatus(Customer customer, boolean isPending) throws BusinessException {
		List<Account> customerAccounts = getAccountsForCustomerId(customer.getId());
		List<Payment> customerPayments = new ArrayList<>();
		for (Account ca : customerAccounts) {
			List<Payment> accountPayments = getAllPaymentsInvolvingAccount(ca);
			for (Payment p : accountPayments) {
				if (p.isPending()==isPending) {
					customerPayments.add(p);
				}
			}
		}
		return customerPayments;
	}

	public List<Request> getAllRequestsInvolvingCustomer(Customer customer) throws BusinessException {
		List<Account> customerAccounts = getAccountsForCustomerId(customer.getId());
		List<Request> customerRequests = new ArrayList<>();
		for (Account ca : customerAccounts) {
			List<Request> accountPayments = getAllRequestsInvolvingAccount(ca);
			for (Request r : accountPayments) {
				customerRequests.add(r);
			}
		}
		return customerRequests;
	}

	public List<Request> getAllRequestsInvolvingAccount(Account ca) throws BusinessException {
		List<Request> requestsToAccount = getAllRequestsWithRequestorAccountNumber(ca.getAccountNumber());
		List<Request> paymentsFromAccount = getAllRequestsWithSoughtAccountNumber(ca.getAccountNumber());
		return Stream.concat(requestsToAccount.stream(), paymentsFromAccount.stream()).collect(Collectors.toList());
	}

	public List<Request> getAllRequestsInvolvingCustomerWithPendingStatus(Customer customer, boolean isPending) throws BusinessException {
		List<Account> customerAccounts = getAccountsForCustomerId(customer.getId());
		List<Request> customerRequests = new ArrayList<>();
		for (Account ca : customerAccounts) {
			List<Request> accountRequests = getAllRequestsInvolvingAccount(ca);
			for (Request p : accountRequests) {
				if (p.isPending()==isPending) {
					customerRequests.add(p);
				}
			}
		}
		return customerRequests;
	}

	public List<Transfer> getTransfersInvolvingCustomer(Customer customer) throws BusinessException {
		List<Payment> customerTransferPayments = getAllPaymentsInvolvingCustomer(customer);
		List<Request> customerTransferRequests = getAllRequestsInvolvingCustomer(customer);
		List<Transfer> customerTransfers = new ArrayList<>();
		for (Payment p : customerTransferPayments) {
			customerTransfers.add((Transfer) p);
		}
		for(Request r :customerTransferRequests) {
			customerTransfers.add((Transfer) r);
		}
		return customerTransfers;
	}

	public List<Transfer> getTransfersInvolvingCustomerWithPendingStatus(Customer customer, boolean isPending) throws BusinessException {
		List<Payment> customerTransferPayments = getAllPaymentsInvolvingCustomer(customer);
		List<Request> customerTransferRequests = getAllRequestsInvolvingCustomer(customer);
		List<Transfer> customerTransfers = new ArrayList<>();
		for (Payment p : customerTransferPayments) {
			if (p.isPending()==isPending) {
				customerTransfers.add((Transfer) p);
			}
		}
		for(Request r :customerTransferRequests) {
			if (r.isPending()==isPending) {
				customerTransfers.add((Transfer) r);
			}
		}
		return customerTransfers;
	}

	@Override
	public void deletePayment(String paymentId) throws BusinessException {
		try(Connection connection = OracleDbConnection.getConnection()){
			String sqlCall = "DELETE FROM bankofben_payments WHERE \"Payment ID\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, paymentId);
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated<1) {
				throw new BusinessException("Internal database error. Could not delete payment with Payment ID "
						+paymentId+". Contact your SYSADMIN");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
	}

	@Override
	public void deleteRequest(String requestId) throws BusinessException {
		try(Connection connection = OracleDbConnection.getConnection()){
			String sqlCall = "DELETE FROM bankofben_requests WHERE \"Request ID\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setString(1, requestId);
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated<1) {
				throw new BusinessException("Internal database error. Could not delete request with Request ID "
						+requestId+". Contact your SYSADMIN");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
	}

	@Override
	public void deleteTransfer(String transferId) throws BusinessException {
		if (paymentExists(transferId)) {
			deletePayment(transferId);
		} else if (requestExists(transferId)) {
			deleteRequest(transferId);
		} else {
			throw new BusinessException("No payment or request exists with ID "+transferId);
		}
	}

	@Override
	public Transaction createTransaction(Transaction transaction) throws BusinessException {
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "{call createtransaction(?,?,?,?,?)}";
			CallableStatement cs = connection.prepareCall(sqlCall);
			
			cs.registerOutParameter(1, java.sql.Types.VARCHAR);
			cs.registerOutParameter(2, java.sql.Types.TIMESTAMP);
			
			//Tell SQL Procedure there should be the following parameters
			cs.setLong(3, transaction.getAccountNumber());
			cs.setDouble(4, transaction.getInitialBalance());
			cs.setDouble(5, transaction.getAmount());
			
			boolean transactionCreated = cs.execute();
			if (transactionCreated) {
				transaction = new Transaction(cs.getString("Transaction ID"), cs.getTimestamp("Timestamp"),
						cs.getLong("Account Number"), cs.getDouble("Initial Balance"), cs.getDouble("Amount"),
						cs.getDouble("Final Balance"));
			} else {
				throw new BusinessException("Internal database error. Transaction could not be created. "
						+ "Please contact your SYSADMIN");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return transaction;
	}

	@Override
	public List<Transaction> getAllTransactions() throws BusinessException {
		
		List<Transaction> transactions = new ArrayList<>();
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_transactions";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			
			ResultSet rset = ps.executeQuery();

			while (rset.next()) {
				transactions.add(new Transaction(rset.getString("Transaction ID"), rset.getTimestamp("Timestamp"),
						rset.getLong("Account Number"), rset.getDouble("Initial Balance"), rset.getDouble("Amount"),
						rset.getDouble("Final Balance")));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return transactions;
	}

	@Override
	public List<Transaction> getAllTransactionsWithAccountNumber(long accountNumber) throws BusinessException {
		
		List<Transaction> transactions = new ArrayList<>();
		
		try(Connection connection = OracleDbConnection.getConnection()){
			
			String sqlCall = "SELECT * FROM bankofben_transactions WHERE \"Account Number\"=?";
			PreparedStatement ps = connection.prepareStatement(sqlCall);
			ps.setLong(1, accountNumber);
			
			ResultSet rset = ps.executeQuery();

			while (rset.next()) {
				transactions.add(new Transaction(rset.getString("Transaction ID"), rset.getTimestamp("Timestamp"),
						rset.getLong("Account Number"), rset.getDouble("Initial Balance"), rset.getDouble("Amount"),
						rset.getDouble("Final Balance")));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return transactions;
	}

	@Override
	public List<Transaction> getAllTransactionsAfterDate(LocalDate date) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAllTransactionsAfterDate(Date date) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
