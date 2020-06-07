package sample.Logic;

import javafx.scene.layout.GridPane;

public class GameField {
    //hier JSON Parsing

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

    //TODO: Removing a Set Cell
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

    private boolean checkIfCellIsNotSet(Point p){
        if (p.getX() >= 0 && p.getY() >= 0 && p.getY() < this.getHeight() && p.getX() < this.getWidth()) {
            return cells[p.getX()][p.getY()].getArrowType() == null; //|| !cells[pX][pY].isFixed();
        }
        return false;
    }

    //rdy
    private boolean checkIfOneArrowIsInArea(int pArea){
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

    //rdy
    private void setArrowInCell(Point p, ArrowType pArrow){
        cells[p.getX()][p.getY()].setArrowType(pArrow);
    }

    //TODO: check COLOUMN no Arrow between two Arrows
    private boolean checkIfNoArrowBetweenInColoumn(Point p,Point q){
        Cells cellOne = cells[p.getX()][p.getY()];
        Cells cellTwo = cells[q.getX()][q.getY()];
        if(cellOne.getArrowType()==ArrowType.UP && cellTwo.getArrowType()==ArrowType.DOWN || cellOne.getArrowType()==ArrowType.DOWN && cellTwo.getArrowType()== ArrowType.UP){
            for(int i = cellOne.getPoint().getY(); i<cellTwo.getPoint().getY();i++){
                return true;
            }
        }
        return false;
    }

    //TODO: check ROW no Arrow between tow Arrows //noch nicht nachgeschaut
    private boolean checkIfNoArrowBetweenInRow(Point p,Point q){
        Cells cellOne = cells[p.getX()][p.getY()];
        Cells cellTwo = cells[q.getX()][q.getY()];

        if(cellOne.getArrowType()==ArrowType.RIGHT && cellTwo.getArrowType()==ArrowType.LEFT || cellOne.getArrowType()==ArrowType.LEFT && cellTwo.getArrowType()== ArrowType.RIGHT){
            for(int i = cellOne.getPoint().getX(); i<cellTwo.getPoint().getX();i++){
                return true;
            }
        }
        return false;
    }

    private boolean checkIfOtherAreaBetweenArrows(Point p, Point q){
        return false;
    }

    public GameField(int height, int lenght) {
       cells = new Cells[lenght][height];
    }
}
