package entity;

import Data.CurrentData;
import Data.SpriteSheet;
import systems.Animator;
import systems.Collider;
import systems.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Data.CurrentData.jugador;

public class Enemy extends Character {
    private SpriteSheet[] sheet;
    private SpriteSheet singleSheet;
    private Animator animator;
    private Collider collider;
    private int velocity = 5;
    private int limit;
    private boolean agro = false;
    private int defense = 2;
    private boolean alive = true;

    int start,height,limit2;

    public Enemy(int life, int x, int y) {
        super(life, x, y);
        init();
        animator = new Animator( singleSheet,singleSheet.sheetWidht(),singleSheet.sheetHeight(),64 );
        getPos().setLocation(x,y);
        limit = (int) getPos().getY() - 40;
    }

    public Enemy(int life, int x, int y , int width, int height, int ox, int oy) {
        super(life, x, y);
        init();
        animator = new Animator( singleSheet,singleSheet.sheetWidht(),singleSheet.sheetHeight(),64 );
        getPos().setLocation(x,y);
        collider = new Collider( x, y , width, height, ox, oy );
        limit = (int) getPos().getY() - 40;
    }

    private void init(){
        //se usa para inicializar
        sheet = new SpriteSheet[1];
        for (int i = 0; i < sheet.length; i++) {
            sheet[i] = new SpriteSheet(null);
        }
        setSheet( "/Resources/Sprites/eskel.png");
    }

    private boolean notTop = true;
    private boolean notBot = false;

    public BufferedImage getCurrentAnimation(){
        return animator.currentAnimation(start, height,limit2);
    }

    private void setCurrentAnimation( int start, int height, int limit ){
        this.start = start;
        this.height = height;
        this.limit2 = limit;
    }

    public void move( ){
        if( notTop  && alive ){
            getPos().y -= velocity;
            origin.y -= velocity;
            //System.out.println( getPos().y);
            //System.out.println( limit);
            if( getPos().y == limit ){
                //System.out.println("limit reach");
                notTop = false;
                notBot = true;
                limit += 80;
                setCurrentAnimation(1,2,8);
            }
        }
        if( notBot  && alive ){
            getPos().y += velocity;
            origin.y += velocity;
            if( getPos().y == limit ){
                notBot = false;
                notTop = true;
                limit -= 80;
                setCurrentAnimation(1,0,8);
            }
        }
        updateBounds();
        if ( checkCollision( jugador.getBounds() )) {
            System.out.println( "player collision vs enemy: " + jugador.getLife() );
            int damage = ((jugador.getLife()) + jugador.getDefense() - jugador.getDamage() ) > 0 ?
                    (jugador.getLife()) + jugador.getDefense() - jugador.getDamage() : 0 ;
            if( jugador.getLife() == 0 )
                CurrentData.state.setGameState(CurrentData.state.getGameOver());
            jugador.setLife(damage);
            System.out.println( jugador.getLife() );
        }
    }

    public boolean checkCollision( Rectangle r ) {
        int x = (int) (collider.getBounds().getX());
        int y = (int) (collider.getBounds().getY());

        return x < r.x + r.width && x + collider.getBounds().getWidth() > r.x &&
                y < r.y + r.height && y + collider.getBounds().getHeight() > r.y;

    }

    private void updateBounds(){
        collider.updateBound(pos.x, pos.y);
    }

    private void setSheet(int i, String path){ sheet[i] = new SpriteSheet( ImageLoader.loadImage(path) ); }
    private void setSheet( String path ){ singleSheet = new SpriteSheet( ImageLoader.loadImage(path)); }
    public void setAgro( boolean agro ){ this.agro = agro; }
    public void setDefense(){ this.defense = defense; }
    public void setAlive( boolean alive ){ this.alive = alive; }

    public Animator getAnimation(){ return animator; }
    public Rectangle getBounds(){ return collider.getBounds(); }
    public boolean getAgro(){ return agro; }
    public int getDefense(){ return defense; }
}
