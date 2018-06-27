package puzle;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


import java.util.Random;

public class part extends JPanel {

	private BufferedImage zdj;
	private BufferedImage[] kawalek = new BufferedImage[9];
	
	private Rectangle[] sektor = new Rectangle[9];
	
	private int[] poczatek = new int[9]; 
	
	private int wys;
	private int szer;
	
	public part() {

		
		File sciezka = new File("images/java.jpg");
		try {
		zdj = ImageIO.read(sciezka);
		}
		catch(IOException e)
		{
			//nie wiem
			System.err.println("Blad odczytu obrazka");
			e.printStackTrace();
			
		}
		
		wys =zdj.getHeight();
		szer = zdj.getWidth();
		Dimension rozmiar = new Dimension(szer, wys);
		setPreferredSize(rozmiar);
		
		tasuj();
		tnij();
		
	}
	
	
	public void tnij() {
		
		int x=-0 ,y=0;
		for(int i=0 ; i<kawalek.length ; i++){

			
			if(i!=8)kawalek[i] =zdj.getSubimage(x, y, szer/3, wys/3);
			
			sektor[i]= new Rectangle(x,y, szer/3 , wys/3);
			
			if(x==200) {
				x=0;
				y+=100;
			}
			else x+=100;

		}
		
	}
	
	private void tasuj()
	{
		for(int i=0 ; i<poczatek.length ; i++) {
			poczatek[i]= i;
		}
		Random r = new Random();
		int losowy;
		int temp;
		for(int i=0 ; i<poczatek.length ; i++) {
			
			losowy = r.nextInt(7)+0;
			
			temp = poczatek[i];
			poczatek[i]=poczatek[losowy];
			poczatek[losowy] = temp;
			
			
		}
		
		
	}
	
	
	public void paint(Graphics g) {
		Graphics2D calosc =(Graphics2D) g;
		int x=0 ,y=0;
		
		for(int i=0 ; i<kawalek.length ; i++) {
			//calosc.drawImage(kawalek[i] ,x,y,this);
			//sektor[i]= new Rectangle(x,y, szer/3 , wys/3);
			calosc.drawImage(kawalek[i] , (int)sektor[poczatek[i]].getMinX(),(int)sektor[poczatek[i]].getMinY(),
					(int)sektor[poczatek[i]].getWidth(), (int)sektor[poczatek[i]].getHeight() , null);
		}
		
	}
	
}
