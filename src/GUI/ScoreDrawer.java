package GUI;
import Data.CurrentData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ScoreDrawer {
     XBoton botonreturn;
     private final Font fuentex = new java.awt.Font("Resources/fonts/fontana/fuente.ttf", Font.BOLD, 50);
     private JTable tabladejugadores;
     private JScrollPane model;
     public ScoreDrawer(JPanel panel){
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
        model = new javax.swing.JScrollPane();
        tabladejugadores = new javax.swing.JTable();

        tabladejugadores.setModel(new javax.swing.table.DefaultTableModel(
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
        ));
        model.setViewportView(tabladejugadores);
        model.setBounds(116, 140, 700, 450);
        panel.add(model);
        tabladejugadores.setDragEnabled(false);
        tabladejugadores.getModel().setValueAt("hola", 0, 0);
        //javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        //panel.setLayout(layout);
        /*
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(model, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(model, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
*/
     }
 
       public ImageIcon getIconImage(String path){
        ImageIcon retvalue = new javax.swing.ImageIcon(path);
        return retvalue;
    }
    
}
