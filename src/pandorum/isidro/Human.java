package pandorum.isidro;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import static pandorum.isidro.PandorumMaps.getPandorum;
import static pandorum.isidro.PandorumMaps.getPandorumBack;
import static pandorum.isidro.PandorumMaps.playerPosition;

/*@author Isidro*/

public class Human extends Player {

    public Human (int id, char sexe) {
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
        int newPos;
        
        if (pos != null) {
            //Up
            if (num < 2) {
                //Get the position where the player is going to move
                if (pos[x]-1 < 0) {
                    newPos = 9;
                }
                else {
                    newPos = pos[x]-1;
                }
                //If there's nothing in the row, the player will move to this position
                if (pandorumBack[newPos][pos[y]] == null) {
                    
                    pandorumBack[newPos][pos[y]] = pandorumBack[pos[0]][pos[1]];
                    pandorumBack[pos[x]][pos[y]] = null;
                    pandorum[newPos][pos[y]].setIcon(pandorum[pos[0]][pos[1]].getIcon());
                    pandorum[pos[x]][pos[y]].setIcon(null);
                }
                //If there's a player in the row, the player has to do some actions
                else {
                    if (pandorumBack[newPos][pos[y]] instanceof Human) {
                        if ((pandorumBack[newPos][pos[y]].getSex()) == this.sex) {
                            //If the player is an Human and they're the same sex, they kill themselves
                            pandorumBack[pos[x]][pos[y]].dead = true;
                            pandorumBack[pos[x]][pos[y]] = null;
                            pandorum[pos[x]][pos[y]].setIcon(null);

                            pandorumBack[newPos][pos[y]].dead = true;
                            pandorumBack[newPos][pos[y]] = null;
                            pandorum[newPos][pos[y]].setIcon(null);
                        }
                        else {
                            //If the player isn't the same sex, they'll reproduce and die
                            //the sex will be determined by random
                            if (getRandomNumber() < 4) {
                                pandorumBack[pos[x]][pos[y]].dead = true;
                                pandorumBack[pos[x]][pos[y]] = null;
                                pandorum[pos[x]][pos[y]].setIcon(null);

                                pandorumBack[newPos][pos[y]].dead = true;
                                pandorum[newPos][pos[y]].setIcon(new ImageIcon("src/resources/maleHuman.jpg"));
                                pandorumBack[newPos][pos[y]] = new Human(PandorumMaps.getIdPlayers(), 'M');
                                pandorumBack[newPos][pos[y]].start();
                            }
                            else {
                                pandorumBack[pos[x]][pos[y]].dead = true;
                                pandorumBack[pos[x]][pos[y]] = null;
                                pandorum[pos[x]][pos[y]].setIcon(null);

                                pandorumBack[newPos][pos[y]].dead = true;
                                pandorum[newPos][pos[y]].setIcon(new ImageIcon("src/resources/femaleHuman.jpg"));
                                pandorumBack[newPos][pos[y]] = new Human(PandorumMaps.getIdPlayers(), 'F');
                                pandorumBack[newPos][pos[y]].start();
                            }
                        }
                    }
                    //If the player that is in this label is an Avatar, the avatar kills this human
                    else {
                        pandorumBack[pos[x]][pos[y]].dead = true;
                        pandorumBack[pos[x]][pos[y]] = null;
                        pandorum[pos[x]][pos[y]].setIcon(null);
                    }
                }
            }
            //Down
            else if (num < 4) {
                //Get the position where the player is going to move
                if (pos[x]+1 > 9) {
                    newPos = 0;
                }
                else {
                    newPos = pos[x]+1;
                }
                //If there's nothing in the row, the player will move to this position
                if (pandorumBack[newPos][pos[y]] == null) {
                    
                    pandorumBack[newPos][pos[y]] = pandorumBack[pos[0]][pos[1]];
                    pandorumBack[pos[x]][pos[y]] = null;
                    pandorum[newPos][pos[y]].setIcon(pandorum[pos[0]][pos[1]].getIcon());
                    pandorum[pos[x]][pos[y]].setIcon(null);
                }
                //If there's a player in the row, the player has to do some actions
                else {
                    if (pandorumBack[newPos][pos[y]] instanceof Human) {
                        if ((pandorumBack[newPos][pos[1]].getSex()) == this.sex) {
                            //If the player is an Human and they're the same sex, they kill themselves
                            pandorumBack[pos[x]][pos[y]].dead = true;
                            pandorumBack[pos[x]][pos[y]] = null;
                            pandorum[pos[x]][pos[y]].setIcon(null);

                            pandorumBack[newPos][pos[y]].dead = true;
                            pandorumBack[newPos][pos[y]] = null;
                            pandorum[newPos][pos[y]].setIcon(null);
                        }
                        else {
                            //If the player isn't the same sex, they'll reproduce and die
                            //the sex will be determined by random
                            if (getRandomNumber() < 4) {
                                pandorumBack[pos[x]][pos[y]].dead = true;
                                pandorumBack[pos[x]][pos[y]] = null;
                                pandorum[pos[x]][pos[y]].setIcon(null);

                                pandorumBack[newPos][pos[y]].dead = true;
                                pandorum[newPos][pos[y]].setIcon(new ImageIcon("src/resources/maleHuman.jpg"));
                                pandorumBack[newPos][pos[y]] = new Human(PandorumMaps.getIdPlayers(), 'M');
                                pandorumBack[newPos][pos[y]].start();
                            }
                            else {
                                pandorumBack[pos[x]][pos[y]].dead = true;
                                pandorumBack[pos[x]][pos[y]] = null;
                                pandorum[pos[x]][pos[y]].setIcon(null);

                                pandorumBack[newPos][pos[y]].dead = true;
                                pandorum[newPos][pos[y]].setIcon(new ImageIcon("src/resources/femaleHuman.jpg"));
                                pandorumBack[newPos][pos[y]] = new Human(PandorumMaps.getIdPlayers(), 'F');
                                pandorumBack[newPos][pos[y]].start();
                            }
                        }
                    }
                    //If the player that is in this label is an Avatar, the avatar kills this human
                    else {
                        pandorumBack[pos[x]][pos[y]].dead = true;
                        pandorumBack[pos[x]][pos[y]] = null;
                        pandorum[pos[x]][pos[y]].setIcon(null);
                    }
                }
            }
//            //Left
            else if (num < 6) {
                //Get the position where the player is going to move
                if (pos[y]-1 < 0) {
                    newPos = 9;
                }
                else {
                    newPos = pos[y]-1;
                }
                //If there's nothing in the row, the player will move to this position
                if (pandorumBack[pos[x]][newPos] == null) {
                    
                    pandorumBack[pos[x]][newPos] = pandorumBack[pos[0]][pos[1]];
                    pandorumBack[pos[x]][pos[y]] = null;
                    pandorum[pos[x]][newPos].setIcon(pandorum[pos[0]][pos[1]].getIcon());
                    pandorum[pos[x]][pos[y]].setIcon(null);
                }
                //If there's a player in the row, the player has to do some actions
                else {
                    if (pandorumBack[pos[x]][newPos] instanceof Human) {
                        if ((pandorumBack[pos[0]][newPos].getSex()) == this.sex) {
                            //If the player is an Human and they're the same sex, they kill themselves
                            pandorumBack[pos[x]][pos[y]].dead = true;
                            pandorumBack[pos[x]][pos[y]] = null;
                            pandorum[pos[x]][pos[y]].setIcon(null);

                            pandorumBack[pos[x]][newPos].dead = true;
                            pandorumBack[pos[x]][newPos] = null;
                            pandorum[pos[x]][newPos].setIcon(null);
                        }
                        else {
                            //If the player isn't the same sex, they'll reproduce and die
                            //the sex will be determined by random
                            if (getRandomNumber() < 4) {
                                pandorumBack[pos[x]][pos[y]].dead = true;
                                pandorumBack[pos[x]][pos[y]] = null;
                                pandorum[pos[x]][pos[y]].setIcon(null);

                                pandorumBack[pos[x]][newPos].dead = true;
                                pandorum[pos[x]][newPos].setIcon(new ImageIcon("src/resources/maleHuman.jpg"));
                                pandorumBack[pos[x]][newPos] = new Human(PandorumMaps.getIdPlayers(), 'M');
                                pandorumBack[pos[x]][newPos].start();
                            }
                            else {
                                pandorumBack[pos[x]][pos[y]].dead = true;
                                pandorumBack[pos[x]][pos[y]] = null;
                                pandorum[pos[x]][pos[y]].setIcon(null);

                                pandorumBack[pos[x]][newPos].dead = true;
                                pandorum[pos[x]][newPos].setIcon(new ImageIcon("src/resources/femaleHuman.jpg"));
                                pandorumBack[pos[x]][newPos] = new Human(PandorumMaps.getIdPlayers(), 'F');
                                pandorumBack[pos[x]][newPos].start();
                            }
                        }
                    }
                    //If the player that is in this label is an Avatar, the avatar kills this human
                    else {
                        pandorumBack[pos[x]][pos[y]].dead = true;
                        pandorumBack[pos[x]][pos[y]] = null;
                        pandorum[pos[x]][pos[y]].setIcon(null);
                    }
                }
            }
//            //Right
            else if (num < 8) {
                //Get the position where the player is going to move
                if (pos[y]+1 > 9) {
                    newPos = 0;
                }
                else {
                    newPos = pos[y]+1;
                }
                //If there's nothing in the row, the player will move to this position
                if (pandorumBack[pos[x]][newPos] == null) {
                    
                    pandorumBack[pos[x]][newPos] = pandorumBack[pos[0]][pos[1]];
                    pandorumBack[pos[x]][pos[y]] = null;
                    pandorum[pos[x]][newPos].setIcon(pandorum[pos[0]][pos[1]].getIcon());
                    pandorum[pos[x]][pos[y]].setIcon(null);
                }
                //If there's a player in the row, the player has to do some actions
                else {
                    if (pandorumBack[pos[x]][newPos] instanceof Human) {
                        if ((pandorumBack[pos[x]][newPos].getSex()) == this.sex) {
                            //If the player is an Human and they're the same sex, they kill themselves
                            pandorumBack[pos[x]][pos[y]].dead = true;
                            pandorumBack[pos[x]][pos[y]] = null;
                            pandorum[pos[x]][pos[y]].setIcon(null);

                            pandorumBack[pos[x]][newPos].dead = true;
                            pandorumBack[pos[x]][newPos] = null;
                            pandorum[pos[x]][newPos].setIcon(null);
                        }
                        else {
                            //If the player isn't the same sex, they'll reproduce and die
                            //the sex will be determined by random
                            if (getRandomNumber() < 4) {
                                pandorumBack[pos[x]][pos[y]].dead = true;
                                pandorumBack[pos[x]][pos[y]] = null;
                                pandorum[pos[x]][pos[y]].setIcon(null);

                                pandorumBack[pos[x]][newPos].dead = true;
                                pandorum[pos[x]][newPos].setIcon(new ImageIcon("src/resources/maleHuman.jpg"));
                                pandorumBack[pos[x]][newPos] = new Human(PandorumMaps.getIdPlayers(), 'M');
                                pandorumBack[pos[x]][newPos].start();
                            }
                            else {
                                pandorumBack[pos[x]][pos[y]].dead = true;
                                pandorumBack[pos[x]][pos[y]] = null;
                                pandorum[pos[x]][pos[y]].setIcon(null);

                                pandorumBack[pos[x]][newPos].dead = true;
                                pandorum[pos[x]][newPos].setIcon(new ImageIcon("src/resources/femaleHuman.jpg"));
                                pandorumBack[pos[x]][newPos] = new Human(PandorumMaps.getIdPlayers(), 'F');
                                pandorumBack[pos[x]][newPos].start();
                            }
                        }
                    }
                    //If the player that is in this label is an Avatar, the avatar kills this human
                    else {
                        pandorumBack[pos[x]][pos[y]].dead = true;
                        pandorumBack[pos[x]][pos[y]] = null;
                        pandorum[pos[x]][pos[y]].setIcon(null);
                    }
                }
            }
        }
    }
    
}
