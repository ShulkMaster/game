package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import Data.SpriteSheet;
import systems.Animator;
import systems.Collider;
import systems.ImageLoader;

public class Player extends Character {
	private SpriteSheet[] sheet;
    private Animator animator;
    private Collider collider;
    private Collider swordCollider;
    private int velocity = 5;
    public enum LastPos{ UP,LEFT,RIGHT,DOWN }
    public LastPos lastPos = LastPos.DOWN;
    private boolean moving = false;
    public boolean attack = false;
    private int damage = 8;
    private int defense = 3;
    private int score = 0;

    //animation
    int start,height,limit;

    public Player( int life, int x, int y, int width, int height, int ox, int oy, int pixels ){
        super( life, x, y );
        init();
        if( pixels != 0 ) {
            animator = new Animator(sheet[0], sheet[0].sheetWidht(), sheet[0].sheetHeight(), pixels);
            animator.setAuxAnimation(21,64,4);
        }
        else
            animator = new Animator( sheet );

        collider = new Collider( x, y , width, height, ox, oy );
        swordCollider = new Collider(x,y ,40,5,ox,oy );
        System.out.println( "jugador vida: " + super.getLife() );
    }


    private void init(){
        //se usa para inicializar
        sheet = new SpriteSheet[3];
        for (int i = 0; i < sheet.length; i++) {
			sheet[i] = new SpriteSheet(null);
		}      
        //Cargamos las hojas de sprites que utilizara nuestro personaje
        //Esto se puede lograr con cualquier tipo de objeto mediante
        //la clase SpriteSheet e ImageLoader

        setSheet( 0,"/Resources/Sprites/sprite.png");
        //setSheet( 0,"/Resources/Sprites/idle.png");
        //setSheet( 1,"/Resources/Sprites/walk.png");
        //setSheet( 2,"/Resources/Sprites/attack.png");
    }

    public BufferedImage getCurrentAnimation(){
        if( attack ) {
            attack = false;
            return animator.currentAnimation(start, height, limit);
        }
        if( lastPos == LastPos.UP && !moving )
            return animator.currentAnimation(0, 8,1);
        if( lastPos == LastPos.LEFT  && !moving )
            return animator.currentAnimation(0, 9,1);
        if( lastPos == LastPos.RIGHT  && !moving )
            return animator.currentAnimation(0, 11,1);
        if( lastPos == LastPos.DOWN  && !moving )
            return animator.currentAnimation(0, 10,1);
        if( moving ) {
            moving = false;
            return animator.currentAnimation(start, height, limit);
        }
        return animator.currentAnimation(1, 10,1);
    }

    private void setCurrentAnimation( int start, int height, int limit ){
        this.start = start;
        this.height = height;
        this.limit = limit;
    }

    public void attack(String axis /*, Graphics g */){
        attack = true;
        //systems.AudioManager.playSound();
        switch( axis ) {
            case "up":
                System.out.println( "attack up");
                swordCollider.updateBound(pos.x,pos.y-40, 5,30);
                setCurrentAnimation(0,21,6);
                //animator.playAnimation(g,21,6,pos.x,pos.y);
                break;
            case "left":
                swordCollider.updateBound(pos.x-40,pos.y, 30,5);
                System.out.println( "attack left");
                setCurrentAnimation(0,22,6);
                //animator.playAnimation(g,22,6,pos.x,pos.y);
                break;
            case "right":
                swordCollider.updateBound(pos.x+40,pos.y,30,5);
                System.out.println( "attack right");
                setCurrentAnimation(0,24,6);
                //animator.playAnimation(g,24,6,pos.x,pos.y);
                break;
            case "down":
                swordCollider.updateBound(pos.x,pos.y+40,5,20);
                setCurrentAnimation(0,21,6);
                System.out.println( "attack down");
                setCurrentAnimation(0,23,6);
                //animator.playAnimation(g,23,6,pos.x,pos.y);
                break;
            default:
                break;
        }
    }

    public void move( String axis ){
        switch( axis ){
            case "up":
                pos.y -= velocity;
                origin.y -= velocity;
                updateBounds();
                moving = true;
                lastPos = LastPos.UP;
                setCurrentAnimation(1,8,8);
                break;
            case "left":
                pos.x -= velocity;
                origin.x -= velocity;
                updateBounds();
                moving = true;
                lastPos = LastPos.LEFT;
                setCurrentAnimation(1,9,8);
                break;
            case "right":
                pos.x += velocity;
                origin.x += velocity;
                updateBounds();
                moving = true;
                lastPos = LastPos.RIGHT;
                setCurrentAnimation(1,11,8);
                break;
            case "down":
                pos.y += velocity;
                origin.y += velocity;
                updateBounds();
                moving = true;
                lastPos = LastPos.DOWN;
                setCurrentAnimation(1,10,8);
                break;
            default:

                break;
        }
    }

    public boolean checkCollision( int colX, int colY ) {
        int x = (int) (collider.getBounds().getX());
        int y = (int) (collider.getBounds().getY());
        int px0 = colX, py0 = colY + 16;
        int px1 = colX + 32, py1 = colY;
        int px2 = colX + 32, py2 = colY + 32;
        int px3 = colX + 64, py3 = colY + 16;

        x /= 64;
        y /= 16;
        x *= 64;
        y *= 16;
        System.out.println("j( " + (x) + ", " + (y) + " )");
        System.out.println();

        //PIXEL PERFECT COLLITIONS
        boolean p0 = (x >= px0 || y >= py0) || (x * 2 >= px0 || y >= py0) || (x >= px0 || y * 2 >= py0) || (x * 2 >= px0 || y * 2 >= py0);
        boolean p1 = (x >= px1 || y >= py1) || (x * 2 >= px1 || y >= py1) || (x >= px1 || y * 2 >= py1) || (x * 2 >= px1 || y * 2 >= py1);
        boolean p2 = (x >= px2 || y >= py2) || (x * 2 >= px2 || y >= py2) || (x >= px2 || y * 2 >= py2) || (x * 2 >= px2 || y * 2 >= py2);
        boolean p3 = (x >= px3 || y >= py3) || (x * 2 >= px3 || y >= py3) || (x >= px3 || y * 2 >= py3) || (x * 2 >= px3 || y * 2 >= py3);
        boolean colFromBottom = (p0 || p2 || p3);
        boolean colFromUp = (p0 || p1 || p3);

        return colFromBottom || colFromUp;
    }
    
    private void updateBounds(){
        collider.updateBound(pos.x, pos.y);
        swordCollider.updateBound(pos.x,pos.y);
    }
    
    public void setVelocity( int velocity ){ this.velocity = velocity; }
    public void setSheet( int i, String path ){ sheet[i] = new SpriteSheet( ImageLoader.loadImage(path) ); }
    public void setScore( int score ){ this.score = score; }

    public Animator getAnimation(){ return animator; }
    public int getVelocity(){ return this.velocity; }
    public Rectangle getBounds(){ return collider.getBounds(); }
    public Rectangle getSBounds(){ return swordCollider.getBounds(); }
    public int getDamage(){ return damage; }
    public int getDefense(){ return defense; }
    public int getScore(){ return score; }


}
