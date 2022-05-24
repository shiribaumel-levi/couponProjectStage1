package coupon_project.dao;

import coupon_project.beans.Company;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CompaniesDAO {
    /**
     * Returning whether a company exists by email and password
     *
     * @param email-   company login email
     * @param password - company login password
     * @return if company exist
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    boolean isCompanyExists(String email, String password) throws SQLException, InterruptedException;

    /**
     * Returning whether a company exists by ID
     *
     * @param companyID- company ID
     * @return if company exist
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    boolean isCompanyExistsByID(int companyID) throws SQLException, InterruptedException;

    /**
     * Adding a company
     *
     * @param company company data
     * @throws SQLException         when SQL throw SQLException         If the company does not exist
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    void addCompany(Company company) throws SQLException, InterruptedException;

    /**
     * Updating a company's data
     *
     * @param company company data
     * @throws SQLException         when SQL throw SQLException         If the company does not exist
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    void updateCompany(Company company) throws SQLException, InterruptedException;

    /**
     * Deleting a company
     *
     * @param companyId The ID number of the company you want to delete
     * @throws SQLException         when SQL throw SQLException         If there is no company with this ID number
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    void deleteCompany(int companyId) throws SQLException, InterruptedException;

    /**
     * Returning a list of all the companies
     *
     * @return companies data list
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    ArrayList<Company> getAllCompany() throws SQLException, InterruptedException;

    /**
     * Returning a specific company by its ID
     *
     * @param companyId             company's id
     * @return company data
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    Company getOneCompany(int companyId) throws SQLException, InterruptedException;

    /**
     * Returning a company id by its email
     *
     * @param email- company email
     * @return company id
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    int getCompanyIDbyEmail(String email) throws SQLException, InterruptedException;

    /**
     * Returning whether a name is already used
     *
     * @param name- company name
     * @return if company exist
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    boolean isCompanyExistsByName(String name) throws SQLException, InterruptedException;

    /**
     * Returning whether an email is already used
     *
     * @param email company email
     * @return if company exist
     * @throws SQLException         when SQL throw SQLException
     * @throws InterruptedException when DataBaseUtils throw InterruptedException
     */
    boolean isCompanyExistsByEmail(String email) throws SQLException, InterruptedException;
}
