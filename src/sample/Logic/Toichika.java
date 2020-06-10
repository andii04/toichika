package sample.Logic;

import java.util.ArrayList;

public class Toichika {

    //TODO: STeps zuerst machen
    public GameField nextStep(GameField gameField, Point p) {
        if (checkFinished(gameField)) {
            System.out.println("solution found");
            return gameField;
        }

        //Evaluation of next steps


        return null;
    }

    private boolean checkFinished(GameField gameField) {
        int highArea = gameField.getHighestArea();
        for(int i=0;i<highArea;i++){
            if(!gameField.checkIfOneArrowIsInArea(i)){
                return false;
            }

        }
        //wenn ned neue for schleifen immer wieder
        return true;
    }

    public static boolean solve(){

        return true;
    }

    public ArrayList<Cells> getSteps() {
        //TODO: schauen
        return null;
    }
}
