import java.sql.*;

class Database {
    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getconnection("jdbc:mysql://localhost:3307/mysql?user=root&password=1234");
        }
        return conn;
    }
}
