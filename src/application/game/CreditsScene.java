package application.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class CreditsScene{
	private Pane creditsPane = new Pane();
	private final String FONT_PATH = "src/application/resources/kenvector_future.ttf";
	private final static String BACKGROUND_IMAGE = "/application/resources/deep_blue.png";
	List<Text> anggota = new ArrayList<Text>();
	
	public CreditsScene() {
		creditsPane = new Pane();		
		createContents();
		createBackground();
	}
	
	public Pane getPane() {
		return creditsPane;
	}
	
	private void createContents() {
		VBox box = new VBox();
		box.setPrefSize(200, 250);
		Text credit = new Text("CREDITS");
		anggota.add(credit);
		Text key = new Text("Keyisa Raihan");
		anggota.add(key);
		Text daud = new Text("Daud Dhiya' R");
		anggota.add(daud);
		Text alfa = new Text("Alfa Fakhrur");
		anggota.add(alfa);
		for(Text nama : anggota) {
			try {
				nama.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 12));
			}
			catch (FileNotFoundException e) {
				nama.setFont(Font.font("Verdana", 12));
			}
			nama.setFill(Color.WHITE);
		}
		
		Button back = new Button("Back");
		try {
			back.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 12));
		}
		catch (FileNotFoundException e) {
			back.setFont(Font.font("Verdana", 12));
		}
		
		box.getChildren().addAll(credit, key,daud,alfa, back);
		box.setAlignment(Pos.CENTER);
		box.setSpacing(10);
		creditsPane.getChildren().add(box);
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("/application/resources/deep_blue.png", 256, 256, false, false);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		creditsPane.setBackground(new Background(background));
	}
}
