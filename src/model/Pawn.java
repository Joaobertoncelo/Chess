package model;

public class Pawn extends Piece{
	
	
	//Construtor
	public Pawn(int pieceColor){
       super(pieceColor);
	}
	
	public String toString() {
		if(pieceColor == 1) {
			return "White Pawn";
		}else {
			return "Black Pawn";
		}
	}
	
	public Boolean move(int initialColumn, int initialrow, int lastColumn, int lastrow, Board board) {

		try {
			if(this.checkPosition(lastColumn, lastrow)) {
				Piece piece = board.getPiece(lastrow, lastColumn);
				int row = lastrow - initialrow;
				int column = lastColumn - initialColumn;
				if(Math.abs(row) == 1 && Math.abs(column) == 0 && piece == null) {
					if(pieceColor == 1 && (lastrow > initialrow)) {
						board.setPiece(lastrow, lastColumn, this);
						board.setPiece(initialrow, initialColumn, null);
						this.firstMove = false;
						return true;
					}else if(pieceColor == 2 && (lastrow < initialrow)){
						board.setPiece(lastrow, lastColumn, this);
						board.setPiece(initialrow, initialColumn, null);
						this.firstMove = false;
						return true;
					}else {
						return false;
					}
				}
				if (((Math.abs(column) == 1))&&(Math.abs(row) == 1)) {
					//en Passant
					if((initialColumn+1)<8 || (initialColumn-1)>8) {
						int leftPawn = initialColumn-1;
						Piece left = board.getPiece(initialrow, leftPawn);
						int rightPawn = initialColumn+1;
						Piece right = board.getPiece(initialrow, rightPawn);
						if(pieceColor == 1) {
							if(((left instanceof Pawn)&&(board.getBlackEnPassant())&&lastColumn<initialColumn)) {
								board.setPiece(initialrow, initialColumn-1, null);
								board.setPiece(lastrow, lastColumn, null);
								board.setPiece(lastrow, lastColumn, this);
								board.setPiece(initialrow, initialColumn, null);
								this.firstMove = false;
								return true;
							}else if((right instanceof Pawn)&&(board.getBlackEnPassant()&&lastColumn>initialColumn)) {
								board.setPiece(initialrow, initialColumn+1, null);
								board.setPiece(lastrow, lastColumn, null);
								board.setPiece(lastrow, lastColumn, this);
								board.setPiece(initialrow, initialColumn, null);
								this.firstMove = false;
								return true;
							}
						}else if (pieceColor == 2) {
							if((left instanceof Pawn)&&(board.getWhiteEnPassant()&&lastColumn<initialColumn)) {
								board.setPiece(initialrow, initialColumn-1, null);
								board.setPiece(lastrow, lastColumn, null);
								board.setPiece(lastrow, lastColumn, this);
								board.setPiece(initialrow, initialColumn, null);
								this.firstMove = false;
								return true;
							}else if((right instanceof Pawn)&&(board.getWhiteEnPassant()&&lastColumn>initialColumn)) {
								board.setPiece(initialrow, initialColumn+1, null);
								board.setPiece(lastrow, lastColumn, null);
								board.setPiece(lastrow, lastColumn, this);
								board.setPiece(initialrow, initialColumn, null);
								this.firstMove = false;
								return true;
							}
						}
					}
					//Captura Peça
					if(piece != null) {
						if(pieceColor == 1 && row > 0) {
							board.setPiece(lastrow, lastColumn, null);
							board.setPiece(lastrow, lastColumn, this);
							board.setPiece(initialrow, initialColumn, null);
							this.firstMove = false;
							return true;
						}else if(pieceColor == 2 && row < 0){
							board.setPiece(lastrow, lastColumn, null);
							board.setPiece(lastrow, lastColumn, this);
							board.setPiece(initialrow, initialColumn, null);
							this.firstMove = false;
							return true;
						}else {
							return false;
						}
					}
				}
				//Primeiro movimento pode move duas casas
				if((this.firstMove == true) && (Math.abs(column) == 0 && Math.abs(row) == 2)) {
					board.setPiece(lastrow, lastColumn, this);
					board.setPiece(initialrow, initialColumn, null);
					this.firstMove = false;
					if(pieceColor == 1) {
						board.setWhiteEnPassant(true);
					}else if(pieceColor ==2) {
						board.setBlackEnPassant(true);
					}
					return true;
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(e);
			System.out.println("A peça não pode ser movimentada nessa casa");
		}
		return false;
	} 
	
	public Piece promote(String piece, int row, int column, int pieceColor) {
		if(piece == "Queen") {
			Queen queen = new Queen(pieceColor);
	        return queen;
		}else if(piece == "Bishop") {
			Piece bishop = new Bishop(pieceColor);
	        return bishop;
		}else if(piece == "Knight") {
			Piece knight = new Knight(pieceColor);
	        return knight;
		}else if(piece == "Rook") {
			Piece rook = new Rook(pieceColor);
	        return rook;
		}
		return null;
	}
	
}

