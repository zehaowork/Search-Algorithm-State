import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class DFS {
    private Grid startState;
    private Grid goalState;
    private ArrayList<String> path = new ArrayList<>();
    private Integer numNode = 0;

    public DFS(Grid startState, Grid goalState){
        this.startState = startState;
        this.goalState = goalState;
    }

    public static void main(String [ ] args) {
        Grid startGrid = new Grid(new Point(3,0),new Point(0,0),new Point(1,0),new Point(2,0),null,"INITIAL",0,4,new Point(2,1));
        Grid endGrid = new Grid(new Point(0,0),new Point(1,2),new Point(1,1),new Point(1,0),null,"goal",0,4,new Point(2,1));
        DFS dfs = new DFS(startGrid,endGrid);

        long startTime = System.currentTimeMillis();
        Boolean isFound = dfs.depthFirstSearch();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        if (isFound){
            System.out.println("Depth First Search");
            System.out.println("----------------------------------");
            System.out.println("Path:"+dfs.path);
            System.out.println("Num of Node Generated:"+dfs.numNode+"    Time Taken:"+elapsedTime+"ms"+"    Depth of Reached:"+(dfs.path.size()-1));
            System.out.println();
        }
        else {
            System.out.println("Not Found");
        }
    }

    public Boolean depthFirstSearch (){
        if (this.startState.isStateEqual(goalState)){
            path.add(startState.getDirection());
            return true;
        }
        Stack<Grid> gridStack = new Stack<>();
        gridStack.add(startState);
        while (!gridStack.isEmpty()){
            Grid currentGrid = gridStack.pop();
            currentGrid.generateChildNodes();
            numNode=numNode+currentGrid.getChildNodes().size();
            if (currentGrid.isStateEqual(goalState)){
                path.add(currentGrid.getDirection());
                while (currentGrid.getParentNode()!=null) {
                    path.add(currentGrid.getParentNode().getDirection());
                    currentGrid = currentGrid.getParentNode();
                }
                Collections.reverse(path);
                return true;
            }
            else {
                ArrayList <Grid> randomisedChildNode = currentGrid.getChildNodes();
                Collections.shuffle(randomisedChildNode);
                for (Grid grid:randomisedChildNode){
                    gridStack.push(grid);
                }
            }
        }
        return false;
    }
}
