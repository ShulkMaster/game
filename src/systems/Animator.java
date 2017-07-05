package systems;

import Data.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animator {
	private SpriteSheet[] sheet = new SpriteSheet[3];
    private boolean firstCall = true;
    private int initialSpritePixels ;
    private int currentSheet = 0;
    private int nextFrame[];

    //SINGLE SHEET ANIMATION
    private BufferedImage[][] sprite;
    private int currentSprite = 0;
    private int pixels, auxPixels;
    private SpriteSheet singleSheet;
    private int width;
    private int height;

    private void calculatePixelsPerFrame(){
        //Esta funcion realiza operaciones matematicas para
        //calcular cada cuantos pixeles es el siguiente frame
        //de la hoja de sprites para poder hacer la animacion
        int aux;
        for( int i = 0; i < sheet.length; i ++ ){
            aux = sheet[i].sheetWidht() / sheet[i].sheetHeight();
            nextFrame[i] = sheet[i].sheetWidht() / aux ;
        }
    }

    private void init(){
        // la cantidad de espacios y nextFrame y
        // SpriteSheet debe ser siempre la misma.
        nextFrame = new int[sheet.length];
        calculatePixelsPerFrame();
    }

    //SINGLE SHEET ANIMATION -------------------------

    private void initSprites(){
        sprite = new BufferedImage[height/pixels][width/pixels];
       for( int i = 0; i < (height/pixels); i++){
           for( int j = 0; j < (width/pixels); j++){
               sprite[i][j] = singleSheet.crop(j*pixels,i*pixels,pixels,pixels);
               System.out.printf("x: %d, y: %d, w: %d, h: %d \n",i*pixels,j*pixels,pixels,pixels);
           }
       }//for
        System.out.println( sprite.length + "," + sprite[0].length );
    }

    public BufferedImage currentAnimation(int start,int height, int limit){
        if( currentSprite >= limit )
            currentSprite = start;
        return sprite[height][currentSprite++];
    }


    public void setAuxAnimation( int height, int pixels, int iterations ){
        int y = 7;
        for( int i = 0; i < iterations; i++) {
            for (int j = 0; j < iterations+2; j++) {
                sprite[height][j] = singleSheet.crop( (j*192),( y*192 ),192,192);
            }
            y++;
            height++;
        }
    }

    public void playAnimation( Graphics g , int height, int limit, int x, int y ){
            for (int j = 0; j/Time.getFps()*5 < limit ; j++) {
                g.drawImage( sprite[height][j/Time.getFps()*5], x-64, y-64, null);
            }
    }

    //---------------------------------------------------

    private int animationCurrentState( int state ){
        //esta funcion devuelve que animacion se esta reproduciendo y tambien la setea
        //al setearla, estamos totalmente seguros que animacion se reproduce
        //y la reproducimos de una vez. el verdadero manejador de que animacion se va a
        //reproducir es el setCurrentSheet(), ya que es lo que evaluamos en nuestro state();
        currentSheet = state;
        if( initialSpritePixels >= sheet[state].sheetWidht() && currentSheet == state )
            initialSpritePixels = 0;
        return initialSpritePixels += nextFrame[state];
        // initialSpritePixels es nuestro iterador entre los frame de la spriteSheet
        // y se debe reiniciar cada vez que llegue al ultimo frame.
    }

    public int state(){
        //esta funcion se utilizada para devolver que animacion se esta reproduciendo.
        switch( currentSheet ){
            case 0:
                return animationCurrentState(0);
            case 1:
                return animationCurrentState(1);
            case 2:
                return animationCurrentState(2);
            default:
                System.out.println( "Error, Animation state out of default animations values");
                return 0;
        }
    }

    public Animator ( SpriteSheet sheet, int width, int height, int pixels ){
        this.singleSheet = sheet;
        this.width = width;
        this.height = height;
        this.pixels = pixels;
        initSprites();
    }

    public Animator( SpriteSheet sheet , int pixels ){
        this.singleSheet = sheet;
        this.pixels = pixels;
    }

    public Animator( SpriteSheet[] sheet ){
        this.sheet = sheet;
        init();
    }

    //SETTERS -------------------------
    public void setCurrentSheet(int currentSheet) { this.currentSheet = currentSheet; }
    public void setPixels( int position ){ initialSpritePixels = position; }
    //---------------------------------

    //GETTERS -------------------------
    public int getCurrentSheet() { return currentSheet; }
    public SpriteSheet getSprites( int i ){ return this.sheet[i]; }
    public SpriteSheet getSheet(){ return this.singleSheet; }
    public BufferedImage[][] getSheets(){ return this.sprite; }

    //---------------------------------
}
