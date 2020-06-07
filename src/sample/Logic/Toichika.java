package sample.Logic;

import java.util.ArrayList;

public class Toichika {
    private GameField gameField;
    private ArrayList<Integer>[][] blackList;
    private ArrayList<Cells> steps = new ArrayList<>();

    public Toichika(GameField gameField) {
        this.gameField = gameField;
        blackList = new ArrayList[gameField.getWidth()][gameField.getHeight()];
        for (int x = 0; x < gameField.getWidth(); x++) {
            for (int y = 0; y < gameField.getHeight(); y++) {
                blackList[x][y] = new ArrayList<>();
            }
        }
    }

    public static boolean solve(){
        //gogogoTODO
        return true;
    }

    public ArrayList<Cells> getSteps() {
        //TODO: schauen
        return null;
    }
}
