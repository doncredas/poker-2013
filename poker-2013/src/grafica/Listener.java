package grafica;

// GESTIONE DEL BOTTONE INVIA DELLA CHAT
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import progettoPoker.*;
import progettoPoker.Comando.Tipo;

public class Listener implements KeyListener,ActionListener {

	JTextArea Chat = new JTextArea();
	JTextField ConsChat = new JTextField();
	
	public Listener(JTextArea a, JTextField b) {
		this.Chat = a;
		this.ConsChat = b;


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
		
	}

}
