package pandorum.isidro;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import static pandorum.isidro.PandorumMaps.getPandorum;
import static pandorum.isidro.PandorumMaps.getPandorumBack;
import static pandorum.isidro.PandorumMaps.playerPosition;
import static pandorum.isidro.Player.getRandomNumber;

/*@author Isidro*/

public class Avatar extends Player {
    
    public Avatar (int id, char sexe) {
        super(id, sexe);
    }
    
    @Override
    public void run(){
    
        while (!PandorumFrame.isFinish()) {
            if (dead) {
                break;
            }
            else {
                try {
                    if (!PandorumFrame.isRefreshing()) {
                        move(this);
                        sleep(500);
                    }
                    else {
                        sleep(200); 
                    }
                } 
                catch (InterruptedException ex) {
                    Logger.getLogger(Human.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    @Override
    public synchronized void move (Player p) {
        //Getting a random number to choose the position where is going to move
        int num = getRandomNumber();
        //Getting the maps to move
        JLabel [][] pandorum = getPandorum();
        Player [][] pandorumBack = getPandorumBack();
        //Getting the position of the player to move it
        int [] pos = playerPosition(p);
        //The new position where the player is going to move
        int [] newPos = new int [2];
        if (pos != null) {
            //Get the new positions
            //Up-left
            if (num == 1) {
                //Y
                if (pos[y]-1 < 0) {
                    newPos[y] = 8;
                }
                else {
                    if (pos[y]-2 < 0) {
                        newPos[y] = 9;
                    }
                    else {
                        newPos[y] = pos[y]-2;
                    }
                }
                //X
                if (pos[x]-1 < 0) {
                    newPos[x] = 9;
                }
                else {
                    newPos[x] = pos[x]-1;
                }
            }
            //Up-right
            else if (num == 2) {
                //Y
                if (pos[y]-1 < 0) {
                    newPos[y] = 8;
                }
                else {
                    if (pos[y]-2 < 0) {
                        newPos[y] = 9;
                    }
                    else {
                        newPos[y] = pos[y]-2;
                    }
                }
                //X
                if (pos[x]+1 > 9) {
                    newPos[x] = 0;
                }
                else {
                    newPos[x] = pos[x]+1;
                }
            }
            //Down-left
            else if (num == 3) {
                //Y
                if (pos[y]+1 > 9) {
                    newPos[y] = 1;
                }
                else {
                    if (pos[y]+2 > 9) {
                        newPos[y] = 0;
                    }
                    else {
                        newPos[y] = pos[y]+2;
                    }
                }
                //X
                if (pos[x]-1 < 0) {
                    newPos[x] = 9;
                }
                else {
                    newPos[x] = pos[x]-1;
                }
            }
            //Down-right
            else if (num == 4) {
                //Y
                if (pos[y]+1 > 9) {
                    newPos[y] = 1;
                }
                else {
                    if (pos[y]+2 > 9) {
                        newPos[y] = 0;
                    }
                    else {
                        newPos[y] = pos[y]+2;
                    }
                }
                //X
                if (pos[x]+1 > 9) {
                    newPos[x] = 0;
                }
                else {
                    newPos[x] = pos[x]+1;
                }
            }
            //Left-up
            else if (num == 5) {
                //Y
                if (pos[y]-1 < 0) {
                    newPos[y] = 9;
                }
                else {
                    newPos[y] = pos[y]-1;
                }
                //X
                if (pos[x]-1 < 0) {
                    newPos[x] = 8;
                }
                else {
                    if (pos[x]-2 < 0) {
                        newPos[x] = 9;
                    }
                    else {
                        newPos[x] = pos[x]-2;
                    }
                }
            }
            //Left-down
            else if (num == 6) {
                //Y
                if (pos[y]+1 > 9) {
                    newPos[y] = 0;
                }
                else {
                    newPos[y] = pos[y]+1;
                }
                //X
                if (pos[x]-1 < 0) {
                    newPos[x] = 8;
                }
                else {
                    if (pos[x]-2 < 0) {
                        newPos[x] = 9;
                    }
                    else {
                        newPos[x] = pos[x]-2;
                    }
                }
            }
            //Right-up
            else if (num == 7) {
                //Y
                if (pos[y]-1 < 0) {
                    newPos[y] = 9;
                }
                else {
                    newPos[y] = pos[y]-1;
                }
                //X
                if (pos[x]+1 > 9) {
                    newPos[x] = 1;
                }
                else {
                    if (pos[x]+2 > 9) {
                        newPos[x] = 0;
                    }
                    else {
                        newPos[x] = pos[x]+2;
                    }
                }
                //
                if (pos[x]+1 > 9) {
                    if (pos[x]+2 > 9) {
                        newPos[x] = 1;
                    }
                    else {
                        newPos[x] = 0;
                    }
                }
                else {
                    newPos[x] = pos[x]+2;
                }
            }
            //Right-down
            else if (num == 8) {
                //Y
                if (pos[y]+1 > 9) {
                    newPos[y] = 0;
                }
                else {
                    newPos[y] = pos[y]+1;
                }
                //X
                if (pos[x]+1 > 9) {
                    newPos[x] = 1;
                }
                else {
                    if (pos[x]+2 > 9) {
                        newPos[x] = 0;
                    }
                    else {
                        newPos[x] = pos[x]+2;
                    }
                }
            }
            
            //Moving
            //If there's nothing in the row, the player will move to this position
            if (pandorumBack[newPos[x]][newPos[y]] == null) {

                pandorumBack[newPos[x]][newPos[y]] = pandorumBack[pos[0]][pos[1]];
                pandorumBack[pos[x]][pos[y]] = null;
                pandorum[newPos[x]][newPos[y]].setIcon(pandorum[pos[0]][pos[1]].getIcon());
                pandorum[pos[x]][pos[y]].setIcon(null);
            }
            //If there's a player in the row, the player has to do some actions
            else {
                if (pandorumBack[newPos[x]][newPos[y]] instanceof Avatar) {
                    //If the player is a Avatar and they're the same sex, they kill themselves
                    if ((pandorumBack[newPos[x]][newPos[y]].getSex()) == this.sex) {
                        
                        pandorumBack[pos[x]][pos[y]].dead = true;
                        pandorumBack[pos[x]][pos[y]] = null;
                        pandorum[pos[x]][pos[y]].setIcon(null);

                        pandorumBack[newPos[x]][newPos[y]].dead = true;
                        pandorumBack[newPos[x]][newPos[y]] = null;
                        pandorum[newPos[x]][newPos[y]].setIcon(null);
                    }
                    //If the player isn't the same sex, they'll reproduce and die
                    //the sex will be determined by random
                    else {
                        if (getRandomNumber() < 4) {
                            
                            pandorumBack[pos[x]][pos[y]].dead = true;
                            pandorumBack[pos[x]][pos[y]] = null;
                            pandorum[pos[x]][pos[y]].setIcon(null);

                            pandorumBack[newPos[x]][newPos[y]].dead = true;
                            pandorum[newPos[x]][newPos[y]].setIcon(new ImageIcon("src/resources/maleAvatar.jpg"));
                            pandorumBack[newPos[x]][newPos[y]] = new Avatar(PandorumMaps.getIdPlayers(), 'M');
                            pandorumBack[newPos[x]][newPos[y]].start();
                        }
                        else {
                            
                            pandorumBack[pos[x]][pos[y]].dead = true;
                            pandorumBack[pos[x]][pos[y]] = null;
                            pandorum[pos[x]][pos[y]].setIcon(null);

                            pandorumBack[newPos[x]][newPos[y]].dead = true;
                            pandorum[newPos[x]][newPos[y]].setIcon(new ImageIcon("src/resources/femaleAvatar.jpg"));
                            pandorumBack[newPos[x]][newPos[y]] = new Avatar(PandorumMaps.getIdPlayers(), 'F');
                            pandorumBack[newPos[x]][newPos[y]].start();
                        }
                    }
                }
                //If the player that is in this label is an Human, the avatar kills the human
                else {
                    
                    pandorumBack[newPos[x]][newPos[y]].dead = true;
                    
                    pandorumBack[newPos[x]][newPos[y]] = pandorumBack[pos[0]][pos[1]];
                    pandorumBack[pos[x]][pos[y]] = null;
                    pandorum[newPos[x]][newPos[y]].setIcon(pandorum[pos[0]][pos[1]].getIcon());
                    pandorum[pos[x]][pos[y]].setIcon(null);
                }
            }
            
        }
    }
    
    
}
