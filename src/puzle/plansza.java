package puzle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class plansza extends JFrame implements KeyListener{

	public JPanel obraz = new part();
	public plansza() {
		super("Puzle");
		
		
		//obraz= new part();
		add(obraz);
		addKeyListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setSize(350 , 350);
		pack();
		setVisible(true);
		
		
	}
	
	public void keyReleased(KeyEvent evt) {
		int c=evt.getKeyCode();
		if(c ==KeyEvent.VK_RIGHT)   ((part) obraz).przesuniecie(3);
		if(c ==KeyEvent.VK_UP)   ((part) obraz).przesuniecie(2);
		if(c ==KeyEvent.VK_LEFT)   ((part) obraz).przesuniecie(1);
		if(c ==KeyEvent.VK_DOWN)   ((part) obraz).przesuniecie(0);
		if(c ==KeyEvent.VK_SPACE) ((part) obraz).hack();
		obraz.repaint();
	}
	
	public void keyPressed(KeyEvent evt) {
	}
	
	public void keyTyped(KeyEvent evt) {
	}
	
}
