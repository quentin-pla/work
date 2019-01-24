package fr.univ_amu.iut;

import java.util.List;
import java.util.Set;

/**
 * The user can create a proposal for a product
 */
public class Proposal {

    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Default constructor
     *
     * @param content The content of the proposal
     * @param user    The user who add the proposal
     */
    public Proposal(String content, UserAccount user) {
        id = ++lastId;
        this.content = content;
        myUserAccount = user;
        isValidated = false;
    }

    // **************************************************
    // Fields
    // **************************************************

    private static List<Proposal> systemProposals;
    /**
     * Last ID given to a proposal
     */
    private static int lastId = 0;

    /**
     * ID of the proposal
     */
    private int id;

    /**
     * Content of the proposal
     */
    private String content;

    /**
     * User who add the proposal
     */
    private UserAccount myUserAccount;

    /**
     * Status of the proposal
     * After the moderator checked the proposal, he will either validate it or invalidate it
     */
    private boolean isValidated;

    // **************************************************
    // Getters
    // **************************************************

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public UserAccount getMyUserAccount() {
        return myUserAccount;
    }

    // **************************************************
    // Public methods
    // **************************************************

    /**
     * Validation of the proposal
     */
    public void validateProposal() {
        isValidated = true;
        myUserAccount.sendNotification("Your proposal (" + id + ") has been validated.");
    }
}