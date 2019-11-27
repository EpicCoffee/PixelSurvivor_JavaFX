package model;

import java.util.ArrayList;

// All info in the game.
public class InGameModel {

    private static InGameModel gameModel;

    private PlayerShip playerModel;
    private ArrayList<IBullet> bulletsModelList = new ArrayList<>();
    private ArrayList<EnemyShip> enemiesModelList = new ArrayList<>();

    private boolean isShooting = false;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;
    //TODO add all movement true or false;

    /////////************** Getter and setters ***********************
    public boolean isShooting() {
        return isShooting;
    }
    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }

    public void setmovingLeft(boolean moveLeft){
        isMovingLeft = moveLeft;
    }
    public void setmovingRight(boolean moveRight){
        isMovingRight = moveRight;
    }

    public static InGameModel getGameModel() {
        if (gameModel == null) {
            gameModel = new InGameModel();
        }
        return gameModel;
    }

    private InGameModel() {
        playerModel = new PlayerShip();
    }


    public PlayerShip getPlayerModel() {
        return playerModel;
    }

    public void setPlayerModel(PlayerShip playerModel) {
        this.playerModel = playerModel;
    }

    public ArrayList<IBullet> getBulletsModelList() {
        return bulletsModelList;
    }

    // add modelinfo
    public void addBullets(IBullet bullet) {
        bulletsModelList.add(bullet);
    }

    public IBullet getLastBullet() {
        return bulletsModelList.get(bulletsModelList.size()-1);
    }

    public ArrayList<EnemyShip> getEnemy() {
        return enemiesModelList;
    }

    public void addEnemy(EnemyShip enemy) {
        enemiesModelList.add(enemy);
    }

    ///// ******************* END OF GETTERS AND SETTERS  ******************************

    // when space is down check if you weapon manage to shoot.
    public boolean checkIfPlayerIsShooting() {
        if (isShooting()) {
            return playerModel.performShootingAction();
        }
        return false;
    }
    private void checkIfPlayerIsMovingLeft(){
       if (isMovingLeft && playerModel.getItemCoordX() > 0){
           playerModel.moveLeft();
        }
    }
    private void checkIfPlayerIsMovingRight() {
        if (isMovingRight && playerModel.getItemCoordX() < Constants.SCREENWIDTH) {
            playerModel.moveRight();
        }
    }

    public void updatePlayerMovement(){
        checkIfPlayerIsMovingLeft();
        checkIfPlayerIsMovingRight();
    }

    // Moving all bullets forward
    public void updateBullets() {
        for (IBullet bullet : bulletsModelList) {
            OnScreenItems itemBullet = (OnScreenItems)bullet;
            itemBullet.moveUp();
        }
    }

    // check if something is out of screen
    private boolean checkIfOutOfScreen(double x, double y) {
        return y > Constants.SCREENHEIGHT+50 || x > Constants.SCREENWIDTH+50 || y < -50 || x < -50;
    }

    // Checking if bullet is out of screen and return the index of the bullets that needs to be removed in our imageview list.
    public ArrayList<Integer> getBulletRemoveList() {
        ArrayList<Integer> bulletsToRemove = new ArrayList<>();
        for (int i = 0; i < bulletsModelList.size() ; i++) {
            OnScreenItems itemBullet = (OnScreenItems)bulletsModelList.get(i);
            if (checkIfOutOfScreen(itemBullet.getItemCoordX(), itemBullet.getItemCoordY())){
                bulletsToRemove.add(i);
            }
        }
        for (int index : bulletsToRemove) {
            bulletsModelList.remove(index);
        }
        return bulletsToRemove;
    }

    //adds +1 to our weaponState to make it ready when at it's state.
    public void updateWeaponsState() {
        playerModel.getWeapon().addToReadyToShoot();
       /* for (EnemyShip enemyShip: enemiesModelList) {
            enemyShip.getWeapon().addToReadyToShoot();
        }*/
    }
}
