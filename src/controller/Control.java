package controller;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import model.*;
import view.*;

public class Control {
	//Cliques
	private int initialrow, initialColumn;
    private int lastrow, lastColumn;
    //Branca ou não
    private Boolean white = true;
    //Validação de pieceColor
    private int pieceColor = 0, initialPieceColor, lastPieceColor;
    //Tabuleiro
    private Board board = new Board();
    //pieceImages
    private Piece initialPosition, finalPosition;
    //Testar caminho Livre
    private Boolean freePath = true;
    //Testar numero de reis
    private int numberOfKings = 0;

    public void setFirst_Click_Coordinates(int initialrow, int initialColumn) {
        this.initialrow = initialrow;
        this.initialColumn = initialColumn;
    }

    public void setSecond_Click_Coordinates(int lastrow, int lastColumn) {
    	this.lastrow = lastrow;
        this.lastColumn = lastColumn;
    }

    public Boolean processClicks(JButton[] buttons) {
		defineTurn();
		selectUserPiece();
		if(initialPosition == null) {
			return false;
		}
		initialPieceColor = initialPosition.getPieceColor();
		//confere se a pieceImage é dele
		if(initialPosition != null && (initialPieceColor == pieceColor)) {
			//Confere o destino da peça
			finalPosition=board.getPiece(lastrow, lastColumn);
			if(finalPosition != null) {
				lastPieceColor=finalPosition.getPieceColor();
			}
			castle();
			//Confere se o movimento é possível
			if(finalPosition == null || lastPieceColor != pieceColor) {
				freePath = initialPosition.move(initialColumn, initialrow, lastColumn, lastrow, board);
				if(freePath) {
					String movePiece = ""; // Initialize the movePiece variable
					if(pieceColor == 1) {
						movePieceToSelectedPosition(7, movePiece);
					}else if(pieceColor == 2) {
						movePieceToSelectedPosition(0, movePiece);
					}
					checkGameOver();
					white = !white;
					return true;
				}
			}
		}
		return freePath;
    }

	public void defineTurn(){
		if(white) {
			pieceColor = 1;
			board.setWhiteEnPassant(false);
		}else {
			pieceColor = 2;
			board.setBlackEnPassant(false);
		}
	}

	public void selectUserPiece(){
		initialPosition=board.getPiece(initialrow, initialColumn);
	}

	public void movePieceToSelectedPosition(int row, String movePiece){
		for (int column = 0; column<8; column++) {
			Piece piece = board.getPiece(row, column);
			if(piece instanceof Pawn) {
				movePiece = Frame.promotePawn();
				piece = ((Pawn) piece).promote(movePiece, row, column, pieceColor);
				board.setPiece(row, column, piece);
			}
		}
	}

	public void checkGameOver(){
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece isKing = board.getPiece(i, j);
				if(isKing instanceof King) {
					numberOfKings++;
				}
			}
		}
		if(numberOfKings!=2) {
			Frame.gameOver();
		}else {
			numberOfKings = 0;
		}
	}

	public boolean castle(){
		if((initialPosition instanceof King)&&(finalPosition instanceof Rook)) {
			freePath = initialPosition.move(initialColumn, initialrow, lastColumn, lastrow, board);
			if(freePath) {
				white = !white;
				return true;
			}
		}
		return false;
	}
    
    public void updateBoard(JButton[] buttons) {
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            Piece piece = board.getPiece(i, j);
	            if (piece == null) {
	                buttons[i * 8 + j].setIcon(null);
	            } else {
	            	Icon pieceImage = new ImageIcon(getClass().getResource("/images/" + piece.toString() + ".png"));
	                buttons[i * 8 + j].setIcon(pieceImage);
	            }
	        }
	    }
	}
}
