package model;

/**
 * This class contains all final static variables.
 *
 * @author Isabelle Romhagen, Ludvig Lundin, Mattias Frigren, Khazar Mehraban
 * @version 1.2
 */
public class Constants {

    public final static double SCREEN_HEIGHT = 600;
    public final static double SCREEN_WIDTH = 900;

    public final static String PLAYER_SHIP_URL = "model/resources/player.png";
    public final static String GREEN_PLAYER_SHIP_URL = "model/resources/greenShip.png";
    public final static String RED_PLAYER_SHIP_URL = "model/resources/redShip.png";
    public final static String BLUE_PLAYER_SHIP_URL = "model/resources/blueShip.png";
    public final static double PLAYER_SHIP_HEIGHT = SCREEN_HEIGHT * 0.12;
    public final static double PLAYER_SHIP_WIDTH = SCREEN_WIDTH * 0.08;
    public final static double PLAYER_SHIP_MOVEMENT_SPEED = SCREEN_HEIGHT * 0.008;
    public final static double PLAYER_SHIP_START_POS_X = SCREEN_WIDTH /2;
    public final static double PLAYER_SHIP_START_POS_Y = (SCREEN_HEIGHT * 0.92) - PLAYER_SHIP_HEIGHT /2;
    public final static int PLAYER_LIVES = 3;
    public final static int ULT_READY_AT = 20;
    public final static int ULTI_DAMAGE = 50;
    public final static String ULTI_IMAGE_URL = "model/resources/ult.png";
    public final static int MAX_FRAMES_TO_SHOW_ULT = 20;

    public final static String ENEMY_SHIP_URL = "model/resources/enemy.png";
    public final static double ENEMY_SHIP_HEIGHT = SCREEN_HEIGHT * 0.06;
    public final static double ENEMY_SHIP_WIDTH = SCREEN_WIDTH * 0.06;
    public final static double ENEMY_SHIP_MOVEMENT_SPEED = SCREEN_HEIGHT * 0.002;
    public final static double ENEMY_SHIP_START_POS_X = SCREEN_WIDTH * 0.1;
    public final static double ENEMY_SHIP_START_POS_Y = SCREEN_HEIGHT -(SCREEN_HEIGHT +45);
    public final static double ENEMY_SPAWN_SPREAD = SCREEN_WIDTH * 0.08;
    public final static int ENEMY_LIVES = 1;
    public final static double ENEMY_LASER_GUN_SHOOTING_INTERVAL = 300;
    public final static double ENEMY_SHIP_MOVEMENT_INTERVAL = 300;

    public final static String ENEMY_DRONE_SHIP_URL = "model/resources/enemy1_4.png";
    public final static double ENEMY_DRONE_SHIP_HEIGHT = SCREEN_HEIGHT *0.12;
    public final static double ENEMY_DRONE_SHIP_WIDTH = SCREEN_WIDTH *0.08;
    public final static double ENEMY_DRONE_SHIP_MOVEMENT_SPEED = SCREEN_HEIGHT *0.004;
    public final static double ENEMY_DRONE_SHIP_START_POS_X = SCREEN_WIDTH *0.1;
    public final static double ENEMY_DRONE_SHIP_START_POS_Y = SCREEN_HEIGHT -(SCREEN_HEIGHT +45);
    public final static double ENEMY_DRONE_SHIP_SPAWN_SPREAD = SCREEN_WIDTH *0.08;
    public final static int ENEMY_DRONE_SHIP_LIVES =2;
    public final static double ENEMY_DRONE_SHIP_LASER_GUN_SHOOTING_INTERVAL = 200;

    public final static String ENEMY_BIG_BOSS_URL = "model/resources/enemy1_5.png";
    public final static double ENEMY_BIG_BOSS_HEIGHT = SCREEN_HEIGHT *0.18;
    public final static double ENEMY_BIG_BOSS_WIDTH = SCREEN_WIDTH *0.12;
    public final static double ENEMY_BIG_BOSS_MOVEMENT_SPEED = SCREEN_HEIGHT *0.004;
    public final static double ENEMY_BIG_BOSS_START_POS_X = SCREEN_WIDTH *0.1;
    public final static double ENEMY_BIG_BOSS_START_POS_Y = SCREEN_HEIGHT -(SCREEN_HEIGHT +45);
    public final static int ENEMY_BIG_BOSS_LIVES = 100;
    public final static double ENEMY_BIG_BOSS_SHOOTING_INTERVAL = 40;

    public final static String IN_GAME_BACK_GROUND_IMAGE = "model/resources/inGameBackground.png";


    public final static String METEOR_IMAGE = "model/resources/meteor.png";
    public final static double METEOR_HEIGHT = SCREEN_HEIGHT * 0.12;
    public final static double METEOR_WIDTH = SCREEN_WIDTH * 0.08;
    public final static double METEOR_MOVEMENT_SPEED = SCREEN_HEIGHT * 0.006;

    public final static String ENEMY_BULLET_URL = "model/resources/enemyLaserBullet.png";
    public final static String LASER_BULLET_URL = "model/resources/bulletShot.png";
    public final static double LASER_BULLET_HEIGHT = SCREEN_HEIGHT * 0.04;
    public final static double LASER_BULLET_WIDTH = SCREEN_WIDTH * 0.025;
    public final static double LASER_BULLET_MOVEMENT_SPEED = SCREEN_HEIGHT * 0.016;
    public final static double LASER_BULLET_SHOOT_INTERVAL = 15;

    public final static String HEART_URL = "model/resources/heart.png";
    public final static double HEART_START_X = SCREEN_WIDTH * 0.87;
    public final static double HEART_START_Y = SCREEN_HEIGHT * 0.95;
    public final static double HEART_HEIGHT = SCREEN_HEIGHT * 0.04;
    public final static double HEART_WIDTH = SCREEN_WIDTH * 0.04;
    public final static double HEART_MOVEMENT_SPEED = SCREEN_HEIGHT * 0.004;

    public final static String GAME_OVER_SUB_SCENE_BACKGROUND = "model/resources/bg.png";
    public final static String GAME_BOOT_SCENE_BACK_GROUND ="model/resources/menuBackGroundBluePrint.png";
    public final static String POINT_LABEL_BACK_GROUND ="model/resources/pointLabel.png";
    public final static String HELP_BACK_GROUND = "model/resources/Help.png";


    public final static double SPAWN_POINT_METEOR = Math.random()* SCREEN_WIDTH;


    public static final String BUTTON_BG_CLICKED_URL = "model/resources/buttonOnClick.png"; //TODO
    public static final String BUTTON_BG_URL = "model/resources/buttonImg.png"; // TODO
    public static final double MENU_BUTTON_WIDTH = SCREEN_WIDTH * 0.15;
    public static final double MENU_BUTTON_STARTING_X_POS = SCREEN_WIDTH * 0.1;
    public static final double MENU_BUTTON_STARTING_Y_POS = SCREEN_HEIGHT * 0.7;


    public static final String LASERSOUNDURL_1 = "src\\model\\resources\\Laser1.wav";
    public static final String LASERSOUNDURL_2 = "src\\model\\resources\\Laser2.wav";
    public static final String ENEMY_EXPLOSION = "src\\model\\resources\\Explosion.wav";
    public static final String ULT_SOUND_URL = "src\\model\\resources\\Explosion2.wav";
    public static final String POWER_UP_SOUND_URL = "src\\model\\resources\\Powerup.wav";
    public static final String PLASMA_SOUND_URL = "src\\model\\resources\\Sci-Fi Plasma.mp3";
    public static final String SOUNDTRACK = "src\\model\\resources\\Soundtrack.wav";
}
