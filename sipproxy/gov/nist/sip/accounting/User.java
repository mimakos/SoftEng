import java.sql.*;
import java.lang.UnsupportedOperationException;
import gov.nist.sip.accounting.BlockManager;

class UserNotFoundException extends Exception {
    public UserNotFound(String message) {
        super(message);
    }
}

class UserBlockedException extends Exception {
    public UserBlocked(String message) {
        super(message);
    }
}

class User {
    private int id;

    User(int id) {
        this.id = id;
    }
    static public User fromURI(String uri) throws UserNotFound {
        String selectQuery = "SELECT\n"
                           + "    uid\n"
                           + "FROM\n"
                           + "    user\n"
                           + "WHERE\n"
                           + "    name = ?\n"
        try {
        	PreparedStatement preparedStatement = Database.getConnection().prepareStatement(selectQuery);
        	preparedStatement.setInt(1, uri);
	        ResultSet rs = preparedStatement.executeQuery();
	      
	        row = rs.next();
            if (row == false) {
                throw UserNotFoundException();
            }
            userid = rs.getInt("uid");
            return new User(userid);
        }
        catch (SQLException e) {
        	e.printStackTrace();
        	return null;
        }
    }
    public boolean hasBlocked(User target) {
        return BlockManager.getInstance().isBlocked(this.id, target.id);
    }
    public void invite(User target) {
        if (this.hasBlocked(target)) {
            throw new UserBlocked();
        }
    }
    public void cancel(User target) {
    }
    public void ack(User target) {
        BillingManager.getInstance().beginCall();
    }
    public void bye(User target) {
    }
}
