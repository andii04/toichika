package sample.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;
import sample.Logic.GameField;
import sample.Logic.Toichika;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("GameField Toichika");
        Scene mainScene = new Scene(root, 500, 500);
        //Key Binding
        mainScene.setOnKeyPressed(e -> {
            KeyCode l = e.getCode();
            switch (l) {
                case F5:
                    //stepByStepAction();
                    System.out.println("F5");
                    break;
                case F6:
                    //completeAction();
                    System.out.println("F6");
                    break;
                case F8:
                    //closeApplication();
                    System.exit(0);
                    break;
            }
        });
        primaryStage.setScene(mainScene);
        primaryStage.show();


        GameField gf = null;
        File file = new File("src/sample/gameField01.json");
        File solvedfile = new File("src/sample/gameField02_geloest.json");
        try {
            Controller con = new Controller();
            gf = con.generateGamefield(readJSONFile(solvedfile));

        }
        catch (Exception e){
            System.out.println("fsajfoisdjfiosdjfiosdjfiosdj   "+e.getMessage());
        }

        gf.printGameField();

        Toichika toichika = new Toichika();
        toichika.nextStep(gf);
    }


    public static void main(String[] args) {
        launch(args);

    }

    static JSONObject readJSONFile(File file) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(file.toPath()));
        return new JSONObject(content);
    }
}
