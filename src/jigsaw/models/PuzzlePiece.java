/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jigsaw.models;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author WaterPlimpie
 */
public class PuzzlePiece extends ImageView {
    
    private static final int WIDTH = 300;
    private static final int HEIGHT = 150;
    private static final int OFFSET = 10;
    private static final int PANE_OFFSET_X = 40;
    private static final int PANE_OFFSET_Y = 100;

    static int count = 0;
    private int row;
    private int col;
    private final int minX;
    private final int minY;
    
    
    private final Point2D topLeft, topRight, bottomLeft, bottomRight; //these are used to detect whether the piece is placed correctly or not
    
    public PuzzlePiece(int row, int col, Image i) {
        super(i);
        this.row = row;
        this.col = col;
        minX = col * 300;
        minY = row * 150;
        
        topLeft = new Point2D(minX + OFFSET + PANE_OFFSET_X, minY + OFFSET + PANE_OFFSET_Y);
        topRight = new Point2D(minX + WIDTH - OFFSET  + PANE_OFFSET_X, minY + OFFSET + PANE_OFFSET_Y);
        bottomLeft = new Point2D(minX + OFFSET + PANE_OFFSET_X, minY + HEIGHT - OFFSET + PANE_OFFSET_Y);
        bottomRight = new Point2D(minX + WIDTH - OFFSET + PANE_OFFSET_X, minY + HEIGHT - OFFSET + PANE_OFFSET_Y);
        
        setViewport(new Rectangle2D(minX, minY, WIDTH, HEIGHT));
        setLayoutX(20 + (int)(Math.random() * ((1260 - WIDTH - 20) + 1)));
        setLayoutY(20 + (int)(Math.random() * ((780 - HEIGHT - 20) + 1)));        
    }

    
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    

    public boolean checkFit() {
        System.out.println("checking fit:");
        //check if the piece is within acceptable range,
        //if it is, then place it in it's correct position and lock it by removing it's action listeners
        //could also give it like a yellow boundary to denote it's in it's correct position
        
        //think about giving all the unplaced pieces a red bounding box and then remove it once it's placed, for a more realistic feel
        //once a piece is fixed, remove it from the arraylist, and then check if the arraylist still has elements in it, if it's empty, then game is over
//        System.out.println("Bounds being checked:");
//        System.out.println("Topleft: " + topLeft + ", TopRight: " + topRight + ", BottomLeft: " + bottomLeft + ", BottomRight: " + bottomRight);
//        System.out.println("Piece's actual location:");
//        System.out.println(this.getBoundsInParent());
        if (this.getBoundsInParent().contains(topLeft) 
                && this.getBoundsInParent().contains(topRight) 
                && this.getBoundsInParent().contains(bottomLeft) 
                && this.getBoundsInParent().contains(bottomRight)) {
            
            //remove listeners
            //placePiece();
            this.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    event.consume();
                }
                
            });
            
            return true;
            //System.out.println("fixed!");
        }
            //setFixed(true);
        return false;
    }

    
    private void placePiece() {
        //setLayoutX(minX + PANE_OFFSET_X);
        //setLayoutY(minY + PANE_OFFSET_Y);
        //relocate(minX + PANE_OFFSET_X, minY + PANE_OFFSET_Y);
        AnchorPane.setTopAnchor(this, 1.0 * minY + PANE_OFFSET_Y);
        AnchorPane.setLeftAnchor(this, 1.0 * PANE_OFFSET_X + minX);
        System.out.println("X = " + getX() + ", Y = " + getY());
    }
    
}
