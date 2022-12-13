package application.game;

import static application.game.MainMenu.BACKGROUND_IMAGE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.CheckersApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	public static final int THEMEWIDTH = 450;
    public static final int THEMEHEIGHT = 300;
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
		back.setPrefSize(100, 25);
		
		back.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	CheckersApp.backToMainMenu(true);
            }
        });
		
		box.getChildren().addAll(chooseTheme, boxCTB, back);
		box.setAlignment(Pos.CENTER);
		box.setSpacing(17);
		themePane.getChildren().add(box);
	}
	
	private void createBackground() {
		Image backgroundImage = new Image(BACKGROUND_IMAGE, 256, 256, false, false);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		themePane.setBackground(new Background(background));
	}
}
