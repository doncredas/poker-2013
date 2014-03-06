package progettoPoker;

import grafica.GraficaPoker;
import grafica.Icone;

import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Arrays;
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
	private int nCarta=0;
	private int fiches;
	private Cronometro cron=new Cronometro();
	private GraficaPoker gp;
	private int posGioc=0;

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
		if(com.getT()==Tipo.GIOCATORI){
			this.gp=new GraficaPoker(com.gioc);
			this.gp.Giocatori[0].setFiches(fiches);
			this.posGioc=com.getGiocN()+1;
		}else
		if(com.t==null&&com.fiches==0){
			this.gp.disableBottoni(false);
			while(risp==null){
				risp=this.gp.getComando();
			}
		}else{
			if(com.t==null){
				if(this.gp!=null)
					this.gp.Giocatori[0].setFiches(com.getFiches());
				else
					fiches=com.getFiches();
			}else{
				switch(com.t){
				case NICK_NAME:
					//String nick=JOptionPane.showInputDialog("inserire nickname");
					String nick= (String) JOptionPane.showInputDialog(null,"Inserisci il tuo Nickname", "NickName", JOptionPane.WARNING_MESSAGE, Icone.logo,null,null);
					risp=new Comando(Tipo.NICK_NAME,nick);
					gp.Giocatori[0].setNome(nick);
					break;
				case DAI_CARTA:{
					if(com.getC()!=null){
						nCarta=(nCarta%4)+1;
						if(nCarta==1||nCarta==2){
							if(nCarta==1){
								this.gp.reset();
								gp.daiCarteGioc();
							}
							this.gp.Giocatori[0].setCarte(com.getC(),nCarta);
						}
						else
							if(nCarta==3)
								this.gp.setTurn(com.getC());
							else
								this.gp.setRiver(com.getC());
						//TODO
					}else{
						this.gp.setFlop(com.getCar(),this.gp);
					}
					if(com.getFiches()!=0){
						//TODO
					}
						
				}
				break;
				case NOTIFICA:
					switch(com.t1){
					case NICK_NAME:
						System.out.println(com.getGioc()+" "+com.getNickName());
						for(int i=0;i<posGioc;i++){
							gp.getGiocatore(i+1).setNome(com.getNickName()[i]);
						}
						for(int i=posGioc+1;i<com.getNickName().length;i++){
							gp.getGiocatore(i).setNome(com.getNickName()[i]);
						}
						break;
					}
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
		gp=new GraficaPoker(d.getG().length);
		try {
			ServerT chat=new ServerT(d.getS().length-1,444);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.d=d;
		OOS=d.getOOS();
		OIS=new ObjectInputStream[d.getG().length-1];
		for(int i=0;i<d.getS().length;i++){
			try {
				//OOS[i]=new ObjectOutputStream(d.getS()[i].getOutputStream());
				OIS[i]=new ObjectInputStream(d.getS()[i].getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		eseguiServer();
	}

	private void eseguiServer() {
		while(true){
			Comando c=new Comando(Tipo.NICK_NAME);
			Comando risp=null;
			Comando vecchioRisp=null;
			boolean ok=true;
			String nick= (String) JOptionPane.showInputDialog(null,"Inserisci il tuo Nickname", "NickName", JOptionPane.WARNING_MESSAGE, Icone.logo,null,null);
			d.getG()[0].setNickName(nick);
			gp.Giocatori[0].setNome(nick);
			//Comando nickServ=new Comando(Tipo.NOTIFICA,Tipo.NICK_NAME,0,nick);
			for (int i = 0; i < OOS.length; i++) {
				try {
					OOS[i].writeObject(new Comando(Tipo.GIOCATORI,d.getG().length,i));
					//OOS[i].writeObject(nickServ);
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i = 0; i < OOS.length; i++) {
				try {
					//OOS[i].writeObject(new Comando(Tipo.GIOCATORI,d.getG().length,i));
					//OOS[i].writeObject(nickServ);
					OOS[i].writeObject(c);
					while(risp==vecchioRisp){
					try {
						risp=(Comando)OIS[i].readObject();
						System.out.println("1");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						System.out.println("2");
					}
					}
					vecchioRisp=risp;
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
				//Comando notifica=new Comando(Tipo.NOTIFICA,Tipo.NICK_NAME,i,risp.getNickName());
				if(ok){
					gp.Giocatori[i+1].setNome(risp.getNick());
					d.getG()[i+1].setNickName(risp.getNick());
					/*for (int j = 0; j < OOS.length; j++) {
							try {
								OOS[i].writeObject(notifica);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}*/
				}
				ok=true;
				
			}
			String [] nickName=new String [d.getG().length];
			for (int i = 0; i < nickName.length; i++) {
				nickName[i]=d.getG()[i].getNickName();
			}
			Comando notifica=new Comando(Tipo.NOTIFICA,Tipo.NICK_NAME,nickName);
			for (int i = 0; i < OOS.length; i++) {
				try {
					OOS[i].writeObject(notifica);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//nick
			gp.reset();
			d.mischia();
			d.daiCarte(gp);
			gp.daiCarteGioc();
			fineMano=false;
			c=new Comando(null);
			int primoGiocatore=0;
			int ultimoRaise;
			risp=null;
			while(!fineMano){
				ultimoRaise=-1;
				for(int cont=0,i=primoGiocatore;cont<d.getG().length;cont++,i=(i+1)%d.getG().length){
					if(i==ultimoRaise){
						ultimoRaise=-1;
						break;
					}
					if(i==0){
						gp.disableBottoni(false);
					}else
					if(d.getG()[i].getInGioco()){
						try {
							OOS[i-1].writeObject(c);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						risp=null;
						cron.reset();//TODO spostare il cronometro nel client
						while(risp==null&&cron.getSecondi()<tempo){
							try {
								risp=(Comando) OIS[i-1].readObject();
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
			gp.setFlop(Arrays.copyOf(d.getCarteComuni(),3), gp);
		}else
			if(d.getCarteComuni()[3]==null){
				d.turn(OOS);
				gp.setTurn(d.getCarteComuni()[3]);
			}else
				if(d.getCarteComuni()[4]==null){
					d.river(OOS);
					gp.setRiver(d.getCarteComuni()[4]);
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
				"Real Poker 2014", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
				Icone.logo, new String[] { "Crea nuova", "Connetti ad esistente" },
				"Crea nuova");
		if(sc==-1)System.exit(0);
		if(sc==0){
			try {
				server = new ServerSocket(7777);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Errore durante la creazione del Server!","Errore",JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			}
			Integer gioc[]={2,3,4,5,6,7,8,9}; 
			Integer nGiocatori= (Integer) JOptionPane.showInputDialog(null,"Inserisci il numero di giocatori", "NickName", JOptionPane.WARNING_MESSAGE, Icone.logo,gioc,gioc);

			
				try {
					Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
					String inet=null;
					for (NetworkInterface netint : Collections.list(nets)){
						if(netint.getName().equals("net0")||netint.getName().equals("net4"))
							try{
							inet= netint.getInetAddresses().nextElement().getHostAddress();
							}catch(Exception e){}
						if(inet!=null)break;

					}
					JOptionPane.showMessageDialog(null,"Inserire "+inet+" nei client");
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			
			
			int numG=nGiocatori;
			Socket [] s=new Socket[numG-1];
			ObjectOutputStream [] OOS=new ObjectOutputStream[numG-1];
			for(int i=0;i<s.length;i++)
				try {
					s[i]=server.accept();
					OOS[i]=new ObjectOutputStream(s[i].getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			Dealer d=new Dealer(numG,10000,s,OOS);
			//OOS=null;
			new Partita(d);
		}else{

			String ip2= (String) JOptionPane.showInputDialog(null,"Inserisci l'IP a cui collegarti", "Inserimento IP", JOptionPane.WARNING_MESSAGE, Icone.logo,null,null); 
			
			try {
				client=new Socket(ip2,7777);
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
	
}
