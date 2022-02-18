import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Path implements ActionListener {
    Labyrinth labyrinth;
    int pathNr;
    ArrayList<Integer> path;
   
    @Override
    public void actionPerformed(ActionEvent e) {
        this.reset();
        for(int i = 0; i < labyrinth.solutions.get(pathNr).path.size(); i+=2){
            this.labyrinth.grid[labyrinth.solutions.get(pathNr).path.get(i)][labyrinth.solutions.get(pathNr).path.get(i+1)].button.setBackground(Color.BLUE);
            this.labyrinth.mainframe.revalidate();
        }
    }

    public void reset(){
        for (Square[] x: this.labyrinth.grid){
            for(Square y: x){
                if(y.button.getBackground() != Color.BLACK){
                    y.button.setBackground(Color.WHITE);
                }
            }
        }
    }

    public Path(Labyrinth labyrinth, int pathNr){
        this.pathNr = pathNr;
        this.labyrinth = labyrinth;
    }
}
