package StateMachine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import Data.CurrentData;
import Data.SpriteSheet;
import entity.Player;
import entity.Enemy;
import maps.Tile;
import systems.Animator;
import systems.ImageLoader;


@SuppressWarnings("serial")
public class Battle implements GameState {

    //FIELDS -----------------
	private GameStateManager state;
    private boolean inBattle = false;
    //--------------------------

    //AUXILIAR FIELDS ----------
    private Player jugador;
    private Enemy[] enemigo;
    private Tile[][] tiles, deco,deco2;
    private Graphics g;
    private Animator anim;
    private SpriteSheet vida,vidaEnemigo;
    private SpriteSheet barraVida,barraEnemigo;

    private Point pos;
    //--------------------------

    Battle(GameStateManager newGameState){
		state = newGameState;
        //this.setFocusable(true);
        //this.addKeyListener( lKey );
	}

	private void initGUI(){
        BufferedImage image = ImageLoader.loadImage("/Resources/Sprites/vida.png");
        vida = new SpriteSheet(image);
        barraVida = new SpriteSheet(image);
    }

    private void drawEnemy(){
        g.drawImage( enemigo[0].getAnimation().getSprites(
                enemigo[0].getAnimation().getCurrentSheet()).crop(
                enemigo[0].getAnimation().state() , 0, 64, 64),
                enemigo[0].getPos().x , enemigo[0].getPos().y,
                null );
    }

    private void idle(){
        //aqui esta idle, idle en nuestro contexto
        //se usar para animacion default y walking
		g.drawImage( anim.getSprites( anim.getCurrentSheet() ).crop( anim.state() , 0, 84, 84), pos.x , pos.y, null );
    }

    private void move(){
        //aqui esta idle, idle en nuestro contexto
        //se usar para animacion default y walking
		g.drawImage( anim.getSprites( anim.getCurrentSheet() ).crop( anim.state() , 0, 64, 64), pos.x , pos.y, null );
    }

    private void attack(){
		g.drawImage( anim.getSprites(2).crop( anim.state(), 0, 96, 96), pos.x, pos.y-38, null );
		if( anim.state() >= 800 ) // el limite de la sprite sheet es 800 asi que al llegar se acabo el ataque
		    anim.setCurrentSheet(0);
    }

    private void drawMap(){
        g = state.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect( 0,0,CurrentData.frame.getWidth(), CurrentData.frame.getHeight() );
        for( int i = 0; i < tiles.length; i++ ){
            for(int j = 0; j < tiles[i].length; j++ ){
                g.drawImage( tiles[i][j].getSprite(), tiles[i][j].getPos().x, tiles[i][j].getPos().y, null );
                g.drawImage( deco[i][j].getSprite(), deco[i][j].getPos().x, deco[i][j].getPos().y, null );
            }//inner for
        }//for
    }//func

    private void drawSquares(){
        g.setColor( new Color(21, 104, 64));
        int x,y;
        for( int i = 0; i <= 14; i++ ){
            for(int j = 0; j <= 39 ; j++ ){
                if ( j%2 != 0 ){
                    x = (i*64)-32;
                    y = ( j*16 )-16;
                    g.drawLine( x, y, x+64, y + 32 );
                    g.setColor( new Color(21, 104, 64));
                    g.drawLine( x, y,x+64, y-32);
                }
            }//inner for
        }//for
    } //func

    private void drawDeco(){
        g = state.getGraphics();
        for( int i = 0; i < tiles.length; i++ ) {
            for (int j = 0; j < tiles[i].length; j++) {
                g.drawImage( deco2[i][j].getSprite(), deco[i][j].getPos().x, deco[i][j].getPos().y, null );
            }//inner for
        }//for
    }

    private void drawPlayer(){
        if ( anim.getCurrentSheet() == 0 )
            idle();
        if( anim.getCurrentSheet() == 1 )
            move();
        if( anim.getCurrentSheet() == 2 )
            attack();
    }

    private void drawGui(){
        g.drawImage( barraVida.crop(0,33,100,31), pos.x-20,pos.y-42,null );
        g.drawImage(vida.crop(0,0, 100,32), pos.x-20, pos.y-42 , null );
    }

	@Override
	public void draw(){
		if(!inBattle){
			battle();
			initGUI();
			inBattle = true;
			g.dispose();
		}
        //System.out.println( "Battle draw" );
		g = state.getGraphics();
        //ESCENARIO ---------------------
		drawMap();
        drawSquares();
		//debug();
		// ------------------------------

        // JUGADOR ---------------------
        drawEnemy();
        drawPlayer();
		// ------------------------------
        drawDeco();
        drawGui();
	}
	
	@Override
	public void menu() {
        System.out.println( "Regresando al menu..." );
        state.setGameState( state.getMenu() );
	}

	@Override
	public void world() {
        System.out.println( "Batalla terminada." );
        state.setGameState( state.getWorld() );
	}

	@Override
	public void battle() {
        jugador = CurrentData.jugador;
        tiles = CurrentData.lvl.getTiles();
        deco = CurrentData.lvl.getLayer1();
        deco2 = CurrentData.lvl.getLayer2();
        anim = CurrentData.jugador.getAnimation();
        pos = CurrentData.jugador.getPos();
        //lKey = CurrentData.lKey;
	}

	@Override
	public void pause() {
        System.out.println( "Pausando batalla..." );
        state.setGameState( state.getPause() );
	}

	@Override
	public void gameOver() {
        System.out.println( "Batalla perdida." );
        state.setGameState( state.getGameOver() );
	}

}
