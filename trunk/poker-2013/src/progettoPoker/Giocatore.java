package progettoPoker;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import progettoPoker.Comando.Tipo;

/**
 * Rappresenta il singolo giocatore con le proprie fiches e carte
 *
 */
public class Giocatore {
	private int fiches;
	private Carta carta1;
	private Carta carta2;
	private boolean inGioco=true;
	private Socket client=null;
	private Comando com=null;
	private ObjectOutputStream OOS=null;
	public int getFiches() {
		return fiches;
	}
	public boolean getInGioco(){
		return inGioco;
	}
	public void setInGioco(boolean inGioco) {
		this.inGioco = inGioco;
	}
	public void setFiches(int fiches) {
		this.fiches = fiches;
		if(OOS!=null){
			com=new Comando(null,fiches);
			try {
				OOS.writeObject(com);
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
		try {
			OOS.writeObject(com);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public Giocatore(int fiches) {
		this.fiches=fiches;
	}
	public Giocatore(int fiches,Socket s) {
		this.fiches=fiches;
		client=s;
		com=new Comando(null,fiches);
		try {
			OOS=new ObjectOutputStream( s.getOutputStream());
			OOS.writeObject(com);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
