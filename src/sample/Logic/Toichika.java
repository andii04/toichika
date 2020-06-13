package sample.Logic;
//KOOOOOOOOOPPPPPPPIIIIIIIIIIIEEEE
import java.util.ArrayList;

public class Toichika {
    private Cells[][]cells;
    private GameField gameField;
    private ArrayList[][] blackList;
    private ArrayList<Cells> listWithArrows = new ArrayList<Cells>();
    private ArrayList<Cells> steps = new ArrayList<Cells>();
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
    public Toichika() {

    }
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
    // complete Finish check
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
        //All areas
        for(int x= 0; x<gameField.getWidth();x++){
            for(int y = 0; y < gameField.getHeight();y++ ){
                if(cells[x][y].getArrowType() != null){
                    listWithArrows.add(cells[x][y]);
                }
            }
        }
        for(int i = 0; i < gameField.getHighestArea();i++){
            Cells cells = listWithArrows.get(0);
            ArrowType arrowType = cells.getArrowType();
            int area = cells.getArea();
        }
        System.out.println(listWithArrows.toString());

        return true;
    }
    public ArrayList<Cells> getSteps() {
        return steps;
    }
}
