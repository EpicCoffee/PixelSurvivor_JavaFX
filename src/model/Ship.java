package model;

/**
 * This class defines what a ship of any kind should contain.
 * It is a child of OnScreenItems.
 *
 * @author Isabelle Romhagen, Ludvig Lundin, Mattias Frigren, Khazar Mehraban
 * @version 1.2
 */
public class Ship extends OnScreenItems{

    protected Weapons weapon;
    private int lifes;

    /**
     * Constructor defines that all ships will have:
     * @param imageURL an image
     * @param itemCoordX a X position
     * @param itemCoordY a Y position
     * @param itemHeight a height
     * @param itemWidth a width
     * @param movementSpeed a speed
     * @param isFacingPlayer a direction
     * @param lifes a life count
     */
    public Ship(String imageURL, double itemCoordX, double itemCoordY, double itemHeight, double itemWidth, double movementSpeed, boolean isFacingPlayer, int lifes) {
        super(imageURL,itemCoordX, itemCoordY, itemHeight, itemWidth, movementSpeed, isFacingPlayer);
        this.lifes = lifes;
    }

    /**
     * Getter for current life count.
     * @return lifes how many lives are left.
     */
    public int getLifes() {
        return lifes;
    }

    /**
     * Setter for life count.
     * @param lifes life count.
     */
    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    /**
     * Reduces remaining lives.
     * @param lifes life count.
     */
    public void looseLife(int lifes) {
        this.lifes -= lifes;
    }

    /**
     * Setter for weapons.
     * @param weapon weapon.
     */
    public void setWeapons(Weapons weapon) {
        this.weapon = weapon;
    }

    /**
     * Getter for Weapon.
     * @return weapon.
     */
    public Weapons getWeapon() {
        return weapon;
    }

    /**
     * Null check, calls shoot method it there's a weapon.
     * @return null
     */
    public IBullet performShootingAction() {
        if (weapon != null) {

            return weapon.shoot();
        }
        return null;
    }

}
