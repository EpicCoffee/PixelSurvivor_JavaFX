package model;

/**
 * This class defines what a player ship should contain.
 *
 * @author Isabelle Romhagen, Ludvig Lundin, Mattias Frigren, Jasmine Söderberg, Khazar Mehraban
 * @version 1.2
 */

public class PlayerShip extends Ship {

    int ultCounter = 0;

    /**
     * The constructor gets the image, X and Y starting position, height, width,
     * speed, direction and initial life count from the Constants class.
     * Creates a Laser gun and gets direction and interval.
     */
    public PlayerShip() {

        super(Constants.playerShipURL, Constants.playerShipStartPosX, Constants.playerShipStartPosY, Constants.playerShipHeight, Constants.playerShipWidth, Constants.playerShipMovementSpeed, false, Constants.playerLifes);

        setWeapons(new LaserGun(false, (int)Constants.laserBulletShootInterval, this));
    }

    public int getUltCounter() {
        return ultCounter;
    }

    public boolean IsUltReady() {
        return ultCounter >= Constants.ultReadyAt;
    }

    public void addToUltCounter() {
        this.ultCounter++;
    }

    public void resetUltCounter() {
        ultCounter = 0;
    }
}
