/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jigsaw;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author WaterPlimpie
 */
public class Jigsaw extends Application {
    
    @Override
    public void start(Stage stage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/jigsaw/views/HomePage.fxml"));
        } catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        
        Scene scene = new Scene(root);
        
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Jigsaw Puzzle");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
