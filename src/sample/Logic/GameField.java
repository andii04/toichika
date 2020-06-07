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
        if(cellOne.getArrowType() == ArrowType.UP && cellTwo.getArrowType() == ArrowType.DOWN || cellOne.getArrowType() == ArrowType.DOWN && cellTwo.getArrowType() == ArrowType.UP){
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
    //TODO: kontrollieren
    private boolean checkIfOtherAreaBetweenArrows(Point p, Point q){
        //Cells and their Areas
        Cells cellOne = cells[p.getX()][p.getY()];
        Cells cellTwo = cells[q.getX()][q.getY()];
        Cells startCell[][] = new Cells[0][];

        int areaFromCellOne = cellOne.getArea();
        int areaFromCellTwo = cellTwo.getArea();

        //same Row Cells
        if(cellOne.getPoint().getX() == cellTwo.getPoint().getX()){
            if(areaFromCellOne!=areaFromCellTwo && cellOne.getPoint().getX()<cellTwo.getPoint().getX())
            {
                for(int xStartCell = cellOne.getPoint().getX(); xStartCell < cellTwo.getPoint().getX(); xStartCell++){
                    if(cellOne.getArea() != cellOne.getArea() && cellOne.getArea() != cellTwo.getArea()){
                        int area = startCell[xStartCell][p.getY()].getArea();
                        if(area != areaFromCellOne && area != areaFromCellTwo)
                        {
                            return true;
                        }
                    }
                }
            }
            else if(areaFromCellOne != areaFromCellTwo && cellTwo.getPoint().getX() < cellOne.getPoint().getX()){
                for(int xsecondStartCell = cellTwo.getPoint().getX(); xsecondStartCell < cellOne.getPoint().getX(); xsecondStartCell++){
                    if(cellOne.getArea() != cellOne.getArea() && cellOne.getArea() != cellTwo.getArea()){
                        int area = startCell[xsecondStartCell][p.getY()].getArea();
                        if(area != areaFromCellOne && area != areaFromCellTwo)
                        {
                            return true;
                        }
                    }
                }
            }
        }
        //same Coloumn Cells // nochmal überarbeiten
        else if(areaFromCellOne != areaFromCellTwo && cellTwo.getPoint().getY() < cellOne.getPoint().getY()){
            for(int xsecondStartCell = cellTwo.getPoint().getX(); xsecondStartCell < cellOne.getPoint().getX(); xsecondStartCell++){
                if(cellOne.getArea() != cellOne.getArea() && cellOne.getArea() != cellTwo.getArea()){
                    int area = startCell[xsecondStartCell][p.getY()].getArea();
                    if(area != areaFromCellOne && area != areaFromCellTwo)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public GameField(int height, int lenght) {
       cells = new Cells[lenght][height];
    }

    public Cells[][] getCells() {
        return cells;
    }

    //TODO nachschauen was des is
    public boolean setCell (int pX, int pY, Cells pCell) {
        if (pX >= 0 && pY >= 0 && pY < this.getHeight() && pX < this.getWidth()) {
            pCell.setPoint(pX, pY);
            cells[pX][pY] = pCell;
            if (pCell.getValue() > 1) {
                pCell.setFixed(true);
            }
            return true;
        }
        return false;
    }
}
