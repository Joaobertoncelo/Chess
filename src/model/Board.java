package model;

public class Board {
	private Boolean BlackEnPassant = false;
	private Boolean WhiteEnPassant = false;
	private Piece[][] fullBoard;
    
    
  //Construtor
    public Board() {
        this.fullBoard = new Piece[8][8];
        placePieces();
    }
    public String toString(){
    	String string = new String();
    	for(int i=0; i<8;i++) {
    		for(int j=0; j<8;j++) {
    			Piece piece = fullBoard[i][j];
    			if (piece != null) {
    				System.out.print(piece.toString() + " ");
    			}else {
    				System.out.print("- ");
    			}
    		}
    		System.out.println();
    	}
    	return string;
    }

    private void placePieces() {
      // Place kings
      fullBoard[0][4] = new King(1);
      fullBoard[7][4] = new King(2);
  
      // Place queens
      fullBoard[0][3] = new Queen(1);
      fullBoard[7][3] = new Queen(2);
  
      // Place bishops
      fullBoard[0][2] = new Bishop(1);
      fullBoard[0][5] = new Bishop(1);
      fullBoard[7][2] = new Bishop(2);
      fullBoard[7][5] = new Bishop(2);
  
      // Place knights
      fullBoard[0][1] = new Knight(1);
      fullBoard[0][6] = new Knight(1);
      fullBoard[7][1] = new Knight(2);
      fullBoard[7][6] = new Knight(2);
  
      // Place rooks
      fullBoard[0][0] = new Rook(1);
      fullBoard[0][7] = new Rook(1);
      fullBoard[7][0] = new Rook(2);
      fullBoard[7][7] = new Rook(2);
  
      // Place pawns
      for (int i = 0; i < 8; i++) {
          fullBoard[1][i] = new Pawn(1);
          fullBoard[6][i] = new Pawn(2);
      }
  }
    
    public Piece getPiece(int row, int column){
    	return fullBoard[row][column];
    }
    
    public void setPiece(int row, int column, Piece p) {
    	fullBoard[row][column] = p;
    }
    
    public Boolean getBlackEnPassant() {
		return BlackEnPassant;
	}
	
	public void setBlackEnPassant(Boolean enPassant) {
		this.BlackEnPassant = enPassant;
	}
	
	public Boolean getWhiteEnPassant() {
		return WhiteEnPassant;
	}
	
	public void setWhiteEnPassant(Boolean enPassant) {
		this.WhiteEnPassant = enPassant;
	}
}
