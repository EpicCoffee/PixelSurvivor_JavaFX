package view;

import controller.SpaceInvaderButtonListener;
import controller.SpaceInvaderController;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.*;

import java.util.ArrayList;


/**
 * This class takes care of the first scene of the game, a menu with the options to start playing, view the high score,
 * learn how to play, read credits or quit the program.
 * Implements IViewState.
 *
 * @author Isabelle Romhagen, Ludvig Lundin, Khazar Mehraban
 * @version 1.2
 */


public class SpaceInvaderMenuView implements IViewState {
    private ArrayList<Button> buttonArrayList = new ArrayList<>();
    private Button button;
    private static SubScene currentSubScene;

    BackgroundImage buttonOnclickImg = new BackgroundImage(new Image(Constants.BUTTON_BG_CLICKED_URL), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    Background buttonOnOnclickBackground = new Background(buttonOnclickImg);
    BackgroundImage buttonOnReleasedImage = new BackgroundImage(new Image(Constants.BUTTON_BG_URL), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    Background buttonOnReleasedBackground = new Background(buttonOnReleasedImage);

    private static Scene menuScene;
    private static AnchorPane menuPane;

    private ArrayList<VBox> shipList;
    private ArrayList<CheckBox> pickBoxes = new ArrayList<>();

    private static SpaceInvaderMenuView spaceInvaderMenuView;

    /////////************** Getter and setters ***********************

    public static Scene getMenuScene() {
        return menuScene;
    }

    public static AnchorPane getMenuPane(){
        return menuPane;
    }

    public static SubScene getCurrentSubScene(){
        return currentSubScene;
    }

    public static SpaceInvaderMenuView getSpaceInvaderMenuView() {
        if (spaceInvaderMenuView == null) {
            spaceInvaderMenuView = new SpaceInvaderMenuView();
        }
        return spaceInvaderMenuView;
    }

    /////////************** End of Getter and setters ***********************

    /**
     * The constructor initializes the pane, scene and background image for the menu.
     * It also calls the methods to create the buttons, initialize the button listeners and get high score from the database.
     */
    private SpaceInvaderMenuView() {
        menuPane = new AnchorPane();
        menuScene = new Scene(menuPane, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        BackgroundImage bootImage = new BackgroundImage(new Image(Constants.GAME_BOOT_SCENE_BACK_GROUND, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);

        menuPane.setBackground(new Background(bootImage));

        createButtonToList();
        initializeButtonListeners();
        HighScore.getHighScore();
    }

    /**
     * Creates an array of buttons, defines their names and positions and adds them to the menuPane.
     */
    private void createButtonToList() {
        String[] buttonList = {"Play", "HighScore", "Settings", "Help", "Credits", "Exit"};
        for (int i = 1; i < buttonList.length + 1; i++) {
            createButton(buttonList[i - 1], Constants.MENU_BUTTON_STARTING_X_POS, Constants.MENU_BUTTON_STARTING_Y_POS * (i * 0.2));
        }
        menuPane.getChildren().addAll(buttonArrayList);
    }

    /**
     * Calls the method to close any active sub scene.
     * Creates a sub scene, defines its width and height, background image.
     * Gets high score from the HighScore class and displays it.
     * Adds it to the menuPane.
     */
    public void initializeHighScoreSubScene() {

        closeCurrentSubScene();

        AnchorPane scoreAnchor = new AnchorPane();
        currentSubScene = new SubScene(scoreAnchor, Constants.SCREEN_WIDTH * 0.45, Constants.SCREEN_HEIGHT * 0.45);

        BackgroundImage image = new BackgroundImage(new Image(Constants.GAME_OVER_SUB_SCENE_BACKGROUND, Constants.SCREEN_WIDTH * 0.6, Constants.SCREEN_HEIGHT * 0.6, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        scoreAnchor.setBackground(new Background(image));
        currentSubScene.setId("highScoreScene");
        currentSubScene.setLayoutX(Constants.SCREEN_WIDTH / 3);
        currentSubScene.setLayoutY(Constants.SCREEN_HEIGHT * 0.5 - currentSubScene.getHeight()/2);

        Text text = new Text("High Score"); //TODO COLOR SET on text in the subscene
        text.setX(scoreAnchor.getWidth() /2.8);
        text.setY(scoreAnchor.getHeight() * 0.2);
        text.setFill(Color.color(0.75, 0.9, 0.9));
        text.setStyle("-fx-font: 24 sergoe;");
        scoreAnchor.getChildren().add(text);

        double highScoreTextLeftX = scoreAnchor.getWidth() * 0.2;
        double highScoreTextRightX = highScoreTextLeftX * 3;
        double highScoreTextLeftY = scoreAnchor.getHeight() * 0.4;
        double highScoreTextRightY = scoreAnchor.getHeight() * 0.4;
        double highScoreTextYMargin = scoreAnchor.getHeight() * 0.08;

                int i = 0;
                for (HighScoreBean highScore : HighScore.getHighScore().getTop10()) {
                    if (highScore != null) {

                        if (i < 5) {
                            Text highScoreText = new Text(highScore.getUsername() + " : " + highScore.getScore());
                            highScoreText.setX(highScoreTextLeftX);
                            highScoreText.setY(highScoreTextLeftY);
                            highScoreTextLeftY += highScoreTextYMargin;
                            highScoreText.setFill(Color.color(0.75, 0.9, 0.9));
                            highScoreText.setStyle("-fx-font: 18 sergoe;");
                            scoreAnchor.getChildren().add(highScoreText);
                            i++;
                        }
                        else {
                            Text highScoreText = new Text(highScore.getUsername() + " : " + highScore.getScore());
                            highScoreText.setX(highScoreTextRightX);
                            highScoreText.setY(highScoreTextRightY);
                            highScoreTextRightY += highScoreTextYMargin;
                            highScoreText.setFill(Color.color(0.75, 0.9, 0.9));
                            highScoreText.setStyle("-fx-font: 18 sergoe;");
                            scoreAnchor.getChildren().add(highScoreText);
                            i++;
                        }


                    }

            }

        menuPane.getChildren().add(currentSubScene);
    }

    /**
     * Calls the method to close any active sub scene.
     * Creates a sub scene, defines its width and height, background image.
     * Creates a string array of contributors and displays it.
     * Adds it to the menuPane.
     */
    public void initializeCreditsSubScene() {

        closeCurrentSubScene();

        AnchorPane creditAnchor = new AnchorPane();
        currentSubScene = new SubScene(creditAnchor, Constants.SCREEN_WIDTH * 0.45, Constants.SCREEN_HEIGHT * 0.45);

        BackgroundImage image = new BackgroundImage(new Image(Constants.GAME_OVER_SUB_SCENE_BACKGROUND, Constants.SCREEN_WIDTH * 0.45, Constants.SCREEN_HEIGHT * 0.45, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        creditAnchor.setBackground(new Background(image));
        currentSubScene.setId("creditScene");
        currentSubScene.setLayoutX(Constants.SCREEN_WIDTH / 3);
        currentSubScene.setLayoutY(Constants.SCREEN_HEIGHT * 0.5 - currentSubScene.getHeight()/2);

        Text text = new Text("Credits"); //TODO se highScore
        text.setX(creditAnchor.getWidth() /2.5);
        text.setY(creditAnchor.getHeight() * 0.2);
        text.setFill(Color.color(0.75, 0.9, 0.9));
        text.setStyle("-fx-font: 24 sergoe;");
        creditAnchor.getChildren().add(text);

        String[] creditsTo = {"Isabelle Romhagen","Jasmine Söderberg", "Khazar Mehraban", "Mattias Frigren", "Ludvig Lundin"};
        for (int i = 0; i < creditsTo.length; i++) {
            Text nameText = new Text(creditsTo[i]);
            nameText.setX(creditAnchor.getWidth()/2.8);
            nameText.setY(creditAnchor.getHeight() * 0.35 + (creditAnchor.getHeight() * 0.1 * i));
            nameText.setFill(Color.color(0.75, 0.9, 0.9));
            nameText.setStyle("-fx-font: 15 sergoe;");
            creditAnchor.getChildren().add(nameText);
        }

        menuPane.getChildren().add(currentSubScene);
    }

    /**
     * Calls the method to close any active sub scene.
     * Creates a sub scene, defines its width and height, background image.
     * Creates an option for the user to turn sound on/off.
     * Adds it to the menuPane.
     */
    public void initializeSettingsSubScene() {

        closeCurrentSubScene();

        AnchorPane settingsAnchor = new AnchorPane();
        currentSubScene = new SubScene(settingsAnchor, Constants.SCREEN_WIDTH * 0.45, Constants.SCREEN_HEIGHT * 0.45);

        BackgroundImage image = new BackgroundImage(new Image(Constants.GAME_OVER_SUB_SCENE_BACKGROUND, Constants.SCREEN_WIDTH * 0.45, Constants.SCREEN_HEIGHT * 0.45, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        settingsAnchor.setBackground(new Background(image));
        currentSubScene.setId("settingScene");
        currentSubScene.setLayoutX(Constants.SCREEN_WIDTH / 3);
        currentSubScene.setLayoutY(Constants.SCREEN_HEIGHT * 0.5 - currentSubScene.getHeight()/2);

        Text text = new Text("Settings"); //TODO COLOR SET
        text.setX(settingsAnchor.getWidth() / 2.5);
        text.setY(settingsAnchor.getHeight() * 0.2);
        settingsAnchor.getChildren().add(text);
        text.setFill(Color.color(0.75, 0.9, 0.9));
        text.setStyle("-fx-font: 24 sergoe;");
        CheckBox soundOn = new CheckBox("Sound");
        soundOn.setSelected(true);
        soundOn.setLayoutX(settingsAnchor.getWidth() / 3);
        soundOn.setLayoutY(settingsAnchor.getHeight() * 0.4);
        soundOn.setTextFill(Color.color(0.75, 0.9, 0.9));
        soundOn.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                SpaceInvaderController.getController().setSoundOn(soundOn.isSelected());
            }
        });
        settingsAnchor.getChildren().add(soundOn);

        menuPane.getChildren().add(currentSubScene);
    }

    /**
     * Calls the method to close any active sub scene.
     * Creates a sub scene, defines its width and height, background image.
     * Adds it to the menuPane.
     */
    public void initializeHelpSubScene() {

        closeCurrentSubScene();

        AnchorPane helpAnchor = new AnchorPane();
        currentSubScene = new SubScene(helpAnchor, Constants.SCREEN_WIDTH * 0.45, Constants.SCREEN_HEIGHT * 0.45);

        BackgroundImage image = new BackgroundImage(new Image(Constants.HELP_BACK_GROUND, Constants.SCREEN_WIDTH * 0.45, Constants.SCREEN_HEIGHT * 0.45, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        helpAnchor.setBackground(new Background(image));
        currentSubScene.setId("helpScene");
        currentSubScene.setLayoutX(Constants.SCREEN_WIDTH / 3);
        currentSubScene.setLayoutY(Constants.SCREEN_HEIGHT * 0.5 - currentSubScene.getHeight()/2);

        Text text = new Text("Help"); //TODO COLOR SET
        text.setX(helpAnchor.getWidth() / 2.5);
        text.setY(helpAnchor.getHeight() * 0.2);
        text.setFill(Color.color(0.75, 0.9, 0.9));
        text.setStyle("-fx-font: 24 sergoe;");
        helpAnchor.getChildren().add(text);
        menuPane.getChildren().add(currentSubScene);
    }

    public void initializeChooseShipSubScene() {

        closeCurrentSubScene();

        AnchorPane helpAnchor = new AnchorPane();
        currentSubScene = new SubScene(helpAnchor, Constants.SCREEN_WIDTH * 0.45, Constants.SCREEN_HEIGHT * 0.45);

        BackgroundImage image = new BackgroundImage(new Image(Constants.GAME_OVER_SUB_SCENE_BACKGROUND, Constants.SCREEN_WIDTH * 0.45, Constants.SCREEN_HEIGHT * 0.45, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        helpAnchor.setBackground(new Background(image));
        currentSubScene.setId("shipChoose");
        currentSubScene.setLayoutX(Constants.SCREEN_WIDTH / 3);
        currentSubScene.setLayoutY(Constants.SCREEN_HEIGHT * 0.5 - currentSubScene.getHeight()/2);

        Button startGame = new Button("Start");
        startGame.setPrefWidth(Constants.SCREEN_WIDTH * 0.1);
        startGame.setTextFill(Color.color(0.75, 0.9, 0.9));
        startGame.setBackground(buttonOnReleasedBackground);
        startGame.setFont(Font.font("Verdana", 20));
        startGame.setLayoutY(currentSubScene.getHeight() * 0.8);
        startGame.setLayoutX((currentSubScene.getWidth() * 0.5) - Constants.SCREEN_WIDTH * 0.05);
        startGame.addEventFilter(MouseEvent.MOUSE_CLICKED, SpaceInvaderButtonListener.getButtonListener().startGame);

        helpAnchor.getChildren().add(createShipsToChoose());
        helpAnchor.getChildren().add(startGame);
        menuPane.getChildren().add(currentSubScene);
    }

    /**
     * Removes any active sub scene.
     */
    public void closeCurrentSubScene(){
        if (currentSubScene != null) {
            menuPane.getChildren().remove(currentSubScene);
        }
    }

    /**
     * Sets current sub scene to null so that it will close next time the user clicks a button.
     */
    public void cleanCurrentSubScene() {
           closeCurrentSubScene();
           currentSubScene = null;

    }

    /**
     * Takes below arguments, creates a button and adds it to the array of buttons created in createButtonList.
     * @param buttonText button name
     * @param x x position
     * @param y y position
     */
    private void createButton(String buttonText, double x, double y) {
        button = new Button(buttonText);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setFont(Font.font("Verdana", 20));
        button.setTextFill(Color.color(0.75, 0.9, 0.9));
        button.setPrefWidth(Constants.MENU_BUTTON_WIDTH);
        button.setBackground(buttonOnReleasedBackground);
        buttonArrayList.add(button);
    }

    private HBox createShipsToChoose() {
        HBox box = new HBox();
        box.setSpacing(20);
        shipList = new ArrayList<>();
        for (SHIPCHOOSER ship : SHIPCHOOSER.values()) {
            VBox shipToPick = new VBox();
            CheckBox pickBox = new CheckBox();
            ImageView shipImage = new ImageView(ship.getURL());

            shipToPick.setAlignment(Pos.CENTER);
            shipToPick.setSpacing(20);
            shipToPick.getChildren().add(shipImage);
            shipToPick.getChildren().add(pickBox);
            pickBoxes.add(pickBox);

            box.getChildren().add(shipToPick);
            pickBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (CheckBox shipBox : pickBoxes) {
                        if (!shipBox.equals(pickBox)){
                            shipBox.setSelected(false);
                        }
                    }
                    InGameModel.getGameModel().setPlayerModelImage(ship.getURL());
                }
            });
        }
        box.setLayoutX(300 - (118*2));
        box.setLayoutY(100);
        return box;
    }

    /**
     * Adds mouse events to all menu buttons.
     * Sets their background font, position and effect when clicked.
     */
    private void initializeButtonListeners() {
        buttonArrayList.get(0).addEventFilter(MouseEvent.MOUSE_CLICKED, SpaceInvaderButtonListener.getButtonListener().chooseShip);
        buttonArrayList.get(1).addEventFilter(MouseEvent.MOUSE_CLICKED, SpaceInvaderButtonListener.getButtonListener().showHighScore);
        buttonArrayList.get(2).addEventFilter(MouseEvent.MOUSE_CLICKED, SpaceInvaderButtonListener.getButtonListener().showSettings);
        buttonArrayList.get(3).addEventFilter(MouseEvent.MOUSE_CLICKED, SpaceInvaderButtonListener.getButtonListener().showHelp);
        buttonArrayList.get(4).addEventFilter(MouseEvent.MOUSE_CLICKED, SpaceInvaderButtonListener.getButtonListener().showCredits);
        buttonArrayList.get(5).addEventFilter(MouseEvent.MOUSE_CLICKED, SpaceInvaderButtonListener.getButtonListener().exit);


        for (Button button : buttonArrayList) {
            {
                button.setOnMousePressed(e -> {
                    if (e.getButton().equals(MouseButton.PRIMARY)) {

                        button.setBackground(buttonOnOnclickBackground);
                        button.setFont(Font.font("Verdana", 18));
                        button.setLayoutY(button.getLayoutY()+4);
                    }
                });
                button.setOnMouseEntered(e->{button.setEffect(new DropShadow());
                });
                button.setOnMouseExited(e->{button.setEffect(null);
                });
                button.setOnMouseReleased(e -> {
                    if (e.getButton().equals(MouseButton.PRIMARY)) {
                        button.setBackground(buttonOnReleasedBackground);
                        button.setFont(Font.font("Verdana", 20));
                        button.setLayoutY(button.getLayoutY()-4);
                    }
                });


            }


        }


    }
}