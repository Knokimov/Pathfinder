import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Solutions implements ActionListener {
    Labyrinth labyrinth;
    JPanel mainframe, solutions;
    JButton[][] components;
    int pathNr = 0, row, column;
    // ArrayList<Path> pathContainer;

    public void solve() {
        HashSet<Integer> visited = new HashSet<>();
        Deque<Integer> queue = new LinkedList<>();
        HashMap<Integer, Integer> parents = new HashMap<>();
        int[] iterationTable = {1,0,-1,0,1};
        
        System.out.println(row + " " + column);
        
        int start = (row << 16) + column;
        queue.add(start);
        
        System.out.println("rows: " + labyrinth.rows + " columns: " + labyrinth.columns);
 
        while(!queue.isEmpty()){ 
            int target = queue.poll();
           
            for(int i = 0; i < 4; i++){
                int neighbourRow = (target >> 16) + iterationTable[i];
                int neighbourColumn = target - ((target >> 16) << 16) + iterationTable[i+1];
                System.out.println(neighbourRow + " row and column " + neighbourColumn);
                
                if(neighbourRow >= 0 && neighbourRow < labyrinth.rows && neighbourColumn >= 0 &&
                   neighbourColumn < labyrinth.columns){
                    int neighbour = (neighbourRow << 16) + neighbourColumn;
                    
                    if(labyrinth.grid[neighbourRow][neighbourColumn] == '.' && !(visited.contains(neighbour))){
                        queue.add(neighbour);
                        visited.add(neighbour);
                        parents.put(neighbour, target);

                    } else if(labyrinth.grid[neighbourRow][neighbourColumn] == 'X'){
                        Path path = new Path(mainframe, components, pathNr,
                                    this.labyrinth);
                        ArrayList<Integer> pathFound = new ArrayList<>();
                        parents.put(neighbour, target);
                        int currentNode = neighbour;
                        
                        while(parents.containsKey(currentNode)){
                            pathFound.add(currentNode >> 16);
                            pathFound.add(currentNode - ((currentNode >> 16) << 16));
                            currentNode = parents.get(currentNode);
                        }

                        path.path = pathFound;
                        labyrinth.solutions.add(path);
                        System.out.println(pathFound);
                    }
                }
            }
            visited.add(target);
        }
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        if(mainframe.getComponentCount()>1){
            mainframe.remove(1);
            pathNr=0;
        }
        this.solve();
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
