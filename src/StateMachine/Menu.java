package StateMachine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.XBoton;
import javax.swing.JPanel;

import Data.CurrentData;

public class Menu implements GameState {

	private GameStateManager state;
	private Graphics g;
    	private XBoton botonstart;
	private XBoton botonscores;
	private XBoton botonexit;
    	private ButtonListener lbutton = new ButtonListener();
	
	public Menu( GameStateManager newGameState ){
	state = newGameState
        botonstart = new XBoton(Color.RED, Color.BLUE);
		botonstart.setColor(Color.RED);
        botonstart.setText("Play");
	botonstart.setBounce(436,317,60,25);


        CurrentData.panel.setBackground(Color.BLACK);
		CurrentData.panel.setLayout(null);
		CurrentData.panel.add(botonstart);
		CurrentData.panel.remove( CurrentData.canvas );
		CurrentData.panel.revalidate();
        boton.addActionListener(lbutton);
    }
	
	@Override public void draw(){
        System.out.println("reach menu draw");
        //g = state.getGraphics();

        //cadena("hola A33", 14, 400, 350, Color.BLACK);
        
	}
	
	private void cadena (String cadena, int fontsize, int Xcor, int Ycor, Color spectroRGB ){
		g.setColor(spectroRGB);
		g.setFont(new Font("/Resources/fonts/fotana/upheavtt.ttf", Font.PLAIN, fontsize));
		g.drawString(cadena, Xcor, Ycor);
	}
	
	@Override public void menu() {
        System.out.println( "Menu state" );
	}

	
	@Override public void world() {
	System.out.println( " Entering World state"	 );
        //para cambiar de estado solo usamos el initCanvas
        //y luego transicionamos
        CurrentData.initCanvas();
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
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent evt) {
			System.out.println( "reach0");
			if( evt.getSource() == boton ){
				System.out.println( "reach");
				world();
			}
			
		}
		
	}

}

