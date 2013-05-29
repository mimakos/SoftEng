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
    static public fromURI(String uri) throws UserNotFound {
        return new User(...);
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
    }
    public void bye(User target) {
    }
}
