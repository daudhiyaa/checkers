package application;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CheckersApp extends Application {
	
	// comp mech
	private ArrayList<Piece>redPiece = new ArrayList<Piece>();

    public static final int TILE_SIZE = 75;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    
    private TurnMove turn = new TurnMove();

    private Parent createContent() {
        Pane root = new Pane();
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
                    
                 // comp mech
                    redPiece.add(piece);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.WHITE, x, y);
                }

                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }
        
        root.getChildren().add(turn.turnUI());
        return root;
    }

    private MoveResult tryMove(Piece piece, int newX, int newY) {
        if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
            return new MoveResult(MoveType.NONE);
        }

        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());
        
        if(!piece.getIsKing()) {
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
        Scene scene = new Scene(createContent());
        
//        createGameLoop();
        
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

            switch (result.getType()) {
                case NONE:
                    piece.abortMove();
                    break;
                case NORMAL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    
                    turn.changeTurn();
                    computerMechanism();
                    break;
                case KILL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);

                    Piece otherPiece = result.getPiece();
                    board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);
                    
                    // comp mech
                    
                    redPiece.remove(otherPiece);
                    
                    break;
            }
            
            if(((newY == HEIGHT-1 && type == PieceType.RED) 
            		|| (newY == 0 && type == PieceType.WHITE)) && (!piece.getIsKing())) {
            	piece.changeToKing();
            	
            	turn.changeTurn();
            	computerMechanism();
            }
            
        });

        return piece;
    }

    
//    private void createGameLoop() {
//		AnimationTimer gameTimer = new AnimationTimer() {
//
//			@Override
//			public void handle(long now) {
//				if(!turn.getPlayerTurn()) {
//					computerMechanism();
//				}
//			}
//			
//		};
//		gameTimer.start();
//	}
//    temporary computer mechanism
    private void computerMechanism() {
    	
    	Piece pieceMove;
    	int point = 0;
    	int xNew = 0;
    	int yNew = 0;
    	
    	System.out.println("Computer");
    	
    	ArrayList<Integer> combi =new ArrayList<Integer>();
		combi.add(1);
		combi.add(-1);
		combi.add(2);
		combi.add(-2);
    	
    	for(Piece piece : redPiece) {
    		int temp;
    		int xNewTemp;
    		int yNewTemp;
    		
            for(int combiX:combi) {
            	for (int combiY:combi) {
            		int x0 = toBoard(piece.getOldX());
                    int y0 = toBoard(piece.getOldY());
            		xNewTemp = (int) (x0 + combiX);
                	yNewTemp = (int) (y0 + combiY);
                	
                	System.out.println(xNewTemp +" " + yNewTemp);
                	
                	MoveResult result;
                	
                	if (xNewTemp < 0 || yNewTemp < 0 || xNewTemp >= WIDTH || yNewTemp >= HEIGHT) {
                        result = new MoveResult(MoveType.NONE);
                    } else {
                        result = tryMove(piece, xNewTemp, yNewTemp);
                    }
                	
                	if(result.getType() != MoveType.NONE) {

                        switch (result.getType()) {
                            case NONE:
                                piece.abortMove();
                                break;
                            case NORMAL:
                                piece.move(xNewTemp, yNewTemp);
                                board[x0][y0].setPiece(null);
                                board[xNewTemp][yNewTemp].setPiece(piece);
                                
                                turn.changeTurn();
                                break;
                            case KILL:
                                piece.move(xNewTemp, yNewTemp);
                                board[x0][y0].setPiece(null);
                                board[xNewTemp][yNewTemp].setPiece(piece);

                                Piece otherPiece = result.getPiece();
                                board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                                pieceGroup.getChildren().remove(otherPiece);
                                
                                // comp mech
                                
                                redPiece.remove(otherPiece);
                                
                                turn.changeTurn();
                                break;
                        }
                        
                        if((yNewTemp == HEIGHT-1 ) 
                        		&& (!piece.getIsKing())) {
                        	piece.changeToKing();
                        	
                        	turn.changeTurn();
                        }
                		             		
                		return;
                	}
            	}
            }
    		
    	}
    }

    public static void main(String[] args) {
        launch(args);
    }
}