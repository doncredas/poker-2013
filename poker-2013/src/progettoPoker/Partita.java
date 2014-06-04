package progettoPoker;


import grafica.*;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

import chatThread.*;
import progettoPoker.Comando.Tipo;


public class Partita {
	private Socket s = null;
	private ObjectOutputStream OOS[] = null;
	private ObjectInputStream OIS[] = null;
	private ObjectOutputStream oos=null;
	private ObjectInputStream ois=null;
	//private BufferedReader br = null;
	//private Thread t = null;
	private Dealer d = null;
	//private boolean fineMano=false;
	private int tempo=60;
	private int nCarta=0;
	private int fiches;
	private Cronometro cron=new Cronometro();
	private GraficaPoker gp;
	private int posGioc=0; //posizione del giocatore che si vede centrale rispetto al server
	private Comando c=null;
	private Comando risp=null;

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
		//System.out.println(s.getInetAddress());
		//ClientT chat=new ClientT(s.getInetAddress(),444);
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
		Boolean azioneRegistrata=false;
		Comando azioneAllIn=null;
		if(gp!=null){
			if(com.t==null||com.t!=Tipo.NOTIFICA){
				gp.setAttivo(0);
			}
			if(com.fichesGioc!=null){
				for (int i = 0; i < com.fichesGioc.length; i++) {
					if(i<posGioc){
						gp.getGiocatore()[i+1].setFiches(com.fichesGioc[i]);
					}else
						if(i==posGioc){
						gp.getGiocatore()[0].setFiches(com.fichesGioc[i]);
						gp.setMaxBar(com.fichesGioc[i]);
					}else
						gp.getGiocatore()[i].setFiches(com.fichesGioc[i]);
				}
			}
			if(com.dealer!=null){
				for(int i=0;i<com.dealer.length;i++)
					if(com.dealer[i]!=0){
						if(com.dealer[i]-1<posGioc){
							switch(i){
							case 0:GraficaPoker.setDealer(com.dealer[i]+1);break;
							case 1:GraficaPoker.setSmallBlind(com.dealer[i]+1);break;
							case 2:GraficaPoker.setBigBlind(com.dealer[i]+1);
							}
						}else
							if(com.dealer[i]-1==posGioc){
								switch(i){                        
								case 0:GraficaPoker.setDealer(1);break;
								case 1:GraficaPoker.setSmallBlind(1);break;
								case 2:GraficaPoker.setBigBlind(1);
								}                             
						}else
							switch(i){                        
							case 0:GraficaPoker.setDealer(com.dealer[i]);break;
							case 1:GraficaPoker.setSmallBlind(com.dealer[i]);break;
							case 2:GraficaPoker.setBigBlind(com.dealer[i]);
							}
					}
			}
			
			
			//if(com.dealer[1]!=0)
				//GraficaPoker.setSmallBlind(com.dealer[1]+1);
			if(com.valPiatto!=-1)
				gp.setPot(com.valPiatto);
			if(com.puntata!=-1)
				gp.setMinBar(com.puntata);
		}
		if(com.getT()==Tipo.GIOCATORI){
			this.gp=new GraficaPoker(com.gioc);
			GraficaPoker.Giocatori[0].setFiches(fiches);
			this.posGioc=com.getGiocN()+1;
		}else
		if(com.t==null&&com.fiches==-1){
			if(gp.getGiocatore(0).getFichesVal()!=0){
				this.gp.disableBottoni(false);
				while(risp==null){
					risp=this.gp.getComando();
				}if(altriAllIn()&&azioneRegistrata){
					risp=azioneAllIn;
				}else
					if(altriAllIn()){
						risp=azioneAllIn=this.gp.getComando();
					}else{
						azioneRegistrata=false;
						azioneAllIn=null;
					}
			}else
				risp=new Comando(Tipo.CHECK_CALL);
			this.gp.resetComando();
		}else{
			if(com.t==null){
				if(this.gp!=null)
					GraficaPoker.Giocatori[0].setFiches(com.getFiches());
				else
					fiches=com.getFiches();
			}else{
				risp=eseguiTipo(com);
			}
		}
		if(risp!=null){
			try {
				oos.writeObject(risp);
				gp.setAttivo(-1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private boolean altriAllIn() {
		int cont=0;
		for (int i = 0; i < GraficaPoker.getnGioc(); i++) {
			if(gp.getGiocatore()[i].getFichesVal()!=0)cont++;
		}
		return cont==1;
	}

	private Comando eseguiTipo(Comando com) {
		Comando risp=null;
		switch(com.t){
		case NICK_NAME:
			String nick=null;
			do{
			   nick=(String) JOptionPane.showInputDialog(null,"Inserisci il tuo Nickname", "NickName", JOptionPane.WARNING_MESSAGE, Icone.logo,null,null);
			   nick.trim();
			}while(nick.length()>11);
			risp=new Comando(Tipo.NICK_NAME,nick);
			GraficaPoker.Giocatori[0].setNome(nick);
			break;
		case DAI_CARTA:
			if(com.getC()!=null){
				nCarta=(nCarta%4)+1;
				if(nCarta==1||nCarta==2){
					if(nCarta==1){
						this.gp.reset();
						gp.daiCarteGioc();
					}
					GraficaPoker.Giocatori[0].setCarte(com.getC(),nCarta);
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
		break;
		case GAME_OVER:
			gameOver(com.getNick());
			break;
		case NOTIFICA:
			eseguiNotifica(com);
		}
		
		return risp;
	}

	private void eseguiNotifica(Comando com) {

		switch(com.t1){
		case NICK_NAME:
			//System.out.println(com.getGioc()+" "+com.getNickName());
			for(int i=0;i<posGioc;i++){
				gp.getGiocatore(i+1).setNome(com.getNickName()[i]);
			}
			for(int i=posGioc+1;i<com.getNickName().length;i++){
				gp.getGiocatore(i).setNome(com.getNickName()[i]);
			}
			break;
		
			//case INVIA:  gp.scriviChat(com.getNick(),gp.ConsChat.getText());
		                 //gp.ConsChat.setText("");break;
		case CHECK_CALL:
		case RAISE:
			if(com.getGioc()<posGioc)
				gp.punta(com.getGioc()+2, com.getFiches(), gp);
			else
				if(com.getGioc()==posGioc)
					gp.punta(1, com.getFiches(), gp);
				else
					gp.punta(com.getGioc()+1, com.getFiches(), gp);
			break;
		case FOLD:
			if(com.getGioc()<posGioc)
				gp.getGiocatore(com.getGioc()+1).setFold(true);
			else
				if(com.getGioc()==posGioc)
					gp.getGiocatore(0).setFold(true); 
				else
					gp.getGiocatore(com.getGioc()).setFold(true);
			break;
		case FINE_MANO:
			boolean rimanenti[]=com.rimanenti;
			gp.reset();
			nCarta=0;
			for (int i = 0; i < rimanenti.length; i++) {
				if(i<posGioc){
					gp.getGiocatore(i+1).setVisible(rimanenti[i]);
				}else
					if(i==posGioc)
					gp.getGiocatore(0).setVisible(rimanenti[i]);
					else
						gp.getGiocatore(i).setVisible(rimanenti[i]);
			}
			break;
		case DISCONNESSIONE:
			if(com.getGioc()<posGioc)
				gp.getGiocatore(com.getGioc()+1).setVisible(false);
			else
				if(com.getGioc()==posGioc)
					gp.getGiocatore(0).setVisible(false); 
				else
					gp.getGiocatore(com.getGioc()).setVisible(false);
			break;
		}
		
	}

	public Partita(Dealer d)  {
		//this.ss=ss;
		gp=new GraficaPoker(d.getG().length);
		/*try {
			ServerT chat=new ServerT(d.getS().length-1,444);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
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
		generaNick();
		int primoGiocatore=0;
		while(true){
			gp.reset();
			d.eliminati();
			gp.resetGioc(d);
			d.mischia();
			d.daiCarte(gp);
			gp.daiCarteGioc();
			c=new Comando(null);
			primoGiocatore=(d.getPosD()+1)%d.getG().length;
			int ultimoRaise;
			c.setDealer(GraficaPoker.setDealer(d.getPosD()+1));
			risp=null;
			if(unicoGiocatore())termina();
			ciclo:
			for(int j=0;j<4;j++){
				ultimoRaise=-1;
				if(unicoGiocatore())break;
				for(int cont=0,i=primoGiocatore;cont<d.getG().length;cont++,i=(i+1)%d.getG().length){
					gp.setPot(d.getValPiatto());
					if(i==ultimoRaise){
						ultimoRaise=-1;
						break;
					}
					if(d.getG()[i].getInGioco()){
						risp=null;
						cron.reset();//TODO spostare il cronometro nel client
						if(i==0){
							gp.setMinBar(d.getPuntata());
							gp.setMaxBar(d.getG()[0].getFiches());
							gp.getGiocatore()[0].setFiches(d.getG()[0].getFiches());
							if(d.getG()[0].getFiches()!=0){
								gp.disableBottoni(false);
								gp.resetComando();
								while(risp==null&&cron.getSecondi()<tempo){
									risp=gp.getComando();
								}
							}else
								risp=new Comando(Tipo.CHECK_CALL);
						}else{
							setComando(c,false);
							try {
								OOS[i-1].writeObject(c);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							while(risp==null&&cron.getSecondi()<tempo){
								try {
									risp=(Comando) OIS[i-1].readObject();
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									risp=new Comando(Tipo.DISCONNESSIONE);
								}
							}
						}
						eseguiComando(risp,i);
						if(risp.getT()==Tipo.RAISE){
							ultimoRaise=i;
							cont=-1;
						}
						if(risp.getT()==Tipo.FOLD){
							if(unicoGiocatore())
								break ciclo;
						}
						
						
					}
				}
				if(ultimoRaise==-1)CreaComando();
			}
			//if(unicoGiocatore())d.fineMano(OOS);
			//d.muoviDealer();
			resetRimanenti(d.getRimanenti());
		}
		
	}
	

	private void termina() {
		gp.dispose();
		for(int i=0;i<d.getG().length;i++){
			if(d.getG()[i].getInGioco()){
				inviaComando(new Comando(Tipo.GAME_OVER,d.getG()[i].getNickName()));
				for(int j=0;j<OIS.length;j++)
					try {
						risp=(Comando) OIS[j].readObject();
					} catch (ClassNotFoundException e) {
					} catch (IOException e) {
					}
					
				JOptionPane.showMessageDialog(gp, d.getG()[i].getNickName()+" vince la partita");
				System.exit(0);
			}
		}
	}
	
	private void gameOver(String nick){
		gp.dispose();
		JOptionPane.showMessageDialog(gp, nick+" vince la partita");
		try {
			oos.writeObject(new Comando(Tipo.GAME_OVER));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	

	private void generaNick() {
		c=new Comando(Tipo.NICK_NAME);
		Comando vecchioRisp=null;
		boolean ok=true;
		String nick= (String) JOptionPane.showInputDialog(null,"Inserisci il tuo Nickname", "NickName", JOptionPane.WARNING_MESSAGE, Icone.logo,null,null);
		d.getG()[0].setNickName(nick);
		GraficaPoker.Giocatori[0].setNome(nick);
		for (int i = 0; i < OOS.length; i++) {
			try {
				OOS[i].writeObject(new Comando(Tipo.GIOCATORI,d.getG().length,i));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		inviaComando(c);
		for (int i = 0; i < OOS.length; i++) {
			while(risp==vecchioRisp){
				try {
					risp=(Comando)OIS[i].readObject();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			vecchioRisp=risp;
			for (int j = 0; j < i; j++) {
				if(d.getG()[j].getNickName().equals(risp.getNick())){//TODO sistemare il caso di nick uguali
					ok=false;
					try {
						OOS[i].writeObject(c);
					} catch (IOException e) {
						e.printStackTrace();
					}
					i--;
					break;
				}
			}
			if(ok){
				GraficaPoker.Giocatori[i+1].setNome(risp.getNick());
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
		}
		
	}

	private void resetRimanenti(boolean[] rimanenti) {
		for (int i = 0; i < rimanenti.length; i++) {
			if(!rimanenti[i])gp.getGiocatore(i).setVisible(false);
		}
		Comando delete=new Comando(Tipo.NOTIFICA,Tipo.FINE_MANO,rimanenti);
		inviaComando(delete);
		
	}

	private boolean unicoGiocatore() {
		int cont=0;
		int indice=0;
		for(int i=0;i<d.getG().length;i++){
			if(d.getG()[i].getInGioco()){
				cont++;
				indice=i;
			}
		}
		if(cont<2){
			d.checkCall(indice);
			d.fineMano(OOS);
		}
		return cont<2;
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
		Comando notifica;
		switch(c.getT()){
		case FOLD:
			d.fold(i);
			gp.getGiocatore(i).setFold(true);
			notifica=new Comando(Tipo.NOTIFICA,Tipo.FOLD,i);
			setComando(notifica,true);
			inviaComando(notifica);
			break;
		case CHECK_CALL:
			d.checkCall(i);
			GraficaPoker.Giocatori[i].setFiches(d.getG()[i].getFiches());
			gp.punta(i+1, d.getPuntata(), gp);
			notifica=new Comando(Tipo.NOTIFICA,Tipo.CHECK_CALL,i,d.getPuntata());
			setComando(notifica,true);
			inviaComando(notifica);
			break;
		case RAISE:
			d.raise(i,c.getFiches());
			GraficaPoker.Giocatori[i].setFiches(d.getG()[i].getFiches());
			gp.punta(i+1,c.getFiches(), gp);
			notifica=new Comando(Tipo.NOTIFICA,Tipo.RAISE,i,c.getFiches());
			setComando(notifica,true);
			inviaComando(notifica);
			break;
		case DISCONNESSIONE:
			d.fold(i);
			d.getG()[i].setFiches(0);
			d.getG()[i].setInGioco(false);
			gp.getGiocatore(i).setAttivo(false);
			gp.getGiocatore(i).setVisible(false);
			notifica=new Comando(Tipo.NOTIFICA,Tipo.DISCONNESSIONE,i);
			setComando(notifica,true);
			inviaComando(notifica);
		default:
			break;
		}
		
	}
	

	private void setComando(Comando notifica, boolean b) {
		notifica.setPuntata(d.getPuntata());
		if(b){
		int fiches[]=new int [d.getG().length];
		notifica.setValPiatto(d.getValPiatto());
		for (int i = 0; i < fiches.length; i++) {
			fiches[i]=d.getG()[i].getFiches();
		}
		notifica.setFichesGioc(fiches);
		}
	}

	private void inviaComando(Comando c) {
		for(int i=0;i<OOS.length;i++)
			try {
				OOS[i].writeObject(c);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	private static void mostraIp() {
		try {
			Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
			String inet=null;
			for (NetworkInterface netint : Collections.list(nets)){
				if(netint.getName().startsWith("net"))
					try{
					inet= netint.getInetAddresses().nextElement().getHostAddress();
					}catch(Exception e){}
				if(inet!=null)break;

			}
			
			
		    if(inet.charAt(0)=='f')JOptionPane.showMessageDialog(null,"Inserire 127.0.0.1 nei client");
			else 
				JOptionPane.showMessageDialog(null,"Inserire "+inet+" nei client","Informazione",3,Icone.logo);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	private static void creaServer() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(7777);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Errore durante la creazione del Server!","Errore",JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		Integer gioc[]={2,3,4,5,6,7,8}; 
		Integer nGiocatori= (Integer) JOptionPane.showInputDialog(null,"Inserisci il numero di giocatori", "Numero Giocatori", JOptionPane.WARNING_MESSAGE, Icone.logo,gioc,gioc);

		if(nGiocatori==null)System.exit(0);
		mostraIp();
		
		int numG=nGiocatori;
		Socket [] s=new Socket[numG-1];
		ObjectOutputStream [] OOS=new ObjectOutputStream[numG-1];
		for(int i=0;i<s.length;i++)
			try {
				s[i]=server.accept();
				OOS[i]=new ObjectOutputStream(s[i].getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		Dealer d=new Dealer(numG,10000,s,OOS);
		new Partita(d);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket client=null;
		int sc = JOptionPane.showOptionDialog(null,
				"Creare una nuova partita o connettersi ad una esistente?",
				"Real Poker 2014", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
				Icone.logo, new String[] { "Crea nuova", "Connetti ad esistente" },
				"Crea nuova");
		if(sc==-1)System.exit(0);
		if(sc==0){
			creaServer();
			}else{

			String ip2= (String) JOptionPane.showInputDialog(null,"Inserisci l'IP a cui collegarti", "Inserimento IP", JOptionPane.WARNING_MESSAGE, Icone.logo,null,null); 
			
			try {
				client=new Socket(ip2,7777);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Errore durante la connessione al Server!","Errore",JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			}
			new Partita(client);
		}
		
	}
	
}
