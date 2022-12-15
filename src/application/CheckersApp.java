package application;
import static application.game.GameBase.HEIGHT;
import static application.game.GameBase.TILE_SIZE;
import static application.game.GameBase.WIDTH;

import application.game.CreditsScene;
import application.game.GameBase;
import application.game.MainMenu;
import application.game.ThemeChooserScene;
import application.game.ModeComputer;
import application.game.TimeAttackMode;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CheckersApp extends Application {

	Scene scene;
	static Stage mainStage;
	static Pane root = new Pane();
	MainMenu mainMenu = new MainMenu();
	static MainMenu mainMenuStatic = new MainMenu();
	static ThemeChooserScene themeChooser = new ThemeChooserScene();
	static CreditsScene creditsScene = new CreditsScene();
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	root.setPrefSize(MainMenu.WIDTH, MainMenu.HEIGHT);
    	root.getChildren().add(mainMenu.getPane());
    	mainStage = primaryStage;
    	scene = new Scene(root);
        mainStage.setTitle("Checkers Game");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.getIcons().add(
				   new Image("application/resources/CheckersIcon.png"));
        mainStage.show();
    }
    
    public static void toVSPlayer() {
    	GameBase vsPlayer = new GameBase();
    	vsPlayer.createContent();
    	root.getChildren().remove(0);
    	root.getChildren().add(vsPlayer.getRoot());
    	mainStage.setWidth(WIDTH * TILE_SIZE + 160);
    	mainStage.setHeight((mainStage.getHeight() - ThemeChooserScene.THEMEHEIGHT) + HEIGHT * TILE_SIZE);
    	mainStage.centerOnScreen();
    }
    
    public static void toVSComputer() {
    	ModeComputer vsComp = new ModeComputer();
    	vsComp.createContent();
    	root.getChildren().remove(0);
    	root.getChildren().add(vsComp.getRoot());
    	mainStage.setWidth(WIDTH * TILE_SIZE + 160);
    	mainStage.setHeight((mainStage.getHeight() - ThemeChooserScene.THEMEHEIGHT) + HEIGHT * TILE_SIZE);
    	mainStage.centerOnScreen();
    }
    
    public static void toVSPlayerTime() {
    	TimeAttackMode vsTime = new TimeAttackMode();
    	vsTime.createContent();
    	root.getChildren().remove(0);
    	root.getChildren().add(vsTime.getRoot());
    	mainStage.setWidth(WIDTH * TILE_SIZE + 160);
    	mainStage.setHeight((mainStage.getHeight() - ThemeChooserScene.THEMEHEIGHT) + HEIGHT * TILE_SIZE);
    	mainStage.centerOnScreen();
    }
    
    public static void showCreditsScene() {
    	root.getChildren().remove(0);
    	root.getChildren().add(creditsScene.getPane());
    	mainStage.setWidth(200);
    	mainStage.setHeight((mainStage.getHeight() - MainMenu.HEIGHT) + 250);
    	mainStage.centerOnScreen();
    }
    
    public static void backToMainMenu(int state) {
    	root.getChildren().remove(0);
    	root.getChildren().add(mainMenuStatic.getPane());
    	
    	if(state ==1) {
    		mainStage.setWidth(MainMenu.WIDTH);
    		mainStage.setHeight((mainStage.getHeight() - CreditsScene.HEIGHT) + MainMenu.HEIGHT);
    	}
    	else if(state ==2) {
    		mainStage.setWidth((mainStage.getWidth() - ThemeChooserScene.THEMEWIDTH) + MainMenu.WIDTH);
        	mainStage.setHeight((mainStage.getHeight() - ThemeChooserScene.THEMEHEIGHT) + MainMenu.HEIGHT);
    	}else {
    		mainStage.setWidth(MainMenu.WIDTH);
    		mainStage.setHeight((mainStage.getHeight() - GameBase.GAMEHEIGHT) + MainMenu.HEIGHT);
    	}
    	mainStage.centerOnScreen();
    }
    
    public static void showThemeChooser() {
    	root.getChildren().remove(0);
    	root.getChildren().add(themeChooser.getPane());
    	mainStage.setWidth(ThemeChooserScene.THEMEWIDTH);
    	mainStage.setHeight((mainStage.getHeight() - MainMenu.HEIGHT) + ThemeChooserScene.THEMEHEIGHT);
    	mainStage.centerOnScreen();
    }
    
    public static void exitGame() {
    	mainStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}