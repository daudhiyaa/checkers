package application;

import javafx.scene.layout.VBox;

public class TurnMove {
	
	public static VBox turnUI() {
		VBox box = new VBox();
		
//		#Continued After
		return box;
	}
	
	
	private boolean playerTurn = true;
	
	public boolean getPlayerTurn() {
		return playerTurn;
	}
	
	public void changeTurn() {
		playerTurn = !playerTurn;
	}
	
}
