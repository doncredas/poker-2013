package progettoPoker;

import java.io.Serializable;

/**
 * Classe che concretizza l'idea d una carta con 2 campi un valore numerico da 1 a 13 e un carattere che rappresenta 
 * il palo c,q,f,p sono i caratteri ammessi, entrambi i valori sono costanti così una volta generata una carta non è
 * più modificabile. Comprende il costruttore che riceve il palo e il valore e i metodi getter.
 *
 */
public class Carta implements Comparable<Carta>,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8806130803849971201L;
	private final int val;
	private final char palo;
	public Carta(int val,char palo){
		this.val=val;
		this.palo=palo;
	}
	public Carta(Carta c){
		this.val=c.val;
		this.palo=c.palo;
	}
	public int getVal() {
		return val;
	}
	public char getPalo() {
		return palo;
	}
	@Override
	public int compareTo(Carta c) {
		if((c.val==1)&&(this.getVal()==1)) return 0;
		if(c.val==1)return 1;
		if(this.getVal()==1)return -1;
		return this.getVal()-c.getVal();
	}
	
}
