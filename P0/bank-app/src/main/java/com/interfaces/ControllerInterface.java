package com.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ControllerInterface {

	public void checkBalance(int inAccountNumber, ResultSet inRs2) throws SQLException;
}
