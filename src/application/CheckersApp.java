package application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    		
    	Piece pieceMove = null;
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
    		int temp = 0;
    		int xNewPiece = 0;
    		int yNewPiece = 0;
    		int xNewPieceTemp;
    		int yNewPieceTemp;
    		
            for(int combiX:combi) {
            	for (int combiY:combi) {
            		int x0 = toBoard(piece.getOldX());
                    int y0 = toBoard(piece.getOldY());
            		xNewPieceTemp = (int) (x0 + combiX);
                	yNewPieceTemp = (int) (y0 + combiY);
                	
                	MoveResult result;
                	
                	if (xNewPieceTemp < 0 || yNewPieceTemp < 0 || xNewPieceTemp >= WIDTH || yNewPieceTemp >= HEIGHT) {
                        result = new MoveResult(MoveType.NONE);
                    } else {
                        result = tryMove(piece, xNewPieceTemp, yNewPieceTemp);
                    }
	
                        switch (result.getType()) {
                            case NORMAL:
                                temp+=1;
                                if(temp <= 16) {
                                	
                                	xNewPiece = xNewPieceTemp;
                                    yNewPiece = yNewPieceTemp;
                                    System.out.println("(Ineer )xNewPiece yNewPiece:" + xNewPiece + yNewPiece);
                                }
                                
                                if((yNewPieceTemp == HEIGHT-1 ) 
                                		&& (!piece.getIsKing())) {
                                		temp+=16;
                                }
                                break;
                            case KILL:
                                temp +=16;
                                xNewPiece = xNewPieceTemp;
                                yNewPiece = yNewPieceTemp;
                                
                                if((yNewPieceTemp == HEIGHT-1 ) 
                                		&& (!piece.getIsKing())) {
                                		temp+=16;
                                }
                                break;
						default:
							break;
                        }
                        
                		             	
            	}
            }
            System.out.println("temp : " + temp);
            System.out.println("xNewPiece yNewPiece:" + xNewPiece + yNewPiece);
            System.out.println("Score : " + point);
            
            if(temp>=point) {
            	xNew = xNewPiece;
            	yNew = yNewPiece;
            	pieceMove = piece;
            	point = temp;
            }
    		
    	}
    	
    	System.out.println("Comp : " + xNew + " " + yNew);

        MoveResult result;

        int x0 = toBoard(pieceMove.getOldX());
        int y0 = toBoard(pieceMove.getOldY());
        
        result = tryMove(pieceMove, xNew, yNew);

        switch (result.getType()) {
            case NORMAL:
                pieceMove.move(xNew, yNew);
                board[x0][y0].setPiece(null);
                board[xNew][yNew].setPiece(pieceMove);
                
                turn.changeTurn();
                break;
            case KILL:
                pieceMove.move(xNew, yNew);
                board[x0][y0].setPiece(null);
                board[xNew][yNew].setPiece(pieceMove);

                Piece otherPiece = result.getPiece();
                board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                pieceGroup.getChildren().remove(otherPiece);
                
                turn.changeTurn();
                
                break;
		default:
			break;
        }
        
        if((yNew == HEIGHT-1) && (!pieceMove.getIsKing())) {
        	pieceMove.changeToKing();
        }
    	
    }

    public static void main(String[] args) {
        launch(args);
    }
}