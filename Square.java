import java.awt.event.*;

class Square implements ActionListener{
    static Labyrinth labyrinth;
    int row;
    int column;
    char type;

    @Override
    public void actionPerformed(ActionEvent e){
        labyrinth.findSolutions(row, column);
        System.out.println(labyrinth.solutions);
        return;
    }

    // public void solve(){
        
    // }

    public Square(Labyrinth labyrinth, int row, int column, char type){
        this.labyrinth = labyrinth;
        this.row = row;
        this.column = column;
        this.type = type;
    }

    public String toString(){
        String result = "[" + row + " " + column + "]";
        

        return result;
    }
}
