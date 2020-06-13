package sample.Logic;

import java.util.ArrayList;

public class GameField {
    private Cells[][] cells;

    public GameField(int height, int lenght) {
        cells = new Cells[lenght][height];
    }
    //function to get widht of 2DCellsArray
    public int getWidth() {
        return cells.length;
    }
    //function to get height of 2DCellsArray
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
    //Function to check if point is free
    public boolean isPointFree (int pX, int pY) {
        if (pX >= 0 && pY >= 0 && pY < this.getHeight() && pX < this.getWidth()) {
            return cells[pX][pY] == null || !cells[pX][pY].isFixed();
        }
        return false;
    }
    //function to print Gamefield
    public void printGameField() {
        for (int i = 0; i < cells.length; i++) {
            for (int k = 0; k < cells[i].length; k++) {
                System.out.print(cells[i][k].getArrowType().toString() + " ");
            }
            System.out.println();
        }
    }
    public boolean checkIfCellIsNotSet(Point p) {
        if (p.getX() >= 0 && p.getY() >= 0 && p.getY() < this.getHeight() && p.getX() < this.getWidth()) {
            return cells[p.getX()][p.getY()].getArrowType() == null; //|| !cells[pX][pY].isFixed();
        }
        return false;
    }
    // Final Checking if exact one Arrow is in each Area
    public boolean checkIfOneArrowIsInArea(int pArea) {
        return 1 == countArrowsInArea(pArea);
    }
    // Final Check to Count all Arrows in one Area
    public int countArrowsInArea(int pArea) {
        int counter = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                Cells currentCell = cells[i][j];
                if (currentCell.getArrowType() != null && currentCell.getArrowType() != ArrowType.EMPTY && currentCell.getArea() == pArea) {
                    counter++;
                }
            }
        }
        return counter;
    }
    //Final Check a function to get the highest Area of the GameField
    public int getHighestArea() {
        int highest = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int k = 0; k < cells[i].length; k++) {
                int area = cells[i][k].getArea();
                if (area > highest) {
                    highest = area;
                }
            }
        }
        return highest;
    }
    //for setting an Arrow in a Cell
    public void setArrowInCell(Point p, ArrowType pArrow) {
        cells[p.getX()][p.getY()].setArrowType(pArrow);
    }
    // final Check function for check that two Arrows in a Row look to each other
    public boolean checkIfTwoArrowsInRowAndLookEachOther(int y) {
        Cells currentcell;
        int counter = 0;
        ArrayList<Cells> celllistInRow = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            currentcell = cells[i][y];
            if (currentcell.getArrowType().equals("LEFT") || currentcell.getArrowType().equals("RIGHT")) {
                celllistInRow.add(currentcell);
                counter++;
            }
        }
        for (int i = 0; i < celllistInRow.size(); i += 2) {
            Cells cellFirst = celllistInRow.get(i);
            Cells cellSecond = celllistInRow.get(i + 1);
            if (!(cellFirst.getPoint().getY() < cellSecond.getPoint().getY() && cellFirst.getArrowType().equals("RIGHT") && cellSecond.getArrowType().equals("LEFT"))) {
                return false;
            } else if (!(cellFirst.getPoint().getY() < cellSecond.getPoint().getY() && cellFirst.getArrowType().equals("LEFT") && cellSecond.getArrowType().equals("RIGHT"))) {
                return false;
            } else if (!(checkIfNoArrowBetweenInRow(cellFirst.getPoint(), cellSecond.getPoint()))) {
                return false;
            }else if(!(checkIfOtherAreaBetweenArrows(cellFirst.getPoint(),cellSecond.getPoint()))){
                return false;
            }
        }
        return true;
    }
    // final Check function for check that two Arrows in a Coloumn look to each other
    public boolean checkIfTwoArrowsInColoumnAndLookEachOther(int x) {
        Cells currentcell;
        int counter = 0;
        ArrayList<Cells> celllistInColoumn = new ArrayList<>();
        for (int i = 0; i < cells[x].length; i++) {
            currentcell = cells[x][i];
            if (currentcell.getArrowType().equals("UP") || currentcell.getArrowType().equals("DOWN")) {
                celllistInColoumn.add(currentcell);
                counter++;
            }
        }
        for (int k = 0; k < celllistInColoumn.size()-1; k += 2) {
            Cells cellFirst = celllistInColoumn.get(k);
            Cells cellSecond = celllistInColoumn.get(k + 1);
            if (cellFirst.getPoint().getX() < cellSecond.getPoint().getX() && cellFirst.getArrowType().equals("DOWN") && cellSecond.getArrowType().equals("UP")) {
                return false;
            } else if (cellFirst.getPoint().getX() > cellSecond.getPoint().getX() && cellFirst.getArrowType().equals("UP") && cellSecond.getArrowType().equals("DOWN")) {
                return false;
            } else if (!(checkIfNoArrowBetweenInColoumn(cellFirst.getPoint(), cellSecond.getPoint()))) {
                return false;
            }
        }
        return true;
    }
    // final Check function for check that no Arrows are between two Arrows in a Coloumn they look to each other
    public boolean checkIfNoArrowBetweenInColoumn(Point p, Point q) {
        Cells cellOne = cells[p.getX()][p.getY()];
        Cells cellTwo = cells[q.getX()][q.getY()];
        for (int i = cellOne.getPoint().getX(); i < cellTwo.getPoint().getX(); i++) {
            if (cellOne.getArrowType().equals("DOWN") && cellTwo.getArrowType().equals("UP")) {
                if (cells[i][cellTwo.getPoint().getY()].equals(ArrowType.EMPTY)) {
                    return true;
                }
            } else if (cellOne.getArrowType().equals("UP") && cellTwo.getArrowType().equals("DOWN")) {
                if (cells[i][cellOne.getPoint().getY()].equals(ArrowType.EMPTY)) {
                    return true;
                }
            }
        }

        return false;
    }
    // final Check function for check that no Arrows are between two Arrows in a Row they look to each other
    public boolean checkIfNoArrowBetweenInRow(Point p, Point q) {
        Cells cellOne = cells[p.getX()][p.getY()];
        Cells cellTwo = cells[q.getX()][q.getY()];
        for (int i = cellOne.getPoint().getY(); i < cellTwo.getPoint().getY(); i++) {
            if (cellOne.getArrowType().equals("LEFT") && cellTwo.getArrowType().equals("RIGHT")) {
                if (cells[i][cellTwo.getPoint().getY()].equals(ArrowType.EMPTY)) {
                    return true;
                }
            } else if (cellOne.getArrowType().equals("RIGHT") && cellTwo.getArrowType().equals("LEFT")) {
                if (cells[i][cellOne.getPoint().getY()].equals(ArrowType.EMPTY)) {
                    return true;
                }
            }
        }
        return false;
    }
    //final Check function if another Area is between to Arrows they look each other
    public boolean checkIfOtherAreaBetweenArrows(Point p, Point q) {
        //Cells and their Areas
        Cells cellOne = cells[p.getX()][p.getY()];
        Cells cellTwo = cells[q.getX()][q.getY()];
        Cells[][] startCell = new Cells[0][];

        int areaFromCellOne = cellOne.getArea();
        int areaFromCellTwo = cellTwo.getArea();

        //same Row Cells
        if (cellOne.getPoint().getX() == cellTwo.getPoint().getX()) {
            if (areaFromCellOne != areaFromCellTwo && cellOne.getPoint().getX() < cellTwo.getPoint().getX()) {
                for (int xStartCell = cellOne.getPoint().getX(); xStartCell < cellTwo.getPoint().getX(); xStartCell++) {
                    int area = startCell[xStartCell][p.getY()].getArea();
                    if (area != areaFromCellOne && area != areaFromCellTwo) {
                        return true;
                    }
                }
            }
            //evtl unnötig
            else if (areaFromCellOne != areaFromCellTwo && cellTwo.getPoint().getX() < cellOne.getPoint().getX()) {
                for (int xsecondStartCell = cellTwo.getPoint().getX(); xsecondStartCell < cellOne.getPoint().getX(); xsecondStartCell++) {
                        int area = startCell[xsecondStartCell][p.getY()].getArea();
                        if (area != areaFromCellOne && area != areaFromCellTwo) {
                            return true;
                        }
                }
            }
        }
        //same Coloumn Cells // nochmal überarbeiten
        else if (areaFromCellOne != areaFromCellTwo && cellTwo.getPoint().getY() < cellOne.getPoint().getY()) {
            for (int xsecondStartCell = cellTwo.getPoint().getX(); xsecondStartCell < cellOne.getPoint().getX(); xsecondStartCell++) {
                    int area = startCell[xsecondStartCell][p.getY()].getArea();
                    if (area != areaFromCellOne && area != areaFromCellTwo) {
                        return true;
                    }
            }
        }
        else if (areaFromCellOne != areaFromCellTwo && cellTwo.getPoint().getY() > cellOne.getPoint().getY()) {
            for (int xsecondStartCell = cellOne.getPoint().getX(); xsecondStartCell < cellTwo.getPoint().getX(); xsecondStartCell++) {
                    int area = startCell[xsecondStartCell][p.getY()].getArea();
                    if (area != areaFromCellOne && area != areaFromCellTwo) {
                        return true;
                    }
            }
        }
        return false;
    }
    //getting all Cells
    public Cells[][] getCells() {
        return cells;
    }
    //set Cells
    public boolean setCell(int x, int y, Cells pCells) {
        if (x >= 0 && y >= 0 && y < this.getHeight() && x < this.getWidth()) {
            pCells.setLocation(x, y);
            cells[x][y] = pCells;
            if (pCells.getArrowType().equals(ArrowType.EMPTY)) {
                pCells.setFixxed(true);
            }
            return true;
        }
        return false;
        //this.cells[x][y] = cells;
    }
    //check if Border is on Left Side
    private boolean isBorderOnLeft (Point pPoint) {
        return !(pPoint.getX() - 1 >= 0);
    }
    //check if Border is on Right Side
    private boolean isBorderOnRight (Point pPoint) {
        return !(pPoint.getX() + 1 < getWidth());
    }
    //check if Border is on top Side
    private boolean isBorderOnTop (Point pPoint) {
        return !(pPoint.getY() - 1 >= 0);
    }
    //check if Border is on down Side
    private boolean isBorderOnDown (Point pPoint) {
        return !(pPoint.getY()+1 < getHeight());
    }
    //set cellvalue and check
    public boolean setCellValueAndCheck(int x, int y, int area, ArrowType atype) {
        if (x >= 0 && y >= 0 && y < this.getHeight() && x < this.getWidth()) {
            Cells prevCell = cells[x][y];
            Cells newCell;
            if (prevCell == null) {
                newCell = new Cells(atype,new Point(x,y),area);
                //newCell.setLocation(x, y);
                cells[x][y] = newCell;
            }
            else {
                newCell = new Cells(prevCell.getArrowType(),new Point(x,y), area);
                //newCell.setLocation(x, y);
                cells[x][y] = newCell;
            }

            /*if (!isSnakeValidOrSolvable(newCell.getLocation())) {
                cells[x][y] = prevCell;
                return false;
            }*/
            /*
            Cell[] neighbours = getNeighbours(new Point (x, y));
            for (int i = 0; i < neighbours.length; i++) {
                if (neighbours[i] != null) {
                    if (!isSnakeValidOrSolvable(neighbours[i].getLocation())) {
                        cells[x][y] = prevCell;
                        return false;
                    }
                }
            }*/
            return true;
        }
        return false;
    }
    //for removing a Cell
    public void removeCell(int x, int y) {
    }
}
