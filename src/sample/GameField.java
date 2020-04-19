package sample;

import javafx.scene.layout.GridPane;

import java.awt.*;

public class GameField {
    int lenght = 10;
    int height = 15;
    String str = "test";
    GridPane grid;
    private Cells[][] cells;

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
    private boolean setCell(int pX,int pY, Cells pCell){
        if(pX >=0 && pY >=0 && pX <this.getWidth() && pY > this.getHeight()){
            pCell.setLocation(pX,pY);
            cells[pX][pY] = pCell;
            if(pCell.getArrowType().equals(null)){
                pCell.setArrowType(ArrowType.RIGHT);
            }
            return true;
        }
        return false;
    }

    private boolean checkIfCellIsNotSet(int pX,int pY){
        if (pX >= 0 && pY >= 0 && pY < this.getHeight() && pX < this.getWidth()) {
            return cells[pX][pY] == null; //|| !cells[pX][pY].isFixed();
        }
        return false;
    }
    private boolean checkIfNoArrowisInArea(int area, Cells cells){
        for(int i=0;i<10;i++){
            System.out.println(i);
        }
        return true;
    }

    public GameField(int lenght, int height) {
       cells = new Cells[lenght][height];
    }

}
