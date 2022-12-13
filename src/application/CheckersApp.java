package application;
import static application.game.GameBase.HEIGHT;
import static application.game.GameBase.TILE_SIZE;
import static application.game.GameBase.WIDTH;

import application.game.CreditsScene;
import application.game.GameBase;
import application.game.MainMenu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CheckersApp extends Application {

	Scene scene;
	static Stage mainStage;
	static Pane root = new Pane();
	MainMenu mainMenu = new MainMenu();
	static MainMenu mainMenuStatic = new MainMenu();
	static CreditsScene creditsScene = new CreditsScene();
	static GameBase vsPlayer = new GameBase();
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	root.setPrefSize(MainMenu.WIDTH, MainMenu.HEIGHT);
    	root.getChildren().add(mainMenu.getPane());
    	mainStage = primaryStage;
    	scene = new Scene(root);
        mainStage.setTitle("Checkers Game");
        mainStage.setScene(scene);
        mainStage.show();
    }
    
    public static void toVSPlayer() {
    	vsPlayer.createContent();
    	root.getChildren().remove(0);
    	root.getChildren().add(vsPlayer.getRoot());
    	mainStage.setWidth(WIDTH * TILE_SIZE + 160);
    	mainStage.setHeight((mainStage.getHeight() - MainMenu.HEIGHT) + HEIGHT * TILE_SIZE);
    	mainStage.centerOnScreen();
    }
    
    public static void toVSComp() {
//    	vsPlayer.createContent();
    	root.getChildren().remove(0);
//    	root.getChildren().add(vsPlayer.getRoot());
    	mainStage.setWidth(WIDTH * TILE_SIZE + 160);
    	mainStage.setHeight((mainStage.getHeight() - MainMenu.HEIGHT) + HEIGHT * TILE_SIZE);
    }
    
    public static void toVSPlayerTime() {
//    	vsPlayer.createContent();
    	root.getChildren().remove(0);
//    	root.getChildren().add(vsPlayer.getRoot());
    	mainStage.setWidth(WIDTH * TILE_SIZE + 160);
    	mainStage.setHeight((mainStage.getHeight() - MainMenu.HEIGHT) + HEIGHT * TILE_SIZE);
    }
    
    public static void showCreditsScene() {
    	root.getChildren().remove(0);
    	root.getChildren().add(creditsScene.getPane());
    	mainStage.setWidth(200);
    	mainStage.setHeight((mainStage.getHeight() - MainMenu.HEIGHT) + 250);
    	mainStage.centerOnScreen();
    }
    
    public static void backToMainMenu() {
    	root.getChildren().remove(0);
    	root.getChildren().add(mainMenuStatic.getPane());
    	mainStage.setWidth(MainMenu.WIDTH);
    	mainStage.setHeight((mainStage.getHeight() - CreditsScene.HEIGHT) + MainMenu.HEIGHT);
    	mainStage.centerOnScreen();
    }
    
    public static void exitGame() {
    	mainStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}