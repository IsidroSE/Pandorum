package pandorum.isidro;

import java.util.Random;

/*@author Isidro*/

public abstract class Player extends Thread {

    int id;
    char sex;
    boolean dead;
    final int x = 0;
    final int y = 1;
    
    public Player (int id, char sex) {
        this.id = id;
        this.sex = sex;
        dead = false;
    }
    
    @Override
    public void run(){}
    
    //Returns a random number between 0 and 7
    public static int getRandomNumber () {
        Random rand;
        rand = new Random();
        int num = rand.nextInt(8);
        
        return num;
    }

    public char getSex() {
        return sex;
    }

    public int getIdPlayer() {
        return id;
    }
    
    @Override
    public boolean equals (Object o) {
        boolean equal = false;
        
        if (o instanceof Player) {
            if (((Player)o).getIdPlayer() == this.id) {
                equal = true;
            }
        }
        
        return equal;
    }
    
    public synchronized void move (Player p) {}
    
}
