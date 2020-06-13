package sample.GUI;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import sample.Logic.ArrowType;
import sample.Logic.Cells;
import sample.Logic.GameField;
import sample.Logic.Point;

import java.util.ArrayList;

public class Drawer {
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

    public void drawInit() {
        gridPane.getChildren().clear();
        lastIndex = steps.size();
        for(int x=0;x<gameField.getWidth();x++){
            for(int y=0;y<gameField.getHeight();y++){
                drawTheCell(new Point(x,y));
            }
        }
    }

    public Drawer(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    private void drawTheCell(Point point) {
        Region tile = new Region();
        int borderOnTop = 1;
        int borderOnRight = 1;
        int borderOnDown = 1;
        int borderOnLeft = 1;
        ArrowType aType = ArrowType.EMPTY;
        Cells actualCell = cells[point.getX()][point.getY()];
        StackPane stackPane = null;
        Text text;

        if(actualCell == null){
            text = new Text();
            aType = actualCell.getArrowType();
            if(aType == ArrowType.EMPTY){
                stackPane = new StackPane(tile,text);
            }else if(aType == ArrowType.UP){
                System.out.println(" UP PFeil");
            }else if(aType == ArrowType.RIGHT){

            }else if(aType == ArrowType.DOWN){

            }else if(aType == ArrowType.LEFT){

            }
        }
        gridPane.add(stackPane,point.getX(),point.getY());
    }

    public void drawNextStep () {
        if (lastIndex < steps.size()) {
            if (lastIndex == steps.size()-1) {
                drawInit();
            }
            else {
                drawTheCell(steps.get(lastIndex).getLocation());
            }
            lastIndex++;
        }
    }
}
