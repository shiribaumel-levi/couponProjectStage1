package coupon_project.db_dao;

import coupon_project.beans.Category;
import coupon_project.beans.Coupon;
import coupon_project.dao.CouponsDAO;
import coupon_project.dao.CustomerVsCouponDAO;
import coupon_project.db_util.DatabaseUtils;
import coupon_project.db_util.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerVsCouponDBDAO implements CustomerVsCouponDAO {
    @Override
    public boolean isPurchaseExists(int customerID, int couponID) throws SQLException, InterruptedException {
        // Creates a map collection to replace "?" on the statement. Key--> number of "?", Value--> value (the replacement)
        Map<Integer, Object> params = new HashMap<>();
        // Adding all the replacement values and their order
        params.put(1, customerID);
        params.put(2, couponID);
        // The statement to run with its "?" where needed
        String CHECK_COUPON_FOR_CUSTOMER = "SELECT COUNT(*) AS total " +
                "FROM coupon_project.coupon_customers " +
                "WHERE customer_id=? AND coupon_id=?";
        // Running the statement and getting a ResultSet
        ResultSet resultSet = DatabaseUtils.runQueryForResult(CHECK_COUPON_FOR_CUSTOMER, params);
        // Moving for the first line of the ResultSet
        resultSet.next();
        // Returning whether it counts more than 0 matching values (=exist)
        return resultSet.getInt("total") > 0;
    }

    @Override
    public void addPurchase(int customerID, int couponID) throws InterruptedException {
        // Creates a map collection to replace "?" on the statement. Key--> number of "?", Value--> value (the replacement)
        Map<Integer, Object> params2 = new HashMap<>();
        // Adding all the replacement values and their order
        params2.put(1, couponID);
        params2.put(2, customerID);
        // The statement to run with its "?" where needed
        String ADD_PURCHASE = "INSERT " +
                "INTO coupon_project.coupon_customers " +
                "(`coupon_id`,`customer_id`) " +
                "VALUES (?,?)";
        // Running the statement
        DatabaseUtils.runQuery(ADD_PURCHASE, params2);
    }

    @Override
    public void deletePurchase(int customerID, int couponID) throws InterruptedException {
        // Creates a map collection to replace "?" on the statement. Key--> number of "?", Value--> value (the replacement)
        Map<Integer, Object> params2 = new HashMap<>();
        // Adding all the replacement values and their order
        params2.put(1, couponID);
        params2.put(2, customerID);
        // The statement to run with its "?" where needed
        String DELETE_PURCHASE = "DELETE " +
                "FROM coupon_project.coupon_customers " +
                "WHERE customer_id=? AND coupon_id=? ";
        // Running the statement
        DatabaseUtils.runQuery(DELETE_PURCHASE, params2);
    }

    @Override
    public void deleteAllPurchasesByCoupon(int couponID) throws InterruptedException {
        // Creates a map collection to replace "?" on the statement. Key--> number of "?", Value--> value (the replacement)
        Map<Integer, Object> params2 = new HashMap<>();
        // Adding all the replacement values and their order
        params2.put(1, couponID);
        // The statement to run with its "?" where needed
        String DELETE_PURCHASE = "DELETE " +
                "FROM coupon_project.coupon_customers " +
                "WHERE coupon_id=?";
        // Running the statement
        DatabaseUtils.runQuery(DELETE_PURCHASE, params2);
    }

    @Override
    public void deleteAllPurchasesByCustomer(int customerID) throws InterruptedException {
        // Creates a map collection to replace "?" on the statement. Key--> number of "?", Value--> value (the replacement)
        Map<Integer, Object> params2 = new HashMap<>();
        // Adding all the replacement values and their order
        params2.put(1, customerID);
        // The statement to run with its "?" where needed
        String DELETE_PURCHASE = "DELETE " +
                "FROM coupon_project.coupon_customers " +
                "WHERE customer_id=?";
        // Running the statement
        DatabaseUtils.runQuery(DELETE_PURCHASE, params2);
    }

    @Override
    public ArrayList<Coupon> getAllCustomerCoupons(int customerID) throws SQLException, InterruptedException {
        // Creates a map collection to replace "?" on the statement. Key--> number of "?", Value--> value (the replacement)
        Map<Integer, Object> params = new HashMap<>();
        // Adding all the replacement values and their order
        params.put(1, customerID);
        // The statement to run with its "?" where needed
        String GET_COUPON = "SELECT coupon_id " +
                "FROM coupon_project.coupon_customers " +
                "WHERE customer_id=?";
        // Running the statement and getting a ResultSet
        ResultSet resultSet = DatabaseUtils.runQueryForResult(GET_COUPON, params);
        // Creating a blank list to fill
        ArrayList<Coupon> couponsList = new ArrayList<>();
        // For every line on the ResultSet
        while (resultSet.next()) {
            // Getting an instance of CouponDAO
            CouponsDAO couponsDAO = Factory.getCouponDAO("sql");
            // Getting the coupon by its ID
            Coupon coupon = couponsDAO.getOneCoupon(resultSet.getInt("coupon_id"));
            // Adding the coupon to the list from above
            couponsList.add(coupon);
        }
        // Return the wanted list
        return couponsList;
    }

    @Override
    public ArrayList<Coupon> getCustomerCouponsByCategory(int customerID, Category category) throws SQLException, InterruptedException {
        // Getting a list of all the customer coupons
        ArrayList<Coupon> couponArrayList = getAllCustomerCoupons(customerID);
        // Filtering out every unwanted coupon (different category)
        couponArrayList.removeIf(coupon -> !coupon.getCategory().equals(category));
        // Return the wanted list
        return couponArrayList;
    }

    @Override
    public ArrayList<Coupon> getCustomerCouponsTillMaxPrice(int customerID, double maxPrice) throws SQLException, InterruptedException {
        // Getting a list of all the customer coupons
        ArrayList<Coupon> couponArrayList = getAllCustomerCoupons(customerID);
        // Filtering out every unwanted coupon (higher price)
        couponArrayList.removeIf(coupon -> coupon.getPrice() > maxPrice);
        // Return the wanted list
        return couponArrayList;
    }
}
