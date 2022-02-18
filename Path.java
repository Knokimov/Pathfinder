import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Path implements ActionListener{
    Labyrinth labyrinth;
    ArrayList<Integer> path;
   
    @Override
    public void actionPerformed(ActionEvent e){
        this.labyrinth.reset();
        for(int i = 0; i < this.path.size(); i+=2){
            this.labyrinth.grid[this.path.get(i)][this.path.get(i+1)].button.setBackground(Color.BLUE);
            this.labyrinth.mainframe.revalidate();
        }
    }

    public Path(Labyrinth labyrinth, ArrayList<Integer> path){
        this.labyrinth = labyrinth;
        this.path = path;
    }
}
