package coupon_project.db_util;

import coupon_project.beans.Category;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DataBaseManager {

    // mySQL URL route
    public static final String URL = "jdbc:mysql://localhost:3306/";

    // mySQL username
    public static final String USER_NAME = "root";

    // mySQL password
    public static final String USER_PASS = "12345678";

    // SQL command for creating database "coupon_project"
    public static final String CREATE_DB = "CREATE DATABASE IF NOT EXISTS coupon_project";

    // SQL command for deleting database "coupon_project"
    public static final String DROP_DB = "DROP DATABASE IF EXISTS coupon_project";

    // SQL command for creating category table
    public static final String CREATE_CATEGORY_TABLE =
            "CREATE TABLE IF NOT EXISTS coupon_project.categories(\n" +
                    "`id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "`name` VARCHAR(45) NOT NULL,\n" +
                    "PRIMARY KEY (`id`));";

    // SQL command for deleting category table
    public static final String DROP_CATEGORY_TABLE =
            "DROP TABLE IF EXISTS coupon_project.categories";

    // SQL command for creating company table
    public static final String CREATE_COMPANY_TABLE =
            "CREATE TABLE IF NOT EXISTS coupon_project.companies(\n" +
                    "`id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "`name` VARCHAR(45) NOT NULL,\n" +
                    "`email` VARCHAR(45) NOT NULL,\n" +
                    "`password` VARCHAR(45) NOT NULL,\n" +
                    "PRIMARY KEY (`id`));";

    // SQL command for deleting company table
    public static final String DROP_COMPANY_TABLE =
            "DROP TABLE IF EXISTS coupon_project.companies";

    // SQL command for creating customer table
    public static final String CREATE_CUSTOMER_TABLE =
            "CREATE TABLE IF NOT EXISTS coupon_project.customers(\n" +
                    "`id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "`first_name` VARCHAR(45) NOT NULL,\n" +
                    "`last_name` VARCHAR(45) NOT NULL,\n" +
                    "`email` VARCHAR(45) NOT NULL,\n" +
                    "`password` VARCHAR(45) NOT NULL,\n" +
                    "PRIMARY KEY (`id`));";

    // SQL command for deleting customer table
    public static final String DROP_CUSTOMER_TABLE =
            "DROP TABLE IF EXISTS coupon_project.customers";

    // SQL command for creating coupons table
    public static final String CREATE_COUPONS_TABLE =
            "CREATE TABLE IF NOT EXISTS coupon_project.coupons(\n" +
                    "`id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "`company_id` INT NOT NULL,\n" +
                    "`category_id` INT NOT NULL,\n" +
                    "`title` VARCHAR(45) NOT NULL,\n" +
                    "`description` VARCHAR(45) NOT NULL,\n" +
                    "`start_date` DATE NOT NULL,\n" +
                    "`end_date` DATE NOT NULL,\n" +
                    "`amount` INT NOT NULL,\n" +
                    "`price` DOUBLE NOT NULL,\n" +
                    "`image` VARCHAR(45) NOT NULL,\n" +
                    "PRIMARY KEY (`id`),\n" +
                    "INDEX `company_id_idx` (`company_id` ASC) VISIBLE,\n" +
                    "INDEX `category_id_idx` (`category_id` ASC) VISIBLE,\n" +
                    "CONSTRAINT `company_id`\n" +
                    "FOREIGN KEY (`company_id`)\n" +
                    "REFERENCES coupon_project.companies (`id`)\n" +
                    "ON DELETE NO ACTION\n" +
                    "ON UPDATE NO ACTION,\n" +
                    "CONSTRAINT `category_id`\n" +
                    "FOREIGN KEY (`category_id`)\n" +
                    "REFERENCES coupon_project.categories (`id`)\n" +
                    "ON DELETE NO ACTION\n" +
                    "ON UPDATE NO ACTION)\n";

    // SQL command for deleting coupons table
    public static final String DROP_COUPONS_TABLE =
            "DROP TABLE IF EXISTS coupon_project.coupons";

    // SQL command for creating "Coupon VS Customers" table
    public static final String CREATE_COUPONS_CUSTOMER_TABLE =
            "CREATE TABLE IF NOT EXISTS coupon_project.coupon_customers(\n" +
                    "`customer_id` INT NOT NULL,\n" +
                    "`coupon_id` INT NOT NULL,\n" +
                    "PRIMARY KEY (`customer_id`, `coupon_id`),\n" +
                    "INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE,\n" +
                    "CONSTRAINT `customer_id`\n" +
                    "FOREIGN KEY (`customer_id`)\n" +
                    "REFERENCES coupon_project.customers (`id`)\n" +
                    "ON DELETE NO ACTION\n" +
                    "ON UPDATE NO ACTION,\n" +
                    "CONSTRAINT `coupon_id`\n" +
                    "FOREIGN KEY (`coupon_id`)\n" +
                    "REFERENCES coupon_project.`coupons` (`id`)\n" +
                    "ON DELETE NO ACTION\n" +
                    "ON UPDATE NO ACTION)";

    // SQL command for deleting "Coupon VS Customers" table
    public static final String DROP_COUPONS_CUSTOMER_TABLE =
            "DROP TABLE IF EXISTS coupon_project.coupon_customers";


    /**
     * A function for creating database "coupon_project"
     *
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    public static void createDataBase() throws SQLException, InterruptedException {
        DatabaseUtils.runQuery(CREATE_DB);
    }

    /**
     * A function for deleting database "coupon_project"
     *
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    public static void dropDataBase() throws SQLException, InterruptedException {
        DatabaseUtils.runQuery(DROP_DB);
    }

    /**
     * A function for creating category table
     *
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    public static void createCategoryTable() throws SQLException, InterruptedException {
        DatabaseUtils.runQuery(CREATE_CATEGORY_TABLE);
        for (Category category : Category.values()) {
            Map<Integer, Object> params = new HashMap<>();
            params.put(1, category.ordinal() + 1);
            params.put(2, category.toString());
            DatabaseUtils.runQuery("INSERT INTO coupon_project.categories (`id`,`name`) VALUES (?,?)", params);
        }
    }

    /**
     * A function for deleting category table
     *
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    public static void dropCategoryTable() throws SQLException, InterruptedException {
        DatabaseUtils.runQuery(DROP_CATEGORY_TABLE);
    }

    /**
     * A function for creating company table
     *
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    public static void createCompanyTable() throws SQLException, InterruptedException {
        DatabaseUtils.runQuery(CREATE_COMPANY_TABLE);
    }

    /**
     * A function for deleting company table
     *
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    public static void dropCompanyTable() throws SQLException, InterruptedException {
        DatabaseUtils.runQuery(DROP_COMPANY_TABLE);
    }

    /**
     * A function for creating customer table
     *
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    public static void createCustomerTable() throws SQLException, InterruptedException {
        DatabaseUtils.runQuery(CREATE_CUSTOMER_TABLE);
    }

    /**
     * A function for deleting customer table
     *
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    public static void dropCustomerTable() throws SQLException, InterruptedException {
        DatabaseUtils.runQuery(DROP_CUSTOMER_TABLE);
    }

    /**
     * A function for creating coupon table
     *
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    public static void createCouponTable() throws SQLException, InterruptedException {
        DatabaseUtils.runQuery(CREATE_COUPONS_TABLE);
    }

    /**
     * A function for deleting coupon table
     *
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    public static void dropCouponTable() throws SQLException, InterruptedException {
        DatabaseUtils.runQuery(DROP_COUPONS_TABLE);
    }

    /**
     * A function for creating Coupon VS Customer table
     *
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    public static void createCouponVSCustomerTable() throws SQLException, InterruptedException {
        DatabaseUtils.runQuery(CREATE_COUPONS_CUSTOMER_TABLE);
    }

    /**
     * A function for deleting Coupon VS Customer table
     *
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    public static void dropCouponVSCustomerTable() throws SQLException, InterruptedException {
        DatabaseUtils.runQuery(DROP_COUPONS_CUSTOMER_TABLE);
    }

    /**
     * A function for creating the whole project (database, company table, customer table and coupon table)
     *
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    public static void createCouponsProjectDB() throws SQLException, InterruptedException {
        createDataBase();
        createCompanyTable();
        createCustomerTable();
        createCategoryTable();
        createCouponTable();
        createCouponVSCustomerTable();
    }

    /**
     * A function for creating the whole project (database, company table, customer table and coupon table)
     *
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    public static void dropCouponsProjectDB() throws SQLException, InterruptedException {
        dropDataBase();
        dropCompanyTable();
        dropCustomerTable();
        dropCategoryTable();
        dropCouponTable();
        dropCouponVSCustomerTable();
    }
}