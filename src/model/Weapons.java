package model;

/**
 * This class declares weapon variables.
 *
 * @author Ludvig Lundin
 * @version 1.2
 */


public abstract class Weapons {

    protected boolean isFacingPlayer;
    protected int readyToShoot;
    protected int shootInterval;
    protected Ship theShip;

    public abstract IBullet shoot();

    /**
     * Checks if it's time to shoot again.
     */
    public void addToReadyToShoot() {
        if (readyToShoot < shootInterval) {
            readyToShoot++;
        }
    }


}
