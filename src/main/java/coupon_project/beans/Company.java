package coupon_project.beans;

import java.util.ArrayList;

public class Company {

    // Company ID
    private int id;
    // Company name
    private String name;
    // Company email
    private String email;
    // Company password
    private String password;
    // Company coupon list
    private ArrayList<Coupon> coupons;


    /**
     * Constructor for company instance
     *
     * @param name     Company name
     * @param email    Company email
     * @param password Company password
     */
    public Company(String name, String email, String password) {
        this.id = 0;
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = new ArrayList<>();
    }

    public Company() {
    }

    /**
     * Set function for the company ID
     *
     * @param id Company ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Set function for the company name
     *
     * @param name Company name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set function for company email
     *
     * @param email Company email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set function for company password
     *
     * @param password Company password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set function for company coupon list
     *
     * @param coupons Company coupon list
     */
    public void setCoupons(ArrayList<Coupon> coupons) {
        this.coupons = coupons;
    }

    /**
     * Get function for company ID
     *
     * @return Company ID
     */
    public int getId() {
        return id;
    }

    /**
     * Get function for company name
     *
     * @return Company name
     */
    public String getName() {
        return name;
    }

    /**
     * Get function for company email
     *
     * @return Company email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get function for company password
     *
     * @return Company password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get function for company coupon list
     *
     * @return Company coupon list
     */
    public ArrayList<Coupon> getCoupons() {
        return coupons;
    }

    @Override
    public String toString() {
        return id + ", " + name + " (" + email + "," + password + ") " + coupons.size() + " coupons";
    }
}






