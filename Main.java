import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
    public static void main (String[] args) {
        int rows = 1;
        int columns = 1;
        Labyrinth l = null;
       
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION)
            System.exit(1);
            File f = chooser.getSelectedFile();
            Scanner reader = null;
        try {
            l = new Labyrinth(f);            
            reader = new Scanner(f);
            String firstLine = reader.nextLine();
            String[] rowsAndColumns = firstLine.split(" ");
            rows = Integer.parseInt(rowsAndColumns[0]);
            columns = Integer.parseInt(rowsAndColumns[1]);

        } catch (FileNotFoundException e) {
            System.exit(1);
        }

        JFrame vindu = new JFrame("Labyrinth");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        vindu.add(panel);
        panel.setPreferredSize(new Dimension(2000,2000));
        JPanel labyrint = new JPanel();
        labyrint.setLayout(new GridLayout(rows,columns));
        JButton[][] components = new JButton[rows][columns];

        for(char[] x: l.grid){
            for(char y: x){
                JButton nr = new JButton();
                nr.setPreferredSize(new Dimension(700/rows,700/columns));
                components[y.row][y.column] = nr;
                if(y instanceof SortRute){
                    nr.setBackground(Color.BLACK);
                }
                else if (y instanceof HvitRute){
                    nr.setBackground(Color.WHITE);
                    nr.addActionListener(new Losninger(l,y,panel,components));
                }
                nr.setHorizontalAlignment(JLabel.CENTER);
                nr.setVerticalAlignment(JLabel.CENTER);
                nr.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                labyrint.add(nr);
            }
        }
        panel.add(labyrint);
        JScrollPane scrollableArea = new JScrollPane(panel);  
        scrollableArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scrollableArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);   
        vindu.getContentPane().add(scrollableArea);  
        vindu.pack();
        vindu.setVisible(true);
    }
}
