package model;

public abstract class Piece{
	int pieceColor;
	Boolean firstMove = true;
        
	//Construtor
    public Piece(int pieceColor){
        this.pieceColor = pieceColor;
    }
        
	public String toString() {
		if(pieceColor == 1) {
			return "Piece";
		}else {
			return "piece";
		}
	}
	//confere se a peca está no tabuleiro
    public boolean checkPosition(int x,int y){
        if(x<= 8 && y <= 8 && x >=0 && y >= 0){
            return true;
        }else{
            return false;
        }
    }
        
	public abstract Boolean move(int initialColumn, int initialrow, int lastColumn, int lastrow, Board tab);
	
	public int getPieceColor(){
    	return pieceColor;
    }
}
