package application.game;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainMenu {
	private Pane mainMenuPane;
	
	List<Button> menuButtons;
	
	public MainMenu() {
		mainMenuPane = new Pane();		
		CreateButtons();
	}
	
	public Pane getPane() {
		return mainMenuPane;
	}
	
	private void CreateButtons() {
		VBox box = new VBox();
		
		Button normalButton = new Button();
		normalButton.setText("NORMAL");
		Button timerButton = new Button();
		timerButton.setText("TIMER");
		Button vsCompButton = new Button();
		vsCompButton.setText("VS COMPUTER");
		Button creditsButton = new Button();
		creditsButton.setText("CREDITS");
		Button exitButton = new Button();
		exitButton.setText("EXIT");
		
		box.getChildren().addAll(normalButton, timerButton, vsCompButton, creditsButton, exitButton);
		mainMenuPane.getChildren().add(box);
	}
}
