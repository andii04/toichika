package sample;

import com.sun.javafx.geom.Area;
import javafx.scene.layout.GridPane;

public class GameField {
    int lenght = 10;
    int height = 15;
    String str = "test";
    GridPane grid;
    Cells[][] cells = new Cells[lenght][height];
    private int area;

    public int getWidth() {
        return cells.length;
    }
    public int getHeight() {
        return cells[0].length;
    }

    private void giveCells(){
        //cells[0][0].setArrowType(ArrowType.EMPTY);
        //cells[0][1].setArrowType(ArrowType.RIGHT);
        int height = getHeight();
        int width = getWidth();
        System.out.println("Höhe "+height+" Breite "+width);
        System.out.println(cells[0][0]);
    }


    private boolean checkIfNoArrowisInArea(int area, Cells cells){
        //this.area = area;
        return true;
    }

    public GameField() {
        giveCells();
        //cells[0][1].setArrowType(ArrowType.RIGHT);
        //giveCells();
    }

}
