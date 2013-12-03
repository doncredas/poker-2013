package progettoPoker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.concurrent.Semaphore;
import javax.swing.*;
import javax.swing.event.*;

import progettoPoker.Comando.Tipo;


public class Partita {
	private ServerSocket ss = null;
	private Socket s = null;
	private ObjectOutputStream OOS[] = null;
	private ObjectInputStream OIS[] = null;
	//private BufferedReader br = null;
	private Thread t = null;
	private Dealer d = null;
	private boolean fineMano=false;
	private int tempo=60;
	private Cronometro cron=new Cronometro();

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
			this.esegui();
			
		}
	}

	public void esegui() {
		while(true){
			Comando c=new Comando(Tipo.NICK_NAME);
			Comando risp=null;
			//TODO set nickname del server
			for (int i = 1; i < OOS.length; i++) {
				try {
					OOS[i].writeObject(c);
					risp=(Comando)OIS[i].readObject();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				for (int j = 0; j < i; j++) {
					if(d.getG()[j].getNickName().equals(risp.getNickName())){
						i--;
						break;
					}
				}
				d.getG()[i].setNickName(risp.getNickName());
			}//nick
			d.mischia();
			d.daiCarte();
			fineMano=false;
			c=new Comando(null);
			int primoGiocatore=0;
			int ultimoRaise;
			risp=null;
			while(!fineMano){	
				ultimoRaise=-1;
				for(int cont=0,i=primoGiocatore;cont<OOS.length;cont++,i=(i+1)%OOS.length){
					if(i==ultimoRaise){
						ultimoRaise=-1;
						break;
					}
					if(i==0){
						//TODO collegare alla grafica
					}else
					if(d.getG()[i].getInGioco()){
						try {
							OOS[i].writeObject(c);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						risp=null;
						cron.reset();//TODO spostare il cronometro nel client
						while(risp==null&&cron.getSecondi()<tempo){
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
						if(risp.getT()==Tipo.RAISE)ultimoRaise=i;
					}
				}
				if(ultimoRaise==-1)CreaComando();
			}
			primoGiocatore=(primoGiocatore+1)%OOS.length;
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
		Socket client=null;
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
		}else{
			String ip=JOptionPane.showInputDialog("Inserire indirizzio ip");
			try {
				client=new Socket(ip,7777);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
