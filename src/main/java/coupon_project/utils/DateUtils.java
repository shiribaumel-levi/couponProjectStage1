package coupon_project.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Date class for creating and messing with date along the program
 */
public class DateUtils {

    /**
     * A convertor from LocalDate instance to Date instance (used also on SQL)
     * @param localDate LocalDate object
     * @return Date object
     */
    public static Date localDateToSqlDate(LocalDate localDate) {
        // Return the converted value of the LocalDate
        return Date.valueOf(localDate);
    }

    /**
     * Returning a random start date, a few days before today. Used to create a coupon
     * @return random start date
     */
    public static Date getStartDate() {
        // Return random start date
        return localDateToSqlDate(LocalDate.now().minusDays((int) (Math.random() * 14) + 1));
    }

    /**
     * Returning a random end date, a few days after today. Used to create a coupon
     * @return random end date
     */
    public static Date getEndDate() {
        // Return random end date
        return localDateToSqlDate(LocalDate.now().plusDays((int) (Math.random() * 14) + 1));
    }

    /**
     * Returning an expired end date (=yesterday). Used to create a specific coupon for the tester
     * @return expired end date
     */
    public static Date getExpiredDate() {
        // Return expired date
        return localDateToSqlDate(LocalDate.now().minusDays(1));
    }
}