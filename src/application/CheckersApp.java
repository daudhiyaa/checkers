package application;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.SubScene;
public class CheckersApp extends Application {

    public static final int TILE_SIZE = 75;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    private ArrayList<Piece> whitePiece = new ArrayList<>();
    private ArrayList<Piece> redPiece = new ArrayList<>();
    private Tile[][] board = new Tile[WIDTH][HEIGHT];
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    private Parent resRoot;
    private SubScene resScene;
    Scene scene;
    Pane root = new Pane();
    private TurnMove turn = new TurnMove();
    private void createContent(Pane croot) {
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
        root.getChildren().add(turn.turnUI());
    }

    private MoveResult tryMove(Piece piece, int newX, int newY) {
        if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
            return new MoveResult(MoveType.NONE);
        }

        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());
        
        if(!piece.getType().isKing) {
	        if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir) {
	            return new MoveResult(MoveType.NORMAL);
	        } else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2) {
	
	            int x1 = x0 + (newX - x0) / 2;
	            int y1 = y0 + (newY - y0) / 2;
	
	            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
	                return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
	            }
	        }
        }else {
        	if (Math.abs(newX - x0) == 1) {
	            return new MoveResult(MoveType.NORMAL);
	        } else if (Math.abs(newX - x0) == 2) {
	
	            int x1 = x0 + (newX - x0) / 2;
	            int y1 = y0 + (newY - y0) / 2;
	
	            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
	                return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
	            }
	        }
        }

        return new MoveResult(MoveType.NONE);
    }

    private int toBoard(double pixel) {
        return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        createContent(root);
    	scene = new Scene(root);
        resRoot = FXMLLoader.load(getClass().getResource("EndPanel.fxml"));
        resScene = new SubScene(resRoot,400,400);
        primaryStage.setTitle("CheckersApp");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
  

    private Piece makePiece(PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y);

        piece.setOnMouseReleased(e -> {
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());

            MoveResult result;
            
            if(turn.checkTurn(type)) {
            	result = new MoveResult(MoveType.NONE);
            }
            else if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
                result = new MoveResult(MoveType.NONE);
            } else {
                result = tryMove(piece, newX, newY);
            }

            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());
            int GameResult = -1;
            switch (result.getType()) {
                case NONE:
                    piece.abortMove();
                    break;
                case NORMAL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    
                    turn.changeTurn();
                    break;
                case KILL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    Piece otherPiece = result.getPiece();
                    board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                    if(otherPiece.getType() == PieceType.RED) redPiece.remove(otherPiece);
                    else if(otherPiece.getType() == PieceType.WHITE) whitePiece.remove(otherPiece);
                    pieceGroup.getChildren().remove(otherPiece);
                    GameResult = GameRes();
                    break;
            }
            
            if(((newY == HEIGHT-1 && type == PieceType.RED) 
            		|| (newY == 0 && type == PieceType.WHITE)) && (!type.isKing)) {
            	piece.changeToKing();
            	
            	turn.changeTurn();
            }
            if(GameResult!=-1) {
            	if(GameResult==1) {
            		System.out.println("Red WIN");
            		GameResPanel.setWinnerText("Red");
            	}else if(GameResult==2) {
            		System.out.println("White WIN");
            		GameResPanel.setWinnerText("White");
            	}
        		root.getChildren().add(resScene);
            }
        });

        return piece;
    }
    
    private int GameRes() {
    	if(whitePiece.size()==0)return 1;
    	if(redPiece.size()==0)return 2;
    	return 0;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}