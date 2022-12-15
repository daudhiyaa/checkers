package application.game;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ThemeChooserBox {
	private HBox themeBox = new HBox();
    
    private String circleNotChoosen = "/application/resources/grey_circle.png";
	private String circleChoosen = "/application/resources/red_choosen.png";
	private String waterMelonTheme = "/application/resources/watermelon_theme.png";
	private String monochromeTheme = "/application/resources/monokrom_theme.png";
	private String coralBrownTheme = "/application/resources/coralbrown_theme.png";

	private Image themeImage;
	private ImageView waterMelonChooser, monochromeChooser, coralChooser;
	public static int themeChoosenCode = 1;
	
    public ThemeChooserBox() {
    	createThemeBox();
    }
    
	public HBox getThemeBox() {
		return themeBox;
	}
	
	private void createThemeBox() {
		VBox theme1 = createThemeChooser();
		VBox theme2 = createThemeChooser();
		VBox theme3 = createThemeChooser();
		
		themeBox.setSpacing(20);
		themeBox.setAlignment(Pos.CENTER);
		themeBox.getChildren().addAll(theme1, theme2, theme3);
	}
	
	private Text createText(int size, String title) {
		Text txt = new Text(title);
		try {
			txt.setFont(Font.loadFont(new FileInputStream(ThemeChooserScene.FONT_PATH), size));
		}
		catch (FileNotFoundException e) {
			txt.setFont(Font.font("Verdana", size));
		}
		txt.setFill(Color.WHITE);
		return txt;
	}
	
	private VBox createThemeChooser() {
		VBox newBox = new VBox();
		
		if(themeChoosenCode == 1) 
			themeImage = new Image(waterMelonTheme);
		else if(themeChoosenCode == 2)
			themeImage = new Image(monochromeTheme);
		else if(themeChoosenCode == 3)
			themeImage = new Image(coralBrownTheme);
		
		
		Rectangle clipImage = new Rectangle(themeImage.getWidth(), themeImage.getHeight());
		clipImage.setFill(new ImagePattern(themeImage));
		clipImage.setArcWidth(20);
		clipImage.setArcHeight(20);
		
		if(themeChoosenCode == 1) {
			Text titleTheme = createText(15, "WATER-MELON");
			waterMelonChooser = new ImageView(circleChoosen);
			waterMelonChooser.setOnMouseClicked(e -> handleClickWaterMelon());
			newBox.getChildren().addAll(clipImage, titleTheme, waterMelonChooser);
			themeChoosenCode = 2;
		}
		else if(themeChoosenCode == 2) {
			Text titleTheme = createText(15, "MONOCHROME");
			monochromeChooser = new ImageView(circleNotChoosen);
			monochromeChooser.setOnMouseClicked(e -> handleClickMonochrome());
			newBox.getChildren().addAll(clipImage, titleTheme, monochromeChooser);
			themeChoosenCode = 3;
		}
		else if(themeChoosenCode == 3) {
			Text titleTheme = createText(15, "CORAL-BROWN");
			coralChooser = new ImageView(circleNotChoosen);
			coralChooser.setOnMouseClicked(e -> handleClickCoral());
			newBox.getChildren().addAll(clipImage, titleTheme, coralChooser);
			themeChoosenCode = 1;
		}
		
		newBox.setAlignment(Pos.CENTER);
		newBox.setSpacing(17);
		return newBox;
	}
		
	public void handleClickWaterMelon() {
		waterMelonChooser.setImage(new Image(circleChoosen));
		monochromeChooser.setImage(new Image(circleNotChoosen));
		coralChooser.setImage(new Image(circleNotChoosen));
		themeChoosenCode = 1;
	}

	public void handleClickMonochrome() {
		waterMelonChooser.setImage(new Image(circleNotChoosen));
		monochromeChooser.setImage(new Image(circleChoosen));
		coralChooser.setImage(new Image(circleNotChoosen));
		themeChoosenCode = 2;
	}
	
	public void handleClickCoral() {
		waterMelonChooser.setImage(new Image(circleNotChoosen));
		monochromeChooser.setImage(new Image(circleNotChoosen));
		coralChooser.setImage(new Image(circleChoosen));
		themeChoosenCode = 3;
	}
}
