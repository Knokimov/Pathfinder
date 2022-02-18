import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class Square implements ActionListener{
    Labyrinth labyrinth;
    JButton button;
    char sign;
    int pathNr = 0, row, column;

    @Override
    public void actionPerformed(ActionEvent e) {
        if(labyrinth.mainframe.getComponentCount()>1){
            labyrinth.mainframe.remove(1);
            pathNr=0;
        }
        labyrinth.solve(row,column);
        JPanel solutionPanel = new JPanel();
        solutionPanel.setLayout(new GridLayout(labyrinth.solutions.size()/3+1,3));
        labyrinth.mainframe.revalidate();

        for(Path x: labyrinth.solutions){
            JButton nr = new JButton("Solution: " + (pathNr+1));
            nr.setPreferredSize(new Dimension(200,50));
            nr.setHorizontalAlignment(JLabel.CENTER);
            nr.setVerticalAlignment(JLabel.CENTER);
            nr.setBackground(Color.GREEN);
            nr.addActionListener(new Path(labyrinth, pathNr));
            solutionPanel.add(nr);
            pathNr++;
        }
        solutionPanel.setAlignmentX(10);
        labyrinth.mainframe.add(solutionPanel);
        labyrinth.mainframe.revalidate();
    }
    

    public Square(Labyrinth labyrinth, int row, int column, char sign){
        this.labyrinth = labyrinth;
        this.row = row;
        this.column = column;
        this.sign = sign;
        this.button = new JButton();

        if(this.sign == '#'){
            this.button.setBackground(Color.BLACK);
        } else {
            this.button.setBackground(Color.WHITE);
            this.button.addActionListener(this);
        }
        this.button.setPreferredSize(new Dimension(700/labyrinth.rows,700/labyrinth.columns));
        this.button.setHorizontalAlignment(JLabel.CENTER);
        this.button.setVerticalAlignment(JLabel.CENTER);
        this.button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    public boolean equals(char x){
        return this.sign == x;
    }
}
