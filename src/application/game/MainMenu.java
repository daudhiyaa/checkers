package application.game;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenu {
	private static final int HEIGHT = 500;
	private static final int WIDTH = 800;
	private final static int MENU_BUTTON_START_X = 100;
	private final static int MENU_BUTTON_START_Y = 50;
	private Pane mainMenuPane;
	private Scene mainMenuScene;
	private Stage mainMenuStage;
	
	List<Button> menuButtons;
	
	public MainMenu() {
		mainMenuPane = new Pane();
		mainMenuScene = new Scene(mainMenuPane, WIDTH, HEIGHT );
		mainMenuStage = new Stage();
		mainMenuStage.setScene(mainMenuScene);
		CreateButtons();
	}
	
	private void AddMenuButtons(Button button) {
		button.setLayoutX(MENU_BUTTON_START_X);
		button.setLayoutY(MENU_BUTTON_START_Y + menuButtons.size() * 60);
		menuButtons.add(button);
		mainMenuPane.getChildren().add(button);
	}
	
	private void createNormalButton() {
		Button normalButton = new Button("Normal");
		AddMenuButtons(normalButton);
		
		normalButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
			}
		});
	}
	
	private void createTimerButton() {
		Button timerButton = new Button("Timer");
		AddMenuButtons(timerButton);
		
		timerButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
			}
		});
	}
	
	private void createVSCompButton() {
		Button vsCompButton = new Button("Timer");
		AddMenuButtons(vsCompButton);
		
		vsCompButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
			}
		});
	}
	
	private void createCreditsButton() {
		Button creditsButton = new Button("CREDITS");
		AddMenuButtons(creditsButton);
		
		creditsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
	}
	
	private void createExitButton() {
		Button exitButton = new Button("EXIT");
		AddMenuButtons(exitButton);
		
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainMenuStage.close();
			}
		});
	}
	
	private void CreateButtons() {
		createNormalButton();
		createTimerButton();
		createVSCompButton();
		createCreditsButton();
		createExitButton();
	}
}
