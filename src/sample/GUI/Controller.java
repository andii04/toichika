package sample.GUI;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.json.JSONArray;
import org.json.JSONObject;
import sample.Logic.*;

public class Controller {
    private Drawer drawer;
    private Toichika toichika;
    private boolean solved;

    public void handleOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.F5) {
            System.out.println("Step");
            /*if (!solved) {
                solved = toichika.solve();
            }
            drawer.drawNextStep();*/
        }
        else if (keyEvent.getCode() == KeyCode.F6) {
            System.out.println("Solve");
            /*infoText.setText("Solving...");
            infoText.setVisible(true);
            if (!toichika.solve()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("ITs Not solveable!");
                alert.show();
            }
            drawer.drawInit();
            infoText.setVisible(false);*/
        }
        else if (keyEvent.getCode() == KeyCode.F8) {
            System.out.println("Close");
            System.exit(0);
        }
    }


    //JSON parsing
    public GameField generateGamefield(JSONObject jsonObject){
        solved = false;
        JSONArray cells = (JSONArray) jsonObject.get("cells");

        int width = Integer.parseInt((String) jsonObject.get("width"));
        System.out.println(width);
        int height = Integer.parseInt((String) jsonObject.get("height"));
        System.out.println(height);
        GameField gameField= new GameField(width, height);

        for (Object cell : cells) {
            JSONObject jsoncell = (JSONObject) cell;
            int x = Integer.parseInt ((String) jsoncell.get("x"));
            int y = Integer.parseInt ((String) jsoncell.get("y"));
            int area = Integer.parseInt((String) (jsoncell.get("area")));
            ArrowType type;
            String sType = (String) jsoncell.get("ArrowType");

            if (sType.equals("UP")) {
                type = ArrowType.UP;
            }
            else if (sType.equals("DOWN")) {
                type = ArrowType.DOWN;
            }
            else if(sType.equals("RIGHT")){
                type = ArrowType.RIGHT;
            }
            else if(sType.equals("LEFT")){
                type = ArrowType.LEFT;
            }
            else {
                type = ArrowType.EMPTY;
            }
            gameField.setCell(x, y, new Cells(type,new Point(x,y), area));

            System.out.println(x);
        }
        return gameField;
    }

}
