package model;

public class Queen extends Piece{
    
	
	//Construtor
    public Queen(int pieceColor){
        super(pieceColor);
    }
    
	public String toString() {
		if(pieceColor == 1) {
			return "White Queen";
		}else {
			return "Black Queen";
		}
	}
	
	public Boolean move(int initialColumn, int initialrow, int lastColumn, int lastrow, Board board) {
		try {
			if(this.checkPosition(lastColumn, lastrow)) {
				Piece pec = board.getPiece(lastrow, lastColumn);
				if (pec == null||(this.pieceColor != pec.pieceColor)) {
					int i,j;
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
					//move na diagonal
					}else if(Math.abs(row) == Math.abs(column)) {
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
					}
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(e);
			System.out.println("A peça não pode ser movimentada nessa casa");
		}
		return true;
	}
}

