package progettoPoker;

import grafica.GraficaPoker;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import progettoPoker.Comando.Tipo;

/**
 * Rappresenta il singolo giocatore con le proprie fiches e carte
 *
 */
public class Giocatore {
	private String nickName;
	private int fiches;
	private Carta carta1;
	private Carta carta2;
	private boolean inGioco=true;
	//se un giocatore viene eliminato fineGiro rimane true fino a quando non diventa dealer
	private boolean fineGiro=true; 
	private Socket client=null;    
	private Comando com=null;
	private ObjectOutputStream OOS=null;
	protected final int indice;
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public int getFiches() {
		return fiches;
	}
	public boolean getInGioco(){
		return inGioco;
	}
	public void setInGioco(boolean inGioco) {
		this.inGioco = inGioco;
	}
	public boolean getFineGiro(){
		return fineGiro;
	}
	public void setFineGiro(boolean fineGiro){
		this.fineGiro=fineGiro;
	}

	public void setFiches(int fiches) {
		this.fiches = fiches;
		if(OOS!=null){
			com=new Comando(null,fiches);
			try {
				OOS.writeObject(com);
				OOS.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public Carta getCarta1() {
		return carta1;
	}
	public void setCarta1(Carta carta1) {
		this.carta1 = carta1;
		if(OOS!=null){
		com=new Comando(Tipo.DAI_CARTA,carta1);
		while(true){
			try {
				OOS.writeObject(com);
				OOS.flush();
				break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//System.out.println("errore setcarta1");
			}
		}
		}
	}
	public Carta getCarta2() {
		return carta2;
	}
	public Socket getSocket(){
		return client;
	}
	public void setCarta2(Carta carta2) {
		this.carta2 = carta2;
		if(OOS!=null){
		com=new Comando(Tipo.DAI_CARTA,carta2);
		try {
			OOS.writeObject(com);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}
	public Giocatore(int fiches,int indice) {
		this.fiches=fiches;
		this.indice=indice;
	}
	public Giocatore(int fiches,Socket s,int indice,ObjectOutputStream OOS) {
		this.fiches=fiches;
		this.indice=indice;
		client=s;
		com=new Comando(null,fiches);
		this.OOS=OOS;
		try {
			OOS.writeObject(com);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void setCartaServer1(Carta carta,GraficaPoker gp) {
		this.carta1 = carta;
		gp.Giocatori[0].setCarte(carta, 1);
	}

	public void setCartaServer2(Carta carta,GraficaPoker gp) {
		this.carta2 = carta;	
		gp.Giocatori[0].setCarte(carta, 2);
	}

	public void disconnetti() {
		OOS=null;
	}

}
