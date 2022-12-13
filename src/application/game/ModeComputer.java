package application.game;

import java.util.ArrayList;

import application.model.MoveResult;
import application.model.MoveType;
import application.model.Piece;
import application.model.PieceType;

public class ModeComputer extends GameBase{
	
	@Override
	protected Piece makePiece(PieceType type, int x, int y) {
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
            int GameResult = 0;
            switch (result.getType()) {
                case NONE:
                    piece.abortMove();
                    break;
                case NORMAL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    
                    if(((newY == HEIGHT-1 && type == PieceType.RED) 
                    		|| (newY == 0 && type == PieceType.WHITE)) && (!piece.getIsKing())) {
                    	piece.changeToKing();
                    	
                    	turn.changeTurn();
                    	computerMechanism();
                    	break;
                    }
                    
                    turn.changeTurn();
                    computerMechanism();
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
                    
                    if(((newY == HEIGHT-1 && type == PieceType.RED) 
                    		|| (newY == 0 && type == PieceType.WHITE)) && (!piece.getIsKing())) {
                    	piece.changeToKing();
                    	
                    	turn.changeTurn();
                    	computerMechanism();
                    	break;
                    }
                    break;
            }
            
            
            if(GameResult!=0) {
            	System.out.println(GameResult);
            	if(GameResult==1) {
            		System.out.println("Red WIN");
            		resScene.setTxt("Red");
            	}else if(GameResult==2) {
            		System.out.println("White WIN");
            		resScene.setTxt("White");
            	}
        		root.getChildren().add(resScene);
            }
        });

        return piece;
    }
	
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
        if(GameResult!=0) {
        	if(GameResult==1) {
        		resScene.setTxt("Red");
        	}else if(GameResult==2) {
        		resScene.setTxt("White");
        	}
    		root.getChildren().add(resScene);
        }
	}
}
