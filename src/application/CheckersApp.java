package application;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static application.GameBase.TILE_SIZE;
import static application.GameBase.HEIGHT;
import static application.GameBase.WIDTH;

public class CheckersApp extends Application {

	Scene scene;
	Pane root = new Pane();
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	root.setPrefSize(WIDTH * TILE_SIZE + 160, HEIGHT * TILE_SIZE);
    	root.getChildren().add(makeMenu());
    	scene = new Scene(root);
        primaryStage.setTitle("CheckersApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Pane makeMenu() {
    	Pane menu = new Pane();
    	Button vsp = new Button();
    	Button vsc = new Button();
    	VBox box = new VBox();
    	
    	vsc.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
            }
        });
    	
    	vsp.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	changeGameScene();
            }
        });
    	
    	vsc.setText("VS Computer");
    	vsp.setText("VS Player");
    	
    	box.getChildren().addAll(vsc,vsp);
    	menu.getChildren().addAll(box);   
    	
    	
    	
    	return menu;
    }
    
    private void changeGameScene() {
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