package coupon_project.tester;

import coupon_project.beans.Category;
import coupon_project.beans.Coupon;
import coupon_project.exceptions.CompanyException;
import coupon_project.exceptions.LoginException;
import coupon_project.facade.CompanyFacade;
import coupon_project.login.ClientType;
import coupon_project.login.LoginManager;
import coupon_project.utils.ArtUtils;
import coupon_project.utils.DateUtils;
import coupon_project.utils.NumberUtils;
import coupon_project.utils.TablePrinter;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CompanyTester {
    //public boolean login(String email, String password) failed
    public static void companyLoginFail() {
        System.out.println("\n\n"+ ArtUtils.COMPANY_BANNER);
        CompanyFacade companyFacade = null;
        try {
            System.out.print("Trying to login galaxybucks (wrong password): ");
            companyFacade = (CompanyFacade) LoginManager.getInstance().login("galaxybucks42@bucks.com", "bucksbucksbucks321", ClientType.COMPANY);
            System.out.println("done!");
        } catch (Exception exception) {
            System.out.println("failed!");
            try {
                System.out.print("Trying to login galaxybucks (wrong email): ");
                companyFacade = (CompanyFacade) LoginManager.getInstance().login("aom", "bucksbucksbucks123", ClientType.COMPANY);
                System.out.println("done!");
            } catch (Exception exception1) {
                System.out.println("failed!");
                try {
                    System.out.print("Trying to login galaxybucks (wrong client type): ");
                    companyFacade = (CompanyFacade) LoginManager.getInstance().login("galaxybucks42@bucks.com", "bucksbucksbucks123", ClientType.ADMINISTRATOR);
                    System.out.println("done!");
                } catch (Exception e) {
                    System.out.println("failed!");
                }
            }
        }
    }

    //public boolean login(String email, String password) one company success
    public static void companyTester() {
        System.out.println("\n\n"+ ArtUtils.COMPANY_BANNER);
        CompanyFacade companyFacade;

        //**************************************************************************************************************
        //                                           RoofMart
        //**************************************************************************************************************

        System.out.println("\n\n"+ArtUtils.ROOFMART_BANNER);

        try {
            System.out.print("Trying to login Roofmart: ");
            companyFacade = (CompanyFacade) LoginManager.getInstance().login("Roof@mart.com", "groceries", ClientType.COMPANY);
            System.out.println("done!");

            //void addCoupon(Coupon coupon) six coupons success
            try {
                System.out.print("Adding coupon shirt: ");
                companyFacade.addCoupon(new Coupon(Category.Fashion, "Shirt", "A cheap cheap shirt", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon computer: ");
                companyFacade.addCoupon(new Coupon(Category.Electricity, "Computer", "A cheap cheap computer", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // expired coupon, ID=3
            try {
                System.out.print("Adding coupon milk: ");
                companyFacade.addCoupon(new Coupon(Category.Grocery, "Milk", "A cheap cheap milk", DateUtils.getStartDate(), DateUtils.getExpiredDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            // amount coupon =0, ID=4
            try {
                System.out.print("Adding coupon mcDonald: ");
                companyFacade.addCoupon(new Coupon(Category.Restaurant, "McDonald", "A cheap cheap fake hamburger", DateUtils.getStartDate(), DateUtils.getEndDate(), 0, NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon fatal: ");
                companyFacade.addCoupon(new Coupon(Category.Vacation, "Fatal", "A cheap cheap vacation", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon jeans: ");
                companyFacade.addCoupon(new Coupon(Category.Fashion, "Jeans", "Cheap cheap jeans", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //void addCoupon(Coupon coupon) failed by name
            try {
                System.out.print("Adding coupon shirt (again): ");
                companyFacade.addCoupon(new Coupon(Category.Fashion, "Shirt", "not A cheap cheap shirt", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //void updateCoupon(Coupon coupon) failed by coupon doesn't exists
            try {
                System.out.print("Updating coupon notFatal (doesn't exists): ");
                companyFacade.updateCoupon(new Coupon(Category.Vacation, "not Fatal", "cheap vacation", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //void updateCoupon(Coupon coupon) success
            try {
                System.out.print("Updating coupon jeans: ");
                companyFacade.updateCoupon(new Coupon(Category.Fashion, "Jeans", "Cheap beauty jeans", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //void deleteCoupon(int couponID) failed by coupon doesn't exists
            try {
                System.out.print("Deleting coupon 7 (doesn't exists): ");
                companyFacade.deleteCoupon(7);
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //void deleteCoupon(int couponID) success
            try {
                System.out.print("Deleting coupon 1: ");
                companyFacade.deleteCoupon(1);
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //ArrayList<Coupon> getCompanyCoupons() success
            try {
                System.out.println("Coupon Table:");
                TablePrinter.print(companyFacade.getCompanyCoupons());
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //ArrayList<Coupon> getCompanyCouponsByCategory(Category category) success
            try {
                System.out.println("Fashion Coupon Table:");
                TablePrinter.print(companyFacade.getCompanyCouponsByCategory(Category.Fashion));
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //ArrayList<Coupon> getCompanyCouponsTillMaxPrice(double maxPrice) success
            try {
                System.out.println("Cheap (lower than 70.50) Coupon Table:");
                TablePrinter.print(companyFacade.getCompanyCouponsTillMaxPrice(70.50));
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //public Company getCompanyDetails() success
            try {
                System.out.println("Roofmart details:");
                TablePrinter.print(companyFacade.getCompanyDetails());
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
        } catch (LoginException | SQLException | InterruptedException e) {
            System.out.println("failed!");
            System.out.println(e.getMessage());
        }

        //and now, for each of the rest four companies:
        //public boolean login(String email, String password)
        //void addCoupon(Coupon coupon) five coupons success
        //ArrayList<Coupon> getCompanyCoupons() success
        //ArrayList<Coupon> getCompanyCouponsByCategory(Category category) success
        //ArrayList<Coupon> getCompanyCouponsTillMaxPrice(double maxPrice) success
        //public Company getCompanyDetails() success

        //**************************************************************************************************************
        //                                           GalaxyBucks
        //**************************************************************************************************************

        System.out.println("\n\n"+ArtUtils.GALAXYBUCKS_BANNER);

        try {
            System.out.print("Trying to login GalaxyBucks: ");
            companyFacade = (CompanyFacade) LoginManager.getInstance().login("galaxybucks42@bucks.com", "bucksbucksbucks123", ClientType.COMPANY);
            System.out.println("done!");
            //void addCoupon(Coupon coupon) five coupons success
            try {
                System.out.print("Adding coupon tv: ");
                companyFacade.addCoupon(new Coupon(Category.Electricity, "TV", "A cheap cheap TV", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon coca cola: ");
                companyFacade.addCoupon(new Coupon(Category.Grocery, "Coca Cola", "A cheap cheap coca cola bottle", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon ahla: ");
                companyFacade.addCoupon(new Coupon(Category.Restaurant, "Ahla", "A cheap cheap hummus", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon leonardo: ");
                companyFacade.addCoupon(new Coupon(Category.Vacation, "Leonardo", "A cheap cheap vacation", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon shoes: ");
                companyFacade.addCoupon(new Coupon(Category.Fashion, "Shoes", "Cheap cheap shoes", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //ArrayList<Coupon> getCompanyCoupons() success
            try {
                System.out.println("Coupon Table:");
                TablePrinter.print(companyFacade.getCompanyCoupons());
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //ArrayList<Coupon> getCompanyCouponsByCategory(Category category) success
            try {
                System.out.println("Vacation Coupon Table");
                TablePrinter.print(companyFacade.getCompanyCouponsByCategory(Category.Vacation));
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //ArrayList<Coupon> getCompanyCouponsTillMaxPrice(double maxPrice) success
            try {
                System.out.println("Cheap (lower than 43.64) Coupon Table");
                TablePrinter.print(companyFacade.getCompanyCouponsTillMaxPrice(43.64));
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //public Company getCompanyDetails() success
            try {
                System.out.println("GalaxyBucks details:");
                TablePrinter.print(companyFacade.getCompanyDetails());
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
        } catch (LoginException | SQLException | InterruptedException e) {
            System.out.println("failed!");
            System.out.println(e.getMessage());
        }

        //**************************************************************************************************************
        //                                           Pear
        //**************************************************************************************************************

        System.out.println("\n\n"+ArtUtils.PEAR_BANNER);

        try {
            System.out.print("Trying to login Pear: ");
            companyFacade = (CompanyFacade) LoginManager.getInstance().login("pear@pear.com", "better_than_apple", ClientType.COMPANY);
            System.out.println("done!");
            //void addCoupon(Coupon coupon) five coupons success
            try {
                System.out.print("Adding coupon phone: ");
                companyFacade.addCoupon(new Coupon(Category.Electricity, "Phone", "A cheap cheap phone", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon pasta: ");
                companyFacade.addCoupon(new Coupon(Category.Grocery, "Pasta", "A cheap cheap pasta", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon shipudei tzipora: ");
                companyFacade.addCoupon(new Coupon(Category.Restaurant, "Shipudei Tzipora", "A cheap cheap meat", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon club hotel: ");
                companyFacade.addCoupon(new Coupon(Category.Vacation, "Club Hotel", "A cheap cheap vacation", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon socks: ");
                companyFacade.addCoupon(new Coupon(Category.Fashion, "Socks", "Cheap cheap socks", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //ArrayList<Coupon> getCompanyCoupons() success
            try {
                System.out.println("Coupon Table:");
                TablePrinter.print(companyFacade.getCompanyCoupons());
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //ArrayList<Coupon> getCompanyCouponsByCategory(Category category) success
            try {
                System.out.println("Restaurant Coupon Table");
                TablePrinter.print(companyFacade.getCompanyCouponsByCategory(Category.Restaurant));
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //ArrayList<Coupon> getCompanyCouponsTillMaxPrice(double maxPrice) success
            try {
                System.out.println("Cheap (lower than 66.60) Coupon Table");
                TablePrinter.print(companyFacade.getCompanyCouponsTillMaxPrice(66.60));
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //public Company getCompanyDetails() success
            try {
                System.out.println("Pear details:");
                TablePrinter.print(companyFacade.getCompanyDetails());
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
        } catch (LoginException | SQLException | InterruptedException e) {
            System.out.println("failed!");
            System.out.println(e.getMessage());
        }

        //**************************************************************************************************************
        //                                           MacroSoft
        //**************************************************************************************************************

        System.out.println("\n\n"+ArtUtils.MACROSOFT_BANNER);

        try {
            System.out.print("Trying to login MacroSoft: ");
            companyFacade = (CompanyFacade) LoginManager.getInstance().login("macrosoft11@macro-soft.com", "macro_means_bigger",ClientType.COMPANY);
            System.out.println("done!");
            //void addCoupon(Coupon coupon) five coupons success
            try {
                System.out.print("Adding coupon laptop: ");
                companyFacade.addCoupon(new Coupon(Category.Electricity, "Laptop", "A cheap cheap laptop", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon cucumber: ");
                companyFacade.addCoupon(new Coupon(Category.Grocery, "Cucumber", "A cheap cheap cucumber", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon cafe cafe: ");
                companyFacade.addCoupon(new Coupon(Category.Restaurant, "Cafe Cafe", "A cheap cheap coffee", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon hilton: ");
                companyFacade.addCoupon(new Coupon(Category.Vacation, "Hilton", "A cheap cheap vacation", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon hat: ");
                companyFacade.addCoupon(new Coupon(Category.Fashion, "Hat", "A cheap cheap hat", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //ArrayList<Coupon> getCompanyCoupons() success
            try {
                System.out.println("Coupon Table:");
                TablePrinter.print(companyFacade.getCompanyCoupons());
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //ArrayList<Coupon> getCompanyCouponsByCategory(Category category) success
            try {
                System.out.println("Restaurant Coupon Table");
                TablePrinter.print(companyFacade.getCompanyCouponsByCategory(Category.Restaurant));
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //ArrayList<Coupon> getCompanyCouponsTillMaxPrice(double maxPrice) success
            try {
                System.out.println("Cheap (lower than 44.60) Coupon Table");
                TablePrinter.print(companyFacade.getCompanyCouponsTillMaxPrice(44.60));
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //public Company getCompanyDetails() success
            try {
                System.out.println("MacroSoft details:");
                TablePrinter.print(companyFacade.getCompanyDetails());
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
        } catch (LoginException | SQLException | InterruptedException e) {
            System.out.println("failed!");
            System.out.println(e.getMessage());
        }

        //**************************************************************************************************************
        //                                           Geegle
        //**************************************************************************************************************

        System.out.println("\n\n"+ArtUtils.GEEGLE_BANNER);

        try {
            System.out.print("Trying to login Geegle: ");
            companyFacade = (CompanyFacade) LoginManager.getInstance().login("geegle@geemail.com", "geegle4ever", ClientType.COMPANY);
            System.out.println("done!");
            //void addCoupon(Coupon coupon) five coupons success
            try {
                System.out.print("Adding coupon electric piano: ");
                companyFacade.addCoupon(new Coupon(Category.Electricity, "Electric Piano", "A cheap cheap electric piano", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon cornfalakes: ");
                companyFacade.addCoupon(new Coupon(Category.Grocery, "Cornflakes", "A cheap cheap cornflakes", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon japanika: ");
                companyFacade.addCoupon(new Coupon(Category.Restaurant, "Japanika", "A cheap cheap sushi", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon tzimerim bazafon: ");
                companyFacade.addCoupon(new Coupon(Category.Vacation, "Tzimerim Bazafon", "A cheap cheap vacation", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding coupon necklace: ");
                companyFacade.addCoupon(new Coupon(Category.Fashion, "Necklace", "A cheap cheap necklace", DateUtils.getStartDate(), DateUtils.getEndDate(), NumberUtils.getAmount(), NumberUtils.getPrice(), "This PC//pictures//wow"));
                System.out.println("done!");
            } catch (CompanyException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //ArrayList<Coupon> getCompanyCoupons() success
            try {
                System.out.println("Coupon Table:");
                TablePrinter.print(companyFacade.getCompanyCoupons());
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //ArrayList<Coupon> getCompanyCouponsByCategory(Category category) success
            try {
                System.out.println("Electricity Coupon Table");
                TablePrinter.print(companyFacade.getCompanyCouponsByCategory(Category.Electricity));
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //ArrayList<Coupon> getCompanyCouponsTillMaxPrice(double maxPrice) success
            try {
                System.out.println("Cheap (lower than 35.46) Coupon Table");
                TablePrinter.print(companyFacade.getCompanyCouponsTillMaxPrice(35.46));
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            //public Company getCompanyDetails() success
            try {
                System.out.println("Geegle details:");
                TablePrinter.print(companyFacade.getCompanyDetails());
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
        } catch (LoginException | SQLException | InterruptedException e) {
            System.out.println("failed!");
            System.out.println(e.getMessage());
        }
    }
}
