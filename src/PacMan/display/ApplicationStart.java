package PacMan.display;

import PacMan.model.PacManGame;
import ucd.comp2011j.engine.GameManager;
import ucd.comp2011j.engine.ScoreKeeper;
import javax.swing.*;

/**
 * @description PacMac game start entry
 * @ClassName ApplicationStart.java
 * @author name: Zhao Yiran, UCD number: 21207295
 * @Date 2022-12-2
 */
public class ApplicationStart {
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame();
        mainWindow.setSize(PacManGame.SCREEN_WIDTH, PacManGame.SCREEN_HEIGHT);
        mainWindow.setResizable(false);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setTitle("PAC-MAN Game");
        mainWindow.setLocationRelativeTo(null);

        PlayerListener playerListener = new PlayerListener();
        mainWindow.addKeyListener(playerListener);
        MenuListener menuListener = new MenuListener();
        mainWindow.addKeyListener(menuListener);
        PacManGame game = new PacManGame(playerListener);
        MenuScreen menuScreen = new MenuScreen();
        GameScreen gameScreen = new GameScreen(game);
        ScoreKeeper scoreKeeper = new ScoreKeeper("scores.txt");
        GameManager mmm = new GameManager(game, mainWindow, menuListener, menuScreen, new AboutScreen(), new ScoreScreen(scoreKeeper), gameScreen, scoreKeeper);
        mainWindow.setVisible(true);
        mmm.run();
    }
}
