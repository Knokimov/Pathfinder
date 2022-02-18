import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class Square implements ActionListener{
    Labyrinth labyrinth;
    int row, column;
    char sign;
    JButton button;

    @Override
    public void actionPerformed(ActionEvent e){
        if(labyrinth.mainframe.getComponentCount()>1){
            labyrinth.mainframe.remove(1);
        }
        this.labyrinth.reset(); 
        this.labyrinth.solve(row,column);
        JPanel solutionPanel = new JPanel();
        solutionPanel.setLayout(new GridLayout(labyrinth.solutions.size()/3+1,3));
        labyrinth.mainframe.revalidate();
        this.button.setBackground(Color.GREEN);

        for(int i = 0; i < labyrinth.solutions.size(); i++){
            JButton solutionButton = new JButton("Solution: " + (i+1));
            solutionButton.setPreferredSize(new Dimension(200,50));
            solutionButton.setHorizontalAlignment(JLabel.CENTER);
            solutionButton.setVerticalAlignment(JLabel.CENTER);
            solutionButton.setBackground(Color.GREEN);
            solutionButton.addActionListener(this.labyrinth.solutions.get(i));
            solutionPanel.add(solutionButton);
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
}
