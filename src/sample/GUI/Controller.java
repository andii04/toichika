package sample.GUI;

import javafx.event.Event;
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
import sample.Logic.ArrowType;
import sample.Logic.Cells;
import sample.Logic.GameField;
import sample.Logic.Toichika;

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
        //int maxNumber = Integer.parseInt((String) jsonObject.get("maxNumber"));
        //gameField.setHighestNumber(maxNumber);

        for (Object cell : cells) {
            JSONObject jsoncell = (JSONObject) cell;
            int x = Integer.parseInt ((String) jsoncell.get("x"));
            int y = Integer.parseInt ((String) jsoncell.get("y"));
            int value = Integer.parseInt((String) (jsoncell.get("value")));
            ArrowType type;
            String sType = (String) jsoncell.get("type");

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

            //schauen
            gameField.setCell(x, y, new Cells(type, value));
        }

        return gameField;
    }

    @FXML public void initialise(Event evt){

    }

    /*kann weg wahrscheinlich
    @FXML public void handleF5ButtonClicked(Event evt){
        GameField pane = new GameField(10,10);
        int panewidth = pane.getWidth();
        int paneheight = pane.getHeight();
        int[][] primes = new int[100][10];
        int rows = primes.length;
        int cols = primes[0].length;
        //System.out.println(rows);
        System.out.println("high"+paneheight);
    }
    @FXML public void handleF6ButtonClicked(Event evt){
        System.out.println("F6 geklickt");
        solveToichika();
        //function solve
    }
    @FXML public void handleF7ButtonClicked(Event evt){
        System.out.println("F7 geklickt");
        System.exit(0);
        //Function close
    }
     */
    private void solveToichika(){
        System.out.println("solved");
    }
}
