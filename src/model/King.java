package model;

import java.lang.ArrayIndexOutOfBoundsException;

public class King extends Piece{
	
	//Construtor
	public King(int pieceColor){
		super(pieceColor);
	}
    
	public String toString() {
		if(pieceColor == 1) {
			return "White King";
		}else {
			return "Black King";
		}
	}
	
	public Boolean move(int initialColumn, int initialrow, int lastColumn, int lastrow, Board board) {	
		try {
			//move normal
			if(this.checkPosition(lastColumn, lastrow)) {
				Piece pec = board.getPiece(lastrow, lastColumn);
				int row = lastrow - initialrow;
				int column = lastColumn - initialColumn;
				if (pec == null||(this.pieceColor != pec.pieceColor)) {
					if(Math.abs(row) <= 1 && Math.abs(column) <=1) {
							board.setPiece(lastrow, lastColumn, this);
							board.setPiece(initialrow, initialColumn, null);
							this.firstMove = false;
							return true;
					}			
				}
				//roque
				//confere se é o primeiro movimento
				if ((this.firstMove)) {
					Piece rook;
					rook = board.getPiece(lastrow, lastColumn);
					//confere se a peça no canto é torre
					if(rook instanceof Rook) {
						if(rook.pieceColor == this.pieceColor) {
							//confere se é o primeiro movimento da torre
							if(rook.firstMove) {
								//direita vazia 
								if(column>0 
										&&(board.getPiece(initialrow,initialColumn+1) == null)
										&&(board.getPiece(initialrow,initialColumn+2) == null)) {
									System.out.println(rook);
									board.setPiece(initialrow, initialColumn+2, this);
									board.setPiece(initialrow, initialColumn, null);
									board.setPiece(initialrow, initialColumn+1, rook);
									board.setPiece(lastrow, lastColumn, null);
									this.firstMove = false;
									return true;
								//Esquerda vazia
								}else if(column<0 
										&& (board.getPiece(initialrow, initialColumn-1) == null)
										&&((board.getPiece(initialrow, initialColumn-2) == null)
										&&(board.getPiece(initialrow, initialColumn-3) == null))) {
									board.setPiece(initialrow, initialColumn-2, this);
									board.setPiece(initialrow, initialColumn, null);
									board.setPiece(initialrow, initialColumn-1, rook);
									board.setPiece(lastrow, lastColumn, null);
									this.firstMove = false;
									return true;
								}
							}
						}
					}
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(e);
			System.out.println("A peça não pode ser movimentada nessa casa \n");
		}
		return false;
	}
}