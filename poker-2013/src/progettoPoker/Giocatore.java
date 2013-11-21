package progettoPoker;

import java.net.Socket;

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
	public int getFiches() {
		return fiches;
	}
	public boolean getInGioco(){
		return inGioco;
	}
	public void setFiches(int fiches) {
		this.fiches = fiches;
	}
	public Carta getCarta1() {
		return carta1;
	}
	public void setCarta1(Carta carta1) {
		this.carta1 = carta1;
	}
	public Carta getCarta2() {
		return carta2;
	}
	public Socket getSocket(){
		return client;
	}
	public void setCarta2(Carta carta2) {
		this.carta2 = carta2;
	}
	public Giocatore(int fiches) {
		this.fiches=fiches;
	}
	public Giocatore(int fiches,Socket s) {
		this.fiches=fiches;
		client=s;
	}

}
