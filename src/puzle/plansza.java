package puzle;

import java.awt.*;
import java.io.IOException;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import javax.swing.JPanel;

public class plansza extends JFrame{

	
	public plansza() {
		super("Puzle");
		
		
		JPanel obraz= new part();
		add(obraz);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350 , 350);
		//pack();
		setVisible(true);
		
		
	}
	

	
}
