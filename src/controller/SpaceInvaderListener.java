package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Constants;
import model.InGameModel;

/**
 * Handles all events for pressed and released keys.
 * Implements event handler.
 *
 * @author Isabelle Romhagen, Ludvig Lundin, Mattias Frigren, Jasmine Söderberg
 * @version 1.2
 */
public class SpaceInvaderListener implements EventHandler<KeyEvent> {
    private static SpaceInvaderListener listener;


    public static SpaceInvaderListener getListener() {
        if (listener == null) {
            listener = new SpaceInvaderListener();
        }
        return listener;
    }

    private SpaceInvaderListener() {
    }

    /**
     * Sets pressed key to true to start movement/shooting or to activate ult/speeding.
     * Sets released key to false to stop movement/shooting or to deactivate ult/speeding.
     *
     * @param event
     */
    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            if (event.getCode() == KeyCode.SPACE) {
                SpaceInvaderController.getController().setShooting(true);
                System.out.println("pressed space");
            }
            if (event.getCode() == KeyCode.A){
                SpaceInvaderController.getController().setMovingLeft(true);
            }
            if (event.getCode() == KeyCode.D){
                SpaceInvaderController.getController().setMovingRight(true);
            }
            if (event.getCode() == KeyCode.W){
                SpaceInvaderController.getController().setMovingUp(true);
            }
            if (event.getCode() == KeyCode.S){
                SpaceInvaderController.getController().setMovingDown(true);
            }
            if (event.getCode() ==KeyCode.SHIFT)
                InGameModel.getGameModel().getPlayerModel().setMovementSpeed(Constants.SCREEN_HEIGHT *0.016);
            if (event.getCode() == KeyCode.ESCAPE) {
                SpaceInvaderController.getController().pauseGame();
            }
            if (event.getCode() == KeyCode.X) {
                SpaceInvaderController.getController().setUltIsPressed(true);
            }
        }
        else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
            if (event.getCode() == KeyCode.SPACE) {
                SpaceInvaderController.getController().setShooting(false);
            }
            if (event.getCode() == KeyCode.A){
                SpaceInvaderController.getController().setMovingLeft(false);
            }
            if (event.getCode() == KeyCode.D){
                SpaceInvaderController.getController().setMovingRight(false);
            }
            if (event.getCode() == KeyCode.W){
                SpaceInvaderController.getController().setMovingUp(false);
            }
            if (event.getCode() == KeyCode.S){
                SpaceInvaderController.getController().setMovingDown(false);
            }
            if (event.getCode() == KeyCode.SHIFT) {
                InGameModel.getGameModel().getPlayerModel().setMovementSpeed(Constants.PLAYER_SHIP_MOVEMENT_SPEED);
            }
            if (event.getCode() == KeyCode.X) {
                SpaceInvaderController.getController().setUltIsPressed(false);
            }
            //TODO add all released events.
        }


    }


}

