package Data;

import java.awt.*;

import StateMachine.GameStateManager;
import systems.ListenKeys;
import entity.Player;
import entity.Enemy;

import javax.swing.*;

import maps.GameMap;
import GUI.GameFrame;

public class CurrentData {	
    public static GameStateManager state;
    public static Player jugador;
    public static Enemy[] enemigo;
    public static ListenKeys lKey;
    public static GameMap lvl;
    public static GameFrame frame;
    public static JPanel menuPanel;
    public static JPanel panel;
    public static JPanel gamePanel;
    public static JPanel scorePanel;
    public static Canvas canvas;
    public static CardLayout layout;
    public static final String menu = "menu";
    public static final String game = "game";
    public static final String score = "score";

    public static void initCanvas(){
        layout.show(panel, game);
        frame.revalidate();
    }
}
