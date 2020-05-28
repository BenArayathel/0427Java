package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.EmployeeController;
import com.exception.BankException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebEmployeeController {

	public static void returnAllAccounts(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {	
			
			EmployeeController ec = new EmployeeController();
			ArrayList<String[]> accountList = ec.returnAllAccounts();
			
			StringBuilder builder = new StringBuilder();
			
//			for(String[] strArr : accountList) {
			for (int i = 0; i < accountList.size(); i++) {
				System.out.print("{");
				for(int j = 0; j < accountList.get(0).length; j++) {
					if(j == accountList.get(0).length - 1) {
						System.out.print("\"" + accountList.get(i)[j] + "\"");
					}else {
						System.out.print("\"" + accountList.get(i)[j] + "\", ");
					}
				}
				if(i == accountList.get(0).length) {
					System.out.print("}\n");
				}else {
					System.out.print("},\n");
				}
			}
			
			PrintWriter writer = response.getWriter();
			writer.write(new ObjectMapper().writeValueAsString(accountList));
				
	}
}
