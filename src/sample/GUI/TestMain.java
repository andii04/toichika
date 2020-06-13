package sample.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;

public class TestMain {
    @Test
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("GameField Toichika");
        Scene mainScene = new Scene(root, 500, 500);
        //Key Binding
        mainScene.setOnKeyPressed(e -> {
            KeyCode l = e.getCode();
            switch (l) {
                case F5:
                    //stepByStepAction();
                    System.out.println("F5 - STEP");
                    /*if(!solved){
                        solved = toichika.solve();
                    }*/
                    //drawer.drawNextStep();
                    break;
                case F6:
                    //completeAction();
                    System.out.println("F6 Solve...Solving");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("no Solution possible");
                    alert.show();
                    /*if (!toichika.solve()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Not solveable!");
                        alert.show();
                    }
                    drawer.drawInit();*/
                    break;
                case F8:
                    System.out.println("Close Application");
                    System.exit(0);
                    break;
            }
        });
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

}
