package application.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.CheckersApp;
import application.model.ButtonStyle;
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

public class MainMenu {
	private Pane mainMenuPane;
	private final String FONT_PATH = "src/application/resources/kenvector_future.ttf";
	public final static String BACKGROUND_IMAGE = "/application/resources/deep_blue.png";
	private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('application/resources/green_buttonRe.png');";
	
	public static final int WIDTH = 350;
    public static final int HEIGHT = 300;
	
	public MainMenu() {
		mainMenuPane = new Pane();
		createContents();
		createBackground();
	}
	
	public Pane getPane() {
		return mainMenuPane;
	}
	
	private Button createButton(String txt) {
		Button newBtn = new Button(txt);
		newBtn.setPrefSize(150, 25);
//		btn.setStyle(BUTTON_FREE_STYLE);
		try {
			newBtn.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 12));
		}
		catch (FileNotFoundException e) {
			newBtn.setFont(Font.font("Verdana", 12));
		}
		return newBtn;
	}
	
	private void createContents() {
		Text judul = new Text("CHECKERS GAME");
		try {
			judul.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
		} catch (FileNotFoundException e1) {
			judul.setFont(Font.font("Verdana", 23));
		}
		judul.setFill(Color.WHITE);
		
		VBox box = new VBox(judul);
		box.setPrefSize(WIDTH, HEIGHT);
		
		Button chooseTheme = createButton("PLAY");
		chooseTheme.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	CheckersApp.showThemeChooser();
            }
        });
		
		Button creditsButton = createButton("CREDITS");
		creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	CheckersApp.showCreditsScene();
            }
        });
		
		Button exitButton = createButton("EXIT");
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	CheckersApp.exitGame();
            }
        });
		
		box.setAlignment(Pos.CENTER);
		box.setSpacing(17);
		
		box.getChildren().addAll(chooseTheme, creditsButton, exitButton);
		mainMenuPane.getChildren().add(box);
	}
	
	private void createBackground() {
		Image backgroundImage = new Image(BACKGROUND_IMAGE, 256, 256, false, false);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainMenuPane.setBackground(new Background(background));
	}
}
