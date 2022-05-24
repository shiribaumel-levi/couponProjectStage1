package coupon_project.beans;

import java.util.ArrayList;

public class Customer {
    // Customer ID
    private int id;
    // Customer first name
    private String firstName;
    // Customer last name
    private String lastName;
    // Customer email
    private String email;
    // Customer password
    private String password;
    // Customer coupon list
    private ArrayList<Coupon> coupons;

    /**
     * Constructor for customer instance
     *
     * @param firstName Customer first name
     * @param lastName  Customer last name
     * @param email     Customer email
     * @param password  Customer password
     */
    public Customer(String firstName, String lastName, String email, String password) {
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.coupons = new ArrayList<>();
    }

    /**
     * Blank constructor for using
     */
    public Customer() {
    }

    /**
     * Get function for Customer ID
     *
     * @return Customer ID
     */
    public int getId() {
        return id;
    }

    /**
     * Set function for Customer ID
     *
     * @param id Customer ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get function for Customer first name
     *
     * @return Customer first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set function for Customer first name
     *
     * @param firstName Customer first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get function for Customer last name
     *
     * @return Customer last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set function for Customer last name
     *
     * @param lastName Customer last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get function for Customer email
     *
     * @return Customer email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set function for Customer email
     *
     * @param email Customer email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get function for Customer password
     *
     * @return Customer password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set function for Customer password
     *
     * @param password Customer password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get function for Customer coupon list
     *
     * @return Customer coupon list
     */
    public ArrayList<Coupon> getCoupons() {
        return coupons;
    }

    /**
     * Set function for Customer coupon list
     *
     * @param coupons Customer coupon list
     */
    public void setCoupons(ArrayList<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return id + "," + firstName + " " + lastName + " (" + email + "," + password + ") " + coupons.size() + " coupons";
    }
}
