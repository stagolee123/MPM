package boredgame2;

import java.util.ArrayList;
import java.util.Random;

public class testGame {
    ///static int round = 0;
    static Map aMap; 
    static ArrayList usedNumbers;
    static Player[] playerArray;
    static Game aGame;
    static int roundCount = 0;
    static String[] playerNames = {"Sam", "Jeff", "Carl", "Dara", "Richie", "Don", "Lucas", "Crevan"};
    static Random generator;
    static Player activePlayer;
    static int turn;
    /**
     * @param args the command line arguments
     */
    public static void main(String[]args) throws InterruptedException {
        runGame();
        }
        
        public static void runGame()throws InterruptedException 
        {
        aMap = new Map(8,11,1);
        /// Create four Players
        createPlayers();
        
        ///create game
        if(roundCount==0)
        {
        aGame = new Game(playerArray);
        aGame.restartButton.setVisible(false);
        aGame.restartButton.setEnabled(false);
            javax.swing.SwingUtilities.invokeLater(new Runnable() {    
            public void run() {     
                aGame.createAndShowGUI();
            }
        });
        }
        else
        {
            System.out.println("Round " + (roundCount+1));
        }
   
        initialiseGame();
       
        if (roundCount == 0)
        {
        activePlayer = aGame.selectPlayer(); 
        }
        else
        {
        activePlayer = aGame.getAPlayer(0);  
        }
        
        aGame.setArea2("You are " + (activePlayer.getName()) + " (" + (activePlayer.getId()) + ")"+ "\n");
        
        System.out.println("this far?");   
       
        runLevel(aGame);
    }
        
    public static void runLevel(Game aGame)throws InterruptedException
    {
//            for(int a=0;a<4;a++)
//            {
//                System.out.println("Player "+a+" is:"+ aGame.getAPlayer(a).getName()+" or " + aGame.getAPlayer(a));
//            }
                System.out.println("Player 1 health: " + activePlayer.getHealth()+", aGame injuredCount: " + aGame.getInjuredCount());
                System.out.println("turn: "+turn);
                
                int playerOneNumber = (activePlayer.getId()-1);
                int playerTwoNumber;
                int playerThreeNumber;
                int playerFourNumber;
                if((playerOneNumber +1) == 4)
                        {
                            playerTwoNumber = (0);
                        }
                else 
                        {
                            playerTwoNumber = (playerOneNumber+1); 
                        }
                if((playerTwoNumber +1) == 4)
                        {
                            playerThreeNumber = (0);
                        }
                else 
                        {
                            playerThreeNumber = (playerTwoNumber+1); 
                        }
                if((playerThreeNumber +1) == 4)
                        {
                            playerFourNumber = (0);
                        }
                else 
                        {
                            playerFourNumber = (playerThreeNumber+1); 
                        }
                
                System.out.println("active player ID=" + playerOneNumber + "player 2 ID=" + playerTwoNumber+ "player 3 ID=" + playerThreeNumber+ "player 4 ID=" + playerFourNumber);
                
                
                
            while ((activePlayer.getHealth() > 1) && (aGame.getInjuredCount() < 3)) {

            switch (turn) {
                case 1:
                    aGame.playButtonsOn();
                    int go = 1;
                    while(go < 4)
                    {
                    System.out.println("A Active player go: " + go);
                    go ++;
                    aGame.takeTurn();
                    }
                    turn = 2;
                    break;
                case 2:
                    aGame.playButtonsOff();
                    if (aGame.getAPlayer(playerTwoNumber).getHealth()>1)
                    {
                    go = 1;
                    while(go < 4)
                    {
                    System.out.println("Player "+playerTwoNumber+" go: " + go);
                    try {
                        Thread.sleep(500);
                        System.out.println("Sleeping...");
                        } 
                    catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        }
                    aGame.getAPlayer(playerTwoNumber).randomMove(aGame.getAPlayer(playerTwoNumber));
                    go ++;
                    }
                    }
                    ///aGame.getRandomPlayer().randomMove(aGame.getRandomPlayer());
                    turn = 3;
                    break;
                case 3:
                    aGame.playButtonsOff();
                    if (aGame.getAPlayer(playerThreeNumber).getHealth()>1)
                    {
                    go = 1;
                    while(go < 4)
                    {
                    System.out.println("Player "+playerThreeNumber+" go: " + go);
                    try {
                        Thread.sleep(500);
                        System.out.println("Sleeping...");
                        } 
                    catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        }
                    aGame.getAPlayer(playerThreeNumber).randomMove(aGame.getAPlayer(playerThreeNumber));
                    go++;
                    }
                    }
                    turn = 4;
                    break;
                case 4:
                    aGame.playButtonsOff();
                    if (aGame.getAPlayer(playerFourNumber).getHealth()>1)
                    {
                    go = 1;
                    while(go < 4)
                    {
                    System.out.println("Player "+playerFourNumber+" go: " + go);
                    try {
                        Thread.sleep(500);
                        System.out.println("Sleeping...");
                        } 
                    catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        }
                    aGame.getAPlayer(playerFourNumber).randomMove(aGame.getAPlayer(playerFourNumber));
                    go++;
                    }
                    }
                    turn = 1;
                    break;
            }
        }
                
                
        if ((activePlayer.getHealth()) > 1) {
            aGame.setArea2("Game Over - YOU WIN!!!" + "\n" + "You won in " + aGame.turnCount + " turns.");
            aGame.restartButton.setVisible(true);
            aGame.restartButton.setEnabled(true);
            ///
            ///level restart call goes here
            ///
        } else {
            aGame.setArea2("Game Over - YOU LOSE!!!");
            aGame.restartButton.setVisible(true);
            aGame.restartButton.setEnabled(true);
        } 
    }
        
    public static void createPlayers()
    {
        playerArray = new Player[4];
        usedNumbers = new ArrayList(3);
        generator = new Random();
        int[] randomSquare;
        int dir;
        for (int x = 0; x < 4; x++) 
        {
            randomSquare = (aMap.getRandomEmptySquare());
            dir = (generator.nextInt(4) + 1);
            int thisPlayer = (generator.nextInt(8));
            while (usedNumbers.contains(thisPlayer)) 
            {
                thisPlayer = (generator.nextInt(4));
            }
            Player pThis = new Player(randomSquare[0], randomSquare[1], (dir), (x + 1), 5, 3,3,3, playerNames[thisPlayer], aMap);
            playerArray[x] = pThis;
            usedNumbers.add(thisPlayer);
            System.out.println("player " + (x + 1) + " is " + playerNames[thisPlayer]);
            if(roundCount!=0)
            {
                playerArray[0]=aGame.chosenPlayer;
            }
        }
    }
    
    public static void initialiseGame()
    {
        aMap.setGame(aGame);
        aGame.setMap(aMap);
        for (int plg = 0; plg < 4; plg++) {
            playerArray[plg].setGame(aGame);
        }
        
        aGame.setArea2("Welcome to Bored Game " + "\n");
        aGame.reDrawItAll();
        aGame.restartButton.setVisible(false);
        aGame.restartButton.setEnabled(false);
        
        turn = 1;
    }
        
    public static void resetGame()
    {
 
    aMap=null;
    aMap=new Map(8,11,3);
    aMap.setGame(aGame);
    aMap.setUpMap();
    aGame.setMap(aMap);
    aGame.reDrawItAll();
    aGame.setMap(aMap);
    aGame.reDrawItAll();
    aGame.setInjuredCount(0);
    System.out.println("map stuff done");
    aGame.setTurnCount(0);
    aGame.setTestResult(0);
    roundCount++;
    createPlayers();
    activePlayer = aGame.getAPlayer(0);
    activePlayer.setGame(aGame);
    activePlayer.setMap(aMap);
    aGame.chosenPlayer = activePlayer;
    aGame.chosenPlayer.setGun(3);
    aGame.chosenPlayer.setHealth(5);
    aGame.chosenPlayer.setAmmo(3);

    aGame.setPlayerArray(playerArray);
    
    for(int a=0;a<4;a++)
    {
        System.out.println("Player "+a+" is:"+ aGame.getAPlayer(a).getName()+" or " + aGame.getAPlayer(a));
    }
    ///aGame.setUpButtons();
//    try
//    {
//    aGame.selectPlayer();
//    ///runGame();
//    }
//    catch (InterruptedException Ex)
//            {
//                System.out.println(Ex);
//            }
    initialiseGame();
    
    System.out.println("reset done");
    ///aGame.createAndShowGUI();
    aGame.reDrawItAll();
    aGame.semaphoreRelease();
//    try
//    {
//    runLevel(aGame);
//    }
//    catch (InterruptedException ex)
//    {
//        System.out.println(ex);
//    }
    }
 }
