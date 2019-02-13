

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class IDS {
    private Grid startState;
    private Grid goalState;
    private ArrayList<String> path = new ArrayList<>();
    private Integer numNode = 0;

    public IDS(Grid startState, Grid goalState){
        this.startState = startState;
        this.goalState = goalState;

    }

    public static void main(String [ ] args) {
        Grid startGrid = new Grid(new Point(3,0),new Point(0,0),new Point(1,0),new Point(2,0),null,"INITIAL",0,4,new Point(5,1));
        Grid endGrid = new Grid(new Point(0,0),new Point(1,2),new Point(1,1),new Point(1,0),null,"goal",0,4,new Point(5,1));

        IDS ids = new IDS(startGrid,endGrid);
        long startTime = System.currentTimeMillis();
        ids.depthLimitSearch();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Iterative Deepening Search");
        System.out.println("----------------------------------");
        System.out.println("Path:"+ids.path);
        System.out.println("Number of Node Generated: "+ids.numNode+"    Time Taken: "+elapsedTime+"ms"+"    Depth of Reached: "+(ids.path.size()-1));
        }

    public void IDS (){


    }

    public void depthLimitSearch () {
        Grid startState = this.startState;
        int maxDepth = 0;

        Stack<Grid> stack = new Stack<>();
        stack.add(startState);
        while (!stack.isEmpty()) {
            Grid current = stack.pop();
            current.generateChildNodes();
            numNode= numNode+current.getChildNodes().size();
            if (current.isStateEqual(goalState)) {
                path.add(current.getDirection());
                while (current.getParentNode()!=null){
                    path.add(current.getParentNode().getDirection());
                    current= current.getParentNode();
                }
                Collections.reverse(path);
                break;
            }
            if (current.getCost() < maxDepth) {

                ArrayList<Grid> childStates = current.getChildNodes();
                for (Grid grid : childStates) {
                    stack.push(grid);
                }
            }
            if (stack.size() == 0) {
                maxDepth++;
                startState.getChildNodes().clear();
                stack.push(startState);

            }
        }

    }
}

