package sample.Logic;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class GameField {
    int lenght = 10;
    int height = 15;
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
        return 1 == countArrowsInArea(pArea);
    }
    //rdy
    private int countArrowsInArea(int pArea){
        int counter = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                Cells currentCell = cells[i][j];
                if(currentCell.getArrowType() != null && currentCell.getArrowType() != ArrowType.EMPTY && currentCell.getArea() == pArea){
                    counter++;
                }
            }
        }
        return counter;
    }

    //rdy
    private void setArrowInCell(Point p, ArrowType pArrow){
        cells[p.getX()][p.getY()].setArrowType(pArrow);
    }

    private boolean checkIfTwoArrowsInRowAndLookEachOther(Point p){
        Cells currentcell;
        int counter = 0;
        ArrayList<Cells> celllistInRow = new ArrayList<>();
        for(int i = 0; i< cells.length;i++){
            currentcell = cells[i][p.getY()];
            if(currentcell.getArrowType().equals("LEFT") || currentcell.getArrowType().equals("RIGHT")){
                celllistInRow.add(currentcell);
                counter++;
            }
        }
        if(counter == 2 )
        {
            Cells cellFirst = celllistInRow.get(0);
            Cells cellSecond = celllistInRow.get(1);
            if(cellFirst.getPoint().getY() < cellSecond.getPoint().getY() && cellFirst.getArrowType().equals("RIGHT") && cellSecond.getArrowType().equals("LEFT")){
                return true;
            }
            else if(cellFirst.getPoint().getY() < cellSecond.getPoint().getY() && cellFirst.getArrowType().equals("LEFT") && cellSecond.getArrowType().equals("RIGHT")){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    private boolean checkIfTwoArrowsInColoumnAndLookEachOther(Point p){
        Cells currentcell;
        int counter = 0;
        ArrayList<Cells> celllistInColoumn = new ArrayList<>();

        //kontrollieren ob cells.length passt
        for(int i = 0; i< cells[p.getX()].length;i++){
            currentcell = cells[p.getX()][i];
            if(currentcell.getArrowType().equals("UP") || currentcell.getArrowType().equals("DOWN")){
                celllistInColoumn.add(currentcell);
                counter++;
            }
        }
        if(counter == 2)
        {
            Cells cellFirst = celllistInColoumn.get(0);
            Cells cellSecond = celllistInColoumn.get(1);
            if(cellFirst.getPoint().getX() < cellSecond.getPoint().getX() && cellFirst.getArrowType().equals("DOWN") && cellSecond.getArrowType().equals("UP")){
                return true;
            }
            else if(cellFirst.getPoint().getX() < cellSecond.getPoint().getX() && cellFirst.getArrowType().equals("UP") && cellSecond.getArrowType().equals("DOWN")){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    //TODO: ANPASSEN mit FUNKTIONEN oberhalb check COLOUMN no Arrow between two Arrows
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
            pCell.setPoint(new Point(pX, pY));
            cells[pX][pY] = pCell;
            if (pCell.getValue() > 1) {
                pCell.setFixed(true);
            }
            return true;
        }
        return false;
    }
}
