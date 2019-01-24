package fr.univ_amu.iut;

/**
 * Person that manage the application
 */
public class Moderator {

    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Default constructor
     *
     * @param firstName First name of the moderator
     * @param lastName  Last name of the moderator
     * @param email     Email of the moderator
     */
    public Moderator(String firstName, String lastName, String email) {
        id = ++lastId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // **************************************************
    // Fields
    // **************************************************

    /**
     * Last ID given to a Moderator
     */
    private static int lastId = 0;

    /**
     * ID of the Moderator
     */
    private int id;

    /**
     * first name of the Moderator
     */
    private String firstName;

    /**
     * last name of the Moderator
     */
    private String lastName;

    /**
     * email of the Moderator
     */
    private String email;

    /**
     * id getter
     *
     * @return attribute id
     */
    public int getId() {
        return id;
    }

    /**
     * email getter
     *
     * @return attribute email
     */
    public String getEmail() {
        return email;
    }

    /**
     * firstName getter
     *
     * @return attribute firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * lastName getter
     *
     * @return attribute lastName
     */
    public String getLastName() {
        return lastName;
    }
}