import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ScoreTracker 
{
    private String[][] nameScores;
    private int allPlayers;
    private final String filePath = "scores.txt";

    //Load player score information from a file, sorts data by time.
    public String[][] loadScores() 
    {
        try
        {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            // Count the number of lines in the file
            int lineCount = 0;
            while (scanner.hasNextLine()) 
            {
                scanner.nextLine();
                lineCount++;
            }
            scanner.close();
            // Initialize the array with the correct size
            nameScores = new String[lineCount+1][2];
            // Populate the array with scores
            scanner = new Scanner(file);
            int row = 0;
            while (scanner.hasNextLine()) 
            {
                String line = scanner.nextLine();
                int commaPos = line.indexOf(',');
                nameScores[row][0] = line.substring(0, commaPos);
                nameScores[row][1] = line.substring(commaPos + 1);
                row++;
            }
            scanner.close();

            allPlayers = lineCount;

            return sortByTime(nameScores, allPlayers);
        }
        catch (IOException e) 
        {
            System.out.println("ERROR LOADING SCORE FILE");
            return null;
        }
    }

    //Keeps name as is and we are sorting the data based on the times by the user
    public String[][] sortByTime(String[][] nameScores, int rows)
    {
        int cols = nameScores[0].length;
        for (int i = 0; i < rows; i++) 
        {
            for (int j = 0; j < rows - 1; j++) 
            {
                double time1 = Double.parseDouble(nameScores[j][1]);
                double time2 = Double.parseDouble(nameScores[j + 1][1]);
                if (time1 > time2) 
                {
                    String[] temp = nameScores[j];
                    nameScores[j] = nameScores[j + 1];
                    nameScores[j + 1] = temp;
                }
            }
        }
        return nameScores;
    }

    //Writes data to file with information of player who started the game.
    public void writeToFile(String[][] nameScores, int count)
    {
        File file = new File("scores.txt");
        try 
        {
            PrintWriter printWriter = new PrintWriter(file);
            for (int i = 0; i < count; i++) 
            {
                System.out.println(nameScores[i][0] + "," + nameScores[i][1]);
                printWriter.println(nameScores[i][0] + "," + nameScores[i][1]);
			}
            printWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Error writing name to scores.txt");
        }
    }

    private int validPlayerCount(String[][] nameScores)
    {
        int rows = 0;
        for (int i =0; i < nameScores.length;i++)
        {
            if (nameScores[i][0]!=null)
                rows++;
        }
        return rows;
    }

    public String[][] getNameScores() 
    {
        return nameScores;
    }

    public void setNameScores(String[][] nameScores) 
    {
        this.nameScores = nameScores;
    }

    public int getAllPlayers() 
    {
        return allPlayers;
    }

    public void setAllPlayers(int allPlayers) 
    {
        this.allPlayers = allPlayers;
    }
}



