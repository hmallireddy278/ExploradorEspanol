// Prathik Kumar and Hrithik Mallireddy
// 5/4/2023
// GameConstants.java (Explorador Español)
// Working on:
// Week 3: This is our getter class so that we can access all the constants in our game from this class

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// Hrithik and Prathik both worked on this.
public class GameConstants
{
    private final int originalTileSize = 16; //16x16, default map tile size for all images put together in the map
    private final int scale = 3; //We need to scale it for the tiles display with better resolution in the screen
    private final int tileSize = originalTileSize * scale; //48x48 tile, after scaling

    //Number of images/tiles we can display on the screen both horizontally and vertically
    private final int maxScreenCol = 16; //16 tiles horizontally
    private final int maxScreenRow = 12; //12 tiles vertically

    //Map Settings to inplement camera view
    private final int mapCol = 50; // max map col is 50, 50 numbers can fit in txt file in one col
    private final int mapRow = 50; // max map row is 50, 50 numbers can fit in txt file in one row

    private final int screenWidth = tileSize * maxScreenCol; //TIle Size times Max Col width, 768 pixels
    private final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    private final int mapLayOutX = tileSize * 23; // player pos in the beginning of game, X coordinate

    private final int mapLayOutY = tileSize * 21; // player pos in the beginning of game, Y coordinate

    //ScreenX and ScreenY is the players starting postion in the map.
    private final int screenX = screenWidth/2 - (tileSize/2); //This returns the middle point in the screen, X Coordinate
    private final int screenY = screenHeight/2 - (tileSize/2); //This returns middle point in the screen, Y coordinate

    // imaginary box in player that helps with collision
    private final int rectangleX = 8; // x coord top left
    private final int rectangleY = 16; // y coord top left
    private final int rectangleHeight = 32; // height of rectangle
    private final int rectangleWidth = 32; // width of rectangle
    private int score;

    public GameConstants() 
    {
    }

    // All the following below are getter methods
    public int getOriginalTileSize()
    {
        return originalTileSize;
    }

    public int getScale()
    {
        return scale;
    }

    public int getTileSize()
    {
        return tileSize;
    }

    public int getMaxScreenCol()
    {
        return maxScreenCol;
    }

    public int getMaxScreenRow()
    {
        return maxScreenRow;
    }

    public int getScreenHeight()
    {
        return screenHeight;
    }


    public int getScreenWidth()
    {
        return screenWidth;
    }

    public int getMapLayOutX()
    {
        return mapLayOutX;
    }

    public int getMapLayOutY()
    {
        return mapLayOutY;
    }


    public int getMapCol()
    {
        return mapCol;
    }

    public int getMapRow()
    {
        return mapRow;
    }

    public int getScreenX()
    {
        return screenX;
    }

    public int getScreenY()
    {
        return screenY;
    }

    public int getRectangleX()
    {
        return rectangleX;
    }

    public int getRectangleY()
    {
        return rectangleY;
    }

    public int getRectangleWidth()
    {
        return rectangleWidth;
    }

    public int getRectangleHeight()
    {
        return rectangleHeight;
    }

    // getImage() uses ImageIO to load the image into a variable.
    /// Hrithik Mallireddy wrote this method.
    public Image getImage(String fileName)
    {
        Image picture = null;
        File pictFile = new File(fileName);
        try
        {
            picture = ImageIO.read(pictFile);
        }
        catch(IOException e)
        {
            System.err.println("/n/nERROR: " + fileName + " can't be found./n/n");
            e.printStackTrace();
        }
        return picture;
    }
}
