package entity;

import Data.GameObject;

public abstract class Character extends GameObject {
    private int life;
    private String name;

    public Character( String name, int life ){
        this.name = name;
        this.life = life;
    }

    Character(String name, int life, int x, int y){
        super( x, y );
    }

    Character( int life, int x, int y ){
        super( x, y);
    }

    public int getLife() { return life; }
    public String getName() { return name; }
    
    public void setLife( int life ) { this.life = life; }
    public void setName( String name ) { this.name = name; }
}
