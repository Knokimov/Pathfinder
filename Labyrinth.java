import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Labyrinth{ 
    Square grid[][];
    File file;
    int rows = 1, columns = 1;
    ArrayList<Path> solutions;
    JPanel mainframe, gridPanel;

    public void solve(int row, int column){
        this.solutions = new ArrayList<Path>();
        HashSet<Integer> visited = new HashSet<>();
        Deque<Integer> queue = new LinkedList<>();
        HashMap<Integer, Integer> parents = new HashMap<>();
        int[] iterationTable = {1,0,-1,0,1};
        int start = (row << 16) + column;
        queue.add(start);
        int pathNr = 0;
 
        while(!queue.isEmpty()){ 
            int target = queue.poll();
            for(int i = 0; i < 4; i++){
                int neighbourRow = (target >> 16) + iterationTable[i];
                int neighbourColumn = target - ((target >> 16) << 16) + iterationTable[i+1];
                
                if(neighbourRow >= 0 && neighbourRow < this.rows && neighbourColumn >= 0 &&
                   neighbourColumn < this.columns){
                    int neighbour = (neighbourRow << 16) + neighbourColumn;
                    
                    if(this.grid[neighbourRow][neighbourColumn].equals('.') && !(visited.contains(neighbour))){
                        queue.add(neighbour);
                        visited.add(neighbour);
                        parents.put(neighbour, target);
                    } else if(this.grid[neighbourRow][neighbourColumn].equals('X')){
                        Path path = new Path(this, pathNr);
                        ArrayList<Integer> pathFound = new ArrayList<>();
                        parents.put(neighbour, target);
                        int currentNode = neighbour;
                        
                        while(parents.containsKey(currentNode)){
                            pathFound.add(currentNode >> 16);
                            pathFound.add(currentNode - ((currentNode >> 16) << 16));
                            currentNode = parents.get(currentNode);
                        }
                        path.path = pathFound;
                        this.solutions.add(path);
                    }
                }
            }
            visited.add(target);
        }
    }
    
    Labyrinth(File file) throws FileNotFoundException{
        this.file = file;
        this.solutions = new ArrayList<Path>();
        this.mainframe = new JPanel();
        this.mainframe.setPreferredSize(new Dimension(2000,2000));
        this.gridPanel = new JPanel();
        try{
            Scanner scanner = new Scanner(file);
            String firstLine = scanner.nextLine();
            String[] rowsAndColumns = firstLine.split(" ");
            this.rows = Integer.parseInt(rowsAndColumns[0]);
            this.columns = Integer.parseInt(rowsAndColumns[1]);
            this.grid = new Square[this.rows][this.columns];
            int rowNr = 0;
            this.gridPanel.setLayout(new GridLayout(this.rows,this.columns));
            
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                char[] row = new char[line.length()];
                int columnNr = 0;
                for(int i = 0; i < line.length(); i++) { 
                    if(line.charAt(i) == '.'){
                        if(columnNr == 0 || columnNr == columns - 1 || rowNr == 0 || rowNr == rows -1){
                            grid[rowNr][columnNr] = new Square(this, rowNr, columnNr, 'X');
                        } else{
                            grid[rowNr][columnNr] = new Square(this, rowNr, columnNr, '.');
                        }
                    } else if(line.charAt(i) == '#'){
                            grid[rowNr][columnNr] = new Square(this, rowNr, columnNr, '#');
                    }
                    gridPanel.add(grid[rowNr][columnNr].button);
                    columnNr++;
                }
                rowNr++;
            }
            scanner.close();
            mainframe.add(gridPanel);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
