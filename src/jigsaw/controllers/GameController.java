/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jigsaw.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jigsaw.models.PuzzlePiece;

/**
 * FXML Controller class
 *
 * @author WaterPlimpie
 */
public class GameController implements Initializable {
    @FXML
    Button playAgainBtn;
    @FXML
    Rectangle rect;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button exitBtn;
    @FXML
    private ImageView finalImg;
    @FXML
    private ImageView hint;
    private int imgID;
    @FXML
    private Label goodLbl;
    private double zeroX;
    private double zeroY;
    private int fitCnt = 0;
    private List<PuzzlePiece> pieces;
    
    private double originalX, originalY, originalTranslateX, originalTranslateY;
    
    public void setImg(int id) {
        imgID = id;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        pieces = new ArrayList<>();
        
        exitBtn.setOnAction(e -> {
            System.exit(0);
        });
        
        playAgainBtn.setOnAction(e -> {
            Stage newStage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/jigsaw/views/ChooseImage.fxml"));
            } catch (IOException ex) {
                System.out.println("Exception: " +  ex.getMessage());
            }

            Scene scene = new Scene(root);

            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setScene(scene);
            newStage.show();
            
            Stage currentStage = (Stage)playAgainBtn.getScene().getWindow();
            currentStage.close();
        });
    }
    
    public void init() {
        hint.setImage(new Image("/jigsaw/resources/puzzles/pic" + imgID + ".jpg"));
        finalImg.setImage(new Image("/jigsaw/resources/puzzles/pic" + imgID + ".jpg"));

        hint.setOnMouseClicked(e -> {
            Stage hintStage = new Stage();
            Pane pane = new Pane();
            Scene hintScene = new Scene(pane);
            ImageView img2 = new ImageView("/jigsaw/resources/puzzles/pic" + imgID + ".jpg");
            img2.setFitWidth(1200);
            pane.getChildren().add(img2);
            hintStage.setScene(hintScene);
            hintStage.show();
        });
        
        //System.out.println("X: " + rect.getLayoutX() + ", Y: " + rect.getLayoutY());
        zeroX = rect.getLayoutX();
        zeroY = rect.getLayoutY();
        System.out.println("in controller");
        
        createPuzzlePieces();
        
        for (PuzzlePiece p : pieces) {
            //add mouse press listener then mouse drag
            p.setOnMousePressed(e -> {
                originalX = e.getSceneX();
                originalY = e.getSceneY();
                
                originalTranslateX = ((PuzzlePiece) (e.getSource())).getTranslateX();
                originalTranslateY = ((PuzzlePiece) (e.getSource())).getTranslateY();
            });
            
            p.setOnMouseDragged(e -> {
                double offsetX = e.getSceneX() - originalX;
                double offsetY = e.getSceneY() - originalY;
                double newTranslateX = originalTranslateX + offsetX;
                double newTranslateY = originalTranslateY + offsetY;
                
                ((PuzzlePiece) (e.getSource())).setTranslateX(newTranslateX);
                ((PuzzlePiece) (e.getSource())).setTranslateY(newTranslateY);
            });
            
            p.setOnMouseReleased(e -> {
                PuzzlePiece temp = (PuzzlePiece) (e.getSource());
                if(temp.checkFit()) {
                    //anchor.getChildren().remove(temp);
                    //finishedGrid.add(temp, temp.getCol(), temp.getRow());
                    System.out.println("minX = " + temp.getMinX());
                    //temp.setX(temp.getMinX());
                    //temp.setY(temp.getMinY());
                    System.out.println("Layout: " + temp.getLayoutX() + " " + temp.getLayoutY());
                    System.out.println("Normal: " + temp.getX() + " " + temp.getY());
                    System.out.println("Scene: " + temp.getBoundsInParent());
                    ++fitCnt;
                    if (fitCnt == 16) {
                        for (PuzzlePiece pc : pieces) {
                            anchor.getChildren().remove(pc);
                            
                        }
                        
                        finalImg.setVisible(true);
                        goodLbl.setVisible(true);
                        playAgainBtn.setVisible(true);
                    }
                    System.out.println(fitCnt);
                }
            });
        }
    }

    private void createPuzzlePieces() {
        Image im =  new Image("/jigsaw/resources/puzzles/pic" + imgID + ".jpg");
        
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                PuzzlePiece p = new PuzzlePiece(i, j, im);
                pieces.add(p);
                anchor.getChildren().add(p);
            }
        }
        
        System.out.println("pieces : " + pieces.size());
    }
    
    
    
}
