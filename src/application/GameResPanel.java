package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
public class GameResPanel{

    @FXML
    private Button BackToMenuButton;

    @FXML
    private static Label winLabel;
    
    @FXML
    void BackToMenuButtonPressed(ActionEvent event) {
    	
    }
    public static void setWinnerText(String winner) {
    	winLabel.setText(winner + " Win!");
    }
}
