package fr.univ_amu.iut;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A registered user of the application.
 */
public class UserAccount {

    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Default constructor
     *
     * @param pseudo    Pseudo of the user
     * @param password  Password of the account
     * @param firstName Firstname of the user
     * @param lastName  Lastname of the user
     * @param mail      Mail of the user
     */
    public UserAccount(String pseudo, String password, String firstName, String lastName, String mail) {
        id = ++lastId;
        creationDate = new Date();

        this.pseudo = pseudo;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;

        userDescription = "Je suis un nouvel utilisateur !";
        instagramAccount = "";
        userWebsite = "";
        personalPicture = "fr/univ_amu/iut/Images/default_personal.png";
        coverPicture = "fr/univ_amu/iut/Images/default_cover.jpg";
        level = 0;
        badge = Badges.DEFAULT;
        ratio = 1;
        numberOfFollowers = 0;
        unbanDate = new Date();
        isPermanentlyBanned = false;

        myComments = new ArrayList<>();
        myLikedComments = new ArrayList<>();
        myDislikedComments = new ArrayList<>();
        myFavoriteAccounts = new ArrayList<>();
        myFavoriteComments = new ArrayList<>();
        myFavoriteProducts = new ArrayList<>();
        sentMessages = new ArrayList<>();
        receivedMessages = new ArrayList<>();
        notifications = new ArrayList<>();
        history = new ArrayList<>();
        myProductAlerts = new ArrayList<>();
    }

    // **************************************************
    // Fields
    // **************************************************

    /**
     * Last ID given to a UserAccount
     */
    private static int lastId = 0;

    /**
     * ID of the user
     */
    private int id;

    /**
     * Account creation date
     */
    private Date creationDate;

    /**
     * Pseudo of the user
     */
    private String pseudo;

    /**
     * Password of the account
     */
    private String password;

    /**
     * Firstname of the user
     */
    private String firstName;

    /**
     * Lastname of the user
     */
    private String lastName;

    /**
     * Mail of the user
     */
    private String mail;

    /**
     * Description of the user (optional)
     */
    private String userDescription;

    /**
     * Link to the user instagram account (optional)
     */
    private String instagramAccount;

    /**
     * Link to the website of the user (optional)
     */
    private String userWebsite;

    /**
     * Personal picture of the user (optional)
     */
    private String personalPicture;

    /**
     * Cover picture of the user (optional)
     */
    private String coverPicture;

    /**
     * Level of the user (increments when the user xp reach a preset value)
     */
    private Integer level;

    /**
     * XP of the user (increments when the user validates missions)
     */
    private int xp;

    /**
     * A very reliable user will have a green badge
     * A non reliable user will have a red badge
     */
    private Badges badge;

    /**
     * Ratio likes/dislikes (from the user comments)
     */
    private double ratio;

    /**
     * Number of users who put the current user in favorite
     */
    private Integer numberOfFollowers;

    /**
     * History of :
     * - visited products, accounts, comments
     * - liked and disliked comments
     * - done researches
     */
    private List<String> history;

    /**
     * Date of user unban
     */
    private Date unbanDate;

    /**
     * TRUE is the user is banned forever
     * else FALSE
     */
    private boolean isPermanentlyBanned;

    /**
     * Messages sent by the user
     */
    private List<Message> sentMessages;

    /**
     * Messages received by the user
     */
    private List<Message> receivedMessages;

    /**
     * Notifications sent to the user
     */
    private List<String> notifications;

    /**
     * Comments posted by the user
     */
    private List<Comment> myComments;

    /**
     * Comments liked by the user
     */
    private List<Comment> myLikedComments;

    /**
     * Comments disliked by the user
     */
    private List<Comment> myDislikedComments;

    /**
     * Products in the favorite list of the user
     */
    private List<Product> myFavoriteProducts;

    /**
     * Comments in the favorite list of the user
     */
    private List<Comment> myFavoriteComments;

    /**
     * Accounts in the favorite list of the user
     */
    private List<UserAccount> myFavoriteAccounts;


    /**
     * ProductAlerts of the user
     */
    private List<ProductAlert> myProductAlerts;

    /**
     * Missions already done by the user
     */
    private List<Mission> myMissions;

    // **************************************************
    // Getters
    // **************************************************

    /**
     * myProductAlerts getter
     *
     * @return attribute myProductAlerts
     */
    public List<ProductAlert> getMyProductAlerts() {
        return myProductAlerts;
    }

    /**
     * mail getter
     *
     * @return attribute mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * sentMessages getter
     *
     * @return attribute sentMessages
     */
    public List<Message> getSentMessages() {
        return sentMessages;
    }

    /**
     * receivedMessages getter
     *
     * @return attribute receivedMessages
     */
    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    /**
     * notifications getter
     *
     * @return attribute notification
     */
    public List<String> getNotifications() {
        return notifications;
    }

    /**
     * myFavoriteAccounts getter
     *
     * @return attribute myFavoriteAccounts
     */
    public List<UserAccount> getMyFavoriteAccounts() {
        return myFavoriteAccounts;
    }

    /**
     * myFavoriteComments getter
     *
     * @return attribute myFavoriteComments
     */
    public List<Comment> getMyFavoriteComments() {
        return myFavoriteComments;
    }

    /**
     * myFavoriteProducts getter
     *
     * @return attribute myFavoriteProducts
     */
    public List<Product> getMyFavoriteProducts() {
        return myFavoriteProducts;
    }

    /**
     * myLikedComments getter
     *
     * @return attribute myLikedComments
     */
    public List<Comment> getMyLikedComments() {
        return myLikedComments;
    }

    /**
     * myDislikedComments getter
     *
     * @return attribute myDislikedComments
     */
    public List<Comment> getMyDislikedComments() { return myDislikedComments; }

    /**
     * id getter
     *
     * @return attribute id
     */
    public int getID() {
        return id;
    }

    /**
     * creationDate getter
     *
     * @return attribute creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * pseudo getter
     *
     * @return attribute pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * password getter
     *
     * @return attribute password
     */
    public String getPassword() {
        return password;
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

    /**
     * coverPicture getter
     *
     * @return attribute coverPicture
     */
    public String getCoverPicture() {
        return coverPicture;
    }

    /**
     * personalPicture getter
     *
     * @return attribute personalPicture
     */
    public String getPersonalPicture() {
        return personalPicture;
    }

    /**
     * history getter
     *
     * @return attribute history
     */
    public List<String> getHistory() {
        return history;
    }

    /**
     * level getter
     *
     * @return attribute level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * numberOfFollowers getter
     *
     * @return attribute numberOfFollowers
     */
    public Integer getNumberOfFollowers() {
        return numberOfFollowers;
    }

    /**
     * userDescription getter
     *
     * @return attribute userDescription
     */
    public String getUserDescription() {
        return userDescription;
    }

    /**
     * instagramAccount getter
     *
     * @return attribute instagramAccount
     */
    public String getInstagramAccount() {
        return instagramAccount;
    }

    /**
     * userWebsite getter
     *
     * @return attribute userWebsite
     */
    public String getUserWebsite() {
        return userWebsite;
    }

    /**
     * badge getter
     *
     * @return attribute badge
     */
    public Badges getBadge() {
        return badge;
    }

    /**
     * unbanDate getter
     *
     * @return attribute unbanDate
     */
    public Date getUnbanDate() {
        return unbanDate;
    }

    // **************************************************
    // Setters
    // **************************************************

    /**
     * personalPicture setter
     *
     * @param pictureLink Link to the personal picture
     */
    public void setPersonalPicture(String pictureLink) {
        personalPicture = pictureLink;
    }

    /**
     * coverPicture setter
     *
     * @param pictureLink Link to the cover picture
     */
    public void setCoverPicture(String pictureLink) {
        coverPicture = pictureLink;
    }

    /**
     * pseudo setter
     *
     * @param pseudo New pseudo
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * mail setter
     *
     * @param mail New mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * description setter
     *
     * @param description New description
     */
    public void setDescription(String description) {
        this.userDescription = description;
    }

    /**
     * instagram setter
     *
     * @param instagram New instagram link
     */
    public void setInstagram(String instagram) {
        this.instagramAccount = instagram;
    }

    /**
     * webSite setter
     *
     * @param link New link to the website of the user
     */
    public void setWebSiteLink(String link) {
        userWebsite = link;
    }

    /**
     * password setter
     *
     * @param password New password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * badge setter
     *
     * @param badge New badge
     */
    public void setBadge(Badges badge) {
        this.badge = badge;
    }

    /**
     * level setter
     *
     * @param level New level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    // **************************************************
    // Public methods
    // **************************************************

    /**
     * Add a comment to the user comments list
     *
     * @param comment Comment that the user created
     */
    public void addComment(Comment comment) { myComments.add(comment); }

    /**
     * Add a comment to the user liked comments list
     *
     * @param comment Comment that the user liked
     */
    public void addLikedComment(Comment comment) {
        myLikedComments.add(comment);
    }

    /**
     * Remove a comment from the user liked comments list
     *
     * @param comment Comment that the user liked
     */
    public void removeLikedComment(Comment comment) { myLikedComments.remove(comment); }

    /**
     * Add a comment to the user disliked comments list
     *
     * @param comment Comment that the user disliked
     */
    public void addDislikedComment(Comment comment) { myDislikedComments.add(comment); }

    /**
     * Remove a comment from the user disliked comments list
     *
     * @param comment Comment that the user disliked
     */
    public void removeDislikedComment(Comment comment) { myDislikedComments.remove(comment); }

    /**
     * Increments the level of the user
     */
    public void addLevel() {
        ++level;
    }

    /**
     * Add a favorite account to the user
     *
     * @param user New favorite user
     */
    public void addFavoriteUser(UserAccount user) {
        myFavoriteAccounts.add(user);
        user.addFollower();
    }

    /**
     * Remove an account from the favorites accounts of the user
     *
     * @param user User to remove from favorites
     */
    public void removeFavoriteUser(UserAccount user) {
        myFavoriteAccounts.remove(user);
        user.removeFollower();
    }

    /**
     * Add a favorite product to the user
     *
     * @param product New favorite product
     */
    public void addFavoriteProduct(Product product) {
        myFavoriteProducts.add(product);
    }

    /**
     * Remove a product from the favorites products of the user
     *
     * @param product Product to remove from favorites
     */
    public void removeFavoriteProduct(Product product) {
        myFavoriteProducts.remove(product);
    }

    /**
     * Add a favorite comment to the user
     *
     * @param comment Comment to which we add a favorite
     */
    public void addFavoriteComment(Comment comment) {
        myFavoriteComments.add(comment);
    }

    /**
     * Remove a comment from the favorites comments of the user
     *
     * @param comment Comment to which we remove a favorite
     */
    public void removeFavoriteComment(Comment comment) {
        myFavoriteComments.remove(comment);
    }

    /**
     * Add an action to the user history
     *
     * @param action action to add
     */
    public void addActionToHistory(String action) {
        history.add(action);
    }

    /**
     * Ban the user for a period of time in seconds
     */
    public void banUser(int sec) {
        if (sec < 1)
            isPermanentlyBanned = true;
        else {
            Date currentDate = new Date();
            long currentBanDuration = this.unbanDate.getTime() - currentDate.getTime();
            this.unbanDate.setTime(currentBanDuration + currentDate.getTime() + (sec * 1000));
        }
        Database.setIsConnected(false);
    }

    /**
     * Unban the user
     */
    public void unbanUser() {
        this.unbanDate = new Date(0);
        this.isPermanentlyBanned = false;
    }

    /**
     * @return TRUE if the user is banned,
     * else FALSE
     */
    public boolean isUserBanned() {
        Date now = new Date();
        return (this.unbanDate.getTime() >= now.getTime() || this.isPermanentlyBanned);
    }

    /**
     * @return TRUE if the user is permanently banned,
     * else FALSE
     */
    public boolean isUserPermanentlyBanned() {
        return isPermanentlyBanned;
    }

    /**
     * Send a message to receiver
     *
     * @param text     Message to send
     * @param receiver Receiver of the message
     */
    public void sendMessage(String text, UserAccount receiver) {
        Message message = new Message(text, this, receiver);
        this.sentMessages.add(message);
        receiver.receiveMessage(text, this);
    }

    /**
     * update the likes and dislikes ratio in order to ban "bad" user from our user list
     */
    public void updateLikesAndDislikesRatio() {
        int likes = 0;
        int dislikes = 0;
        for (Comment c : myComments) {
            likes += c.getLikes();
            dislikes += c.getDislikes();
        }
        if (dislikes == 0) ratio = likes / (double) dislikes;
        else ratio = 1000;
    }

    /**
     * Send a notification to the user
     *
     * @param notification Notification to send
     */
    public void sendNotification(String notification) {
        notifications.add(notification);
    }

    /**
     * Add an element to current user's history
     *
     * @param element element to add in the history
     */
    public void addInHistory(String element) {
        history.add(element);
    }

    /**
     * Remove all elements in the history
     */
    public void deleteHistory() {
        history.clear();
    }

    /**
     * Increments the number of followers of the user
     */
    public void addFollower() {
        ++numberOfFollowers;
    }

    /**
     * Decrements the number of followers of the user
     */
    public void removeFollower() {
        --numberOfFollowers;
    }

    // **************************************************
    // Private methods
    // **************************************************

    /**
     * Add a message to current user received messages
     *
     * @param text   Message to send
     * @param sender Sender of the message
     */
    private void receiveMessage(String text, UserAccount sender) {
        Message message = new Message(text, sender, this);
        this.receivedMessages.add(message);
    }
}