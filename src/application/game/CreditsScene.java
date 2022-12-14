package application.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.CheckersApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import static application.game.MainMenu.BACKGROUND_IMAGE;

public class CreditsScene{
	private Pane creditsPane = new Pane();
	private final String FONT_PATH = "src/application/resources/kenvector_future.ttf";
	
	public static final int WIDTH = 200;
    public static final int HEIGHT = 250;
    
	public CreditsScene() {		
		createContents();
		createBackground();
	}
	
	public Pane getPane() {
		return creditsPane;
	}
	
	private Text createText(int size, String title) {
		Text txt = new Text(title);
		try {
			txt.setFont(Font.loadFont(new FileInputStream(FONT_PATH), size));
		}
		catch (FileNotFoundException e) {
			txt.setFont(Font.font("Verdana", size));
		}
		txt.setFill(Color.WHITE);
		return txt;
	}
	
	private void createContents() {
		VBox box = new VBox();
		box.setPrefSize(WIDTH, HEIGHT);
		Text credit = createText(18, "CREDITS");
		Text key = createText(12, "Keyisa Raihan");
		Text daud = createText(12, "Daud Dhiya' R");
		Text alfa = createText(12, "Alfa Fakhrur");
		
		Button back = new Button("Back");
		try {
			back.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 12));
		}
		catch (FileNotFoundException e) {
			back.setFont(Font.font("Verdana", 12));
		}
		back.setPrefSize(100, 25);
//		back.setStyle("-fx-background-color: transparent; -fx-background-image: url('application/resources/green_buttonRe.png');");
		
		back.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	CheckersApp.backToMainMenu(1);
            }
        });
		
		box.getChildren().addAll(credit, key,daud,alfa, back);
		box.setAlignment(Pos.CENTER);
		box.setSpacing(17);
		creditsPane.getChildren().add(box);
	}
	
	private void createBackground() {
		Image backgroundImage = new Image(BACKGROUND_IMAGE, 256, 256, false, false);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		creditsPane.setBackground(new Background(background));
	}
}
