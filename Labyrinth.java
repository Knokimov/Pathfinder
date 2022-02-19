import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Labyrinth{ 
    File file;
    Square grid[][];
    int rows = 1, columns = 1;
    ArrayList<Path> solutions;
    JPanel mainframe, gridPanel;

    public void solve(int row, int column){
        this.solutions = new ArrayList<Path>();
        HashSet<Integer> visited = new HashSet<>();
        Deque<Integer> queue = new LinkedList<>();
        HashMap<Integer, Integer> parentTree = new HashMap<>();
        int[] iterationTable = {1,0,-1,0,1};
        int start = (row << 16) + column;
        queue.add(start);
 
        while(!queue.isEmpty()){ 
            int target = queue.poll();
            for(int i = 0; i < 4; i++){
                int neighbourRow = (target >> 16) + iterationTable[i];
                int neighbourColumn = target - ((target >> 16) << 16) + iterationTable[i+1];
                if(neighbourRow >= 0 && neighbourRow < this.rows && neighbourColumn >= 0 &&
                   neighbourColumn < this.columns){
                    int neighbour = (neighbourRow << 16) + neighbourColumn;
                    if(this.grid[neighbourRow][neighbourColumn].sign == '.' && !(visited.contains(neighbour))){
                        queue.add(neighbour);
                        visited.add(neighbour);
                        parentTree.put(neighbour, target);
                    } else if(this.grid[neighbourRow][neighbourColumn].sign == 'X' && !(visited.contains(neighbour))){
                        ArrayList<Integer> pathFound = new ArrayList<>();
                        pathFound.add(start >> 16);
                        pathFound.add(start - ((start >> 16) << 16));
                        parentTree.put(neighbour, target);
                        int currentNode = neighbour;
                        
                        while(parentTree.containsKey(currentNode)){
                            pathFound.add(currentNode >> 16);
                            pathFound.add(currentNode - ((currentNode >> 16) << 16));
                            currentNode = parentTree.get(currentNode);
                        }
                        Path path = new Path(this, pathFound);
                        this.solutions.add(path);
                    }
                }
            }
            visited.add(target);
        }
    }

    public void reset(){
        for(Square[] x: this.grid){
            for(Square y: x){
                if(y.button.getBackground() != Color.BLACK){
                    y.button.setBackground(Color.WHITE);
                }
            }
        }
    }
    
    Labyrinth(File file) throws FileNotFoundException{
        this.file = file;
        this.gridPanel = new JPanel();
        this.solutions = new ArrayList<Path>();
        this.mainframe = new JPanel();
        this.mainframe.setPreferredSize(new Dimension(2000,2000));
        try{
            Scanner scanner = new Scanner(file);
            String firstLine = scanner.nextLine();
            String[] rowsAndColumns = firstLine.split(" ");
            this.rows = Integer.parseInt(rowsAndColumns[0]);
            this.columns = Integer.parseInt(rowsAndColumns[1]);
            this.grid = new Square[this.rows][this.columns];
            this.gridPanel.setLayout(new GridLayout(this.rows,this.columns));
            int rowNr = 0;
            
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                char[] row = new char[line.length()];
                int columnNr = 0;
                for(int i = 0; i < line.length(); i++){ 
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
