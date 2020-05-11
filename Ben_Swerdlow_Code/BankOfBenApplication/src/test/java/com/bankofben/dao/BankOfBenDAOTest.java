package com.bankofben.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
//import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.models.Employee;
import com.bankofben.models.Payment;
import com.bankofben.models.Request;
import com.bankofben.models.User;

public class BankOfBenDAOTest {
	
	private static BankOfBenDAO dao = new BankOfBenDAO();

	@BeforeClass
	public static void beforeClass() {
		System.out.println("Getting ready for all the DAO tests\n");
	}
	
	// Will be executed after each test
	@Before
	public void before() {
		System.out.println("\nAttempting the next DAO test");
	}
	
	@Test
	public void testCustomerCreationDeletion() throws ParseException, ClassNotFoundException, SQLException, BusinessException {
		
		String firstName = "Test";
		String middleName = "Tessa";
		String lastName = "Testerson";
		String momsMaidenName = "Testitzky";
		long ssn = 321758889L;
		String email = "test@tester.com";
		long phoneNumber = 3059994444L;
		String username = "mctesterson";
		String password = "hah4h&HA";
		String birthYear="1990";
		boolean applicationPending = true;
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date dob = format.parse("04/09/"+birthYear);
		
		int seqNextValue;
		try(Connection connection = OracleDbConnection.getConnection()){
			String getSeqCall = "SELECT CUSTOMER_SEQ.nextval FROM dual";
			PreparedStatement ps = connection.prepareStatement(getSeqCall);
			ResultSet rset = ps.executeQuery();
			if (rset.next()) {
				seqNextValue = rset.getInt(1)+1;
			} else {
				throw new BusinessException("Could not retrieve customer sequence value from SQL.");
			}
		}
		
		String id = "CU"+lastName.substring(0,2).toUpperCase()+username.substring(0,2).toUpperCase()+birthYear+seqNextValue;
		
		if(dao.customerIdExists(id)) {
			throw new BusinessException("Test failed. User with id "+id+" already exists");
		} else {
			User initialUser = new User(firstName, middleName, lastName, momsMaidenName, dob, ssn, email, phoneNumber, username, password);
			Customer actualCustomer = dao.createCustomer(initialUser);
			assertTrue(dao.customerIdExists(actualCustomer.getId()));
			assertArrayEquals(new String[]{actualCustomer.getFirstName()}, new String[] {firstName});
			assertArrayEquals(new String[]{actualCustomer.getMiddleName()}, new String[] {middleName});
			assertArrayEquals(new String[]{actualCustomer.getLastName()}, new String[] {lastName});
			assertArrayEquals(new String[]{actualCustomer.getMomsMaidenName()}, new String[] {momsMaidenName});
			assertArrayEquals(new Date[]{actualCustomer.getDob()}, new Date[] {dob});
			assertArrayEquals(new long[]{actualCustomer.getSsn()}, new long[] {ssn});
			assertArrayEquals(new String[]{actualCustomer.getEmail()}, new String[] {email});
			assertArrayEquals(new long[] {actualCustomer.getPhoneNumber()}, new long[] {phoneNumber});
			assertArrayEquals(new String[] {actualCustomer.getUsername()}, new String[] {username});
			assertArrayEquals(new String[] {actualCustomer.getPassword()}, new String[] {password});
			assertArrayEquals(new String[] {actualCustomer.getId()}, new String[] {id});
			assertArrayEquals(new boolean[] {actualCustomer.isApplicationPending()}, new boolean[] {applicationPending});
			dao.deleteCustomer(actualCustomer.getId());
			assertFalse(dao.customerIdExists(id));
		}
	}
	
	@Test
	public void testAccountCreationDeletion() throws BusinessException, ClassNotFoundException, SQLException {
		double balance = 9743.25;
		
		String customerId = "CUCAAA2000100000";
		
		Account account = new Account(1234567890, balance, customerId);
		
		account = dao.createAccount(account);
		
		assertTrue(dao.accountExists(account.getAccountNumber()));
		assertTrue(Long.toString(account.getAccountNumber()).matches("^[0-9]{10}$"));
		assertArrayEquals(new double[] {balance}, new double[] {account.getBalance()}, 0.01);
		assertArrayEquals(new String[] {customerId}, new String[] {account.getCustomerId()});
		
		dao.deleteAccount(account.getAccountNumber());
		
		assertFalse(dao.accountExists(account.getAccountNumber()));
	}
	
	@Test
	public void testPaymentCreationDeletion() throws BusinessException, ClassNotFoundException, SQLException {
		
		String id;
		double amount = 2370349.35;
		String initUserId = "CUCAAA2000100000";
		boolean pending = true;
		long payingAccountNumber = 9180651690L;
		long receivingAccountNumber = 3969255661L;
		
		int seqNextValue;
		try(Connection connection = OracleDbConnection.getConnection()){
			String getSeqCall = "SELECT PAYMENT_SEQ.nextval FROM dual";
			PreparedStatement ps = connection.prepareStatement(getSeqCall);
			ResultSet rset = ps.executeQuery();
			if (rset.next()) {
				seqNextValue = rset.getInt(1)+1;
			} else {
				throw new BusinessException("Could not retrieve payment sequence value from SQL.");
			}
		}
		
		id = "PA"+initUserId.substring(0,2).toUpperCase()+Long.toString(payingAccountNumber).substring(0,2)+Long.toString(receivingAccountNumber).substring(0,2)+seqNextValue;
		
		Payment p = new Payment(id, initUserId, pending, payingAccountNumber, receivingAccountNumber, amount);
		
		p = dao.createPayment(p);
		
		assertTrue(dao.paymentIdExists(id));
		assertArrayEquals(new String[] {id}, new String[] {p.getId()});
		assertArrayEquals(new String[] {initUserId}, new String[] {p.getInitUserId()});
		assertTrue(pending);
		assertArrayEquals(new long[] {payingAccountNumber}, new long[] {p.getPayingAccountNumber()});
		assertArrayEquals(new long[] {receivingAccountNumber}, new long[] {p.getReceivingAccountNumber()});
		
		dao.deletePayment(id);
		
		assertFalse(dao.paymentIdExists(id));
	}
	
	@Test
	public void testRequestCreationDeletion() throws BusinessException, ClassNotFoundException, SQLException {
		
		String id;
		double amount = 2370349.35;
		String initUserId = "CUCAAA2000100000";
		boolean pending = true;
		long requestorAccountNumber = 9180651690L;
		long soughtAccountNumber = 3969255661L;
		
		int seqNextValue;
		try(Connection connection = OracleDbConnection.getConnection()){
			String getSeqCall = "SELECT REQUEST_SEQ.nextval FROM dual";
			PreparedStatement ps = connection.prepareStatement(getSeqCall);
			ResultSet rset = ps.executeQuery();
			if (rset.next()) {
				seqNextValue = rset.getInt(1)+1;
			} else {
				throw new BusinessException("Could not retrieve request sequence value from SQL.");
			}
		}
		
		id = "RE"+initUserId.substring(0,2).toUpperCase()+Long.toString(requestorAccountNumber).substring(0,2)+Long.toString(soughtAccountNumber).substring(0,2)+seqNextValue;
		
		Request r = new Request(id, initUserId, pending, requestorAccountNumber, soughtAccountNumber, amount);
		
		r = dao.createRequest(r);
		
		assertTrue(dao.requestExists(id));
		assertArrayEquals(new String[] {id}, new String[] {r.getId()});
		assertArrayEquals(new String[] {initUserId}, new String[] {r.getInitUserId()});
		assertTrue(pending);
		assertArrayEquals(new long[] {requestorAccountNumber}, new long[] {r.getRequestorAccountNumber()});
		assertArrayEquals(new long[] {soughtAccountNumber}, new long[] {r.getSoughtAccountNumber()});
		
		dao.deleteRequest(id);
		
		assertFalse(dao.requestExists(id));
	}
	
	@Test
	public void testEmployeeCreationDeletion() throws BusinessException, ParseException, ClassNotFoundException, SQLException {
		
		String firstName = "Test";
		String middleName = "Tessa";
		String lastName = "Testerson";
		String momsMaidenName = "Testitzky";
		long ssn = 321758889L;
		String email = "test@tester.com";
		long phoneNumber = 3059994444L;
		String username = "mctesterson";
		String password = "hah4h&HA";
		String designation = "Test Employee";
//		System.out.println("Getting Supervisor");
		Employee supervisor = new Employee("Sup", "Er", "Visor", "Man", 
				new GregorianCalendar(1964, Calendar.MARCH, 15).getTime(), 123456789L, "test@gmail.com", 
				1987654321L, "test", "P4ssw0rd!", "test", "test", "test", true);;
//		System.out.println("Got Supervisor "+supervisor.getId());
		String birthYear = "1990";
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date dob = format.parse("04/09/"+birthYear);
		
		int seqNextValue;
		try(Connection connection = OracleDbConnection.getConnection()){
			String getSeqCall = "SELECT EMPLOYEE_SEQ.nextval FROM dual";
			PreparedStatement ps = connection.prepareStatement(getSeqCall);
			ResultSet rset = ps.executeQuery();
			if (rset.next()) {
				seqNextValue = rset.getInt(1)+1;
			} else {
				throw new BusinessException("Could not retrieve employee sequence value from SQL.");
			}
		}
		
		String id = "EM"+lastName.substring(0,2).toUpperCase()+username.substring(0,2).toUpperCase()+birthYear+seqNextValue;
		
		if(dao.customerIdExists(id)) {
			throw new BusinessException("Test failed. User with id "+id+" already exists");
		} else {
			User initialUser = new User(firstName, middleName, lastName, momsMaidenName, dob, ssn, email, phoneNumber, username, password);
			Employee actualEmployee = dao.createEmployee(initialUser, designation, supervisor);
			assertTrue(dao.employeeIdExists(id));
			assertArrayEquals(new String[] {firstName}, new String[]{actualEmployee.getFirstName()});
			assertArrayEquals(new String[] {middleName}, new String[]{actualEmployee.getMiddleName()});
			assertArrayEquals(new String[] {lastName}, new String[]{actualEmployee.getLastName()});
			assertArrayEquals(new String[] {momsMaidenName}, new String[]{actualEmployee.getMomsMaidenName()});
			assertArrayEquals(new Date[] {dob}, new Date[]{actualEmployee.getDob()});
			assertArrayEquals(new long[] {ssn}, new long[]{actualEmployee.getSsn()});
			assertArrayEquals(new String[] {email}, new String[]{actualEmployee.getEmail()});
			assertArrayEquals(new long[] {phoneNumber}, new long[] {actualEmployee.getPhoneNumber()});
			assertArrayEquals(new String[] {username}, new String[] {actualEmployee.getUsername()});
			assertArrayEquals(new String[] {password}, new String[] {actualEmployee.getPassword()});
			assertArrayEquals(new String[] {designation}, new String[] {actualEmployee.getDesignation()});
			assertArrayEquals(new String[] {id}, new String[] {actualEmployee.getId()});
			assertArrayEquals(new String[] {supervisor.getId()}, new String[] {actualEmployee.getSupervisorEmployeeId()});
			dao.deleteEmployee(actualEmployee.getId());
			assertFalse(dao.employeeIdExists(id));
		}
	}
	
	@Test
	public void testGetCustomerById() throws BusinessException {
		Customer customer = dao.getCustomerById("CUCAAA2000100000");
		assertArrayEquals(new String[] {"Aaron"}, new String[]{customer.getFirstName()});
		assertArrayEquals(new String[] {"Babbish"}, new String[]{customer.getMiddleName()});
		assertArrayEquals(new String[] {"Calhoun"}, new String[]{customer.getLastName()});
		assertArrayEquals(new String[] {"Davidson"}, new String[]{customer.getMomsMaidenName()});
		assertArrayEquals(new Date[] {new GregorianCalendar(2000, 0, 1).getTime()}, new Date[]{customer.getDob()});
		assertArrayEquals(new long[] {123456789L}, new long[]{customer.getSsn()});
		assertArrayEquals(new String[] {"aabaca@gmail.com"}, new String[]{customer.getEmail()});
		assertArrayEquals(new long[] {3216540987L}, new long[] {customer.getPhoneNumber()});
		assertArrayEquals(new String[] {"aabaca"}, new String[] {customer.getUsername()});
		assertArrayEquals(new String[] {"P4ssw0rd!"}, new String[] {customer.getPassword()});
		assertArrayEquals(new boolean[] {false}, new boolean[] {customer.isApplicationPending()});
		assertArrayEquals(new String[] {"CUCAAA2000100000"}, new String[] {customer.getId()});
	}
	
	@Test
	public void testGetEmployeeById() throws BusinessException {
		Employee employee = dao.getEmployeeById("EMSCMI1964100002");
		assertArrayEquals(new String[] {"Michael"}, new String[]{employee.getFirstName()});
		assertArrayEquals(new String[] {"Gary"}, new String[]{employee.getMiddleName()});
		assertArrayEquals(new String[] {"Scott"}, new String[]{employee.getLastName()});
		assertArrayEquals(new String[] {"Kevis"}, new String[]{employee.getMomsMaidenName()});
		assertArrayEquals(new Date[] {new GregorianCalendar(1964, 2, 15).getTime()}, new Date[]{employee.getDob()});
		assertArrayEquals(new long[] {987654321L}, new long[]{employee.getSsn()});
		assertArrayEquals(new String[] {"michael.scott@dundermifflin.com"}, new String[]{employee.getEmail()});
		assertArrayEquals(new long[] {9287437243L}, new long[] {employee.getPhoneNumber()});
		assertArrayEquals(new String[] {"michaelscarn"}, new String[] {employee.getUsername()});
		assertArrayEquals(new String[] {"Ih34rtH0lly!"}, new String[] {employee.getPassword()});
		assertArrayEquals(new String[] {"Branch Manager"}, new String[] {employee.getDesignation()});
		assertArrayEquals(new String[] {"EMSCMI1964100002"}, new String[] {employee.getId()});
		assertArrayEquals(new String[] {"EMSWBE1992100000"}, new String[] {employee.getSupervisorEmployeeId()});
	}
	
	@Test
	public void testUpdateCustomerApplicationPending() throws BusinessException {
		String customerId = "CUCAAA2000100000";
		Customer customer = dao.getCustomerById("CUCAAA2000100000");
		boolean originalAppPending = customer.isApplicationPending();
		boolean changedAppPending = !originalAppPending;
		Customer updatedCustomer = dao.updateCustomerApplicationPending(changedAppPending, customerId);
		assertTrue(updatedCustomer.isApplicationPending()==changedAppPending);
		Customer revertedCustomer = dao.updateCustomerApplicationPending(originalAppPending, customerId);
		assertTrue(revertedCustomer.isApplicationPending()==customer.isApplicationPending());
	}
	
	// Will be executed after each test
	@After
	public void testEquals() {
		System.out.println("Completed test");
	}
	
	// Will be executed after testing begins
	@AfterClass
	public static void after() {
		System.out.println("Completed all Application tests!");
	}

}
