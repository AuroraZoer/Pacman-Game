package PacMan.util;

/**
 * Read the maze error class
 * @description Throw this exception if a maze error is read
 * @ClassName wrongMazeException.java
 * @author name: Zhao Yiran, UCD number: 21207295
 * @Date 2022-12-2
 */
public class wrongMazeException extends Exception{
    public wrongMazeException(String errorMessage) {
        super(errorMessage);
    }
}
