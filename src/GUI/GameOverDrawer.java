package GUI;

import Data.CurrentData;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import systems.Fontloader;

public class GameOverDrawer {

    XBoton reiniciar;
    XBoton salir;
    JLabel fondoover;
    Fontloader loader = new Fontloader("/Resources/fonts/fotana/Blood.ttf");
    Font fuentex = loader.MyFont(0, 60);
    private final ButtonListenerS lbutton = new ButtonListenerS();

    public GameOverDrawer(JPanel lienzo) {
        initbotons();
        lienzo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lienzo.setBackground(Color.BLACK);
        lienzo.setLayout(null);
        lienzo.add(salir);
        lienzo.add(reiniciar);
        lienzo.add(fondoover);

    }

    public void initbotons() {
        //GENERA BOTON PARA reiniciar EL JUEGO   
        reiniciar = new XBoton(Color.RED, Color.DARK_GRAY);
        reiniciar.setBackground(Color.DARK_GRAY);
        reiniciar.setText("Reiniciar");
        reiniciar.setBounds(144, 550, 250, 100);
        reiniciar.setFont(fuentex);
        reiniciar.addActionListener(lbutton);

        // anañade el boton para salir por que el juego es muy dificil >:v
        salir = new XBoton(Color.RED, Color.DARK_GRAY);
        salir.setBackground(Color.DARK_GRAY);
        salir.setText("Salir");
        salir.setBounds(538, 550, 250, 100);
        salir.setFont(fuentex);
        salir.addActionListener(lbutton);

        //añade un bonito logo de calavera en el panel :v
        fondoover = new JLabel();
        fondoover.setBounds(0, 0, 932, 658);
        fondoover.setFont(fuentex);
        fondoover.setIcon(getIconImage("/Resources/image/gameover.png"));

    }

    public ImageIcon getIconImage(String path) {
        ImageIcon retvalue = new javax.swing.ImageIcon(getClass().getResource(path));
        return retvalue;
    }

    private class ButtonListenerS implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == reiniciar) {
                System.out.println("reach botonreturn");
                CurrentData.layout.show(CurrentData.panel, CurrentData.game);
                CurrentData.frame.revalidate();
            }
            if (evt.getSource() == salir) {
                CurrentData.layout.show(CurrentData.panel, CurrentData.menu);
                CurrentData.frame.revalidate();
            }
        }
    }
}
