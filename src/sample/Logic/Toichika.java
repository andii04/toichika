package sample.Logic;
//KOOOOOOOOOPPPPPPPIIIIIIIIIIIEEEE
import java.util.ArrayList;

public class Toichika {
    //declarations and initialising
    private Cells[][]cells;
    private GameField gameField;
    private ArrayList[][] blackList;
    private ArrayList<Cells> listWithArrows = new ArrayList<Cells>();
    private ArrayList<Integer> fixAreas = new ArrayList<Integer>();
    private ArrayList<Cells> steps = new ArrayList<Cells>();
    //Constructor
    public Toichika(GameField gameField) {
        cells = gameField.getCells();
        this.gameField = gameField;
        blackList = new ArrayList[gameField.getWidth()][gameField.getHeight()];
        for(int x = 0; x < gameField.getWidth();x++){
            for(int y = 0; y < gameField.getHeight();y++){
                blackList[x][y] = new ArrayList<>();
            }
        }
    }
    //function to get a Step before
    private Point getStepBefore(Point p) {
        if (steps.size() > 0) {
            blackList[p.getX()][p.getY()].clear();
            gameField.removeCell(p.getX(), p.getY());
            Cells prev = steps.get(steps.size() - 1);
            blackList[prev.getLocation().getX()][prev.getLocation().getY()].add(prev.getValue());
            steps.remove(steps.size() - 1);
            return prev.getLocation();
        }
        System.out.println("Not a solution possible!");
        return null;
    }
    //function to get the next point
    private Point getNextPoint(Point p) {
        Point nextPoint = null;
        if (p == null) {
            //startcell left top
            nextPoint = new Point (0, 0);
        }
        else {
            //going one cell right in same row
            nextPoint = new Point (p.getX() + 1, p.getY());
        }

        while (true) {
            if (nextPoint.getX() >= gameField.getWidth()) {
                nextPoint.setX(0);
                nextPoint.setY(nextPoint.getY() + 1);
            }
            if (nextPoint.getY() < gameField.getHeight()) {
                if (gameField.isPointFree(nextPoint.getX(), nextPoint.getY())) {
                    return nextPoint;
                }
                else {
                    nextPoint.setX(nextPoint.getX()+1);
                }
            }
            else {
                break;
            }
        }
        System.out.println("is solveble");
        return null;
    }
    /*public static boolean solve() {

        return true;
    }*/
    //TODO: STeps zuerst machen
    public GameField nextStep(GameField gameField) {
        if (checkFinished(gameField)) {
            System.out.println("solution found");
            return gameField;
        }
        //Evaluation of next steps
        return null;
    }
    // complete Finish check if Solution is correct and finished
    private boolean checkFinished(GameField gameField) {
        int highArea = gameField.getHighestArea();
        int gameFieldlenght = gameField.getWidth();
        int gameFieldHeight = gameField.getHeight();
        for (int i = 0; i < highArea; i++) {
            if (!gameField.checkIfOneArrowIsInArea(i)) {
                return false;
            }
        }
        for (int i = 0; i < gameFieldlenght; i++) {
                if (!(gameField.checkIfTwoArrowsInColoumnAndLookEachOther(i))) {
                    return false;
                }
        }
        for (int i = 0; i < gameFieldHeight; i++) {
            if (!(gameField.checkIfTwoArrowsInRowAndLookEachOther(i))) {
                return false;
            }
        }
        //wenn ned neue for schleifen immer wieder
        return true;
    }
    public boolean solve() {
        int counter = 0;
        //All arrows
        for(int x= 0; x<gameField.getWidth();x++){
            for(int y = 0; y < gameField.getHeight();y++ ){
                if(cells[x][y].getArrowType() != null){
                    listWithArrows.add(cells[x][y]);
                    counter++;
                }
            }
        }
        System.out.println("Counter"+ counter);
        //all areas for fix them from beginning setted arrows
        System.out.println("highest Area" + gameField.getHighestArea());
        System.out.println(listWithArrows.size());
        for(int i = 0; i < listWithArrows.size();i++){
            System.out.println("i:    "+i);
            Cells cells = listWithArrows.get(i);
            int area = cells.getArea();
            ArrowType arrowType = cells.getArrowType();
            if(arrowType.equals(ArrowType.UP) || arrowType.equals(ArrowType.RIGHT) || arrowType.equals(ArrowType.DOWN) || arrowType.equals(ArrowType.LEFT)){
                if(!(fixAreas.contains(area))){
                    fixAreas.add(area);
                    System.out.println("Area:   "+ area);
                }
            }
            else{
                System.out.println("EMPTY Pfeil");
            }
        }
        System.out.println(fixAreas.toString()+"  fixe Areas");
        System.out.println("listwitharrows:      "+ listWithArrows.toString());
        for(int k = 0;k<= gameField.getHighestArea();k++){
            if(fixAreas.contains(k)){
                System.out.println("contains  "+k);
            }else{
                System.out.println("dont contains k");
            }
        }

        return true;
        //Test
        /*Point currentPoint = getNextPoint(null);
        int highestArea = gameField.getHighestArea();
        Cells newCell;
        while (currentPoint != null) {
            for (int i = highestArea; i>= 2; i--) {
                if (!blackList[currentPoint.getX()][currentPoint.getY()].contains(i)) {
                    if (gameField.setCellValueAndCheck(currentPoint.getX(), currentPoint.getY(), i)) {
                        newCell = new Cells(i);
                        newCell.setLocation(currentPoint.getX(), currentPoint.getY());
                        steps.add(newCell);
                        currentPoint = getNextPoint(currentPoint);
                        break;
                    }
                    else if (2 == i) {
                        currentPoint = getStepBefore(currentPoint);
                        if (currentPoint == null) {
                            return false;
                        }
                        break;
                    }
                    else {
                        blackList[currentPoint.getX()][currentPoint.getY()].add(i);
                    }
                }
                else if (i == 2) {
                    currentPoint = getStepBefore(currentPoint);
                    if (currentPoint == null) {
                        return false;
                    }
                    break;
                }
            }
        }*/

        //return true;
    }
    public ArrayList<Cells> getSteps() {
        return steps;
    }
}
