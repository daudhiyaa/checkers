package application.model;

import static application.game.GameBase.TILE_SIZE;

import application.game.ThemeChooserBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Piece extends StackPane {

    protected PieceType type;
    private boolean isKing = false;

    private double mouseX, mouseY;
    private double oldX, oldY;

    public PieceType getType() {
        return type;
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }
    
    public boolean getIsKing() {
    	return isKing;
    }
    
    public void changeToKing() {
    	this.isKing = true;
    	
    	Ellipse kingMark = new Ellipse(TILE_SIZE * 0.15, TILE_SIZE * 0.15);

        
        if(ThemeChooserBox.themeChoosenCode == 1) {
        	// watermelon
        	kingMark.setFill(Color.BLACK);
        }
        else if(ThemeChooserBox.themeChoosenCode == 2) {
        	// monochrom
        	kingMark.setFill(type == PieceType.RED
                    ? Color.valueOf("#ffffff") : Color.valueOf("#000000"));
        }
        else if(ThemeChooserBox.themeChoosenCode == 3) {
        	// coral
        	kingMark.setFill(type == PieceType.RED
                    ? Color.valueOf("#ffffff") : Color.valueOf("#000000"));
        }

        kingMark.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        kingMark.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2);
        
    	this.getChildren().add(kingMark);
    	
    }

    public Piece(PieceType type, int x, int y) {
        this.type = type;

        move(x, y);

        Ellipse bg = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        bg.setFill(Color.BLACK);

        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(TILE_SIZE * 0.03);

        bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.07);

        Ellipse ellipse = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        
        if(ThemeChooserBox.themeChoosenCode == 1) {
        	// watermelon
        	ellipse.setFill(type == PieceType.RED
                    ? Color.valueOf("#c40003") : Color.valueOf("#fff9f4"));
        	ellipse.setStroke(Color.BLACK);
        }
        else if(ThemeChooserBox.themeChoosenCode == 2) {
        	// monochrom
        	ellipse.setFill(type == PieceType.RED
                    ? Color.valueOf("#000000") : Color.valueOf("#fff9f4"));
        	
        	if(type == PieceType.RED) {
        		ellipse.setStroke(Color.WHITE);
        		
        		bg.setFill(Color.WHITE);
                bg.setStroke(Color.WHITE);
        	}
        }
        else if(ThemeChooserBox.themeChoosenCode == 3) {
        	// coral
        	ellipse.setFill(type == PieceType.RED
                    ? Color.BLACK : Color.WHITE);
        	
        	if(type == PieceType.RED) {
        		ellipse.setStroke(Color.WHITE);
        		
        		bg.setFill(Color.WHITE);
                bg.setStroke(Color.WHITE);
        	}
        	else ellipse.setStroke(Color.BLACK);
        }

        ellipse.setStrokeWidth(TILE_SIZE * 0.03);
        ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2);

        getChildren().addAll(bg, ellipse);

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
        });
    }

    public void move(int x, int y) {
        oldX = x * TILE_SIZE;
        oldY = y * TILE_SIZE;
        relocate(oldX, oldY);
    }

    public void abortMove() {
        relocate(oldX, oldY);
    }
}