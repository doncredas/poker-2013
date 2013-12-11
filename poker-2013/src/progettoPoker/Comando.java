package progettoPoker;

import java.io.Serializable;

public class Comando implements Serializable {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 4387454705887783423L;
	public enum Tipo{DAI_CARTA,FOLD,CHECK_CALL,RAISE,NOTIFICA, NICK_NAME};
	Carta c;
	Carta[] car=new Carta[3];
	int fiches;
	int gioc;
	Tipo t;
	Tipo t1;
	String nickName;
	
	public Comando(Tipo nickName, String name){
		this.t = nickName;
		this.nickName = name;
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
	Comando(Tipo t, Tipo t1,int g){
		this.t=t;
		this.t1=t1;
		this.gioc=g;
	}
	
	Comando(Tipo t, Tipo t1, int g, String nickName){
		this.t=t;
		this.t1=t1;
		this.gioc=g;
		this.nickName=nickName;
	}
	
	Comando(Tipo t, Tipo t1, int g, int fiches){
		this.t=t;
		this.t1=t1;
		this.gioc=g;
		this.fiches=fiches;
	}
	
	public String getNickName(){
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

}
