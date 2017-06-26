package StateMachine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import GUI.XBoton;
import Data.CurrentData;
import maps.Level;

public class Menu implements GameState {

	private final GameStateManager state;
	private Graphics g;
        private final XBoton botonstart;
        private final XBoton botonscore;
        private final XBoton botonsalir;
        private final JLabel menuicon;
        private final JLabel fuego;
        private final JLabel fuego2;
        private final Font fuentex = new java.awt.Font("Resources/fonts/fontana/fuente.ttf", 2, 15);
        private final ButtonListener lbutton = new ButtonListener();
	
	public Menu( GameStateManager newGameState ){
        
	state = newGameState;
        //boton inicio
        botonstart = new XBoton(Color.BLUE, Color.CYAN);
        botonstart.setBackground(Color.CYAN);
        botonstart.setBounds(401,350,130,30); 
        botonstart.setFont(fuentex);
        botonstart.setText("Play");
        botonstart.addActionListener(lbutton);
        //boton de scorea
        botonscore = new XBoton(Color.BLUE, Color.CYAN);
        botonscore.setBackground(Color.CYAN);
        botonscore.setBounds(401,420,130,30);
        botonscore.setFont(fuentex);
        botonscore.setText("puntajes");
        botonscore.addActionListener(lbutton);
        //boton salir 
        botonsalir = new XBoton(Color.BLUE, Color.CYAN);
        botonsalir.setBackground(Color.CYAN);
        botonsalir.setBounds(401,490,130,30);
        botonsalir.setFont(fuentex);
        botonsalir.setText("Salir");
        botonsalir.addActionListener(lbutton);
        //icono del menu XD las calaveras we
        menuicon = new JLabel();
        menuicon.setIcon(getIconImage("/Resources/image/calavera.png"));
        menuicon.setBounds(66, 0,800 , 328);
        // dibuja el fueguito :3
        fuego = new JLabel();
        fuego.setIcon(getIconImage("/Resources/image/fire.gif"));
        fuego.setBounds(0, 358,400 , 300);
        fuego2 = new JLabel();
        fuego2.setIcon(getIconImage("/Resources/image/fire.gif"));
        fuego2.setBounds(400, 358,400 , 300);
        
	JPanel menuPanel = CurrentData.menuPanel;
        menuPanel.setBackground(Color.BLACK);
        menuPanel.setLayout(null);
        //adding all Jcomponens to the panel
        menuPanel.add(botonstart);
        menuPanel.add(botonscore);
        menuPanel.add(botonsalir);
        menuPanel.add(menuicon);
        menuPanel.add(fuego);
        menuPanel.add(fuego2);
        CurrentData.layout.show( CurrentData.panel, CurrentData.menu);
        CurrentData.frame.revalidate();
        
    }
	
	@Override public void draw(){
        //System.out.println("reach menu draw");
        //g = state.getGraphics();
	}
	
	
	@Override public void menu() {
        System.out.println( "Menu state" );
	}

	
	@Override public void world() {
		System.out.println( " Entering World state"	 );
		//AQUI SE LLAMA AL MUNDO
        Level.generateLevel( 0 );
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
       public ImageIcon getIconImage(String path){
        ImageIcon retvalue = new javax.swing.ImageIcon(getClass().getResource(path));
        return retvalue;
    }
	
	private class ButtonListener implements ActionListener{
			@Override public void actionPerformed(ActionEvent evt) {
			System.out.println( "reach0");
			if( evt.getSource() == botonstart ){
				System.out.println( "reach boton");
				world();
			}
                        if( evt.getSource() == botonscore ){
				System.out.println( "la puta");
			}
                        if( evt.getSource() == botonsalir ){
				System.exit(0);
			}
		}
	}

}

