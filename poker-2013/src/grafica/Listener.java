package grafica;

// GESTIONE DEL BOTTONE INVIA DELLA CHAT

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import progettoPoker.*;
import progettoPoker.Comando.Tipo;

public class Listener implements KeyListener,ActionListener,MouseListener{

	JTextArea Chat = new JTextArea();
	JTextField ConsChat = new JTextField();
	JTextArea Statistiche=new JTextArea();
	JScrollPane ScrollChat = new JScrollPane(Chat);
	JScrollPane ScrollStat = new JScrollPane(Statistiche);
	
	
	JScrollBar BarRaise=new JScrollBar();
	JTextField ConsRaise=new JTextField();
	
     
    JLabel carta1=null;
    JLabel carta2=null;

	
	
	public Listener(JTextArea a, JTextField b,JTextArea c,JScrollPane sc,JScrollPane ss,JScrollBar sb,JTextField br,JLabel gioc1ca1,JLabel gioc1ca2) {
		this.Chat = a;
		this.ConsChat = b;
		this.Statistiche=c;
		this.ScrollChat=sc;
		this.ScrollStat=ss;
		this.BarRaise=sb;
		this.ConsRaise=br;
		
		this.carta1=gioc1ca1;
		this.carta2=gioc1ca2;
		
		

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
		{
			if (this.ConsChat.getText().length() > 0) 
			{
				this.Chat.setText(this.Chat.getText() + "  Nickname: "	+ ConsChat.getText()+ "\n" );
				ConsChat.setText("");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		
		if(arg0.getSource()==GraficaPoker.VisChat)
		{
			this.Statistiche.setVisible(false);
			this.ScrollStat.setVisible(false);
			this.Chat.setVisible(true);
			this.ScrollChat.setVisible(true);
			this.ConsChat.setVisible(true);
			GraficaPoker.Invia.setVisible(true);
			GraficaPoker.VisChat.setIcon(Icone.ChatSelected);;
            GraficaPoker.VisStat.setIcon(Icone.VisualizzaStat);
		}
		if(arg0.getSource()==GraficaPoker.VisStat)
		{
			this.Chat.setVisible(false);
			this.ScrollChat.setVisible(false);
			this.Statistiche.setVisible(true);
			this.ScrollStat.setVisible(true);
			this.ConsChat.setVisible(false);
			GraficaPoker.Invia.setVisible(false);
            GraficaPoker.VisStat.setIcon(Icone.StatSelected);
			GraficaPoker.VisChat.setIcon(Icone.VisualizzaChat);
		}
			
		if(arg0.getSource()==GraficaPoker.Invia)
		{
			if (this.ConsChat.getText().length() > 0) 
			{
				this.Chat.setText(this.Chat.getText() + "  Nickname: "	+ ConsChat.getText()+ "\n" );
				ConsChat.setText("");
			}
		}
		if(arg0.getSource()==GraficaPoker.Fold)
		{
             GraficaPoker.com=new Comando(Tipo.FOLD);
		}
		if(arg0.getSource()==GraficaPoker.Raise)
		{
             GraficaPoker.com=new Comando(Tipo.RAISE);
             //TODO scroll o textbox per inserire raise
		}
		if(arg0.getSource()==GraficaPoker.Call)
		{
             GraficaPoker.com=new Comando(Tipo.CHECK_CALL);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
	

	if ((carta1.getIcon()==Icone.coperta)&&(carta2.getIcon()==Icone.coperta))
	{
		carta1.setIcon(Icone.quaranta);
		carta2.setIcon(Icone.quarantadue);
	}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if ((carta1.getIcon()!=Icone.coperta)&&(carta2.getIcon()!=Icone.coperta))
		{
			carta1.setIcon(Icone.coperta);
			carta2.setIcon(Icone.coperta);
		}
		
	}
	
	
     
	@Override
	public void mousePressed(MouseEvent arg0) {
		
		this.ConsRaise.setText(Integer.toString(this.BarRaise.getValue()));

	}
    

	@Override
	public void mouseReleased(MouseEvent arg0) {
		this.ConsRaise.setText(Integer.toString(this.BarRaise.getValue()));
		
	}



}
