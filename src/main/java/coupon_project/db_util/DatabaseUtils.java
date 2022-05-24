package coupon_project.db_util;

import java.sql.*;
import java.util.Map;

public class DatabaseUtils {
    // Creating a private instance of connection
    private static Connection connection;

    /**
     * Function for run a command on mySQL
     *
     * @param query Command (mySQL language)
     * @throws InterruptedException when the thread is interrupted
     * @throws SQLException when SQL raises an exception while executing a statement.
     */
    public static void runQuery(String query) throws InterruptedException, SQLException {
        // First, we get a connection from the connection pool
        connection = ConnectionPool.getInstance().getConnection();
        // Next, we prepare the statement to run
        PreparedStatement statement = connection.prepareStatement(query);
        // Next, we execute the statement
        statement.execute();
        // And we don't forget to return the connection
        ConnectionPool.getInstance().restoreConnection(connection);
    }

    /**
     * Function for run a command on mySQL with parameters to replace "?"
     *
     * @param query  Command (mySQL language)
     * @param params Parameters to replace "?"
     * @throws InterruptedException when the thread is interrupted if thread is interrupted.
     */
    public static void runQuery(String query, Map<Integer, Object> params) throws InterruptedException {
        try {
            // First, we get a connection from the connection pool
            connection = ConnectionPool.getInstance().getConnection();
            // Next, we prepare the statement to run
            PreparedStatement statement = connection.prepareStatement(query);
            // Now, for each parameter we change the next "?" in the statement to the value
            params.forEach((key, value) -> {
                try {
                    // In case of Integer
                    if (value instanceof Integer) {
                        // Changing "?" to value
                        statement.setInt(key, (Integer) value);
                    }
                    // In case of String
                    else if (value instanceof String) {
                        // Changing "?" to value
                        statement.setString(key, String.valueOf(value));
                    }
                    // In case of Date
                    else if (value instanceof Date) {
                        // Changing "?" to value
                        statement.setDate(key, (Date) value);
                    }
                    // In case of Double
                    else if (value instanceof Double) {
                        // Changing "?" to value
                        statement.setDouble(key, (Double) value);
                    }
                    // In case of Boolean
                    else if (value instanceof Boolean) {
                        // Changing "?" to value
                        statement.setBoolean(key, (Boolean) value);
                    }
                    // In case of Float
                    else if (value instanceof Float) {
                        // Changing "?" to value
                        statement.setFloat(key, (Float) value);
                    }
                } catch (SQLException err) {
                    System.out.println(err.getMessage());
                }
            });
            // And we execute the statement
            statement.execute();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        } finally {
            // We don't forget to return the connection, no matter what happened before
            ConnectionPool.getInstance().restoreConnection(connection);
        }
    }

    /**
     * Function for run a command on mySQL and return data
     *
     * @param query Command (mySQL language)
     * @return Wanted data
     * @throws InterruptedException when the thread is interrupted
     */
    public static ResultSet runQueryForResult(String query) throws InterruptedException {
        // Initializing a ResultSet instance to return
        ResultSet resultSet = null;
        try {
            // First, we get a connection from the connection pool
            connection = ConnectionPool.getInstance().getConnection();
            // Next, we prepare the statement to run
            PreparedStatement statement = connection.prepareStatement(query);
            // Next, we execute the statement and changing the resultSet instance from before to the result of what we
            // asked for
            resultSet = statement.executeQuery();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        } finally {
            // We don't forget to return the connection, no matter what happened before
            ConnectionPool.getInstance().restoreConnection(connection);
        }
        // And returning the result
        return resultSet;
    }

    /**
     * Function for run a command on mySQL with parameters to replace "?" and return data
     *
     * @param query  Command (mySQL language)
     * @param params Parameters to replace "?"
     * @return Wanted data
     * @throws InterruptedException when the thread is interrupted
     */
    public static ResultSet runQueryForResult(String query, Map<Integer, Object> params) throws InterruptedException {
        ResultSet resultset = null;
        try {
            // First, we get a connection from the connection pool
            connection = ConnectionPool.getInstance().getConnection();
            // Next, we prepare the statement to run
            PreparedStatement statement = connection.prepareStatement(query);
            // Now, for each parameter we change the next "?" in the statement to the value
            params.forEach((key, value) -> {
                try {
                    // In case of Integer
                    if (value instanceof Integer) {
                        // Changing "?" to value
                        statement.setInt(key, (Integer) value);
                    }
                    // In case of String
                    else if (value instanceof String) {
                        // Changing "?" to value
                        statement.setString(key, String.valueOf(value));
                    }
                    // In case of Date
                    else if (value instanceof Date) {
                        // Changing "?" to value
                        statement.setDate(key, (Date) value);
                    }
                    // In case of Double
                    else if (value instanceof Double) {
                        // Changing "?" to value
                        statement.setDouble(key, (Double) value);
                    }
                    // In case of Boolean
                    else if (value instanceof Boolean) {
                        // Changing "?" to value
                        statement.setBoolean(key, (Boolean) value);
                    }
                    // In case of Float
                    else if (value instanceof Float) {
                        // Changing "?" to value
                        statement.setFloat(key, (Float) value);
                    }
                } catch (SQLException err) {
                    System.out.println(err.getMessage());
                }
            });
            // Next, we execute the statement and changing the resultSet instance from before to the result of what we
            // asked for
            resultset = statement.executeQuery();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        } finally {
            // We don't forget to return the connection, no matter what happened before
            ConnectionPool.getInstance().restoreConnection(connection);
        }
        // And returning the result
        return resultset;
    }
}
