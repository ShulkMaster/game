package GUI;

import Data.CurrentData;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import systems.Fontloader;


public class ScoreDrawer {

    XBoton botonreturn;
    XBoton actualizar;
    XBoton Ranking;
    Fontloader loader = new Fontloader("/Resources/fonts/fotana/metal.ttf");
    Font fuentex = loader.MyFont(0, 30);
    private JTable tabladejugadores;
    private JScrollPane scroll;
    private DefaultTableModel model;
    private final ButtonListenerS lbutton = new ButtonListenerS();
    //SQL
    private Connection conexion = null;
    private Statement comando = null;
    private ResultSet resultados = null;
    
    //inicio del constructor
    public ScoreDrawer(JPanel panel) {

        //inizializa y carga los Xbottons
        intitBotons();

        //dibuja el label que dice PUNTUACIONES
        JLabel tablero = new JLabel();
        loader = new Fontloader("/Resources/fonts/fotana/metal.ttf");
        fuentex = loader.MyFont(0, 90);
        tablero.setFont(fuentex);
        tablero.setText("PUNTUACIONES");
        tablero.setBounds(266, 20, 400, 100);
        tablero.setForeground(Color.WHITE);

        //creando la tabla de puntuaciones
        
        inittable();

        //agregando al panel
        panel.add(actualizar);
        panel.add(botonreturn);
        panel.add(tablero);
        panel.add(Ranking);
        panel.add(scroll);
    }

    public ImageIcon getIconImage(String path) {
        ImageIcon retvalue = new javax.swing.ImageIcon(path);
        return retvalue;
    }

    private void intitBotons() {
        //dibujar boton para regresar al menu
        botonreturn = new XBoton(getIconImage("src/Resources/image/flecha.png"), Color.DARK_GRAY);
        botonreturn.setBackground(Color.DARK_GRAY);
        botonreturn.setText("Atras");
        botonreturn.setBounds(116, 550, 100, 100);
        botonreturn.setFont(fuentex);
        botonreturn.addActionListener(lbutton);

        //dibuja el boton actualizar ranking
        actualizar = new XBoton(getIconImage("src/Resources/image/update.png"), Color.DARK_GRAY);
        actualizar.setBackground(Color.DARK_GRAY);
        actualizar.setText("Actualizar");
        actualizar.setFont(fuentex);
        actualizar.setBounds(250, 550, 150, 100);
        actualizar.addActionListener(lbutton);

        // dibuja el boton para llenar el raking
        Ranking = new XBoton(getIconImage("src/Resources/image/rank.png"), Color.DARK_GRAY);
        Ranking.setBackground(Color.DARK_GRAY);
        Ranking.setText("Ranking");
        Ranking.setBounds(434, 550, 150, 100);
        Ranking.setFont(fuentex);
        Ranking.addActionListener(lbutton);
    }

    private void inittable() {
        //Dibuja la tabla y la inicializa en vacio
        scroll = new javax.swing.JScrollPane();
        model = new DefaultTableModel(
                new String[][]{},
                new String[]{"Rango", "Jugador", "Puntaje", "Fecha"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tabladejugadores = new javax.swing.JTable();
        tabladejugadores.setFillsViewportHeight(true);
        tabladejugadores.setDragEnabled(false);
        loader = new Fontloader("/Resources/fonts/fotana/fuente.ttf");
        fuentex = loader.MyFont(0, 15);
        tabladejugadores.setFont(fuentex);
        tabladejugadores.setBounds(116, 140, 700, 450);
        tabladejugadores.setModel(model);
        tabladejugadores.setColumnSelectionAllowed(false);
        tabladejugadores.setSelectionBackground(Color.RED);
        tabladejugadores.setSize(new Dimension(700, 450));
        scroll.setViewportView(tabladejugadores);
        scroll.setBounds(116, 140, 700, 400);
        
        
        
    }

    private class ButtonListenerS implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == botonreturn) {
                System.out.println("reach botonreturn");
                CurrentData.layout.show(CurrentData.panel, CurrentData.menu);
                CurrentData.frame.revalidate();
            }
            if (evt.getSource() == actualizar) {
                /* BLOQUE DE CODIGO AQL AQUI
                            
                 */
                
                 try {
            // Obtener datos de la tabla
            this.leerDatos();
            
            while(resultados.next() == true) {
                
                String aux = "";
                for(int i = 1 ; i <= tabladejugadores.getRowCount()+1 ; i++){
                    aux = Integer.toString(i);
                    //rango++; 
                }
                int obtenido;
                String nombre, fecha; 
                //id = resultados.getInt("idPlayer");
                nombre = resultados.getString("nickPlayer");
                obtenido = resultados.getInt("scorePlayer");
                fecha = resultados.getString("fecha");
                
                model.addRow( new Object[] {aux, nombre, obtenido, fecha} );                
            }
            
            this.cerrar();
            
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error de lectura de BD\n\n");
            
            e.printStackTrace();
                    } 
            }
            if (evt.getSource() == Ranking) {
                /* BLOQUE DE CODIGO SQL AQUI
                            
                 */
                //ejemplo de metodo addRow
                //model.addRow(new String[]{"holi2", "jugado2", "inumal2", "tobal2"});

            }

        }
        //abro conexion con la database Game
        private void leerDatos() throws ClassNotFoundException, SQLException {
        String usuario = "postgres";
        String passwd = "5ce3d2a5";
        String instruccion = "SELECT * FROM RANKING ORDER BY scorePlayer DESC LIMIT 10 ";

        Class.forName("org.postgresql.Driver");
        conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Game" + "?" + "user=" + usuario + "&" + "password=" + passwd + "");
        comando = conexion.createStatement();
        resultados = comando.executeQuery(instruccion);
    }
        //cierro conexion con database Game
        private void cerrar() throws SQLException {
        conexion.close();        
        } 
    }
         
    
    
}
