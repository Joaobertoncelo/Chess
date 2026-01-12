package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import controller.Control;

public class Frame extends JFrame implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	public final JButton[] buttons; 
	private final GridLayout gridLayout; 
	private int[] first_Click_Coordinates = {-1, -1};
    private int[] second_Click_Coordinates = {-1, -1};

	Control control = new Control();

		
	public Frame(){
	    super("Chess");
	    String[] chessPieces = {"White King", "White Queen", "White Rook", "White Knight", "White Bishop", "White Pawn",
                				"Black King", "Black Queen", "Black Rook", "Black Knight", "Black Bishop", "Black Pawn"};
	    this.buttons = new JButton[64];
	    
	    for (String piece : chessPieces) {
	    	Icon icon = new ImageIcon(getClass().getResource("/images/" + piece + ".png"));
	    	new JLabel(icon);
	    	if(piece.equals("White King")) {
	    		buttons[4] = new JButton(icon);
	    	}else if(piece.equals("White Queen")) {
	    		buttons[3] = new JButton(icon);
	    	}else if(piece.equals("White Rook")) {
	    		buttons[0] = new JButton(icon);
	    		buttons[7] = new JButton(icon);
	    	}else if(piece.equals("White Knight")) {
	    		buttons[1] = new JButton(icon);
	    		buttons[6] = new JButton(icon);
	    	}else if(piece.equals("White Bishop")) {
	    		buttons[2] = new JButton(icon);
	    		buttons[5] = new JButton(icon);
	    	}else if(piece.equals("White Pawn")) {
	    		for (int i = 8; i < 16; i++){
	    			buttons[i] = new JButton(icon);
	    		}
	    	}else if(piece.equals("Black King")) {
	    		buttons[60] = new JButton(icon);
	    	}else if(piece.equals("Black Queen")) {
	    		buttons[59] = new JButton(icon);
	    	}else if(piece.equals("Black Rook")) {
	    		buttons[56] = new JButton(icon);
	    		buttons[63] = new JButton(icon);
	    	}else if(piece.equals("Black Knight")) {
	    		buttons[57] = new JButton(icon);
	    		buttons[62] = new JButton(icon);
	    	}else if(piece.equals("Black Bishop")) {
	    		buttons[58] = new JButton(icon);
	    		buttons[61] = new JButton(icon);
	    	}else if(piece.equals("Black Pawn")) {
	    		for (int i = 48; i < 56; i++){
	    			buttons[i] = new JButton(icon);
	    		}
	    	}
	    }
	    //Empty points
	    for (int i = 0; i < 64; i++){
	    	if(buttons[i]==null) {
	    		buttons[i] = new JButton(" ");
	    	}
	    }
	    for (int i = 0; i < 64; i++) {
	        buttons[i].addActionListener(this);
	    }
	    
	    set_Board_Colors();
	    
	    //Plot Board
	    for (int i = 0; i < 64; i++){
	    	if(buttons[i]!=null) {
	    		add(buttons[i]);
	    	}
	    }
	    
	    gridLayout = new GridLayout(8, 8, 5, 5); // 2 por 3; gaps de 5
	    getContentPane();
	    setLayout(gridLayout); 
	    
	    for (int i = 0; i < 64; i++) {
	        buttons[i].addActionListener(this);
	    }
	}

	@Override
	public void actionPerformed(ActionEvent event){ 
    	JButton button = (JButton) event.getSource();
    	int[] coordinates = getButtonCoordinates(button);
    	if (isFirstClick()) {
    	    setFirstClickCoordinates(coordinates);
    	} else if (isDifferentCoordinate(coordinates)) {
    	    setSecondClickCoordinates(coordinates);
    	    resetClickCoordinates();
    	}
	}

	private int[] getButtonCoordinates(JButton button) {
    	int row = -1;
    	int column = -1;
    	for (int i = 0; i < 64; i++) {
    	    if (buttons[i] == button) {
    	        row = i / 8;
    	        column = i % 8;
    	        break;
    	    }
    	}
    	return new int[]{row, column};
	}

	private boolean isFirstClick() {
    	return first_Click_Coordinates[0] == -1;
	}

	private boolean isDifferentCoordinate(int[] coordinates) {
	    return coordinates[0] != first_Click_Coordinates[0] || coordinates[1] != first_Click_Coordinates[1];
	}

	private void setFirstClickCoordinates(int[] coordinates) {
	    first_Click_Coordinates[0] = coordinates[0];
	    first_Click_Coordinates[1] = coordinates[1];
	    control.setFirst_Click_Coordinates(coordinates[0], coordinates[1]);
	}

	private void setSecondClickCoordinates(int[] coordinates) {
    	second_Click_Coordinates[0] = coordinates[0];
    	second_Click_Coordinates[1] = coordinates[1];
    	control.setSecond_Click_Coordinates(coordinates[0], coordinates[1]);
    	control.processClicks(buttons);              
    	control.updateBoard(buttons);
	}

	private void resetClickCoordinates() {
    	first_Click_Coordinates[0] = -1;
    	first_Click_Coordinates[1] = -1;
    	second_Click_Coordinates[0] = -1;
    	second_Click_Coordinates[1] = -1;
	}
	
	public void set_Board_Colors() {
		Boolean white = true;
	    for (int i = 0; i < 64; i++){
	    	if(white) {
	    		buttons[i].setBackground(Color.ORANGE);
	    	}else {
	    		buttons[i].setBackground(Color.BLUE);
	    	}
	    	white = !white;
	    	if((i == 7)||(i==14)||(i==23)||(i==31)||(i==39)||(i==47)||(i==55)) {
	    		white = !white;
	    	}
	    }
	    buttons[15].setBackground(Color.ORANGE);
	}
	
	
	public static String promotePawn() {
		Frame frame = new Frame();
		Object[] possibilities = {"Queen", "Bishop", "Knight", "Rook"};
		String promotionDescription = (String)JOptionPane.showInputDialog(
                frame,
                "Promote to:\n"
                + " ",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null, possibilities,
                "ham");
		return promotionDescription;
	}
	
	public static void gameOver() {
		Frame frame = new Frame();
		JOptionPane.showMessageDialog(frame,
			    "Game Over.");
	}
	 
	public static void main(String[] args)
	{ 
		Frame frame = new Frame(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800); 
		frame.setVisible(true); 
	}	 
}