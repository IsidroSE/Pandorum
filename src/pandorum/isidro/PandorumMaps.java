package pandorum.isidro;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*@author Isidro*/

public class PandorumMaps {

    //Map that is going to show in the screen
    private static JLabel [][] pandorum;
    //The map that has the player and control the move of the player off-screen
    private static Player [][] pandorumBack;
    //Random numbers, these numbers are used to create the players and put them into the maps
    private Random rand;
    private int randNum1;
    private int randNum2;
    //The number of players
    private int numPlayers = 30;
    //Variable to put draw in the screen the players' image
    private ImageIcon icon;
    //A vector to save all the players and start their threads
    private static ArrayList <Player> players;
    //The id of the players
    private static int idPlayers;
    
    public PandorumMaps () {
        idPlayers = 0;
        //Initializing the matrix
        pandorum = new JLabel[10][10];
        pandorumBack = new Player[10][10];
        players = new ArrayList();
        //--------------------------------------------------
        //Putting players into both matrix
        fillMatrix();
        
    } //end Constructor
    
    //Put players into both matrix
    private void fillMatrix () {
        rand = new Random();
        
        for (int i = 0; i < pandorum.length ; i++) {
            for (int j = 0; j < pandorum[0].length; j++) {
                pandorum[i][j] = new JLabel();
                pandorum[i][j].setBorder(BorderFactory.createLineBorder(
                        new Color(0, 0, 0)));
            }
        }
        
        while (numPlayers > 0) {
            
            randNum1 = rand.nextInt(pandorum.length);
            randNum2 = rand.nextInt(pandorum.length);
            
            //Humans
            if (numPlayers > 10) {
                //Male Humans
                if (numPlayers > 20) {
                    if (pandorumBack[randNum1][randNum2] == null) {
                        pandorumBack[randNum1][randNum2] = new Human(idPlayers, 'M');
                        icon = new ImageIcon("src/resources/maleHuman.jpg");
                        pandorum[randNum1][randNum2] = new JLabel(icon);
                        pandorum[randNum1][randNum2].setBorder(BorderFactory.createLineBorder(
                        new Color(0, 0, 0)));
                        players.add(pandorumBack[randNum1][randNum2]);
                        numPlayers--;
                        idPlayers++;
                    }
                }
                //Female Humans
                else {
                    if (pandorumBack[randNum1][randNum2] == null) {
                        pandorumBack[randNum1][randNum2] = new Human(idPlayers, 'F');
                        icon = new ImageIcon("src/resources/femaleHuman.jpg");
                        pandorum[randNum1][randNum2] = new JLabel(icon);
                        pandorum[randNum1][randNum2].setBorder(BorderFactory.createLineBorder(
                        new Color(0, 0, 0)));
                        players.add(pandorumBack[randNum1][randNum2]);
                        numPlayers--;
                        idPlayers++;
                    }
                }
            }
            //Avatars
            else {
                //Male Avatars
                if (numPlayers > 5) {
                    if (pandorumBack[randNum1][randNum2] == null) {
                        pandorumBack[randNum1][randNum2] = new Avatar(idPlayers, 'M');
                        icon = new ImageIcon("src/resources/maleAvatar.jpg");
                        pandorum[randNum1][randNum2] = new JLabel(icon);
                        pandorum[randNum1][randNum2].setBorder(BorderFactory.createLineBorder(
                        new Color(0, 0, 0)));
                        players.add(pandorumBack[randNum1][randNum2]);
                        numPlayers--;
                        idPlayers++;
                    }
                }
                //Female Avatars
                else {
                    if (pandorumBack[randNum1][randNum2] == null) {
                        pandorumBack[randNum1][randNum2] = new Avatar(idPlayers, 'F');
                        icon = new ImageIcon("src/resources/femaleAvatar.jpg");
                        pandorum[randNum1][randNum2] = new JLabel(icon);
                        pandorum[randNum1][randNum2].setBorder(BorderFactory.createLineBorder(
                        new Color(0, 0, 0)));
                        players.add(pandorumBack[randNum1][randNum2]);
                        numPlayers--;
                        idPlayers++;
                    }
                }
            }
        } //end while
    } //end fillMatrix

    public static JLabel[][] getPandorum() {
        return pandorum;
    }

    public static Player[][] getPandorumBack() {
        return pandorumBack;
    }
    
    //Return the position where the player is in the map
    public static int [] playerPosition (Player p) {
        int [] pos = new int [2];
        
        for (int i=0 ; i<pandorumBack.length ; i++) {
            for (int j=0 ; j<pandorumBack[0].length ; j++) {
                //If the player is the same that is in the matrix, it will return its position
                if (pandorumBack[i][j] != null) {
                    if (pandorumBack[i][j].equals(p)) {
                        //Horizontal
                        pos[0] = i;
                        //Vertical
                        pos[1] = j;
                        return pos;
                    }
                }
            }
        }
        
        return null;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }
    
    public static int getIdPlayers() {
        idPlayers++;
        return idPlayers;
    }
    
} // end PandorumMaps
