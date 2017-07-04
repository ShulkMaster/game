package StateMachine;

import java.awt.*;
import javax.swing.*;
import GUI.XBoton;
import Data.CurrentData;
import maps.Level;
import GUI.ScoreDrawer;
import systems.Fontloader;
import systems.GUIFX.menuanimator;

public class Menu implements GameState {

    //declarando todos los elemnetos del GUImenu
    private final GameStateManager state;
    private final JLabel fuego2;
    private final JPanel menuPanel;
    private final JPanel escorepanel = CurrentData.scorePanel;
    private Fontloader loader = new Fontloader("/Resources/fonts/fotana/fuente.ttf");
    private Font fuentex = loader.MyFont(0, 120);
    private JLabel menuicon;
    private JLabel fuego;
    private JLabel startlabel;
    private JLabel titlelabel;
    private XBoton botonstart;
    private XBoton botonscore;
    private XBoton botonsalir;
    private Thread blinklabel;

    public ScoreDrawer PaneldePuntajes;
    //private final AudioManager music; esto es adudio kk ;v
    //private final AudioMaster music;
    
    // inicio del constructor
    public Menu(GameStateManager newGameState) {
        state = newGameState;
        /*musica plox 
        audio manager es una kk no funciona :v
        music = new AudioManager();
        ninguno de los audio manager es capaz de encontrar el mp3 :v en ruta relativa solo en ruta absoluta
        ponagele fix plox
        music = new AudioMaster(this.getClass().getResourceAsStream("/Resources/Music/epicmusic.mp3"));
        boton inicio
         */
               
        //auxiliar del fuego para scorepanel
        fuego2 =  new JLabel();
        fuego2.setIcon(getIconImage("/Resources/image/fire.gif"));
        fuego2.setBounds(0, 0,932 , 658);
               
        // incializamos el subpanel escorepanel y lo dejamos listo para el overlaping 
        escorepanel.setBackground(Color.BLACK);
        escorepanel.setLayout(null);
        escorepanel.add(fuego2); 
        PaneldePuntajes = new ScoreDrawer(escorepanel);
        
        //inicializar el Jpanel que contiene todo el main menu y el listener del mouse
        menuPanel = CurrentData.menuPanel;
        menuPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Presstart(evt);
            }
        });
        menuPanel.setBackground(Color.BLACK);
        menuPanel.setLayout(null);
        
        //inicializamos componentes de la pantalla de inicio
        initJmenu();
        
        //SE agrega todo al panel y se imprime, adding all Jcomponens to the panel
        initJcomponens();
        menuPanel.add(fuego);
        CurrentData.layout.show( CurrentData.panel, CurrentData.menu);
        CurrentData.frame.revalidate();
        
    }
    private void initJmenu() {
        //icono del menu XD las calaveras we
        menuicon = new JLabel();
        menuicon.setIcon(getIconImage("/Resources/image/calavera.png"));
        menuicon.setBounds(66, 0, 800, 328);

        // dibuja el fueguito :3
        fuego = new JLabel();
        fuego.setIcon(getIconImage("/Resources/image/fire.gif"));
        fuego.setBounds(0, 0, 932, 658);

        //imprime el titulo del juego
        titlelabel = new JLabel();
        titlelabel.setFont(fuentex);
        titlelabel.setBounds(210, 350, 650, 220);
        titlelabel.setForeground(Color.WHITE);
        titlelabel.setText("The cave");
        
        //Imprime el mensaje  para dar click en ka pantalla
        startlabel = new JLabel();
        startlabel.setBounds(305, 520, 600, 250);
        fuentex = new java.awt.Font("Resources/fonts/fontana/fuente.ttf", Font.BOLD, 25);
        startlabel.setFont(fuentex);
        startlabel.setText("Click On Screen to start");

        //creamos un thread de animacion para los Jlabels
        blinklabel = new Thread(new menuanimator(startlabel));
        blinklabel.start();
        //añade los elementos al panel
        menuPanel.add(titlelabel);
        menuPanel.add(startlabel);
        menuPanel.add(menuicon);

    }
    private void initJcomponens() {
        loader = new Fontloader("/Resources/fonts/fotana/Blood.ttf");
        fuentex = loader.MyFont(0, 60);
        //boton inicio
        botonstart = new XBoton(Color.RED, Color.DARK_GRAY);
        botonstart.setBackground(Color.DARK_GRAY);
        botonstart.setBounds(351, 350, 230, 70);
        botonstart.setFont(fuentex);
        botonstart.setText("Play");
        botonstart.setVisible(false);
        //boton de scorea
        botonscore = new XBoton(Color.RED, Color.DARK_GRAY);
        botonscore.setBackground(Color.DARK_GRAY);
        botonscore.setBounds(351, 450, 230, 70);
        botonscore.setFont(fuentex);
        botonscore.setText("puntajes");
        botonscore.setVisible(false);
        
        //boton salir 
        botonsalir = new XBoton(Color.RED, Color.DARK_GRAY);
        botonsalir.setBackground(Color.DARK_GRAY);
        botonsalir.setBounds(351, 550, 230, 70);
        botonsalir.setFont(fuentex);
        botonsalir.setText("Salir");
        botonsalir.setVisible(false);

        //ahora los añadimos al panel para ser mostrados
        menuPanel.add(botonstart);
        menuPanel.add(botonscore);
        menuPanel.add(botonsalir);

    }
    public void Presstart(java.awt.event.MouseEvent e) {
        //elimina el mensaje de la pantalla
        titlelabel.setVisible(false);
        //preparando botonsalir para entrar al menu state
        botonsalir.setVisible(true);
        botonsalir.addActionListener((java.awt.event.ActionEvent evt) -> {
            System.exit(0);
        });
        
        //preparando botonscore para entrar al menu state
        botonscore.setVisible(true);
        botonscore.addActionListener((java.awt.event.ActionEvent evt) -> {
            System.out.println("score listener online");
            escorepanel.add(fuego2);
            CurrentData.layout.show(CurrentData.panel, CurrentData.score);
            CurrentData.frame.revalidate();
        });

        //preparando botonstart para entrar al menu state
        botonstart.setVisible(true);
        botonstart.addActionListener((java.awt.event.ActionEvent evt) -> {
            System.out.println("score play online");
            System.out.println("reach boton");
            world();
        });
        blinklabel.suspend();
        //boolean method form legacy
        //menuanimator.continuar = false;
        startlabel.setVisible(false);     

    }

    private boolean firstCall = true;
	@Override public void draw(){
	    if( firstCall ) {
            System.out.println("reach");
            //systems.AudioManager.playMusic("src/Resources/Music/menu.wav");
            firstCall = false;
        }
        //System.out.println("reach menu draw");
	}
	
	
	@Override public void menu() {
        System.out.println( "Menu state" );
	}

	@Override public void world() {
		System.out.println( " Entering World state"	 );
		//AQUI SE LLAMA AL MUNDO
        Level.generateLevel( 0 );
        systems.AudioManager.stopMusic();
        CurrentData.gamePanel.grabFocus();
        CurrentData.panel.requestFocus();
        state.setGameState( state.getWorld());
	}

	@Override public void battle() {
       System.out.println( "No has ni entrado al mundo! " );
	}

	@Override public void pause() {
       System.out.println( "No puedees pausar el menu!" );
	}

	@Override public void gameOver() {
       System.out.println( "Como pierdes estando en el menu???" );
	}
    public ImageIcon getIconImage(String path) {
        ImageIcon retvalue = new javax.swing.ImageIcon(getClass().getResource(path));
        return retvalue;
    }               
 }