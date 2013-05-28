import java.sql.*;

class Blocker {
    private static Blocker singleton = null;

    public static Blocker getInstance() {
        if (singleton == null) {
            singleton = new Blocker();
        }
        return singleton;
    }
    public bool find(blocker, blockee) throws SQLException {
        String selectQuery = "SELECT\n"
                           + "    blocker\n"
                           + "FROM\n"
                           + "    block\n"
                           + "WHERE\n"
                           + "    blocker = ?\n"
                           + "    AND blockee = ?\n";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, blocker);
        preparedStatement.setInt(2, blockee);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }
    public void block(blocker, blockee) throws SQLException {
        try {
            String insertQuery = "INSERT INTO\n"
                               + "    block\n"
                               + "(blocker, blockee)\n"
                               + "VALUES\n"
                               + "(?, ?)\n";
            PreparedStatement = dbConnection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, blocker);
            preparedStatement.setInt(2, blockee);
            preparedStatement.executeUpdate();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    public void unblock(blocker, blockee) throws SQLException {
        try {
            String deleteQuery = "DELETE FROM\n"
                               + "    block\n"
                               + "WHERE\n"
                               + "    blocker = ?\n"
                               + "    AND blockee = ?\n";
            PreparedStatement = dbConnection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, blocker);
            preparedStatement.setInt(2, blockee);
            preparedStatement.executeUpdate();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
