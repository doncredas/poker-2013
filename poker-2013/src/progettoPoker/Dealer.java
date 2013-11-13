package progettoPoker;

import java.util.HashMap;

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
	private final int SCALA_REALE=8;
	private Giocatore[]g;
	private Carta[] carteComuni=new Carta[5];
	public Dealer(int nGiocatori, int fiches) {
		g=new Giocatore[nGiocatori];
		for(int i=0; i<g.length;i++){
			g[i]=new Giocatore(fiches);
		}
		for(int i=0;i<52;i++){
			if(i<=12){mazzo[i]=new Carta(i+1,'c');continue;}
			if(i<=25){mazzo[i]=new Carta((i%13)+1,'q');continue;}
			if(i<=38){mazzo[i]=new Carta((i%13)+1,'f');continue;}
			else{mazzo[i]=new Carta((i%13)+1,'p');}
		}
		piccoloBuio=fiches/100;
		
	}//Costruttore
	
	public Giocatore[] vincitoreMano(){
		HashMap<Mano, Giocatore> mani= new HashMap<Mano, Giocatore>();
		Giocatore[] vincenti = null;
		
		for(int i=0; i<g.length; i++)
			if(g[i].getInGioco()) mani.put(new Mano(carteComuni, g[i].getCarta1(), g[i].getCarta2()), g[i]);
		int manoMigliore=0;
		int manoCorrente=0;
		Mano migliore=null;
		
		for(Mano m : mani.keySet()){
			manoCorrente=m.getVal();
			if(manoCorrente>manoMigliore){
				manoMigliore=manoCorrente;
				migliore=m;
			}
		}
		
		for(Mano m : mani.keySet())
			if(m.getVal()!=manoMigliore) mani.remove(m);
		
		
		if(mani.size()==1){
			vincenti=new Giocatore[1];
			vincenti[1]=mani.get(migliore);
		}
		else{
			
		}
		
		
		return vincenti;
		
	}
	
	
	public void mischia(){
		for(int k=0;k<3;k++){
			int iS=0, iD, sx, dx, iTotale=0;			
			iD=(int)((Math.random()*10)+22); //indice per dividere il mazzo a metà
			int inD=iD;
			Carta[] mazzoMischiato=new Carta[52];
			while(iTotale<52){
				sx=(int)Math.round(Math.random()*2)+1;
				dx=(int)Math.round(Math.random()*2)+1;
				 for(int i=0; i<sx && iS<inD; i++){
					 mazzoMischiato[iTotale]=mazzo[iS+i];
					 iS++;
					 iTotale++;
				 } 
				 for(int j=0; j<dx && iD<52; j++){
					 mazzoMischiato[iTotale]=mazzo[iD+j];
					 iD++;
					 iTotale++;
				 }
			}//while
			Carta[] tmp=mazzo;
			mazzo=mazzoMischiato;
			mazzoMischiato=tmp;
		}//for
	}//mischia
	
	public void daiCarte(){
		for(int i = 0; i < g.length; i++){
			g[i].setCarta1(mazzo[primaCarta]);
			primaCarta--;
		}
		for(int i = 0; i < g.length; i++){
			g[i].setCarta2(mazzo[primaCarta]);
			primaCarta--;
		}
	}//daiCarte
	
	public void flop(){
		primaCarta--;
		for(int i=0;i<3;i++, primaCarta--)
			carteComuni[i]=mazzo[primaCarta];
	}//flop
	
	public void turnRiver(){
		primaCarta--;
		if(carteComuni[3]==null)
			carteComuni[3]=mazzo[primaCarta];
		else
			carteComuni[4]=mazzo[primaCarta];
		primaCarta--;
	}//turnRiver
	
	
	
}//Dealer
