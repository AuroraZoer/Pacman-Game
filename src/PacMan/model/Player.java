package PacMan.model;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Player implements Movable{
    private int x, y;
    public static final int width = 25;
    public static final int height = 25;
    private Rectangle hitBox;
    private final boolean alive = true;
    private boolean powerStatus = false;
    private int direction = -1;  // top 0, left 1, bottom 2, right 3
    private int stop = 0;
    PacManGame game;

    public Player(int x, int y, PacManGame game){
        this.x = x;
        this.y = y;
        this.game = game;
        hitBox = new Rectangle(x,y,PacManGame.BLOCK_WIDTH,PacManGame.BLOCK_HEIGHT);
    }

    public int getX() {
        return x;
    }
    public void setX(int newX){
        this.x = newX;
    }
    public int getY() {
        return y;
    }
    public void setY(int newY){
        this.y = newY;
    }
    public void setDirection(){
        direction = -1;
    }
    public int[] nextPos(int dir){
        int nextX = x;
        int nextY = y;
        if (dir == 1) {
            nextX -= 1;
        } else if (dir == 3) {
            nextX += 1;
        } else if (dir == 0) {
            nextY -= 1;
        } else if (dir == 2){
            nextY += 1;
        }
        return new int[]{nextX,nextY};
    }

    public boolean checkWall(int x, int y){
        return game.getMaze().get(y).get(x).equals("W");
    }

    public boolean checkEmptySquare(int x, int y){
        return game.getMaze().get(y).get(x).equals("-");
    }

    @Override
    public void move() {
        stop++;
        if (stop == 10){
            stop = 0;
            int[] nextPosition = nextPos(direction);
            Rectangle newBox = new Rectangle(x + nextPosition[0],y + nextPosition[1],
                    PacManGame.BLOCK_WIDTH, PacManGame.BLOCK_HEIGHT);
            if (game.getScreenBounds().contains(newBox.getBounds())){
                hitBox = newBox;
                if (checkWall(nextPosition[0], nextPosition[1]) || checkEmptySquare(nextPosition[0], nextPosition[1])){
                } else {
                    x = nextPosition[0];
                    y = nextPosition[1];
                }
            }
        }
    }

    public void turnDirection(int dir){
        int[] nextPosition = nextPos(dir);
        if (checkWall(nextPosition[0], nextPosition[1]) || checkEmptySquare(nextPosition[0], nextPosition[1])){
        } else {
            direction = dir;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean getPowerStatus() {
        return powerStatus;
    }

    public void setPowerStatus(){
        powerStatus = true;
        TimerTask task = new TimerTask(){
            public void run() {
                powerStatus = false;
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,8000);
    }

}
