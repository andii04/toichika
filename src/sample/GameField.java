package sample;

import javafx.scene.layout.GridPane;

public class GameField {
    int lenght = 10;
    int height = 10;
    String str = "test";
    GridPane grid;
    Cells[][] cells = new Cells[lenght][height];

    public int getWidth() {
        return cells.length;
    }
    public int getHeight() {
        return cells[0].length;
    }

    private void giveCells(){
        int height = getHeight();
        int width = getWidth();
        System.out.println("Höhe "+height+" Breite "+width);
    }

    public GameField() {
        giveCells();
        //cells[0][1].setArrowType(ArrowType.RIGHT);
        //giveCells();
    }

}
