package progettoPoker;


import grafica.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import progettoPoker.Comando.Tipo;


public class Partita {
	private Socket s = null;
	private ObjectOutputStream OOS[] = null;
	private ObjectInputStream OIS[] = null;
	private ObjectOutputStream oos=null;
	private ObjectInputStream ois=null;
	private Dealer d = null;
	private int tempo=35;
	private int nCarta=0;
	private int fiches;
	private Cronometro cron=new Cronometro();
	private GraficaPoker gp;
	private int posGioc=0; //posizione del giocatore che si vede centrale rispetto al server
	private Comando c=null;
	private Comando risp=null;
	private int disconnessi[]=null; //contiene 0-1-2 
	                                 //0:giocatore in gioco
	                                 //1:giocatore sconfitto
	                                 //2:giocatore disconnesso
	                                        
	private boolean flag=false;
	private boolean rimanenti[];
	private Boolean azioneRegistrata=false;
	private Comando azioneAllIn=null;
	
	public Partita(Socket client)  {
		this.s=client;
		try {
			oos=new ObjectOutputStream(s.getOutputStream());
			ois=new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		eseguiClient();
	}
	
	private void eseguiClient() {
		Comando com=null;
		Comando vecchio=null;
		while(true){
			while(com==vecchio){
				try {
					com=(Comando)ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					gp.dispose();
					JOptionPane.showMessageDialog(null, "Server disconnesso!","Errore",JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
			}
			eseguiComando(com,gp);
			vecchio=com;
		}
	}

	private void eseguiComando(Comando com,GraficaPoker gp) {
		Comando risp=null;
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
			if(com.valPiatto!=-1)
				gp.setPot(com.valPiatto);
			if(com.puntata!=-1){
				gp.setMinBar(com.puntata);
				if(com.puntata!=0)
					gp.setPuntataCall(com.puntata);
			}
			if(com.statistica!=null){
				GraficaPoker.scriviStatistica(com.getStatistica());
			}
		}
		if(com.getT()==Tipo.GIOCATORI){
			this.gp=new GraficaPoker(com.gioc);
			this.gp.creaOrol(tempo-5);
			GraficaPoker.Giocatori[0].setFiches(fiches);
			this.posGioc=com.getGiocN()+1;
		}else
		if(com.t==null&&com.fiches==-1){
			if(gp.getGiocatore(0).getFichesVal()!=0){
				if(!(altriAllIn()&&azioneRegistrata)){
					if(gp.getPuntataCall()!=0){
						gp.restartOr();
						this.gp.disableBottoni(false);
						while(risp==null&&gp.getTime()>0){
							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							risp=this.gp.getComando();
						}if(gp.getTime()==0)
							risp=new Comando(Tipo.FOLD);
					}
					if(azioneRegistrata){
						azioneRegistrata=false;
						azioneAllIn=null;
					}
					if(altriAllIn()){
						if(gp.getPuntataCall()==0)
							risp=new Comando(Tipo.CHECK_CALL);
						azioneAllIn=risp;
						azioneRegistrata=true;
					}
				}else
					risp=azioneAllIn;
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
			String nick="";
			if(flag)
				JOptionPane.showMessageDialog(null,"Nickname già in uso, inserirne un altro!","Attenzione",JOptionPane.WARNING_MESSAGE);
			flag=true;
			do{
			   nick=(String) JOptionPane.showInputDialog(null,"Inserisci il tuo Nickname", "NickName", JOptionPane.WARNING_MESSAGE, Icone.logo,null,null);
			   if(nick.equals("")){
				     JOptionPane.showMessageDialog(null,"Non hai inserito un nickname!","Attenzione",JOptionPane.WARNING_MESSAGE);
				     continue;
			   }
			   nick=nick.trim();
			   if(nick.length()>11)
				   JOptionPane.showMessageDialog(null,"Nickname troppo lungo, inserirne un altro!","Attenzione",JOptionPane.WARNING_MESSAGE);
			}while(nick.length()>11 || nick.equals(""));
			risp=new Comando(Tipo.NICK_NAME,nick);
			GraficaPoker.Giocatori[0].setNome(nick);
			break;
		case DAI_CARTA:
			if(com.getC()!=null){
				if(rimanenti==null||rimanenti[posGioc])
				nCarta=(nCarta%4)+1;
				else{
					if(nCarta==1||nCarta==0)nCarta=2;
					nCarta=(nCarta%4)+1;
				}
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
			}else{
				this.gp.setFlop(com.getCar(),this.gp);
			}
		break;
		case GAME_OVER:
			gameOver(com.getNick());
			break;
		case VINCITORI:
			int vinc[]=Arrays.copyOf(com.vincite, com.vincite.length);
			int tmp=vinc[posGioc];
			for (int i = 0; i < posGioc; i++) {
				vinc[i+1]=vinc[i];
			}
			vinc[0]=tmp;
			vincitoreMano(vinc);
			break;
		case ATTIVA:
			gp.restartOr();
			if(com.getFiches()<posGioc)gp.setAttivo(com.getFiches()+1);
			if(com.getFiches()>posGioc)gp.setAttivo(com.getFiches());
			break;
		case NOTIFICA:
			gp.stopOr();
			eseguiNotifica(com);
		default:
			break;
		}
		
		return risp;
	}

	private void eseguiNotifica(Comando com) {

		switch(com.t1){
		case NICK_NAME:
			for(int i=0;i<posGioc;i++){
				gp.getGiocatore(i+1).setNome(com.getNickName()[i]);
			}
			for(int i=posGioc+1;i<com.getNickName().length;i++){
				gp.getGiocatore(i).setNome(com.getNickName()[i]);
			}
			break;
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
			rimanenti=com.rimanenti;
			gp.reset();
			nCarta=0;
			for (int i = 0; i < rimanenti.length; i++) {
				if(i<posGioc){
					gp.getGiocatore(i+1).setVisible(rimanenti[i]);
				}else
					if(i==posGioc){
						gp.getGiocatore(0).setVisible(rimanenti[i]);
						gp.eliminaBui();
					}else
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
		default:
			break;
		}
		
	}

	public Partita(Dealer d)  {
		gp=new GraficaPoker(d.getG().length);
		gp.creaOrol(tempo-5);
		disconnessi=new int[d.getG().length];
		this.d=d;
		OOS=d.getOOS();
		OIS=new ObjectInputStream[d.getG().length-1];
		for(int i=0;i<d.getS().length;i++){
			try {
				OIS[i]=new ObjectInputStream(d.getS()[i].getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		eseguiServer();
	}

	private void eseguiServer() {
		generaNick();
		int primoGiocatore=0;//TODO provare a spostare
		while(true){
			gp.reset();
			d.eliminati();
			gp.resetGioc(d);
			gp.repaint();
			d.mischia();
			d.daiCarte(gp,disconnessi);
			gp.daiCarteGioc();
			c=new Comando(null);
			primoGiocatore=(d.getPosD()+1)%d.getG().length;
			int ultimoRaise;//TODO provare a spostare
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
						cron.reset();
						if(i==0){
							gp.setMinBar(d.getPuntata());
							gp.setPuntataCall(d.getPuntata());
							gp.setMaxBar(d.getG()[0].getFiches());
							gp.getGiocatore()[0].setFiches(d.getG()[0].getFiches());
							if(d.getG()[0].getFiches()!=0){
								gp.disableBottoni(false);
								gp.restartOr();
								inviaComando(new Comando(Tipo.ATTIVA,i));
								gp.setAttivo(i);
								gp.resetComando();
								while(risp==null&&gp.getTime()>0){
									try {
										Thread.sleep(200);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									risp=gp.getComando();
								}
								gp.stopOr();
								if(gp.getTime()==0){
									risp=new Comando(Tipo.FOLD);
								}
							}else
								risp=new Comando(Tipo.CHECK_CALL);
						}else{
							setComando(c);
							try {
								OOS[i-1].writeObject(new Comando(c,null));
							} catch (IOException e) {
								eseguiComando(new Comando(Tipo.DISCONNESSIONE),i+1);
							}
							inviaComando(new Comando(Tipo.ATTIVA,i));
							gp.restartOr();
							gp.setAttivo(i);
							while(risp==null&&cron.getSecondi()<tempo){
								try {
									risp=(Comando) OIS[i-1].readObject();
								} catch (ClassNotFoundException e) {
									e.printStackTrace();
								} catch (IOException e) {
									risp=new Comando(Tipo.DISCONNESSIONE);
								}
							}
							gp.stopOr();
							if(cron.getSecondi()>tempo)risp=new Comando(Tipo.FOLD);
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
			resetRimanenti(d.getRimanenti());
		}
		
	}
	

	private void vincitoreMano(int[] is) {
		for(int i=0;i<is.length;i++){
			gp.getGiocatore()[i].resetFiches(gp);
			if(is[i]!=0){
			Fiches f=new Fiches(is[i],i,gp);
			Fiches.setPosition(i, f, gp);
			gp.vincitoreMano(i+1, f);
			}
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
					
				JOptionPane.showMessageDialog(gp, d.getG()[i].getNickName()+" vince la partita","Vincitore",JOptionPane.INFORMATION_MESSAGE,Icone.logo);
				System.exit(0);
			}
		}
	}
	
	private void gameOver(String nick){
		gp.dispose();
		JOptionPane.showMessageDialog(gp, nick+" vince la partita","Vincitore",JOptionPane.INFORMATION_MESSAGE,Icone.logo);
		try {
			oos.writeObject(new Comando(Tipo.GAME_OVER));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	

	private void generaNick() {
		c=new Comando(Tipo.NICK_NAME);
		Comando vecchioRisp=null;
		boolean ok=true;
		String nick="";
		do{
			nick= (String) JOptionPane.showInputDialog(null,"Inserisci il tuo Nickname", "NickName", JOptionPane.WARNING_MESSAGE, Icone.logo,null,null);
			if(nick.equals("")){
			     	JOptionPane.showMessageDialog(null,"Non hai inserito un nickname!","Attenzione",JOptionPane.WARNING_MESSAGE);
			     	continue;
			}
			nick=nick.trim();   
			if(nick.length()>11)
			   JOptionPane.showMessageDialog(null,"Nickname troppo lungo, inserirne un altro!","Attenzione",JOptionPane.WARNING_MESSAGE);
		   
		}while(nick.equals("")|| nick.length()>11);
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
			for (int j = 0; j <= i; j++) {
				if(d.getG()[j].getNickName().equalsIgnoreCase(risp.getNick())){
					c=new Comando(Tipo.NICK_NAME);
					try {
						OOS[i].writeObject(c);
					} catch (IOException e) {
						e.printStackTrace();
					}
					i--;			
					ok=false;
					break;
				}
			}
			if(ok){
				GraficaPoker.Giocatori[i+1].setNome(risp.getNick());
				d.getG()[i+1].setNickName(risp.getNick());
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
				e.printStackTrace();
			}
		}
		
	}

	private void resetRimanenti(boolean[] rimanenti) {
		for (int i = 0; i < rimanenti.length; i++) {
			if(!rimanenti[i]){
				gp.getGiocatore(i).setVisible(false);
				if(disconnessi[i]!=2)disconnessi[i]=1;
			}
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
			d.fineMano(OOS,gp);
			vincitoreMano(d.getVincite());
			eseguiComando(new Comando(Tipo.VINCITORI,d.getVincite()),1);
		}
		return cont<2;
	}

	private void CreaComando() {
		
		if(d.getCarteComuni()[0]==null){
			d.flop(OOS,disconnessi);
			gp.setFlop(Arrays.copyOf(d.getCarteComuni(),3), gp);
		}else
			if(d.getCarteComuni()[3]==null){
				d.turn(OOS,disconnessi);
				gp.setTurn(d.getCarteComuni()[3]);
			}else
				if(d.getCarteComuni()[4]==null){
					d.river(OOS,disconnessi);
					gp.setRiver(d.getCarteComuni()[4]);
				}else{
					d.fineMano(OOS,gp);
					vincitoreMano(d.getVincite());
					eseguiComando(new Comando(Tipo.VINCITORI,d.getVincite()),0);
				}
	}

	private void eseguiComando(Comando c,int i) {
		Comando notifica;
		switch(c.getT()){
		case FOLD:
			d.fold(i);
			gp.getGiocatore(i).setFold(true);
			GraficaPoker.scriviStatistica(d.getG()[i].getNickName()+" ha foldato");
			notifica=new Comando(Tipo.NOTIFICA,Tipo.FOLD,i);
			setComando(notifica);
			notifica.setStatistica(d.getG()[i].getNickName()+" ha foldato");
			inviaComando(notifica);
			break;
		case CHECK_CALL:
			d.checkCall(i);
			GraficaPoker.Giocatori[i].setFiches(d.getG()[i].getFiches());
			gp.punta(i+1, d.getPuntata(), gp); 
			notifica=new Comando(Tipo.NOTIFICA,Tipo.CHECK_CALL,i,d.getPuntata());
			setComando(notifica);
			if(d.getPuntata()!=0){
				GraficaPoker.scriviStatistica(d.getG()[i].getNickName()+" ha effettuato una call di "+d.getPuntata()+"€");
				notifica.setStatistica(d.getG()[i].getNickName()+" ha effettuato una call di "+d.getPuntata()+"€");
			}else{
				GraficaPoker.scriviStatistica(d.getG()[i].getNickName()+" ha effettuato un check");
				notifica.setStatistica(d.getG()[i].getNickName()+" ha effettuato un check");
			}
			inviaComando(notifica);
			break;
		case RAISE:
			d.raise(i,c.getFiches());
			GraficaPoker.Giocatori[i].setFiches(d.getG()[i].getFiches());
			gp.punta(i+1,c.getFiches(), gp);
			GraficaPoker.scriviStatistica(d.getG()[i].getNickName()+" ha effettuato un raise di "+c.getFiches()+"€");
			notifica=new Comando(Tipo.NOTIFICA,Tipo.RAISE,i,c.getFiches());
			setComando(notifica);
			notifica.setStatistica(d.getG()[i].getNickName()+" ha effettuato un raise di "+c.getFiches()+"€");
			inviaComando(notifica);
			break;
		case DISCONNESSIONE:
			d.getG()[i].disconnetti();
			disconnessi[i]=2;
			d.fold(i);
			d.getG()[i].setFiches(0);
			gp.getGiocatore(i).setAttivo(false);
			gp.getGiocatore(i).setVisible(false);
			GraficaPoker.scriviStatistica(d.getG()[i].getNickName()+" si è disconnesso");
			notifica=new Comando(Tipo.NOTIFICA,Tipo.DISCONNESSIONE,i);
			notifica.setStatistica(d.getG()[i].getNickName()+" si è disconnesso");
			setComando(notifica);
			inviaComando(notifica);
			break;
		case VINCITORI:
			String s="";
			boolean flag=true;
			if(i==0)
				for(int j=0;j<d.getVincite().length;j++){
					if(d.getVincite()[j]>0){
						if(flag){
							s+="carte a terra: "+d.getManiVincenti()[j].c[0]+" "+
									d.getManiVincenti()[j].c[1]+" "+
									d.getManiVincenti()[j].c[2]+" "+
									d.getManiVincenti()[j].c[3]+" "+
									d.getManiVincenti()[j].c[4]+'\n';
							flag=false;
						}
						s+=d.getG()[j].getNickName()+" vince "+ d.getVincite()[j]+"€ con "+d.getManiVincenti()[j].getValString()+'\n'
								+" carte in mano "+d.getManiVincenti()[j].c[5]+" "+d.getManiVincenti()[j].c[6]+'\n';
					}
				}
			else
				for(int j=0;j<d.getVincite().length;j++){
					if(d.getVincite()[j]>0){
						s+=d.getG()[j].getNickName()+" vince "+ d.getVincite()[j]+"€";
					}
				}
			c.setStatistica(s);
			GraficaPoker.scriviStatistica(s);
			inviaComando(c);
			break;
		default:
		}
		
	}
	

	private void setComando(Comando notifica) {
		notifica.setPuntata(d.getPuntata());
		int fiches[]=new int [d.getG().length];
		notifica.setValPiatto(d.getValPiatto());
		for (int i = 0; i < fiches.length; i++) {
			fiches[i]=d.getG()[i].getFiches();
		}
		notifica.setFichesGioc(fiches);
	}

	private void inviaComando(Comando c) {
		for(int i=0;i<OOS.length;i++)
			if(disconnessi[i+1]!=2)	
			try {
				OOS[i].writeObject(c);
			} catch (IOException e) {
				eseguiComando(new Comando(Tipo.DISCONNESSIONE),i+1);
			}
		
	}
	
	private static void mostraIp() {
		try {
			Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
			String inet=null;
			for (NetworkInterface netint : Collections.list(nets)){
					try{
					inet= netint.getInetAddresses().nextElement().getHostAddress();
					}catch(Exception e){}
				if(inet.startsWith(""+10))break;

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
