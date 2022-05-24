package coupon_project.dao;

import coupon_project.beans.Category;
import coupon_project.beans.Coupon;
import coupon_project.beans.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CouponsDAO {
    /**
     * A method that adds a new coupon to the system
     *
     * @param coupon coupon data
     * @throws SQLException         when SQL throws SQLException
     * @throws InterruptedException when DataBaseUtils throws InterruptedException
     */
    void addCoupon(Coupon coupon) throws SQLException, InterruptedException;

    /**
     * A method that updates existing coupon data
     *
     * @param coupon coupon data
     * @throws SQLException         when SQL throws SQLException
     * @throws InterruptedException when DataBaseUtils throws InterruptedException
     */
    void updateCoupon(Coupon coupon) throws SQLException, InterruptedException;

    /**
     * Method that deletes a coupon by ID number
     *
     * @param couponID id number
     * @throws SQLException         when SQL throws SQLException
     * @throws InterruptedException when DataBaseUtils throws InterruptedException
     */
    void deleteCoupon(int couponID) throws SQLException, InterruptedException;

    /**
     * A method that returns the list of all coupons in the system
     *
     * @return list of all coupons in the system
     * @throws SQLException         when SQL throws SQLException
     * @throws InterruptedException when DataBaseUtils throws InterruptedException
     */
    ArrayList<Coupon> getAllCoupons() throws SQLException, InterruptedException;

    /**
     * Method that returns one coupon by his ID
     *
     * @param couponID wanted coupon ID
     * @return wanted coupom
     * @throws SQLException         when SQL throws SQLException
     * @throws InterruptedException when DataBaseUtils throws InterruptedException
     */
    Coupon getOneCoupon(int couponID) throws SQLException, InterruptedException;

    /**
     * A method that decrease the amount by 1
     *
     * @param couponID Coupon's id
     * @throws SQLException         when SQL throws SQLException
     * @throws InterruptedException when DataBaseUtils throws InterruptedException
     */
    void decreaseCouponAmount(int couponID) throws SQLException, InterruptedException;

    /**
     * A method that increase the amount by 1
     *
     * @param couponID Coupon's id.
     * @throws SQLException         when SQL throws SQLException
     * @throws InterruptedException when DataBaseUtils throws InterruptedException
     */
    void increaseCouponAmount(int couponID) throws SQLException, InterruptedException;

    /**
     * A method that shows all company coupons
     *
     * @param companyID company's id.
     * @return coupons list
     * @throws SQLException         when SQL throws SQLException
     * @throws InterruptedException when DataBaseUtils throws InterruptedException
     */
    ArrayList<Coupon> getAllCompanyCoupons(int companyID) throws SQLException, InterruptedException;

    /**
     * method that shows company coupons by specific category
     *
     * @param companyID - company's id.
     * @param category  of coupon
     * @return coupons list from the chosen category
     * @throws SQLException         when SQL throws SQLException
     * @throws InterruptedException when DataBaseUtils throws InterruptedException
     */
    ArrayList<Coupon> getCompanyCouponsByCategory(int companyID, Category category) throws SQLException, InterruptedException;

    /**
     * method that shows company coupons till max price
     *
     * @param companyID- company's id.
     * @param maxPrice- max price of coupons list
     * @return coupons list till max price
     * @throws SQLException         when SQL throws SQLException
     * @throws InterruptedException when DataBaseUtils throws InterruptedException
     */
    ArrayList<Coupon> getCompanyCouponsTillMaxPrice(int companyID, double maxPrice) throws SQLException, InterruptedException;

    /**
     * A method that checks whether a particular coupon exists by its ID
     *
     * @param couponID coupon id
     * @return whether a particular coupon exists
     * @throws SQLException         when SQL throws SQLException
     * @throws InterruptedException when DataBaseUtils throws InterruptedException
     */
    boolean isCouponExists(int couponID) throws SQLException, InterruptedException;

    /**
     * A method that checks whether a particular coupon remains by its ID
     *
     * @param couponID coupon id
     * @return whether a particular coupon remains
     * @throws SQLException         when SQL throws SQLException
     * @throws InterruptedException when DataBaseUtils throws InterruptedException
     */
    boolean isCouponLeft(int couponID) throws SQLException, InterruptedException;

    /**
     * A method that checks whether a particular coupon still valid by its ID
     *
     * @param couponID coupon id
     * @return whether a particular coupon valid
     * @throws SQLException         when SQL throws SQLException
     * @throws InterruptedException when DataBaseUtils throws InterruptedException
     */
    boolean isCouponValid(int couponID) throws SQLException, InterruptedException;

    /**
     * method that delete all company coupons
     *
     * @param companyID             company's id
     * @throws SQLException         when SQL throws SQLException
     * @throws InterruptedException when DataBaseUtils throws InterruptedException
     */
    void deleteAllCompanyCoupons(int companyID) throws SQLException, InterruptedException;

    /**
     * checks if coupon name already exist in specific company
     *
     * @param name-     coupon name
     * @param companyID  company's id.
     * @return if coupon by this name exist in the company
     * @throws SQLException         when SQL throws SQLException
     * @throws InterruptedException when DataBaseUtils throws InterruptedException
     */
    boolean isCouponExistsByNameForCompany(String name, int companyID) throws SQLException, InterruptedException;
}
