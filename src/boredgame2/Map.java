package boredgame2;

import java.util.Random;

public class Map{
    private static int WIDTH;
    private static int DEPTH;
    private static int[][] Level1MapGrid;
    private static int[][] Level2MapGrid;
    
    private static int[][] mapGrid = Level1MapGrid;
    Game aGame;
    
public Map(int W, int D, int level)
{
    WIDTH = W;
    DEPTH = D;
    switch(level){
        case 1:
        Level1MapGrid = new int[][]{
        {0, 0, 9, 8, 0, 0, 8, 8},
        {9, 0, 0, 0, 0, 0, 0, 0},
        {9, 0, 9, 0, 0, 0, 0, 0},
        {9, 0, 9, 8, 8, 0, 0, 0},
        {9, 0, 9, 9, 9, 9, 9, 9},
        {0, 0, 8, 0, 0, 0, 0, 8},
        {8, 0, 8, 0, 0, 0, 0, 0},
        {8, 0, 0, 0, 8, 8, 0, 0},
        {8, 0, 0, 0, 9, 9, 0, 9},
        {0, 0, 0, 0, 0, 0, 0, 8},
        {9, 0, 9, 9, 9, 9, 9, 9}};
        mapGrid = Level1MapGrid;
        break;
        case 2:
        Level2MapGrid = new int[][]{
        {9, 9, 9, 8, 8, 0, 8, 8},
        {9, 0, 0, 0, 0, 0, 8, 0},
        {9, 9, 9, 0, 0, 0, 8, 0},
        {9, 8, 9, 8, 8, 0, 8, 0},
        {9, 0, 9, 9, 9, 9, 9, 8},
        {9, 0, 9, 9, 0, 9, 0, 8},
        {8, 8, 9, 8, 0, 8, 0, 8},
        {8, 0, 9, 9, 8, 8, 8, 0},
        {8, 8, 8, 9, 9, 9, 0, 9},
        {0, 0, 8, 8, 0, 0, 0, 8},
        {9, 0, 9, 9, 9, 9, 9, 9}};
        mapGrid = Level2MapGrid;
        break;    
        case 3:
        Level2MapGrid = new int[][]{
        {9, 9, 9, 9, 9, 9, 9, 9},
        {9, randomPowerup(), 0, 8, 8, 0, randomPowerup(), 9},
        {9, 0, 0, 8, 8, 0, 0, 9},
        {9, 8, 8, 8, 8, 8, 8, 9},
        {9, 0, 0, 8, 8, 0, 0, 9},
        {9, 8, 8, 8, 8, 8, 8, 9},
        {9, 0, 0, 8, 8, 0, 0, 9},
        {9, 8, 8, 8, 8, 8, 8, 9},
        {9, 0, 0, 8, 8, 0, 0, 9},
        {9, randomPowerup(), 0, 8, 8, 0, randomPowerup(), 9},
        {9, 9, 9, 9, 9, 9, 9, 9}};
        mapGrid = Level2MapGrid;
        break;            
    }
    
    
}

    public static int randomPowerup() {
        Random generatorRP = new Random();
        int x = ((generatorRP.nextInt(3)+10));
        return x;
    }

public void setUpMap()
{
int[][] mapGrid = Level2MapGrid;
}

    /// methods
    ///setGame
    public void setGame(Game thisGame) {
        
        aGame = thisGame;
    }

    ///set map positions
    public void setMapPosition(int x, int y, int i) {
        mapGrid[x][y] = i;
    }

    ///get map positions
    public int getMapPosition(int x, int y) {
        return mapGrid[x][y];
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getDEPTH() {
        return DEPTH;
    }
        
    public static int[] getRandomEmptySquare() {
        Random generator = new Random();
        int x = generator.nextInt(DEPTH);
        int y = generator.nextInt(WIDTH);
        while (mapGrid[x][y] != 0) {
            x = generator.nextInt(DEPTH);
            y = generator.nextInt(WIDTH);
        }
        ///System.out.println("Square X:" +x+ " Y:" +y+ " is empty.");
        int xy[] = new int[]{x, y};
        return xy;
    }
}
