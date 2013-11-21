package progettoPoker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.*;
import javax.swing.event.*;


public class Partita extends JFrame implements Runnable {
	private ServerSocket ss = null;
	private Socket s = null;
	private ObjectOutputStream OOS[] = null;
	private ObjectInputStream OIS[] = null;
	//private BufferedReader br = null;
	private Thread[] t = null;
	private Dealer d = null;
	private boolean fineMano=false;
	

	public Partita()  {
		// TODO Auto-generated constructor stub
	}
	
	public Partita(ServerSocket ss,Dealer d)  {
		this.ss=ss;
		this.d=d;
		for(int i=0;i<d.getS().length;i++){
			try {
				OOS[i]=new ObjectOutputStream(d.getS()[i].getOutputStream());
				OIS[i]=new ObjectInputStream(d.getS()[i].getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread t=new Thread(this);
			t.run();
			
		}
	}

	@Override
	public void run() {
		while(true){
			d.mischia();
			while(!fineMano){
				d.daiCarte();
				//TODO continue
			}
		}
		

	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket server=null;
		int sc = JOptionPane.showOptionDialog(null,
				"Creare una nuova partita o connettersi ad una esistente?",
				"Poker", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, new String[] { "Crea nuova", "Connetti ad esistente" },
				"Crea nuova");
		if(sc==-1)System.exit(0);
		if(sc==0){
			try {
				server =new ServerSocket(7777);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Errore durante la creazione del Server!","Errore",JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			}
			JFrame f=new JFrame();
			Semaphore sem=new Semaphore(0);
			NumGioc n=new NumGioc(sem);
			f.add(n);
			f.setLocation(500,300);
			f.setSize(200, 200);
			f.setVisible(true);
			
			
			try {
				sem.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int numG=n.getValue();
			Socket [] s=new Socket[numG-1];
			for(int i=0;i<s.length;i++)
				try {
					s[i]=server.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			Dealer d=new Dealer(numG,10000,s);
			new Partita(server,d);
		}
		//System.out.println(sc);
		//Partita p = new Partita();
		//p.setVisible(true);

	}
	static class NumGioc extends JPanel implements ActionListener{ 

		JList<Integer> list; 
		 int numGioc=0;
		 Semaphore s;
		 public NumGioc(Semaphore s){ 
		 super();
		 this.s=s;
		 JLabel testo=new JLabel("Seleziona il numero di giocatori");
		 add(testo);
		 Integer gioc[]={2,3,4,5,6,7,8,9}; 
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
				s.release();
			}catch(Exception e){System.out.println("err");}
			
		}
		 
		}
}
