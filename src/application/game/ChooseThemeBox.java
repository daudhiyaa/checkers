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
	private boolean isCircleChoosen;
	
	private ImageView themeImage;
	private ImageView circleImage;
	
    public ChooseThemeBox() {
    	createThemeBox();
    }
    
	public HBox getThemeBox() {
		return themeBox;
	}
	
	List<ImageView> listCircle =  new ArrayList<ImageView>();
	
	private void createThemeBox() {
		VBox theme1 = createThemeChooser();
		VBox theme2 = createThemeChooser();
		
		themeBox.setSpacing(20);
		themeBox.setAlignment(Pos.CENTER);
		themeBox.getChildren().addAll(theme1, theme2);
	}
	
	private VBox createThemeChooser() {
		VBox newBox = new VBox();
		
		themeImage = new ImageView(waterMelonTheme);
		themeImage.setStyle("-border-radius: 5;");
		circleImage = new ImageView(circleNotChoosen);
		listCircle.add(circleImage);
		
		isCircleChoosen = false;
		
		newBox.getChildren().addAll(themeImage, circleImage);
		newBox.setAlignment(Pos.CENTER);
		newBox.setSpacing(17);
		return newBox;
	}
	
//	listCircle.get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
//		public void handle(MouseEvent event) {
////			for (ImageView img : listCircle.values()) {
////				img.setIsCircleChoosen(false);
////			}
////			circleImage.setIsCircleChoosen(true);
//	//		choosenShip = shipToPick.getShip();
//		}
//	}
	
	
	public boolean getCircleChoosen() {
		return isCircleChoosen;
	}
	
	public void setIsCircleChoosen(boolean isCircleChoosen) {
		this.isCircleChoosen = isCircleChoosen;
		String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
		circleImage.setImage(new Image(imageToSet));
	}
}
