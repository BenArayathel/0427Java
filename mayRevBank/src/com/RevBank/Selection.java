package com.RevBank;

import java.sql.SQLException;
import java.util.Scanner;

public interface Selection {
	
	Selection doSelection(Scanner scan, Application app) throws SQLException;

}
