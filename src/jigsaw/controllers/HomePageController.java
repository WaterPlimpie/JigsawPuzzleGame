/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jigsaw.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author WaterPlimpie
 */
public class HomePageController implements Initializable {
    @FXML
    private TextField nameTF;
    @FXML
    private Button playBtn;
    @FXML
    private Button exitBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        exitBtn.setOnAction(e -> {
            System.exit(0);
        });
        
        playBtn.setOnAction(e -> {
            Stage newStage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/jigsaw/views/ChooseImage.fxml"));
            } catch (IOException ex) {
                System.out.println("Exception: " +  ex.getMessage());
            }

            Scene scene = new Scene(root);

            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setTitle("Academy Master");
            newStage.setScene(scene);
            newStage.show();
            
            Stage currentStage = (Stage)playBtn.getScene().getWindow();
            currentStage.close();
        });
        
    }    
    
}
