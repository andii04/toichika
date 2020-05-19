package sample;

import javafx.scene.layout.GridPane;

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

    /*public void removeCell (int pX, int pY) {
        if (pX >= 0 && pY >= 0 && pY < this.getHeight() && pX < this.getWidth()) {
            Cells currentCell = cells[pX][pY];
            if (currentCell != null && !currentCell.isFixed()) {
                if (currentCell.getArrowType() == ArrowType.EMPTY) {
                    cells[pX][pY] = null;
                }
                else {
                    //cells[pX][pY] = new Cells(currentCell.getArrowType());
                    cells[pX][pY].setLocation(pX, pY);
                }
            }
        }
    }*/

    private boolean setCell(int pX,int pY, Cells pCell){
        if(pX >=0 && pY >=0 && pX <this.getWidth() && pY > this.getHeight()){
            pCell.setLocation(pX,pY);
            cells[pX][pY] = pCell;
            if(pCell.getArrowType().equals(null)){
                pCell.setArrowType(ArrowType.RIGHT);
                System.out.println(pCell);
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
    private boolean checkIfArrowIsInArea(int pArea){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                Cells currentCell = cells[i][j];
                if(currentCell.getArrowType() != null && currentCell.getArea() == pArea){
                    return true;
                }
            }
        }
        return false;
    }

    private void setArrowInCell(Point p,ArrowType pArrow){
        cells[p.getX()][p.getY()].setArrowType(pArrow);
    }

    private boolean checkRow(Point p){
        Cells cell = cells[p.getX()][p.getY()];
        return false;
    }

    private boolean checkColoumn(Point p){
        Cells cell = cells[p.getX()][p.getY()];
        return false;
    }

    public GameField(int height, int lenght) {
       cells = new Cells[lenght][height];
    }

}
