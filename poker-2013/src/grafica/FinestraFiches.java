package grafica;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FinestraFiches extends JFrame implements MouseListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //TODO
	static File Immagini=new File("Immagini");
	File Bottoni=new File(Immagini.getAbsolutePath()+"\\Bottoni");
	
	public FinestraFiches()
	{
	      super("Valore delle Fiches");
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
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e){}
	@Override
	public void mousePressed(MouseEvent e){}
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		
		if(this.isVisible())
	       {
	    	   
	    	   this.setVisible(false);
	       }
		else{
			
	    
	    ImageIcon sfondo = new ImageIcon(Bottoni.getAbsolutePath()+"\\ValoreFiches.jpg");
		this.setResizable(false);
		this.setBounds(115, 15, 600, 650);
		this.setContentPane(new JLabel(sfondo));
		this.setSize(600,665);
	    this.setSize(600,666);
	    
	    this.setVisible(true);
		}
	}

}
