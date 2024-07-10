// Prathik Kumar and Hrithik Mallireddy
// 5/4/2023
// Coin.java (Explorador Español)
// Working on:
// Week 3: Full of getter and setter methods to add map x and map y values
// to the coins in the coin array. We are also drawing the coin.

import java.awt.Graphics2D;
import java.awt.Image;


//Access Coding Object and drawing coins as needed
/// Prathik Kumar and Hrithik Mallireddy did this.
public class Coin
{
    private GamePanel gp; //game panel obj to access
    private GameConstants gameConstants; // game constants obj to access game constants variables
    private Image coin;

    private int mapX, mapY; // map x and map y coords of the coin

    public Coin(GamePanel gamePanel)
    {
        this.gp = gamePanel;
        gameConstants = new GameConstants();
        setCoinImage();
    }

    // Getter and setter methods
    // Hrithik did the first two methods and Prathik did the rest.
    public void setCoinImage()
    {
        coin = gameConstants.getImage("coin1.png");
    }

    public Image getCoinImage()
    {
        return coin;
    }

    public int getMapX()
    {
        return mapX;
    }

    public void setMapX(int mapX)
    {
        this.mapX = mapX;
    }

    public int getMapY()
    {
        return mapY;
    }

    public void setMapY(int mapY)
    {
        this.mapY = mapY;
    }

    // draws the coin onto the screen, draws based on player's view of the game not just the map grid system
    public void draw(Graphics2D g2, GamePanel gp)
    {
        int screenX = getMapX() - gp.getMapLayOutX() + gameConstants.getScreenX();
        int screenY = getMapY() - gp.getMapLayOutY() + gameConstants.getScreenY();
        g2.drawImage(getCoinImage(), screenX, screenY, gameConstants.getTileSize(), gameConstants.getTileSize(), null);
    }
}
