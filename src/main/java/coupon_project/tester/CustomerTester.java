package coupon_project.tester;

import coupon_project.beans.Category;
import coupon_project.exceptions.LoginException;
import coupon_project.exceptions.PurchaseException;
import coupon_project.facade.CustomerFacade;
import coupon_project.login.ClientType;
import coupon_project.login.LoginManager;
import coupon_project.utils.ArtUtils;
import coupon_project.utils.TablePrinter;

import java.sql.SQLException;

public class CustomerTester {
    public static void customerLoginFail() {
        System.out.println("\n\n" + ArtUtils.CUSTOMER_BANNER);
        try {
            System.out.print("Trying to login notTal (wrong password): ");
            LoginManager.getInstance().login("notTalHarel@gmail.com", "asd", ClientType.CUSTOMER);
            System.out.println("done!");
        } catch (Exception exception) {
            System.out.println("failed!");
            try {
                System.out.print("Trying to login notTal (wrong email): ");
                LoginManager.getInstance().login("aom", "tal_the_king", ClientType.CUSTOMER);
                System.out.println("done!");
            } catch (Exception exception1) {
                System.out.println("failed!");
                try {
                    System.out.print("Trying to login notTal (wrong client type): ");
                    LoginManager.getInstance().login("notTalHarel@gmail.com", "tal_the_king", ClientType.ADMINISTRATOR);
                    System.out.println("done!");
                } catch (Exception e) {
                    System.out.println("failed!");
                }
            }
        }
    }

    //public boolean login(String email, String password) success
    public static void customerTester() {
        System.out.println("\n\n" + ArtUtils.CUSTOMER_BANNER);
        CustomerFacade customerFacade;
        //**************************************************************************************************************
        //                                           Tal Harel
        //**************************************************************************************************************

        System.out.println("\n\n"+ArtUtils.TAL_BANNER);

        try {

            System.out.print("Trying to login notTal: ");
            customerFacade = (CustomerFacade) LoginManager.getInstance().login("notTalHarel@gmail.com", "tal_the_king", ClientType.CUSTOMER);
            System.out.println("done!");
            // public void purchaseCoupon(int couponID) three coupons success
            try {
                System.out.print("Trying to buy coupon 6: ");
                customerFacade.purchaseCoupon(6);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Trying to buy coupon 2: ");
                customerFacade.purchaseCoupon(2);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Trying to buy coupon 5: ");
                customerFacade.purchaseCoupon(5);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //public void purchaseCoupon(int couponID) failed by coupon doesn't exists
            try {
                System.out.print("Trying to buy coupon 55 (doesn't exists): ");
                customerFacade.purchaseCoupon(55);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //public void purchaseCoupon(int couponID) failed by already bought
            try {
                System.out.print("Trying to buy coupon 5 (again): ");
                customerFacade.purchaseCoupon(5);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public void purchaseCoupon(int couponID) failed by already expired
            try {
                System.out.print("Trying to buy coupon 3 (expired): ");
                customerFacade.purchaseCoupon(3);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public void purchaseCoupon(int couponID) failed by no left to buy
            try {
                System.out.print("Trying to buy coupon 4 (sold out): ");
                customerFacade.purchaseCoupon(4);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //public ArrayList<Coupon> getCustomerCoupons() success
            try {
                System.out.println("Coupon Table:");
                TablePrinter.print(customerFacade.getCustomerCoupons());
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public ArrayList<Coupon> getCustomerCouponsByCategory(Category category) success
            try {
                System.out.println("Fashion Coupon Table:");
                TablePrinter.print(customerFacade.getCustomerCouponsByCategory(Category.Fashion));
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public ArrayList<Coupon> getCustomerCouponsTillMaxPrice(double maxPrice) success
            try {
                System.out.println("Cheap (lower than 89.0) Coupon Table:");
                TablePrinter.print(customerFacade.getCustomerCouponsTillMaxPrice(89.0));
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public Customer getCustomerDetails() success
            try {
                System.out.println("notTal details:");
                TablePrinter.print(customerFacade.getCustomerDetails());
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
        } catch (LoginException | SQLException | InterruptedException e) {
            System.out.println("failed!");
            System.out.println(e.getMessage());
        }


        //**************************************************************************************************************
        //                                           Shiri levi
        //**************************************************************************************************************

        System.out.println("\n\n"+ArtUtils.SHIRI_BANNER);

        try {
            System.out.print("Trying to login notShiri: ");
            customerFacade = (CustomerFacade) LoginManager.getInstance().login("notShiriLevi@gmail.com", "shiri_the_queen", ClientType.CUSTOMER);
            System.out.println("done!");
            // public void purchaseCoupon(int couponID) three coupons success
            try {
                System.out.print("Trying to buy coupon 22: ");
                customerFacade.purchaseCoupon(22);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Trying to buy coupon 12: ");
                customerFacade.purchaseCoupon(12);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Trying to buy coupon 24: ");
                customerFacade.purchaseCoupon(24);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public ArrayList<Coupon> getCustomerCoupons() success
            try {
                System.out.println("Coupon Table:");
                TablePrinter.print(customerFacade.getCustomerCoupons());
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public ArrayList<Coupon> getCustomerCouponsByCategory(Category category) success
            try {
                System.out.println("Fashion Coupon Table:");
                TablePrinter.print(customerFacade.getCustomerCouponsByCategory(Category.Fashion));
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public ArrayList<Coupon> getCustomerCouponsTillMaxPrice(double maxPrice) success
            try {
                System.out.println("Cheap (lower than 95.50) Coupon Table:");
                TablePrinter.print(customerFacade.getCustomerCouponsTillMaxPrice(95.50));
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public Customer getCustomerDetails() success
            try {
                System.out.println("notShiri details:");
                TablePrinter.print(customerFacade.getCustomerDetails());
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
        } catch (LoginException | SQLException | InterruptedException e) {
            System.out.println(e.getMessage());
        }


        //**************************************************************************************************************
        //                                           Matan ozer
        //**************************************************************************************************************

        System.out.println("\n\n"+ArtUtils.MATAN_BANNER);

        try {
            System.out.print("Trying to login notMatan: ");
            customerFacade = (CustomerFacade) LoginManager.getInstance().login("notMatanOzer@gmail.com", "matan_still_the_king", ClientType.CUSTOMER);
            System.out.println("done!");
            // public void purchaseCoupon(int couponID) three coupons success
            try {
                System.out.print("Trying to buy coupon 15: ");
                customerFacade.purchaseCoupon(15);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Trying to buy coupon 18: ");
                customerFacade.purchaseCoupon(18);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Trying to buy coupon 13: ");
                customerFacade.purchaseCoupon(13);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public ArrayList<Coupon> getCustomerCoupons() success
            try {
                System.out.println("Coupon Table:");
                TablePrinter.print(customerFacade.getCustomerCoupons());
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public ArrayList<Coupon> getCustomerCouponsByCategory(Category category) success
            try {
                System.out.println("Restaurant Coupon Table:");
                TablePrinter.print(customerFacade.getCustomerCouponsByCategory(Category.Restaurant));
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public ArrayList<Coupon> getCustomerCouponsTillMaxPrice(double maxPrice) success
            try {
                System.out.println("Cheap (lower than 45.55) Coupon Table:");
                TablePrinter.print(customerFacade.getCustomerCouponsTillMaxPrice(45.55));
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public Customer getCustomerDetails() success
            try {
                System.out.println("notMatan details:");
                TablePrinter.print(customerFacade.getCustomerDetails());
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
        } catch (LoginException | SQLException | InterruptedException e) {
            System.out.println("failed!");
            System.out.println(e.getMessage());
        }


        //**************************************************************************************************************
        //                                             Roei Rashty
        //**************************************************************************************************************

        System.out.println("\n\n"+ArtUtils.ROEI_BANNER);

        try {
            System.out.print("Trying to login notRoei: ");
            customerFacade = (CustomerFacade) LoginManager.getInstance().login("notRoeiRashty@gmail.com", "roei_the_king", ClientType.CUSTOMER);
            System.out.println("done!");
            // public void purchaseCoupon(int couponID) three coupons success
            try {
                System.out.print("Trying to buy coupon 6: ");
                customerFacade.purchaseCoupon(6);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Trying to buy coupon 7: ");
                customerFacade.purchaseCoupon(7);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Trying to buy coupon 9: ");
                customerFacade.purchaseCoupon(9);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public ArrayList<Coupon> getCustomerCoupons() success
            try {
                System.out.println("Coupon Table:");
                TablePrinter.print(customerFacade.getCustomerCoupons());
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public ArrayList<Coupon> getCustomerCouponsByCategory(Category category) success
            try {
                System.out.println("Electricity Coupon Table:");
                TablePrinter.print(customerFacade.getCustomerCouponsByCategory(Category.Electricity));
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public ArrayList<Coupon> getCustomerCouponsTillMaxPrice(double maxPrice) success
            try {
                System.out.println("Cheap (lower than 60.75) Coupon Table:");
                TablePrinter.print(customerFacade.getCustomerCouponsTillMaxPrice(60.75));
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public Customer getCustomerDetails() success
            try {
                System.out.println("notRoei details:");
                TablePrinter.print(customerFacade.getCustomerDetails());
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
        } catch (LoginException | SQLException | InterruptedException e) {
            System.out.println("failed!");
            System.out.println(e.getMessage());
        }


        //**************************************************************************************************************
        //                                             Zeev Mindali
        //**************************************************************************************************************

        System.out.println("\n\n"+ArtUtils.ZEEV_BANNER);

        try {
            System.out.print("Trying to login notZeev: ");
            customerFacade = (CustomerFacade) LoginManager.getInstance().login("notZeevMindali@gmail.com", "zeev_the_king", ClientType.CUSTOMER);
            System.out.println("done!");
            // public void purchaseCoupon(int couponID) three coupons success
            try {
                System.out.print("trying to purchase coupon 11: ");
                customerFacade.purchaseCoupon(11);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("trying to purchase coupon 17: ");
                customerFacade.purchaseCoupon(17);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("trying to purchase coupon 20: ");
                customerFacade.purchaseCoupon(20);
                System.out.println("done!");
            } catch (PurchaseException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public ArrayList<Coupon> getCustomerCoupons() success
            try {
                System.out.println("Coupon Table:");
                TablePrinter.print(customerFacade.getCustomerCoupons());
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public ArrayList<Coupon> getCustomerCouponsByCategory(Category category) success
            try {
                System.out.println("Vacation Coupon Table:");
                TablePrinter.print(customerFacade.getCustomerCouponsByCategory(Category.Vacation));
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public ArrayList<Coupon> getCustomerCouponsTillMaxPrice(double maxPrice) success
            try {
                System.out.println("Cheap (lower than 60.75) Coupon Table:");
                TablePrinter.print(customerFacade.getCustomerCouponsTillMaxPrice(60.75));
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // public Customer getCustomerDetails() success
            try {
                System.out.println("notZeev details:");
                TablePrinter.print(customerFacade.getCustomerDetails());
            } catch (SQLException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
        } catch (LoginException | SQLException | InterruptedException e) {
            System.out.println("failed!");
            System.out.println(e.getMessage());
        }
    }
}