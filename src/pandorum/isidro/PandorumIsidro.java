package pandorum.isidro;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/*@author Isidro*/

public class PandorumIsidro {

    public static void main(String[] args) {
        
        PandorumFrame aplicacion = new PandorumFrame();
        aplicacion.startGame();
        
//        try {
//            SwingUtilities.invokeAndWait(new Runnable() {
//                    @Override
//                    public void run() {
//                        PandorumFrame aplicacion = new PandorumFrame();
//                        aplicacion.start();
//                    }
//                });
//        } catch (Exception e) {
//            Logger.getLogger(PruebaPandora.class.getName()).log(Level.SEVERE, null, e);
//        }
    }

}
