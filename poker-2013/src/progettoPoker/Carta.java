package progettoPoker;

public class Carta {
	final int val;
	final char palo;
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
