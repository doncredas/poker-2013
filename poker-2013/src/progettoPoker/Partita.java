package progettoPoker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.concurrent.Semaphore;
import javax.swing.*;
import javax.swing.event.*;

import progettoPoker.Comando.Tipo;


public class Partita extends JFrame implements Runnable {
	private ServerSocket ss = null;
	private Socket s = null;
	private ObjectOutputStream OOS[] = null;
	private ObjectInputStream OIS[] = null;
	//private BufferedReader br = null;
	private Thread t = null;
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
			t=new Thread(this);
			t.run();
			
		}
	}

	@Override
	public void run() {
		while(true){
			d.mischia();
			d.daiCarte();
			fineMano=false;
			Comando c=new Comando(null);
			boolean raise=false;
			while(!fineMano){	
				raise=false;
				for(int i=0;i<OOS.length;i++){
					if(d.getG()[i].getInGioco()){
						try {
							OOS[i].writeObject(c);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Comando risp=null;
						while(risp==null){
							try {
								risp=(Comando) OIS[i].readObject();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						eseguiComando(risp,i);
						if(risp.getT()==Tipo.RAISE)raise=true;
					}
				}
				if(!raise)CreaComando();
			}
		}
		

	}
	

	private void CreaComando() {
		
		if(d.getCarteComuni()[0]==null){
			d.flop(OOS);
		}else
			if(d.getCarteComuni()[3]==null){
				d.turn(OOS);
			}else
				if(d.getCarteComuni()[4]==null){
					d.river(OOS);
				}else
					d.fineMano(OOS);
	}

	private void eseguiComando(Comando c,int i) {
		switch(c.getT()){
		case FOLD:
			d.fold(i);
			break;
		case CHECK_CALL:
			d.checkCall(i);
			break;
		case RAISE:
			d.raise(i,c.getFiches());
			break;
		default:
			break;
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
