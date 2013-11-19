package progettoPoker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.event.*;


public class Partita extends JFrame implements Runnable {
	private ServerSocket ss = null;
	private Socket s = null;
	private ObjectOutputStream OOS = null;
	private ObjectInputStream OIS = null;
	private BufferedReader br = null;
	private Thread t = null;
	private Dealer d = null;

	public Partita()  {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int sc = JOptionPane.showOptionDialog(null,
				"Creare una nuova partita o connettersi ad una esistente?",
				"Poker", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, new String[] { "Crea nuova", "Connetti ad esistente" },
				"Crea nuova");
		if(sc==-1)System.exit(0);
		if(sc==0){
			try {
				ServerSocket server =new ServerSocket(7777);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Errore durante la creazione del Server!","Errore",JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			}
			JFrame f=new JFrame();
			NumGioc n=new NumGioc();
			f.add(n);
			f.setLocation(500,300);
			f.setSize(200, 200);
			f.setVisible(true);
			int tmp=0;
			do{
				tmp=n.getValue();
			if(tmp==0){}
			else	System.out.println("-->"+tmp);
			//else System.out.println("->"+tmp);
			}
			while(tmp==0);
		
			
		}
		//System.out.println(sc);
		//Partita p = new Partita();
		//p.setVisible(true);

	}

}

class NumGioc extends JPanel implements ActionListener{ 

	JList<Integer> list; 
	 int numGioc=0;
	 public NumGioc(){ 
	 super();
	 JLabel testo=new JLabel("Seleziona il numero di giocatori");
	 add(testo);
	 Integer gioc[]={1,2,3,4,5,6,7,8,9}; 
	 list = new JList<Integer>(gioc); 
	 JScrollPane pane = new JScrollPane(list); 
	 list.setVisibleRowCount(5); 
	 add(pane);
	 JButton conferma=new JButton("OK");
	 add(conferma);
	 conferma.addActionListener(this);
	 
	 } 
	 public int getValue(){
		 return numGioc;
	 }
	 
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try{
		numGioc=list.getSelectedValue();
		}catch(Exception e){System.out.println("err");}
		
	}
	 
	} 
