package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import progettoPoker.*;
import progettoPoker.Comando.Tipo;

public class Listener extends JPanel implements KeyListener, ActionListener,
		MouseListener, AdjustmentListener {

	private static final long serialVersionUID = 1L;
	private GraficaPoker gp;
	private JScrollBar BarRaise = new JScrollBar();
	private JTextField ConsRaise = new JTextField();
	private JLabel carta1 = null;
	private JLabel carta2 = null;
	private int puntataClient=-1;
	
	public void setPuntata(int val){
		this.puntataClient=val;
	}
	
	//ImageIcon ico1=null;
	//ImageIcon ico2=null;
	

	public Listener(JScrollBar sb, JTextField br, JLabel gioc1ca1,

			JLabel gioc1ca2,GraficaPoker gp) {

		this.BarRaise = sb;
		this.ConsRaise = br;
		this.carta1 = gioc1ca1;
		this.carta2 = gioc1ca2;
		this.gp=gp;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		if (arg0.getSource() == ConsRaise) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				this.BarRaise
						.setValue(Integer.valueOf(this.ConsRaise.getText()));
				repaint();
			}
		}//if
	}//keyPressed

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == GraficaPoker.Fold) {
			GraficaPoker.setCom(new Comando(Tipo.FOLD));
			gp.disableBottoni(true);
		}
		if (arg0.getSource() == GraficaPoker.Raise) {
			this.BarRaise.setValue(Integer.valueOf(this.ConsRaise.getText()));
			
			GraficaPoker.setCom(new Comando(Tipo.RAISE,Integer.parseInt(gp.ConsRaise.getText())+puntataClient));
			
			gp.disableBottoni(true);
		}
		if(arg0.getSource() == GraficaPoker.AllIn){
			GraficaPoker.setCom(new Comando(Tipo.RAISE,gp.BarRaise.getMaximum()));
			gp.disableBottoni(true);
		}
		if (arg0.getSource() == GraficaPoker.Call) {
			GraficaPoker.setCom(new Comando(Tipo.CHECK_CALL));
			gp.disableBottoni(true);
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

		if ((arg0.getSource() == carta1) || (arg0.getSource() == carta2)) 
		{
			gp.getGiocatore(0).giraCarte(true);
			
		}
	}//mouseEntered

	@Override
	public void mouseExited(MouseEvent arg0) {
		if ((arg0.getSource() == carta1) || (arg0.getSource() == carta2)) 
		{
			gp.getGiocatore(0).giraCarte(false);
		}
	}//mouseExited

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {

		this.ConsRaise.setText("" + arg0.getValue());
		repaint();

	}//adjustmentValueChanged

}
