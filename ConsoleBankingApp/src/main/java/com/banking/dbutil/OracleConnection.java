package com.banking.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection {

    private static Connection connection = null;

    private OracleConnection() {


    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        String url = "jdbc:oracle:thin:@myoracledb-1.czojqj6xa7dh.us-west-1.rds.amazonaws.com:1521:ORCL";
        String username = "Scottslatton";
        String password = "masterpass";
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
