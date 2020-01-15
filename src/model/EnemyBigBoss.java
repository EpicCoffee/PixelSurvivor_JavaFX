package model;

/**
 * This class defines the big boss weapon type and shooting interval.
 * It is a child of EnemyShip.
 *
 * @author Mattias Frigren
 * @version 1.2
 */

public class EnemyBigBoss extends EnemyShip {

    /**
     * The constructor gets the image, X and Y starting position, height, width,
     * speed, direction and initial life count from the Constants class.
     * Creates a Laser gun and gets direction and interval.
     */
    public EnemyBigBoss() {
        super(Constants.ENEMY_BIG_BOSS_URL,Constants.ENEMY_BIG_BOSS_START_POS_X, Constants.ENEMY_BIG_BOSS_START_POS_Y,
                Constants.ENEMY_BIG_BOSS_HEIGHT,Constants.ENEMY_BIG_BOSS_WIDTH,Constants.ENEMY_BIG_BOSS_MOVEMENT_SPEED, true,Constants.ENEMY_BIG_BOSS_LIVES);
        setWeapons(new BossWeapon(true, (int)Constants.ENEMY_BIG_BOSS_SHOOTING_INTERVAL,this));

    }

}
