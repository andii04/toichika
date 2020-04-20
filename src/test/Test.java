package test;

import sample.GameField;

public class Test {
    GameField gameField = new GameField(3,12);

    public void Test(){
        int height = gameField.getHeight();
        int width = gameField.getWidth();
    }
}
