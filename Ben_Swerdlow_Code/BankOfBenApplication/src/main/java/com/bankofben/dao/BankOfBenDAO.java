package com.bankofben.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.models.Employee;
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
				throw new BusinessException("Customer ID "+customerId+" doesn't exist.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal database error. Please contact your SYSADMIN.");
		}
		
		return customer;
	}

	@Override
	public Customer getCustomerByUsername(String username) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomerByEmail(String email) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
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
//			ResultSetMetaData rsetmd = rset.getMetaData();
//			
//			for (int i=1; i<=rsetmd.getColumnCount(); i++) {
//				if (i > 1) System.out.print("\t");
//				System.out.print(rsetmd.getColumnName(i));
//			}
//			
//			while (rset.next()) {
//				for (int i=1; i<=rsetmd.getColumnCount(); i++) {
//					if (i > 1) System.out.println("\t");
//					System.out.print(rset.getString(i));
//				}
//			}
			
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

}
