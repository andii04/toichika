package sample;

import javafx.scene.layout.GridPane;

public class GameField {
    int lenght = 10;
    int height = 8;
    String str = "test";
    GridPane grid;
    Cells[][] cells = new Cells[lenght][height];

    private void giveCells(){
        System.out.println(cells.length);
    }

    public GameField() {
        //cells[0][0].setArrowType(ArrowType.RIGHT);
        //System.out.println(cells[0][0]);
        giveCells();
    }
}
