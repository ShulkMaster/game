package StateMachine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import GUI.XBoton;
import Data.CurrentData;

public class Menu implements GameState {

	private GameStateManager state;
	private Graphics g;
        private XBoton botonstart;
        private XBoton botonscore;
        private XBoton botonsalir;
        private ButtonListener lbutton = new ButtonListener();
	
	public Menu( GameStateManager newGameState ){
	state = newGameState;
        botonstart = new XBoton(Color.BLUE, Color.CYAN);
        botonstart.setBackground(Color.CYAN);
        botonstart.setBounds(436,317,130,25);
        botonstart.setText("Lord Zerxes");
	JPanel menuPanel = CurrentData.menuPanel;
        menuPanel.setBackground(Color.BLACK);
        menuPanel.setLayout(null);
        menuPanel.add(botonstart);
        botonstart.addActionListener(lbutton);
        CurrentData.layout.show( CurrentData.panel, CurrentData.menu);
        CurrentData.frame.revalidate();
        

    }
	
	@Override public void draw(){
        //System.out.println("reach menu draw");
        //g = state.getGraphics();

        //cadena("hola A33", 14, 400, 350, Color.BLACK);
	}
	
	private void cadena (String cadena, int fontsize, int Xcor, int Ycor, Color color ){
		g.setColor(color);
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
			@Override public void actionPerformed(ActionEvent evt) {
			System.out.println( "reach0");
			if( evt.getSource() == botonstart ){
				System.out.println( "reach boton");
				world();
			}
		}
	}

}

