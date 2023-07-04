package PacMan.display;

import PacMan.model.PacManGame;
import javax.swing.*;
import java.awt.*;

/**
 * @description PacMac game controls interface implementation
 * @ClassName MenuScreen.java
 * @author name: Zhao Yiran, UCD number: 21207295
 * @Date 2022-12-2
 */
public class MenuScreen extends JPanel {
    private static final long serialVersionUID = 1616386874546775416L;

    private void drawString(Graphics g, String text, Rectangle rect, int size){
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
        drawString(g, "Welcome to PacMan",
                new Rectangle(PacManGame.SCREEN_WIDTH / 3, 10,
                        PacManGame.SCREEN_WIDTH / 3, PacManGame.SCREEN_WIDTH / 3),50);
        drawString(g, "To play a game press G",
                new Rectangle(PacManGame.SCREEN_WIDTH / 3, PacManGame.SCREEN_HEIGHT / 7,
                        PacManGame.SCREEN_WIDTH / 3, PacManGame.SCREEN_WIDTH / 3),30);
        drawString(g, "To see the controls press Z",
                new Rectangle(PacManGame.SCREEN_WIDTH / 3, 2 * PacManGame.SCREEN_HEIGHT / 7,
                        PacManGame.SCREEN_WIDTH / 3, PacManGame.SCREEN_WIDTH / 3),30);
        drawString(g, "To see the High scores press H",
                new Rectangle(PacManGame.SCREEN_WIDTH / 3, 3 * PacManGame.SCREEN_HEIGHT / 7,
                        PacManGame.SCREEN_WIDTH / 3, PacManGame.SCREEN_WIDTH / 3),30);
        drawString(g, "To exit press X",
                new Rectangle(PacManGame.SCREEN_WIDTH / 3, 4 * PacManGame.SCREEN_HEIGHT / 7,
                        PacManGame.SCREEN_WIDTH / 3, PacManGame.SCREEN_WIDTH / 3),30);
    }

}
