import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Path implements ActionListener {
    Labyrinth labyrinth;
    JPanel mainframe;
    JButton[][] components;
    int pathNr;
    ArrayList<Square> path;

    @Override
    public void actionPerformed (ActionEvent e) {
        this.reset();
        for(Square x: labyrinth.solutions.get(pathNr).path) {
                components[x.row][x.column].setBackground(Color.BLUE);
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
    
    public Path(int losningnr, Labyrinth labyrinth, int pathNr){
        this.labyrinth = labyrinth;
        this.mainframe = labyrinth.mainframe;
        this.components = labyrinth.components;
        this.pathNr = pathNr;
    }

    public String toString(){
        String result = "";
            for(Square x: path){
                result += x;
            }
        return result;
    }
}
