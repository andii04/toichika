package sample.Logic;

import java.util.ArrayList;

public class GameField {
    private Cells[][] cells;

    public GameField(int height, int lenght) {
        cells = new Cells[lenght][height];
    }

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

    //rdy
    public boolean checkIfOneArrowIsInArea(int pArea) {
        return 1 == countArrowsInArea(pArea);
    }

    //rdy
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

    //rdy
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

    //rdy
    public void setArrowInCell(Point p, ArrowType pArrow) {
        cells[p.getX()][p.getY()].setArrowType(pArrow);
    }

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

    //soltle passen
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

    //sollte passen
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

    //sollte passen
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

    public Cells[][] getCells() {
        return cells;
    }

    public void setCell(int x, int y, Cells cells) {
        this.cells[x][y] = cells;
    }
}
