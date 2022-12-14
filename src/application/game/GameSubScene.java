package application.game;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.SubScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameSubScene extends SubScene{
	private boolean isHidden;
	private final String FONT_PATH = "src/application/resources/kenvector_future.ttf";
	private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('application/resources/green_buttonRe.png');";
	private final static String TURNOFF_PATH = "application/resources/turn_off.png";
	private final static String TURNON_PATH = "application/resources/turn_on.png";
	
	ImageView markerPlayer = new ImageView(TURNON_PATH);;
	ImageView markerOpponent = new ImageView(TURNOFF_PATH);;
	
	private Label winTxt = new Label();
	private Button backButton = new Button();
	public GameSubScene() {
		super(new BorderPane(), 400,400);
		prefWidth(400);
		prefHeight(400);
		try {
			winTxt.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 20));
		}
		catch (FileNotFoundException e) {
			winTxt.setFont(Font.font("Verdana", 26));
		}
		BorderPane subRoot = (BorderPane) this.getRoot();
		subRoot.setCenter(winTxt);
		isHidden = true;
		setLayoutX(100);
		setLayoutY(100);
	}
	
	public void setTxt(String msg) {
		winTxt.setText(msg);
	}
	public BorderPane getPane() {
		return (BorderPane)this.getRoot();
	}
}
