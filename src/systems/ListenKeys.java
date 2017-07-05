package systems;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import GUI.*;
import Data.CurrentData;
import StateMachine.GameStateManager;
import entity.Player;
import entity.Enemy;
import maps.Tile;

public class ListenKeys implements KeyListener {
    private boolean up, down, left, right, attack, pause;
    private enum Pos{ TOP, MID, BOT }
    private Pos currentPos = Pos.MID;

    //AUXILIAR FIELDS ----------
    private GameStateManager state;
    private Player jugador;
    private Tile[][]  deco;
    private Animator anim;
    private Enemy[] enem = new Enemy[3];
    private PauseDrawer pasado = new  PauseDrawer(CurrentData.pausepanel);
    private GameOverDrawer perdi = new  GameOverDrawer(CurrentData.GameOverPanel);
    private Point iso;
    private Point aux;
    //--------------------------
    
    public ListenKeys(){
    	state = CurrentData.state;
        jugador = CurrentData.jugador;
        deco = CurrentData.lvl.getLayer1();
        anim = CurrentData.jugador.getAnimation();
        iso = CurrentData.jugador.getIso();
        aux =  new Point();
    }

    //Enemy[] enem = CurrentData.enemigo;
    private void enemyCollision() {
        for (Enemy enem : CurrentData.enemigo) {
        //Enemy enem = CurrentData.enemigo[enem];
        System.out.println( "enemy life: " + enem.getLife() );
        if( jugador.attack && enem.checkCollision( jugador.getSBounds() ) ) {
            int damage = ((enem.getLife()) + enem.getDefense() - jugador.getDamage() ) > 0 ?
                    (enem.getLife()) + enem.getDefense() - jugador.getDamage() : 0 ;
            enem.setAgro( true );
            System.out.println( "hit! " +enem.getLife() );
            if( damage == 0 ) {
                enem.setAlive(false);
                jugador.setScore(jugador.getScore() + 100);
                enem.getPos().setLocation(2000, 200);
                enem = null;
            }else
                enem.setLife(damage);
        }
        }
    }

    private void initEnemies(){
        for( int i = 0 ; i < 3 ; i++){
            enem[i] = CurrentData.enemigo[i];
        }
    }


    private boolean checkCollision( String axis ){
        try{
            switch( axis ){
                case "up":
                    aux.setLocation(aux.x = deco[iso.y-1][iso.x].getPos().x, aux.y = deco[iso.y-1][iso.x].getPos().y);
                    return (deco[iso.y - 1][iso.x].isSolid() && jugador.checkCollision(aux.x, aux.y) );
                case "down":
                    aux.setLocation(aux.x = deco[iso.y+1][iso.x].getPos().x, aux.y = deco[iso.y+1][iso.x].getPos().y);
                    return  (deco[iso.y + 1][iso.x].isSolid() && jugador.checkCollision(aux.x, aux.y) );
                case "left":
                    aux.setLocation(aux.x = deco[iso.y][iso.x-1].getPos().x, aux.y = deco[iso.y][iso.x-1].getPos().y);
                    return  (deco[iso.y][iso.x-1].isSolid() && jugador.checkCollision(aux.x, aux.y) );
                case "right":
                    aux.setLocation(aux.x = deco[iso.y][iso.x+1].getPos().x, aux.y = deco[iso.y][iso.x+1].getPos().y);
                    return  (deco[iso.y][iso.x+1].isSolid() && jugador.checkCollision(aux.x, aux.y) );
                default:
                    return false;
        }
        }catch( Exception e ){                                  
            //e.printStackTrace();
            System.out.println( "out of bounce "+ "\n " + e );
            return false;
        }
    }
  	
    @Override public void keyPressed( KeyEvent e ){
        //en cada Key Press solo debemos alterar la sheet que se reproducira
        //al presionar y no hacer el redibujado, de eso se carga el animator y el draw.
        // creo que el if mas interno es innecesario, pero da mas control
        // STANDAR AUXILIAR VARIABLES
        up = e.getKeyCode() == KeyEvent.VK_UP;
        down = e.getKeyCode() == KeyEvent.VK_DOWN; 
        left = e.getKeyCode() == KeyEvent.VK_LEFT;
        right = e.getKeyCode() == KeyEvent.VK_RIGHT;
        attack = e.getKeyCode() == KeyEvent.VK_SPACE;
        pause = e.getKeyCode() == KeyEvent.VK_ESCAPE;
        jugador.toIso();
        CurrentData.enemigo[0].toIso();
        //-------------------------------------------
        
        if( state.getCurrentState() == state.getWorld() )
        	inWorld();

        if( e.getKeyCode() == KeyEvent.VK_E  && state.getCurrentState() != state.getBattle()) {
            jugador.toIso();
            jugador.getPos().setLocation( deco[iso.y][iso.x].getPos() );
            CurrentData.layout.show(CurrentData.panel, CurrentData.gameOver);
            
        }
    }//func

    private void inWorld(){
        jugador.toIso();
        if( left && !checkCollision("left") && iso.x > 1 ){
            jugador.move("left");
        }
        if( right  && !checkCollision("right")  && iso.x < 14){
            jugador.move("right");
        }
        if( up && !checkCollision("up") && iso.y < 38){
            jugador.move("up");
        }
        if( down && !checkCollision("down") && iso.y > 0 ){
            jugador.move("down");
        }
        if( attack ) {
            anim.setCurrentSheet(2);
            enemyCollision();
            System.out.println("ATTACK");
            if (jugador.lastPos == Player.LastPos.UP) {
                jugador.attack("up" );
            }else if (jugador.lastPos == Player.LastPos.LEFT) {
                jugador.attack("left"  );
            }else if (jugador.lastPos == Player.LastPos.RIGHT) {
                jugador.attack("right" );
            }else if (jugador.lastPos == Player.LastPos.DOWN) {
                jugador.attack("down" );
            }
        }

        if (pause) {
            state.setGameState(state.getPause());
            CurrentData.layout.show(CurrentData.panel, CurrentData.pause);
        }

    }

	@Override public void keyReleased(KeyEvent e) {
        //anim.setCurrentSheet(0);
        /*if( left  ){
            jugador.lastPos = Player.LastPos.LEFT;
        }
        if( right ){
            jugador.lastPos = Player.LastPos.RIGHT;
        }
        if( up){
            jugador.lastPos = Player.LastPos.UP;
        }
        if( down ){
            jugador.lastPos = Player.LastPos.DOWN;
        }
        if( attack && anim.getCurrentSheet() != 1 && anim.getCurrentSheet() != 2 ) {
        }*/
    }

	@Override public void keyTyped(KeyEvent e) { }
	
	public KeyListener getListen(){ return this; }
}
