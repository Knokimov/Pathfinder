import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

class Main {
    public static void main (String[] args) {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION){
            System.exit(1);
        } else {
                File file = chooser.getSelectedFile();
            try {
                Labyrinth labyrinth = new Labyrinth(file);            
                JFrame window = new JFrame("Labyrinth");
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.add(labyrinth.mainframe);
                JScrollPane scrollableArea = new JScrollPane(labyrinth.mainframe);  
                scrollableArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
                scrollableArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);   
                window.getContentPane().add(scrollableArea);  
                window.pack();
                window.setVisible(true);
            } catch (FileNotFoundException e) {
                System.exit(1);
            }
        }
    }
}
