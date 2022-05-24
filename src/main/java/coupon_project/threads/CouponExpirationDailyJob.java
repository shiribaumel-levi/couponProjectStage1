package coupon_project.threads;

import coupon_project.beans.Coupon;
import coupon_project.dao.CouponsDAO;
import coupon_project.dao.CustomerVsCouponDAO;
import coupon_project.db_util.Factory;

import java.sql.SQLException;

/**
 * Thread class that is used to erase every expired coupon from the database
 */
public class CouponExpirationDailyJob implements Runnable {
    // Boolean field that is used to define if the thread should keep running
    private boolean isContinue;
    // Coupon DAO instance used to access some database actions
    private final CouponsDAO couponActions;
    // CustomerVsCoupon DAO instance used to access some database actions
    private final CustomerVsCouponDAO purchaseActions;

    /**
     * Constructor to create an instance of CouponExpirationDailyJob in order to run it
     *
     * @param DB current used database (="sql")
     */
    public CouponExpirationDailyJob(String DB) {
        // Creates the CouponDBDAO instance
        this.couponActions = Factory.getCouponDAO(DB);
        // Creates the CustomerVsCouponDBDAO instance
        this.purchaseActions = Factory.getCustomerVsCouponDAO(DB);
        // Initializing isContinue field to true
        this.isContinue = true;
    }

    /**
     * Run function that implemented from the Runnable interface. The main function of the thread, begin running when
     * called "start" and stops running when catching InterruptedException and turning isContinue field false
     */
    @Override
    public void run() {
        // First, while isContinue field is true, the thread should continue running
        while (isContinue) {
            // Try and Catch to catch the InterruptedException or SQLException
            try {
                // For every coupon on the table we check is expiration date (=end date)
                for (Coupon coupon : couponActions.getAllCoupons()) {
                    // If the coupon is no longer valid we delete it
                    if (!couponActions.isCouponValid(coupon.getId())) {
                        // So we delete all of his purchases
                        purchaseActions.deleteAllPurchasesByCoupon(coupon.getId());
                        // And finally delete it
                        this.couponActions.deleteCoupon(coupon.getId());
                    }
                }
                // After checking all the coupons, the thread sleeps for 24 hours
                Thread.sleep(1000 * 60 * 60 * 24);
                //            milliseconds, seconds, minutes, hours
            } catch (InterruptedException | SQLException e) {
                // If the thread catches some problem, it stops itself using the stop function
                stop();
            }
        }
    }

    /**
     * changing the isContinue field to false, so the thread won't keep running
     */
    public void stop() {
        // Changes isContinue field to false
        this.isContinue = false;
    }
}
