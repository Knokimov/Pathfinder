import javax.swing.*;
import java.io.File;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.*;

// public class Labyrinth implements ActionListener{ 
public class Labyrinth{ 
    // public char[][] grid;
    // ArrayList<ArrayList<Square>> grid;
    char grid[][];
    File file;
    int z;
    int rows;
    int columns;
    ArrayList<Path> solutions;
    JPanel mainframe, solutionFrame;
    JButton[][] components;
    
    void findSolutions(int row, int column){
        // ArrayList<Integer> test = new ArrayList<Integer>();
        // if(grid[row][column].type == '.'){

           Path solution = new Path(mainframe, components, 0, this);
           ArrayList<Integer> test = new ArrayList<Integer>();
           test.add(2);
           test.add(2);
           test.add(1);
           test.add(1);
           test.add(0);
           test.add(0);
           solution.path = test;

           solutions.add(solution);

            // if(grid[row][column].type)
                
        // if(this.grid[row][column] == '.'){
        //     // grid[row][column] = '0';
        //     if(row - 1 >= 0 && grid[row-1][column] == '1'){
        //         transform(grid, row-1, column);
        //     }
        //     if(row + 1 < grid.size() && grid[row+1][column] == '1'){
        //         transform(grid, row+1, column);
        //     }
        //     if(column - 1 >= 0 && grid[row][column-1] == '1'){
        //         transform(grid, row, column-1);
        //     }
        //     if(column + 1 < grid[0].size() && grid[row][column+1] == '1'){
        //         transform(grid, row, column+1);
        //         }
        //     }
        // }
    }

    Labyrinth(File file) throws FileNotFoundException{
        this.file = file;
        this.solutions = new ArrayList<Path>();
        
        try{
            Scanner scanner = new Scanner(file);
            String firstLine = scanner.nextLine();
            String[] rowsAndColumns = firstLine.split(" ");
            
            this.rows = Integer.parseInt(rowsAndColumns[0]);
            this.columns = Integer.parseInt(rowsAndColumns[1]);
            // this.grid = new char[this.rows][this.columns];
            this.grid = new char[this.rows][this.columns];
            int rowNr = 0;
            
            // System.out.println("row and col" + this.rows + " " + this.columns);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                char[] row = new char[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    row[i] = line.charAt(i);
                }

                int columnNr = 0;
                
                for(char x: row){
                    // System.out.println("Char" + x);
                    if(x == '.') {
                        if(columnNr == 0 || columnNr == columns - 1 || rowNr == 0 || rowNr == rows -1){
                            grid[rowNr][columnNr] =  'X';
                            // grid[rowNr][columnNr] =  new Square(this, rowNr, columnNr, 'X');
                        }
                        else{
                            // grid[rowNr][columnNr] = new Square(this, rowNr, columnNr, '.');
                            grid[rowNr][columnNr] = '.';
                        }
                    }
                    else if (x == '#'){
                        // grid[rowNr][columnNr] = new Square(this, rowNr, columnNr, '#');;
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

    // public String toString(){
    //     String result = "";   
    //     for (Square[] x: grid){
    //         result = result + "\n";
    //         for(Square y: x){
    //             result = result + y;
    //         }
    //     }
    //     return result;
    // }
}
