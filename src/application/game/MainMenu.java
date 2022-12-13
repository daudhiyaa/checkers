package application.game;

import static application.game.GameBase.HEIGHT;
import static application.game.GameBase.TILE_SIZE;
import static application.game.GameBase.WIDTH;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import application.CheckersApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
	private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('application/resources/green_buttonRe.png');";
	List<Button> menuButtons = new ArrayList<Button>();
	
	public static final int WIDTH = 300;
    public static final int HEIGHT = 400;
	
	public MainMenu() {
		mainMenuPane = new Pane();
//		mainMenuPane.getChildren().add(creditsScene.getPane());
		createButtons();
		createBackground();
	}
	
	public Pane getPane() {
		return mainMenuPane;
	}
	
	private void createButtons() {
		Text judul = new Text("CHECKERS GAME");
		try {
			judul.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 18));
		} catch (FileNotFoundException e1) {
			judul.setFont(Font.font("Verdana", 18));
		}
		judul.setFill(Color.WHITE);
		
		VBox box = new VBox(judul);
		box.setPrefSize(300, 400);
		
		Button normalButton = new Button("NORMAL MODE");
		menuButtons.add(normalButton);
		
		normalButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	CheckersApp.toVSPlayer();
            }
        });
		
		Button timerButton = new Button("TIMER MODE");
		menuButtons.add(timerButton);
		
		timerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
            }
        });
		
		Button vsCompButton = new Button("VS COMPUTER");
		menuButtons.add(vsCompButton);
		
		vsCompButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
            }
        });
		
		Button creditsButton = new Button("CREDITS");
		menuButtons.add(creditsButton);
		
		creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	CheckersApp.showCreditsScene();
            }
        });
		
		Button exitButton = new Button("EXIT");
		menuButtons.add(exitButton);
		
		for(Button btn : menuButtons) {
			btn.setPrefSize(150, 20);
//			btn.setStyle(BUTTON_FREE_STYLE);
			try {
				btn.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 12));
			}
			catch (FileNotFoundException e) {
				btn.setFont(Font.font("Verdana", 12));
			}
		}
		
		box.setAlignment(Pos.CENTER);
		box.setSpacing(17);
		
		box.getChildren().addAll(normalButton, timerButton, vsCompButton, creditsButton, exitButton);
		mainMenuPane.getChildren().add(box);
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("/application/resources/deep_blue.png", 256, 256, false, false);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainMenuPane.setBackground(new Background(background));
	}
}
