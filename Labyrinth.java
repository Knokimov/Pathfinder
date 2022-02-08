import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Labyrinth {
    public char[][] grid;
    File file;
    int rows;
    int columns;
    ArrayList<ArrayList<int>> solutions;

    public void addSolution(ArrayList<int> solution){
        this.solutions.add(solution);
    }


    Labyrint(File fil) throws FileNotFoundException{
        this.file = file;
        this.solutions = new ArrayList<ArrayList<int>>();
        
        try{
            Scanner scanner = new Scanner(file);
            String firstLine = scanner.nextLine();
            String[] rowsAndColumns = firstLine.split(" ");
            
            this.rows = Integer.parseInt(rowsAndColumns[0]);
            this.columns = Integer.parseInt(rowsAndColumns[1]);
            this.grid = new char[this.rows][this.columns];
            int row = 0;
            
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                char[] row = line.split("");
                int column = 0;
                
                for(char x: row){
                    if(x.startsWith(".")) {
                        if(column == 0 || column == columns - 1 || row == 0 || row == rows -1){
                            grid[row][column] =  'X';
                        }
                        else{
                            grid[row][column] = '.';
                        }
                    }
                    else if (x.startsWith("#")){
                        grid[row][column] = '#'; 
                    }
                    column++;
                }
                row++;
            }
            scanner.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public String toString(){
        String result = "";   
        for (char[] x: grid){
            result = result + "\n";
            for(char y: x){
                result = result + y;
            }
        }
        return result;
    }
}
