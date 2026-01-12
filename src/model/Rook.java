package model;

public class Rook extends Piece{
	
	//Construtor
	public Rook(int pieceColor){
	    super(pieceColor);
	}
	
	public String toString() {
		if(pieceColor == 1) {
			return "White Rook";
		}else {
			return "Black Rook";
		}
	}
	
	public Boolean move(int initialColumn, int initialrow, int lastColumn, int lastrow, Board board) {
		try {
			if(this.checkPosition(lastColumn, lastrow)) {
				Piece piece = board.getPiece(lastrow, lastColumn);
				if ((piece == null)||(this.pieceColor != piece.pieceColor)) {
					int i;
					int row = lastrow - initialrow;
					int column = lastColumn - initialColumn;
					//move na horizontal
					if((Math.abs(row) == 0 && Math.abs(column) != 0)) {
						if(lastColumn > initialColumn) {
							for(i=initialColumn+1; i<lastColumn; i++) {
								if(board.getPiece(initialrow, i) != null) {
									return false;
								}
							}
						}else {
							for(i=initialColumn-1; i>lastColumn; i--) {
								if(board.getPiece(initialrow, i) != null) {
									return false;
								}
							}
						}
						board.setPiece(lastrow, lastColumn, null);
						board.setPiece(lastrow, lastColumn, this);
						board.setPiece(initialrow, initialColumn, null);
						this.firstMove = false;
						return true;
					//move na vertical
					}else if((Math.abs(row) != 0 && Math.abs(column) == 0)){
						if(lastrow > initialrow) {
							for(i=initialrow+1; i<lastrow; i++) {
								if(board.getPiece(i, initialColumn) != null) {
									return false;
								}
							}
						}else {
							for(i=initialrow-1; i>lastrow; i--) {
								if(board.getPiece(i, initialColumn) != null) {
									return false;
								}
							}
						}
						board.setPiece(lastrow, lastColumn, null);
						board.setPiece(lastrow, lastColumn, this);
						board.setPiece(initialrow, initialColumn, null);
						this.firstMove = false;
						return true;
					}
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(e);
			System.out.println("A peça não pode ser movimentada nessa casa");
		}
		return false;
	}
}

