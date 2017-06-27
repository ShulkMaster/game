package systems;

import sun.audio.*;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioManager {
	
	//private static AudioStream music;
	private static AudioStream sound;
	private static Thread MusicPlayer;
	private static ContinuousAudioDataStream loopyLoopytyLoop;
	//private static Clip clip;
	
    public static void playMusic( final String path){
		MusicPlayer = new Thread(){
		    @Override public void run() {
                try {
                    AudioStream music = new AudioStream(new FileInputStream(path)); //ruta incluye src/
                    AudioData data = music.getData();
                    loopyLoopytyLoop = new ContinuousAudioDataStream( data);
                    AudioPlayer.player.start(loopyLoopytyLoop);
                    /*clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            AudioManager.class.getResourceAsStream(path)); // usa ruta que no incluye src/
                    clip.open(inputStream);
                    clip.start();*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
		MusicPlayer.start();
	}

	public static void stopMusic(){
        //clip.stop();
        AudioPlayer.player.stop( loopyLoopytyLoop);

    }
	public static void playSound( String path ){
        try{
	        sound = new AudioStream(new FileInputStream(path));
	        AudioPlayer.player.start(sound);
        }catch(Exception e){
            e.printStackTrace();
        }
	}//func
}
