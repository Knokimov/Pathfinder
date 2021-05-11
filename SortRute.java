public class SortRute extends Rute {
    
    @Override
    public char tilTegn(){
        return this.tegn; 
    }

    public SortRute(int row, int column, Labyrint labyrint){
        this.row = row;
        this.column = column;
        this.labyrint = labyrint;
        this.tegn = '#';
        this.naboer = new Rute[4];
    }
    
    @Override
    public String toString() {
        return "#";
    }
}
