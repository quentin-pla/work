package fr.univ_amu.iut;

/**
 * Goals that users can fullfill in order to obtain experience points and raise their level
 */
public class Mission {

    /**
     * Default constructor
     *
     * @param name Name given to the Mission
     * @param xp   Experience points the user get by achieving the Mission
     */
    public Mission(String name, int xp) {
        id = ++lastId;
        this.name = name;
        this.xp = xp;
    }

    // **************************************************
    // Fields
    // **************************************************

    /**
     * Last ID given to a Mission
     */
    private static int lastId = 0;

    /**
     * ID of the Mission
     */
    private int id;

    /**
     * Name given to the Mission
     */
    private String name;

    /**
     * Experience points the user get by achieving the Mission
     */
    private int xp;

}