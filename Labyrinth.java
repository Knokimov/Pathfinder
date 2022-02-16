import javax.swing.*;
import java.io.File;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.*;
import java.util.*;

public class Labyrinth{ 
    char grid[][];
    File file;
    // int z;
    int rows, columns;
    ArrayList<Path> solutions;
    JPanel mainframe, solutionFrame;
    JButton[][] components;
    
    Labyrinth(File file) throws FileNotFoundException{
        this.file = file;
        this.solutions = new ArrayList<Path>();
        
        try{
            Scanner scanner = new Scanner(file);
            String firstLine = scanner.nextLine();
            String[] rowsAndColumns = firstLine.split(" ");
            
            this.rows = Integer.parseInt(rowsAndColumns[0]);
            this.columns = Integer.parseInt(rowsAndColumns[1]);
            this.grid = new char[this.rows][this.columns];
            int rowNr = 0;
            
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                char[] row = new char[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    row[i] = line.charAt(i);
                }

                int columnNr = 0;
                
                for(char x: row){
                    if(x == '.') {
                        if(columnNr == 0 || columnNr == columns - 1 || rowNr == 0 || rowNr == rows -1){
                            grid[rowNr][columnNr] =  'X';
                        }
                        else{
                            grid[rowNr][columnNr] = '.';
                        }
                    }
                    else if (x == '#'){
                        grid[rowNr][columnNr] = '#'; 
                    }
                    columnNr++;
                }
                rowNr++;
            }
            scanner.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
