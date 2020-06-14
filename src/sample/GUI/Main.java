package sample.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sample.Logic.Toichika;

public class Main extends Application {

    boolean started = false;
    private Drawer drawer;
    private Toichika toichika;
    private boolean solved;
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
                    System.out.println("F5 - STEP");
                    break;
                case F6:
                    System.out.println("F6 Solve...Solving");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Solution not possible");
                    alert.show();
                    break;
                case F8:
                    System.out.println("Closing App");
                    System.exit(0);
                    break;
            }
        });
        primaryStage.setScene(mainScene);
        primaryStage.show();
        /*GameField gf = null;
        File file = new File("src/sample/gameField01_mitFehlern_12AreaFehlt.json");
        File solvedfile = new File("src/sample/gameField02_geloest.json");
        try {
            Controller con = new Controller();
            //gf = con.generateGamefield(readJSONFile(solvedfile));
        }
        catch (Exception e){
            System.out.println("!!! Exception:  "+e.getMessage());
        }
        gf.printGameField();
        Toichika toichika = new Toichika();
        toichika.nextStep(gf);*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}
