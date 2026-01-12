package model;

public class Knight extends Piece{
	
	
	//Construtor
	public Knight(int pieceColor) {
		super(pieceColor);
	}
	
	public String toString() {
		if(pieceColor == 1) {
			return "White Knight";
		}else {
			return "Black Knight";
		}
	}
	
	public Boolean move(int initialColumn, int initialrow, int lastColumn, int lastrow, Board board) {
		try {
			if(this.checkPosition(lastColumn, lastrow)) {
				Piece pec = board.getPiece(lastrow, lastColumn);
				if (pec == null||(this.pieceColor != pec.pieceColor)) {
					int row = lastrow - initialrow;
					int column = lastColumn - initialColumn;
					if((Math.abs(row) ==1 && Math.abs(column) ==2) || (Math.abs(row) ==2 && Math.abs(column) ==1)) {
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
