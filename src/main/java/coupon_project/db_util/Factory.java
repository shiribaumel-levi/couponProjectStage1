package coupon_project.db_util;

import coupon_project.dao.CompaniesDAO;
import coupon_project.dao.CouponsDAO;
import coupon_project.dao.CustomerVsCouponDAO;
import coupon_project.dao.CustomersDAO;
import coupon_project.db_dao.CompaniesDBDAO;
import coupon_project.db_dao.CouponsDBDAO;
import coupon_project.db_dao.CustomerVsCouponDBDAO;
import coupon_project.db_dao.CustomersDBDAO;

/**
 * Factory class used to manage the DBDAO used, in case of using more than one database
 */
public class Factory {
    /**
     * Return the used database CustomerVsCouponDBDAO
     *
     * @param DB used database
     * @return CustomerVsCouponDBDAO
     */
    public static CustomerVsCouponDAO getCustomerVsCouponDAO(String DB) {
        // We use switch case (easy to add databases)
        switch (DB) {
            // In case of mySQL
            case "sql":
                // Return mySQL CustomerVsCouponDBDAO
                return new CustomerVsCouponDBDAO();
            // In case of other database (doesn't exist) it just returns null
            default:
                // Return null
                return null;
        }
    }

    /**
     * Return the used database CouponsDBDAO
     *
     * @param DB used database
     * @return CouponDBDAO
     */
    public static CouponsDAO getCouponDAO(String DB) {
        // We use switch case (easy to add databases)
        switch (DB) {
            // In case of mySQL
            case "sql":
                // Return mySQL CouponsDBDAO
                return new CouponsDBDAO();
            // In case of other database (doesn't exist) it just returns null
            default:
                // Return null
                return null;
        }
    }

    /**
     * Return the used database CompaniesDBDAO
     *
     * @param DB used database
     * @return CompaniesDBDAO
     */
    public static CompaniesDAO getCompanyDAO(String DB) {
        // We use switch case (easy to add databases)
        switch (DB) {
            // In case of mySQL
            case "sql":
                // Return mySQL CompaniesDBDAO
                return new CompaniesDBDAO();
            // In case of other database (doesn't exist) it just returns null
            default:
                // Return null
                return null;
        }
    }

    /**
     * Return the used database CustomersDBDAO
     *
     * @param DB used database
     * @return CustomersDBDAO
     */
    public static CustomersDAO getCustomerDAO(String DB) {
        // We use switch case (easy to add databases)
        switch (DB) {
            // In case of mySQL
            case "sql":
                // Return mySQL CustomersDBDAO
                return new CustomersDBDAO();
            // In case of other database (doesn't exist) it just returns null
            default:
                // Return null
                return null;
        }
    }
}
