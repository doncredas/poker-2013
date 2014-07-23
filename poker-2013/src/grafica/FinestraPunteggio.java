package grafica;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FinestraPunteggio extends JFrame implements MouseListener
{

	private static final long serialVersionUID = 1L; 
	public FinestraPunteggio()
	{
	      super("Punteggio");
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(this.isActive())
       {
    	   
    	   this.setDefaultCloseOperation(FinestraPunteggio.DISPOSE_ON_CLOSE);
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
		     this.setResizable(false);
		     this.setBounds(115, 15, 600, 650);
		     this.setContentPane(new JLabel(Icone.getSfondoPunteggio()));
		     this.setSize(600,665);
	         this.setSize(600,666);
	         this.setVisible(true);
		}//else
	}//mouseReleased

}//FinestraPunteggio



