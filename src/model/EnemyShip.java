package model;

/**
 * This class defines what an enemy ship of any kind should contain.
 * It is a child of Ship.
 *
 * @author Mattias Frigren, Khazar Mehraban
 * @version 1.2
 */

public class EnemyShip extends Ship{

    private boolean moveStateUpDown =true;
    private boolean moveStateRightLeft = true;

    /**
     * Defines that all an enemy ships should have:
     * @param imageURL image
     * @param itemCoordX x position
     * @param itemCoordY y position
     * @param itemHeight height
     * @param itemWidth width
     * @param movementSpeed speed
     * @param isFacingPlayer direction
     * @param lifes life count
     */
    public EnemyShip(String imageURL, double itemCoordX, double itemCoordY, double itemHeight, double itemWidth, double movementSpeed, boolean isFacingPlayer, int lifes){
            super(imageURL,itemCoordX, itemCoordY, itemHeight, itemWidth, movementSpeed, isFacingPlayer,lifes);

    }

    /**
     * @return moveStateUpDown
     */
    public boolean isMoveStateUpDown() {
        return moveStateUpDown;
    }

    /**
     *
     * @param moveStateUpDown defines if the unit should move up/down on the screen.
     */
    public void setMoveStateUpDown(boolean moveStateUpDown) {
        this.moveStateUpDown = moveStateUpDown;
    }

    /**
     *
     * @return moveStateRightLeft defines if the unit should move right/left on the screen.
     */
    public boolean isMoveStateRightLeft() {
        return moveStateRightLeft;
    }

    /**
     * @param moveStateRightLeft setter for moveStateRightLeft.
     */
    public void setMoveStateRightLeft(boolean moveStateRightLeft) {
        this.moveStateRightLeft = moveStateRightLeft;
    }

}