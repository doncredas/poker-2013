package progettoPoker;

import java.io.Serializable;

import progettoPoker.Comando.Tipo;


public class Comando implements Serializable {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 4387454705887783423L;
	public enum Tipo{DAI_CARTA,FOLD,CHECK_CALL,RAISE,NOTIFICA, NICK_NAME,GIOCATORI,FINE_MANO,
		DISCONNESSIONE,GAME_OVER,VINCITORI,ATTIVA};
	Carta c;
	Carta[] car=new Carta[3];
	int fiches=-1;
	int gioc;  //numero giocatori totali o giocatore corrente
	int giocN; //posizione giocatore corrente
	Tipo t;
	Tipo t1;
	String [] nickName;
	String nick;
	boolean[] rimanenti;
	int valPiatto=-1;
	int puntata=-1;
	int fichesGioc[];
	int dealer[]=null;
	int vincite[];
	String statistica;
	
	public String getStatistica() {
		return statistica;
	}

	public void setStatistica(String statistica) {
		this.statistica = statistica;
	}

	public int[] getVincite() {
		return vincite;
	}

	public int getValPiatto() {
		return valPiatto;
	}

	public void setValPiatto(int valPiatto) {
		this.valPiatto = valPiatto;
	}

	public int getPuntata() {
		return puntata;
	}

	public void setPuntata(int puntata) {
		this.puntata = puntata;
	}
	
	public void setDealer(int[] dealer) {
		this.dealer = dealer;
	}

	public int[] getFichesGioc() {
		return fichesGioc;
	}

	public void setFichesGioc(int[] fichesGioc) {
		this.fichesGioc = fichesGioc;
	}

	public Comando(Tipo nickName, String name){
		this.t = nickName;
		this.nick = name;
	}
	
	public Comando(Tipo t){
		this.t=t;
	}
	Comando(Tipo t,Carta c){
		this.t=t;
		this.c=c;
	}
	Comando(Tipo t,Carta c,int i){
		this.t=t;
		this.c=c;
		this.fiches=i;
	}
	Comando(Tipo t,Carta[] c){
		this.t=t;
		this.car=c;
	}
	public Comando(Tipo t,int i){
		this.t=t;
		this.fiches=i;
	}
	Comando(Tipo t,int i, int j){
		this.t=t;
		this.gioc=i;
		this.giocN=j;
	}
	Comando(Tipo t, Tipo t1,int g){
		this.t=t;
		this.t1=t1;
		this.gioc=g;
	}
	
	Comando(Tipo t, Tipo t1, String [] nickName){
		this.t=t;
		this.t1=t1;
		this.nickName=nickName;
	}
	
	Comando(Tipo t, Tipo t1, int g, int fiches){
		this.t=t;
		this.t1=t1;
		this.gioc=g;
		this.fiches=fiches;
	}
	
	public Comando(Tipo notifica, Tipo fineMano, boolean[] rimanenti) {
		this.t=notifica;
		this.t1=fineMano;
		this.rimanenti=rimanenti;
	}

	public Comando(Comando c2,Object o) {
		this.c=c2.c;
		this.car=c2.car;
		this.fiches=c2.fiches;
		this.gioc=c2.gioc;  
		this.giocN=c2.giocN; 
		this.t=c2.t;
		this.t1=c2.t1;
		this.nickName=c2.nickName;
		this.nick=c2.nick;
		this.rimanenti=c2.rimanenti;
		this.valPiatto=c2.valPiatto;
		this.puntata=c2.puntata;
		this.fichesGioc=c2.fichesGioc;
		this.dealer=c2.dealer;
	}

	public Comando(Tipo vincitori, int[] vincite) {
		this.t=vincitori;
		this.vincite=vincite;
	}

	public String getNick(){
		return nick;
	}
	public String[] getNickName(){
		return nickName;
	}
	public Carta getC() {
		return c;
	}
	public Carta[] getCar() {
		return car;
	}
	public int getFiches() {
		return fiches;
	}
	public Tipo getT() {
		return t;
	}
	public int getGioc(){
		return gioc;
	}
	public int getGiocN(){
		return giocN;
	}

}
