package view;

import controller.SpaceInvaderListener;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.*;

import java.util.ArrayList;

public class SpaceInvaderInGameView implements IViewState {


    private static SpaceInvaderInGameView gameView;
    private static InGameModel model;

    private static AnchorPane gamePane;
    private static Scene gameScene;

    private ArrayList<ImageView> enemiesImageList = new ArrayList<>();
    private ArrayList<ImageView> bulletsImageList = new ArrayList<>();;
    private ImageView playerImage;

    private AnimationTimer inGameTimer;

    public static Scene getGameScene() {
        return gameScene;
    }

    public static SpaceInvaderInGameView getGameView() {
        if (gameView == null) {
            gameView = new SpaceInvaderInGameView();
        }
        return gameView;
    }

    private SpaceInvaderInGameView() {

        model = InGameModel.getGameModel();
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, Constants.SCREENWIDTH, Constants.SCREENHEIGHT);


        initializeLevelToPane();  //adds all images to the pane.
        initializeGameListener(); // add key listener to game
        createGameLoop(); // starts animator.

    }

    private void createGameLoop() {
        inGameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            // checks and update movement of images
                updateIfPlayerIsShooting();
                updateAllModels(); // update all models before checks.
                updateAllImageviews();
            }
        };

        inGameTimer.start();
    }

    //add all model updates here
    private void updateAllModels() {
        model.updateBullets();
        model.updateWeaponsState();
    }

    //add all imagesviews here
    private void updateAllImageviews() {
        updateBulletsImage();
    }

// update the bullets images to mirror the model bullets.
    private void updateBulletsImage() {
        ArrayList<IBullet> bulletsModelList = model.getBulletsModelList();

        if (!bulletsImageList.isEmpty()) {
            for (int i = 0; i < bulletsImageList.size(); i++) {
                ImageView theImageBullet = bulletsImageList.get(i);
                OnScreenItems theModelBullet = (OnScreenItems) bulletsModelList.get(i);

                theImageBullet.setX(theModelBullet.getItemCoordX());
                theImageBullet.setY(theModelBullet.getItemCoordY());
            }
        }
        ArrayList<IBullet> bulletsToRemove = model.getBulletRemoveList(); // adds all bullets who are out of screen and those who collided.
        for (IBullet bullet: bulletsToRemove) {
            int bulletIndex = model.getBulletsModelList().indexOf(bullet);  // gets index of the model bullet.
            model.getBulletsModelList().remove(bulletIndex); // removes the model bullet from our list.
            removeFromGamePane(bulletsImageList.get(bulletIndex)); // removes bullet image from pane.
            bulletsImageList.remove(bulletIndex); // removes bullet image from our bullet image list.
            System.out.println("Bullet removed");
        }

    }

    private void updateIfPlayerIsShooting() {
        IBullet currentBullet = model.checkIfPlayerIsShooting();
        if (currentBullet != null) {
          createBullet(currentBullet);
        }
    }

    // sets the imageView based on the model Ibullet.
    private void createBullet(IBullet bullet) {
        OnScreenItems itemBullet = (OnScreenItems) bullet;
        System.out.println("bullet image created at x: " + itemBullet.getItemCoordX() + " y: " + itemBullet.getItemCoordY());
        ImageView imageBullet = new ImageView(itemBullet.getImageUrl());
        imageBullet.setX(itemBullet.getItemCoordX());
        imageBullet.setY(itemBullet.getItemCoordY());
        //imageBullet.resize(itemBullet.getItemWidth(), itemBullet.getItemHeight());

        if (itemBullet.isFacingPlayer()) {
            imageBullet.setRotate(180);
        }

        bulletsImageList.add(imageBullet);
        addToGamePane(imageBullet);
    }


    private void initializeLevelToPane() {
        initializePlayer();
        //TODO add all starting images.
    }

    //Creates the image of the player and set it's position and add to pane.
    private void initializePlayer() {
        PlayerShip playerModel = model.getPlayerModel();
        playerImage = new ImageView(playerModel.getImageUrl());
        playerImage.setX(playerModel.getItemCoordX());
        playerImage.setY(playerModel.getItemCoordY());
        //playerImage.resize(playerModel.getItemWidth(), playerModel.getItemHeight());
        addToGamePane(playerImage);
    }

    // starts the listeners.
    private void initializeGameListener() {
        gameScene.setOnKeyPressed(SpaceInvaderListener.getListener());
        gameScene.setOnKeyReleased(SpaceInvaderListener.getListener());
    }

    private void createBackGround() {
        firstBackGroundImage.setPreserveRatio(true);
        firstBackGroundImage.setFitWidth(Constants.SCREENWIDTH);
        firstBackGroundImage.setFitHeight(Constants.SCREENHEIGHT);
        addToGamePane(firstBackGroundImage);

        secondBackGroundImage.setPreserveRatio(true);
        secondBackGroundImage.setFitWidth(Constants.SCREENWIDTH);
        secondBackGroundImage.setFitHeight(Constants.SCREENHEIGHT);
        secondBackGroundImage.setY(-Constants.SCREENHEIGHT);
        addToGamePane(secondBackGroundImage);

    }

    private void moveInGameBackGround() {
        firstBackGroundImage.setY(firstBackGroundImage.getY()+5);
        secondBackGroundImage.setY(secondBackGroundImage.getY()+5);

        if (firstBackGroundImage.getY()==Constants.SCREENHEIGHT) {
            firstBackGroundImage.setY(-Constants.SCREENHEIGHT);
        }
        if (secondBackGroundImage.getY()==Constants.SCREENHEIGHT) {
            secondBackGroundImage.setY(-Constants.SCREENHEIGHT);
        }

    }
    private void addToGamePane(ImageView imageItem) {
        gamePane.getChildren().add(imageItem);
        
    }

    private void removeFromGamePane(ImageView imageItem) {
        gamePane.getChildren().remove(imageItem);
    }




}
