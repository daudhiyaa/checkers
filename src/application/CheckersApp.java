package application;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class CheckersApp extends Application {
	
	GameBase base = new GameBase();
	
	Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        base.createContent();
    	scene = new Scene(base.getRoot());
        primaryStage.setTitle("CheckersApp");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}