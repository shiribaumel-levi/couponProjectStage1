package coupon_project.facade;

import coupon_project.dao.CompaniesDAO;
import coupon_project.dao.CouponsDAO;
import coupon_project.dao.CustomerVsCouponDAO;
import coupon_project.dao.CustomersDAO;
import coupon_project.db_util.Factory;

import java.sql.SQLException;

public abstract class ClientFacade {
    // In order to use the DBDao functions, we need to create an intance of every Dao, let's do it:
    // Company DAO
    protected CompaniesDAO companyActions = Factory.getCompanyDAO("sql");
    // Customer DAO
    protected CustomersDAO customerActions = Factory.getCustomerDAO("sql");
    // Coupon DAO
    protected CouponsDAO couponActions = Factory.getCouponDAO("sql");
    // Purchase (customer vs coupon) DAO
    protected CustomerVsCouponDAO purchaseActions = Factory.getCustomerVsCouponDAO("sql");

    /**
     * Login function that must be implemented on every client facade
     *
     * @param userName client username
     * @param password client password
     * @return whether the login was successful or not
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when SQL throw InterruptedException
     */
    public abstract boolean login(String userName, String password) throws SQLException, InterruptedException;
}