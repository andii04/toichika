package sample.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.Logic.*;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class Controller {
    private Drawer drawer;
    private Toichika toichika;
    private boolean solved;

    @FXML
    public Text infoText;

    @FXML
    public GridPane gridPane;

    public void handleOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.F5) {
            System.out.println("Step");
            if (!solved) {
                solved = toichika.solve();
            }
            drawer.drawNextStep();
        }
        else if (keyEvent.getCode() == KeyCode.F6) {
            System.out.println("Solve");
            infoText.setText("Solving...");
            infoText.setVisible(true);
            if (!toichika.solve()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("ITs Not solveable!");
                alert.show();
            }
            drawer.drawInit();
            infoText.setVisible(false);
        }
        else if (keyEvent.getCode() == KeyCode.F8) {
            System.out.println("Close");
            System.exit(0);
        }
    }

    public void handleDragOver(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles()){
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
    }

    public void handleDragDropped(DragEvent dragEvent) {
        try{
            List<File> files = dragEvent.getDragboard().getFiles();
            FileReader fileReader = new FileReader(files.get(0));
            Object obj = new JSONParser().parse(fileReader);
            JSONObject jsonObject = (JSONObject) obj;

            GameField gameField = generateGamefield(jsonObject);
            toichika = new Toichika(gameField);
            drawer = new Drawer(gridPane, gameField.getCells(), gameField, toichika.getSteps());
            drawer.drawInit();
            infoText.setVisible(false);
        }
        catch (Exception e){
            infoText.setVisible(true);
            infoText.setText("Invalid File. Try again with another JSON, compatible with the puzzle format " + e);
        }
    }

    public GameField generateGamefield(JSONObject jsonObject){
        solved = false;
        JSONArray cells = (JSONArray) jsonObject.get("cells");

        int width = Integer.parseInt((String) jsonObject.get("width"));
        int height = Integer.parseInt((String) jsonObject.get("height"));
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
        }

        return gameField;
    }

}
