package model;

public class Bishop extends Piece{
	
	
	//Construtor
	public Bishop(int pieceColor) {
		super(pieceColor);
	}
	
	public String toString() {
		if(pieceColor == 1) {
			return "White Bishop";
		}else {
			return "Black Bishop";
		}
	}
	
	public Boolean move(int initialColumn, int initialrow, int lastColumn, int lastrow, Board board) {
		try{
			if(this.checkPosition(lastColumn, lastrow)) {
				Piece pec = board.getPiece(lastrow, lastColumn);
				if (pec == null||(this.pieceColor != pec.pieceColor)) {
					int i, j;
					int row = lastrow - initialrow;
					int column = lastColumn - initialColumn;
					if(Math.abs(row) == Math.abs(column)) {
						if(lastrow > initialrow && lastColumn > initialColumn) {
							j=initialColumn+1;
							for(i=initialrow+1; i<lastrow; i++) {
								if(board.getPiece(i, j) != null) {
									return false;
								}
								j++;
							}
						}else if(lastrow < initialrow && lastColumn < initialColumn){
							j=initialColumn-1;
							for(i=initialrow-1; i>lastrow; i--) {
								if(board.getPiece(i, j) != null) {
									return false;
								}
								j--;
							}
						}else if(lastrow < initialrow && lastColumn > initialColumn) {
							j=initialColumn+1;
							for(i=initialrow-1; i>lastrow; i--) {
								if(board.getPiece(i, j) != null) {
									return false;
								}
								j++;
							}
						}else if(lastrow > initialrow && lastColumn < initialColumn){
							j=initialColumn-1;
							for(i=initialrow+1; i<lastrow; i++) {
								if(board.getPiece(i, j) != null) {
									return false;
								}
								j--;
							}
						}
						board.setPiece(lastrow, lastColumn, this);
						board.setPiece(initialrow, initialColumn, null);
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
