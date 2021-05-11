public class Tuppel {
    int row;
    int column;

    public Tuppel(int row, int column){
        this.row = row;
        this.column = column;
    }

    public String toString() {
        return String.format("(%d,%d)", this.column, this.row);
    }
}
