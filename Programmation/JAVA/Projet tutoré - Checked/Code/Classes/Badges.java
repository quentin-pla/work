package fr.univ_amu.iut;

/**
 * All types of badge
 */
public enum Badges {
    /**
     * Badge for reliable users
     */
    RELIABLE("M256 48C141.1 48 48 141.1 48 256s93.1 208 208 208 208-93.1 208-208S370.9 48 256 48zm106.5 150.5L228.8 332.8h-.1c-1.7 1.7-6.3 5.5-11.6 5.5-3.8 0-8.1-2.1-11.7-5.7l-56-56c-1.6-1.6-1.6-4.1 0-5.7l17.8-17.8c.8-.8 1.8-1.2 2.8-1.2 1 0 2 .4 2.8 1.2l44.4 44.4 122-122.9c.8-.8 1.8-1.2 2.8-1.2 1.1 0 2.1.4 2.8 1.2l17.5 18.1c1.8 1.7 1.8 4.2.2 5.8z",
            /* color : */ "#00cc00" /* = green */),

    /**
     * Badge for non reliable users
     */
    NON_RELIABLE("M256 48C141.1 48 48 141.1 48 256s93.1 208 208 208 208-93.1 208-208S370.9 48 256 48zm52.7 283.3L256 278.6l-52.7 52.7c-6.2 6.2-16.4 6.2-22.6 0-3.1-3.1-4.7-7.2-4.7-11.3 0-4.1 1.6-8.2 4.7-11.3l52.7-52.7-52.7-52.7c-3.1-3.1-4.7-7.2-4.7-11.3 0-4.1 1.6-8.2 4.7-11.3 6.2-6.2 16.4-6.2 22.6 0l52.7 52.7 52.7-52.7c6.2-6.2 16.4-6.2 22.6 0 6.2 6.2 6.2 16.4 0 22.6L278.6 256l52.7 52.7c6.2 6.2 6.2 16.4 0 22.6-6.2 6.3-16.4 6.3-22.6 0z",
            /* color : */ "red"),

    /**
     * Default badge
     */
    DEFAULT("",
            /* color : */ "#a5a5a5" /* = grey */);

    /**
     * String to init the svg picture of the badge
     */
    private String SVGPicture;

    /**
     * Color of the badge
     */
    private String color;

    /**
     * Default constructor
     *
     * @param SVGPicture String to init the svg picture of the badge
     * @param color      Color of the badge
     */
    Badges(String SVGPicture, String color) {
        this.SVGPicture = SVGPicture;
        this.color = color;
    }

    /**
     * SVGPicture getter
     *
     * @return attribute SVGPicture
     */
    public String getSVGPicture() {
        return SVGPicture;
    }

    /**
     * color getter
     *
     * @return attribute color
     */
    public String getColor() {
        return color;
    }
}