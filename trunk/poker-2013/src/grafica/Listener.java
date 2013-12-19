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
	JTextArea Chat = new JTextArea();
	JTextField ConsChat = new JTextField();
	JTextArea Statistiche = new JTextArea();
	JScrollPane ScrollChat = new JScrollPane(Chat);
	JScrollPane ScrollStat = new JScrollPane(Statistiche);

	JScrollBar BarRaise = new JScrollBar();
	JTextField ConsRaise = new JTextField();
	GiocatoreGrafico g1=null;

	JLabel carta1 = null;
	JLabel carta2 = null;

	Socket socket=null;
	Thread thread;
	//Giocatore [] giocatore;


	
	ImageIcon ico1=null;
	ImageIcon ico2=null;
	

	public Listener(JTextArea a, JTextField b, JTextArea c, JScrollPane sc,
			JScrollPane ss, JScrollBar sb, JTextField br, JLabel gioc1ca1,
			JLabel gioc1ca2) {
		this.Chat = a;
		this.ConsChat = b;
		this.Statistiche = c;
		this.ScrollChat = sc;
		this.ScrollStat = ss;
		this.BarRaise = sb;
		this.ConsRaise = br;

		this.carta1 = gioc1ca1;
		this.carta2 = gioc1ca2;
/*
		try{
			socket=s;
		thread= new Thread(this);
		thread.start();
		}catch(Exception ex){ex.printStackTrace();}		
*/
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	
		if (arg0.getSource() == GraficaPoker.ConsChat) 
		{
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) 
			{
				if (this.ConsChat.getText().length() > 0) 
				{/*
					String s=ConsChat.getText();					
					invia(s);
				*/				
					this.Chat.setText(this.Chat.getText() + "  Nickname: "
							+ ConsChat.getText() + "\n");
					ConsChat.setText("");
				}//if lunghezza
			}//if key event
		}//if getSource
		if (arg0.getSource() == ConsRaise) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				this.BarRaise
						.setValue(Integer.valueOf(this.ConsRaise.getText()));
				repaint();
			}
		}
	}
/*
	private void invia(String s) {
		// TODO Auto-generated method stub
		OutputStream outStream;
		try {
			outStream = socket.getOutputStream();
			outStream.write(s.getBytes());
			outStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//invia
	
	 public String ricevi() throws IOException {
		 String line = "";
		 InputStream inStream = socket.getInputStream();
		 int read = inStream.read();
		 while (read!=10 && read > -1) {
			 line+=String.valueOf((char)read);
			 read = inStream.read();
		}
		if (read==-1) return null;
		line+=String.valueOf((char)read);
		return line;
	}//receive
	 
	 public void run() {
		 try {
			 while(true) {
				 String msg = ricevi();
				 Chat.append(msg);
			}
		 }catch(Exception ex){System.out.println("disconnesso");}
	}//run
*/
	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == GraficaPoker.VisChat) {
			this.Statistiche.setVisible(false);
			this.ScrollStat.setVisible(false);
			this.Chat.setVisible(true);
			this.ScrollChat.setVisible(true);
			this.ConsChat.setVisible(true);
			GraficaPoker.Invia.setVisible(true);
			GraficaPoker.VisChat.setIcon(Icone.ChatSelected);
			;
			GraficaPoker.VisStat.setIcon(Icone.VisualizzaStat);
		}
		if (arg0.getSource() == GraficaPoker.VisStat) {
			this.Chat.setVisible(false);
			this.ScrollChat.setVisible(false);
			this.Statistiche.setVisible(true);
			this.ScrollStat.setVisible(true);
			this.ConsChat.setVisible(false);
			GraficaPoker.Invia.setVisible(false);
			GraficaPoker.VisStat.setIcon(Icone.StatSelected);
			GraficaPoker.VisChat.setIcon(Icone.VisualizzaChat);
		}

		if (arg0.getSource() == GraficaPoker.Invia) {
			if (this.ConsChat.getText().length() > 0) {
				this.Chat.setText(this.Chat.getText() + "  Nickname: "
						+ ConsChat.getText() + "\n");
				ConsChat.setText("");
			}
		}
		if (arg0.getSource() == GraficaPoker.Fold) {
			GraficaPoker.com = new Comando(Tipo.FOLD);
		}
		if (arg0.getSource() == GraficaPoker.Raise) {
			GraficaPoker.com = new Comando(Tipo.RAISE);
			// TODO scroll o textbox per inserire raise
		}
		if (arg0.getSource() == GraficaPoker.Call) {
			GraficaPoker.com = new Comando(Tipo.CHECK_CALL);
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

		if ((arg0.getSource() == carta1) || (arg0.getSource() == carta2)) 
		{
			if(ico1!=null){
			this.carta1.setIcon(ico1);
			this.carta2.setIcon(ico2);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if ((arg0.getSource() == carta1) || (arg0.getSource() == carta2)) 
		{
			ico1=(ImageIcon) this.carta1.getIcon();
			this.carta1.setIcon(Icone.coperta);
			ico2=(ImageIcon) this.carta2.getIcon();
			this.carta2.setIcon(Icone.coperta);
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
