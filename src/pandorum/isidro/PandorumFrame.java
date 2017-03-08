package pandorum.isidro;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/*@author Isidro*/

public class PandorumFrame extends JFrame {
    
    private Timer t;
    //The map where the player are going to move
    private JLabel [][] pandorum;
    //The time where the game starts
    private double startTime;
    //A boolean to say to the other players when is this method refreshing the screen
    private static boolean refreshing;
    //A boolean to say to the other players when the game finish
    private static boolean finish;
    //A vector to save all the players and start their threads
    private ArrayList <Player> players;

    public PandorumFrame() {
        super("Pandorum");
        setLayout(new GridLayout(10, 10, 10, 10));
        
        finish = false;
        //Set the refresh variable to false to say to the player that they can move
        refreshing = false;
        //Getting the matrix with the players
        PandorumMaps pm = new PandorumMaps();
        pandorum = PandorumMaps.getPandorum();
        players = PandorumMaps.getPlayers();
        
        //Display the map in the screen
        for (int i = 0; i < pandorum.length ; i++) {
            for (int j = 0; j < pandorum[0].length; j++) {
                add(pandorum[i][j]);
            }
        }
        
        setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH );
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void startGame() {
        // Start the timer and alter the gui
//        t = new Timer(2000, new PandorumFrame.RefreshScreen());
//        t.setRepeats(false);
        //Getting the time before start
        startTime = System.currentTimeMillis();
        //Moving the characters
        for (int i=0 ; i<players.size() ; i++) {
            players.get(i).start();
        }
        //Start resfreshing the screen
        while (true) {
            RefreshScreen();
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(PandorumFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        t.start();
    }
    
    private void RefreshScreen () {
        refreshing = true;
        if (System.currentTimeMillis() > startTime+30000) {
            exit(0);
        }
        getContentPane().repaint();
        refreshing = false;
    }
    
    
//    private class RefreshScreen implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            refreshing = true;
//            if (System.currentTimeMillis() > startTime+30000) {
//                exit(0);
//            }
//            getContentPane().repaint();
//            refreshing = false;
//        }
//    }

    public static boolean isRefreshing() {
        return refreshing;
    }

    public static boolean isFinish() {
        return finish;
    }
    
}
