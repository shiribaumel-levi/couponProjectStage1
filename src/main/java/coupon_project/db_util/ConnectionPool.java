package coupon_project.db_util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

/**
 * ConnectionPool class that manage the connections to the database
 */
public class ConnectionPool {

    // Getting the max number of connection
    private static final int NUM_OF_CONS = 10;
    // Initializing the instance for null at the beginning
    private static volatile ConnectionPool instance = null;
    // Initializing the connections stack at the beginning
    private final Stack<Connection> connections = new Stack<>();

    /**
     * Blank constructor that open the connections. Private constructor indicates singleton
     *
     * @throws SQLException if sql raises an exception.
     */
    private ConnectionPool() throws SQLException {
        //open all connections
        openAllConnections();
    }

    /**
     * Returning the instance of the loginManager. If the instance haven't been created yet, it creates it using double
     * check, so that only one instance will be created, and so that only at creation time the sync block will be accessed.
     *
     * @return ConnectionPool with the connections stack
     */
    public static ConnectionPool getInstance() {
        // First, we check if the instance needs to be created
        if (instance == null) {
            // Next, we lock the ConnectionPool class, so no one can access it and accidentally touch the instance while
            // we are creating it
            synchronized (ConnectionPool.class) {
                // Now, we check again if the instance is null and needs to be created, just to make sure that it
                // hasn't been created since we check above
                if (instance == null) {
                    try {
                        // And finally we create the instance
                        instance = new ConnectionPool();
                    } catch (SQLException throwable) {
                        throwable.printStackTrace();
                    }
                }
            }
        }
        // If the instance already created or the creation process finished, return the instance
        return instance;
    }

    /**
     * Function for getting one connection from the stack
     *
     * @return Connection (the top one)
     * @throws InterruptedException if a thread is interrupted while connectionpool is waiting for a notification.
     */
    public Connection getConnection() throws InterruptedException {
        // First, we lock the connections to prevent more than one stack-action at a time
        synchronized (connections) {
            // Next, we check if there is a connection to take
            if (connections.isEmpty()) {
                // If not, we wait until someone return a connection and wake us (=notify)
                connections.wait();
            }
            // And finally we take a connection and return it to the one who asked for it
            return connections.pop();
        }
    }

    /**
     * Function for restore the connection that was taken back to the stack
     *
     * @param connection The connection that was taken
     */
    public void restoreConnection(Connection connection) {
        // First, we lock the connections to prevent more than one stack-action at a time
        synchronized (connections) {
            // Next, we return the connection to the stack
            connections.push(connection);
            // And we wake up (=notify) the sleeping ones who wait for a connection to be returned
            connections.notify();
        }
    }

    /**
     * Function for opening the stack, creating and adding all the connections
     *
     * @throws SQLException when sql throws exception.
     */
    private void openAllConnections() throws SQLException {
        // Looping for the number of connections we want
        for (int index = 0; index < NUM_OF_CONS; index += 1) {
            // Creates a connection that connects us to our database (using our URL, username and password)
            Connection connection = DriverManager.getConnection(DataBaseManager.URL, DataBaseManager.USER_NAME, DataBaseManager.USER_PASS);
            // And loading the stack with that connection
            connections.push(connection);
        }
    }

    /**
     * Function for closing the connections, prevent the connections from getting out and waiting for all of them to be back
     *
     * @throws InterruptedException if thread is interrupted.
     */
    public void closeAllConnection() throws InterruptedException {
        // First, we lock the connections to prevent anyone from taking a connection
        synchronized (connections) {
            // Next, we check if all the connections have been returned
            while (connections.size() < NUM_OF_CONS) {
                // If not, we wait
                connections.wait();
            }
            // After all the connections have been returned, we close the stack and the ConnectionPool is no longer available
            connections.removeAllElements();
        }
    }
}