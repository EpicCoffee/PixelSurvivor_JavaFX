package model;

/**
 * This class creates meteors and sets its attributes.
 * Child of OnScreenItems.
 *
 * @author Mattias Frigren
 * @version 1.2
 */
public class Meteor extends OnScreenItems {

    public Meteor() {
        super(Constants.METEOR_IMAGE,Constants.SPAWN_POINT_METEOR,0,Constants.METEOR_HEIGHT,
                Constants.METEOR_WIDTH,Constants.METEOR_MOVEMENT_SPEED,true);
    }

}
