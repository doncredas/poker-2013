package grafica;
                         //AL CLICK DEL BOTTONE PUNTI APRE UNA FINESTRA CON LA LISTA DEI PUNTEGGI
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FinestraPunteggio implements MouseListener{
	
	JFrame punti=new JFrame("Punteggio");
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
		ImageIcon sfondo = new ImageIcon("C:\\poo_eclipse\\immagini\\punteggio.jpg");
		
		punti.setResizable(false);
		punti.setBounds(115, 15, 600, 650);
		punti.setContentPane(new JLabel(sfondo));
		punti.setSize(600,650);
	    punti.setSize(600,651);
	    
	    punti.setVisible(true);

	}

}
