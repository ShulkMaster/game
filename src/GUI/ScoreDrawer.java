package GUI;
import Data.CurrentData;
import StateMachine.Menu;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ScoreDrawer {
     XBoton botonreturn;
     XBoton actualizar;
     private Font fuentex = new java.awt.Font("Resources/fonts/fontana/fuente.ttf", Font.BOLD, 15);
     private final JTable tabladejugadores;
     private final JScrollPane scroll;
     private final DefaultTableModel model;
     private final ButtonListenerS lbutton = new ButtonListenerS();
     public ScoreDrawer(JPanel panel){
        //dibujar boton para regresar al menu
        botonreturn = new XBoton(getIconImage("src/Resources/image/flecha.png"),Color.DARK_GRAY);
        botonreturn.setBackground(Color.DARK_GRAY);
        botonreturn.setText("salida");
        botonreturn.setBounds(116, 550, 100, 100);
        botonreturn.setFont(fuentex);
        botonreturn.addActionListener(lbutton);
        //dibuja el boton actualizar ranking
        actualizar = new XBoton(getIconImage("src/Resources/image/update.png"),Color.DARK_GRAY);
        actualizar.setBackground(Color.DARK_GRAY);
        actualizar.setText("Actualizar");
        actualizar.setBounds(250, 550, 150, 100);
        actualizar.addActionListener(lbutton);
        //dibuja el label que dice PUNTUACIONES
        JLabel tablero = new JLabel();
        fuentex = new java.awt.Font("Resources/fonts/fontana/fuente.ttf", Font.BOLD, 50);
        tablero.setFont(fuentex);
        tablero.setText("PUNTUACIONES");
        tablero.setBounds(266, 20, 400, 100);
        tablero.setForeground(Color.WHITE);
        //agregando al panel
        panel.add(actualizar);
        panel.add(botonreturn);
        panel.add(tablero);
        //Dibuja la tabla y la inicializa en vacio
        scroll = new javax.swing.JScrollPane();
        model = new DefaultTableModel(
                new String [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {"Rango", "Jugador", "Puntaje", "Fecha"}
        );
        tabladejugadores = new javax.swing.JTable();
        tabladejugadores.setDragEnabled(false);
        tabladejugadores.setBounds(116, 140, 700, 450);
        tabladejugadores.setModel(model);
        scroll.setViewportView(tabladejugadores);
        scroll.setBounds(116, 140, 700, 400);
        panel.add(scroll);
        tabladejugadores.getModel().setValueAt("hola", 0, 0);
     }
 
       public ImageIcon getIconImage(String path){
        ImageIcon retvalue = new javax.swing.ImageIcon(path);
        return retvalue;
    }
       private class ButtonListenerS implements ActionListener{
			@Override public void actionPerformed(ActionEvent evt) {
			if( evt.getSource() == botonreturn ){
				System.out.println( "reach botonreturn");
				CurrentData.layout.show( CurrentData.panel, CurrentData.menu);
                                CurrentData.frame.revalidate();
			}
                        if( evt.getSource() == actualizar ){                           
                            /* BLOQUE DE CODIGO AQL AQUI
                            under construction
                            */
			}

		}
	}
    
}
