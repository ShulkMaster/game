package Data;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import GUI.GameFrame;
//import Data.Player;
import systems.Time;
import StateMachine.GameStateManager;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Game{
	//ticker
	private boolean running = true;
	//-----------------------------
	
	private GameStateManager gameStateMachine;
	private GameFrame window;
	private BufferStrategy bs;
	private Graphics g;
	private boolean firstCall = true;

    public Game(Dimension dimension) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				//System.out.println(info.getName());
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}
		window = new GameFrame();
		window.init((int) dimension.getWidth(), (int) dimension.getHeight());
		gameStateMachine = new GameStateManager(dimension);
		window.getCanvas().requestFocus();
		window.getCanvas().createBufferStrategy(3);
	}


    private void update(){
        gameStateMachine.draw();
    }

    void init(){
    	try{
			bs = window.getCanvas().getBufferStrategy();
			if(bs == null){
				window.getCanvas().createBufferStrategy(3);
				return;
			}
			System.out.println("Menu stopped");
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	Time.start();
        start();
    }
    
    private void start(){
        Thread t = new Thread() {
			@Override public synchronized void run() {
				while (running) {
					try {
						bs = window.getCanvas().getBufferStrategy();
						g = bs.getDrawGraphics();
						gameStateMachine.setG(g);
						bs.show();
						update();
						g.dispose();

						sleep(Time.getTime());
					} catch (Exception e) {
						e.getStackTrace();
					}
				}
			}
		};
        t.start();
    }
 

}
