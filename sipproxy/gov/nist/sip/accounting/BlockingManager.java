package gov.nist.sip.accounting;

import java.sql.*;

public class BlockingManager {
    private static BlockingManager singleton = null;

    public static BlockingManager getInstance() {
        if (singleton == null) {
            singleton = new BlockingManager();
        }
        return singleton;
    }
    public boolean isBlocked(int blocker, int blockee) {
        String selectQuery = "SELECT\n"
                           + "    blocker\n"
                           + "FROM\n"
                           + "    block\n"
                           + "WHERE\n"
                           + "    blocker = ?\n"
                           + "    AND blockee = ?\n";
        try {
        	PreparedStatement preparedStatement = Database.getConnection().prepareStatement(selectQuery);
        	preparedStatement.setInt(1, blocker);
	        preparedStatement.setInt(2, blockee);
	        ResultSet rs = preparedStatement.executeQuery();
	      
	        return rs.next();
        }
        catch (SQLException e) {
        	e.printStackTrace();
        	return false;
        }
    }
    public boolean block(int blocker, int blockee){
        try {
            String insertQuery = "INSERT INTO\n"
                               + "    block\n"
                               + "(blocker, blockee)\n"
                               + "VALUES\n"
                               + "(?, ?)\n";
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(insertQuery);
            preparedStatement.setInt(1, blocker);
            preparedStatement.setInt(2, blockee);
            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }
    public boolean unblock(int blocker, int blockee) {
        try {
            String deleteQuery = "DELETE FROM\n"
                               + "    block\n"
                               + "WHERE\n"
                               + "    blocker = ?\n"
                               + "    AND blockee = ?\n";
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(deleteQuery);
            preparedStatement.setInt(1, blocker);
            preparedStatement.setInt(2, blockee);
            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }
}