package grafica;

// GESTIONE DEL BOTTONE INVIA DELLA CHAT

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;

import javax.swing.*;

import progettoPoker.*;
import progettoPoker.Comando.Tipo;

public class Listener extends JPanel implements KeyListener, ActionListener,
		MouseListener, AdjustmentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //TODO
	GraficaPoker gp;
	/* TODO Chat
	JTextArea Chat = new JTextArea();
	JTextField ConsChat = new JTextField();
	JScrollPane ScrollChat = new JScrollPane(Chat);
	*/
	
	//JTextArea Statistiche = new JTextArea();
	//JScrollPane ScrollStat = new JScrollPane(Statistiche);

	JScrollBar BarRaise = new JScrollBar();
	JTextField ConsRaise = new JTextField();
	GiocatoreGrafico g1=null;

	JLabel carta1 = null;
	JLabel carta2 = null;

	Socket socket=null;
	Thread thread;
	//Giocatore [] giocatore;
	
	int puntataClient=-1;
	
	public void setPuntata(int val){
		this.puntataClient=val;
	}
	
	//ImageIcon ico1=null;
	//ImageIcon ico2=null;
	

	public Listener(/*JTextArea a, JTextField b,JTextArea c, JScrollPane sc,
			JScrollPane ss,*/ JScrollBar sb, JTextField br, JLabel gioc1ca1,
			JLabel gioc1ca2,GraficaPoker gp) {
		/*
		this.Chat = a;
		this.ConsChat = b;
		this.ScrollChat = sc;
		*/
		//this.Statistiche = c;
		//this.ScrollStat = ss;
		this.BarRaise = sb;
		this.ConsRaise = br;

		this.carta1 = gioc1ca1;
		this.carta2 = gioc1ca2;
		
		this.gp=gp;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	
		/* TODO chat
		if (arg0.getSource() == GraficaPoker.ConsChat) 
		{
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) 
			{
				if (this.ConsChat.getText().length() > 0) 
				{/*
					String s=ConsChat.getText();					
					invia(s);			
					this.Chat.setText(this.Chat.getText() + "  Nickname: "
							+ ConsChat.getText() + "\n");
					ConsChat.setText("");
				}//if lunghezza
			}//if key event
		}//if getSource
		*/
		
		if (arg0.getSource() == ConsRaise) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				this.BarRaise
						.setValue(Integer.valueOf(this.ConsRaise.getText()));
				repaint();
			}
		}
	}
	

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		/*
		if (arg0.getSource() == GraficaPoker.VisChat) {
			this.Statistiche.setVisible(false);
			this.ScrollStat.setVisible(false);
			this.Chat.setVisible(true);
			this.ScrollChat.setVisible(true);
			this.ConsChat.setVisible(true);
			GraficaPoker.Invia.setVisible(true);
			GraficaPoker.VisChat.setIcon(Icone.ChatSelected);
			GraficaPoker.VisStat.setIcon(Icone.VisualizzaStat);
		}
		
		if (arg0.getSource() == GraficaPoker.VisStat) {
			//this.Chat.setVisible(false);
			//this.ScrollChat.setVisible(false);
			this.Statistiche.setVisible(true);
			this.ScrollStat.setVisible(true);
			//this.ConsChat.setVisible(false);
			//GraficaPoker.Invia.setVisible(false);
			GraficaPoker.VisStat.setIcon(Icone.StatSelected);
			//GraficaPoker.VisChat.setIcon(Icone.VisualizzaChat);
		}

		if (arg0.getSource() == GraficaPoker.Invia) {
			if (this.ConsChat.getText().length() > 0) {
				this.Chat.setText(this.Chat.getText() + "  Nickname: "
						+ ConsChat.getText() + "\n");
				ConsChat.setText("");
			}
		}
		*/
		if (arg0.getSource() == GraficaPoker.Fold) {
			GraficaPoker.com = new Comando(Tipo.FOLD);
			gp.disableBottoni(true);
		}
		if (arg0.getSource() == GraficaPoker.Raise) {
			this.BarRaise.setValue(Integer.valueOf(this.ConsRaise.getText()));
			
			GraficaPoker.com = new Comando(Tipo.RAISE,Integer.parseInt(gp.ConsRaise.getText()+puntataClient));
			
			gp.disableBottoni(true);
		}
		if(arg0.getSource() == GraficaPoker.AllIn){
			GraficaPoker.com = new Comando(Tipo.RAISE,gp.BarRaise.getMaximum());
			gp.disableBottoni(true);
		}
		if (arg0.getSource() == GraficaPoker.Call) {
			GraficaPoker.com = new Comando(Tipo.CHECK_CALL);
			//Fiches.punta(1, BarRaise.getValue(), gp);
			gp.disableBottoni(true);
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		//JOptionPane.showMessageDialog(null,"X:"+arg0.getX()+" Y:"+arg0.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

		if ((arg0.getSource() == carta1) || (arg0.getSource() == carta2)) 
		{
			gp.getGiocatore(0).giraCarte(true);
			
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if ((arg0.getSource() == carta1) || (arg0.getSource() == carta2)) 
		{
			gp.getGiocatore(0).giraCarte(false);
		}

	}

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

	}

}
