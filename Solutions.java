import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Solutions implements ActionListener {
    Labyrinth labyrinth;
    static JPanel mainframe, losninger;
    JButton[][] components;
    int z = 0;
    int row;
    int column;
    
    @Override
    public void actionPerformed (ActionEvent e) {
        if(mainframe.getComponentCount()>1){
            mainframe.remove(1);
            z=0;
        }
        labyrinth.findSolutions(column, row);
        losninger = new JPanel();
        losninger.setLayout(new GridLayout(labyrinth.solutions.size()/3+1,3));
        mainframe.revalidate();

        for (Path x: labyrinth.solutions){
            JButton nr = new JButton("Solution: " + (z+1));
            nr.setPreferredSize(new Dimension(200,50));
            nr.setHorizontalAlignment(JLabel.CENTER);
            nr.setVerticalAlignment(JLabel.CENTER);
            nr.setBackground(Color.GREEN);
            nr.addActionListener(new Path(mainframe, components, z, labyrinth));
            losninger.add(nr);
            z++;
        }
        losninger.setAlignmentX(10);
        mainframe.add(losninger);
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
