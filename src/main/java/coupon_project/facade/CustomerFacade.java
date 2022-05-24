package coupon_project.facade;

import coupon_project.beans.Category;
import coupon_project.beans.Coupon;
import coupon_project.beans.Customer;
import coupon_project.exceptions.PurchaseException;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerFacade extends ClientFacade {
    // Logged in customer ID number
    private int customerID;

    /**
     * Blank constructor to create customer facade instance
     */
    public CustomerFacade() {
    }

    /**
     * Checks if the login arguments are correct
     *
     * @param email    customer email
     * @param password customer password
     * @return whether the login arguments are correct
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when SQL throw InterruptedException
     */
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        // First, we check if there is a customer with those email and password
        if (customerActions.isCustomerExists(email, password)) {
            // If there is a matching customer, the customerID field is changed to the matching customer ID
            this.customerID = customerActions.getCustomerIDbyEmail(email);
            // And the function returns true
            return true;
        }
        // If there isn't a matching customer, the login failed and the function returns false
        return false;
    }

    /**
     * logged in customer buying a coupon
     * @param couponID wanted coupon ID
     * @throws SQLException when SQL throw SQLException
     * @throws InterruptedException when SQL throw InterruptedException
     * @throws PurchaseException when we throw custom exception
     */
    public void purchaseCoupon(int couponID) throws SQLException, InterruptedException, PurchaseException {
        // First, if the coupon ID doesn't exist (=coupon doesn't exists), you can't buy that coupon
        if (!couponActions.isCouponExists(couponID)) {
            // An exception is thrown
            throw new PurchaseException("This coupon doesn't exists");
        }
        // Next, if the customer already bought that coupon, you can't buy that coupon
        if (purchaseActions.isPurchaseExists(customerID, couponID)) {
            // An exception is thrown
            throw new PurchaseException("You already bought this coupon");
        }
        // Next, if the coupon amount is 0, you can't buy that coupon
        if (!couponActions.isCouponLeft(couponID)) {
            // An exception is thrown
            throw new PurchaseException("This coupon is no longer available");
        }
        // Next, if the coupon end date already passed, you can't buy that coupon
        if (!couponActions.isCouponValid(couponID)) {
            // An exception is thrown
            throw new PurchaseException("This coupon is no longer valid");
        }
        // Now, we decrease the purchased coupon amount by 1
        couponActions.decreaseCouponAmount(couponID);
        // And finally purchasing the coupon
        purchaseActions.addPurchase(customerID, couponID);
    }

    /**
     * Returning a list of all the customer coupons
     * @return customer coupons list
     * @throws SQLException when SQL throw SQLException
     * @throws InterruptedException when SQL throw InterruptedException
     */
    public ArrayList<Coupon> getCustomerCoupons() throws SQLException, InterruptedException {
        // Return a list of all customer coupons
        return purchaseActions.getAllCustomerCoupons(this.customerID);
    }

    /**
     * Returning a list of all the customer coupons from a specific category
     * @param category wanted category
     * @return customer coupons list from a specific category
     * @throws SQLException when SQL throw SQLException
     * @throws InterruptedException when SQL throw InterruptedException
     */
    public ArrayList<Coupon> getCustomerCouponsByCategory(Category category) throws SQLException, InterruptedException {
        // Return a list of all the customer coupons from a specific category
        return purchaseActions.getCustomerCouponsByCategory(this.customerID, category);
    }

    /**
     * Returning a list of all the customer coupons with lower price than a given number
     * @param maxPrice max price
     * @return customer coupons list with lower price than a given number
     * @throws SQLException when SQL throw SQLException
     * @throws InterruptedException when SQL throw InterruptedException
     */
    public ArrayList<Coupon> getCustomerCouponsTillMaxPrice(double maxPrice) throws SQLException, InterruptedException {
        // Return a list of all the customer coupons with lower price than a given number
        return purchaseActions.getCustomerCouponsTillMaxPrice(this.customerID, maxPrice);
    }

    /**
     * Returning the customer details
     * @return logged in customer's details
     * @throws SQLException when SQL throw SQLException
     * @throws InterruptedException when SQL throw InterruptedException
     */
    public Customer getCustomerDetails() throws SQLException, InterruptedException {
        // Return the customer details
        return customerActions.getOneCustomer(this.customerID);
    }
}
