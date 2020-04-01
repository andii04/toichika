package sample;

import com.sun.media.jfxmediaimpl.platform.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class Controller {
    @FXML
    Button f5;
    @FXML
    Button f6;
    @FXML
    Button f7;
    @FXML public void handleF5ButtonClicked(Event evt){
        System.out.println("F5 geklickt");
        //function step
    }
    @FXML public void handleF6ButtonClicked(Event evt){
        System.out.println("F6 geklickt");
        //function solve
    }
    @FXML public void handleF7ButtonClicked(Event evt){
        System.out.println("F7 geklickt");
        //Function close
    }

}
