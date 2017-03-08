package pandorum.isidro;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.*;
import javax.swing.*;

//Borrar una vez acabado el programa, clase de pruebas donde he copy pasteado codigo y probado cosas

public class PruebaPandora extends JFrame {
    JLabel[][] pandorum;

    public PruebaPandora() {
        super("Pandora");
        setLayout(new GridLayout(10, 10, 10, 10));

        pandorum = new JLabel[10][10];

        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                pandorum[i][j] = new JLabel(" ");
                pandorum[i][j].setBorder(BorderFactory.createLineBorder(
                        new Color(0, 0, 0)));
            }
        }

        pandorum[5][5] = new JLabel("MH");
        pandorum[5][5].setBorder(BorderFactory.createLineBorder(
                        new Color(0, 0, 0)));

        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                add(pandorum[i][j]);
            }
        }

        Toolkit myScreen = Toolkit.getDefaultToolkit();
        Dimension screenSize = myScreen.getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        setSize(width, height);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void start() {
        // Start the timer and alter the gui
        Timer t = new Timer(5000, new MutationListener());
        t.setRepeats(false);
        t.start();
    }

    public static void main(String[] args) {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        PruebaPandora aplicacion = new PruebaPandora();
                        aplicacion.start();
                    }
                });
        } catch (Exception e) {
            Logger.getLogger(PruebaPandora.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private class MutationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            pandorum[5][4].setText(pandorum[5][5].getText());
            pandorum[5][5].setText("");
            getContentPane().repaint();
        }
    }
}
