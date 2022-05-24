package coupon_project.tester;

import coupon_project.beans.Company;
import coupon_project.beans.Customer;
import coupon_project.exceptions.AdministrationException;
import coupon_project.exceptions.LoginException;
import coupon_project.facade.AdminFacade;
import coupon_project.login.ClientType;
import coupon_project.login.LoginManager;
import coupon_project.utils.ArtUtils;
import coupon_project.utils.TablePrinter;

import java.sql.SQLException;

public class AdminTester {
    public static void adminLoginFail() {
        System.out.println("\n\n"+ArtUtils.ADMIN_BANNER);
        AdminFacade adminFacade = null;
        try {
            System.out.print("Trying to login (wrong password): ");
            adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "asd", ClientType.ADMINISTRATOR);
            System.out.println("done!");
        } catch (Exception exception) {
            System.out.println("failed!");
            try {
                System.out.print("Trying to login (wrong email): ");
                adminFacade = (AdminFacade) LoginManager.getInstance().login("aom", "admin", ClientType.ADMINISTRATOR);
                System.out.println("done!");
            } catch (Exception exception1) {
                System.out.println("failed!");
                try {
                    System.out.print("Trying to login (wrong client type): ");
                    adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin", ClientType.CUSTOMER);
                    System.out.println("done!");
                } catch (Exception e) {
                    System.out.println("failed!");
                }
            }
        }
    }

    public static void adminTester() {
        System.out.println("\n\n"+ArtUtils.ADMIN_BANNER);
        AdminFacade adminFacade;
        try {
            System.out.print("Trying to login: ");
            adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
            System.out.println("done!");
            //public void addCompany(Company company) six companies success
            try {
                System.out.print("Adding Roofmart: ");
                adminFacade.addCompany(new Company("Roofmart", "roof@mart.com", "groceries"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding Pear: ");
                adminFacade.addCompany(new Company("Pear", "pear@pear.com", "better_than_apple"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding Geegle: ");
                adminFacade.addCompany(new Company("Geegle", "geegle@geemail.com", "geegle4ever"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding Macrosoft: ");
                adminFacade.addCompany(new Company("Macrosoft", "macrosoft11@macro-soft.com", "macro_means_bigger"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding Galaxy bucks: ");
                adminFacade.addCompany(new Company("Galaxybucks", "galaxybucks42@bucks.com", "bucksbucksbucks123"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding Nice: ");
                adminFacade.addCompany(new Company("Nice", "nice@good.com", "87654321"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public void addCompany(Company company) failed by name
            try {
                System.out.print("Adding Nice (again): ");
                adminFacade.addCompany(new Company("Nice", "notnice@good.com", "12345678"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public void addCompany(Company company) failed by email
            try {
                System.out.print("Adding Telport (same email as galaxybucks): ");
                adminFacade.addCompany(new Company("telport", "galaxybucks42@bucks.com", "whyb79"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public void updateCompany(Company company) failed by company doesn't exists
            try {
                System.out.print("Updating NotNice (doesn't exists): ");
                adminFacade.updateCompany(new Company("notNice", "notnice@good.com", "12345678"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public void updateCompany(Company company) success
            try {
                System.out.print("Updating Nice: ");
                adminFacade.updateCompany(new Company("Nice", "notnice@good.com", "not87654321"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public void deleteCompany(int companyID) failed by company doesn't exists
            try {
                System.out.print("Deleting company 7 (doesn't exists): ");
                adminFacade.deleteCompany(7);
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public void deleteCompany(int companyID) success
            try {
                System.out.print("Deleting company 6: ");
                adminFacade.deleteCompany(6);
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public ArrayList<Company> getAllCompanies() success
            try {
                System.out.println("Companies Table:");
                TablePrinter.print(adminFacade.getAllCompanies());
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public Company getOneCompany(int companyID) success
            try {
                System.out.println("Company number 3:");
                TablePrinter.print(adminFacade.getOneCompany(3));
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public Company getOneCompany(int companyID) failed by company doesn't exists
            try {
                System.out.println("Company number 7:");
                TablePrinter.print(adminFacade.getOneCompany(7));
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public void addCustomer(Customer customer) six customers success
            try {
                System.out.print("Adding notTal: ");
                adminFacade.addCustomer(new Customer("notTal", "notHarel", "notTalHarel@gmail.com", "tal_the_king"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding notShiri: ");
                adminFacade.addCustomer(new Customer("notShiri", "notLevi", "notShiriLevi@gmail.com", "shiri_the_queen"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding notMatan: ");
                adminFacade.addCustomer(new Customer("notMatan", "notOzer", "notMatanOzer@gmail.com", "matan_the_king"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding notRoei: ");
                adminFacade.addCustomer(new Customer("notRoei", "notRashty", "notRoeiRashty@gmail.com", "roei_the_king"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding notZeev: ");
                adminFacade.addCustomer(new Customer("notZeev", "notMindali", "notZeevMindali@gmail.com", "zeev_the_king"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
            try {
                System.out.print("Adding notElon: ");
                adminFacade.addCustomer(new Customer("notElon", "notMusk", "notElonMusk@gmail.com", "elon_the_king"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public void addCustomer(Customer customer) failed by email
            try {
                System.out.print("Adding Elon (same email as notElon): ");
                adminFacade.addCustomer(new Customer("Elon", "Musk", "notElonMusk@gmail.com", "not_elon_the_king"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public void updateCustomer(Customer customer) failed by customer doesn't exists
            try {
                System.out.print("Updating notRoei (wrong email): ");
                adminFacade.updateCustomer(new Customer("notRoei", "notRashty", "RoeiRashty@gmail.com", "roei_the_king"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public void updateCustomer(Customer customer) success
            try {
                System.out.print("Updating notMatan: ");
                adminFacade.updateCustomer(new Customer("notMatan", "notOzer", "notMatanOzer@gmail.com", "matan_still_the_king"));
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public void deleteCustomer(int customerID) failed by customer doesn't exists
            try {
                System.out.print("Deleting customer 7 (doesn't exists): ");
                adminFacade.deleteCustomer(7);
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public void deleteCustomer(int customerID) success
            try {
                System.out.print("Deleting customer 6: ");
                adminFacade.deleteCustomer(6);
                System.out.println("done!");
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public ArrayList<Customer> getAllCustomers() success
            try {
                System.out.println("Customer Table:");
                TablePrinter.print(adminFacade.getAllCustomers());
            } catch (SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public Customer getOneCustomer(int customerID) success
            try {
                System.out.println("Customer number 4:");
                TablePrinter.print(adminFacade.getOneCustomer(4));
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }

            //public Customer getOneCustomer(int customerID) failed by customer doesn't exist
            try {
                System.out.println("Customer number 7:");
                adminFacade.getOneCustomer(7);
            } catch (AdministrationException | SQLException | InterruptedException e) {
                System.out.println("failed!");
                System.out.println(e.getMessage());
            }
        } catch (LoginException | InterruptedException | SQLException e) {
            System.out.println("failed!");
            System.out.println(e.getMessage());
        }
    }
}
