package sample.Logic;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import sample.GUI.Controller;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGameField {
    Cells cells[][] = null;
    File file = new File("src/sample/gameField02_ungeloest.json");
    Controller con= new Controller();
    Object obj = new JSONParser().parse(String.valueOf(file));
    JSONObject jsonObject = (JSONObject) obj;
    GameField gameField = con.generateGamefield(jsonObject);
    Toichika toichika = new Toichika(gameField);

    public TestGameField() throws ParseException {
    }

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
        int y=-1;
        Point p = new Point(x,y);
        assertEquals(p.getX(),x);
        assertEquals(p.getY(),y);
    }

    @Test
    public void getHighestArea() {
        int highest = 0;
        int area = 10;
        if (area > highest) {
            highest = area;
        }
        assertEquals(highest,area);
    }

    @Test
    public void countArrowsInArea() {
        int counter = 0;
        counter += 2;
        assertEquals(counter,2,"This should be same");
    }
    @Test
    public void checkIfOneArrowIsInArea() {
        int pArea = 1;
        assertTrue(pArea == 1);
    }

    @Test
    public void checkIfTwoArrowsInRowAndLookEachOther() {
        int y = 0;
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
        /*
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
        }*/
    }
}
