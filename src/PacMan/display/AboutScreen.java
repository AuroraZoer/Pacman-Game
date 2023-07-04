package PacMan.display;

import PacMan.model.PacManGame;
import javax.swing.*;
import java.awt.*;

/**
 * @description PacMac game controls interface implementation
 * @ClassName AboutScreen.java
 * @author name: Zhao Yiran, UCD number: 21207295
 * @Date 2022-12-2
 */
public class AboutScreen extends JPanel {
    private static final long serialVersionUID = -1264717778772722118L;
    public AboutScreen() {
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

    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, PacManGame.SCREEN_WIDTH, PacManGame.SCREEN_HEIGHT);
        g.setColor(Color.YELLOW);
        drawString(g, "PacMan Controls",
                new Rectangle(0, PacManGame.SCREEN_HEIGHT / 20,
                        PacManGame.SCREEN_WIDTH, PacManGame.SCREEN_HEIGHT / 8),48);
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        g.drawString("Move Left", PacManGame.SCREEN_WIDTH / 6, 250);
        g.drawString("left arrow", 4 * PacManGame.SCREEN_WIDTH / 6, 250);
        g.drawString("Move Right", PacManGame.SCREEN_WIDTH / 6, 250 + 50);
        g.drawString("right arrow", 4 * PacManGame.SCREEN_WIDTH / 6, 250 + 50);
        g.drawString("Move Up", PacManGame.SCREEN_WIDTH / 6, 250 + 2 * 50);
        g.drawString("up arrow", 4 * PacManGame.SCREEN_WIDTH / 6, 250 + 2 * 50);
        g.drawString("Move Down", PacManGame.SCREEN_WIDTH / 6, 250 + 3 * 50);
        g.drawString("down arrow", 4 * PacManGame.SCREEN_WIDTH / 6, 250 + 3 * 50);
        g.drawString("Play/Pause", PacManGame.SCREEN_WIDTH / 6, 250 + 4 * 50);
        g.drawString("p", 4 * PacManGame.SCREEN_WIDTH / 6, 250 + 4 * 50);
        drawString(g, "Press 'M' to return to the Main Menu",
                new Rectangle(0, 600, PacManGame.SCREEN_WIDTH, 96), 32);
    }
}
