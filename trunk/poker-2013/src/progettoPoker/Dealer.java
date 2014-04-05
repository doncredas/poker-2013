package progettoPoker;

import grafica.GraficaPoker;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import progettoPoker.Comando.Tipo;

/**
 * Classe che gestisce i bui e il mazzo: rialza i bui dopo un certo numero di mani, preleva le quote dai giocatori,
 * mischia il mazzo, da le carte ecc..
 * 
 *
 */
public class Dealer {
	private int tempoBui=600;
	private double incrementoBui=2;
	private int buioPosizione;//indica il giocatore che dovrà pagare il piccolo buio
	private int nMano=0;//a che mano siamo
	private int posD=0;
	private int nGiocatori=0;
	private int piccoloBuio;//il valore del piccolo buio (il grande buio è il doppio)
	private Carta []mazzo=new Carta[52];//il mazzo visto come array di 52 carte
	private int primaCarta=51;/*invece di eliminare le carte che diamo ai giocatori dal mazzo, manteniamo un indice
	*della prima "carta utile" del mazzo
	*/
	private Cronometro c;
	private Socket[] s; //array dei client da passare ai singoli giocatori
	private ObjectOutputStream OOS[];
	private int puntata;
	private Giocatore[]g;
	private int []piatto;
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

	public Dealer(int nGiocatori, int fiches, Socket []s,ObjectOutputStream [] OOS) {
		this.s=s;
		this.OOS=OOS;
		this.nGiocatori=nGiocatori;
		g=new Giocatore[nGiocatori];
		piatto=new int [g.length];
		g[0]=new Giocatore(fiches,0);
		for(int i=1; i<g.length;i++){
			g[i]=new Giocatore(fiches,s[i-1],i,OOS[i-1]);
		}
		for(int i=0;i<52;i++){
			if(i<=12){mazzo[i]=new Carta(i+1,'c');continue;}
			if(i<=25){mazzo[i]=new Carta((i%13)+1,'q');continue;}
			if(i<=38){mazzo[i]=new Carta((i%13)+1,'f');continue;}
			else{mazzo[i]=new Carta((i%13)+1,'p');}
		}
		piccoloBuio=fiches/100;
		puntata=piccoloBuio*2;

		buioPosizione=1;
		//this.incrementoBui=incrementoBui;
		//this.tempoBui=tempoBui;
		c=new Cronometro();
		c.start();
		
	}//Costruttore
	
	public ObjectOutputStream[] getOOS() {
		return OOS;
	}

	public int getPiccoloBuio(){
		return buioPosizione;
	}
	
	public int getValBuio(){
		return piccoloBuio;
	}

	public Giocatore[] vincitoreMano(){
		HashMap<Mano, Giocatore> mani= new HashMap<Mano, Giocatore>();
		Giocatore[] vincenti = null;
		
		for(int i=0; i<g.length; i++)
			if(g[i].getInGioco()) mani.put(new Mano(carteComuni, g[i].getCarta1(), g[i].getCarta2()), g[i]);
		if(mani.size()>1){
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
		
		LinkedList<Mano> maniPerdenti=new LinkedList<Mano>();
		
		for(Mano m : mani.keySet())
			if(m.getVal()!=manoMigliore) maniPerdenti.add(m);
		
		for(Mano m:maniPerdenti){
			mani.remove(m);
		}

		if(mani.size()==1){
			vincenti=new Giocatore[1];
			vincenti[0]=mani.get(migliore);
			return vincenti;
		}
		else{
			Mano.ManiMigliori(mani);
		}
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
			iD=(int)((Math.random()*10)+22); //indice per dividere il mazzo a met
			int inD=iD;
			Carta[] mazzoMischiato=new Carta[52];
			while(iTotale<52){
				sx=(int)Math.round(Math.random()*2)+1;
				dx=(int)Math.round(Math.random()*2)+1;
				for(int j=0; j<dx && iD<52; j++){
					mazzoMischiato[iTotale]=mazzo[iD];
					iD++;
					iTotale++;
				}for(int i=0; i<sx && iS<inD; i++){
					mazzoMischiato[iTotale]=mazzo[iS];
					iS++;
					iTotale++;
				}
				 
			}//while
			mazzo=mazzoMischiato;
		}//for
		primaCarta=51;
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
	
	public int getPosD() {
		return posD;
	}

	public void daiCarte(GraficaPoker gp){		
		int j = (posD+1)%nGiocatori;
		do{	
			if(g[j].getFiches()>0){
				g[j].setCarta1(mazzo[primaCarta]);
				primaCarta--;
				j=(j+1)%nGiocatori;
			}
			if(j==posD){
				g[j].setCartaDealer1(mazzo[primaCarta], gp);
				primaCarta--;
			}
		}while( j != posD);

		int i = (posD+1)%nGiocatori;
		do{	
			if(g[i].getFiches()>0){
				g[i].setCarta2(mazzo[primaCarta]);
				primaCarta--;
				i=(i+1)%nGiocatori;
			}	
			if(i==posD){
				g[i].setCartaDealer2(mazzo[primaCarta], gp);
				primaCarta--;
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
		puntata=getGrandeBuio();
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
		puntata=getGrandeBuio();
	}//turn
	
	public void river(ObjectOutputStream [] oos){
		primaCarta--;
		carteComuni[4]=mazzo[primaCarta];
		Comando c=new Comando(Tipo.DAI_CARTA, carteComuni[4] );
		for(int i=0; i<oos.length; i++){
			try {
				oos[i].writeObject(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		puntata=getGrandeBuio();
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
		int i=(buioPosizione+1)%nGiocatori;
		while(!g[i].getInGioco()){i=(i+1)%nGiocatori;}
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
	
	public int getPuntata(){
		return puntata;
	}

	public int getGrandeBuio() {
		// TODO Auto-generated method stub
		return 0;
	}

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
			if(vincitori[i].indice!=0){
				try {
					oOS[vincitori[i].indice-1].writeObject(vin);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				//TODO gestire vittoria del server
				System.out.println("vittoria del server");
			}
			vincita=0;
		}
		muoviDealer();
		for (int i = 0; i < g.length; i++) {
			if(g[i].getFiches()!=0)
			g[i].setInGioco(true);
		}
		if(c.getSecondi()>=tempoBui){
			c.reset();
			piccoloBuio*=incrementoBui;
		}
		carteComuni= new Carta[5];
	}//fineMano

	public boolean[] getRimanenti() {
		boolean rimanenti[]=new boolean[g.length];
		for (int i=0;i<g.length;i++){
			rimanenti[i]=g[i].getInGioco();
		}
		return rimanenti;
		
	}
	
}//Dealer
