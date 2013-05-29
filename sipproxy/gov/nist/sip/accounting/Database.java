package gov.nist.sip.accounting;

import java.sql.*;
import java.util.Properties;

public class Database {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3307/mysql";
    private static String user = "root";
	private static String password = "1234";
	private static Connection conn = null;

    public static Connection getConnection() {
    	try {
	        if (conn == null) {
	            Class.forName(driver);
	            Properties connectionProps = new Properties();
	            connectionProps.put("user", user);
	            connectionProps.put("password", password);
	            conn = DriverManager.getConnection(url, connectionProps);
	        }
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
        return conn;
    }
}
