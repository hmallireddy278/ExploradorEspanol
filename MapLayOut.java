// Prathik Kumar and Hrithik Mallireddy
// 5/4/2023
// MapLayOut.java (Explorador Español)
// Working on:
// Week 2: This class manages tiles, we can load tiles based on numbers in a txt file
// We are loading tiles and the text file system is working well.
// Practicing: ImageIO, Components(JButtons, MenuBar), and different layouts, like border,
// grid, and flow layout. Practicing using handler classes as well

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.Scanner;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Image;
import java.io.File;
import java.util.Scanner;

/// Prathik did this entire class besides collision logic
public class MapLayOut
{
    private Image[] tileImages; // tile array holding all tile images
    public int mapTileNum1[][]; // 2d array with number version of the map for level1
    public int mapTileNum2[][]; // 2d array with number version of the map for level2
    public int mapTileNum3[][]; // 2d array with number version of the map for level2
    public int mapTileNum4[][]; // 2d array with number version of the map for level2
    private GamePanel gp; // game panel obj
    private final int maxScreenCol = 16; // max tiles that can fit in screen col
    private final int maxScreenRow = 12; // max tiles that can fit in screen row
    private Image[] coinImages; // array of coin images

    private GameConstants gameConstants; // game constants obj
    private int level; // tracks level set in GamePanel

    public MapLayOut(GamePanel gp, int levelIn)
    {
        gameConstants = new GameConstants();
        this.gp = gp;
        level = levelIn;
        tileImages = new Image[10];
        mapTileNum1 = new int[gameConstants.getMapCol()][gameConstants.getMapRow()];
        mapTileNum2 = new int[gameConstants.getMapCol()][gameConstants.getMapRow()];
        mapTileNum3 = new int[gameConstants.getMapCol()][gameConstants.getMapRow()];
        mapTileNum4 = new int[gameConstants.getMapCol()][gameConstants.getMapRow()];
        String file = "level2.txt";
         if (level == 1)
             file = "level1.txt";
         else if (level == 2)
             file = "level2.txt";
         else if (level == 3)
             file = "level3.txt";
         else if (level == 4)
             file = "level4.txt";

        loadMap(file, level);
        getTileImage();
        coinImages = new Image[5];
    }

    // loads all the different tile images we are using in our game onto our tile array
    /// Prathik Kumar did this
    public void getTileImage()
    {
        tileImages[0] = gameConstants.getImage("grass3.png");
        tileImages[1] = gameConstants.getImage("wall.png");
        tileImages[2] = gameConstants.getImage("water3.png");
        tileImages[3] = gameConstants.getImage("earth.png");
        tileImages[4] = gameConstants.getImage("treeE.png");
        tileImages[5] = gameConstants.getImage("sand.png");
    }

    // getCollison() checks which tile the user is on using the tileImages array in MapLayOut. It sends back a boolean based on if collision should be true or false.
    /// Hrithik Malireddy did this method
    public boolean getCollision(int tileNum)
    {
        if(tileNum == 1 || tileNum == 2 || tileNum == 4)
            return true;
        else
            return false;
    }

    //Reads Text file from map file to load tileImages for water, grass & wall.
    //Scans text file line by line and get that number to load tileImages based on index
    /// Prathik Kumar did this
    public void loadMap(String filePath, int level)
    {
        try
        {
            File fileText = new File(filePath);
            Scanner fileScanner = new Scanner(fileText);
            int col = 0;
            int row = 0;
            while (col < gameConstants.getMapCol() && row < gameConstants.getMapRow())
            {
                String line = fileScanner.nextLine();

                while (col < gameConstants.getMapCol())
                {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    if (level == 1)
                    {
                        mapTileNum1[col][row] = num;
                    }
                    else if (level == 2)
                    {
                        mapTileNum2[col][row] = num;
                    }
                    else if (level == 3)
                    {
                        mapTileNum3[col][row] = num;
                    }
                    else if (level == 4)
                    {
                        mapTileNum4[col][row] = num;
                    }
                    col++;
                }
                if(col == gameConstants.getMapCol())
                {
                    col = 0;
                    row++;
                }
            }
            fileScanner.close();
            
        }
        catch (Exception e)
        {
            System.out.println("ERROR LOADING FILE FOR" + level);
        }
    }

    // for drawing the coin image
    /// Prathik Kumar did this
    public Image setCoinImage()
    {
        return gameConstants.getImage("coin.png");
    }

    /*
     * This method goes over all the elements in the map file and gets the index and loads the tile images
     * for the map. We are doing
     */
    /// Prathik Kumar did this
    public void draw(Graphics2D g2)
    {
        level = gp.getCurrrentLevel();
        loadMap("level" + level + ".txt", level);
        int wordCol = 0;
        int worldRow = 0;

        while (wordCol < gameConstants.getMapCol() && worldRow < gameConstants.getMapRow())
        {
            int tileNum  = -1;
            if (level == 1)
                tileNum = mapTileNum1[wordCol][worldRow];
            else if (level == 2){
                tileNum = mapTileNum2[wordCol][worldRow];
            }
            else if (level == 3)
                tileNum = mapTileNum3[wordCol][worldRow];
            else if (level == 4)
                tileNum = mapTileNum4[wordCol][worldRow];

            int worldX = wordCol * gameConstants.getTileSize(); // 50 * 48
            int worldY = worldRow * gameConstants.getTileSize();

            int screenX = worldX - gp.getMapLayOutX() + gameConstants.getScreenX();
            int screenY = worldY - gp.getMapLayOutY() + gameConstants.getScreenY();

            g2.drawImage(tileImages[tileNum], screenX, screenY, gameConstants.getTileSize(), gameConstants.getTileSize(), null);

            wordCol++;
            if (wordCol == gameConstants.getMapCol())
            {
                wordCol = 0;
                worldRow++;
            }
        }
    }

    // setter method
    public void setLevel(int levelIn)
    {
        this.level = levelIn;
    }

    // getter method
    /// Hrithik Malireddy did this method
    public int getMapTileNum(int playerColIn, int playerRowIn)
    {
        if(level == 1)
            return mapTileNum1[playerColIn][playerRowIn];
        else if(level == 2)
            return mapTileNum2[playerColIn][playerRowIn];
        else if(level == 3)
            return mapTileNum3[playerColIn][playerRowIn];
        else if(level == 4)
            return mapTileNum4[playerColIn][playerRowIn];
        else
            return 0;
    }
}
