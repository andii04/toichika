package sample.GUI;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.Logic.ArrowType;
import sample.Logic.Cells;
import sample.Logic.GameField;
import sample.Logic.Point;

import javax.swing.text.Style;
import java.util.ArrayList;

public class Drawer {
    private GridPane gridPane;
    private Cells[][] cells;
    private GameField gameBoard;
    private ArrayList<Cells> steps;
    private int lastIndex;


    public Drawer(GridPane gridPane, Cells[][] cells, GameField gameField, ArrayList<Cells> steps) {
        this.gridPane = gridPane;
        this.cells = cells;
        this.gameBoard = gameField;
        this.steps = steps;
    }

    public void drawNextStep () {
        if (lastIndex < steps.size()) {
            if (lastIndex == steps.size()-1) {
                drawInit();
            }
            else {
                drawCell(steps.get(lastIndex).getLocation(), true);
            }
            lastIndex++;
        }
    }

    public void drawInit() {
        gridPane.getChildren().clear();
        lastIndex = steps.size();
        for (int x = 0; x < gameBoard.getWidth(); x++) {
            for (int y = 0; y < gameBoard.getHeight(); y++) {
                drawCell(new Point (x,y), true);
            }
        }
    }

    public void drawCell (Point pPoint, boolean coloring) {
        Region tile = new Region();
        int aboveBorder = 1;
        int rightBorder = 1;
        int belowBorder = 1;
        int leftBorder = 1;

        ArrowType type = ArrowType.EMPTY;

        int value;

        Cells currentCell = cells[pPoint.getX()][pPoint.getY()];

        if (currentCell == null) {
            value = 0;
        }
        else {
            value = currentCell.getValue();
            type = currentCell.getCellType();

            Cells leftN = gameBoard.getLeftNeighbour(pPoint);
            Cells rN = gameBoard.getRightNeighbour(pPoint);
            Cells aN = gameBoard.getAboveNeighbour(pPoint);
            Cells bN = gameBoard.getBelowNeighbour(pPoint);

            if (leftN != null && leftN.getValue() == value) {
                leftBorder = 0;
            }
            if (rN != null && rN.getValue() == value) {
                rightBorder = 0;
            }
            if (aN != null && aN.getValue() == value) {
                aboveBorder = 0;
            }
            if (bN != null && bN.getValue() == value) {
                belowBorder = 0;
            }
        }

        String borders = aboveBorder + " " + rightBorder + " " + belowBorder + " " + leftBorder;

        tile.setStyle("-fx-background-color: black, white; -fx-background-insets: 0, " + borders + "; -fx-min-width: 40; -fx-min-height:40; -fx-max-width:40; -fx-max-height: 40;");

        Text text;

        if (value > 1) {
            text = new Text(String.valueOf(value));
            if (coloring) {
                tile.setStyle("-fx-background-color: black, " + Style.getCellColorString(value) + "; -fx-background-insets: 0, " + borders + "; -fx-min-width: 40; -fx-min-height:40; -fx-max-width:40; -fx-max-height: 40;");
            }
        }
        else {
            text = new Text("");
        }

        text.setFont(Font.font(20));

        StackPane stackPane;

        if (type == ArrowType.EMPTY) {
            stackPane = new StackPane(tile, text);
        }
        else if (type == ArrowType.RIGHT) {
            Circle circle = new Circle();
            circle.setStroke(Color.BLACK);
            if (coloring) {
                circle.setFill(Style.getCellColor(value));
            }
            else {
                circle.setFill(Color.WHITE);
            }


            circle.setRadius(17.0);
            stackPane = new StackPane(tile,  circle, text);
        }
        else {
            Text cross = new Text("X");
            cross.setFont(Font.font(30));
            if (currentCell.getValue() > 1) {
                stackPane = new StackPane(tile, text);
            }
            else {
                stackPane = new StackPane(tile, cross, text);
            }
        }

        gridPane.add(stackPane, pPoint.getX(), pPoint.getY());
    }
}
