package controller;

import javafx.stage.Stage;
import model.*;
import view.SpaceInvaderInGameView;
import view.ViewManager;
import java.util.ArrayList;
import java.util.Random;

/**
 * Controller that talks with View and Model, changes the state of the game.
 *
 * @author Isabelle Romhagen, Ludvig Lundin, Mattias Frigren, Jasmine Söderberg, Khazar Mehraban
 * @version 1.2
 */
public class SpaceInvaderController {

    private static SpaceInvaderController controller;
    private static SpaceInvaderListener listener;
    private static ViewManager view;
    private static InGameModel gameModel;

    private boolean soundOn = true;

    private boolean gamePaused = false;

    private boolean isShooting = false;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean ultIsPressed = false;
    int spawnWave = 0;

    /////////************** Getter and setters ***********************

    public static SpaceInvaderController getController(Stage stage) {
        if (controller == null) {
            controller = new SpaceInvaderController(stage);
        }
        return controller;
    }

    public static SpaceInvaderController getController() {
        return controller;
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }

    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }

    public boolean isMovingLeft() {
        return isMovingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        isMovingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return isMovingRight;
    }

    public void setMovingRight(boolean movingRight) {
        isMovingRight = movingRight;
    }

    public boolean isMovingUp() {
        return isMovingUp;
    }

    public void setMovingUp(boolean movingUp) {
        isMovingUp = movingUp;
    }

    public boolean isMovingDown() {
        return isMovingDown;
    }

    public void setMovingDown(boolean movingDown) {
        isMovingDown = movingDown;
    }

    public boolean isUltIsPressed() {
        return ultIsPressed;
    }

    public void setUltIsPressed(boolean ultIsPressed) {
        this.ultIsPressed = ultIsPressed;
    }

    /////////************** End of Getter and setters ***********************

    /**
     *
     * @param stage
     */
    private SpaceInvaderController(Stage stage) {
        this.gameModel = InGameModel.getGameModel();
        this.view = ViewManager.getViewManager(stage);
        listener = SpaceInvaderListener.getListener();
    }

    public void resetController() {
        isShooting = false;
        isMovingLeft = false;
        isMovingRight = false;
        isMovingUp = false;
        isMovingDown = false;
        ultIsPressed = false;
        spawnWave = 0;
    }

    public void pauseGame() {
        gamePaused = gamePaused ? false : true;
        SpaceInvaderInGameView.getGameView().setAnimationTimer(gamePaused);
    }

    public ArrayList<EnemyShip> checkWhatToSpawn() {

        ArrayList<EnemyShip> combinedEnemies;
        //int[] spawnPoints = {1, 6, 16, 36, 37, 56, 76, 77, 116, 156, 216};
        int currentLvl = gameModel.getLevel();

        if (gameModel.getEnemyModelList().isEmpty()) {
            switch (spawnWave) {
                case 0:
                    spawnWave++;
                    return spawnEnemies(5 * currentLvl, "default");
                case 1:
                    spawnWave++;
                    spawnMeteor();
                    return spawnEnemies(10 * currentLvl, "default");
                case 2:
                    spawnWave++;
                    spawnMeteor();
                    return spawnEnemies(20 * currentLvl, "default");
                case 3:
                    spawnWave++;
                    spawnMeteor();
                    combinedEnemies = spawnEnemies(10 * currentLvl, "default");
                    combinedEnemies.addAll(spawnEnemies(5 * currentLvl, "drone"));
                    return combinedEnemies;
                case 4:
                    spawnWave++;
                    spawnMeteor();
                    return spawnEnemies(20 * currentLvl, "drone");
                case 5:
                    spawnWave++;
                    spawnMeteor();
                    combinedEnemies = spawnEnemies(20 * currentLvl, "drone");
                    combinedEnemies.addAll(spawnEnemies(20 * currentLvl, "default"));
                    return combinedEnemies;
                case 6:
                    spawnWave++;
                    spawnMeteor();
                    return spawnEnemies(40 * currentLvl, "drone");
                case 7:
                    spawnWave++;
                    combinedEnemies = spawnEnemies(60 * currentLvl, "drone");
                    combinedEnemies.addAll(spawnEnemies(30 * currentLvl, "default"));
                    spawnMeteor();
                    return combinedEnemies;
                case 8:
                    spawnWave = 0;
                    gameModel.addLevel();
                    return spawnEnemies(1 * currentLvl, "boss");
            }
        }
        return null;
    }


    private void spawnMeteor() {
        gameModel.setModelMeteor(new Meteor());
        gameModel.getModelMeteor().setItemCoordX((Math.random() * (Constants.SCREENWIDTH - 150)) + 50 );
        gameModel.getModelMeteor().setItemCoordY((Math.random() * -600) - 200);
        SpaceInvaderInGameView.getGameView().initializeMeteor();
    }

    private ArrayList<EnemyShip> spawnEnemies(int amount, String enemy) {
        ArrayList<EnemyShip> enemyModelsToView = new ArrayList<>();
        EnemyShip enemyShip = null;

        if (enemy.equals("default")) {
            enemyShip = new DefaultEnemy();
        }
        if (enemy.equals("drone")) {
            enemyShip = new EnemyDroneShip();
        }
        if (enemy.equals("boss")) {
            enemyShip = new EnemyBigBoss();
        }

        double startXPos = Constants.SCREENWIDTH/2 - (enemyShip.getItemWidth()/2);
        boolean toRight = true;
        double currentYPos = -50;
        double currentXPos = startXPos;
        double currentDistanceFromCenter = 0;

        for (int i = 0; i < amount; i++) {

            if (enemy.equals("default")) {
                enemyShip = new DefaultEnemy();
            }
            if (enemy.equals("drone")) {
                enemyShip = new EnemyDroneShip();
            }
            if (enemy.equals("boss")){
                enemyShip = new EnemyBigBoss();
            }

            if (i%10 == 0) {
                currentDistanceFromCenter = 0;
                currentXPos = startXPos;
                currentYPos -= 100;
                toRight = true;
            }
            else {
                if (toRight) {
                    currentDistanceFromCenter += Constants.enemySpawnSpread;
                    currentXPos = startXPos + currentDistanceFromCenter;
                    toRight = false;
                    enemyShip.setMoveStateRightLeft(false);
                }
                else {
                    currentXPos = startXPos - currentDistanceFromCenter;
                    toRight = true;
                    enemyShip.setMoveStateRightLeft(true);
                }
            }
            enemyShip.setItemCoordX(currentXPos + (new Random().nextInt(20)));
            enemyShip.setItemCoordY(currentYPos + (new Random().nextInt(20)));

            enemyModelsToView.add(enemyShip);
            gameModel.addEnemyModel(enemyShip);
        }

        return enemyModelsToView;
    }

    public void createHpUpHeart() {
        SoundEffects.playSound(Constants.POWERUPSOUNDURL);
        gameModel.setHeartHpUp(new HpUp());
        gameModel.getHeartHpUp().setItemCoordY(gameModel.getModelMeteor().getItemCoordY());
        gameModel.getHeartHpUp().setItemCoordX(gameModel.getModelMeteor().getItemCoordX());
        SpaceInvaderInGameView.getGameView().initializeHpUpHeart();
    }
    public void moveHpUpHeart() {
        if (gameModel.getHeartHpUp()!=null) {
            gameModel.getHeartHpUp().moveUp();

            if (checkIfOutOfScreen(gameModel.getHeartHpUp().getItemCoordX(), gameModel.getHeartHpUp().getItemCoordY())) {
                gameModel.setHeartHpUp(null);
             }
            else if (gameModel.getPlayerModel().getItemWidth() / 5 + gameModel.getHeartHpUp().getItemWidth() / 5 > distanceBetween(gameModel.getHeartHpUp(), gameModel.getPlayerModel())) {
                if (gameModel.getPlayerModel().getLifes() < 3) {
                    SoundEffects.playSound(Constants.POWERUPSOUNDURL);
                    gameModel.getPlayerModel().setLifes(gameModel.getPlayerModel().getLifes() + 1);
                }
                gameModel.setHeartHpUp(null);
            }
        }
    }

    public void moveMeteorModel() {
        if (gameModel.getModelMeteor() !=null) {
            gameModel.getModelMeteor().moveUp();
            if (gameModel.getModelMeteor().getItemCoordY() >= Constants.SCREENHEIGHT+300) {
                gameModel.setModelMeteor(null);
                System.out.println("meteor removed");
            }
        }
    }

    public void checkIfEnemyIsmoving() {
        ArrayList<EnemyShip> enemyShips = gameModel.getEnemyModelList();
        for (int i = 0; i < enemyShips.size() ; i++) {

            if (enemyShips.get(i).getItemCoordY() <= Constants.SCREENHEIGHT / 2 && enemyShips.get(i).isMoveStateUpDown()==true){
                enemyShips.get(i).moveUp();
                if (enemyShips.get(i).getItemCoordY() >= Constants.SCREENHEIGHT/2) {
                    enemyShips.get(i).setMoveStateUpDown(false);
                }
            }
            if (enemyShips.get(i).getItemCoordY() < Constants.SCREENHEIGHT && enemyShips.get(i).isMoveStateUpDown() ==false) {
                enemyShips.get(i).moveDown();
                if (enemyShips.get(i).getItemCoordY() <= 0) {
                    enemyShips.get(i).setMoveStateUpDown(true);
                }
            }
            if (enemyShips.get(i).getItemCoordX() <= (Constants.SCREENWIDTH-30) && enemyShips.get(i).isMoveStateRightLeft()==true){
                enemyShips.get(i).moveLeft();
                if (enemyShips.get(i).getItemCoordX() >= Constants.SCREENWIDTH-30) {
                    enemyShips.get(i).setMoveStateRightLeft(false);
                }
            }
            if (enemyShips.get(i).getItemCoordX() < Constants.SCREENWIDTH  && enemyShips.get(i).isMoveStateRightLeft()==false){
                enemyShips.get(i).moveRight();
                if (enemyShips.get(i).getItemCoordX() <=0) {
                    enemyShips.get(i).setMoveStateRightLeft(true);
                }
            }

        }
    }

    public ArrayList<IBullet> checkIfEnemyIsShooting() {
        ArrayList<IBullet> enemyModelBullets = new ArrayList<>();
        for (EnemyShip enemyModel : gameModel.getEnemyModelList()) {
            IBullet theEnemyBullet = enemyModel.performShootingAction();
            if (theEnemyBullet != null) {
                enemyModelBullets.add(theEnemyBullet);
                gameModel.addBullets(theEnemyBullet);
            }
        }
        return enemyModelBullets;
    }

    /**
     * When space is down check, if the weapon manages to shoot.
     * @return current bullet
     */
    public IBullet checkIfPlayerIsShooting() {
        if (isShooting) {
            IBullet currentBullet = gameModel.getPlayerModel().performShootingAction();
            new SoundEffects().playSound(Constants.LASERSOUNDURL1);//PlayerShoot soundEffect.KM
            if (currentBullet != null) {
                gameModel.addBullets(currentBullet);
                System.out.println("bullet added to list");
                return currentBullet;
            }
        }
        return null;
    }


    public boolean checkIfPlayerIsUlting() {
        if (ultIsPressed && gameModel.getPlayerModel().IsUltReady()) {
            ultActivated();
            new SoundEffects().playSound(Constants.ultSoundUrl);
            return true;
        }
        return false;
    }
    private void checkIfPlayerIsMovingLeft() {
        if (isMovingLeft && gameModel.getPlayerModel().getItemCoordX() > 0) {
            gameModel.getPlayerModel().moveLeft();
        }
    }

    private void checkIfPlayerIsMovingRight() {
        if (isMovingRight && gameModel.getPlayerModel().getItemCoordX() < Constants.SCREENWIDTH - Constants.playerShipWidth) {
            gameModel.getPlayerModel().moveRight();
        }
    }

    private void checkIfPlayerIsMovingUp() {
        if (isMovingUp && gameModel.getPlayerModel().getItemCoordY() > (Constants.SCREENHEIGHT / 2)+(gameModel.getPlayerModel().getItemHeight()/2)) {
            gameModel.getPlayerModel().moveUp();
        }
    }

    private void checkIfPlayIsMovingDown() {
        if (isMovingDown && gameModel.getPlayerModel().getItemCoordY() < (Constants.SCREENHEIGHT * 0.92) - Constants.playerShipHeight) {
            gameModel.getPlayerModel().moveDown();
        }
    }

    public void updatePlayerMovement() {
        checkIfPlayerIsMovingLeft();
        checkIfPlayerIsMovingRight();
        checkIfPlayerIsMovingUp();
        checkIfPlayIsMovingDown();
    }

    /**
     * Moving all bullets forward
     */
    public void updateBullets() {
        for (IBullet bullet : gameModel.getBulletsModelList()) {
            OnScreenItems itemBullet = (OnScreenItems) bullet;
            itemBullet.moveUp();
        }
    }

    /**
     * check if something is out of screen
     * @param x x position
     * @param y y position
     * @return y
     */
    private boolean checkIfOutOfScreen(double x, double y) {
        return y > Constants.SCREENHEIGHT + 50 || x > Constants.SCREENWIDTH + 50 || y < -50 || x < -50;
    }

    private boolean checkIfOutOfScreen(double x, double y, double width) {
        return y > Constants.SCREENHEIGHT + width || x > Constants.SCREENWIDTH + width || y < -width || x < -width;
    }

    public ArrayList<IBullet> checkIfMeteorShoot() {
        if (gameModel.getModelMeteor()!=null) {
            ArrayList<IBullet> bulletsToRemove = new ArrayList<>();
            if (!gameModel.getBulletsModelList().isEmpty()) {
                for (int j = 0; j < gameModel.getBulletsModelList().size(); j++) {
                    OnScreenItems bulletsToRemoveNow = (OnScreenItems) gameModel.getBulletsModelList().get(j);
                    if (!bulletsToRemoveNow.isFacingPlayer()) {
                        if (gameModel.getModelMeteor() != null) {
                            if (gameModel.getModelMeteor().getItemWidth() / 5 + bulletsToRemoveNow.getItemWidth() / 5 > distanceBetween(bulletsToRemoveNow, gameModel.getModelMeteor())) {
                                createHpUpHeart();
                                gameModel.setModelMeteor(null);
                                bulletsToRemove.add((IBullet) bulletsToRemoveNow);
                            }
                        }
                    }
                }
            }
            return bulletsToRemove;
        }
        return null;
    }

    /**
     * Checks if meteor connects with the player ship.
     */
    public void checkIfMeteorCollide() {
        if (gameModel.getModelMeteor()!=null) {
            if (gameModel.getPlayerModel().getItemWidth() / 5 + gameModel.getModelMeteor().getItemWidth() / 5 > distanceBetween(gameModel.getModelMeteor(), gameModel.getPlayerModel())) {
                gameModel.getPlayerModel().looseLife(1);
                gameModel.setModelMeteor(null);
            }
        }
    }

    /**
     *  Checking if bullet is out of screen and return the index of the bullets that needs to be removed in our image view list.
     * @return bulletsToRemove
     */
    public ArrayList<IBullet> getBulletRemoveList() {
        ArrayList<IBullet> bulletsToRemove = new ArrayList<>();
        for (int i = 0; i < gameModel.getBulletsModelList().size(); i++) {
            OnScreenItems itemBullet = (OnScreenItems) gameModel.getBulletsModelList().get(i);
            if (checkIfOutOfScreen(itemBullet.getItemCoordX(), itemBullet.getItemCoordY())) {
                bulletsToRemove.add(gameModel.getBulletsModelList().get(i));
                continue;
            }
            // checking if bullet collided.
            if (itemBullet.isFacingPlayer()) {
                if (gameModel.getPlayerModel().getItemWidth() / 5 + itemBullet.getItemWidth() / 5 > distanceBetween(itemBullet, gameModel.getPlayerModel())) {
                    gameModel.getPlayerModel().looseLife(1);
                    bulletsToRemove.add(gameModel.getBulletsModelList().get(i));
                    continue;
                }
            } else {
                for (EnemyShip enemy : gameModel.getEnemyModelList()) {
                    if (enemy.getItemWidth() / 5 + itemBullet.getItemWidth() / 5 > distanceBetween(itemBullet, enemy)) {
                        enemy.looseLife(1);
                        bulletsToRemove.add(gameModel.getBulletsModelList().get(i));
                        break;
                    }
                }
            }

        }


        return bulletsToRemove;
    }

    public ArrayList<EnemyShip> getDeadEnemies() {
        ArrayList<EnemyShip> deadEnemyList = new ArrayList<>();

        for (EnemyShip enemy : gameModel.getEnemyModelList()) {
            if (enemy.getLifes() < 1) {
                deadEnemyList.add(enemy);
                gameModel.addPoints(1);
                if (!ultIsPressed) {
                    gameModel.getPlayerModel().addToUltCounter();
                }
                new SoundEffects().playSound(Constants.enemyExplosion); //Enemy deadSouncEffect.KM
            }
        }
        return deadEnemyList;
    }

    /**
     * adds +1 to weaponState to make it ready when at it's state.
     */
    public void updateWeaponsState() {
        gameModel.getPlayerModel().getWeapon().addToReadyToShoot();
        for (EnemyShip enemyShip : gameModel.getEnemyModelList()) {
            enemyShip.getWeapon().addToReadyToShoot();
        }
    }

    private void ultActivated() {
        for (EnemyShip enemyShip : gameModel.getEnemyModelList()) {
            enemyShip.looseLife(Constants.ultiDamage);
        }
        gameModel.getPlayerModel().resetUltCounter();
    }

    //Doing pythagoras rate to check distance between positions together with height and width of the objects..
    private double distanceBetween(double x1, double y1, double x2, double y2, double height1, double height2, double width1, double width2) {
        return Math.sqrt(Math.pow((x1 + (width1 / 2)) - (x2 + (width2 / 2)), 2) + Math.pow((y1 + (height1 / 2)) - (y2 + (height2 / 2)), 2));
    }

    private double distanceBetween(OnScreenItems firstObjc, OnScreenItems secondObjc) {
        return Math.sqrt(Math.pow((firstObjc.getItemCoordX() + (firstObjc.getItemWidth() / 2)) - (secondObjc.getItemCoordX() + (secondObjc.getItemWidth() / 2)), 2) + Math.pow((firstObjc.getItemCoordY() + (firstObjc.getItemHeight() / 2)) - (secondObjc.getItemCoordY() + (secondObjc.getItemHeight() / 2)), 2));
    }



}


