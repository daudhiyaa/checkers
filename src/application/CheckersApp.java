package application;
import static application.game.GameBase.HEIGHT;
import static application.game.GameBase.TILE_SIZE;
import static application.game.GameBase.WIDTH;

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
	Pane root = new Pane();
	MainMenu mainMenu = new MainMenu();
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	root.setPrefSize(300, 400);
    	root.getChildren().add(mainMenu.getPane());
    	scene = new Scene(root);
        primaryStage.setTitle("Checkers Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    //temporary
    private Pane makeMenu() {
    	Pane menu = new Pane();
    	Button vsp = new Button();
    	Button vsc = new Button();
    	Button vst = new Button();
    	VBox box = new VBox();
    	
    	vsc.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
            }
        });
    	
    	vst.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
            }
        });
    	
    	vsp.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	changeVSPlayer();
            }
        });
    	
    	vsc.setText("VS Computer");
    	vsp.setText("VS Player");
    	
    	box.getChildren().addAll(vsc,vsp);
    	menu.getChildren().addAll(box);   
    	
    	
    	
    	return menu;
    }
    
    private void changeVSPlayer() {
    	System.out.println("changeScene");
    	GameBase base = new GameBase();
    	base.createContent();
    	root.getChildren().remove(0);
    	root.getChildren().add(base.getRoot());
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}