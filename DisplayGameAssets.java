// Prathik Kumar and Hrithik Mallireddy
// 5/4/2023
// DisplayGameAssets.java (Explorador Español)
// Working on:
// Week 3: Has coin array that calls other methods in other classes to draw the coin

/// Prathik Kumar did the constructor and setUpGameObjects(), but Hrithik Mallireddy
/// did deleteCoin().
public class DisplayGameAssets
{
    private GamePanel gamePanel; // GamePanel obj to call the drawing coin method in game panel
    private int level;
    public DisplayGameAssets(GamePanel gp, int level)
    {
        this.gamePanel = gp;
        this.level = level;
    }

    /* calls the get coin method in coin.java, has array with multiple indexes
     * with the same coin image in each index, but diff coordinates
     * are assigned to the coin image
     */
    public void setUpGameObjects()
    {
        if (level == 1)
        {
            gamePanel.getCoins()[0] = new Coin(gamePanel);
            gamePanel.getCoins()[0].setMapX(200);
            gamePanel.getCoins()[0].setMapY(200);

            gamePanel.getCoins()[1] = new Coin(gamePanel);
            gamePanel.getCoins()[1].setMapX(700);
            gamePanel.getCoins()[1].setMapY(700);

            gamePanel.getCoins()[2] = new Coin(gamePanel);
            gamePanel.getCoins()[2].setMapX(500);
            gamePanel.getCoins()[2].setMapY(500);

            gamePanel.getCoins()[3] = new Coin(gamePanel);
            gamePanel.getCoins()[3].setMapX(300);
            gamePanel.getCoins()[3].setMapY(300);

            gamePanel.getCoins()[4] = new Coin(gamePanel);
            gamePanel.getCoins()[4].setMapX(1120);
            gamePanel.getCoins()[4].setMapY(1120);

            gamePanel.getCoins()[5] = new Coin(gamePanel);
            gamePanel.getCoins()[5].setMapX(2300);
            gamePanel.getCoins()[5].setMapY(2300);

            gamePanel.getCoins()[6] = new Coin(gamePanel);
            gamePanel.getCoins()[6].setMapX(1120);
            gamePanel.getCoins()[6].setMapY(1130);

            gamePanel.getCoins()[7] = new Coin(gamePanel);
            gamePanel.getCoins()[7].setMapX(1130);
            gamePanel.getCoins()[7].setMapY(1130);

            gamePanel.getCoins()[8] = new Coin(gamePanel);
            gamePanel.getCoins()[8].setMapX(1100);
            gamePanel.getCoins()[8].setMapY(1100);

            gamePanel.getCoins()[9] = new Coin(gamePanel);
            gamePanel.getCoins()[9].setMapX(1090);
            gamePanel.getCoins()[9].setMapY(1090);
        }
        else if (level == 2)
        {
            gamePanel.getCoins()[0] = new Coin(gamePanel);
            gamePanel.getCoins()[0].setMapX(288);
            gamePanel.getCoins()[0].setMapY(288);

            gamePanel.getCoins()[1] = new Coin(gamePanel);
            gamePanel.getCoins()[1].setMapX(700);
            gamePanel.getCoins()[1].setMapY(700);

            gamePanel.getCoins()[2] = new Coin(gamePanel);
            gamePanel.getCoins()[2].setMapX(500);
            gamePanel.getCoins()[2].setMapY(500);

            gamePanel.getCoins()[3] = new Coin(gamePanel);
            gamePanel.getCoins()[3].setMapX(320);
            gamePanel.getCoins()[3].setMapY(320);

            gamePanel.getCoins()[4] = new Coin(gamePanel);
            gamePanel.getCoins()[4].setMapX(1296);
            gamePanel.getCoins()[4].setMapY(1248);

            gamePanel.getCoins()[5] = new Coin(gamePanel);
            gamePanel.getCoins()[5].setMapX(1920);
            gamePanel.getCoins()[5].setMapY(1920);

            gamePanel.getCoins()[6] = new Coin(gamePanel);
            gamePanel.getCoins()[6].setMapX(552);
            gamePanel.getCoins()[6].setMapY(552);

            gamePanel.getCoins()[7] = new Coin(gamePanel);
            gamePanel.getCoins()[7].setMapX(1152);
            gamePanel.getCoins()[7].setMapY(1152);

            gamePanel.getCoins()[8] = new Coin(gamePanel);
            gamePanel.getCoins()[8].setMapX(2248);
            gamePanel.getCoins()[8].setMapY(2248);

            gamePanel.getCoins()[9] = new Coin(gamePanel);
            gamePanel.getCoins()[9].setMapX(1056);
            gamePanel.getCoins()[9].setMapY(1056);
        }
        else if (level == 3)
        {
            gamePanel.getCoins()[0] = new Coin(gamePanel);
            gamePanel.getCoins()[0].setMapX(192);
            gamePanel.getCoins()[0].setMapY(192);

            gamePanel.getCoins()[1] = new Coin(gamePanel);
            gamePanel.getCoins()[1].setMapX(1440);
            gamePanel.getCoins()[1].setMapY(1584);

            gamePanel.getCoins()[2] = new Coin(gamePanel);
            gamePanel.getCoins()[2].setMapX(2064);
            gamePanel.getCoins()[2].setMapY(1584);

            gamePanel.getCoins()[3] = new Coin(gamePanel);
            gamePanel.getCoins()[3].setMapX(96);
            gamePanel.getCoins()[3].setMapY(480);

            gamePanel.getCoins()[4] = new Coin(gamePanel);
            gamePanel.getCoins()[4].setMapX(912);
            gamePanel.getCoins()[4].setMapY(192);

            gamePanel.getCoins()[5] = new Coin(gamePanel);
            gamePanel.getCoins()[5].setMapX(720);
            gamePanel.getCoins()[5].setMapY(480);

            gamePanel.getCoins()[6] = new Coin(gamePanel);
            gamePanel.getCoins()[6].setMapX(432);
            gamePanel.getCoins()[6].setMapY(720);

            gamePanel.getCoins()[7] = new Coin(gamePanel);
            gamePanel.getCoins()[7].setMapX(624);
            gamePanel.getCoins()[7].setMapY(960);

            gamePanel.getCoins()[8] = new Coin(gamePanel);
            gamePanel.getCoins()[8].setMapX(96);
            gamePanel.getCoins()[8].setMapY(1248);

            gamePanel.getCoins()[9] = new Coin(gamePanel);
            gamePanel.getCoins()[9].setMapX(2064);
            gamePanel.getCoins()[9].setMapY(2112);

            gamePanel.getCoins()[10] = new Coin(gamePanel);
            gamePanel.getCoins()[10].setMapX(336);
            gamePanel.getCoins()[10].setMapY(928);

            gamePanel.getCoins()[11] = new Coin(gamePanel);
            gamePanel.getCoins()[11].setMapX(1008);
            gamePanel.getCoins()[11].setMapY(1776);

            gamePanel.getCoins()[12] = new Coin(gamePanel);
            gamePanel.getCoins()[12].setMapX(864);
            gamePanel.getCoins()[12].setMapY(240);

            gamePanel.getCoins()[13] = new Coin(gamePanel);
            gamePanel.getCoins()[13].setMapX(1632);
            gamePanel.getCoins()[13].setMapY(1778);

            gamePanel.getCoins()[14] = new Coin(gamePanel);
            gamePanel.getCoins()[14].setMapX(384);
            gamePanel.getCoins()[14].setMapY(732);
        }
        else if (level == 4)
        {
            gamePanel.getCoins()[0] = new Coin(gamePanel);
            gamePanel.getCoins()[0].setMapX(192);
            gamePanel.getCoins()[0].setMapY(192);

            gamePanel.getCoins()[1] = new Coin(gamePanel);
            gamePanel.getCoins()[1].setMapX(1440);
            gamePanel.getCoins()[1].setMapY(1584);

            gamePanel.getCoins()[2] = new Coin(gamePanel);
            gamePanel.getCoins()[2].setMapX(2064);
            gamePanel.getCoins()[2].setMapY(1584);

            gamePanel.getCoins()[3] = new Coin(gamePanel);
            gamePanel.getCoins()[3].setMapX(96);
            gamePanel.getCoins()[3].setMapY(480);

            gamePanel.getCoins()[4] = new Coin(gamePanel);
            gamePanel.getCoins()[4].setMapX(912);
            gamePanel.getCoins()[4].setMapY(192);

            gamePanel.getCoins()[5] = new Coin(gamePanel);
            gamePanel.getCoins()[5].setMapX(720);
            gamePanel.getCoins()[5].setMapY(480);

            gamePanel.getCoins()[6] = new Coin(gamePanel);
            gamePanel.getCoins()[6].setMapX(432);
            gamePanel.getCoins()[6].setMapY(720);

            gamePanel.getCoins()[7] = new Coin(gamePanel);
            gamePanel.getCoins()[7].setMapX(624);
            gamePanel.getCoins()[7].setMapY(960);

            gamePanel.getCoins()[8] = new Coin(gamePanel);
            gamePanel.getCoins()[8].setMapX(96);
            gamePanel.getCoins()[8].setMapY(1248);

            gamePanel.getCoins()[9] = new Coin(gamePanel);
            gamePanel.getCoins()[9].setMapX(2064);
            gamePanel.getCoins()[9].setMapY(2112);

            gamePanel.getCoins()[10] = new Coin(gamePanel);
            gamePanel.getCoins()[10].setMapX(336);
            gamePanel.getCoins()[10].setMapY(928);

            gamePanel.getCoins()[11] = new Coin(gamePanel);
            gamePanel.getCoins()[11].setMapX(1008);
            gamePanel.getCoins()[11].setMapY(1776);

            gamePanel.getCoins()[12] = new Coin(gamePanel);
            gamePanel.getCoins()[12].setMapX(864);
            gamePanel.getCoins()[12].setMapY(240);

            gamePanel.getCoins()[13] = new Coin(gamePanel);
            gamePanel.getCoins()[13].setMapX(1632);
            gamePanel.getCoins()[13].setMapY(1778);

            gamePanel.getCoins()[14] = new Coin(gamePanel);
            gamePanel.getCoins()[14].setMapX(384);
            gamePanel.getCoins()[14].setMapY(732);
            gamePanel.getCoins()[15] = new Coin(gamePanel);
            gamePanel.getCoins()[15].setMapX(450);
            gamePanel.getCoins()[15].setMapY(450);

            gamePanel.getCoins()[16] = new Coin(gamePanel);
            gamePanel.getCoins()[16].setMapX(250);
            gamePanel.getCoins()[16].setMapY(250);

            gamePanel.getCoins()[17] = new Coin(gamePanel);
            gamePanel.getCoins()[17].setMapX(750);
            gamePanel.getCoins()[17].setMapY(750);

            gamePanel.getCoins()[18] = new Coin(gamePanel);
            gamePanel.getCoins()[18].setMapX(800);
            gamePanel.getCoins()[18].setMapY(800);

            gamePanel.getCoins()[19] = new Coin(gamePanel);
            gamePanel.getCoins()[19].setMapX(475);
            gamePanel.getCoins()[19].setMapY(475);
        }
    }

    // deleteCoin() "deletes" the coin after the user walks on it. In
    // reality, it does this by just moving the coin outside of the map
    // where the player can't see it.
    /// Hrithik Mallireddy wrote this method.
    public void deleteCoin(int whichCoin)
    {
        gamePanel.getCoins()[whichCoin].setMapX(-2000);
        gamePanel.getCoins()[whichCoin].setMapY(-2000);
        gamePanel.repaint();
    }
}

