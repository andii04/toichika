package sample;

import javafx.scene.layout.GridPane;

public class GameField {
    String str = "test";
    GridPane grid;

    Cells[][] field = new Cells[2][5];

    String[][] Array = new String[5][4];

    public GameField() {
        Array[0][0] = "test";
        grid = new GridPane();
        grid.addColumn(3);
        grid.addRow(3);
        System.out.println(grid);
    }
}
