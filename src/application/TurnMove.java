package application;

public class TurnMove {
	private boolean playerTurn = true;
	
	public boolean getPlayerTurn() {
		return playerTurn;
	}
	
	public void changeTurn() {
		playerTurn = !playerTurn;
	}
	
}
