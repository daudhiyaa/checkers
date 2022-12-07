package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.VBox;

public class TurnMove {
	
	private boolean playerTurn = true;
	
	private final static String TURNOFF_PATH = "application/resources/turn_off.png";
	private final static String TURNON_PATH = "application/resources/turn_on.png";
	
	public boolean getPlayerTurn() {
		return playerTurn;
	}
	
	public void changeTurn() {
		playerTurn = !playerTurn;
	}
	
	public VBox turnUI() {
		VBox box = new VBox();
		
		box.setTranslateX(CheckersApp.WIDTH * CheckersApp.TILE_SIZE);
		box.setPrefHeight(CheckersApp.HEIGHT * CheckersApp.TILE_SIZE);
		box.setPrefWidth(150);

		ImageView markerOff = new ImageView(TURNOFF_PATH);
		ImageView markerOn = new ImageView(TURNON_PATH);
			
		box.getChildren().add(markerOff);
			
		box.getChildren().add(createBtn());
			
		box.getChildren().add(markerOn);
		
		box.setSpacing(20);
		
		BackgroundImage bgImg = new BackgroundImage(new Image("application/resources/black.png"),
				BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		box.setBackground(new Background(bgImg));
			
		
		box.setAlignment(Pos.CENTER);
		
		return box;
	}
	
	private Button createBtn() {
		Button btn = new Button();
        btn.setText("Switch");
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	changeTurn();
            	if (playerTurn) {
            		
            	}
            	else ;
            }
        });
		
		return btn;
	}
	
}
