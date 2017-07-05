package systems;

import StateMachine.GameStateManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import Data.CurrentData;

public class MasterAudio{
    private String musicademenu;
    private String musicadeworld1;
    private String musicadepausa;
    private Thread machine;
    private Thread MUSIC;
    private boolean playing;
    private GameStateManager statetomusic;

    public MasterAudio() {
        playing = true;
        statetomusic = CurrentData.state;
        musicademenu = "/Resources/Music/menu.wav";
        musicadeworld1 = "/Resources/Music/mundo1.wav";
        musicadepausa = "/Resources/Music/ambient.wav";
        machine = new Thread() {
            @Override
            public void run() {
                //while (playing) {
                    musicselect();
                    /*try {
                        Thread.sleep(750);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MasterAudio.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                //}
            }
        };
        machine.start();
       

        //systems.AudioManager.stopMusic();

        System.out.println("hoal");

    }
    public void musicselect() {
        if (statetomusic.getCurrentState() == statetomusic.getMenu()) {
            AudioManager.loadSong(musicademenu);
            AudioManager.playMusic();

        }
        if (statetomusic.getCurrentState() == statetomusic.getWorld()) {
            AudioManager.loadSong(musicadeworld1);
            AudioManager.playMusic();

        }
        if (statetomusic.getCurrentState() == statetomusic.getPause()) {
            AudioManager.loadSong(musicadepausa);
            AudioManager.playMusic();

        }

    }
    
    public void notoverloop(String papa){
        
    
    
    
    }
    
public void pararMusica(){
    playing = false;

}
       
}