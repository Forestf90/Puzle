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


import java.awt.Dimension;

import javax.swing.JOptionPane;

public class part extends JPanel {

	private BufferedImage zdj;
	private BufferedImage[] kawalek = new BufferedImage[9];
	
	private Rectangle[] sektor = new Rectangle[9];
	
	private int[] poczatek = new int[9]; 
	private int[] poczatek_id = new int[9];
	
	private int wys;
	private int szer;
	
	private int ruchy=0;
	private boolean victory=false;
	public Graphics2D calosc;
	
	public int startowy;
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
			else {
				kawalek[i] = new BufferedImage(szer/3, wys/3, BufferedImage.TYPE_INT_ARGB);
				Graphics2D    graphics = kawalek[i].createGraphics();

				graphics.setPaint ( Color.WHITE) ;
				graphics.fillRect ( 0, 0, kawalek[i].getWidth(), kawalek[i].getHeight() );
			}
			
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
			poczatek_id[i]=i;
		}
		Random r = new Random();
		int losowy;
		int temp;
		for(int i=0 ; i<poczatek.length ; i++) {
			
			losowy = r.nextInt(8)+0;
			
			temp = poczatek[i];
			poczatek[i]=poczatek[losowy];
			poczatek[losowy] = temp;
			
			
		}
		
		for(int i=0 ; i<poczatek.length ;i++) {
			if(poczatek[i]==8) {
				startowy=i;
				break;
			}
		}
		startowy =8;
	}
	

	
	
	public void paint(Graphics g) {
		calosc =(Graphics2D) g;
		int x=0 ,y=0;
		Color c= new Color(70,130,180);
		
		((Graphics2D) g).setStroke(new BasicStroke(5));
		g.setColor(c);
		
		
		for(int i=0 ; i<kawalek.length ; i++) {

			calosc.drawImage(kawalek[i] , (int)sektor[poczatek[i]].getMinX(),(int)sektor[poczatek[i]].getMinY(),
					(int)sektor[poczatek[i]].getWidth(), (int)sektor[poczatek[i]].getHeight() , null);
		}
		g.drawLine(szer/3, 0, szer/3, wys);
		g.drawLine(2*szer/3, 0, 2*szer/3, wys);
		g.drawLine(0, wys/3, szer, wys/3);
		g.drawLine(0, 2*szer/3, szer, 2*wys/3);
		
		if(victory) calosc.drawImage(zdj ,x,y,this);
		
	}
	
	public void przesuniecie(int kierunek) {	
		int docelowy=0;

		int startID= poczatek[8];
		switch(kierunek) {
		case 0: //dol - czyli gora
			if(startID>5) return;
			else docelowy =(startID+3);
			break;
		case 1: //lewo
			if(startID%3==0) return;
			else docelowy = (startID-1);
			break;
			
		case 2: //gora - czyli dol
			if(startID<3) return;
			else docelowy = (startID -3);
			break;
		case 3: //prawo
			if((startID+1)%3==0) return;
			else docelowy = (startID+1);
			break;
		}
		
	
			int jprd=0;
			for(int i=0 ; i<poczatek.length ; i++) {
				if(poczatek[i]==docelowy) jprd=i;
			}
				
			int temp = poczatek[8];
			poczatek[8]=poczatek[jprd];
			poczatek[jprd] = temp;
			
		
		startowy=8;
		ruchy++;
		zwyciestwo();
	}
	
	public void hack() {
		for(int i=0 ; i< poczatek.length ; i++) {
			poczatek[i]=i;
		}
			
		
	}
	
	public void zwyciestwo() {
		for(int i=0 ; i< poczatek.length ; i++) {
			if(poczatek[i]!=i)return;
		}
		
		repaint();
		JOptionPane.showMessageDialog(null, "Udalo Ci sie ulozyc puzle w "+ruchy+" ruchach", "Puzle ulozone", JOptionPane.INFORMATION_MESSAGE);
		victory= true;
		repaint();
		
		
		int response = JOptionPane.showConfirmDialog(null, "Zaczac od poczatku ?", "Jeszcze raz ?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
            System.exit(0);
    } 
        else if (response == JOptionPane.YES_OPTION) {
        	ruchy=0;
        	victory= false;
        	tasuj();
    } 
        else if (response == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
    }
		
	}
	

	
}
