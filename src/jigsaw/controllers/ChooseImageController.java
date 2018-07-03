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
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author WaterPlimpie
 */
public class ChooseImageController implements Initializable {
    @FXML
    private Button exitBtn;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        exitBtn.setOnAction(e -> {
            System.exit(0);
        });
        
        img1.setOnMouseClicked(e -> {
            startGame(1);
        });
        img2.setOnMouseClicked(e -> {
            startGame(2);
        });
        img3.setOnMouseClicked(e -> {
            startGame(3);
        });
        img4.setOnMouseClicked(e -> {
            startGame(4);
        });
    }
    
    public void startGame(int imgID) {
         Stage newStage = new Stage();
                
            Parent root = null;
            FXMLLoader loader = null;
            GameController controller = null;
            try {
                loader = new FXMLLoader(getClass().getResource("/jigsaw/views/Game.fxml"));
                root = loader.load();
                controller = loader.getController();                
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            Scene scene = new Scene(root);
            controller.setImg(imgID);
            controller.init();
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setScene(scene);
            newStage.show();

            Stage currentStage = (Stage)img1.getScene().getWindow();
            currentStage.close();
    }
    
}
