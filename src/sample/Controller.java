package sample;

import com.sun.media.jfxmediaimpl.platform.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class Controller {
    String JSON = "---------";
    int sqrtgrid = (int) Math.sqrt(JSON.length());
    GameField pane = new GameField();




    @FXML public void initialise(Event evt){

    }

    @FXML public void handleF5ButtonClicked(Event evt){
        System.out.println("F5 geklickt   " +sqrtgrid);
        int[][] primes = new int[100][10];
        int rows = primes.length;
        int cols = primes[0].length;
        System.out.println(rows);
        //function step
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
    private void solveToichika(){
        System.out.println("solved");
    }

}
