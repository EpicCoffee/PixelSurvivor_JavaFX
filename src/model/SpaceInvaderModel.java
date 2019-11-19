package model;


import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class SpaceInvaderModel {

    private static SpaceInvaderModel model; //sitt egna object endast 1 kommer finnas
    private ArrayList<OnScreenItems> enemies;
    private ArrayList<OnScreenItems> bullets;

    PlayerShip player;


    private SpaceInvaderModel() { //konstruktor som ingen når(singelton)
        player = new PlayerShip();
    }

    public static SpaceInvaderModel getModel() {  //checkar om det redan finns ett skapat object av klassen
        if (model == null) {                      //  skpar ett nytt objevt om det inte redan existerar.
            model = new SpaceInvaderModel();
        }
        return model;
    }

    public void createAll(AnchorPane pane) {
        createShip(pane);
    }

    public void createShip(AnchorPane pane) {
        pane.getChildren().add(player);
        pane.getChildren().add((Node) player.performShootingAction());
    }


    // Ishots OnScreenItems : player
    //
    //-model:SpaceInvaderModel
    //-rightButtonClicked:Boolean
    //-leftButtonClicked:Boolean
    //-upButtonClicked:Boolean
    //-downuttonClicked:Boolean
    //-spaceDown:Boolean



    //CreateFunctions...
    //newLevel()
    //
    //+doAllCheckFunctions()
    //  checkFunctions...
    //-moveShip()
    //-moveEnemy
    //+getModel():model


}
