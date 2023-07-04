package PacMan.display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @description Listener for the player object
 * @ClassName PlayerListener.java
 * @author name: Zhao Yiran, UCD number: 21207295
 * @Date 2022-12-2
 */
public class PlayerListener implements KeyListener {
    private boolean left;
    private boolean right;
    private boolean top;
    private boolean bottom;
    private boolean pause;

    public boolean isPressingLeft() {
        return left;
    }

    public boolean isPressingRight() {
        return right;
    }

    public boolean isPressingTop() {
        return top;
    }

    public boolean isPressingBottom() {
        return bottom;
    }
    public boolean hasPressedPause() {
        return pause;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'P' || e.getKeyChar() == 'p') {
            pause = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            top = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            bottom = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            top = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            bottom = false;
        }
    }

    public void resetPause() {
        pause = false;
    }

}
