package sample.GUI;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.Logic.ArrowType;
import sample.Logic.Cells;
import sample.Logic.GameField;
import sample.Logic.Point;

import java.util.ArrayList;

public class Drawer {
    //Constructor and declarations/initialising
    public Drawer(GridPane gridPane, Cells[][] cells, GameField gameField, ArrayList<Cells> steps) {
        this.gridPane = gridPane;
        this.cells = cells;
        this.gameField = gameField;
        this.steps = steps;
    }

    private GridPane gridPane;
    private Cells[][] cells;
    private GameField gameField;
    private ArrayList<Cells> steps;
    private int lastIndex;

    //function to draw the initialise state
    public void drawInit() {
        gridPane.getChildren().clear();
        lastIndex = steps.size();
        for (int x = 0; x < gameField.getWidth(); x++) {
            for (int y = 0; y < gameField.getHeight(); y++) {
                drawTheCell(new Point(x, y));
            }
        }
    }

    //function for drawing one cell
    private void drawTheCell(Point point) {
        Region tile = new Region();
        double aboveBorder = 1;
        double rightBorder = 1;
        double belowBorder = 1;
        double leftBorder = 1;

        ArrowType type = ArrowType.EMPTY;

        Cells currentCell = cells[point.getY()][point.getX()];

        if (currentCell != null) {
            type = currentCell.getArrowType();

           Cells leftN = gameField.getLeftNeighbour(point);
            Cells rN = gameField.getRightNeighbour(point);
            Cells aN = gameField.getAboveNeighbour(point);
            Cells bN = gameField.getBelowNeighbour(point);

            if (leftN != null && leftN.getArea() == currentCell.getArea()) {
                leftBorder = 0.2;
            }
            if (rN != null && rN.getArea() == currentCell.getArea()) {
                rightBorder = 0.2;
            }
            if (aN != null && aN.getArea() == currentCell.getArea()) {
                aboveBorder = 0.2;
            }
            if (bN != null && bN.getArea() == currentCell.getArea()) {
                belowBorder = 0.2;
            }
        }

        String borders = aboveBorder + " " + rightBorder + " " + belowBorder + " " + leftBorder;

        tile.setStyle("-fx-background-color: black, white; -fx-background-insets: 0, " + borders + "; -fx-min-width: 40; -fx-min-height:40; -fx-max-width:40; -fx-max-height: 40;");

        Text text;

        if (type == ArrowType.DOWN) {
            text = new Text("↓");
        } else if (type == ArrowType.UP) {
            text = new Text("↑");
        } else if (type == ArrowType.LEFT) {
            text = new Text("←");
        } else if (type == ArrowType.RIGHT) {
            text = new Text("→");
        } else {
            text = new Text(" ");
        }

        text.setFont(Font.font(20));

        StackPane stackPane;

        stackPane = new StackPane(tile, text);

        gridPane.add(stackPane, point.getX(), point.getY());

    }

    //function for draw next step
    public void drawNextStep() {
        if (lastIndex < steps.size()) {
            if (lastIndex == steps.size() - 1) {
                drawInit();
            } else {
                drawTheCell(steps.get(lastIndex).getLocation());
            }
            lastIndex++;
        }
    }
}
