package progettoPoker;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;

import progettoPoker.Comando.Tipo;

/**
 * Classe che gestisce i bui e il mazzo: rialza i bui dopo un certo numero di mani, preleva le quote dai giocatori,
 * mischia il mazzo, da le carte ecc..
 * 
 *
 */
public class Dealer {
	private int buioPosizione;//indica il giocatore che dovrà pagare il piccolo buio
	private int nMano=0;//a che mano siamo
	private int posD=0;
	private int nGiocatori=0;
	private int piccoloBuio;//il valore del piccolo buio (il grande buio è il doppio)
	private Carta []mazzo=new Carta[52];//il mazzo visto come array di 52 carte
	private int primaCarta=51;
	private Socket[] s;/*invece di eliminare le carte che diamo ai giocatori dal mazzo, manteniamo un indice 
	*della prima "carta utile" del mazzo
	*/
	private int puntata=piccoloBuio*2;
	private final int SCALA_REALE=8;
	private Giocatore[]g;
	private int []piatto=new int [g.length];
	private Carta[] carteComuni=new Carta[5];
	public Socket[] getS() {
		return s;
	}

	public Carta[] getCarteComuni() {
		return carteComuni;
	}

	public Giocatore[] getG() {
		return g;
	}//getG

	public Dealer(int nGiocatori, int fiches, Socket []s) {
		this.s=s;
		this.nGiocatori=nGiocatori;
		g=new Giocatore[nGiocatori];
		g[0]=new Giocatore(fiches,0);
		for(int i=1; i<g.length;i++){
			g[i]=new Giocatore(fiches,s[i],i);
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
			return vincenti;
		}
		else{
			Mano.ManiMigliori(mani);
		}
		vincenti=new Giocatore[mani.size()];
		int i=0;
		for(Mano m:mani.keySet()){
			vincenti[i]=mani.get(m);
			i++;
		}
		return vincenti;
		
	}//vincitoreMano
	
	
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
	
	public void muoviDealer(){
		boolean trovato=false;
		for(int i=(posD+1)%nGiocatori; i==posD || !trovato ; i=(i+1)%nGiocatori){
			if(g[i].getFiches()>0){
				posD=i;
				trovato=true;
			}else 
				if(g[i].getFineGiro()){
					g[i].setFineGiro(false);
					posD=i;
					trovato=true;
				}
		}//for				
		nMano++;
	}//muoviDealer
	
	public void daiCarte(){		
		int j = (posD+1)%nGiocatori;
		do{	
			if(g[j].getFiches()>0){
				g[j].setCarta1(mazzo[primaCarta]);
				primaCarta--;
				j=(j+1)%nGiocatori;
			}
			
		}while( j != posD);

		int i = (posD+1)%nGiocatori;
		do{	
			if(g[i].getFiches()>0){
				g[i].setCarta1(mazzo[primaCarta]);
				primaCarta--;
				j=(i+1)%nGiocatori;
			}			
		}while( i != posD);
	}//daiCarte
	
	public void flop(ObjectOutputStream [] oos){
		primaCarta--;
		for(int i=0;i<3;i++, primaCarta--)
			carteComuni[i]=mazzo[primaCarta];
		Comando c=new Comando(Tipo.DAI_CARTA, Arrays.copyOf(carteComuni, 3) );
		for(int i=0; i<oos.length; i++){
			try {
				oos[i].writeObject(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}//flop
	
	public void turn(ObjectOutputStream [] oos){
		primaCarta--;
		carteComuni[3]=mazzo[primaCarta];
		Comando c=new Comando(Tipo.DAI_CARTA, carteComuni[3] );
		for(int i=0; i<oos.length; i++){
			try {
				oos[i].writeObject(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}//turn
	
	public void river(ObjectOutputStream [] oos){
		carteComuni[4]=mazzo[primaCarta];
		primaCarta--;
		Comando c=new Comando(Tipo.DAI_CARTA, carteComuni[4] );
		for(int i=0; i<oos.length; i++){
			try {
				oos[i].writeObject(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}//river
	
	public void fold(int gioc){
		if(buioPosizione==gioc){
			g[gioc].setFiches(g[gioc].getFiches()-piccoloBuio);
			piatto[gioc]+=piccoloBuio;
		}
		if(gioc==posGrandeBuio()){
			g[gioc].setFiches(g[gioc].getFiches()-piccoloBuio*2);
			piatto[gioc]+=piccoloBuio*2;
		}
		g[gioc].setInGioco(false);
	}//fold
	
	public int posGrandeBuio() {
		int i=buioPosizione+1%nGiocatori;
		while(!g[i].getInGioco()){i++;}
		return i;
	}

	public void checkCall(int gioc){
		if(g[gioc].getFiches()<puntata){
			piatto[gioc]+=g[gioc].getFiches();
			g[gioc].setFiches(0);
		}		
		else{
			g[gioc].setFiches(g[gioc].getFiches()-puntata);
			piatto[gioc]+=puntata;
		}
	}//checCall
	
	public void raise(int gioc, int fiches){
		g[gioc].setFiches(g[gioc].getFiches()-fiches);
		piatto[gioc]+=fiches;
		puntata=fiches;
	}//rais

	public void fineMano(ObjectOutputStream[] oOS) {
		Giocatore [] vincitori=vincitoreMano();
		Comando vin=null;
		int quota=0;
		int vincita=0;
		for(int i=0;i<vincitori.length;i++){
			quota=piatto[vincitori[i].indice]/vincitori.length;
			for (int j = 0; j < g.length; j++) {
				if(piatto[j]/vincitori.length<quota)
					vincita+=piatto[j]/vincitori.length;
				else
					vincita+=quota;
			}
			vin=new Comando(null,vincitori[i].getFiches()+vincita);
			try {
				oOS[vincitori[i].indice].writeObject(vin);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vincita=0;
		}
		muoviDealer();
		for (int i = 0; i < g.length; i++) {
			if(g[i].getFiches()!=0)
			g[i].setInGioco(true);
		}
	}
	
}//Dealer
