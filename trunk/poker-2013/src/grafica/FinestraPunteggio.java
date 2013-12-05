package grafica;
                         //AL CLICK DEL BOTTONE PUNTI APRE UNA FINESTRA CON LA LISTA DEI PUNTEGGI
import java.awt.Container;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FinestraPunteggio extends JFrame implements MouseListener
{
	static File Immagini=new File("Immagini");
	File Bottoni=new File(Immagini.getAbsolutePath()+"\\Bottoni");
	
	public FinestraPunteggio()
	{
	      super("Punteggio");
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub

		if(this.isActive())
       {
    	   
    	   this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
       }
	}

	@Override
	public void mouseEntered(MouseEvent e)
    {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{

	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub

		
		if(this.isVisible())
	       {
	    	   
	    	   this.setVisible(false);
	       }
		else{
			
	    
	    ImageIcon sfondo = new ImageIcon(Bottoni.getAbsolutePath()+"\\punteggio.jpg");
		this.setResizable(false);
		this.setBounds(115, 15, 600, 650);
		this.setContentPane(new JLabel(sfondo));
		this.setSize(600,665);
	    this.setSize(600,666);
	    
	    this.setVisible(true);
		}
	}

}



