package GUI;

import Data.CurrentData;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

    private Canvas canvas;
    private JPanel panel;
    private JPanel gamePanel;
    private JPanel menuPanel;
    private JPanel scorePanel;
    private JPanel winPanel;
    private JPanel gameOverPanel;
    private JPanel pausePanel;
    private CardLayout cLayout;

    private JPanel panel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));
        return panel;
    }

    public void init(int width, int height) {
        cLayout = new CardLayout();

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        panel = panel(width, height);
        panel.setLayout(cLayout);

        gamePanel = panel(width, height);
        menuPanel = panel(width, height);
        scorePanel = panel(width, height);
        gameOverPanel = panel(width, height);
        winPanel = panel(width, height);
        pausePanel = panel(width, height);

        gamePanel.add(canvas);
        panel.add(menuPanel, CurrentData.menu);
        panel.add(gamePanel, CurrentData.game);
        panel.add(scorePanel, CurrentData.score);
        panel.add(gameOverPanel, CurrentData.gameOver);
        panel.add(pausePanel, CurrentData.pause);
        panel.add(winPanel, CurrentData.win);

        CurrentData.layout = this.cLayout;
        CurrentData.canvas = this.canvas;
        CurrentData.panel = this.panel;
        CurrentData.menuPanel = this.menuPanel;
        CurrentData.gamePanel = this.gamePanel;
        CurrentData.pausepanel = this.pausePanel;
        CurrentData.scorePanel = this.scorePanel;
        CurrentData.GameOverPanel = this.gameOverPanel;
        CurrentData.frame = this;

        this.setContentPane(panel);
        this.setTitle("The Island V1.0");
        this.setSize(width, height);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.black);
        this.setIconImage(getIconImage());
    }

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public Image getIconImage() {
        Image retvalue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/image/calcaca.png"));
        return retvalue;
    }
}
