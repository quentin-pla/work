package fr.univ_amu.iut;

import java.util.Date;

/**
 * Save of each moderator action. Allows to check the moderators activities
 * in order to have the most secure application possible.
 */
public class Archive {

    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Default constructor
     *
     * @param _action   Action that did the moderator
     * @param moderator Moderator that did the action
     */
    public Archive(String _action, Moderator moderator) {
        id = ++lastId;
        date = new Date();
        action = _action;
        myModerator = moderator;
        myComment = null;
        myProduct = null;
        myUserAccount = null;
        myProposal = null;
    }

    /**
     * Comment archive constructor
     * A Comment archive is created when a moderator do an action on a Comment.
     *
     * @param _action   Action that did the moderator on the Comment
     * @param moderator Moderator that did the action
     * @param comment   Comment on which the action has been made
     */
    public Archive(String _action, Moderator moderator, Comment comment) {
        id = ++lastId;
        date = new Date();
        action = _action;
        myModerator = moderator;
        myComment = comment;
        myProduct = null;
        myUserAccount = null;
        myProposal = null;
    }

    /**
     * Product archive constructor
     * A Product archive is created when a moderator do an action on a Product.
     *
     * @param _action   Action that did the moderator on the Product
     * @param moderator Moderator that did the action
     * @param product   Product on which the action has been made
     */
    public Archive(String _action, Moderator moderator, Product product) {
        id = ++lastId;
        date = new Date();
        action = _action;
        myModerator = moderator;
        myComment = null;
        myProduct = product;
        myUserAccount = null;
        myProposal = null;
    }

    /**
     * UserAccount archive constructor
     * A UserAccount archive is created when a moderator do an action on a UserAccount.
     *
     * @param _action     Action that did the moderator on the UserAccount
     * @param moderator   Moderator that did the action
     * @param userAccount UserAccount on which the action has been made
     */
    public Archive(String _action, Moderator moderator, UserAccount userAccount) {
        id = ++lastId;
        date = new Date();
        action = _action;
        myModerator = moderator;
        myComment = null;
        myProduct = null;
        myUserAccount = userAccount;
        myProposal = null;
    }

    /**
     * Proposal archive constructor
     * A Proposal archive is created when a moderator do an action on a Proposal.
     *
     * @param _action   Action that did the moderator on the Proposal
     * @param moderator Moderator that did the action
     * @param proposal  Proposal on which the action has been made
     */
    public Archive(String _action, Moderator moderator, Proposal proposal) {
        id = ++lastId;
        date = new Date();
        action = _action;
        myModerator = moderator;
        myComment = null;
        myProduct = null;
        myUserAccount = null;
        myProposal = proposal;
    }

    // **************************************************
    // Fields
    // **************************************************

    /**
     * Last ID given to an Archive
     */
    private static int lastId = 0;

    /**
     * ID of the archive
     */
    private int id;

    /**
     * Date of creation of the Archive
     */
    private Date date;

    /**
     * Action that did the moderator (delete, ban...)
     */
    private String action;

    /**
     * Moderator that did the action
     */
    private Moderator myModerator;

    /**
     * Only if the Archive concerns a Comment
     */
    private Comment myComment;

    /**
     * Only if the Archive concerns a Product
     */
    private Product myProduct;

    /**
     * Only if the Archive concerns a UserAccount
     */
    private UserAccount myUserAccount;

    /**
     * Only if the Archive concerns a Proposal
     */
    private Proposal myProposal;
}