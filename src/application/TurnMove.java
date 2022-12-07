package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class TurnMove {
	
	
	private boolean playerTurn = true;
	
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
		
		box.getChildren().add(createBtn());
		
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
