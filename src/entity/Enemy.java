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
    private int limit;

    public Enemy(int life, int x, int y) {
        super(life, x, y);
        init();
        animator = new Animator( sheet );
        getPos().setLocation(x,y);
        limit = (int) getPos().getY() - 40;
    }

    private void init(){
        //se usa para inicializar
        sheet = new SpriteSheet[1];
        for (int i = 0; i < sheet.length; i++) {
            sheet[i] = new SpriteSheet(null);
        }
        setSheet( 0,"/Resources/Sprites/walk.png");
    }

    private boolean notTop = true;
    private boolean notBot = false;

    private void attack( /*set direction*/){
        //TODO
    }

    public void move( ){
        if( notTop ){
            getPos().y -= velocity;
            System.out.println( getPos().y);
            System.out.println( limit);
            if( getPos().y == limit ){
                System.out.println("limit reach");
                notTop = false;
                notBot = true;
                limit += 80;
            }
        }
        if( notBot ){
            getPos().y += velocity;
            if( getPos().y == limit ){
                notBot = false;
                notTop = true;
                limit -= 80;
            }
        }
    }

    private void setSheet(int i, String path){ sheet[i] = new SpriteSheet( ImageLoader.loadImage(path) ); }
    public Animator getAnimation(){ return animator; }
}
