package application.game;

import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ChooseThemeBox {
	private HBox themeBox = new HBox();
    
    private String circleNotChoosen = "/application/resources/grey_circle.png";
	private String circleChoosen = "/application/resources/red_choosen.png";
	private String waterMelonTheme = "/application/resources/watermelon_theme.png";
	private String monochromTheme = "/application/resources/monokrom_theme.png";
	
	private ImageView themeImage;
	private ImageView circleImage;
	
	// 1 = watermelon, 2 = monochrom
	public static int themeChoosenCode = 1;
	
	private boolean isWaterMelon = true;
	
    public ChooseThemeBox() {
    	createThemeBox();
    }
    
	public HBox getThemeBox() {
		return themeBox;
	}
	
	List<ImageView> listCircle =  new ArrayList<ImageView>();
	
	private void createThemeBox() {
		VBox theme1 = createThemeChooser(waterMelonTheme);
		VBox theme2 = createThemeChooser(monochromTheme);
				
		themeBox.setSpacing(20);
		themeBox.setAlignment(Pos.CENTER);
		themeBox.getChildren().addAll(theme1, theme2);
	}
	
	private VBox createThemeChooser(String theme) {
		VBox newBox = new VBox();
		
		themeImage = new ImageView(theme);
		themeImage.setStyle("-border-radius: 5;");
		
		themeImage.setFitHeight(150);
		themeImage.setFitWidth(150);
		if(isWaterMelon) {
			circleImage = new ImageView(circleChoosen);
			isWaterMelon = false;
			
			themeImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					//waterMelon
					listCircle.get(0).setImage(new Image(circleChoosen));
					
					//monochrom
					listCircle.get(1).setImage(new Image(circleNotChoosen));
					
					themeChoosenCode = 1;
				}
			});
			
			circleImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					//waterMelon
					listCircle.get(0).setImage(new Image(circleChoosen));
					
					//monochrom
					listCircle.get(1).setImage(new Image(circleNotChoosen));
					
					themeChoosenCode = 1;
				}
			});
		}
		else {
			circleImage = new ImageView(circleNotChoosen);
			
			themeImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					//monochrom
					listCircle.get(1).setImage(new Image(circleChoosen));
					
					//waterMelon
					listCircle.get(0).setImage(new Image(circleNotChoosen));
					
					themeChoosenCode = 2;
				}
			});
			
			circleImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					//monochrom
					listCircle.get(1).setImage(new Image(circleChoosen));
					
					//waterMelon
					listCircle.get(0).setImage(new Image(circleNotChoosen));
					
					themeChoosenCode = 2;
				}
			});
		}
		listCircle.add(circleImage);
		
		newBox.getChildren().addAll(themeImage, circleImage);
		newBox.setAlignment(Pos.CENTER);
		newBox.setSpacing(17);
		return newBox;
	}
	
	
	
}
