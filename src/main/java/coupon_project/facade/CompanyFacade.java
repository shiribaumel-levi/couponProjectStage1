package coupon_project.facade;

import coupon_project.beans.Category;
import coupon_project.beans.Company;
import coupon_project.beans.Coupon;
import coupon_project.exceptions.CompanyException;

import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyFacade extends ClientFacade {
    // The logged in company ID
    private int companyID;

    /**
     * Blank constructor to crate company facade instance
     */
    public CompanyFacade() {
    }

    /**
     * checks if the login arguments are correct.
     *
     * @param email    company email.
     * @param password company password.
     * @return whether arguments are correct.
     */
    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        // First, we check if there is a company with those email and password
        if (companyActions.isCompanyExists(email, password)) {
            // If there is a matching company, the companyID field is changed to the matching company ID
            this.companyID = companyActions.getCompanyIDbyEmail(email);
            // And the function returns true
            return true;
        }
        // If there isn't a matching company, the login failed and the function returns false
        return false;
    }

    /**
     * adds coupon to the database
     *
     * @param coupon coupon object
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when SQL throw InterruptedException
     * @throws CompanyException     when we throw custom exception
     */
    public void addCoupon(Coupon coupon) throws SQLException, InterruptedException, CompanyException {
        // First, if the coupon name (=title) already used by that company, you can't add that coupon
        if (couponActions.isCouponExistsByNameForCompany(coupon.getTitle(), companyID)) {
            // An exception is thrown
            throw new CompanyException("Coupon name already exists for your company");
        }
        // Next, We change the coupon's companyID to the logged in company ID
        coupon.setCompanyID(companyID);
        // And finally we add the coupon
        couponActions.addCoupon(coupon);
    }

    /**
     * updates coupon's data. can only update category, description, start date, end date, amount, price and image url
     *
     * @param coupon coupon object
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when SQL throw InterruptedException
     * @throws CompanyException     when we throw custom exception
     */
    public void updateCoupon(Coupon coupon) throws SQLException, InterruptedException, CompanyException {
        // First, we check if the coupon name exist for that company (=the coupon exists for that company)
        if (!couponActions.isCouponExistsByNameForCompany(coupon.getTitle(), companyID)) {
            // An exception is thrown
            throw new CompanyException("This coupon doesn't exist");
        }
        // Next, we update the coupon details (category, description, start date, end date, amount, price and image url)
        couponActions.updateCoupon(coupon);
    }

    /**
     * delete coupon
     *
     * @param couponID coupon ID
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when SQL throw InterruptedException
     * @throws CompanyException     when we throw custom exception
     */
    public void deleteCoupon(int couponID) throws SQLException, InterruptedException, CompanyException {
        // First, we check if the coupon ID exists (=if the coupon exists)
        if (!couponActions.isCouponExists(couponID)) {
            // An exception is thrown
            throw new CompanyException("This coupon doesn't exists)");
        }
        // Next, we check if the logged in company owns the coupon
        if (couponActions.getOneCoupon(couponID).getCompanyID() != companyID) {
            // An exception is thrown
            throw new CompanyException("This is not your coupon");
        }
        // Now, we delete all the coupon purchases
        purchaseActions.deleteAllPurchasesByCoupon(couponID);
        // And finally delete the coupon
        couponActions.deleteCoupon(couponID);
    }

    /**
     * Returning a list of all the company coupons
     *
     * @return company coupons list
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when SQL throw InterruptedException
     */
    public ArrayList<Coupon> getCompanyCoupons() throws SQLException, InterruptedException {
        // Return a list of all the company coupons
        return couponActions.getAllCompanyCoupons(this.companyID);
    }

    /**
     * Returning a list of all the company coupons from a specific category
     *
     * @param category wanted category
     * @return company coupon from a specific category
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when SQL throw InterruptedException
     */
    public ArrayList<Coupon> getCompanyCouponsByCategory(Category category) throws SQLException, InterruptedException {
        // Return a list of all the company coupons from a specific category
        return couponActions.getCompanyCouponsByCategory(this.companyID, category);
    }

    /**
     * Returning a list of all the company coupons with lower price than the given number
     *
     * @param maxPrice max price
     * @return company coupons list with lower price than the given number
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when SQL throw InterruptedException
     */
    public ArrayList<Coupon> getCompanyCouponsTillMaxPrice(double maxPrice) throws SQLException, InterruptedException {
        // Return a list of all the company coupons with lower price than the given number
        return couponActions.getCompanyCouponsTillMaxPrice(this.companyID, maxPrice);
    }

    /**
     * Returning the company's details
     *
     * @return company details
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when SQL throw InterruptedException
     */
    public Company getCompanyDetails() throws SQLException, InterruptedException {
        // Return the logged in company's details
        return companyActions.getOneCompany(this.companyID);
    }
}