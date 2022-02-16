import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Path implements ActionListener {
    Labyrinth labyrinth;
    JPanel mainframe;
    JButton[][] components;
    int pathNr;
    ArrayList<Integer> path;
    
    @Override
    public void actionPerformed (ActionEvent e) {
        this.reset();
        for(int i = 0; i < labyrinth.solutions.get(pathNr).path.size(); i+=2){
            // System.out.println(i);
            components[labyrinth.solutions.get(pathNr).path.get(i)][labyrinth.solutions.get(pathNr).path.get(i+1)].setBackground(Color.BLUE);
                mainframe.revalidate();
        }
    }

    public void reset(){
        for (JButton[] x: this.components){
            for(JButton y: x){
                if(y.getBackground() != Color.BLACK){
                    y.setBackground(Color.WHITE);
                }
            }
        }
    }

    public Path(JPanel mainframe, JButton[][] components, int pathNr, Labyrinth labyrinth){
        this.mainframe = mainframe;
        this.components = components;
        this.pathNr = pathNr;
        this.labyrinth = labyrinth;
    }
}
