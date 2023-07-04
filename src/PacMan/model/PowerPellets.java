package PacMan.model;

import java.awt.*;

public class PowerPellets {
    private final int x, y;
    public static final int width = 12;
    public static final int height = 12;


    public PowerPellets(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
