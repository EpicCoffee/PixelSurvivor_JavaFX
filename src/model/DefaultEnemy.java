package model;

/**
 * This class defines the default enemies weapon type and shooting interval.
 * It is a child of EnemyShip.
 *
 * @author Mattias Frigren
 * @version 1.2
 */

public class DefaultEnemy extends EnemyShip {
    /**
     * The constructor gets the image, X and Y starting position, height, width,
     * speed, direction and initial life count from the Constants class.
     * Creates a Laser gun and gets direction and interval.
     */
    public DefaultEnemy() {
        super(Constants.ENEMY_SHIP_URL,Constants.ENEMY_SHIP_START_POS_X,Constants.ENEMY_SHIP_START_POS_Y,
                Constants.ENEMY_SHIP_HEIGHT,Constants.ENEMY_SHIP_WIDTH,Constants.ENEMY_SHIP_MOVEMENT_SPEED,true, Constants.ENEMY_LIVES);

        setWeapons(new LaserGun(true, (int)Constants.ENEMY_LASER_GUN_SHOOTING_INTERVAL,this));

    }
}
