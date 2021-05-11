import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Utvei implements ActionListener {
    Labyrint labyrint;
    JPanel mainframe;
    JButton[][] components;
    int losningnr;

    @Override
    public void actionPerformed (ActionEvent e) {
        this.reset();
        for (Tuppel x: labyrint.utveier.get(losningnr)) {
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
    
    public Utvei(JPanel mainframe, JButton[][] components, int losningnr, Labyrint labyrint){
        this.mainframe = mainframe;
        this.components = components;
        this.losningnr = losningnr;
        this.labyrint = labyrint;
    }
}


