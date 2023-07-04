package PacMan.display;

import PacMan.model.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @description PacMac game main game interface implementation
 * @ClassName GameScreen.java
 * @author name: Zhao Yiran, UCD number: 21207295
 * @Date 2022-12-2
 */
public class GameScreen extends JPanel {
    private static final long serialVersionUID = -8282302849760730222L;
    private final PacManGame game;
    public GameScreen(PacManGame game) {
        this.game = game;
    }

    public void drawFruit(Graphics2D gc, Fruit f){
        if (game.getFruitStatus()){
            int x = f.x()*PacManGame.BLOCK_WIDTH;
            int y = f.y()*PacManGame.BLOCK_HEIGHT;
            int x1 = PacManGame.BLOCK_WIDTH/2;
            int y1 = PacManGame.BLOCK_HEIGHT/5;
            int x2 = PacManGame.BLOCK_WIDTH/5;
            int y2 = 4*PacManGame.BLOCK_HEIGHT/5;
            int x3 = 4*PacManGame.BLOCK_WIDTH/5;
            int[] x_coords = new int[]{x1,x2,x3,x1};
            int[] y_coords = new int[]{y1,y2, y2,y1};
            int[] x_adjusted = new int[x_coords.length];
            int[] y_adjusted = new int[y_coords.length];
            for (int i = 0; i < x_coords.length; i++) {
                x_adjusted[i] = x + x_coords[i] + game.getMazeBeginX();
                y_adjusted[i] = y + y_coords[i] + game.getMazeBeginY();
            }
            Polygon pg = new Polygon(x_adjusted, y_adjusted, x_adjusted.length);
            gc.setColor(Color.GREEN);
            gc.fillPolygon(pg);
        }
    }

    public void drawDots(Graphics2D gc, ArrayList<Dots> d){
        gc.setColor(Color.WHITE);
        for (Dots dots : d) {
            gc.fillOval(game.getMazeBeginX() + dots.x() * PacManGame.BLOCK_WIDTH + (PacManGame.BLOCK_WIDTH - Dots.width) / 2,
                    game.getMazeBeginY() + dots.y() * PacManGame.BLOCK_HEIGHT + (PacManGame.BLOCK_HEIGHT - Dots.height) / 2,
                    Dots.width, Dots.height);
        }
    }

    public void drawPowerPellets(Graphics2D gc, ArrayList<PowerPellets> pp){
        gc.setColor(Color.WHITE);
        for (PowerPellets powerPellets : pp) {
            gc.fillOval(game.getMazeBeginX() + powerPellets.getX() * PacManGame.BLOCK_WIDTH + (PacManGame.BLOCK_WIDTH - PowerPellets.width) / 2,
                    game.getMazeBeginY() + powerPellets.getY() * PacManGame.BLOCK_HEIGHT + (PacManGame.BLOCK_HEIGHT - PowerPellets.height) / 2,
                    PowerPellets.width, PowerPellets.height);
        }
    }

    public void drawWall(Graphics2D gc, ArrayList<Wall> w){
        gc.setColor(Color.BLUE);
        for (Wall wall : w) {
            gc.fillRect(game.getMazeBeginX() + wall.x() * PacManGame.BLOCK_WIDTH, game.getMazeBeginY() + wall.y() * PacManGame.BLOCK_HEIGHT,
                    PacManGame.BLOCK_WIDTH, PacManGame.BLOCK_HEIGHT);
        }
    }

    public void drawEmptySquare(Graphics2D gc, ArrayList<EmptySquare> es){
        gc.setColor(Color.GRAY);
        for (EmptySquare e : es) {
            gc.fillRect(game.getMazeBeginX() + e.x() * PacManGame.BLOCK_WIDTH, game.getMazeBeginY() + e.y() * PacManGame.BLOCK_HEIGHT,
                    PacManGame.BLOCK_WIDTH, PacManGame.BLOCK_HEIGHT);
        }
    }

    public void drawPacMan(Graphics2D gc, Player pc){
        gc.setColor(Color.YELLOW);
        gc.fillOval(game.getMazeBeginX()+pc.getX()*PacManGame.BLOCK_WIDTH+(PacManGame.BLOCK_WIDTH - Player.width)/2,
                game.getMazeBeginY()+pc.getY()*PacManGame.BLOCK_HEIGHT+(PacManGame.BLOCK_HEIGHT - Player.height)/2,
                Player.width, Player.height);
    }

    public void drawGhost(Graphics2D gc, ArrayList<Ghost> g, Player pc){
        Color[] colors = new Color[4];
        colors[0] = Color.RED;
        colors[1] = Color.PINK;
        colors[2] = Color.CYAN;
        colors[3] = Color.ORANGE;

        for (int i = 0; i < g.size(); i++) {
            if (pc.getPowerStatus()){
                gc.setColor(Color.BLUE);
            } else {
                gc.setColor(colors[i]);
            }
            gc.fillOval(game.getMazeBeginX()+g.get(i).getX()*PacManGame.BLOCK_WIDTH+(PacManGame.BLOCK_WIDTH - Ghost.width)/2,
                    game.getMazeBeginY()+g.get(i).getY()*PacManGame.BLOCK_HEIGHT+(PacManGame.BLOCK_HEIGHT - Ghost.height)/2,
                    Ghost.width, Ghost.height);
        }

    }

    protected void paintComponent(Graphics g){
        if (game != null){
            Graphics2D g2 = (Graphics2D) g;
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, PacManGame.SCREEN_WIDTH, PacManGame.SCREEN_HEIGHT);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            g.drawString("Level: "+ game.getCurrentLevel(), PacManGame.SCREEN_WIDTH / 7, 30);
            g.drawString("Lives: " + game.getLives(), 3 * PacManGame.SCREEN_WIDTH / 7, 30);
            g.drawString("Score: " + game.getPlayerScore(), 5 * PacManGame.SCREEN_WIDTH / 7, 30);

            drawWall(g2,game.getWallList());
            drawEmptySquare(g2,game.getEmptySquaresList());
            drawDots(g2,game.getDotsList());
            drawPowerPellets(g2,game.getPowerPelletsList());
            if (game.getFruit() != null){
                drawFruit(g2,game.getFruit());
            }
            drawGhost(g2,game.getGhostList(),game.getPlayer());
            drawPacMan(g2,game.getPlayer());

            if (game.isPaused() && !game.isGameOver()) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
                g.drawString("Press 'p' to continue", PacManGame.SCREEN_WIDTH / 2, PacManGame.SCREEN_HEIGHT / 2);
            } else if (game.isGameOver()) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
                g.drawString("Game over", PacManGame.SCREEN_WIDTH / 2, PacManGame.SCREEN_HEIGHT / 2);
            }
        }
    }

}
