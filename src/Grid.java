
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Grid {
    private Integer gridSize;
    private Grid parentNode;
    private String direction;
    private Integer cost;
    private Point Agent;
    private Point letterA;
    private Point letterB;
    private Point letterC;

    private Point tempAgent;
    private Point tempLetterA;
    private Point tempLetterB;
    private Point tempLetterC;

    private Point obstacle;

    private ArrayList <Grid> childNodes = new ArrayList<>();

    public Grid(Point Agent, Point letterA, Point letterB,Point letterC ,Grid parentNode, String direction,Integer cost,Integer gridSize,Point obstacle ){
        this.Agent = Agent;
        this.letterA = letterA;
        this.letterB = letterB;
        this.letterC = letterC;
        this.parentNode = parentNode;
        this.direction = direction;
        this.cost = cost;
        this.gridSize=gridSize-1;
        this.obstacle = obstacle;
    }





    public void generateChildNodes(){
        if (this.getDirection() != "INITIAL"){
            if (this.getDirection()=="RIGHT"){
                childNodes.add(moveRight("RIGHT"));
                childNodes.add(moveDown("DOWN"));
                childNodes.add(moveUp("UP"));
                childNodes.add(moveDOWNLEFT("DOWNLEFT"));
                childNodes.add(moveDOWNRIGHT("DOWNRIGHT"));
                childNodes.add(moveUPLEFT("UPLEFT"));
                childNodes.add(moveUPRIGHT("UPRIGHT"));
            }
            else if (this.getDirection()=="LEFT"){
                childNodes.add(moveLeft("LEFT"));
                childNodes.add(moveDown("DOWN"));
                childNodes.add(moveUp("UP"));
                childNodes.add(moveDOWNLEFT("DOWNLEFT"));
                childNodes.add(moveDOWNRIGHT("DOWNRIGHT"));
                childNodes.add(moveUPLEFT("UPLEFT"));
                childNodes.add(moveUPRIGHT("UPRIGHT"));
            }
            else if (this.getDirection()=="UP"){
                childNodes.add(moveLeft("LEFT"));
                childNodes.add(moveRight("RIGHT"));
                childNodes.add(moveUp("UP"));
                childNodes.add(moveDOWNLEFT("DOWNLEFT"));
                childNodes.add(moveDOWNRIGHT("DOWNRIGHT"));
                childNodes.add(moveUPLEFT("UPLEFT"));
                childNodes.add(moveUPRIGHT("UPRIGHT"));
            }
            else if (this.getDirection()=="DOWN"){
                childNodes.add(moveLeft("LEFT"));
                childNodes.add(moveRight("RIGHT"));
                childNodes.add(moveDown("DOWN"));
                childNodes.add(moveDOWNLEFT("DOWNLEFT"));
                childNodes.add(moveDOWNRIGHT("DOWNRIGHT"));
                childNodes.add(moveUPLEFT("UPLEFT"));
                childNodes.add(moveUPRIGHT("UPRIGHT"));
            }
            else if (this.getDirection()=="UPRIGHT"){
                childNodes.add(moveRight("RIGHT"));
                childNodes.add(moveDown("DOWN"));
                childNodes.add(moveUp("UP"));
                childNodes.add(moveLeft("LEFT"));
                childNodes.add(moveDOWNRIGHT("DOWNRIGHT"));
                childNodes.add(moveUPLEFT("UPLEFT"));
                childNodes.add(moveUPRIGHT("UPRIGHT"));
            }
            else if (this.getDirection()=="UPLEFT"){
                childNodes.add(moveRight("RIGHT"));
                childNodes.add(moveDown("DOWN"));
                childNodes.add(moveUp("UP"));
                childNodes.add(moveLeft("LEFT"));
                childNodes.add(moveDOWNLEFT("DOWNLEFT"));
                childNodes.add(moveUPLEFT("UPLEFT"));
                childNodes.add(moveUPRIGHT("UPRIGHT"));
            }
            else if (this.getDirection()=="DOWNLEFT"){
                childNodes.add(moveRight("RIGHT"));
                childNodes.add(moveDown("DOWN"));
                childNodes.add(moveUp("UP"));
                childNodes.add(moveLeft("LEFT"));
                childNodes.add(moveDOWNLEFT("DOWNLEFT"));
                childNodes.add(moveDOWNRIGHT("DOWNRIGHT"));
                childNodes.add(moveUPLEFT("UPLEFT"));
            }
            else if (this.getDirection()=="DOWNRIGHT"){
                childNodes.add(moveRight("RIGHT"));
                childNodes.add(moveDown("DOWN"));
                childNodes.add(moveUp("UP"));
                childNodes.add(moveLeft("LEFT"));
                childNodes.add(moveDOWNLEFT("DOWNLEFT"));
                childNodes.add(moveDOWNRIGHT("DOWNRIGHT"));
                childNodes.add(moveUPRIGHT("UPRIGHT"));
            }
        }
        else {
            childNodes.add(moveRight("RIGHT"));
            childNodes.add(moveDown("DOWN"));
            childNodes.add(moveUp("UP"));
            childNodes.add(moveLeft("LEFT"));
            childNodes.add(moveDOWNLEFT("DOWNLEFT"));
            childNodes.add(moveDOWNRIGHT("DOWNRIGHT"));
            childNodes.add(moveUPLEFT("UPLEFT"));
            childNodes.add(moveUPRIGHT("UPRIGHT"));
        }
            clearUpChildrenNode();

    }

    public Grid swapTiles(Integer x, Integer y,String direction) {
        if (!isObstacle(x,y)){
            if (letterA.x==x && letterA.y == y) {
                Point tempCoordinate = new Point(tempAgent.x,tempAgent.y);
                tempAgent = new Point(x,y);
                tempLetterA = new Point(tempCoordinate.x,tempCoordinate.y);
                return new Grid(tempAgent,tempLetterA,tempLetterB,tempLetterC,this,direction,this.cost+1,this.gridSize+1,this.obstacle);
            }
            else if (letterB.x == x && letterB.y == y){
                Point tempCoordinate = new Point(tempAgent.x,tempAgent.y);
                tempAgent = new Point(x,y);
                tempLetterB = new Point(tempCoordinate.x,tempCoordinate.y);
                return new Grid(tempAgent,tempLetterA,tempLetterB,tempLetterC,this,direction,this.cost+1,this.gridSize+1,this.obstacle);
            }
            else if (letterC.x == x && letterC.y == y){
                Point tempCoordinate = new Point(tempAgent.x,tempAgent.y);
                tempAgent = new Point(x,y);
                tempLetterC = new Point(tempCoordinate.x,tempCoordinate.y);
                return new Grid(tempAgent,tempLetterA,tempLetterB,tempLetterC,this,direction,this.cost+1,this.gridSize+1,this.obstacle);
            }
            else {
                tempAgent = new Point(x,y);
                return new Grid(tempAgent,tempLetterA,tempLetterB,tempLetterC,this,direction,this.cost+1,this.gridSize+1,this.obstacle);
            }
        }
        return null;
    }



    public void resetTiles (){
        tempAgent = new Point(Agent.x,Agent.y);
        tempLetterA = new Point(letterA.x,letterA.y);
        tempLetterB = new Point(letterB.x,letterB.y);
        tempLetterC = new Point(letterC.x,letterC.y);
    }

    public Grid moveLeft (String direction) {
        resetTiles();
        if (tempAgent.x>0){
            return swapTiles(tempAgent.x-1,tempAgent.y,direction);
        }
        else{
            return null;
        }
    }

    public Grid moveRight (String direction) {
        resetTiles();
        if (tempAgent.x<this.gridSize){
            return swapTiles(tempAgent.x+1,tempAgent.y,direction);
        }
        else{
            return null;
        }
    }

    public Grid moveUp (String direction) {
        resetTiles();
        if (tempAgent.y<this.gridSize){
            return swapTiles(tempAgent.x,tempAgent.y+1,direction);
        }
        else{
            return null;
        }
    }

    public Grid moveDown (String direction) {
        resetTiles();
        if (tempAgent.y>0){
            return swapTiles(tempAgent.x,tempAgent.y-1,direction);
        }
        else{
            return null;
        }
    }

    public Grid moveUPLEFT (String direction){
        resetTiles();
        if (tempAgent.x>0&&tempAgent.y<gridSize){
            return swapTiles(tempAgent.x-1,tempAgent.y+1,direction);
        }
        else {
            return null;
        }
    }

    public Grid moveUPRIGHT (String direction){
        resetTiles();
        if (tempAgent.x<gridSize&&tempAgent.y<gridSize){
            return swapTiles(tempAgent.x+1,tempAgent.y+1,direction);
        }
        else {
            return null;
        }

    }

    public Grid moveDOWNLEFT (String direction){
        resetTiles();
        if (tempAgent.x>0&&tempAgent.y>0){
            return swapTiles(tempAgent.x-1,tempAgent.y-1,direction);
        }
        else {
            return null;
        }
    }

    public Grid moveDOWNRIGHT (String direction){
        resetTiles();
        if (tempAgent.x<gridSize&&tempAgent.y>0){
            return swapTiles(tempAgent.x+1,tempAgent.y-1,direction);
        }
        else {
            return null;
        }

    }
    public ArrayList<Grid> getChildNodes() {
        return childNodes;
    }

    public void clearUpChildrenNode(){
        childNodes.removeAll(Collections.singleton(null));
    }

    public boolean isStateEqual (Grid compareGrid){
        if (this.letterA.equals(compareGrid.letterA)&&this.letterB.equals(compareGrid.letterB)&&this.letterC.equals(compareGrid.letterC)){
          return true;
        }
        return false;
    }

    public String getDirection() {
        return direction;
    }

    public Grid getParentNode() {
        return parentNode;
    }

    public Point getLetterA() {
        return letterA;
    }

    public Point getLetterB() {
        return letterB;
    }

    public Point getLetterC() {
        return letterC;
    }

    public Integer getCost() {
        return cost;
    }

    public Boolean isObstacle (Integer x , Integer y){
        if (obstacle.x == x && obstacle.y == y){
            return true;
        }
        return false;
    }


    public boolean isStateEqualIncludeAgent (Grid compareGrid){
        if (this.letterA.equals(compareGrid.letterA)&&this.letterB.equals(compareGrid.letterB)&&this.letterC.equals(compareGrid.letterC)&&this.Agent.equals(compareGrid.Agent)){
            return true;
        }
        return false;
    }

}
