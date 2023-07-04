package PacMan.display;

import PacMan.model.PacManGame;
import ucd.comp2011j.engine.Score;
import ucd.comp2011j.engine.ScoreKeeper;
import javax.swing.*;
import java.awt.*;

/**
 * @description PacMac Games Hall of Fame interface implementation
 * @ClassName ScoreScreen.java
 * @author name: Zhao Yiran, UCD number: 21207295
 * @Date 2022-12-2
 */
public class ScoreScreen extends JPanel {
    private static final long serialVersionUID = 1616386874546775416L;
    private final ScoreKeeper scoreKeeper;
    public ScoreScreen(ScoreKeeper sc) {
        this.scoreKeeper = sc;
    }

    private void drawString(Graphics g, String text, Rectangle rect, int size) {
        Graphics2D g2d = (Graphics2D) g.create();

        Font font = new Font("Comic Sans MS", Font.BOLD, size);
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2d.setColor(Color.YELLOW);
        g2d.drawString(text, x, y);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, PacManGame.SCREEN_WIDTH, PacManGame.SCREEN_HEIGHT);
        drawString(g, "PacMan Hall of Fame",
                new Rectangle(0, PacManGame.SCREEN_HEIGHT / 20,
                        PacManGame.SCREEN_WIDTH, PacManGame.SCREEN_HEIGHT / 8),48);
        g.setColor(Color.ORANGE);
        Score[] scores = scoreKeeper.getScores();
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        for (int i = 0; i < scores.length; i++) {
            Score score = scores[i];
            g.drawString(score.getName(), 2 * PacManGame.SCREEN_WIDTH / 6, PacManGame.SCREEN_HEIGHT / 4 + i * 40);
            g.drawString("" + score.getScore(), 4 * PacManGame.SCREEN_WIDTH / 6, PacManGame.SCREEN_HEIGHT / 4 + i * 40);
        }
        drawString(g, "Press 'M' to return to the Main Menu",
                new Rectangle(0, 600, PacManGame.SCREEN_WIDTH, 96), 32);
    }

}
