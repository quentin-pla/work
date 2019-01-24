package fr.univ_amu.iut;


import java.util.Date;

/**
 * The user can make a personal ProductAlert in order to receive notifications
 * whenever a product fulfill givens conditions
 */
public class ProductAlert {
    /**
     * Default constructor
     *
     * @param category The category in which you can find the product
     * @param date     The user can chose a date so a product that did come out before the chosen date will
     *                 not pop an alert
     * @param mark     The minimum quality criteria that an user can chose
     * @param user     The concerned user account
     */
    public ProductAlert(String category, Date date, int mark, UserAccount user) {
        id = ++lastId;
        this.category = category;
        this.date = date;
        this.mark = mark;
        myUserAccount = user;
    }

    // **************************************************
    // Fields
    // **************************************************

    /**
     * Last ID given to a ProductAlert
     */
    private static int lastId = 0;

    /**
     * ID of the ProductAlert
     */
    private int id;

    /**
     * The category in which you can find the product
     */
    private String category;

    /**
     * The user can chose a date so a product that did come out before the chosen date will
     * not pop an alert
     */
    private Date date;

    /**
     * The minimum quality criteria that an user can chose
     */
    private int mark;

    /**
     * The concerned user account
     */
    private UserAccount myUserAccount;

}