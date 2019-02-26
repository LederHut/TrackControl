
import java.awt.Color;

import javax.swing.*;

public class JWindow {
	
	static JButton button;
	
	public JWindow() {
		
		JFrame Frame = new JFrame("Fenstertext");
		Frame.pack();
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setResizable(false);
		Frame.setSize(720, 720);
		Frame.setLayout(null);
		Frame.requestFocus();
		Frame.setBackground(Color.WHITE);
		
		button = new JButton("Knopf");
		button.setBounds(25, 25, 128, 64);
		button.setBackground(new Color(0,190,0));
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		button.addActionListener(new ActionHandler());
		button.setVisible(true);
		
		Frame.add(button);
		
		Frame.setVisible(true);
		
	}

	public static void main(String[] args) {
		new JWindow();

	}

}
