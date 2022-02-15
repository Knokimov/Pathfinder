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

    // public void actionPerformed (ActionEvent e) {
    //     this.reset();
    //     for(Square x: labyrinth.solutions.get(pathNr).path) {
    //             components[x.row][x.column].setBackground(Color.BLUE);
    //             mainframe.revalidate();
    //     }
    // }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        this.reset();
        // for(int i = 0; i < path.size(); i+=2){
        for(int i = 0; i < labyrinth.solutions.get(pathNr).path.size(); i+=2){
        // for( x: labyrinth.solutions.get(pathNr).path) {
            System.out.println(i);
                components[labyrinth.solutions.get(pathNr).path.get(i)][labyrinth.solutions.get(pathNr).path.get(i+1)].setBackground(Color.BLUE);
                // components[i][i+1].setBackground(Color.BLUE);
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

    // public String toString(){
    //     String result = "";
    //         for(Square x: path){
    //             result += x;
    //         }
    //     return result;
    // }
}
