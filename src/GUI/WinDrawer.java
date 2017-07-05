package GUI; 

import Data.CurrentData;
import static Data.CurrentData.state;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import systems.Fontloader;
import GUI.ScoreDrawer.ButtonListenerS;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WinDrawer {
    
     XBoton aceptar;
     JTextField nickbox;
     JLabel fondowin;
     Fontloader loader = new Fontloader("/Resources/fonts/fotana/Blood.ttf");
     Font  fuentex = loader.MyFont(0, 60);
     
     private final ButtonListenerS lbutton = new ButtonListenerS();
     
    public WinDrawer(JPanel lienzo) {
        intiCC();
        lienzo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lienzo.setBackground(Color.WHITE);
        lienzo.setLayout(null);
        lienzo.add(nickbox);
        lienzo.add(aceptar);
        lienzo.add(fondowin);

    }
    
    public void intiCC() {
        //GENERA BOTON PARA RESUMIR EL JUEGO   
        aceptar = new XBoton(Color.RED, Color.DARK_GRAY);
        aceptar.setBackground(Color.WHITE);
        aceptar.setText("aceptar");
        aceptar.setBounds(200, 360, 203, 120);
        aceptar.setFont(fuentex);
        aceptar.addActionListener(lbutton);
        
        // anañade el boton para salir por que el juego es muy dificil >:v
        nickbox = new JTextField();
        nickbox.setBackground(Color.WHITE);
        nickbox.setText("");
        nickbox.setBounds(460, 360, 203, 120);
        nickbox.setFont(fuentex);
        
        //añade un bonito circulo de fuego en el panel :v
        fondowin = new JLabel();
        fondowin.setBounds(0, 0 , 932, 658);
        fondowin.setFont(fuentex);
        //fondowin.setIcon(getIconImage("/Resources/image/winner.png"));
        fondowin.setIcon(getIconImage("/Resources/image/winner.png"));

    }

    public ImageIcon getIconImage(String path) {
        ImageIcon retvalue = new javax.swing.ImageIcon(getClass().getResource(path));
        return retvalue;
    }

    private class ButtonListenerS implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == aceptar) {
                System.out.println("reach botonreturn");
                CurrentData.layout.show(CurrentData.panel, CurrentData.game);
                state.setGameState( state.getWorld() );
                CurrentData.frame.revalidate();
                //codigo SQL aqui
                //ButtonListernerS puntuacion = new ButtonListenerS();
            }
        }
    }

    
}
