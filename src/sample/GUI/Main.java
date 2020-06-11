package sample.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;
import sample.Logic.GameField;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("GameField Toichika");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();

        GameField gf = null;
        File file = new File("src/sample/gameField01.json");
        try {
            Controller con = new Controller();
            gf = con.generateGamefield(readJSONFile(file));

        }
        catch (Exception e){
            System.out.println("fsajfoisdjfiosdjfiosdjfiosdj   "+e.getMessage());
        }

        gf.printGameField();
    }


    public static void main(String[] args) {
        launch(args);

    }

    static JSONObject readJSONFile(File file) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(file.toPath()));
        return new JSONObject(content);
    }
}
