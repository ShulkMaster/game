package StateMachine;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

import Data.CurrentData;
import Data.SpriteSheet;
import entity.Player;
import entity.Enemy;
import maps.GameMap;
import maps.Level;
import maps.Tile;
import systems.Animator;
import systems.ImageLoader;
import systems.ListenKeys;

@SuppressWarnings("serial")
public class World extends JComponent implements  GameState {

    //FIELDS -----------------
    private GameStateManager state;
    
    private ListenKeys lKey;
    //--------------------------

    //AUXILIAR FIELDS ----------
    private Player jugador;
    private Enemy[] enemigo;
    private GameMap lvl;
    private Tile[][] tiles, deco,deco2;
    private Graphics g;
    private Animator anim;
    private boolean firstCall = true;

    private SpriteSheet vida,vidaEnemigo;
    private SpriteSheet barraVida,barraEnemigo;

    private Point iso, pos, origin, aux;

    private int score = 0;
    private final Font font = new java.awt.Font("Resources/fonts/fontana/fuente.ttf", Font.BOLD, 16);
    //--------------------------
    
	World(GameStateManager newGameState){
		state = newGameState;
        loadPlayer();
		/*loadPlayer();
		//loadLevel();
		setData();
        this.setFocusable(true);
        this.addKeyListener( lKey ); */
	}
	
	private void loadPlayer(){
            jugador = new Player(300, 352,192, 20, 20, 22,32,64) ;
            anim = jugador.getAnimation();
            pos = jugador.getPos();
            jugador.setOrigin( 24, 48 );
            origin = jugador.getOrigin();
            iso = jugador.getIso();
            aux = new Point();
            jugador.toIso();
            systems.AudioManager.loadEffect("/Resources/Music/FXsound/espada1.wav");
            //auxVida = (int) jugador.getLife();
	}
	
	private void loadLevel(){
        lvl = Level.getLevel( 0 );
        tiles = lvl.getTiles();
        deco = lvl.getLayer1();
        deco2 = lvl.getLayer2();
        enemigo = new Enemy[1];
        enemigo[0] = new Enemy(300, 400,400,20,20,22,32);
        enemigo[0].setOrigin(24,48);
        setData();
        lKey = new ListenKeys();
        firstCall = false;
        CurrentData.canvas.addKeyListener(lKey);
        this.setFocusable(true);
        this.addKeyListener( lKey );
	}

    private void initGUI(){
        BufferedImage image = ImageLoader.loadImage("/Resources/Sprites/vida.png");
        vida = new SpriteSheet(image);
        barraVida = new SpriteSheet(image);
    }

	private void drawEnemy(){
        /*g.drawImage( enemigo[0].getAnimation().getSprites(
                enemigo[0].getAnimation().getCurrentSheet()).crop(
                        enemigo[0].getAnimation().state() , 0, 64, 64),
                enemigo[0].getPos().x , enemigo[0].getPos().y,
                null );*/
        g.drawImage( enemigo[0].getCurrentAnimation(), enemigo[0].getPos().x, enemigo[0].getPos().y , null);
    }

    private void idle(){
        //aqui esta idle, idle en nuestro contexto
        //se usar para animacion default y walking
        //g.drawImage( anim.getSprites( anim.getCurrentSheet() ).crop( anim.state() , 0, 64, 64), pos.x , pos.y, null );
        //g.drawImage( anim.currentAnimation(0,10,1), pos.x, pos.y , null);
        g.drawImage( jugador.getCurrentAnimation(), pos.x, pos.y , null);
    }

    private void move(){
        //aqui esta idle, idle en nuestro contexto
        //se usar para animacion default y walking
		//g.drawImage( anim.getSprites( anim.getCurrentSheet() ).crop( anim.state() , 0, 64, 64), pos.x , pos.y, null );
        g.drawImage( jugador.getCurrentAnimation(), pos.x, pos.y , null);
    }

    private int tick = 0 ;
    private void attack(){
		/*g.drawImage( anim.getSprites(2).crop( anim.state(), 0, 96, 96), pos.x, pos.y-38, null );
		if( anim.state() >= 800 ) // el limite de la sprite sheet es 800 asi que al llegar se acabo el ataque
		    anim.setCurrentSheet(0);*/
		int x = pos.x;
		int y = pos.y;
        g.drawImage(jugador.getCurrentAnimation(), x - 64, y - 64, null);
        tick++;
        if( tick >= 8 ){
            tick = 0;
            jugador.attack = false;
        }
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

    private void drawDeco(){
        g = state.getGraphics();
        for( int i = 0; i < tiles.length; i++ ) {
            for (int j = 0; j < tiles[i].length; j++) {
                g.drawImage( deco2[i][j].getSprite(), deco[i][j].getPos().x, deco[i][j].getPos().y, null );
            }//inner for
        }//for
    }

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
        g.setColor(Color.black);
        g.fillRect(0,625,932,38);
    } //func

    private void drawGui(){
        int aux = jugador.getLife();
        g.drawImage( barraVida.crop(0,33,100 ,31), 160,620,null );
        g.drawImage(vida.crop(0, 0, jugador.getLife(), 32), 160, 620, null);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString("HP",140,642);
        g.drawString("SCORE: " + score,640,642);

        if( enemigo[0].getAgro() ){
            g.drawImage(vida.crop(0, 10,enemigo[0].getLife(), 11 ), enemigo[0].getPos().x,
                    enemigo[0].getPos().y, null);
        }
    }

    private void debug(){
        int row = (int) ( origin.getX() );
        int col = (int) ( origin.getY() );
        jugador.toIso();
        //System.out.println("j:( " + pos.x + ", " + pos.y + " ) " );
        //System.out.println("jugador ( " + (row) + ", " + (col) + " )" +
        //		"[ " + (row/64) + ", " + (col/16) + " ]" + "iso x,y( " + (iso.x) + ", " + (iso.y) + " )");

        ///PLAYER---------------------------------
        g.setColor( Color.red );
        g.fillRect( (int)jugador.getBounds().getX(), (int)jugador.getBounds().getY(), (int)jugador.getBounds().getWidth(),
                (int)jugador.getBounds().getHeight() );

        g.drawRect( jugador.getPos().x , jugador.getPos().y , 64,64);

        g.setColor(Color.MAGENTA);
        g.fillRect( (int)jugador.getSBounds().getX(), (int)jugador.getSBounds().getY(), (int)jugador.getSBounds().getWidth(),
                (int)jugador.getSBounds().getHeight());

        ///PLAYER---------------------------------

        //ENEMY------------------------------------
        g.setColor(Color.PINK);
        g.fillOval(origin.x, origin.y,15,15);
        g.drawRect(origin.x, origin.y, 64-48, 64-48);

        g.setColor(Color.cyan);
        g.drawRect( enemigo[0].getPos().x , enemigo[0].getPos().y , 64,64);
        g.fillOval(enemigo[0].getOrigin().x, enemigo[0].getOrigin().y,15,15);
        g.drawRect(enemigo[0].getOrigin().x, enemigo[0].getOrigin().y, 64-48, 64-48);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect( (int)enemigo[0].getBounds().getX(), (int)enemigo[0].getBounds().getY(), (int)enemigo[0].getBounds().getWidth(),
                (int)enemigo[0].getBounds().getHeight());
        //ENEMY------------------------------------

    }

    private void drawPlayer(){
        if( /*anim.getCurrentSheet() == 1 &&*/ !jugador.attack )
            move();
       /* if ( /*anim.getCurrentSheet() == 0 && !jugador.attack )
            idle(); */
        if( /*anim.getCurrentSheet() == 2  */ jugador.attack )
            //jugador.attack = true;
            attack();
        /*if( jugador.checkCollision( enemigo.getPos().x , enemigo.getPos().y ) )
            battle();*/
    }


    private void moveEnemy(){
        enemigo[0].move();
    }

    private boolean overLap = false;
	@Override public void draw(){
        if (firstCall) {
            CurrentData.initCanvas();
            loadLevel();
            initGUI();
        }
        moveEnemy();

        //System.out.println("World draw");
        g = state.getGraphics();

        //ESCENARIO ---------------------
        drawMap();
        drawSquares();
        drawEnemy();
        // ------------------------------

        // JUGADOR ---------------------
        overLap = (origin.y > deco2[iso.y][iso.x].getPos().y) && deco2[iso.y][iso.x].isOverLapAble() &&
                !deco2[iso.y - 1][iso.x].isOverLapAble() && !deco2[iso.y + 1][iso.x].isOverLapAble();

        if (overLap) {
            drawPlayer();
            drawDeco();
        } else {
            drawDeco();
            drawPlayer();
        }
        // ------------------------------

        debug();
        drawGui();
	}
	
	@Override public void menu() {
        System.out.println( "Regresando al menu..." );
	    state.setGameState( state.getMenu() );	
	}

    @Override public void battle() {
        System.out.println( "Entrando a batalla " );
        state.getBattle().battle();
	    state.setGameState( state.getMenu() );	
	}

	@Override public void pause() {
		System.out.println( "Pausa " );
	    state.setGameState( state.getPause() );	
	}

	@Override public void gameOver() { System.out.println( "Nadie se puede morir fuera de batalla!" ); }
	@Override public void world() { System.out.println( "Ya estabas en WORLD state" ); }
	
	private void setData(){
        CurrentData.jugador = jugador;
        CurrentData.lvl = lvl;
        CurrentData.lKey = lKey;
        CurrentData.state = state;
        CurrentData.enemigo = enemigo;
	}

}
