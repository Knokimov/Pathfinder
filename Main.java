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
       
        // JFileChooser chooser = new JFileChooser();
        // JFileChooser chooser = new JFileChooser("~/Desktop/Pathfinder/labyrinths");
        // int result = chooser.showOpenDialog(null);
        // if (result != JFileChooser.APPROVE_OPTION){
            // System.exit(1);
        // } else {
                // File f = chooser.getSelectedFile();
                File f = new File("/home/andrey/Desktop/Pathfinder/labyrinths/1.in");
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
        // }
        JFrame window = new JFrame("Labyrinth");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        window.add(panel);
        panel.setPreferredSize(new Dimension(2000,2000));
        JPanel labyrinth = new JPanel();
        labyrinth.setLayout(new GridLayout(rows,columns));
        JButton[][] components = new JButton[rows][columns];

            System.out.println(l);
	for(int i = 0; i < rows; i++){
		for(int j = 0; j < columns; j++){
			JButton nr = new JButton();
			nr.setPreferredSize(new Dimension(700/rows,700/columns));
			components[i][j] = nr;
            // System.out.println("We are here");
            
            // System.out.println(l);
            
            if(l.grid[i][j] == '#'){
                nr.setBackground(Color.BLACK);
                // System.out.println("We are her3");
            }
            else if (l.grid[i][j] == '.'){
                nr.setBackground(Color.WHITE);
                // nr.addActionListener(new Losninger(l,l.grid[i][j],panel,components));
            // System.out.println("We are her4");
            }

            // System.out.println("We are here2");
            nr.setHorizontalAlignment(JLabel.CENTER);
            nr.setVerticalAlignment(JLabel.CENTER);
            nr.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            labyrinth.add(nr);
        }
    }
        panel.add(labyrinth);
        JScrollPane scrollableArea = new JScrollPane(panel);  
        scrollableArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scrollableArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);   
        window.getContentPane().add(scrollableArea);  
        window.pack();
        window.setVisible(true);
    }
}
