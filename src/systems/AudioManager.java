package systems;

import sun.audio.*;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class AudioManager {
	
	//private static AudioStream music;
    private static AudioPlayer MusicPlayer = AudioPlayer.player;
	private static AudioStream BGM;
	private static AudioStream sound;
    private static AudioData data;
    private static ContinuousAudioDataStream loop;

	private static Thread MusicThread;
	private static Clip clip;
	private static Clip effect;
	private static AudioInputStream inputStream;
	private static AudioInputStream fx;


    public static void loadSong( final String path){
        //AudioStream music = null; //ruta incluye src/
        /*try {
            BGM = new AudioStream(new FileInputStream(path));
            data = BGM.getData();
            loop = new ContinuousAudioDataStream( data );
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        onplay = path;
        try {
            clip = AudioSystem.getClip();
            inputStream = AudioSystem.getAudioInputStream( AudioManager.class.getResourceAsStream(path));
            clip.open(inputStream);
        } catch ( Exception e) {
            e.printStackTrace();
        }
                // usa ruta que no incluye src/
    }

    public static void loadEffect( final String path){
        try {
            effect = AudioSystem.getClip();
            fx = AudioSystem.getAudioInputStream( AudioManager.class.getResourceAsStream(path));
            effect.open(fx);
        } catch ( Exception e) {
            e.printStackTrace();
        }
        // usa ruta que no incluye src/
    }

    public static void playMusic(){
		MusicThread = new Thread(){
		    @Override public void run() {
                try {
                    //MusicPlayer.player.start( loop );
                    clip.start();
                    clip.loop(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
		MusicThread.start();
	}

	public static void stopMusic(){
        clip.stop();
        //MusicPlayer.player.stop( loop );

    }
	public static void playSound( ){
        try{
            effect.start();
	        //sound = new AudioStream(new FileInputStream(path));
	        //AudioPlayer.player.start(sound);
        }catch(Exception e){
            e.printStackTrace();
        }
	}//func
}
