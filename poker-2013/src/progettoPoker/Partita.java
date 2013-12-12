package progettoPoker;

import grafica.GraficaPoker;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.concurrent.Semaphore;
import javax.swing.*;
import javax.swing.event.*;

import chatThread.ClientT;
import chatThread.ServerT;

import progettoPoker.Comando.Tipo;


public class Partita {
	//private ServerSocket ss = null;
	private Socket s = null;
	private ObjectOutputStream OOS[] = null;
	private ObjectInputStream OIS[] = null;
	private ObjectOutputStream oos=null;
	private ObjectInputStream ois=null;
	//private BufferedReader br = null;
	private Thread t = null;
	private Dealer d = null;
	private boolean fineMano=false;
	private int tempo=60;
	private Cronometro cron=new Cronometro();

	public Partita(Socket client)  {
		this.s=client;
		try {
			oos=new ObjectOutputStream(s.getOutputStream());
			ois=new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		eseguiClient();
	}
	
	private void eseguiClient() {
		GraficaPoker gp=new GraficaPoker();
		System.out.println(s.getInetAddress());
		ClientT chat=new ClientT(s.getInetAddress(),444);
		Comando com=null;
		Comando vecchio=null;
		while(true){
			while(com==vecchio){
				try {
					com=(Comando)ois.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			eseguiComando(com,gp);
			vecchio=com;
		}
	}

	private void eseguiComando(Comando com,GraficaPoker gp) {
		Comando risp=null;
		if(com.t==null&&com.fiches==0){
			gp.disableBottoni(false);
			while(risp==null){
				risp=gp.getComando();
			}
		}else{
			if(com.t==null){
				gp.Giocatori[0].setFiches(com.getFiches());
			}else{
				switch(com.t){
				case NICK_NAME:risp=new Comando(Tipo.NICK_NAME,JOptionPane.showInputDialog("inserire nickname"));
				case DAI_CARTA:
				case NOTIFICA:
				}
			}
		}
		if(risp!=null){
			try {
				oos.writeObject(risp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public Partita(Dealer d)  {
		//this.ss=ss;
		GraficaPoker gp=new GraficaPoker();
		try {
			ServerT chat=new ServerT(d.getS().length-1,444);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.d=d;
		OOS=new ObjectOutputStream[d.getG().length-1];
		OIS=new ObjectInputStream[d.getG().length-1];
		for(int i=0;i<d.getS().length;i++){
			try {
				OOS[i]=new ObjectOutputStream(d.getS()[i].getOutputStream());
				OIS[i]=new ObjectInputStream(d.getS()[i].getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			eseguiServer();
			
		}
	}

	private void eseguiServer() {
		while(true){
			Comando c=new Comando(Tipo.NICK_NAME);
			Comando risp=null;
			boolean ok=true;
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
						ok=false;
						i--;
						break;
					}
				}
				Comando notifica=new Comando(Tipo.NOTIFICA,Tipo.NICK_NAME,i,risp.getNickName());
				if(ok){
					for (int j = 1; j < OOS.length; j++) {
						if(j!=i)
							try {
								OOS[i].writeObject(notifica);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				}
				ok=true;
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
	
	static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        System.out.printf("Display name: %s\n", netint.getDisplayName());
        System.out.printf("Name: %s\n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
        	System.out.printf("InetAddress: %s\n", inetAddress);
        }
        System.out.printf("\n");
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
			
				try {
					Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
					for (NetworkInterface netint : Collections.list(nets))
			            displayInterfaceInformation(netint);
					InetAddress []ia=server.getInetAddress().getAllByName("Utente-PC");
					System.out.println(ia[1]);
					JOptionPane.showMessageDialog(null,"Inserire "+Inet4Address.getLocalHost().getHostAddress()+" nei client");
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SocketException e) {
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
			//OOS=null;
			new Partita(d);
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
			new Partita(client);
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
