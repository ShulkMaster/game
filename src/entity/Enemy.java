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

    private boolean notTop = true;
    private boolean notBot = false;

    public void move( ){
        if( notTop ){
            getPos().y--;
            if( getPos().y == y ){
                notTop = false;
                notBot = true;
            }
        }
        if( notBot ){
            getPos().y++;
            if( getPos().y == y ){
                notBot = false;
                notTop = true;
            }
        }
    }

    public void setSheet( int i, String path ){ sheet[i] = new SpriteSheet( ImageLoader.loadImage(path) ); }
    public Animator getAnimation(){ return animator; }
}
