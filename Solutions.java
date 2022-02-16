import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Solutions implements ActionListener {
    Labyrinth labyrinth;
    static JPanel mainframe, solutions;
    JButton[][] components;
    int pathNr = 0;
    int row;
    int column;

    public void solve() {
        HashSet<Integer> visited = new HashSet<>();
        Deque<Integer> queue = new LinkedList<>();
        HashMap<Integer, Integer> parents = new HashMap<>();
        int[] iterationTable = {1,0,-1,0,1};
        // parents.put(start, null);
        // int[] start = {row,column};
        System.out.println(row + " " + column);
        int start = (row << 16) + column;
        parents.put(start, null);
        queue.add(start);
        
        while(!queue.isEmpty()){ 
            int target = queue.poll();
            // System.out.println(target + " size: " + queue.size());
            // System.out.println(visited.size());
           
            for(int i = 0; i < 4; i++){
                int neighbourRow = target - ((target >> 16) << 16) + iterationTable[i+1];
                int neighbourColumn = (target >> 16) + iterationTable[i];
                // System.out.println(neighbourRow + " row and column " + neighbourColumn);
                
                if(neighbourRow >= 0 && neighbourRow < labyrinth.rows && neighbourColumn >= 0 &&
                   neighbourColumn < labyrinth.columns && labyrinth.grid[neighbourRow][neighbourColumn] == '.'){
                    // int[] neighbour = {target[0]+iterationTable[i], target[1]+iterationTable[i+1]};
                System.out.println(neighbourRow + " row and column " + neighbourColumn);
                    int neighbour = (neighbourRow << 16) + neighbourColumn;
                    // System.out.println(neighbour + " neighbour " );

                    // if(neighbourRow == 0 || neighbourColumn == 0 ||
                        // neighbourRow == labyrinth.rows-1 ||
                        // neighbourColumn == labyrinth.columns-1){
                        System.out.println("here it is");
                        // ArrayList<Integer> pathFound = new ArrayList<>();
                        
                        int currentNode = neighbour;
                        
                        // while(parents.containsKey(currentNode)){
                            // pathFound.add(currentNode);
                            // currentNode = parents.get(currentNode);
                            Path path = new Path(this.mainframe, this.components, 0, this.labyrinth);

                           // for(int x: pathFound){
                               // path.path.add(x -((x >> 16) << 16));
                               // path.path.add(x >> 16);
                           // }
                            System.out.println(path);

                            // solution.path = path;
                            labyrinth.solutions.add(path);
                        // }
                    // }

                    if(!(visited.contains(neighbour))){
                        queue.add(neighbour);
                        visited.add(neighbour);
                        // System.out.println("No Neighbour here" + neighbour);
                        // ArrayList<int[]> parent = new ArrayList<>();
                        // parent.add(target);
                        parents.put(neighbour, target);
                    }
                }
            }
            visited.add(target);
            // System.out.println("added " + target);
        }
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        if(mainframe.getComponentCount()>1){
            mainframe.remove(1);
            pathNr=0;
        }
        // labyrinth.findSolutions(column, row);
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
        // this.mainframe = labyrinth.mainframe;
        // this.components = labyrinth.components;
        this.row = row;
        this.column = column;
    }
}
