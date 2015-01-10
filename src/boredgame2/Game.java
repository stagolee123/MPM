package boredgame2;

import java.awt.*;
///import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;


public class Game extends JPanel implements ActionListener {

    JFrame frame;
    Player[] thisPlayerArray = new Player[4];
    Player chosenPlayer = new Player();
    Map aMap;
    public int injuredCount = 0;
    int turnCount = 0;
    int testResult = 0;
    JPanel statusPanel = new JPanel(new GridLayout(8, 1));
    JPanel p1StatusPanel = new JPanel(new GridLayout(1, 8));
    JLabel p1PlayerTxt = new JLabel();
    JLabel p1PlayerIco = new JLabel();
    JLabel p1AmmoTxt = new JLabel();
    JLabel p1AmmoIco = new JLabel();
    JLabel p1HealthTxt = new JLabel();
    JLabel p1HealthIco = new JLabel();
    JLabel p1GunTxt = new JLabel();
    JLabel p1GunIco = new JLabel();
    JPanel p2StatusPanel = new JPanel(new GridLayout(1, 8));
    JLabel p2PlayerTxt = new JLabel();
    JLabel p2PlayerIco = new JLabel();
    JLabel p2AmmoTxt = new JLabel();
    JLabel p2AmmoIco = new JLabel();
    JLabel p2HealthTxt = new JLabel();
    JLabel p2HealthIco = new JLabel();
    JLabel p2GunTxt = new JLabel();
    JLabel p2GunIco = new JLabel();
    JPanel p3StatusPanel = new JPanel(new GridLayout(1, 8));
    JLabel p3PlayerTxt = new JLabel();
    JLabel p3PlayerIco = new JLabel();
    JLabel p3AmmoTxt = new JLabel();
    JLabel p3AmmoIco = new JLabel();
    JLabel p3HealthTxt = new JLabel();
    JLabel p3HealthIco = new JLabel();
    JLabel p3GunTxt = new JLabel();
    JLabel p3GunIco = new JLabel();
    JPanel p4StatusPanel = new JPanel(new GridLayout(1, 8));
    JLabel p4PlayerTxt = new JLabel();
    JLabel p4PlayerIco = new JLabel();
    JLabel p4AmmoTxt = new JLabel();
    JLabel p4AmmoIco = new JLabel();
    JLabel p4HealthTxt = new JLabel();
    JLabel p4HealthIco = new JLabel();
    JLabel p4GunTxt = new JLabel();
    JLabel p4GunIco = new JLabel();
    JPanel blankPanel1 = new JPanel();
    JPanel blankPanel2 = new JPanel();
    JPanel blankPanel3 = new JPanel();
    JPanel bigPanel = new JPanel();
    JScrollPane scrollPane2;
    JButton restartButton = new JButton("Restart");
    JButton oneButton = new JButton("Player 1");
    JButton twoButton = new JButton("Player 2");
    JButton threeButton = new JButton("Player 3");
    JButton fourButton = new JButton("Player 4");
    JButton fireButton = new JButton("Fire");
    JButton kickButton = new JButton("Kick");
    JButton northButton = new JButton("North");
    JButton southButton = new JButton("South");
    JButton eastButton = new JButton("East");
    JButton westButton = new JButton("West");
    JButton turnLButton = new JButton("Turn Left");
    JButton turnRButton = new JButton("Turn Right");
    JPanel buttonPanel = new JPanel();
    JPanel buttonPanel2 = new JPanel(new GridLayout(3, 3));
    JPanel imagePanel = new JPanel(new GridLayout (11,8));
    
    private JTextArea aTextArea2;
    
    ImageIcon Icon0 = new ImageIcon();
    ImageIcon Icon1E = new ImageIcon();
    ImageIcon Icon1W = new ImageIcon();
    ImageIcon Icon1S = new ImageIcon();
    ImageIcon Icon1N = new ImageIcon();
    ImageIcon Icon2E = new ImageIcon();
    ImageIcon Icon2W = new ImageIcon();
    ImageIcon Icon2S = new ImageIcon();
    ImageIcon Icon2N = new ImageIcon();
    ImageIcon Icon3E = new ImageIcon();
    ImageIcon Icon3W = new ImageIcon();
    ImageIcon Icon3S = new ImageIcon();
    ImageIcon Icon3N = new ImageIcon();
    ImageIcon Icon4E = new ImageIcon();
    ImageIcon Icon4W = new ImageIcon();
    ImageIcon Icon4S = new ImageIcon();
    ImageIcon Icon4N = new ImageIcon();
    ImageIcon Icon8 = new ImageIcon();
    ImageIcon Icon9 = new ImageIcon();
    ImageIcon IconA = new ImageIcon();
    ImageIcon IconM = new ImageIcon();
    ImageIcon IconP = new ImageIcon();
    ImageIcon IconH = new ImageIcon();
    ImageIcon IconG = new ImageIcon();
    private Semaphore semaphore = new Semaphore(0);
    
    ///constructor for game
    Game(Player[] playerArray) {///String name){
        ///frame.setFocusable(true);
        ///frame.addKeyListener(new MyKeyListener());
 
        thisPlayerArray = playerArray;
        setUpButtons();
        aTextArea2 = new JTextArea(3, 30);
        aTextArea2.setEditable(false);
        JScrollPane scrollPane2 = new JScrollPane(aTextArea2);
        buttonPanel.add(oneButton);
        buttonPanel.add(twoButton);
        buttonPanel.add(threeButton);
        buttonPanel.add(fourButton);
        buttonPanel2.add(turnLButton);
        buttonPanel2.add(northButton);
        buttonPanel2.add(turnRButton);
        buttonPanel2.add(westButton);
        buttonPanel2.add(fireButton);
        buttonPanel2.add(eastButton);
        buttonPanel2.add(restartButton);
        buttonPanel2.add(southButton);
        buttonPanel2.add(kickButton);
        statusPanel.add(scrollPane2);
        bigPanel.add(statusPanel, BorderLayout.NORTH);
        statusPanel.add(scrollPane2, BorderLayout.SOUTH);
        add(imagePanel, BorderLayout.EAST);
        add(bigPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
        add(buttonPanel2, BorderLayout.SOUTH); 
    }
   
   private class BindingAction extends AbstractAction {
   public static final String FIRE = "5";
   public static final String NORTH = "8";
   public static final String SOUTH = "2";
   public static final String EAST = "6";
   public static final String WEST = "4";
   public static final String TURNL = "7";
   public static final String TURNR = "9";
   public static final String KICK = "15";

      public BindingAction(String text) {
         super(text);
         putValue(ACTION_COMMAND_KEY, text);
      }

      @Override
      public void actionPerformed(ActionEvent evt) {
      int keyResult = Integer.parseInt(evt.getActionCommand());
      testResult = keyResult;
      semaphore.release();
      }
      
   }
    public void actionPerformed(ActionEvent e) {
        
        int x = Integer.parseInt(e.getActionCommand());
        switch (x) {
            case 5:
                this.setArea2("FIRE" + "\n");
                testResult = 5;
                semaphore.release();
                break;
            case 8:
                this.setArea2("NORTH" + "\n");
                testResult = 8;
                semaphore.release();
                break;
            case 2:
                this.setArea2("SOUTH" + "\n");
                testResult = 2;
                semaphore.release();
                break;
            case 4:
                this.setArea2("WEST" + "\n");
                testResult = 4;
                semaphore.release();
                break;
            case 6:
                this.setArea2("EAST" + "\n");
                testResult = 6;
                semaphore.release();
                break;
            case 7:
                this.setArea2("TURNL" + "\n");
                testResult = 7;
                semaphore.release();
                break;
            case 9:
                this.setArea2("TURNR" + "\n");
                testResult = 9;
                semaphore.release();
                break;
            case 11:
                this.setArea2((thisPlayerArray[0].getName()) + "\n");
                testResult = 11;
                semaphore.release();
                break;
            case 12:
                this.setArea2((thisPlayerArray[1].getName()) + "\n");
                testResult = 12;
                semaphore.release();
                break;
            case 13:
                this.setArea2((thisPlayerArray[2].getName()) + "\n");
                testResult = 13;
                semaphore.release();
                break;
            case 14:
                this.setArea2((thisPlayerArray[3].getName()) + "\n");
                testResult = 14;
                semaphore.release();
                break;
            case 15:
                this.setArea2("KICK" + "\n");
                testResult = 15;
                semaphore.release();
                break;
            case 19:
                testGame.resetGame();
                System.out.println("game reset");
                semaphore.release();
                break;    
            default:
                this.setArea2("???" + x + "\n");
                semaphore.release(0);
                break;
        }
    }
    
    public void setMap(Map thisMap) {
        aMap = thisMap;
    }

    public void setArea2(String mess) {
        aTextArea2.append(mess);
        aTextArea2.setCaretPosition(aTextArea2.getDocument().getLength());
    }

    public int getInjuredCount() {
        return injuredCount;
    }

    public void setInjuredCount(int inj) {
        injuredCount=inj;
    }

    public void createAndShowGUI() {
        frame = new JFrame("boredGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(700,550);
        oneButton.setText(thisPlayerArray[0].getName());
        oneButton.setForeground(Color.green);
        oneButton.setFont(new Font("sansserif",Font.BOLD,16));
        twoButton.setText(thisPlayerArray[1].getName());
        twoButton.setForeground(Color.red);
        twoButton.setFont(new Font("sansserif",Font.BOLD,16));
        threeButton.setText(thisPlayerArray[2].getName());
        threeButton.setForeground(Color.blue);
        threeButton.setFont(new Font("sansserif",Font.BOLD,16));
        fourButton.setText(thisPlayerArray[3].getName());
        fourButton.setForeground(Color.yellow);
        fourButton.setFont(new Font("sansserif",Font.BOLD,16));
        
               try{
               Image pic0 = ImageIO.read(new File("images/0.jpg"));    
               Icon0.setImage(pic0);
               Image pic1E = ImageIO.read(new File("images/1E.jpg"));
               Icon1E.setImage(pic1E); 
               Image pic1W = ImageIO.read(new File("images/1W.jpg"));
               Icon1W.setImage(pic1W);
               Image pic1N = ImageIO.read(new File("images/1N.jpg"));
               Icon1N.setImage(pic1N); 
               Image pic1S = ImageIO.read(new File("images/1S.jpg"));
               Icon1S.setImage(pic1S); 
               Image pic2E = ImageIO.read(new File("images/2E.jpg"));
               Icon2E.setImage(pic2E); 
               Image pic2W = ImageIO.read(new File("images/2W.jpg"));
               Icon2W.setImage(pic2W);                
               Image pic2N = ImageIO.read(new File("images/2N.jpg"));
               Icon2N.setImage(pic2N);                
               Image pic2S = ImageIO.read(new File("images/2S.jpg"));
               Icon2S.setImage(pic2S);                          
               Image pic3E = ImageIO.read(new File("images/3E.jpg"));
               Icon3E.setImage(pic3E);
               Image pic3W = ImageIO.read(new File("images/3W.jpg"));
               Icon3W.setImage(pic3W);  
               Image pic3N = ImageIO.read(new File("images/3N.jpg"));
               Icon3N.setImage(pic3N);  
               Image pic3S = ImageIO.read(new File("images/3S.jpg"));
               Icon3S.setImage(pic3S);  
               Image pic4E = ImageIO.read(new File("images/4E.jpg"));
               Icon4E.setImage(pic4E);
               Image pic4W = ImageIO.read(new File("images/4W.jpg"));
               Icon4W.setImage(pic4W);  
               Image pic4N = ImageIO.read(new File("images/4N.jpg"));
               Icon4N.setImage(pic4N);  
               Image pic4S = ImageIO.read(new File("images/4S.jpg"));
               Icon4S.setImage(pic4S);  
               Image pic8 = ImageIO.read(new File("images/8.jpg"));
               Icon8.setImage(pic8);
               Image pic9 = ImageIO.read(new File("images/9.jpg"));
               Icon9.setImage(pic9);               
               Image picA = ImageIO.read(new File("images/A.jpg"));
               IconA.setImage(picA); 
               Image picP = ImageIO.read(new File("images/P.jpg"));
               IconP.setImage(picP); 
               Image picM = ImageIO.read(new File("images/M.jpg"));
               IconM.setImage(picM);              
               Image picH = ImageIO.read(new File("images/H.jpg"));               
               IconH.setImage(picH); 
               Image picG = ImageIO.read(new File("images/G.jpg"));               
               IconG.setImage(picG); 
                    
        } catch (IOException ex) {
            System.out.println("Image load fucked");
        }
        
        p1PlayerTxt.setText(thisPlayerArray[0].getName());
        p1PlayerIco.setIcon(Icon1N);
        p1AmmoIco.setIcon(IconA);
        p1HealthIco.setIcon(IconH);
        p1GunIco.setIcon(IconP);
        p1AmmoTxt.setText(Integer.toString(thisPlayerArray[0].getAmmoCount()));
        p1HealthTxt.setText(Integer.toString(thisPlayerArray[0].getHealth()));
        p1GunTxt.setText(Integer.toString(thisPlayerArray[0].getGun()));
        p1StatusPanel.add(p1PlayerIco);
        p1StatusPanel.add(p1PlayerTxt);
        p1StatusPanel.add(p1AmmoIco);
        p1StatusPanel.add(p1AmmoTxt);
        p1StatusPanel.add(p1HealthIco);
        p1StatusPanel.add(p1HealthTxt);
        p1StatusPanel.add(p1GunIco);
        p1StatusPanel.add(p1GunTxt);
        p2PlayerTxt.setText(thisPlayerArray[1].getName());
        p2PlayerIco.setIcon(Icon2N);
        p2AmmoIco.setIcon(IconA);
        p2HealthIco.setIcon(IconH);
        p2GunIco.setIcon(IconP);
        p2AmmoTxt.setText(Integer.toString(thisPlayerArray[1].getAmmoCount()));
        p2HealthTxt.setText(Integer.toString(thisPlayerArray[1].getHealth()));
        p2GunTxt.setText(Integer.toString(thisPlayerArray[1].getGun()));
        p2StatusPanel.add(p2PlayerIco);
        p2StatusPanel.add(p2PlayerTxt);
        p2StatusPanel.add(p2AmmoIco);
        p2StatusPanel.add(p2AmmoTxt);
        p2StatusPanel.add(p2HealthIco);
        p2StatusPanel.add(p2HealthTxt);
        p2StatusPanel.add(p2GunIco);
        p2StatusPanel.add(p2GunTxt);
        p3PlayerTxt.setText(thisPlayerArray[2].getName());
        p3PlayerIco.setIcon(Icon3N);
        p3AmmoIco.setIcon(IconA);
        p3HealthIco.setIcon(IconH);
        p3GunIco.setIcon(IconP);
        p3AmmoTxt.setText(Integer.toString(thisPlayerArray[2].getAmmoCount()));
        p3HealthTxt.setText(Integer.toString(thisPlayerArray[2].getHealth()));
        p3GunTxt.setText(Integer.toString(thisPlayerArray[2].getGun()));
        p3StatusPanel.add(p3PlayerIco);
        p3StatusPanel.add(p3PlayerTxt);
        p3StatusPanel.add(p3AmmoIco);
        p3StatusPanel.add(p3AmmoTxt);
        p3StatusPanel.add(p3HealthIco);
        p3StatusPanel.add(p3HealthTxt);
        p3StatusPanel.add(p3GunIco);
        p3StatusPanel.add(p3GunTxt);
        p4PlayerTxt.setText(thisPlayerArray[3].getName());
        p4PlayerIco.setIcon(Icon4N);
        p4AmmoIco.setIcon(IconA);
        p4HealthIco.setIcon(IconH);
        p4GunIco.setIcon(IconP);
        p4AmmoTxt.setText(Integer.toString(thisPlayerArray[3].getAmmoCount()));
        p4HealthTxt.setText(Integer.toString(thisPlayerArray[3].getHealth()));
        p4GunTxt.setText(Integer.toString(thisPlayerArray[3].getGun()));
        p4StatusPanel.add(p4PlayerIco);
        p4StatusPanel.add(p4PlayerTxt);
        p4StatusPanel.add(p4AmmoIco);
        p4StatusPanel.add(p4AmmoTxt);
        p4StatusPanel.add(p4HealthIco);
        p4StatusPanel.add(p4HealthTxt);
        p4StatusPanel.add(p4GunIco);
        p4StatusPanel.add(p4GunTxt);
        imagePanel.setVisible(true);
        statusPanel.add(p1StatusPanel);
        statusPanel.add(p2StatusPanel);
        statusPanel.add(p3StatusPanel);
        statusPanel.add(p4StatusPanel);
    }

    public void reDrawItAll()
    {
            JLabel case_labels[][] = new JLabel[aMap.getDEPTH()][aMap.getWIDTH()];
                   imagePanel.removeAll();
                   for (int id =(aMap.getDEPTH()-1); id>=0; id--)
                   {
                        for (int iw =0; iw<aMap.getWIDTH(); iw++)
                        {
                            case_labels[id][iw] = new JLabel();
                            case_labels[id][iw].setOpaque(true);
                            imagePanel.add(case_labels[id][iw],id,iw);
                            int inImage = aMap.getMapPosition(id,iw);
                            switch (inImage){
                            case 0:
                                case_labels[id][iw].setIcon(Icon0);
                                break;
                            case 1:
                                switch((thisPlayerArray[0].getDirection())){
                                    case 1:
                                    case_labels[id][iw].setIcon(Icon1N);   
                                    break;
                                    case 2:
                                    case_labels[id][iw].setIcon(Icon1W);   
                                    break;
                                    case 3:
                                    case_labels[id][iw].setIcon(Icon1S);   
                                    break;
                                    case 4:
                                    case_labels[id][iw].setIcon(Icon1E);   
                                    break;
                                    }
                                break;
                            case 2:
                                switch((thisPlayerArray[1].getDirection())){
                                    case 1:
                                    case_labels[id][iw].setIcon(Icon2N);   
                                    break;
                                    case 2:
                                    case_labels[id][iw].setIcon(Icon2W);   
                                    break;
                                    case 3:
                                    case_labels[id][iw].setIcon(Icon2S);   
                                    break;
                                    case 4:
                                    case_labels[id][iw].setIcon(Icon2E);   
                                    break;
                                    }
                                break;
                            case 3:
                                    switch((thisPlayerArray[2].getDirection())){
                                    case 1:
                                    case_labels[id][iw].setIcon(Icon3N);   
                                    break;
                                    case 2:
                                    case_labels[id][iw].setIcon(Icon3W);   
                                    break;
                                    case 3:
                                    case_labels[id][iw].setIcon(Icon3S);   
                                    break;
                                    case 4:
                                    case_labels[id][iw].setIcon(Icon3E);   
                                    break;
                                    }
                                break;
                            case 4:
                                    switch((thisPlayerArray[3].getDirection())){
                                    case 1:
                                    case_labels[id][iw].setIcon(Icon4N);   
                                    break;
                                    case 2:
                                    case_labels[id][iw].setIcon(Icon4W);   
                                    break;
                                    case 3:
                                    case_labels[id][iw].setIcon(Icon4S);   
                                    break;
                                    case 4:
                                    case_labels[id][iw].setIcon(Icon4E);   
                                    break;
                                    }
                                break;
                            case 8:
                                case_labels[id][iw].setIcon(Icon8);
                                break;
                            case 9:
                                case_labels[id][iw].setIcon(Icon9);
                                break;
                            case 11:
                                case_labels[id][iw].setIcon(IconA);
                                break;
                            case 12:
                                case_labels[id][iw].setIcon(IconM);
                                break;
                            case 10:
                                case_labels[id][iw].setIcon(IconH);
                                break;
                            }
                    
                        }
                    } 
                   p1AmmoTxt.setText(Integer.toString(thisPlayerArray[0].getAmmoCount()));
                   p2AmmoTxt.setText(Integer.toString(thisPlayerArray[1].getAmmoCount()));
                   p3AmmoTxt.setText(Integer.toString(thisPlayerArray[2].getAmmoCount()));
                   p4AmmoTxt.setText(Integer.toString(thisPlayerArray[3].getAmmoCount()));
                   p1HealthTxt.setText(Integer.toString(thisPlayerArray[0].getHealth()));
                   p2HealthTxt.setText(Integer.toString(thisPlayerArray[1].getHealth()));
                   p3HealthTxt.setText(Integer.toString(thisPlayerArray[2].getHealth()));
                   p4HealthTxt.setText(Integer.toString(thisPlayerArray[3].getHealth()));
                   p1GunTxt.setText(Integer.toString(thisPlayerArray[0].getGun()));
                   p2GunTxt.setText(Integer.toString(thisPlayerArray[1].getGun()));
                   p3GunTxt.setText(Integer.toString(thisPlayerArray[2].getGun()));
                   p4GunTxt.setText(Integer.toString(thisPlayerArray[3].getGun()));
                   p1PlayerTxt.setText(thisPlayerArray[0].getName());
                   p2PlayerTxt.setText(thisPlayerArray[1].getName());
                   p3PlayerTxt.setText(thisPlayerArray[2].getName());
                   p4PlayerTxt.setText(thisPlayerArray[3].getName());
                   if((thisPlayerArray[0].getGun())==6)
                   {
                       p1GunIco.setIcon(IconM);
                   }
                   else
                   {
                       p1GunIco.setIcon(IconP);
                   }
                   if((thisPlayerArray[1].getGun())==6)
                   {
                       p2GunIco.setIcon(IconM);
                   }
                   else
                   {
                       p2GunIco.setIcon(IconP);
                   }
                   if((thisPlayerArray[2].getGun())==6)
                   {
                       p3GunIco.setIcon(IconM);
                   }
                   else
                   {
                       p3GunIco.setIcon(IconP);
                   }
                   if((thisPlayerArray[3].getGun())==6)
                   {
                       p4GunIco.setIcon(IconM);
                   }
                   else
                   {
                       p4GunIco.setIcon(IconP);
                   }
                   if((thisPlayerArray[0].getHealth())>1)
                   {
                       p1PlayerIco.setIcon(Icon1N);
                   }
                   else
                   {
                       p1PlayerIco.setIcon(IconG);
                   }
                   if((thisPlayerArray[1].getHealth())>1)
                   {
                       p2PlayerIco.setIcon(Icon2N);
                   }
                   else
                   {
                       p2PlayerIco.setIcon(IconG);
                   }
                   if((thisPlayerArray[2].getHealth())>1)
                   {
                       p3PlayerIco.setIcon(Icon3N);
                   }
                   else
                   {
                       p3PlayerIco.setIcon(IconG);
                   }
                   if((thisPlayerArray[3].getHealth())>1)
                   {
                       p4PlayerIco.setIcon(Icon4N);
                   }
                   else
                   {
                       p4PlayerIco.setIcon(IconG);
                   }
     }
     
    public Player getRandomPlayer() {
        Player returnedPlayer = new Player();
        Random generator = new Random();
        int x = generator.nextInt(4) + 1;
        while (((thisPlayerArray[x - 1].getHealth()) < 2) || (thisPlayerArray[x - 1] == chosenPlayer)) {
            if ((thisPlayerArray[x - 1].getHealth()) == 1) {
                this.setArea2((thisPlayerArray[x - 1].getName()) + " is unconcious" + "\n");
                x = generator.nextInt(4) + 1;
            }
            x = generator.nextInt(4) + 1;
        }

        switch (x) {
            case 1:
                returnedPlayer = thisPlayerArray[0];
                break;
            case 2:
                returnedPlayer = thisPlayerArray[1];
                break;
            case 3:
                returnedPlayer = thisPlayerArray[2];
                break;
            case 4:
                returnedPlayer = thisPlayerArray[3];
                break;
        }
        return returnedPlayer;
    }

    public Player selectPlayer() throws InterruptedException {
        buttonPanel.setVisible(true);
        buttonPanel2.setVisible(false);
        Player returnedPlayer = new Player();
        this.setArea2("Select Player" + "\n");
        semaphore.acquire();
        if (!semaphore.tryAcquire()) {
            int inX = testResult;
            switch (inX) {
                case 11:
                    returnedPlayer = thisPlayerArray[0];
                    break;
                case 12:
                    returnedPlayer = thisPlayerArray[1];
                    break;
                case 13:
                    returnedPlayer = thisPlayerArray[2];
                    break;
                case 14:
                    returnedPlayer = thisPlayerArray[3];
                    break;
            }
            chosenPlayer = returnedPlayer;
        }
        buttonPanel2.setVisible(true);
        buttonPanel.setVisible(false);
        setKeyBindings();
        return returnedPlayer;
    }

    public void takeTurn() throws InterruptedException {
        ///System.out.println("Moves "+ chosenPlayer.getMoves());
        ///System.out.println("Name "+ chosenPlayer.getName());
        ///System.out.println("Position "+ chosenPlayer.getPosition());
        ///System.out.println("Health "+ chosenPlayer.getHealth());
        ///System.out.println("AmmoCount "+ chosenPlayer.getAmmoCount());
        //for (int x = 1 ; x < chosenPlayer.getMoves(); x++)
        //{
        this.setArea2("Your move " + chosenPlayer.getName() + ", \n"); 
        this.setArea2("You are at " + chosenPlayer.getPosition() + " health:"+ chosenPlayer.getHealth()+ " ammo:" + chosenPlayer.getAmmoCount() +"\n");
        semaphore.acquire();
        if (!semaphore.tryAcquire()) {
            int inX = testResult;
            switch (inX) {
                case 2:
                    chosenPlayer.move(2);
                    break;
                case 4:
                    chosenPlayer.move(4);
                    break;
                case 5:
                    chosenPlayer.fire();
                    break;
                case 6:
                    chosenPlayer.move(3);
                    break;
                case 7:
                    chosenPlayer.turnLeft();
                    break;
                case 8:
                    chosenPlayer.move(1);
                    break;
                case 9:
                    chosenPlayer.turnRight();
                    break;
                case 15:
                    chosenPlayer.kick();
                    break;    
                default:
                    this.setArea2("not a valid choice" + "\n");
                    break;
            }
            this.reDrawItAll();
        ///}
        }
          turnCount++;
    }
    

 
    public void setPlayerArray(Player[] playerArray)
    {
        thisPlayerArray = playerArray;
    }
    
    public void setTurnCount(int tc)
    {
        turnCount = tc;
    }
    
    public void setTestResult(int tr)
    {
        testResult = tr;
    }
    
    public void setActivePlayer(Player ap)
    {
        chosenPlayer = ap;
    }
    
    public Player getAPlayer(int pl)
    {
        return thisPlayerArray[pl];
    }
    
    public void semaphoreRelease()
    {
        semaphore.release();
    }
    
    public void setUpButtons()
    {
        oneButton.setActionCommand("11");       
        oneButton.setEnabled(true);
        oneButton.addActionListener(this);
        oneButton.setToolTipText("Click this button to choose " + (thisPlayerArray[0].getName()));

        twoButton.setActionCommand("12");
        twoButton.setEnabled(true);
        twoButton.addActionListener(this);
        twoButton.setToolTipText("Click this button to choose " + (thisPlayerArray[1].getName()));

        threeButton.setActionCommand("13");
        threeButton.setEnabled(true);
        threeButton.addActionListener(this);
        threeButton.setToolTipText("Click this button to choose " + (thisPlayerArray[2].getName()));

        fourButton.setActionCommand("14");
        fourButton.setEnabled(true);
        fourButton.addActionListener(this);
        fourButton.setToolTipText("Click this button to choose " + (thisPlayerArray[3].getName()));

        fireButton.setActionCommand("5");
        fireButton.setEnabled(true);
        fireButton.addActionListener(this);
        fireButton.setToolTipText("Click this button to fire.");

        kickButton.setActionCommand("15");
        kickButton.setEnabled(true);
        kickButton.addActionListener(this);
        kickButton.setToolTipText("Click this button to kick.");
        
        northButton.setActionCommand("8");
        northButton.setEnabled(true);
        northButton.addActionListener(this);
        northButton.setToolTipText("Click this button to go north.");

        southButton.setActionCommand("2");
        southButton.setEnabled(true);
        southButton.addActionListener(this);
        southButton.setToolTipText("Click this button to go south.");

        eastButton.setActionCommand("6");
        eastButton.setEnabled(true);
        eastButton.addActionListener(this);
        eastButton.setToolTipText("Click this button to go east.");

        westButton.setActionCommand("4");
        westButton.setEnabled(true);
        westButton.addActionListener(this);
        westButton.setToolTipText("Click this button to go west.");

        turnLButton.setActionCommand("7");
        turnLButton.setEnabled(true);
        turnLButton.addActionListener(this);
        turnLButton.setToolTipText("Click this button to turn left.");

        turnRButton.setActionCommand("9");
        turnRButton.setEnabled(true);
        turnRButton.addActionListener(this);
        turnRButton.setToolTipText("Click this button to turn right.");
        
        restartButton.setActionCommand("19");
        restartButton.setEnabled(false);
        restartButton.setVisible(false);
        restartButton.addActionListener(this);
        restartButton.setToolTipText("Click this button to restart");
    }
    
        public void playButtonsOn()
        {
            fireButton.setEnabled(true);
            kickButton.setEnabled(true);
            northButton.setEnabled(true);
            southButton.setEnabled(true);
            westButton.setEnabled(true);
            eastButton.setEnabled(true);
            turnLButton.setEnabled(true);
            turnRButton.setEnabled(true);
            fireButton.setVisible(true);
            kickButton.setVisible(true);
            northButton.setVisible(true);
            southButton.setVisible(true);
            westButton.setVisible(true);
            eastButton.setVisible(true);
            turnLButton.setVisible(true);
            turnRButton.setVisible(true);
        }
        
        public void playButtonsOff()
        {
            fireButton.setEnabled(false);
            kickButton.setEnabled(false);
            northButton.setEnabled(false);
            southButton.setEnabled(false);
            westButton.setEnabled(false);
            eastButton.setEnabled(false);
            turnLButton.setEnabled(false);
            turnRButton.setEnabled(false);
            fireButton.setVisible(false);
            kickButton.setVisible(false);
            northButton.setVisible(false);
            southButton.setVisible(false);
            westButton.setVisible(false);
            eastButton.setVisible(false);
            turnLButton.setVisible(false);
            turnRButton.setVisible(false);
        }
    
      private void setKeyBindings() {
      Action fireAction = new BindingAction(BindingAction.FIRE);
      Action northAction = new BindingAction(BindingAction.NORTH);
      Action southAction = new BindingAction(BindingAction.SOUTH);
      Action eastAction = new BindingAction(BindingAction.EAST);
      Action westAction = new BindingAction(BindingAction.WEST);
      Action turnLAction = new BindingAction(BindingAction.TURNL);
      Action turnRAction = new BindingAction(BindingAction.TURNR);
      Action kickAction = new BindingAction(BindingAction.KICK);
      int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
      InputMap inputMap = getInputMap(condition);
      ActionMap actionMap = getActionMap();

      actionMap.put(BindingAction.FIRE, fireAction);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), BindingAction.FIRE);
      actionMap.put(BindingAction.NORTH, northAction);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), BindingAction.NORTH);
      actionMap.put(BindingAction.SOUTH, southAction);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), BindingAction.SOUTH);
      actionMap.put(BindingAction.EAST, eastAction);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), BindingAction.EAST);
      actionMap.put(BindingAction.WEST, westAction);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), BindingAction.WEST);
      actionMap.put(BindingAction.TURNL, turnLAction);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), BindingAction.TURNL);
      actionMap.put(BindingAction.TURNR, turnRAction);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), BindingAction.TURNR);
      actionMap.put(BindingAction.KICK, kickAction);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), BindingAction.KICK);
   }
     
}

