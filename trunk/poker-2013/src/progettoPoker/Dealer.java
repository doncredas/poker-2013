package progettoPoker;
/**
 * Classe che gestisce i bui e il mazzo: rialza i bui dopo un certo numero di mani, preleva le quote dai giocatori,
 * mischia il mazzo, da le carte ecc..
 * 
 *
 */
public class Dealer {
	private int buioPosizione;//indica il giocatore che dovrà pagare il piccolo buio
	private int nMano=0;//a che mano siamo
	private int piccoloBuio;//il valore del piccolo buio (il grande buio è il doppio)
	private Carta []mazzo=new Carta[52];//il mazzo visto come array di 52 carte
	private int primaCarta=51;/*invece di eliminare le carte che diamo ai giocatori dal mazzo, manteniamo un indice 
	*della prima "carta utile" del mazzo
	*/
	private Giocatore[]g;
	private Carta[] carteComuni=new Carta[5];
	public Dealer(int nGiocatori, int fiches) {
		g=new Giocatore[nGiocatori];
		for(int i=0; i<g.length;i++){
			g[i]=new Giocatore(fiches);
		}
		for(int i=0;i<52;i++){
			if(i<=13){mazzo[i]=new Carta(i+1,'c');continue;}
			if(i<=26){mazzo[i]=new Carta((i%13)+1,'q');continue;}
			if(i<=39){mazzo[i]=new Carta((i%13)+1,'f');continue;}
			else{mazzo[i]=new Carta((i%13)+1,'p');}
		}
		piccoloBuio=fiches/100;
		
	}//Costruttore
	
	
	
	

}
