package application.game;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import application.CheckersApp;

public class GameSubScene extends SubScene{
	private final String FONT_PATH = "src/application/resources/kenvector_future.ttf";
	private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('application/resources/green_buttonRe.png');";
	BackgroundImage bgImg = new BackgroundImage(new Image("application/resources/blue.png"),
			BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
	private Label winTxt = new Label();
	private Button backButton;
	public GameSubScene() {
		super(new VBox(), 400,400);
		prefWidth(400);
		prefHeight(400);
		try {
			winTxt.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 20));
		}
		catch (FileNotFoundException e) {
			winTxt.setFont(Font.font("Verdana", 26));
		}
		winTxt.setTextFill(Color.WHITE);
		createBackButton();
		VBox subRoot = (VBox) this.getRoot();
		subRoot.setBackground(new Background(bgImg));
		subRoot.setAlignment(Pos.CENTER);
		subRoot.getChildren().add(winTxt);
		subRoot.getChildren().add(backButton);
		subRoot.setSpacing(50);
		setLayoutX(100);
		setLayoutY(100);
	}
	
	public void setTxt(String msg) {
		winTxt.setText(msg);
	}
	public VBox getPane() {
		return (VBox)this.getRoot();
	}
	
	private void createBackButton() {
		
		backButton = new Button();
        backButton.setText("Back");
        backButton.setStyle(BUTTON_FREE_STYLE);
        backButton.setPrefHeight(45);
        backButton.setPrefWidth(150);
        
        try {
			backButton.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 18));
		}
		catch (FileNotFoundException e) {
			backButton.setFont(Font.font("Verdana", 20));
		}
        
		backButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				backButton.setEffect(new DropShadow());
				
			}
		});
		
		backButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				backButton.setEffect(null);
				
			}
		});
        
		backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	CheckersApp.backToMainMenu(3);
            }
        });
	}
}
