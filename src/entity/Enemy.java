package entity;

import Data.SpriteSheet;
import systems.Animator;
import systems.Collider;
import systems.ImageLoader;

public class Enemy extends Character {
    private SpriteSheet[] sheet;
    private Animator animator;
    private Collider collider;
    private int velocity = 5;
    private int x, y;

    public Enemy(int life, int x, int y) {
        super(life, x, y);
        this.x = x;
        this.y = y;
        init();
        animator = new Animator( sheet );
        this.getPos().setLocation(x,y);
    }

    private void init(){
        //se usa para inicializar
        sheet = new SpriteSheet[1];
        for (int i = 0; i < sheet.length; i++) {
            sheet[i] = new SpriteSheet(null);
        }
        setSheet( 0,"/Resources/Sprites/idle.png");
    }

    public synchronized void move( String aux){
        y -= 10;
        Thread hilo = new Thread() {
            @Override public void run(){
                String axis = aux;
                if( axis == "up") {
                    while (axis == "up") {
                        if( getPos().y != y ){
                            y = ( getPos().y > y ) ? y--: y;
                            axis = ( getPos().y == y ) ? "down": axis;
                        }
                    }
                }
                if( axis == "down"){
                    while ( axis == "down") {
                        if( getPos().y != y ){
                            y = ( getPos().y < y ) ? y++: y;
                            axis = ( getPos().y == y ) ? "up": axis;
                        }
                    }

                }

            }
        };
        hilo.start();
    }

    public void setSheet( int i, String path ){ sheet[i] = new SpriteSheet( ImageLoader.loadImage(path) ); }
    public Animator getAnimation(){ return animator; }
}
