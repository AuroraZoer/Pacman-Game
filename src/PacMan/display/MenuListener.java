package PacMan.display;

import ucd.comp2011j.engine.MenuCommands;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @description Listener for the menu interface
 * @ClassName MenuListener.java
 * @author name: Zhao Yiran, UCD number: 21207295
 * @Date 2022-12-2
 */
public class MenuListener implements MenuCommands, KeyListener {
    private boolean about;
    private boolean exit;
    private boolean high;
    private boolean menu;
    private boolean newGame;
    @Override
    public boolean hasPressedNewGame() {
        return newGame;
    }

    @Override
    public boolean hasPressedAboutScreen() {
        return about;
    }

    @Override
    public boolean hasPressedHighScoreScreen() {
        return high;
    }

    @Override
    public boolean hasPressedMenu() {
        return menu;
    }

    @Override
    public boolean hasPressedExit() {
        return exit;
    }

    @Override
    public void resetKeyPresses() {
        menu = false;
        about = false;
        newGame = false;
        high = false;
        exit = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'Z' || e.getKeyChar() == 'z') {
            about = true;
        } else if (e.getKeyChar() == 'X' || e.getKeyChar() == 'x') {
            exit = true;
        } else if (e.getKeyChar() == 'H' || e.getKeyChar() == 'h') {
            high = true;
        } else if (e.getKeyChar() == 'M' || e.getKeyChar() == 'm') {
            menu = true;
        } else if (e.getKeyChar() == 'G' || e.getKeyChar() == 'g') {
            newGame = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
