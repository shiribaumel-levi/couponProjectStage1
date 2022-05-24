package coupon_project.db_dao;

import coupon_project.beans.Customer;
import coupon_project.dao.CustomersDAO;
import coupon_project.db_util.DatabaseUtils;
import coupon_project.db_util.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomersDBDAO implements CustomersDAO {

    @Override
    public boolean isCustomerExists(String email, String password) throws SQLException, InterruptedException {
        // Creates a map collection to replace "?" on the statement. Key--> number of "?", Value--> value (the replacement)
        Map<Integer, Object> params = new HashMap<>();
        // Adding all the replacement values and their order
        params.put(1, email);
        params.put(2, password);
        // The statement to run with its "?" where needed
        String CHECK_CUSTOMER = "SELECT COUNT(*) AS total " +
                "FROM coupon_project.customers " +
                "WHERE email=? AND password=?";
        // Running the statement and getting a ResultSet
        ResultSet resultSet = DatabaseUtils.runQueryForResult(CHECK_CUSTOMER, params);
        // Moving for the first line of the ResultSet
        resultSet.next();
        // Returning whether it counts more than 0 matching values (=exist)
        return resultSet.getInt("total") > 0;
    }

    @Override
    public boolean isCustomerExistsByID(int customerID) throws SQLException, InterruptedException {
        // Creates a map collection to replace "?" on the statement. Key--> number of "?", Value--> value (the replacement)
        Map<Integer, Object> params = new HashMap<>();
        // Adding all the replacement values and their order
        params.put(1, customerID);
        // The statement to run with its "?" where needed
        String IS_COUPON_EXISTS = "SELECT COUNT(*) AS total " +
                "FROM coupon_project.customers " +
                "WHERE id=?";
        // Running the statement and getting a ResultSet
        ResultSet resultSet = DatabaseUtils.runQueryForResult(IS_COUPON_EXISTS, params);
        // Moving for the first line of the ResultSet
        resultSet.next();
        // Returning whether it counts more than 0 matching values (=exist)
        return resultSet.getInt("total") > 0;
    }

    @Override
    public void addCustomer(Customer customer) throws InterruptedException {
        // Creates a map collection to replace "?" on the statement. Key--> number of "?", Value--> value (the replacement)
        Map<Integer, Object> params = new HashMap<>();
        // Adding all the replacement values and their order
        params.put(1, customer.getEmail());
        params.put(2, customer.getPassword());
        params.put(3, customer.getFirstName());
        params.put(4, customer.getLastName());
        // The statement to run with its "?" where needed
        String ADD_CUSTOMER = "INSERT " +
                "INTO coupon_project.customers " +
                "(`email`,`password`,`first_name`, `last_name`) " +
                "VALUES (?,?,?,?)";
        // Running the statement
        DatabaseUtils.runQuery(ADD_CUSTOMER, params);
    }

    @Override
    public void updateCustomer(Customer customer) throws InterruptedException {
        // Creates a map collection to replace "?" on the statement. Key--> number of "?", Value--> value (the replacement)
        Map<Integer, Object> params = new HashMap<>();
        // Adding all the replacement values and their order
        params.put(1, customer.getFirstName());
        params.put(2, customer.getLastName());
        params.put(3, customer.getPassword());
        params.put(4, customer.getEmail());
        // The statement to run with its "?" where needed
        String UPDATE_CUSTOMER = "UPDATE " +
                "coupon_project.customers " +
                "SET first_name=?, last_name=?, password=? " +
                "WHERE email=?";
        // Running the statement
        DatabaseUtils.runQuery(UPDATE_CUSTOMER, params);
    }

    @Override
    public void deleteCustomer(int customerID) throws InterruptedException {
        // Creates a map collection to replace "?" on the statement. Key--> number of "?", Value--> value (the replacement)
        Map<Integer, Object> params = new HashMap<>();
        // Adding all the replacement values and their order
        params.put(1, customerID);
        // The statement to run with its "?" where needed
        String DELETE_CUSTOMER = "DELETE " +
                "FROM coupon_project.customers " +
                "WHERE id=?";
        // Running the statement
        DatabaseUtils.runQuery(DELETE_CUSTOMER, params);
    }

    @Override
    public ArrayList<Customer> getAllCustomers() throws SQLException, InterruptedException {
        // The statement to run
        String GET_CUSTOMERS = "SELECT * " +
                "FROM coupon_project.customers";
        // Running the statement and getting a ResultSet
        ResultSet resultSet = DatabaseUtils.runQueryForResult(GET_CUSTOMERS);
        // Creating a blank list to fill
        ArrayList<Customer> customerList = new ArrayList<>();
        // For every line on the ResultSet
        while (resultSet.next()) {
            // Creating a blank customer to fill
            Customer customer = new Customer();
            // Changing its email
            customer.setEmail(resultSet.getString("email"));
            // Changing its ID
            customer.setId(resultSet.getInt("id"));
            // Changing it's first name
            customer.setFirstName(resultSet.getString("first_name"));
            // Changing it's surname
            customer.setLastName(resultSet.getString("last_name"));
            // Changing its password
            customer.setPassword(resultSet.getString("password"));
            // Changing its coupons list
            customer.setCoupons(Factory.getCustomerVsCouponDAO("sql").getAllCustomerCoupons(resultSet.getInt("id")));
            // Adding the filled customer to the list above
            customerList.add(customer);
        }
        // Return the wanted list
        return customerList;
    }

    @Override
    public Customer getOneCustomer(int customerID) throws SQLException, InterruptedException {
        // Creates a map collection to replace "?" on the statement. Key--> number of "?", Value--> value (the replacement)
        Map<Integer, Object> params = new HashMap<>();
        // Adding all the replacement values and their order
        params.put(1, customerID);
        // The statement to run with its "?" where needed
        String GET_CUSTOMER = "SELECT * " +
                "FROM coupon_project.customers " +
                "WHERE id=?";
        // Running the statement and getting a ResultSet
        ResultSet resultSet = DatabaseUtils.runQueryForResult(GET_CUSTOMER, params);
        // Moving for the first line of the ResultSet
        resultSet.next();
        // Creating a blank customer to fill
        Customer customer = new Customer();
        // Changing its email
        customer.setEmail(resultSet.getString("email"));
        // Changing its ID
        customer.setId(resultSet.getInt("id"));
        // Changing it's first name
        customer.setFirstName(resultSet.getString("first_name"));
        // Changing it's surname
        customer.setLastName(resultSet.getString("last_name"));
        // Changing its password
        customer.setPassword(resultSet.getString("password"));
        // Changing its coupon list
        customer.setCoupons(Factory.getCustomerVsCouponDAO("sql").getAllCustomerCoupons(resultSet.getInt("id")));
        // Return the wanted customer
        return customer;
    }

    @Override
    public int getCustomerIDbyEmail(String email) throws SQLException, InterruptedException {
        // Creates a map collection to replace "?" on the statement. Key--> number of "?", Value--> value (the replacement)
        Map<Integer, Object> params = new HashMap<>();
        // Adding all the replacement values and their order
        params.put(1, email);
        // The statement to run with its "?" where needed
        String GET_COMPANY = "SELECT id " +
                "FROM coupon_project.customers " +
                "WHERE email=?";
        // Running the statement and getting a ResultSet
        ResultSet resultSet = DatabaseUtils.runQueryForResult(GET_COMPANY, params);
        // Moving for the first line of the ResultSet
        resultSet.next();
        // Return the wanted ID
        return resultSet.getInt("id");
    }

    @Override
    public boolean isCustomerExistsByEmail(String email) throws SQLException, InterruptedException {
        // Creates a map collection to replace "?" on the statement. Key--> number of "?", Value--> value (the replacement)
        Map<Integer, Object> params = new HashMap<>();
        // Adding all the replacement values and their order
        params.put(1, email);
        // The statement to run with its "?" where needed
        String CHECK_CUSTOMER = "SELECT COUNT(*) AS total " +
                "FROM coupon_project.customers " +
                "WHERE email=?";
        // Running the statement and getting a ResultSet
        ResultSet resultSet = DatabaseUtils.runQueryForResult(CHECK_CUSTOMER, params);
        // Moving for the first line of the ResultSet
        resultSet.next();
        // Returning whether it counts more than 0 matching values (=exist)
        return resultSet.getInt("total") > 0;
    }
}
