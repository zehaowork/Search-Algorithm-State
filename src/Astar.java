

import java.awt.*;
import java.util.*;


public class Astar {
    private Grid startState;
    private Grid goalState;
    private Integer numNode=0;
    private ArrayList<String> path = new ArrayList<>();

    public Astar (Grid startState, Grid goalState){
        this.startState = startState;
        this.goalState = goalState;
    }

    public static void main(String [ ] args) {
        Grid startGrid = new Grid(new Point(3,0),new Point(0,0),new Point(1,0),new Point(2,0),null,"INITIAL",0,4,new Point(5,1));
        Grid endGrid = new Grid(new Point(3,0),new Point(1,2),new Point(1,1),new Point(1,0),null,"goal",0,4,new Point(5,1));
        Astar astar = new Astar(startGrid,endGrid);

        long startTime = System.currentTimeMillis();
        Boolean found = astar.aStarSearch();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        if (found){
            System.out.println("A* Search");
            System.out.println("----------------------------------");
            System.out.println("Path:"+astar.path);
            System.out.println("Num of Node Generated:"+astar.numNode+"    Time Taken:"+elapsedTime+"ms"+"    Depth of Reached:"+(astar.path.size()-1));
            System.out.println();
        }
    }

    public Boolean aStarSearch(){
        PriorityQueue<Grid> openList = new PriorityQueue<>(new Comparator<Grid>() {
            @Override
            public int compare(Grid o1, Grid o2) {
                if (f(o1)>f(o2)){
                    return 1;
                }
                else if (f(o1)<f(o2)){
                    return -1;
                }
                else {
                    return 0;
                }
            }
        });
        LinkedList<Grid> closeList = new LinkedList<>();
        openList.add(startState);
        while (!openList.isEmpty()){
            Grid current = openList.poll();
            if (current.isStateEqual(goalState)){
                path.add(current.getDirection());
                while (current.getParentNode()!=null) {
                    path.add(current.getParentNode().getDirection());
                    current = current.getParentNode();
                }
                Collections.reverse(path);
                return true;
            }
            current.generateChildNodes();
            numNode =numNode+current.getChildNodes().size();
            for (Grid grid:current.getChildNodes()){
                Integer fValue = f(grid);
                Integer currentF = f(current);
                if (isInClosedList(closeList,grid)&&fValue>=currentF){
                    continue;
                }
                else if (!(isInOpenList(openList,grid))||fValue<currentF){
                    if (isInClosedList(closeList,grid)){
                        closeList.remove(grid);
                    }
                    if (!isInOpenList(openList,grid)){
                        openList.add(grid);
                    }
                }
            }
                closeList.add(current);
        }
        return false;
    }


    public Integer h(Grid currentGrid){
        Integer dA = Math.abs(goalState.getLetterA().x-currentGrid.getLetterA().x)+Math.abs(goalState.getLetterA().y-currentGrid.getLetterA().y);
        Integer dB = Math.abs(goalState.getLetterB().x-currentGrid.getLetterB().x)+Math.abs(goalState.getLetterB().y-currentGrid.getLetterB().y);
        Integer dC = Math.abs(goalState.getLetterC().x-currentGrid.getLetterC().x)+Math.abs(goalState.getLetterC().y-currentGrid.getLetterC().y);
        return dA+dB+dC;
    }

    public Integer f(Grid currentGrid) {
        return currentGrid.getCost()+h(currentGrid);
    }

    private Boolean isInClosedList(LinkedList<Grid> closeList,Grid grid){
        for (Grid grid1:closeList){
            if (grid.isStateEqualIncludeAgent(grid1)){
                return true;
            }
        }
        return false;
    }

    private Boolean isInOpenList(PriorityQueue<Grid> openList,Grid grid){
        for (Grid grid1:openList){
            if (grid.isStateEqualIncludeAgent(grid1)){
                return true;
            }
        }
        return false;
    }
}
