package grafica;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FinestraPunteggio extends JFrame implements MouseListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //TODO
	//static File Immagini=new File("Immagini");
	//File Varie=new File(Immagini.getAbsolutePath()+"\\Varie");
	
	public FinestraPunteggio()
	{
	      super("Punteggio");
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		// Auto-generated method stub

		if(this.isActive())
       {
    	   
    	   this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
       }
	}

	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e) {}
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
	        // ImageIcon sfondo = new ImageIcon(Icone.sfondoFiches);
		     this.setResizable(false);
		     this.setBounds(115, 15, 600, 650);
		     this.setContentPane(new JLabel(Icone.sfondoPunteggio));
		     this.setSize(600,665);
	         this.setSize(600,666);
	         this.setVisible(true);
		}//else
	}//mouseReleased

}//FinestraPunteggio



