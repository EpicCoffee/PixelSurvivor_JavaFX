package model;

import java.util.ArrayList;

/**
 * This class handles all info on the models in the game.
 *
 * @author  Ludvig Lundin, Mattias Frigren
 * @version 1.2
 */
public class InGameModel {

    private static InGameModel gameModel;
    private PlayerShip playerModel;
    private ArrayList<IBullet> bulletsModelList = new ArrayList<>();
    private ArrayList<EnemyShip> enemiesModelList = new ArrayList<>();

    private HpUp heartHpUp;
    private Meteor modelMeteor;
    private int points = 0;
    private int level = 1;
    private String myShipURL = Constants.PLAYER_SHIP_URL;

    /////////************** Getter and setters ***********************

    public HpUp getHeartHpUp() {
        return heartHpUp;
    }

    public void setHeartHpUp(HpUp heartHpUp) {
        this.heartHpUp = heartHpUp;
    }

    public int getLevel() {
        return level;
    }

    public void addLevel() {
        this.level++;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Meteor getModelMeteor() {
        return modelMeteor;
    }
    public void setModelMeteor(Meteor modelMeteor) {
        this.modelMeteor = modelMeteor;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * Creates a new InGameModel unless there is one already.
     * @return gameModel
     */
    public static InGameModel getGameModel() {
        if (gameModel == null) {
            gameModel = new InGameModel();
        }
        return gameModel;
    }

    private InGameModel() {
        playerModel = new PlayerShip(Constants.PLAYER_SHIP_URL);
    }

    public PlayerShip getPlayerModel() {
        return playerModel;
    }

    public void setPlayerModel(PlayerShip playerModel) {
        this.playerModel = playerModel;
    }

    public void setPlayerModelImage(String url) {
        myShipURL = url;
        playerModel = new PlayerShip(url);
    }

    public void setBulletsModelList(ArrayList<IBullet> bulletsModelList) {
        this.bulletsModelList = bulletsModelList;
    }

    public ArrayList<IBullet> getBulletsModelList() {
        return bulletsModelList;
    }

    /**
     * Add model of bullet to bulletsModelList.
     * @param bullet bullet to add
     */
    public void addBullets(IBullet bullet) {
        bulletsModelList.add(bullet);
    }

    public ArrayList<EnemyShip> getEnemyModelList() {
        return enemiesModelList;
    }

    /**
     * Adds enemies to enemiesModelList
     * @param enemy model of enemy ship
     */
    public void addEnemyModel(EnemyShip enemy) {
        enemiesModelList.add(enemy);
    }

    //adds meteor to meteorModelList

    ///// ******************* END OF GETTERS AND SETTERS  ******************************

    /**
     * Resets points and models for player ship, bullets list, enemies list, meteor.
     */
    public void resetAllModel() {
        playerModel = new PlayerShip(myShipURL);
        bulletsModelList.clear();
        enemiesModelList.clear();
        modelMeteor = null;
        points = 0;
        heartHpUp = null;
        level = 1;
    }





}
