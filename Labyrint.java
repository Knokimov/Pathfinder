import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Labyrint {
    public Rute[][] ruteArray;
    File fil;
    int rows;
    int columns;
    ArrayList<ArrayList<Tuppel>> utveier;

    // Setter nabo til SortRute hvis naboen ikke eksisterer i den retningen
    public void naboer(Rute x){
        // Nabo til venstre
        if(x.column == 0){
            x.naboer[0] = new SortRute(-1,-1, this);
        }
        else{
            x.naboer[0] = this.ruteArray[x.row][x.column-1];
        }
        // Nabo til høyre
        if(x.column == this.columns - 1){
            x.naboer[1] = new SortRute(-1,-1, this);
        }
        else{
            x.naboer[1] = this.ruteArray[x.row][x.column+1];
        }
        // Nabo over
        if(x.row == 0){
            x.naboer[2] = new SortRute(-1,-1, this);
        }
        else{
            x.naboer[2] = this.ruteArray[x.row-1][x.column];
        }
        // Nabo under
        if(x.row == this.rows - 1){
            x.naboer[3] = new SortRute(-1,-1, this);
        }
        else{
            x.naboer[3] = this.ruteArray[x.row+1][x.column];
        }
    }

    public void settNaboer(){
        for (Rute[] x: ruteArray){
            for(Rute y: x){
                naboer(y);
            }
        }
    }

    public void leggTilRute(ArrayList<Tuppel> rute){
        this.utveier.add(rute);
    }

    public ArrayList<ArrayList<Tuppel>> finnUtveiFra(int column, int row){
        this.utveier.clear();
        ruteArray[row][column].finnUtvei();
        return this.utveier;
    }

    Labyrint(File fil) throws FileNotFoundException{
        this.fil = fil;
        this.utveier = new ArrayList<ArrayList<Tuppel>>();
        
        try{
            Scanner leser = new Scanner(fil);
            String firstLine = leser.nextLine();
            String[] rowsAndColumns = firstLine.split(" ");
            
            this.rows = Integer.parseInt(rowsAndColumns[0]);
            this.columns = Integer.parseInt(rowsAndColumns[1]);
            this.ruteArray = new Rute[this.rows][this.columns];
            int row = 0;
            
            while(leser.hasNextLine()) {
                String rad = leser.nextLine();
                String[] ruter = rad.split("");
                int column = 0;
                
                for(String x: ruter){
                    if(x.startsWith(".")) {
                        // Hvis ruten er på kanten så er det en Aapning
                        if(column == 0 || column == columns - 1 || row == 0 || row == rows -1){
                            ruteArray[row][column] = new Aapning(row, column, this);   
                        }
                        else{
                            ruteArray[row][column] = new HvitRute(row, column, this);
                        }
                    }
                    else if (x.startsWith("#")){
                        ruteArray[row][column] = new SortRute(row,column,this);
                    }
                    column++;
                }
                row++;
            }
            leser.close();
            this.settNaboer();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public String toString(){
        String result = "";   
        for (Rute[] x: ruteArray){
            result = result + "\n";
            for(Rute y: x){
                result = result + y;
            }
        }
        return result;
    }
}
