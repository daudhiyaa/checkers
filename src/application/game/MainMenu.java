package application.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainMenu {
	private Pane mainMenuPane;
	private final String FONT_PATH = "src/application/resources/kenvector_future.ttf";
	private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('application/resources/green_buttonRe.png');";
	List<Button> menuButtons = new ArrayList<Button>();
	
	public MainMenu() {
		mainMenuPane = new Pane();		
		CreateButtons();
	}
	
	public Pane getPane() {
		return mainMenuPane;
	}
	
	private void CreateButtons() {
		Text judul = new Text("CHECKERS GAME");
		try {
			judul.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 18));
		} catch (FileNotFoundException e1) {
			judul.setFont(Font.font("Verdana", 18));
		}
		VBox box = new VBox(judul);
		box.setPrefSize(300, 400);
		
		Button normalButton = new Button("NORMAL");
		menuButtons.add(normalButton);
		Button timerButton = new Button("TIMER");
		menuButtons.add(timerButton);
		Button vsCompButton = new Button("VS COMPUTER");
		menuButtons.add(vsCompButton);
		Button creditsButton = new Button("CREDITS");
		menuButtons.add(creditsButton);
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
}
