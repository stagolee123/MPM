package boredgame2;

import java.util.Random;

public class Player implements Runnable {
/// Instance variables

    private int x; // x-position(N-S) of Player
    private int y; // y-position(W-E) of Player
    private int z; // Direction of Player N=1 E=4 S=3 W=2
    private int i; // Identifier of Player
    private int h; // Health of Player
    private int am; // Ammo count
    private int g; // Gun type
    private int mo; // number of moves per turn
    private String n; //names
    private Map thisMap;
    private Game thisGame;

///run override
    public void run() {
    }

///constructors
    public Player() {
        x = 1;
        y = 1;
        z = 1;
    }

    public Player(int xPos, int yPos, int direction, int identifier, int health, int ammo,int gun, int moves, String name, Map aMap) {
        x = xPos;
        y = yPos;
        z = direction; /// N=1 E=4 S=3 W=2
        i = identifier;
        h = health;
        am = ammo;
        g = gun;
        mo = moves;
        n = name;
        thisMap = aMap;
        thisMap.setMapPosition(this.getXPosition(), this.getYPosition(), this.getId());
    }

/// Methods
///Get & Set Methods
    public void setGame(Game aGame) {
        thisGame = aGame;
    }
    
    public void setMap(Map aMap) {
    thisMap = aMap;
    }
    
    public void setHealth(int he) {
    h = he;
    }
    
    public void setAmmo(int amm) {
    amm = am;
    }

    public int getId() {
        return i;
    }

    public int getDirection() {
        return z;
    }

    public int getXPosition() {
        return x;
    }

    public void setXPosition(int newX) {
        this.x = newX;
    }

    public int getYPosition() {
        return y;
    }

    public void setYPosition(int newY) {
        this.x = newY;
    }

    public String getName() {
        return n;
    }

    public int getHealth() {
        return h;
    }
    
    public int getAmmoCount() {
        return am;
    }
    
    public int getGun() {
        return g;
    }
     
    public int getMoves() {
        return mo;
    }
     
    public void setMoves(int moves) {
        mo = moves;
    }
    public void setGun(int gun) {
        g = gun;
    } 
    
    public void decrementAmmoCount(int bullets){
        am=am-bullets;
    }

    public void decrementHealth(int damage) {
        h=h-damage;
        if (h <= 1) { 
            thisGame.setInjuredCount(thisGame.getInjuredCount()+1);
        }
    }
    
    public void incrementAmmoCount(int ammoInc){
        am=am+ammoInc;
    }

    public void incrementHealth() {
        h=h+3;
    }

    public String getPosition() {
        String dirString = "";
        switch (z) {
            case 0:
                dirString = ("WTF?");
                break;
            case 1:
                dirString = ("North");
                break;
            case 2:
                dirString = ("West");
                break;
            case 3:
                dirString = ("South");
                break;
            case 4:
                dirString = ("East");
                break;
            default:
                dirString = ("WTF?");
                break;
        }
        return ("(" + (x + 1) + ", " + (y + 1) + ") facing " + dirString);
    }

    /// Move
    public void move(int dir) {
        int curX = this.getXPosition();
        int curY = this.getYPosition();
        int currentTarget;
        switch (dir)
        {
            case 1: ///north
                if (x > 0){ /// boundary detection 
                    currentTarget = (thisMap.getMapPosition((curX - 1), curY)); 
                    if ((currentTarget == 0)||(currentTarget > 9)){ ///collision detection
                        this.callMove(dir, currentTarget);
                    }    
                }
                break;
            case 2: ///south
                if (x < (thisMap.getDEPTH() - 1)){ /// boundary detection
                    currentTarget = (thisMap.getMapPosition((curX + 1), curY));
                    if ((currentTarget == 0)||(currentTarget > 9)){ ///collision detection
                        this.callMove(dir, currentTarget);
                    }
                }
                break;
            case 3: ///east
                if (y < (thisMap.getWIDTH() - 1)) {/// boundary detection 
                    currentTarget = (thisMap.getMapPosition(curX, (curY + 1)));
                    if ((currentTarget == 0)||(currentTarget > 9)){ ///collision detection
                        this.callMove(dir, currentTarget);
                    }
                }
                break;
            case 4: ///west
                if (y > 0){/// boundary detection 
                    currentTarget = (thisMap.getMapPosition(curX , (curY - 1)));
                    if ((currentTarget == 0)||(currentTarget > 9)){ ///collision detection
                        this.callMove(dir, currentTarget);
                    }
                }
                break;  
        }
        thisGame.reDrawItAll();
    }
    
    public void callMove(int dir, int currentTarget)
    {
        int curX = this.getXPosition();
        int curY = this.getYPosition();
        switch (currentTarget)
        {
            case 10:
                this.incrementHealth();
                thisGame.setArea2(this.getName() + " picked up health" + "\n");
                break;
            case 11:
                this.incrementAmmoCount(3);
                thisGame.setArea2(this.getName() + " picked up ammo" + "\n");
                break;
            case 12:
                this.setGun(6);
                this.incrementAmmoCount(1);
                thisGame.setArea2(this.getName() + " picked up a Magnum 44" + "\n");
                break;
        }
        switch (dir)
        {
            case 1: ///north
                thisMap.setMapPosition(curX, curY, 0);
                x = x - 1;
                thisMap.setMapPosition(this.getXPosition(), curY, this.getId());
                break;
            case 2: ///south
                thisMap.setMapPosition(curX, curY, 0);
                x = x + 1;
                thisMap.setMapPosition(this.getXPosition(), curY, this.getId());
                break;
            case 3: ///east
                thisMap.setMapPosition(curX, curY, 0);
                y = y + 1;
                thisMap.setMapPosition(curX, this.getYPosition(), this.getId());
                break;
            case 4: ///west
                thisMap.setMapPosition(curX, curY, 0);
                y = y - 1;
                thisMap.setMapPosition(curX, this.getYPosition(), this.getId());
                break;
        }  
    }

    public void randomMove(Player thisPlayer) throws InterruptedException {
        Random generator = new Random();
        int x = generator.nextInt(10);
        switch (x) {

            case 0:
                thisPlayer.move(1);
                thisGame.setArea2(thisPlayer.getName() + " moved north to " + thisPlayer.getPosition() + " health:"+ thisPlayer.getHealth()+"\n");
                break;
            case 1:
                thisPlayer.move(2);
                thisGame.setArea2(thisPlayer.getName() + " moved south to " + thisPlayer.getPosition() +" health:"+ thisPlayer.getHealth()+ "\n");
                break;
            case 2:
                thisPlayer.move(3);
                thisGame.setArea2(thisPlayer.getName() + " moved east to " + thisPlayer.getPosition() +" health:"+ thisPlayer.getHealth()+ "\n");
                break;
            case 3:
                thisPlayer.move(4);
                thisGame.setArea2(thisPlayer.getName() + " moved west to " + thisPlayer.getPosition() + " health:"+ thisPlayer.getHealth()+"\n");
                break;
            case 4:
                thisPlayer.turnLeft();
                thisGame.setArea2(thisPlayer.getName() + " turned left " + thisPlayer.getPosition() + " health:"+ thisPlayer.getHealth()+"\n");
                break;
            case 5:
                thisPlayer.turnRight();
                thisGame.setArea2(thisPlayer.getName() + " turned right " + thisPlayer.getPosition() + " health:"+ thisPlayer.getHealth()+"\n");
                break;
            ///case 6: int randomSquare[] = thisPlayer.teleportTo();thisGame.setArea2(thisPlayer.getName()+" teleports to " +(randomSquare[0]+1)+","+(randomSquare[1]+1)+"\n"); break;
            default:
                thisPlayer.fire();
                break;
        }
        thisGame.reDrawItAll();
    }

    ///Turn
    public void turnLeft() {
        z = this.getDirection();
        if (z < 4) {
            z = z + 1;
        } else {
            z = 1;
        }
    }

    public void turnRight() {
        z = this.getDirection();
        if (z > 1) {
            z = z - 1;
        } else {
            z = 4;
        }
    }

    ///fire
    public void fire() {
        int hitResult = 0;
        int thisX = 0;
        int thisY = 0;
        int direction = this.getDirection();
        if (this.getAmmoCount() < 1){
        thisGame.setArea2("click... " + n + " needs to reload \n");
        }
        else
        {    
        thisGame.setArea2(n + " fires ");
        switch (direction) {
            case 1:
                thisGame.setArea2("north." + "\n");
                for (int xCoOrd = (this.getXPosition() - 1); xCoOrd >= 0; xCoOrd--) {
                    hitResult = thisMap.getMapPosition(xCoOrd, y);
                    if (hitResult != 0) {
                        thisX = xCoOrd;
                        thisY = y;
                        break;
                    }
                }
                break;
            case 2:
                thisGame.setArea2("west." + "\n");
                for (int yCoOrd = (this.getYPosition() - 1); yCoOrd >= 0; yCoOrd--) {
                    hitResult = thisMap.getMapPosition(x, yCoOrd);
                    if (hitResult != 0) {
                        thisX = x;
                        thisY = yCoOrd;
                        break;
                    }
                }
                break;

            case 3:
                thisGame.setArea2("south." + "\n");
                for (int xCoOrd = (this.getXPosition() + 1); xCoOrd < (thisMap.getDEPTH()); xCoOrd++) {
                    hitResult = thisMap.getMapPosition(xCoOrd, y);
                    if (hitResult != 0) {
                        thisX = xCoOrd;
                        thisY = y;
                        break;
                    }
                }
                break;

            case 4:
                thisGame.setArea2("east." + "\n");
                for (int yCoOrd = (this.getYPosition() + 1); yCoOrd < (thisMap.getWIDTH()); yCoOrd++) {
                    hitResult = thisMap.getMapPosition(x, yCoOrd);
                    if (hitResult != 0) {
                        thisX = x;
                        thisY = yCoOrd;
                        break;
                    }
                }

                break;
        }
        this.decrementAmmoCount(1);
        this.callHit(hitResult, thisX, thisY, " shot ", this.getGun());
        }
    }
    
        public void kick() {
        int hitResult = 0;
        int thisX = 0;
        int thisY = 0;
        int xCoOrd = 0;
        int yCoOrd = 0;
        int direction = this.getDirection();
        thisGame.setArea2(n + " kicks ");
        switch (direction) {
            case 1:
                thisGame.setArea2("north." + "\n");
                if ((this.getXPosition() - 1)>=0){
                    xCoOrd = this.getXPosition() - 1;
                    hitResult = thisMap.getMapPosition(xCoOrd, y);
                    if (hitResult != 0) {
                        thisX = xCoOrd;
                        thisY = y;}
                        break; 
                }
                break;
            case 2:
                thisGame.setArea2("west." + "\n");
                if ((this.getYPosition() - 1)>=0){
                yCoOrd = this.getYPosition() - 1;
                    hitResult = thisMap.getMapPosition(x, yCoOrd);
                    if (hitResult != 0) {
                        thisX = x;
                        thisY = yCoOrd;}
                        break;
                    }
                break;

            case 3:
                thisGame.setArea2("south." + "\n");
                if ((this.getXPosition() + 1)<= thisMap.getDEPTH()){
                xCoOrd = this.getXPosition() + 1;
                    hitResult = thisMap.getMapPosition(xCoOrd, y);
                    if (hitResult != 0) {
                        thisX = xCoOrd;
                        thisY = y;}
                        break;
                }
                break;

            case 4:
                thisGame.setArea2("east." + "\n");
                if ((this.getYPosition() + 1) <= thisMap.getWIDTH()){
                yCoOrd = this.getYPosition() + 1;
                    hitResult = thisMap.getMapPosition(x, yCoOrd);
                    if (hitResult != 0) {
                        thisX = x;
                        thisY = yCoOrd;}
                        break;
                }
                break;
        }
        this.callHit(hitResult, thisX, thisY, " kicked ",1);
    }
    ///call a hit

    public void callHit(int result, int thisX, int thisY, String damType, int damAmount) {
        switch (result) {
            case 0:
                thisGame.setArea2(n + " missed completely" + "\n");
                break;
            case 1:
            case 2:
            case 3:
            case 4:
                if (((thisGame.thisPlayerArray[(result - 1)].getHealth()-damAmount) > 1)) {
                    thisGame.setArea2(n + damType + thisGame.thisPlayerArray[(result - 1)].getName() + "\n");
                    thisGame.thisPlayerArray[(result - 1)].decrementHealth(damAmount);
                    break;
                } else {
                    thisGame.thisPlayerArray[(result - 1)].decrementHealth(damAmount);
                    thisGame.setArea2(n + " eliminated " + thisGame.thisPlayerArray[(result - 1)].getName() + "\n");
                    thisMap.setMapPosition(thisX, thisY, 0);
                    break;
                }

            case 8: case 10: case 11: case 12:
                thisGame.setArea2(n + damType + " some furniture" + "\n");
                int pickup;
                Random generator = new Random();
                if  (generator.nextInt(10)>5)
                {
                pickup = (generator.nextInt(3)+10);
                thisMap.setMapPosition(thisX, thisY, pickup);
                }
                else
                {
                thisMap.setMapPosition(thisX, thisY, 0);
                }
                break;
            case 9:
                thisGame.setArea2(n + damType + " a wall" + "\n");
                break;
            default:
                thisGame.setArea2("WTF???" + "\n");
                break;
        }
    }
}