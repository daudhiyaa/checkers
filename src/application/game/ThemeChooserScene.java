package application.game;

import static application.game.MainMenu.BACKGROUND_IMAGE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.CheckersApp;
//import application.model.ButtonStyle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ThemeChooserScene{
	public static final int THEMEWIDTH = 700;
    public static final int THEMEHEIGHT = 450;
    private final String FONT_PATH = "src/application/resources/kenvector_future.ttf";
    
	private Pane themePane = new Pane();
	
	public ThemeChooserScene() {
		createContents();
		createBackground();
	}
	
	public Pane getPane() {
		return themePane;
	}
	
	private Text createText(int size, String title) {
		Text txt = new Text(title);
		try {
			txt.setFont(Font.loadFont(new FileInputStream(FONT_PATH), size));
		}
		catch (FileNotFoundException e) {
			txt.setFont(Font.font("Verdana", size));
		}
		txt.setFill(Color.WHITE);
		return txt;
	}
	
	private Button createButton(String txt) {
		Button newBtn = new Button(txt);
		newBtn.setPrefSize(150, 25);
//		btn.setStyle(BUTTON_FREE_STYLE);
		try {
			newBtn.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 12));
		}
		catch (FileNotFoundException e) {
			newBtn.setFont(Font.font("Verdana", 12));
		}
		return newBtn;
	}
	
	private void createContents() {
		VBox box = new VBox();
		box.setPrefSize(THEMEWIDTH, THEMEHEIGHT);
		Text chooseTheme = createText(18, "CHOOSE THEME");
		
		ChooseThemeBox CTB = new ChooseThemeBox();
		HBox boxCTB = CTB.getThemeBox();
		
		Button back = new Button("Back");
		try {
			back.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 12));
		}
		catch (FileNotFoundException e) {
			back.setFont(Font.font("Verdana", 12));
		}
		back.setPrefSize(120, 25);
		
		back.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	CheckersApp.backToMainMenu(2);
            }
        });
		
		Text chooseMode = createText(15, "CHOOSE YOUR MODE AND PLAY!");
		
		HBox modeBox = new HBox();
		
		Button normalButton = createButton("NORMAL MODE");
		normalButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	CheckersApp.toVSPlayer();
            }
        });
		
		Button timerButton = createButton("TIMER MODE");
		timerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	CheckersApp.toVSPlayerTime();
            }
        });
		
		Button vsCompButton = createButton("VS COMPUTER");
		vsCompButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	CheckersApp.toVSComputer();
            }
        });
		
		modeBox.getChildren().addAll(normalButton, timerButton, vsCompButton);
		modeBox.setAlignment(Pos.CENTER);
		modeBox.setSpacing(15);
		
		VBox.setMargin(boxCTB, new Insets(0,0,30,0));
		VBox.setMargin(modeBox, new Insets(0,0,30,0));
		
		box.getChildren().addAll(chooseTheme, boxCTB, chooseMode, modeBox, back);
		box.setAlignment(Pos.CENTER);
		box.setSpacing(15);
		themePane.getChildren().add(box);
	}
	
	private void createBackground() {
		Image backgroundImage = new Image(BACKGROUND_IMAGE, 256, 256, false, false);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		themePane.setBackground(new Background(background));
	}
}
