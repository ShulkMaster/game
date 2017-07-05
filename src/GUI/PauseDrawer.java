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
public class PauseDrawer {
     XBoton resumir;
     XBoton salir;
     JLabel fondodepusa;
     Fontloader loader = new Fontloader("/Resources/fonts/fotana/Blood.ttf");
     Font  fuentex = loader.MyFont(0, 60);
     private final ButtonListenerS lbutton = new ButtonListenerS();
     
    public PauseDrawer(JPanel lienzo) {
        intiCC();
        lienzo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lienzo.setBackground(Color.WHITE);
        lienzo.setLayout(null);
        lienzo.add(salir);
        lienzo.add(resumir);
        lienzo.add(fondodepusa);

    }
    
    public void intiCC() {
        //GENERA BOTON PARA RESUMIR EL JUEGO   
        resumir = new XBoton(Color.RED, Color.DARK_GRAY);
        resumir.setBackground(Color.DARK_GRAY);
        resumir.setText("Resumir");
        resumir.setBounds(360, 200, 203, 120);
        resumir.setFont(fuentex);
        resumir.addActionListener(lbutton);
        
        // anañade el boton para salir por que el juego es muy dificil >:v
        salir = new XBoton(Color.RED, Color.DARK_GRAY);
        salir.setBackground(Color.DARK_GRAY);
        salir.setText("Salir");
        salir.setBounds(360, 350, 203, 120);
        salir.setFont(fuentex);
        salir.addActionListener(lbutton);
        
        //añade un bonito circulo de fuego en el panel :v
        fondodepusa = new JLabel();
        fondodepusa.setBounds(191, 130 , 550, 400);
        fondodepusa.setFont(fuentex);
        fondodepusa.setIcon(getIconImage("/Resources/image/roundfire.gif"));

    }

    public ImageIcon getIconImage(String path) {
        ImageIcon retvalue = new javax.swing.ImageIcon(getClass().getResource(path));
        return retvalue;
    }

    private class ButtonListenerS implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == resumir) {
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
