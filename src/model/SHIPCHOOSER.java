package model;

/**
 * This class handles the ship options.
 *
 * @author Ludvig Lundin
 * @version 1.2
 */

public enum SHIPCHOOSER {

    GREY(Constants.PLAYER_SHIP_URL),
    GREEN(Constants.GREEN_PLAYER_SHIP_URL),
    BLUE(Constants.BLUE_PLAYER_SHIP_URL),
    RED(Constants.RED_PLAYER_SHIP_URL);

    private String urlShip;

    private SHIPCHOOSER(String urlShip) {

        this.urlShip = urlShip;
    }

    public String getURL() {
        return this.urlShip;
    }

}
