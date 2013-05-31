package gov.nist.sip.accounting;

import java.sql.*;
import java.util.*;

public class ForwardingManager {
	private static ForwardingManager singleton = null;
	
	public static ForwardingManager getInstance() {
		if (singleton == null) {
			singleton = new ForwardingManager();
		}
		return singleton;
	}
	
	public boolean isForwarding(int user) {
        String selectQuery = "SELECT\n"
                           + "    forwarder\n"
                           + "FROM\n"
                           + "    forward\n"
                           + "WHERE\n"
                           + "    forwarder = ?\n";

        try {
        	PreparedStatement pstmt = Database.getConnection().prepareStatement(selectQuery);
       	    pstmt.setInt(1, user);
	        ResultSet rs = pstmt.executeQuery();
	        return rs.next();
        }
        catch (SQLException sqlE) {
        	sqlE.printStackTrace();
        	return false;
        }
	}
	
	public int getForwardee(int user) {
		
		int forwardee = -1;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectQuery = " SELECT\n"
                           + "      forwardee\n"
                           + "FROM\n"
                           + "      forward\n"
                           + "WHERE\n"
                           + "forwarder = ?\n";
		
		try {
			pstmt = Database.getConnection().prepareStatement(selectQuery);
			pstmt.setInt(1, user);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				forwardee = rs.getInt(2);
			}
			return forwardee;
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			return (-1);
		}
	}
	
	public int getFinalForwardee(int caller, int callee) {
		
		int forwardee = 0; 
		int finalForwardee = 0;
		
		Set<Integer> s = new HashSet<Integer>();
		s.add(caller);
		s.add(callee);
		
		forwardee = getForwardee(callee);
		finalForwardee = forwardee;
		while ( forwardee != -1 ) {	
			finalForwardee = forwardee; 
			
			if (s.contains(forwardee)) {
				// loop detected in forwarding
				forwardee = -1;
				finalForwardee = forwardee;
			}
			else {
				s.add(forwardee);
				forwardee = getForwardee(forwardee);
			}	
		}
		return finalForwardee;	
	}
}