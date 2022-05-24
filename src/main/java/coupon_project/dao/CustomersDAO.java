package coupon_project.dao;

import coupon_project.beans.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomersDAO {
    /**
     * checks if specific customer exist in the system by email and password
     *
     * @param email-   customer email
     * @param password - customer password
     * @return if company exists
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    boolean isCustomerExists(String email, String password) throws SQLException, InterruptedException;

    /**
     * checks if specific customer exist by ID
     *
     * @param customerID- customer ID
     * @return if company exists
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    boolean isCustomerExistsByID(int customerID) throws SQLException, InterruptedException;

    /**
     * add new customer to the system
     *
     * @param customer data
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    void addCustomer(Customer customer) throws SQLException, InterruptedException;

    /**
     * a method update customer existing data
     *
     * @param customer data
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    void updateCustomer(Customer customer) throws SQLException, InterruptedException;

    /**
     * a method delete customer from the system by id
     *
     * @param customerID customer's id
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    void deleteCustomer(int customerID) throws SQLException, InterruptedException;

    /**
     * a methode that shows all customer in the system
     *
     * @return list of all customers
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    ArrayList<Customer> getAllCustomers() throws SQLException, InterruptedException;

    /**
     * a method shows specific customer data by id
     *
     * @param customerID customer's id
     * @return customer data
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    Customer getOneCustomer(int customerID) throws SQLException, InterruptedException;

    /**
     * a method that get customer id by customer email
     *
     * @param email customer's email.
     * @return customer id
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    int getCustomerIDbyEmail(String email) throws SQLException, InterruptedException;

    /**
     * a method that checks if customer exist in the system by customer email
     *
     * @param email customer's email
     * @return if customer exist in the system
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    boolean isCustomerExistsByEmail(String email) throws SQLException, InterruptedException;
}
