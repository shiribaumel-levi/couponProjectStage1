package coupon_project.tester;

import coupon_project.db_util.ConnectionPool;
import coupon_project.db_util.DataBaseManager;
import coupon_project.threads.CouponExpirationDailyJob;

import java.sql.SQLException;

public class MainTester {
    public static Thread dailyJob() {
        CouponExpirationDailyJob runTask = new CouponExpirationDailyJob("sql");
        Thread dailyJob = new Thread(runTask);
        return dailyJob;
    }

    public static void adminTest() {
        AdminTester.adminLoginFail();
        AdminTester.adminTester();
    }

    public static void companyTest() {
        CompanyTester.companyLoginFail();
        CompanyTester.companyTester();
    }

    public static void customerTest() {
        CustomerTester.customerLoginFail();
        CustomerTester.customerTester();
    }

    public static void closeTester() {
        try {
            ConnectionPool.getInstance().closeAllConnection();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void mainTest() {
        try {
            DataBaseManager.dropCouponsProjectDB();
            DataBaseManager.createCouponsProjectDB();
            Thread dailyJob = dailyJob();
            dailyJob.start();
            adminTest();
            companyTest();
            customerTest();
            dailyJob.interrupt();
            closeTester();
        } catch (SQLException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}