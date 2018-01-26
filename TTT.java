package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TTT extends JFrame implements ActionListener {
	
	private enum GameStatus{
		XWon,
		OWon,
		Tie,
		Incomplete
	}
	
	private int board_size=3;
	private boolean crossTurn;
	private JButton[][] buttons= new JButton[board_size][board_size];
	
	public TTT(){
		super.setTitle("TIC TAC TOE");
		super.setSize(800, 800);
		
		GridLayout layout=new GridLayout(board_size, board_size);
		super.setLayout(layout);
		
		Color c=new Color(00101000);
		
		
		Font font= new Font("Comic Sans MS", 1, 100);     //1->bold
		
		for(int i=0; i<board_size; i++){
			for(int j=0; j<board_size; j++){
				JButton btn=new JButton("");
				
				btn.setFont(font);
				btn.setBackground(c);
				
				buttons[i][j]=btn;
				btn.addActionListener(this);
				super.add(btn);
		
			}
		}
		
		
		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JButton cb=(JButton)e.getSource();
		
		makeMove(cb);
		
		GameStatus gs=this.getGameStatus();
		
		if(gs==GameStatus.Incomplete){
			return;
		}
		
		declareWinner(gs);
		
		int choice=JOptionPane.showConfirmDialog(this, "RESTART?");
		if(choice==JOptionPane.YES_OPTION){
			for(int i=0; i<board_size;i++){
				for(int j=0;j<board_size;j++){
					buttons[i][j].setText("");
				}
			}
			this.crossTurn=true;
		}else{
			super.dispose();
		}
		
	}
	
	
	private void makeMove(JButton cb){
		String text=cb.getText();
		
		
		if(text.length()>0){
			JOptionPane.showMessageDialog(this, "Invalid move");
			return;
		}
		
		if(this.crossTurn){
			cb.setText("X");
		}else{
			cb.setText("O");
		}
		
		this.crossTurn=!this.crossTurn;
		
		
	}
	
	
	private GameStatus  getGameStatus( ){
          String text1="", text2="", text3="";
		//rows
		for(int i=0; i<board_size; i++){
			text1=buttons[i][0].getText();
			text2=buttons[i][1].getText();
			text3=buttons[i][2].getText();
				
			if(text1.equals(text2) && text2.equals(text3) && !text1.equals("")){
				if(text1.equals("X")){
					return GameStatus.XWon;
				}else{
					return GameStatus.OWon;
				}
			}
			             
			}
		//columns
		for(int i=0; i<board_size; i++){
			text1=buttons[0][i].getText();
			text2=buttons[1][i].getText();
			text3=buttons[2][i].getText();
				
			if(text1.equals(text2) && text2.equals(text3) && !text1.equals("")){
				if(text1.equals("X")){
					return GameStatus.XWon;
				}else{
					return GameStatus.OWon;
				}
			}
			             
			}
		
		//diagonal1
		text1=buttons[0][0].getText();
		text2=buttons[1][1].getText();
		text3=buttons[2][2].getText();
			
		if(text1.equals(text2) && text2.equals(text3) && !text1.equals("")){
			if(text1.equals("X")){
				return GameStatus.XWon;
			}else{
				return GameStatus.OWon;
			}
		}
		             
		//diagonal2
		text1=buttons[0][2].getText();
		text2=buttons[1][1].getText();
		text3=buttons[2][0].getText();
			
		if(text1.equals(text2) && text2.equals(text3) && !text1.equals("")){
			if(text1.equals("X")){
				return GameStatus.XWon;
			}else{
				return GameStatus.OWon;
			}
		}
		
		for(int i=0; i<board_size; i++){
			for(int j=0;j <board_size;j++){
				if(buttons[i][j].getText().length()==0){
					return GameStatus.Incomplete;
				}
			}
		}
	return GameStatus.Tie;
		
	}
	
	private void declareWinner(GameStatus gs){
		if(gs==GameStatus.XWon){
			JOptionPane.showMessageDialog(this, "X WON");
		}else if(gs==GameStatus.OWon){
			JOptionPane.showMessageDialog(this, "O WON");
		}else if(gs==GameStatus.Tie){
			JOptionPane.showMessageDialog(this, "TIED");
		}
		
		}
		
}
