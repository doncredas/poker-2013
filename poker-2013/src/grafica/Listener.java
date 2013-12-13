package grafica;

// GESTIONE DEL BOTTONE INVIA DELLA CHAT

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import progettoPoker.*;
import progettoPoker.Comando.Tipo;

public class Listener implements KeyListener,ActionListener,MouseListener,MouseMotionListener{

	JTextArea Chat = new JTextArea();
	JTextField ConsChat = new JTextField();
	JTextArea Statistiche=new JTextArea();
	JScrollPane ScrollChat = new JScrollPane(Chat);
	JScrollPane ScrollStat = new JScrollPane(Statistiche);
	
	
	JScrollBar BarRaise=new JScrollBar();
	JScrollPane ScrollRaise=new JScrollPane(BarRaise);
	JTextField ConsRaise=new JTextField();
	
	
	

	
	
	public Listener(JTextArea a, JTextField b,JTextArea c,JScrollPane sc,JScrollPane ss,JScrollPane spb,JScrollBar sb,JTextField br) {
		this.Chat = a;
		this.ConsChat = b;
		this.Statistiche=c;
		this.ScrollChat=sc;
		this.ScrollStat=ss;
		this.BarRaise=sb;
		this.ConsRaise=br;
		this.ScrollRaise=spb;

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
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
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


	

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
             GraficaPoker.com=new Comando(Tipo.RAISE);//TODO scroll o textbox per inserire raise
		}
		if(arg0.getSource()==GraficaPoker.Call)
		{
             GraficaPoker.com=new Comando(Tipo.CHECK_CALL);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}
	
	
     
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
       // BarRaise.setUnitIncrement(20); Setta il movimento della barra alla presione delle frecce
		this.ConsRaise.setText(Integer.toString(this.BarRaise.getValue()));
	}
    

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

		this.ConsRaise.setText(Integer.toString(this.BarRaise.getValue()));
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

}
