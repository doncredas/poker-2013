package progettoPoker;

import java.io.Serializable;

import progettoPoker.Comando.Tipo;

public class Comando implements Serializable {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 4387454705887783423L;
	public enum Tipo{DAI_CARTA,FOLD,CHECK_CALL,RAISE,NOTIFICA, NICK_NAME,GIOCATORI,FINE_MANO};
	Carta c;
	Carta[] car=new Carta[3];
	int fiches;
	int gioc;  //numero giocatori totali
	int giocN; //posizione giocatore corrente
	Tipo t;
	Tipo t1;
	String [] nickName;
	String nick;
	boolean[] rimanenti;
	
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
	Comando(Tipo t,int i){
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
