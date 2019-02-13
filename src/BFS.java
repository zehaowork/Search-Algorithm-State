import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
        private Grid startState;
        private Grid goalState;
        private Integer numNode = 0;
        private ArrayList<String> path = new ArrayList<>();

        public BFS(Grid startState, Grid goalState ){
            this.startState = startState;
            this.goalState = goalState;
        }


    public static void main(String [ ] args) {
        Grid startGrid = new Grid(new Point(3,0),new Point(0,0),new Point(1,0),new Point(2,0),null,"INITIAL",0,4,new Point(5,1));
        Grid endGrid = new Grid(new Point(0,0),new Point(1,2),new Point(1,1),new Point(1,0),null,"goal",0,4,new Point(5,1));
        BFS bfs = new BFS(startGrid,endGrid);
        long startTime = System.currentTimeMillis();
        Boolean isFound = bfs.breathFirstSearch();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        if (isFound){
           System.out.println("Breadth First Search");
           System.out.println("----------------------------------");
           System.out.println("Path:"+bfs.path);
           System.out.println("Num of Node Generated:"+bfs.numNode+"    Time Taken:"+elapsedTime+"ms"+"    Depth of Reached:"+(bfs.path.size()-1));
           System.out.println();
        }
    }

    public Boolean breathFirstSearch (){
        if (this.startState.isStateEqual(goalState)){
            path.add(startState.getDirection());
           return true;
        }

        Queue<Grid> queue = new LinkedList<>();
        queue.add(this.startState);
        while (!queue.isEmpty()){
            Grid currentGrid = queue.remove();
            currentGrid.generateChildNodes();
            numNode = numNode+currentGrid.getChildNodes().size();
            if (currentGrid.isStateEqual(goalState)){
                path.add(currentGrid.getDirection());
                while (currentGrid.getParentNode()!=null){
                    path.add(currentGrid.getParentNode().getDirection());
                    currentGrid= currentGrid.getParentNode();
                }
                Collections.reverse(path);
            return true;
            }
            else {
                if (currentGrid.getChildNodes().isEmpty()){
                    return false;
                }
                else {
                    queue.addAll(currentGrid.getChildNodes());
                }
            }
        }
        return false;
    }

}
