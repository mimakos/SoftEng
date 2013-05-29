package gov.nist.sip.accounting;

import java.sql.*;

public class ForwardingManager {
	private static ForwardingManager singleton = null;
	
	public static ForwardingManager getInstance() {
		if (singleton == null) {
			singleton = new ForwardingManager();
		}
		return singleton;
	}
	
	public boolean isForwarded(int forwarder, int forwardee) {
        String selectQuery = "SELECT\n"
                           + "    forwarder\n"
                           + "FROM\n"
                           + "    forward\n"
                           + "WHERE\n"
                           + "    forwarder = ?\n"
                           + "    AND forwardee = ?\n";
        try {
        	PreparedStatement preparedStatement = Database.getConnection().prepareStatement(selectQuery);
        	preparedStatement.setInt(1, forwarder);
	        preparedStatement.setInt(2, forwardee);
	        ResultSet rs = preparedStatement.executeQuery();
	        return rs.next();
        }
        catch (SQLException e) {
        	e.printStackTrace();
        	return false;
        }
	}
	
	public boolean forward(int forwarder, int forwardee){
		try {
			String insertQuery = "INSERT INTO\n"
			                   + "	   forward\n"
			                   + "(forwarder, forwardee)\n"
			                   + "VALUES\n"
			                   + "(?, ?)\n";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(insertQuery);
			pstmt.setInt(1,  forwarder);
			pstmt.setInt(2,  forwardee);
			pstmt.executeUpdate();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean unforward(int forwarder, int forwardee) {
		try {
			String deleteQuery = "DELETE FROM\n"
		                       + "    forward\n"
		                       + "WHERE\n"
		                       + "    forwarder = ?\n"
		                       + "    AND forwardee = ?\n";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(deleteQuery);
			pstmt.setInt(1, forwarder);
			pstmt.setInt(2, forwardee);
			pstmt.executeUpdate();
			return true;
		}
		catch (SQLException e) {
			return false;
			
		}
    }
}