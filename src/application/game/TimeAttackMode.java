package application.game;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import application.model.Piece;
import application.model.PieceType;
import application.model.Tile;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import java.text.DecimalFormat;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TimeAttackMode extends GameBase{
	private Label timeLabel;
	private final String timeStyle = "-fx-background-color: transparent; -fx-background-image: url('application/resources/green_buttonRe.png');";
	private final String FONT_PATH = "src/application/resources/kenvector_future.ttf";
	boolean flagStop = true;
	int miliseconds=90;
	int seconds;
	int minutes=3;
	private DecimalFormat dFormat = new DecimalFormat("00");
	String sMinutes;
	String sSeconds;
	private VBox box = turn.turnUI();
	
	@Override
	public void createContent() {
        root.setPrefSize(WIDTH * TILE_SIZE + 160, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tileGroup, pieceGroup);
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);

                Piece piece = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.RED, x, y);
                    redPiece.add(piece);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.WHITE, x, y);
                    whitePiece.add(piece);
                }

                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }
        createTimeLabel();
        box.getChildren().add(timeLabel);
        root.getChildren().add(box);
        createGameLoop();
    }
	
	private void createGameLoop() {
		AnimationTimer gameTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				miliseconds--;
				if(flagStop && miliseconds==0) {
				miliseconds = 90;
				seconds--;
				sSeconds = dFormat.format(seconds);
				sMinutes = dFormat.format(minutes);	
				timeLabel.setText(sMinutes + ":" + sSeconds);
				
				if(seconds==-1) {
					seconds = 59;
					minutes--;
					sSeconds = dFormat.format(seconds);
					sMinutes = dFormat.format(minutes);	
					timeLabel.setText(sMinutes + ":" + sSeconds);
				}
				if(minutes==0 && seconds==0) {
					flagStop = false;
					if(redPiece.size() < whitePiece.size()) {
						resScene.setTxt("White Win!");
					}else if(redPiece.size() > whitePiece.size()){
						resScene.setTxt("Red Win!");
					}else{
						resScene.setTxt("DRAW!");
					}
						getWinPanel();
					}
				}
			}
			
		};
		gameTimer.start();
		if(flagStop == false)gameTimer.stop();
	}
				
	
	private void createTimeLabel() {
		timeLabel = new Label();
		timeLabel.setPrefHeight(45);
		timeLabel.setPrefWidth(150);
		timeLabel.setStyle(timeStyle);
		timeLabel.setText("03:00");
		timeLabel.setAlignment(Pos.CENTER);
		try {
			timeLabel.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 18));
		}
		catch (FileNotFoundException e) {
			timeLabel.setFont(Font.font("Verdana", 23));
		}
        
	}
	
	@Override
	public int GameRes() {
    	if(whitePiece.size()==0) {
    		flagStop = false;
    		return 1;
    	}
    	if(redPiece.size()==0) {
    		flagStop = false;
    		return 2;
    	}
    	return 0;
    }
}
