package application.game;

import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ChooseThemeBox {
	private HBox themeBox = new HBox();
    
    private String circleNotChoosen = "/application/resources/grey_circle.png";
	private String circleChoosen = "/application/resources/red_choosen.png";
	private boolean isCircleChoosen;
	
	private ImageView themeImage;
	private ImageView circleImage;
	
    public ChooseThemeBox() {
    	createThemeBox();
    }
    
	public HBox getThemeBox() {
		return themeBox;
	}
	
	private void createThemeBox() {
		VBox theme1 = createThemeChooser();
		VBox theme2 = createThemeChooser();
		
//		themeBox.setPrefSize(ThemeChooserScene.THEMEWIDTH, ThemeChooserScene.THEMEHEIGHT);
		themeBox.setSpacing(20);
		themeBox.setAlignment(Pos.CENTER);
		themeBox.getChildren().addAll(theme1, theme2);
	}
	
	private VBox createThemeChooser() {
		VBox newBox = new VBox();
		
		themeImage = new ImageView(circleChoosen);
		circleImage = new ImageView(circleNotChoosen);
		isCircleChoosen = false;
		
		newBox.getChildren().addAll(themeImage, circleImage);
		newBox.setAlignment(Pos.CENTER);
		newBox.setSpacing(17);
		return newBox;
	}
	
	public void setIsCircleChoosen(boolean isCircleChoosen) {
		this.isCircleChoosen = isCircleChoosen;
		String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
		circleImage.setImage(new Image(imageToSet));
	}
}
