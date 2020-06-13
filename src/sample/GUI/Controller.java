package sample.GUI;


import javafx.fxml.FXML;
import javafx.scene.input.DragEvent;
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
    Cells [][]cells;
    @FXML
    public GridPane gridPane;
    @FXML
    public Text infoText;

    /*static JSONObject readJSONFile(File file) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(file.toPath()));
        return new JSONObject(content);
    }*/
    //Function to handle DragOverFile
    public void handleDragOver(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles()){
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
    }
    //Function for handle DragDroppedFile
    public void handleDragDropped(DragEvent dragEvent) {
        try{
            List<File> files = dragEvent.getDragboard().getFiles();
            FileReader fileReader = new FileReader(files.get(0));
            Object obj = new JSONParser().parse(fileReader);
            JSONObject jsonObject = (JSONObject) obj;
            GameField gameField = generateGamefield(jsonObject);
            toichika = new Toichika(gameField);
            toichika.solve();
            /*for(int x = 0; x<gameField.getWidth();x++){
                for(int y = 0;y < gameField.getHeight();y++){
                    cells[x][y].setArrowType(jsonObject.get());
                }
            }*/
            //drawer = new Drawer(gridPane, gameField.getCells(), gameField, toichika.getSteps());
            //drawer.drawInit();
            //infoText.setVisible(false);
        }
        catch (Exception e){
            infoText.setVisible(true);
            infoText.setText("Invalid File. Try again with another JSON file" + e);
        }
    }
    //JSON parsing function
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

            //System.out.print(x);
            //System.out.println("TEsst");
        }
        return gameField;
    }

}
