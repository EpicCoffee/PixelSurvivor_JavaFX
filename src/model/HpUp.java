package model;

/**
 * This class handles the heart power up.
 * It is a child of OnScreenItems.
 *
 * @author Mattias Frigren
 * @version 1.2
 */

public class HpUp extends OnScreenItems {

    /**
     * Constructor gets image, starting position X and Y, height, width, speed and direction from Constants class.
     */
    public HpUp() {
        super(Constants.HEART_URL,Constants.HEART_START_X,Constants.HEART_START_Y,
                Constants.HEART_HEIGHT,Constants.HEART_WIDTH,Constants.HEART_MOVEMENT_SPEED,true);
    }
}
