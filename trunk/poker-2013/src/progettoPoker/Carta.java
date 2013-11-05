package progettoPoker;
/**
 * Classe che concretizza l'idea d una carta con 2 campi un valore numerico da 1 a 13 e un carattere che rappresenta 
 * il palo c,q,f,p sono i caratteri ammessi, entrambi i valori sono costanti così una volta generata una carta non è
 * più modificabile. Comprende il costruttore che riceve il palo e il valore e i metodi getter.
 *
 */
public class Carta {
	private final int val;
	private final char palo;
	public Carta(int val,char palo){
		this.val=val;
		this.palo=palo;
	}
	public int getVal() {
		return val;
	}
	public char getPalo() {
		return palo;
	}
	
}
