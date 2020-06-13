package sample.Logic;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGameField {
    @Test
    public void getWidth() {
        GameField exampleField = new GameField(10,10);
        assertEquals(10,exampleField.getWidth());
    }
    @Test
    public void getHeight() {
        GameField exampleField = new GameField(10,10);
        assertEquals(10,exampleField.getHeight());
    }
    @Test
    public void isPointFree(){
        int x=0;
        int y=0;
        Point p = new Point(x,y);
    }
    /*@Test
    public void checkIfOneArrowIsInArea(int pArea) {
        return 1 == countArrowsInArea(pArea);
    }
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
    }*/

}
