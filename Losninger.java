import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Losninger implements ActionListener {
    Labyrinth labyrinth;
    static JPanel mainframe, losninger;
    JButton[][] components;
    int z = 0;
    
    @Override
    public void actionPerformed (ActionEvent e) {
        if(mainframe.getComponentCount()>1){
            mainframe.remove(1);
            z=0;
        }
        labyrint.finnUtveiFra(rute.column, rute.row);
        losninger = new JPanel();
        losninger.setLayout(new GridLayout(labyrint.utveier.size()/3+1,3));
        mainframe.revalidate();

        for (ArrayList<Tuppel> x: labyrint.utveier){
            JButton nr = new JButton("Solution: " + (z+1));
            nr.setPreferredSize(new Dimension(200,50));
            nr.setHorizontalAlignment(JLabel.CENTER);
            nr.setVerticalAlignment(JLabel.CENTER);
            nr.setBackground(Color.GREEN);
            nr.addActionListener(new Utvei(mainframe, components, z, labyrint));
            losninger.add(nr);
            z++;
        }
        losninger.setAlignmentX(10);
        mainframe.add(losninger);
        mainframe.revalidate();
    }

    public Losninger(Labyrinth labyrinth, JPanel mainframe, JButton[][] components){
        this.labyrinth = labyrinth;
        this.mainframe = mainframe;
        this.components = components;
    }
}
