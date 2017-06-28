package GUI;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ScoreDrawer {
     XBoton botonreturn;
     private final Font fuentex = new java.awt.Font("Resources/fonts/fontana/fuente.ttf", Font.BOLD, 50);
    public ScoreDrawer(JPanel panel){
        //botonreturn.setIcon(new ImageIcon("src/Resources/image/calcaca.png"));
        botonreturn = new XBoton(getIconImage("src/Resources/image/flecha.png"),getIconImage("src/Resources/image/calcacamini.png"));
        botonreturn.setIcon(getIconImage("src/Resources/image/calcacamini.png"));
        botonreturn.setBounds(0, 0, 100, 100);
        panel.add(botonreturn);
        JLabel tablero = new JLabel();
        tablero.setFont(fuentex);
        tablero.setText("PUNTUACIONES");
        tablero.setBounds(266, 20, 400, 100);
        tablero.setForeground(Color.WHITE);
        panel.add(tablero);
        
        
    
    }
       public ImageIcon getIconImage(String path){
        ImageIcon retvalue = new javax.swing.ImageIcon(path);
        return retvalue;
    }
    
}
