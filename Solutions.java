import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Solutions implements ActionListener {
    Labyrinth labyrinth;
    static JPanel mainframe, solutions;
    JButton[][] components;
    int pathNr = 0;
    int row;
    int column;

    // public void solve(ArrayList<Integer> currentPath) {
    //     for(int i = 0; i < labyrinth.rows; i++){
    //         for(int j = 0; j < labyrinth.columns; j++){
    //             if(labyrinth.grid[i][j] == '.' && (i == 0 || j == 0 || i == labyrinth.rows -1 || j == labyrinth.columns -1)){
    //                // solutions.add(); 
    //             }
    //         }

    //     }
    // }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        if(mainframe.getComponentCount()>1){
            mainframe.remove(1);
            pathNr=0;
        }
        labyrinth.findSolutions(column, row);
        solutions = new JPanel();
        solutions.setLayout(new GridLayout(labyrinth.solutions.size()/3+1,3));
        mainframe.revalidate();

        for(Path x: labyrinth.solutions){
            JButton nr = new JButton("Solution: " + (pathNr+1));
            nr.setPreferredSize(new Dimension(200,50));
            nr.setHorizontalAlignment(JLabel.CENTER);
            nr.setVerticalAlignment(JLabel.CENTER);
            nr.setBackground(Color.GREEN);
            nr.addActionListener(new Path(mainframe, components, pathNr, labyrinth));
            solutions.add(nr);
            pathNr++;
        }
        solutions.setAlignmentX(10);
        mainframe.add(solutions);
        mainframe.revalidate();
    }

    public Solutions(Labyrinth labyrinth, JPanel mainframe, JButton[][] components, int row, int column){
        this.labyrinth = labyrinth;
        this.mainframe = mainframe;
        this.components = components;
        this.row = row;
        this.column = column;
    }
}
