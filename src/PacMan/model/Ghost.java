package PacMan.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class Ghost implements Movable{
    private int x, y;
    public static final int width = 24;
    public static final int height = 24;
    private Rectangle hitBox;
    private int direction;  // top 0, left 1, bottom 2, right 3
    private int stop = 0;
    private final double speed;
    private boolean moveStatus = true;
    PacManGame game;

    public Ghost(int x, int y, PacManGame game, double speed){
        this.x = x;
        this.y = y;
        this.game = game;
        this.speed = speed;
        direction = 0;  // Set the initial movement direction based on the maze
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
        direction = 0;
    }

    public boolean checkWall(int x, int y){
        return game.getMaze().get(y).get(x).equals("W");
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

    @Override
    public void move() {
        if (moveStatus){
            stop++;
            if (stop == 40/speed){
                stop = 0;
                int[] nextPosition = nextPos(direction);
                Rectangle newBox = new Rectangle(x + nextPosition[0],y + nextPosition[1],
                        PacManGame.BLOCK_WIDTH, PacManGame.BLOCK_HEIGHT);
                if (game.getScreenBounds().contains(newBox.getBounds())){
                    hitBox = newBox;
                    if (checkWall(nextPosition[0], nextPosition[1])){
                        direction = turnDirection();
                    } else {
                        x = nextPosition[0];
                        y = nextPosition[1];
                    }
                }
            }
        }
    }

    public int turnDirection(){
        int nextDirection = -1;
        ArrayList<Integer> dirList = new ArrayList<>();
        dirList.add(0);
        dirList.add(1);
        dirList.add(2);
        dirList.add(3);
        for (int i = 0; i < dirList.size(); i++){
            int[] nextPos = nextPos(dirList.get(i));
            if (checkWall(nextPos[0],nextPos[1])) {
                dirList.remove(i--);
            }
        }
        ArrayList<Integer> disList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        if (dirList.size() == 1){
            nextDirection = dirList.get(0);
        } else {
            int playerX = game.getPlayer().getX();
            int playerY = game.getPlayer().getY();
            for (Integer integer : dirList) {
                int[] nextPos = nextPos(integer);
                int distance = (int) Math.pow(nextPos[0] - playerX, 2) + (int) Math.pow(nextPos[1] - playerY, 2);
                ArrayList<Integer> disAndDir = new ArrayList<>();
                disAndDir.add(distance);
                disAndDir.add(integer);
                temp.add(disAndDir);
                disList.add(distance);
            }
            for (ArrayList<Integer> integers : temp) {
                if (game.getPlayer().getPowerStatus()){
                    if (Collections.max(disList).equals(integers.get(0))){
                        nextDirection = integers.get(1);
                    }
                } else {
                    if (Collections.min(disList).equals(integers.get(0))){
                        nextDirection = integers.get(1);
                    }
                }
            }
        }

        return nextDirection;
    }

    public boolean getMoveStatus(){
        return moveStatus;
    }

    public void setMoveStatus(int s){
        moveStatus = false;
        TimerTask task = new TimerTask(){
            public void run() {
                moveStatus = true;
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,400*s);
    }



}
