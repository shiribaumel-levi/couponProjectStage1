package coupon_project.login;

import coupon_project.exceptions.LoginException;
import coupon_project.facade.AdminFacade;
import coupon_project.facade.ClientFacade;
import coupon_project.facade.CompanyFacade;
import coupon_project.facade.CustomerFacade;

import java.sql.SQLException;

/**
 * A singleton class that manages all the login system to any type of client
 */
public class LoginManager {

    // The instance of the loginManager, created only once and then used every time you want to login
    // "volatile" --> prevents a visibility problem (one thread changes the value but haven't return it yet to the main
    // memory, and another thread whose reading the value doesn't see the updated value)
    private static volatile LoginManager instance = null;

    /**
     * Blank constructor to create an instance of the loginManager
     * Private constructor indicates singleton class
     */
    private LoginManager() {
    }

    /**
     * Returning the instance of the loginManager. If the instance haven't been created yet, it creates it using double
     * check, so that only one instance will be created, and so that only at creation time the sync block will be accessed.
     *
     * @return reference to instance of login manager.
     */
    public static LoginManager getInstance() {
        // First, we check if the instance needs to be created
        if (instance == null) {
            // Next, we lock the LoginManager class, so no one can access it and accidentally touch the instance while
            // we are creating it
            synchronized (LoginManager.class) {
                // Now, we check again if the instance is null and needs to be created, just to make sure that it
                // hasn't been created since we check above
                if (instance == null) {
                    // And finally we create the instance
                    instance = new LoginManager();
                }
            }
        }
        // If the instance already created or the creation process finished, return the instance
        return instance;
    }

    /**
     * Manages the login system and returning a facade if the login success
     *
     * @param email      client's email.
     * @param password   client's password.
     * @param clientType one of the 3 client types (admin, company, customer).
     * @return a facade object, which gives access to the client's allowed actions.
     * @throws LoginException       when the combination of email, password and client type can't login
     * @throws SQLException         when exception is raised from sql queries.
     * @throws InterruptedException if thread is interrupted.
     */
    public ClientFacade login(String email, String password, ClientType clientType) throws LoginException, SQLException, InterruptedException {
        // Using a switch case for every client type. Easy to add client type if needed
        switch (clientType) {
            // In case of admin
            case ADMINISTRATOR:
                // We create a ClientFacade using AdminFacade
                ClientFacade adminFacade = new AdminFacade();
                // Next, we check if the login is successful
                if (!adminFacade.login(email, password)) {
                    // An exception is thrown
                    throw new LoginException("Invalid email or password");
                }
                // If the login was successful, the AdminFacade is returned
                return adminFacade;
            // In case of company
            case COMPANY:
                // We create a ClientFacade using CompanyFacade
                ClientFacade companyFacade = new CompanyFacade();
                // Next, we check if the login is successful
                if (!companyFacade.login(email, password)) {
                    // An exception is thrown
                    throw new LoginException("Invalid email or password");
                }
                // If the login was successful, the CompanyFacade is returned
                return companyFacade;
            // In case of customer
            case CUSTOMER:
                // We create a ClientFacade using CustomerFacade
                ClientFacade customerFacade = new CustomerFacade();
                // Next, we check if the login is successful
                if (!customerFacade.login(email, password)) {
                    // An exception is thrown
                    throw new LoginException("Invalid email or password");
                }
                // If the login was successful, the CustomerFacade is returned
                return customerFacade;
            // In case of other client type (doesn't exist) it just returns null
            default:
                return null;
        }
    }
}
