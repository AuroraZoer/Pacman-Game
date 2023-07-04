package PacMan.model;

import PacMan.display.PlayerListener;
import PacMan.util.FileUtil;
import ucd.comp2011j.engine.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @description PacMac game function logic implementation part
 * @ClassName PacManGame.java
 * @author name: Zhao Yiran, UCD number: 21207295
 * @Date 2022-12-2
 */
public class PacManGame implements Game {
    public static final int SCREEN_WIDTH = 700; // 50 + 30*20 + 50
    public static final int SCREEN_HEIGHT = 820; // 50 + 30*24 + 50
    public static final int BLOCK_WIDTH = 30;
    public static final int BLOCK_HEIGHT = 30;
    private static final int NO_LEVELS = 14;
    private static final int DOT_SCORE = 10;
    private static final int POWER_SCORE = 50;
    private static final int FRUIT_SCORE = 500;
    private static final int GHOST_SCORE = 200;
    private static Rectangle SCREEN_BOUNDS;
    private final PlayerListener listener;
    private boolean pause = true;
    private int playerLives;
    private int playerScore;
    private Player player;
    private static final ArrayList<Dots> dotsList = new ArrayList<>();
    private static final ArrayList<Ghost> GhostList = new ArrayList<>();
    private int eatGhostNum = 0;
    private static final ArrayList<Wall> wallList = new ArrayList<>();
    private static final ArrayList<EmptySquare> emptySquaresList = new ArrayList<>();
    private static final ArrayList<PowerPellets> powerPelletsList = new ArrayList<>();
    private Fruit fruit;
    private final Timer timer = new Timer();
    private boolean fruitStatus = true;
    private Level[] level;
    private int currentLevel;
    private final String levelPath = "level.csv";
    private ArrayList<Level> levelFile;
    private final String mazePath = "maze.txt";
    private ArrayList<ArrayList<String>> maze;
    public static int MazeBeginX;
    public static int MazeBeginY;
    private int PacmanBeginX;
    private int PacmanBeginY;
    private int GhostBeginX;
    private int GhostBeginY;
    private int stop = 8000;

    public PacManGame(PlayerListener listener){
        this.listener = listener;
    }

    @Override
    public int getPlayerScore() {
        return playerScore;
    }

    @Override
    public int getLives() {
        return playerLives;
    }

    @Override
    public void updateGame() {
        if (!isPaused()) {
            moveGhost();
            movePlayer();
            playerHitGhost();
            eat();
            stop++;
            if (stop == 10000){
                stop = 8000;
                fruitTimer();
            }

        }
    }

    @Override
    public boolean isPaused() {
        return pause;
    }

    @Override
    public void checkForPause() {
        if (listener.hasPressedPause()) {
            pause = !pause;
            listener.resetPause();
        }
    }
    public void loadMaze(){
        maze = FileUtil.readMaze(mazePath);
        wallList.clear();
        dotsList.clear();
        powerPelletsList.clear();
        GhostList.clear();
        MazeBeginX = 50 + (30*20 - maze.get(0).size()*30) / 2;
        MazeBeginY = 50 + (30*24 - maze.size()*30) / 2;
        SCREEN_BOUNDS = new Rectangle(0, 0,
                PacManGame.BLOCK_WIDTH*maze.get(0).size(), PacManGame.BLOCK_HEIGHT*maze.size());

        for (int y = 0; y < maze.size(); y++) {
            for (int x = 0; x < maze.get(y).size(); x++) {
                String block = maze.get(y).get(x);
                switch (block) {
                    case "W" -> wallList.add(new Wall(x, y));
                    case "P" -> {
                        PacmanBeginX = x;
                        PacmanBeginY = y;
                        player = new Player(x, y, this);
                    }
                    case "." -> dotsList.add(new Dots(x, y));
                    case "*" -> powerPelletsList.add(new PowerPellets(x, y));
                    case "F" -> fruit = new Fruit(x, y);
                    case "G" -> {
                        GhostBeginX = x;
                        GhostBeginY = y;
                    }
                    case "-" -> emptySquaresList.add(new EmptySquare(x, y));
                }

            }
        }

        while (GhostList.size() < level[currentLevel].getNumGhosts()){
            GhostList.add(new Ghost(GhostBeginX, GhostBeginY, this, level[currentLevel].getSpeed()));
        }

    }


    @Override
    public void startNewGame() {
        playerLives = 3;
        playerScore = 0;
        try {
            Object[] allLevel = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
            currentLevel = (int) JOptionPane.showInputDialog(null,
                    "Please select the difficulty you want to start the game on",
                    "Choose your Game Level", JOptionPane.INFORMATION_MESSAGE,
                    null, allLevel, 1) - 1;
        } catch (Exception e) {
            currentLevel = 0;
        }
        levelFile = FileUtil.readLevel(levelPath);
        level = new Level[levelFile.size()];
        for (int i = 0; i < levelFile.size(); i++){
            level[i] = levelFile.get(i);
        }
        loadMaze();
        loadLevel();

    }

    public void loadLevel(){
        pause = true;
        for (int i = 0; i < GhostList.size(); i++){
            GhostList.get(i).setMoveStatus(level[currentLevel].getDelay()*i);
        }

    }

    @Override
    public boolean isLevelFinished() {
        return dotsList.size() == 0 && powerPelletsList.size() == 0;
    }

    @Override
    public boolean isPlayerAlive() {
        return player.isAlive();
    }

    @Override
    public void resetDestroyedPlayer() {
        player.setX(PacmanBeginX);
        player.setY(PacmanBeginY);
        player.setDirection();
    }

    @Override
    public void moveToNextLevel() {
        if (currentLevel < 13){
            pause = true;
            currentLevel++;
            loadLevel();
            loadMaze();
        }

    }

    @Override
    public boolean isGameOver() {
        return currentLevel >= NO_LEVELS || playerLives == 0;
    }

    @Override
    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    @Override
    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public Rectangle getScreenBounds() {
        return SCREEN_BOUNDS;
    }

    public int getMazeBeginX() {
        return MazeBeginX;
    }

    public int getMazeBeginY() {
        return MazeBeginY;
    }

    public ArrayList<ArrayList<String>> getMaze(){
        return maze;
    }

    public ArrayList<Wall> getWallList(){
        return wallList;
    }
    public ArrayList<EmptySquare> getEmptySquaresList(){
        return emptySquaresList;
    }

    public ArrayList<Dots> getDotsList(){
        return dotsList;
    }

    public ArrayList<PowerPellets> getPowerPelletsList(){
        return powerPelletsList;
    }

    public Fruit getFruit(){
        return fruit;
    }

    public boolean getFruitStatus(){
        return fruitStatus;
    }

    public Player getPlayer(){
        return player;
    }

    public ArrayList<Ghost> getGhostList (){
        return GhostList;
    }

    public int getCurrentLevel(){
        return currentLevel + 1;
    }

    public void movePlayer(){
        try {
            if (listener.isPressingTop()) {
                player.turnDirection(0);
            } else if (listener.isPressingLeft()) {
                player.turnDirection(1);
            } else if (listener.isPressingBottom()){
                player.turnDirection(2);
            } else if (listener.isPressingRight()) {
                player.turnDirection(3);
            }
            player.move();
        } catch (IndexOutOfBoundsException e){
            System.out.println("Pac-man has reached the edge of the maze. Please change direction.");
        }
    }

    public void moveGhost(){
        for (Ghost ghost: GhostList) {
            ghost.move();
        }
    }

    public void eat(){
        int currentX = player.getX();
        int currentY = player.getY();
        String currentPosition = maze.get(currentY).get(currentX);

        switch (currentPosition) {
            case ".":
                for (int i = 0; i < dotsList.size(); i++) {
                    if (dotsList.get(i).x() == currentX && dotsList.get(i).y() == currentY) {
                        dotsList.remove(i);
                        playerScore += DOT_SCORE;
                    }
                }
                break;
            case "*":
                for (int i = 0; i < powerPelletsList.size(); i++) {
                    if (powerPelletsList.get(i).getX() == currentX && powerPelletsList.get(i).getY() == currentY) {
                        powerPelletsList.remove(i);
                        playerScore += POWER_SCORE;
                        eatGhostNum = 0;
                        player.setPowerStatus();
                    }
                }
                break;
            case "F":
                if (fruitStatus) {
                    playerScore += FRUIT_SCORE;
                    fruit = null;
                    fruitStatus = false;
                }
                break;
        }
    }

    public void fruitTimer(){
        if (fruitStatus && fruit != null){
            fruitStatus = false;
            TimerTask task = new TimerTask(){
                public void run() {
                    fruitStatus = true;
                }
            };
            // Randomly generate the time the fruit appears
            Random r = new Random();
            int delay = r.nextInt(9000, 10000);
            timer.schedule(task, delay);
        }
    }

    public void playerHitGhost(){
        for (Ghost ghost : GhostList) {
            if (player.getX() == ghost.getX() && player.getY() == ghost.getY()) {
                if (player.getPowerStatus()) {
                    ghost.setMoveStatus(level[currentLevel].getDelay());
                    ghost.setX(GhostBeginX);
                    ghost.setY(GhostBeginY);
                    ghost.setDirection();
                    eatGhostNum++;
                    playerScore += eatGhostNum * GHOST_SCORE;
                } else {
                    resetDestroyedPlayer();
                    playerLives -= 1;
                }

            }
        }
    }

}
