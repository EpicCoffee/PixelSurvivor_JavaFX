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


        initializeLevelToPane();
        initializeGameListener();
        createGameLoop();

    }

    private void createGameLoop() {
        inGameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            // checks and update movement of images
                updateAllModels(); // update all models before checks.
                updateAllImageviews();
                updateIfPlayerIsShooting();
            }
        };

        inGameTimer.start();
    }

    private void updateAllModels() {
        model.updateBullets();
        model.updateWeaponsState();
    }

    private void updateAllImageviews() {
        updateBulletsImage();
    }


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
        ArrayList<Integer> bulletsToRemove = model.getBulletRemoveList();
        for (int bulletsIndex : bulletsToRemove) {
            removeFromGamePane(bulletsImageList.get(bulletsIndex));
            bulletsImageList.remove(bulletsIndex);
            System.out.println("Bullet removed");
        }

    }

    private void updateIfPlayerIsShooting() {
        if (model.checkIfPlayerIsShooting()) {
            createBullet(model.getLastBullet());
        }
    }

    private void createBullet(IBullet bullet) {
        OnScreenItems itemBullet = (OnScreenItems) bullet;
        System.out.println("x: " + itemBullet.getItemCoordX() + " y: " + itemBullet.getItemCoordY());
        ImageView imageBullet = new ImageView(itemBullet.getImageUrl());
        imageBullet.setX(itemBullet.getItemCoordX());
        imageBullet.setY(itemBullet.getItemCoordY());
        //imageBullet.resize(itemBullet.getItemWidth(), itemBullet.getItemHeight());


        bulletsImageList.add(imageBullet);
        addToGamePane(imageBullet);
    }


    private void initializeLevelToPane() {
        initializePlayer();
    }

    private void initializePlayer() {
        PlayerShip playerModel = model.getPlayerModel();
        playerImage = new ImageView(playerModel.getImageUrl());
        playerImage.setX(playerModel.getItemCoordX());
        playerImage.setY(playerModel.getItemCoordY());
        //playerImage.resize(playerModel.getItemWidth(), playerModel.getItemHeight());
        addToGamePane(playerImage);
    }

    private void initializeGameListener() {
        gameScene.setOnKeyPressed(SpaceInvaderListener.getListener());
        gameScene.setOnKeyReleased(SpaceInvaderListener.getListener());
    }

    private void addToGamePane(ImageView imageItem) {
        gamePane.getChildren().add(imageItem);
    }

    private void removeFromGamePane(ImageView imageItem) {
        gamePane.getChildren().remove(imageItem);
    }




}
