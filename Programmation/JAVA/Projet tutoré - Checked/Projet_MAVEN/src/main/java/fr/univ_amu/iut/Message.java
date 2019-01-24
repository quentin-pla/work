package fr.univ_amu.iut;

import java.util.Date;

/**
 * A private message from a user to another
 */
public class Message {

    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Default constructor
     *
     * @param content  the text of the message
     * @param sender   the sender of the message
     * @param receiver the receiver of the message
     */
    /*public*/ Message(String content, UserAccount sender, UserAccount receiver) {
        sendDate = new Date();
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    // **************************************************
    // Fields
    // **************************************************

    /**
     * the date of the message
     */
    private Date sendDate;
    /**
     * the sender of the message
     */
    private UserAccount sender;
    /**
     * the receiver of the message
     */
    private UserAccount receiver;
    /**
     * The text of the message
     */
    private String content;

    // **************************************************
    // Getters
    // **************************************************

    /**
     * sendDate getter
     *
     * @return attribute sendDate
     */
    public Date getSendDate() {
        return sendDate;
    }

    /**
     * sender getter
     *
     * @return attribute sender
     */
    public UserAccount getSender() {
        return sender;
    }

    /**
     * receiver getter
     *
     * @return attribute receiver
     */
    public UserAccount getReceiver() {
        return receiver;
    }

    /**
     * content getter
     *
     * @return attribute content
     */
    public String getContent() {
        return content;
    }
}