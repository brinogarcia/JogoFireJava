package Jogo;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;


public class FireScreen extends JFrame {

	
	
	public FireScreen(){
			add(new Fase());
			setTitle("Fire");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(1000,900);
			setLocationRelativeTo(null);
			setResizable(false);
			setVisible(true);
			
	}



}
