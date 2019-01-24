package fr.univ_amu.iut;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Temporary database in order to test the program
 * Init categories, products, users ...
 */
public class Database {

    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Default constructor
     */
    /*public*/ Database() {
        initCategories();
        initProducts();
        initGirltrip();
        initJeek();
        initBannedAccount();
        initPermanentlyBannedAccount();
        initCommentK70Vengeance();
        initCommentIPhoneX();
        Clavier.addProduct(k70Vengeance);
        Telephonie.addProduct(IPhoneX);
    }

    // **************************************************
    // Fields
    // **************************************************

    /**
     * TRUE if a user is connected
     */
    private static boolean isConnected = false;

    // --- USERS --- //

    /**
     * All users
     */
    private static List<UserAccount> myUsers = new ArrayList<>();

    /**
     * Connected user
     * Only one user can be connected. It will have to log out if another user wants to connect
     */
    private static UserAccount connectedUser;

    /**
     * User girltrip
     */
    private static UserAccount girltrip;

    /**
     * User girltrip
     */
    private static UserAccount jeek;

    /**
     * A banned user
     */
    private static UserAccount bannedAccount;

    /**
     * A permanently banned account
     */
    private static UserAccount permanentlyBannedAccount;

    // --- CATEGORIES --- //

    /**
     * List of all categories
     */
    private static List<Category> myCategories = new ArrayList<>();

    /**
     * Category computing
     */
    private static Category Informatique;


    /**
     * Category peripheral device
     */
    private static Category Peripheriques;

    /**
     * Category keyboard
     */
    private static Category Clavier;

    /**
     * Category phone
     */
    private static Category Telephonie;

    /**
     * Category mouse
     */
    private static Category Souris;

    // --- PRODUCTS --- //

    /**
     * All products of the systel
     */
    private static List<Product> myProducts = new ArrayList<>();

    /**
     * A keyboard
     */
    private static Product k70Vengeance;

    /**
     * A smartphone
     */
    private static Product IPhoneX;

    /**
     * A vacuum
     */
    private static Product Dysonv7;

    // --- COMMENTS --- //

    /**
     * All comments of the system
     */
    private static List<Comment> myComments = new ArrayList<>();

    /**
     * All short comments of the system
     */
    private static List<ShortComment> myShortComments = new ArrayList<>();

    // **************************************************
    // Getters
    // **************************************************

    /**
     * myComments getter
     *
     * @return All comments of the system
     */
    public static List<Comment> getMyComments() {
        return myComments;
    }

    /**
     * myShortComments getter
     *
     * @return All short comments of the system
     */
    public static List<ShortComment> getMyShortComments() {
        return myShortComments;
    }

    /**
     * isConnected getter
     *
     * @return attribute isConnected
     */
    static boolean getIsConnected() {
        return isConnected;
    }

    /**
     * connectedUser getter
     *
     * @return attribute connectedUser
     */
    public static UserAccount getConnectedUser() {
        return connectedUser;
    }

    /**
     * myUsers getter
     *
     * @return attribute myUsers
     */
    static List<UserAccount> getMyUsers() {
        return myUsers;
    }

    /**
     * myCategories getter
     *
     * @return attribute myCategories
     */
    static List<Category> getMyCategories() {
        return myCategories;
    }

    /**
     * myProducts getter
     *
     * @return attribute myProducts
     */
    static List<Product> getMyProducts() {
        return myProducts;
    }

    // **************************************************
    // Setters
    // **************************************************

    /**
     * Set a user as the connected user
     *
     * @param user User to connect
     */
    static void setConnectedUser(UserAccount user) {
        connectedUser = user;
    }

    /**
     * isConnected setter
     *
     * @param value New value
     */
    static void setIsConnected(boolean value) {
        isConnected = value;
    }

    // **************************************************
    // Private methods
    // **************************************************

    /**
     * Inits all categories
     */
    private static void initCategories() {
        Informatique = new Category("Informatique", CategoryPicture.infoPic);
        Peripheriques = new Category("Périphériques", CategoryPicture.periPic);
        Clavier = new Category("Claviers", CategoryPicture.clavierPic);
        Telephonie = new Category("Téléphonie", CategoryPicture.telePic);
        Souris = new Category("Souris", CategoryPicture.periPic);

        Informatique.addUnderCategory(Peripheriques);

        Peripheriques.addUnderCategory(Clavier);
        Peripheriques.addUnderCategory(Souris);
    }

    /**
     * Init user girltrip
     */
    private static void initGirltrip() {
        girltrip = new UserAccount("girltrip", "girltrip", "Laura", "Glazowski", "girltrip@gmail.com");
        girltrip.setDescription("Je suis une personne plutôt curieuse qui aime la nature et qui aime voyager dans le monde entier. J'essaie donc souvent de nouvelles innovations afin de vous partager mes opinions très détaillées !");
        girltrip.setCoverPicture("fr/univ_amu/iut/Images/userBackground.jpg");
        girltrip.setPersonalPicture("fr/univ_amu/iut/Images/voyageuse.jpg");
        girltrip.setInstagram("girltrip");
        girltrip.setWebSiteLink("www.girltrip.blog.com");
        girltrip.setBadge(Badges.RELIABLE);
        girltrip.setLevel(58);
        for (int i = 0; i < 1025; ++i) {
            girltrip.addFollower();
        }

        myUsers.add(girltrip);
    }

    /**
     * Init user jeek
     */
    private static void initJeek() {
        jeek = new UserAccount("Jeek", "jeangeek", "Jean", "Dubois", "jeekdb@gmail.com");
        jeek.setDescription("Informaticien depuis un long moment, je suis passionné des nouvelles technologies et de l'informatique en général.");
        jeek.setCoverPicture("fr/univ_amu/iut/Images/jeekcover.jpg");
        jeek.setPersonalPicture("fr/univ_amu/iut/Images/jeekpersonal.jpg");
        jeek.setInstagram("jeek");
        jeek.setBadge(Badges.RELIABLE);
        jeek.setLevel(43);
        for (int i = 0; i < 695; ++i) {
            jeek.addFollower();
        }

        myUsers.add(jeek);
    }

    /**
     * Init banned user
     */
    private static void initBannedAccount() {
        bannedAccount = new UserAccount("toto", "toto", "toto", "toto", "toto@gmail.com");
        bannedAccount.setDescription("Je suis toto et je suis banni !");
        bannedAccount.banUser(70);
        bannedAccount.setLevel(3);
        bannedAccount.setBadge(Badges.NON_RELIABLE);
        for (int i = 0; i < 6; ++i) {
            bannedAccount.addFollower();
        }

        myUsers.add(bannedAccount);
    }

    /**
     * Init permanently banned user
     */
    private static void initPermanentlyBannedAccount() {
        permanentlyBannedAccount = new UserAccount("titi", "titi", "titi", "titi", "titi@gmail.com");
        permanentlyBannedAccount.setDescription("Je suis toto et je suis banni définitivement!");
        permanentlyBannedAccount.banUser(0);

        myUsers.add(permanentlyBannedAccount);
    }

    /**
     * Init all products
     */
    private static void initProducts() {
        IPhoneX = new Product("iPhone X", "Apple",
                "/fr/univ_amu/iut/Images/iphoneXpicture.png",
                "la 10ème génération de l'IPhone",
                initIPhoneTS(), Telephonie);

        k70Vengeance = new Product("K70 Vengeance", "Corsair", "/fr/univ_amu/iut/Images/corsairK70Vengeance.png", "Un clavier mécanique aux switchs Cherry MX Red de la marque Corsair, doté d'un prix d'environ 115€ ainsi que de nombreuses caractéristiques:\n" +
                "\n" +
                "- Châssis en aluminium\n" +
                "- Repose poignet ergonomique et détachable\n" +
                "- Touches entièrement mécaniques Cherry Red\n" +
                "- Touches multimédia\n" +
                "- Touches ZQSD et 1 à 6 remplaçables par des touches colorées et anti-dérapantes\n" +
                "- Port USB 2.0 pour la connexion d'un périphérique supplémentaire\n" +
                "- Switch pour changer la fréquence de fonctionnement du clavier (1000, 500 , 250 et 125 Hz)\n" +
                "- Installation simple et rapide des touches de rechange \n" +
                "- Rollover sur 20 touches\n" +
                "- Technologie anti-ghosting\n" +
                "- Frappe confortable et toucher agréable\n" +
                "- Touche Windows verrouillable\n" +
                "- Garantie constructeur 2 ans", initK70VengeanceTS(), Clavier);

        myProducts.add(IPhoneX);
        myProducts.add(k70Vengeance);
    }

    /**
     * Init the iphone technical sheet
     *
     * @return Iphone technical sheet
     */
    private static List<String> initIPhoneTS() {
        List<String> IPhoneTS = new ArrayList<>();
        IPhoneTS.add("System : IOS 11");
        IPhoneTS.add("Fréquence processeur : 2.4 GHz");
        IPhoneTS.add("RAM : 3 Go");
        IPhoneTS.add("Stockage : 64 Go");
        IPhoneTS.add("Taille (diagonale) : 5.8 &rdquo; ");
        IPhoneTS.add("Largeur : 7.09 cm ");
        IPhoneTS.add("Hauteur : 14.36 cm ");
        IPhoneTS.add("Epaisseur : 0.77 cm ");
        IPhoneTS.add("Poids : 174 g  ");
        return IPhoneTS;
    }

    /**
     * Init the K70Vengeance technical sheet
     *
     * @return K70Vengeance technical sheet
     */
    private static List<String> initK70VengeanceTS() {
        List<String> K70VengeanceTS = new ArrayList<>();
        K70VengeanceTS.add("https://images.anandtech.com/doci/8141/CVK70_25.JPG");
        return K70VengeanceTS;
    }

    /**
     * Init a comment for product K70Vengeance
     */
    private static void initCommentK70Vengeance() {
        List<String> mesPhotos = new ArrayList<>();
        mesPhotos.add("/fr/univ_amu/iut/Images/image_k70_veangeance.JPG");
        Comment k70VengeanceComment = new Comment("Bien mais cher", "Pour tout le temps que j'ai passé à utilisé ce clavier (3 ~ 4 ans), je pense pouvoir en deviner les qualités et défauts. Un clavier mécanique aux switchs rouges Cherry MX fabriqués par une marque réputée pour leur qualité de production. Intéréssant peut-on se dire. " +
                "Acheté au prix de 130€, je ne saurai lui trouver qu'un seul défaut, le bruit des touches, caractéristique d'un clavier mécanique. Sincerement, je pense que ce clavier ne représente pas le top de la qualité qu'on puisse obtenir sur le marché, que ce soit en terme de fonctionnalités ou bien en terme d'efficacité (même si mon avis peut être biaisé " +
                "par le désir d'acquérir d'autres claviers). Cependant, force est d'admettre qu'il a relativement bien tenu le coup jusque là. Je serais tout de même d'avis de dire que ce clavier n'est plus à même de se départager de la conccurence, sûrement d'aussi bonne voir de meilleure qualité, pour un prix réduit. " +
                "Pour conclure, ce clavier à été très satisfaisant mais je ne le recommenderai pas pour autant à moins d'avoir affaire à une réduction importante.",
                4,
                mesPhotos,
                girltrip,
                k70Vengeance);

        k70Vengeance.addComment(k70VengeanceComment);

        myComments.add(k70VengeanceComment);
        myShortComments.add(new ShortComment(k70VengeanceComment));
    }

    /**
     * Init a comment for product IPhoneX
     */
    private static void initCommentIPhoneX() {
        List<String> myIPhonePictures = new ArrayList<>();
        myIPhonePictures.add("/fr/univ_amu/iut/Images/image_iphoneX1.jpg");
        myIPhonePictures.add("/fr/univ_amu/iut/Images/image_iphoneX2.jpg");
        Comment IPhoneXComment = new Comment("Une merveille",
                "Ayant eu auparavant la chance de pouvoir essayer de nombreux smartphones toute marque confondue," +
                        " que ce soit chez Samsung, Huawei, Apple, etc... Je n'avais jamais encore vécu une sensation aussi" +
                        " excellente que celle que j'ai pu obtenir au vu des performances époustouflante qu'un simple smart" +
                        "phone peut délivrer. Que ce soit au niveau des jeux vidéos, il n'y a jamais un lag, au niveau des " +
                        "réseaux sociaux, tout est fluide comme pour le reste. L'appareil photo permet de prendre de très b" +
                        "elles photos et vidéos grâce à son double capteur, et son écran large, quasiment sans bordures off" +
                        "re une large surface pour regarder ses séries préférées sans problème. Le point positif majeur est " +
                        "quand même l'autonomie de la batterie qui est bien meilleure que sur les versions précédentes grâc" +
                        "e à son écran OLED, et pour ce qui est du point négatif je dirais l'encoche située en haut de l'éc" +
                        "ran qui est à mon goût plutôt gênante mais l'on fini rapidement par l'oublier au fur et à mesure du temps.",
                5d,
                myIPhonePictures,
                jeek,
                IPhoneX);

        IPhoneX.addComment(IPhoneXComment);

        myComments.add(IPhoneXComment);
        myShortComments.add(new ShortComment(IPhoneXComment));
    }

    // **************************************************
    // Package private methods
    // **************************************************

    /**
     * Display existing categories
     *
     * @param page Page on which to display the categories
     */
    static void addContentRechercher(RechercherControl page) {
        page.displayCategories();
    }

    /**
     * Add products to display in selection page
     *
     * @param page Page on which to display the products
     */
    static void addContentSelection(SelectionControl page) {
        page.addSelectedComment(new SelectedComment(IPhoneX.getMyComments().get(0), "linear-gradient(#31F280, #223E61)", "/fr/univ_amu/iut/Images/iPhoneX.png"));
        page.addSelectedComment(new SelectedComment(k70Vengeance.getMyComments().get(0), "linear-gradient(#400007, #AE121C)", "/fr/univ_amu/iut/Images/k70vengeance.png"));
    }

    /**
     * Add a user to the database
     *
     * @param user User to add
     */
    static void addUserAccount(UserAccount user) {
        myUsers.add(user);
    }

    /**
     * Add a comment to the database
     *
     * @param comment Comment to add
     */
    static void addComment(Comment comment) {
        myComments.add(comment);
        comment.getMyProduct().addComment(comment);
    }

    /**
     * Creates the profile of the connected user
     *
     * @param page
     */
    static void addConnectedProfil(ProfilControl page) {
        page.setProfil(connectedUser);
    }
}
