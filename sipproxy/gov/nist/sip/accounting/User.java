import java.sql.*;
import java.lang.UnsupportedOperationException;
import gov.nist.sip.accounting.BlockManager;

class UserNotFound extends Exception {
    public UserNotFound(String message) {
        super(message);
    }
}

class UserBlocked extends Exception {
    public UserBlocked(String message) {
        super(message);
    }
}

class User {
    private int id;

    static public fromURI(String uri) {
        return new User(...);
    }
    User(String name) throws UserNotFound, UnsupportedOperationException {
        throw UnsupportedOperationException;
        // TODO: this.id = ...;
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
