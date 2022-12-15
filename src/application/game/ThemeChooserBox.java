package application.game;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ThemeChooserBox {
	private HBox themeBox = new HBox();
    
    private String circleNotChoosen = "/application/resources/grey_circle.png";
	private String circleChoosen = "/application/resources/red_choosen.png";
	private String waterMelonTheme = "/application/resources/watermelon_theme.png";
	private String coralBrownTheme = "/application/resources/coralbrown_theme.png";
	
	private boolean isWaterMelon = true;
	private Image themeImage;
	private ImageView waterMelonChooser, coralChooser;
	
    public ThemeChooserBox() {
    	createThemeBox();
    }
    
	public HBox getThemeBox() {
		return themeBox;
	}
	
	private void createThemeBox() {
		VBox theme1 = createThemeChooser();
		VBox theme2 = createThemeChooser();
		
		themeBox.setSpacing(20);
		themeBox.setAlignment(Pos.CENTER);
		themeBox.getChildren().addAll(theme1, theme2);
	}
	
	private VBox createThemeChooser() {
		VBox newBox = new VBox();
		
		themeImage = (isWaterMelon ? 
				new Image(waterMelonTheme) : new Image(coralBrownTheme));
		Rectangle clipImage = new Rectangle(
				themeImage.getWidth(), themeImage.getHeight());
		clipImage.setFill(new ImagePattern(themeImage));
		clipImage.setArcWidth(20);
		clipImage.setArcHeight(20);
		
		if(isWaterMelon) {
			waterMelonChooser = new ImageView(circleChoosen);
			waterMelonChooser.setOnMouseClicked(e -> handleClickWaterMelon());
			newBox.getChildren().addAll(clipImage, waterMelonChooser);
		}
		else {
			coralChooser = new ImageView(circleNotChoosen);
			coralChooser.setOnMouseClicked(e -> handleClickCoral());
			newBox.getChildren().addAll(clipImage, coralChooser);
		}
		
		newBox.setAlignment(Pos.CENTER);
		newBox.setSpacing(17);
		isWaterMelon = false;
		return newBox;
	}
		
	public void handleClickWaterMelon() {
		waterMelonChooser.setImage(new Image(circleChoosen));
		coralChooser.setImage(new Image(circleNotChoosen));
	}

	public void handleClickCoral() {
		waterMelonChooser.setImage(new Image(circleNotChoosen));
		coralChooser.setImage(new Image(circleChoosen));
	}
}
