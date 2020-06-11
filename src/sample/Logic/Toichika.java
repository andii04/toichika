package sample.Logic;
//KOOOOOOOOOPPPPPPPIIIIIIIIIIIEEEE

import java.util.ArrayList;

public class Toichika {

    public static boolean solve() {

        return true;
    }

    //TODO: STeps zuerst machen
    public GameField nextStep(GameField gameField) {
        if (checkFinished(gameField)) {
            System.out.println("solution found");
            return gameField;
        }
        //Evaluation of next steps
        return null;
    }

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

    public ArrayList<Cells> getSteps() {
        //TODO: schauen
        return null;
    }
}
