package coupon_project.utils;

/**
 * Number class for creating numbers along the program
 */
public class NumberUtils {
    /**
     * Returning a random number for coupon amount. Used to create a coupon
     * @return random number for coupon amount
     */
    public static int getAmount() {
        // Maximum number for amount
        final int MAX_AMOUNT = 25;
        // Minimum number for amount
        final int MIN_AMOUNT = 10;
        // Return random number for coupon amount
        return (int) (Math.random() * (MAX_AMOUNT - MIN_AMOUNT + 1)) + MIN_AMOUNT;
    }

    /**
     * Returning a random number for coupon price. Used to create a coupon
     * @return random number for coupon price
     */
    public static double getPrice() {
        // Maximum number for price
        final int MAX_PRICE = 100;
        // Minimum number for price
        final int MIN_PRICE = 20;
        // Return random number for coupon price
        return (int)(Math.random() * (MAX_PRICE*100 - MIN_PRICE*100 + 1) + MIN_PRICE*100)/100.00;
    }
}
