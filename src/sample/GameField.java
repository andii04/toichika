package sample;

import javafx.scene.layout.GridPane;

public class GameField {
    String str = "test";
    GridPane grid;

    public GameField() {
        grid = new GridPane();
        grid.addColumn(3);
        grid.addRow(3);
        System.out.println(grid);
    }
}
