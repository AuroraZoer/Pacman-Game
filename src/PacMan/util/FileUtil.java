package PacMan.util;

import PacMan.model.Level;

import java.io.*;
import java.util.ArrayList;

/**
 * @description File processing function class
 * @ClassName FileUtil.java
 * @author name: Zhao Yiran, UCD number: 21207295
 * @Date 2022-12-2
 */
public class FileUtil {

    public static ArrayList<ArrayList<String>> readMaze(String mazePath){
        ArrayList<ArrayList<String>> mazeList = new ArrayList<>();
        int lineLength = -1;
        try (BufferedReader br = new BufferedReader(new FileReader(mazePath))){
            String line = br.readLine();
            while(line != null){
                if (lineLength == -1){
                    lineLength = line.length();
                } else if (lineLength != line.length()) {
                    throw new wrongMazeException("The length of each line in the maze is not consistent, " +
                            "so the maze is not recognized, please replace the correct maze.");
                }
                ArrayList<String> lineList = new ArrayList<>();
                for (int i = 0; i < line.length(); i++){
                    lineList.add(line.substring(i,i+1));
                }
                mazeList.add(lineList);
                line = br.readLine();
            }
        } catch (IOException | wrongMazeException e){
            throw new RuntimeException(e);
        }
        return mazeList;
    }

    public static ArrayList<Level> readLevel(String fileName) {
        ArrayList<Level> levels = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                String[] parts = line.split(",");
                Level s = new Level(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Double.parseDouble(parts[2]));
                levels.add(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return levels;
    }

}
