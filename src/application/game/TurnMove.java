package application.game;

import static application.game.GameBase.HEIGHT;
import static application.game.GameBase.TILE_SIZE;
import static application.game.GameBase.WIDTH;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.model.PieceType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TurnMove {
	private boolean playerTurn = true;
	
	private final static String TURNOFF_PATH = "application/resources/turn_off.png";
	private final static String TURNON_PATH = "application/resources/turn_on.png";
	
	private final String FONT_PATH = "src/application/resources/kenvector_future.ttf";
	private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('application/resources/green_buttonRe.png');";
	
	ImageView markerPlayer = new ImageView(TURNON_PATH);;
	ImageView markerOpponent = new ImageView(TURNOFF_PATH);;
	
	public boolean getPlayerTurn() {
		return playerTurn;
	}
	
	public boolean checkTurn(PieceType type) {
    	if(type == PieceType.RED && getPlayerTurn() == true) {
    		return true;
    	}
    	if(type == PieceType.WHITE && getPlayerTurn() == false) {
    		return true;
    	}
    	return false;
    }
	
	public void changeTurn() {
		playerTurn = !playerTurn;
		changeMarker();
	}
	
	public VBox turnUI() {
		VBox box = new VBox();
		box.setTranslateX(WIDTH * TILE_SIZE);
		box.setPrefHeight(HEIGHT * TILE_SIZE);
		box.setPrefWidth(160);
			
		box.getChildren().add(markerOpponent);
		box.getChildren().add(createBtn());	
		box.getChildren().add(markerPlayer);
		
		box.setSpacing(20);
		
		BackgroundImage bgImg = new BackgroundImage(new Image("application/resources/blue.png"),
				BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		box.setBackground(new Background(bgImg));
			
		
		box.setAlignment(Pos.CENTER);
		return box;
	}
	
	private void changeMarker() {
		if(playerTurn) {
			markerPlayer.setImage(new Image(TURNON_PATH)); 
			markerOpponent.setImage(new Image(TURNOFF_PATH));
		}else {
			markerPlayer.setImage(new Image(TURNOFF_PATH)); 
			markerOpponent.setImage(new Image(TURNON_PATH));
		}
	}
	
	private Button createBtn() {
		
		Button btn = new Button();
        btn.setText("Switch");
        btn.setStyle(BUTTON_FREE_STYLE);
        btn.setPrefHeight(45);
        btn.setPrefWidth(150);
        
        try {
			btn.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 18));
		}
		catch (FileNotFoundException e) {
			btn.setFont(Font.font("Verdana", 23));
		}
        
		btn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btn.setEffect(new DropShadow());
				
			}
		});
		
		btn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btn.setEffect(null);
				
			}
		});
        
		btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	changeTurn();
            }
        });
		
		return btn;
	}
}
