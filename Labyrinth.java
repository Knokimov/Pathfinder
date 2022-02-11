import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Labyrinth {
    public char[][] grid;
    File file;
    int rows;
    int columns;
    ArrayList<ArrayList<Integer>> solutions;

    // public void addSolution(ArrayList<Integer> solution){
    //     this.solutions.add(solution);
    // }


    Labyrinth(File file) throws FileNotFoundException{
        this.file = file;
        this.solutions = new ArrayList<ArrayList<Integer>>();
        
                    System.out.println("Char");
        try{
            Scanner scanner = new Scanner(file);
            String firstLine = scanner.nextLine();
            String[] rowsAndColumns = firstLine.split(" ");
            
            this.rows = Integer.parseInt(rowsAndColumns[0]);
            this.columns = Integer.parseInt(rowsAndColumns[1]);
            this.grid = new char[this.rows][this.columns];
            int rowNr = 0;
            
            System.out.println("row and col" + this.rows + " " + this.columns);
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
