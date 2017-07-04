package systems.GUIFX;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;


public class menuanimator implements Runnable {
    int y,x;
    JLabel paraFX;
    public static boolean continuar;

    public menuanimator(JLabel toFX){ 
        paraFX = toFX;
        continuar = true;
    }

    @Override
    public void run() {
        try {
            while (continuar) {
                paraFX.setVisible(false);
                Thread.sleep(750);
                paraFX.setVisible(true);
                Thread.sleep(750);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(menuanimator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
