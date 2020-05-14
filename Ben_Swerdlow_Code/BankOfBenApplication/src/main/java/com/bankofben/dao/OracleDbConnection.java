package com.bankofben.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.bankofben.exceptions.BusinessException;

public class OracleDbConnection {
	
//	final static Logger loggy = Logger.getLogger(OracleDbConnection.class);
	
	private static Connection connection = null;
	
	private OracleDbConnection() {}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException, BusinessException {
		Class.forName("oracle.jdbc.OracleDriver");
		
		String projectPath = new File("").getAbsolutePath();
		
		if (connection==null) {
			
			List<String> login = new ArrayList<>();
			
			try(BufferedReader reader = new BufferedReader(new FileReader(projectPath+"\\..\\..\\..\\BankOfBenDbLogin.txt"))){
				String line = "";
				while ((line = reader.readLine()) != null) {
					login.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e);
				throw new BusinessException("Could not read login information. Please check your login information and try again.");
			}
			connection = DriverManager.getConnection(login.get(0), login.get(1), login.get(2));
			
		} else if (!connection.isValid(0)) {
			
			List<String> login = new ArrayList<>();
			
			try(BufferedReader reader = new BufferedReader(new FileReader(projectPath+"\\..\\..\\..\\BankOfBenDbLogin.txt"))){
				String line = "";
				while ((line = reader.readLine()) != null) {
					login.add(line);
				}
			} catch (IOException e) {
				throw new BusinessException("Could not read login information. Please check your login information and try again.");
			}
			connection = DriverManager.getConnection(login.get(0), login.get(1), login.get(2));
			
		}
		return connection;
	}

}
