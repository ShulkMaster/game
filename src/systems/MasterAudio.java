package systems;

import StateMachine.GameStateManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import Data.CurrentData;

public class MasterAudio{
    private String musicademenu;
    private String musicadeworld1;
    private Thread machine;
    private Thread MUSIC;
    private boolean playing;
    private GameStateManager statetomusic;

    public MasterAudio() {
        playing = true;
        musicademenu = "/Resources/Music/menu.wav";
        musicadeworld1 = "/Resources/Music/mundo1.wav";
        statetomusic = CurrentData.state;
        machine = new Thread() {
            @Override
            public void run() {
                while (playing) {
                    statetomusic = CurrentData.state;
                    musicselect();
                    try {
                        Thread.sleep(750);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MasterAudio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        machine.start();
       

        //systems.AudioManager.stopMusic();

        System.out.println("hoal");

    }
    public void musicselect(){
        if(CurrentData.state == statetomusic.getMenu()){
        AudioManager.loadSong(musicademenu);
        AudioManager.playMusic();
        
        }
                if(CurrentData.state == statetomusic.getWorld() ){
        AudioManager.loadSong(musicadeworld1);
        AudioManager.playMusic();
        
        }
    
    
    }
public void pararMusica(){
    playing = false;

}
       
}