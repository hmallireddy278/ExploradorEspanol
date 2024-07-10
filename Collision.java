// Hrithik Mallireddy and Prathik Kumar
// 5-5-2023
// Collision.java (Explorador Español)
// Week 3: Before this, the user could walk through trees and walls and over water. This class
// and sparsed out lines in other classes prevent the user from doing this.

/// Hrithik Mallireddy did this entire class.
public class Collision
{
    private GamePanel gp;  // instance of GamePanel to access other methods
    private GameConstants gc;  // instance of GameConstants to access other methods
    private MapLayOut mp;  // instance of MapLayOut to access other methods
    private String directionPressed;  // direction that saves which arrow user clicked
    private int level; // int that tracks the level
    private DisplayGameAssets dga;  // instance of class to delete coin

    public Collision(GamePanel gpIn, int levelIn)
    {
        level = levelIn;
        gp = gpIn;
        gc = new GameConstants();
        mp = new MapLayOut(gp, level);
        dga = new DisplayGameAssets(gp, level);
        directionPressed = "";
    }

    // checkTile() finds what tile the user is on. We created an imaginary rectangle
    // around the user that is smaller than the tile so the user can walk through
    // 1-tile wide paths. We check which direction the user is going and check
    // which tile they are on, and send back true or false accordingly.
    public boolean checkTile()
    {

        int playerWorldLeftX = gc.getTileSize() * 23 + gp.getLocationX() - 8;
        int playerWorldRightX = gc.getTileSize() * 23 + gp.getLocationX() + 8;
        int playerWorldTopY = gc.getTileSize() * 21 + gp.getLocationY() - 8;
        int playerWorldDownY = gc.getTileSize() * 21 + gp.getLocationY() + 8;

        int playerLeftCol = playerWorldLeftX/gc.getTileSize();
        int playerRightCol = playerWorldRightX/gc.getTileSize();
        int playerTopRow = playerWorldTopY/gc.getTileSize();
        int playerBottomRow = playerWorldDownY/gc.getTileSize();

        int tileNum1, tileNum2;

        if(directionPressed.equals("up"))
        {
            tileNum1 = mp.getMapTileNum(playerLeftCol, playerTopRow);
            tileNum2 = mp.getMapTileNum(playerRightCol, playerTopRow);
            if(mp.getCollision(tileNum1) == true && mp.getCollision(tileNum2) == true)
                return true;
        }
        else if(directionPressed.equals("down"))
        {
            tileNum1 = mp.getMapTileNum(playerLeftCol, playerBottomRow);
            tileNum2 = mp.getMapTileNum(playerRightCol, playerBottomRow);
            if(mp.getCollision(tileNum1) == true && mp.getCollision(tileNum2) == true)
                return true;
        }
        else if(directionPressed.equals("left"))
        {
            tileNum1 = mp.getMapTileNum(playerLeftCol, playerTopRow);
            tileNum2 = mp.getMapTileNum(playerLeftCol, playerBottomRow);
            if(mp.getCollision(tileNum1) == true && mp.getCollision(tileNum2) == true)
                return true;
        }
        else if(directionPressed.equals("right"))
        {
            tileNum1 = mp.getMapTileNum(playerRightCol, playerTopRow);
            tileNum2 = mp.getMapTileNum(playerRightCol, playerBottomRow);
            if(mp.getCollision(tileNum1) == true && mp.getCollision(tileNum2) == true)
                return true;
        }
        return false;
    }

    // setDirection() receives the direction that the user clicked on and sets the
    // direction variable in this class to that.
    public void setDirection(String directionIn)
    {
        directionPressed = directionIn;
    }

    // checkCoin() checks where the user is, and if that corresponds with a coin that
    // is set in GamePanel and DisplayGameAssets.
    public void checkCoin()
    {
        Coin[] coins = gp.getCoins();
        int counter = 0;
        for(int i = 0; i < coins.length; i++)
        {
            if(level == 1 && counter < 10)
            {
                int locationX = gp.getMapLayOutX();
                int locationY = gp.getMapLayOutY();
                if(locationX >= coins[i].getMapX() - 8 && locationX <= coins[i].getMapX() + 8)
                {
                    if(locationY >= coins[i].getMapY() - 8 && locationY <= coins[i].getMapY() + 8)
                    {
                        QuestionPanel qp = new QuestionPanel(gp);
                        qp.runner();
                        dga.deleteCoin(i);
                    }
                }
                counter++;
            }
            else if(level == 2 && counter < 10)
            {
                int locationX = gp.getMapLayOutX();
                int locationY = gp.getMapLayOutY();
                if(locationX >= coins[i].getMapX() - 8 && locationX <= coins[i].getMapX() + 8)
                {
                    if(locationY >= coins[i].getMapY() - 8 && locationY <= coins[i].getMapY() + 8)
                    {
                        QuestionPanel qp = new QuestionPanel(gp);
                        qp.runner();
                        dga.deleteCoin(i);
                    }
                }
                counter++;
            }
            else if(level == 3 && counter < 15)
            {
                int locationX = gp.getMapLayOutX();
                int locationY = gp.getMapLayOutY();
                if(locationX >= coins[i].getMapX() - 8 && locationX <= coins[i].getMapX() + 8)
                {
                    if(locationY >= coins[i].getMapY() - 8 && locationY <= coins[i].getMapY() + 8)
                    {
                        QuestionPanel qp = new QuestionPanel(gp);
                        qp.runner();
                        dga.deleteCoin(i);
                    }
                }
                counter++;
            }
            else if(level == 4 && counter < 20)
            {
                int locationX = gp.getMapLayOutX();
                int locationY = gp.getMapLayOutY();
                if(locationX >= coins[i].getMapX() - 8 && locationX <= coins[i].getMapX() + 8)
                {
                    if(locationY >= coins[i].getMapY() - 8 && locationY <= coins[i].getMapY() + 8)
                    {
                        QuestionPanel qp = new QuestionPanel(gp);
                        qp.runner();
                        dga.deleteCoin(i);
                    }
                }
                counter++;
            }
        }
    }
}
