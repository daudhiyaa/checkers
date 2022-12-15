package application.model;

import static application.game.GameBase.TILE_SIZE;

import application.game.ThemeChooserBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private Piece piece;

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Tile(boolean light, int x, int y) {
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);

        relocate(x * TILE_SIZE, y * TILE_SIZE);
        
        if(ThemeChooserBox.themeChoosenCode == 1) {
        	// watermelon
        	setFill(light ? Color.valueOf("#feb") : Color.valueOf("#582"));
        }
        else if(ThemeChooserBox.themeChoosenCode == 2) {
        	// monochrom
        	setFill(light ? Color.valueOf("#808080") : Color.valueOf("#3b3b3b"));
        }
        else if(ThemeChooserBox.themeChoosenCode == 3) {
        	// coral
        	setFill(light ? Color.WHITE : Color.CORAL);
        }
    }
}