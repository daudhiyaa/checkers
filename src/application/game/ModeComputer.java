package application.game;

import java.util.ArrayList;
import java.util.Collections;

import application.model.MoveResult;
import application.model.MoveType;
import application.model.Piece;
import application.model.PieceType;
import application.model.Tile;
import javafx.animation.AnimationTimer;

public class ModeComputer extends GameBase{
	
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
        root.getChildren().add(turn.turnUI());
        createGameLoop();
        
    }
	
	private void createGameLoop() {
		AnimationTimer gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				if(!turn.getPlayerTurn()) {
					computerMechanism();
				}
			}
			
		};
		gameTimer.start();
	}
	
	private void computerMechanism() {
		Piece pieceMove = null;
    	int point = 0;
    	int xNew = 0;
    	int yNew = 0;
    	
    	ArrayList<Integer> combi =new ArrayList<Integer>();
		combi.add(1);
		combi.add(-1);
		combi.add(2);
		combi.add(-2);
		
		Collections.shuffle(combi);
    	
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
            
            if(temp>=point) {
            	xNew = xNewPiece;
            	yNew = yNewPiece;
            	pieceMove = piece;
            	point = temp;
            }
    		
    	}
    	

        MoveResult result;

        int x0 = toBoard(pieceMove.getOldX());
        int y0 = toBoard(pieceMove.getOldY());
        int GameResult = 0;
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
                whitePiece.remove(otherPiece);
                pieceGroup.getChildren().remove(otherPiece);
                GameResult = GameRes();
                turn.changeTurn();
                
                break;
		default:
			break;
        }
        
        if((yNew == HEIGHT-1) && (!pieceMove.getIsKing())) {
        	pieceMove.changeToKing();
        }
        
        if(point == 0) {
    		GameResult = 3;
    	}
        if(GameResult!=0) {
        	if(GameResult==1) {
        		resScene.setTxt("Red Win!");
        	}else if(GameResult==2) {
        		resScene.setTxt("White Win!");
        	}else {
        		resScene.setTxt("DRAW!");
        	}
    		root.getChildren().add(resScene);
        }
	}
}
