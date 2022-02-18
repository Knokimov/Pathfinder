import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
    public static void main (String[] args) {
        Labyrinth labyrinth = null;
       
        // JFileChooser chooser = new JFileChooser();
        // JFileChooser chooser = new JFileChooser("~/Desktop/Pathfinder/labyrinths");
        // int result = chooser.showOpenDialog(null);
        // if (result != JFileChooser.APPROVE_OPTION){
            // System.exit(1);
        // } else {
                // File f = chooser.getSelectedFile();
                File file = new File("/home/andrey/Desktop/Compare/Pathfinder/labyrinths/5.in");
                // Scanner reader = null;
            try {
                labyrinth = new Labyrinth(file);            
                // reader = new Scanner(f);
                // String firstLine = reader.nextLine();
                // String[] rowsAndColumns = firstLine.split(" ");
                // rows = Integer.parseInt(rowsAndColumns[0]);
                // columns = Integer.parseInt(rowsAndColumns[1]);

            } catch (FileNotFoundException e) {
                System.exit(1);
            }
        // }
        JFrame window = new JFrame("Labyrinth");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(labyrinth.mainframe);
        JScrollPane scrollableArea = new JScrollPane(labyrinth.mainframe);  
        scrollableArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scrollableArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);   
        window.getContentPane().add(scrollableArea);  
        window.pack();
        window.setVisible(true);
    }
}
