package sample.Logic;
//KOOOOOOOOOPPPPPPPIIIIIIIIIIIEEEE
import java.util.ArrayList;

public class Toichika {
    private GameField gameField;
    private ArrayList[][] blackList;
    private ArrayList<Cells> steps = new ArrayList<>();
    public Toichika(GameField gameField) {
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
            nextPoint = new Point (0, 0);
        }
        else {
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
        Point currentPoint = getNextPoint(null);
        Cells newCell;
        while (currentPoint != null) {
            for (int i = 0; i<= gameField.getWidth() ; i++) {
                if (!blackList[currentPoint.getX()][currentPoint.getY()].contains(i)) {
                    if (gameField.setCellValueAndCheck(currentPoint.getX(), currentPoint.getY(), i,ArrowType.EMPTY)) {
                        newCell = new Cells(ArrowType.EMPTY,new Point(i,3),0);
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
        }
        return true;
    }
    public ArrayList<Cells> getSteps() {
        return steps;
    }
}
