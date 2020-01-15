package model;

/**
 * This class defines what a player ship should contain.
 *
 * @author Ludvig Lundin
 * @version 1.2
 */

public class PlayerShip extends Ship {

    private int ultCounter = 0;
    private boolean hasBossWeapon = false;

    /**
     * The constructor gets the image, X and Y starting position, height, width,
     * speed, direction and initial life count from the Constants class.
     * Creates a Laser gun and gets direction and interval.
     */
    public PlayerShip(String playerShipURL) {

        super(playerShipURL, Constants.PLAYER_SHIP_START_POS_X, Constants.PLAYER_SHIP_START_POS_Y, Constants.PLAYER_SHIP_HEIGHT, Constants.PLAYER_SHIP_WIDTH, Constants.PLAYER_SHIP_MOVEMENT_SPEED, false, Constants.PLAYER_LIVES);

        setWeapons(new LaserGun(false, (int)Constants.LASER_BULLET_SHOOT_INTERVAL, this));
    }

    public int getUltCounter() {
        return ultCounter;
    }

    public boolean IsUltReady() {
        return ultCounter >= Constants.ULT_READY_AT;
    }

    public void addToUltCounter() {
        this.ultCounter++;
    }

    public void resetUltCounter() {
        ultCounter = 0;
    }

    public void changeToBossWeapon() {
        if (!hasBossWeapon) {
            setWeapons(new BossWeapon(false, (int)Constants.LASER_BULLET_SHOOT_INTERVAL, this));
            hasBossWeapon = true;
        }
    }
}
