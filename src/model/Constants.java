package model;

/**
 * This class contains all final static variables.
 *
 * @author Isabelle Romhagen, Ludvig Lundin, Mattias Frigren, Khazar Mehraban
 * @version 1.2
 */
public class Constants {

    public final static double SCREENHEIGHT = 600;
    public final static double SCREENWIDTH = 900;

    public final static String playerShipURL = "model/resources/player.png";
    public final static String greenPlayerShipURL = "model/resources/greenShip.png";
    public final static String redPlayerShipURL = "model/resources/redShip.png";
    public final static String bluePlayerShipURL = "model/resources/blueShip.png";
    public final static double playerShipHeight = SCREENHEIGHT * 0.12;
    public final static double playerShipWidth = SCREENWIDTH * 0.08;
    public final static double playerShipMovementSpeed = SCREENHEIGHT * 0.008;
    public final static double playerShipStartPosX = SCREENWIDTH/2;
    public final static double playerShipStartPosY= (SCREENHEIGHT * 0.92) - playerShipHeight/2;
    public final static int playerLifes = 3;
    public final static int ultReadyAt = 20;
    public final static int ultiDamage = 50;
    public final static String ultImageUrl = "model/resources/ult.png";
    public final static int maxFramesToShowUlt = 20;

    public final static String enemyShipURL = "model/resources/enemy.png";
    public final static double enemyShipHeight = SCREENHEIGHT * 0.06;
    public final static double enemyShipWidth = SCREENWIDTH * 0.06;
    public final static double enemyShipMovementSpeed = SCREENHEIGHT * 0.002;
    public final static double enemyShipStartPosX = SCREENWIDTH * 0.1;
    public final static double enemyShipStartPosY = SCREENHEIGHT -(SCREENHEIGHT+45);
    public final static double enemySpawnSpread = SCREENWIDTH * 0.08;
    public final static int enemyLifes = 1;
    public final static double enemyLaserGunShootingInterval = 300;

    public final static String enemyDroneShipUrl = "model/resources/enemy1_4.png";
    public final static double enemyDroneShipHeight = SCREENHEIGHT*0.12;
    public final static double enemyDroneShipWidth = SCREENWIDTH*0.08;
    public final static double enemyDroneShipMovmentSpeed = SCREENHEIGHT*0.004;
    public final static double enemyDroneShipStartPosX = SCREENWIDTH*0.1;
    public final static double enemyDroneShipStartPosY = SCREENHEIGHT -(SCREENHEIGHT +45);
    public final static int enemyDroneShipLifes =2;
    public final static double enemyDroneShipLaserGunShootingInterval = 200;

    public final static String enemyBigBossUrl = "model/resources/enemy1_5.png";
    public final static double enemyBigBossHeight = SCREENHEIGHT*0.18;
    public final static double enemyBigBossWidth = SCREENWIDTH*0.12;
    public final static double enemyBigBossMovmentSpeed = SCREENHEIGHT*0.005;
    public final static double enemyBigBossStartPosX = SCREENWIDTH*0.1;
    public final static double enemyBigBossStartPosY = SCREENHEIGHT -(SCREENHEIGHT+45);
    public final static int enemyBigBossLifes = 100;
    public final static double enemyBigBossShootingInterval = 33;

    public final static String inGameBackGroundImage = "model/resources/inGameBackground.png";


    public final static String meteorImage = "model/resources/meteor.png";
    public final static double meteorHeight = SCREENHEIGHT * 0.12;
    public final static double meteorWidth = SCREENWIDTH * 0.08;
    public final static double meteorMovementSpeed = SCREENHEIGHT * 0.006;

    public final static String enemyBulletUrl = "model/resources/enemyLaserBullet.png";
    public final static String laserBulletURL = "model/resources/bulletShot.png";
    public final static double laserBulletHeight = SCREENHEIGHT * 0.04;
    public final static double laserBulletWidth = SCREENWIDTH * 0.025;
    public final static double laserBulletMovementSpeed = SCREENHEIGHT * 0.016;
    public final static double laserBulletShootInterval = 15;

    public final static String heartURL = "model/resources/heart.png";
    public final static double heartStartX = SCREENWIDTH * 0.87;
    public final static double heartStartY = SCREENHEIGHT * 0.95;
    public final static double heartHeight = SCREENHEIGHT * 0.04;
    public final static double heartWidth = SCREENWIDTH * 0.04;
    public final static double heartMovementSpeed = SCREENHEIGHT * 0.004;

    public final static String gameOverSubSceneBackground = "model/resources/bg.png";
    public final static String gameBootSceneBackGround ="model/resources/menuBackGroundBluePrint.png";
    public final static String pointLabelBackGround ="model/resources/pointLabel.png";
    public final static String helpBackGround = "model/resources/Help.png";


    public final static double spawnPointMeteor = Math.random()*SCREENWIDTH;


    public static final String buttonBgClickedURL = "model/resources/buttonOnClick.png"; //TODO
    public static final String buttonBgURL = "model/resources/buttonImg.png"; // TODO
    public static final double menuButtonWidth = SCREENWIDTH * 0.15;
    public static final double menuButtonStartingXPos = SCREENWIDTH * 0.1;
    public static final double menuButtonStartingYPos = SCREENHEIGHT * 0.7;


    public static final String LASERSOUNDURL1 = "src\\model\\resources\\Laser1.wav";
    public static final String LASERSOUNDURL2 = "src\\model\\resources\\Laser2.wav";
    public static final String enemyExplosion = "src\\model\\resources\\Explosion.wav";
    public static final String ultSoundUrl = "src\\model\\resources\\Explosion2.wav";
    public static final String POWERUPSOUNDURL = "src\\model\\resources\\Powerup.wav";
    public static final String PLASMASOUNDURL = "src\\model\\resources\\Sci-Fi Plasma.mp3";
    public static final String Soundtrack = "src\\model\\resources\\Soundtrack.wav";
}
