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
	private int puntataTotale;
	private Giocatore[]g;
	private int []piatto;
	private Carta[] carteComuni=new Carta[5];
	private int[] vincite;
	private Mano[] maniVincenti;
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
		maniVincenti=new Mano[nGiocatori];
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

		//this.incrementoBui=incrementoBui;
		//this.tempoBui=tempoBui;
		c=new Cronometro();
		c.start();
		
	}//Costruttore
	
	public ObjectOutputStream[] getOOS() {
		return OOS;
	}
	
	public int getValBuio(){
		return piccoloBuio;
	}

	public LinkedList<Giocatore []> vincitoreMano(){
		HashMap<Mano, Giocatore> mani= new HashMap<Mano, Giocatore>();
		LinkedList<Giocatore []>vincenti= new LinkedList<Giocatore []>();
		Giocatore[] vinc;
		int cont=0;
		for(int i=0; i<g.length; i++){
			if(!g[i].getInGioco())cont++;
		}
		Giocatore[] perd=new Giocatore[cont];
		Giocatore[]unico = new Giocatore[1];
		int index=0;
		for(int i=0; i<g.length; i++){
			if(g[i].getInGioco()){
				Mano tmp=new Mano(carteComuni, g[i].getCarta1(), g[i].getCarta2());
				mani.put(tmp, g[i]);
				maniVincenti[i]=tmp;
				unico[0]=g[i];
			}else{
				perd[index]=g[i];
				index++;
			}
		}		
		if(mani.size()>1){
		int manoMigliore=0;
		int manoCorrente=0;
		Mano migliore=null;
		HashMap<Mano,Giocatore> maniTmp=new HashMap<Mano, Giocatore>();
		while(!mani.isEmpty()){
		
		manoMigliore=0;
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
		
		maniTmp=new HashMap<Mano, Giocatore>(mani);
		for(Mano m:maniPerdenti){
			mani.remove(m);
		}
		
		if(mani.size()==1){
			vinc=new Giocatore[1];
			vinc[0]=mani.get(migliore);
		}
		else{
			Mano.ManiMigliori(mani);
			vinc=new Giocatore[mani.size()];
			int i=0;
			for(Mano m:mani.keySet()){
				vinc[i]=mani.get(m);
				i++;
			}
		}
		for(Mano m:mani.keySet()){
			maniTmp.remove(m);
		}
		
		vincenti.add(vinc);
		mani=maniTmp;
		}
		}else{
			vincenti.add(unico);
		}
		vincenti.add(perd);
		return vincenti;
		
	}//vincitoreMano
	
	
	public Mano[] getManiVincenti() {
		return maniVincenti;
	}

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
		for(int i=(posD+1)%nGiocatori; ; i=(i+1)%nGiocatori){
			if(g[i].getFiches()>0){
				posD=i;
				break;
			}else 
				if(g[i].getFineGiro()){
					g[i].setFineGiro(false);
					posD=i;
					break;
				}
		}//for				
	}//muoviDealer
	
	public int getPosD() {
		return posD;
	}

	public void daiCarte(GraficaPoker gp,int[] disconnessi){		
		int j = (posD+1)%nGiocatori;
		do{	
			if(disconnessi[j]==0){
				if(j==0){
					g[j].setCartaServer1(mazzo[primaCarta], gp);
					primaCarta--;
				}else
				if(g[j].getFiches()>0){
					g[j].setCarta1(mazzo[primaCarta]);
					primaCarta--;
				}
			}
			j=(j+1)%nGiocatori;
		}while( j != (posD+1)%nGiocatori);

		int i = (posD+1)%nGiocatori;
		do{	
			if(disconnessi[i]==0){
				if(i==0){
			
					g[i].setCartaServer2(mazzo[primaCarta], gp);
					primaCarta--;
				}else
					if(g[i].getFiches()>0){
					g[i].setCarta2(mazzo[primaCarta]);
					primaCarta--;
				}	
			}
			i=(i+1)%nGiocatori;
		}while( i != (posD+1)%nGiocatori);

		puntata=piccoloBuio*2;
		puntataTotale=0;
	}//daiCarte
	
	public void flop(ObjectOutputStream [] oos, int[] disconnessi){
		primaCarta--;
		for(int i=0;i<3;i++, primaCarta--)
			carteComuni[i]=mazzo[primaCarta];
		Comando c=new Comando(Tipo.DAI_CARTA, Arrays.copyOf(carteComuni, 3) );
		for(int i=0; i<oos.length; i++){
			if(disconnessi[i+1]!=2)
			try {
				oos[i].writeObject(c);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		puntataTotale=puntataTotale+puntata;
		puntata=0;
	}//flop
	
	public void turn(ObjectOutputStream [] oos, int[] disconnessi){
		primaCarta--;
		carteComuni[3]=mazzo[primaCarta];
		Comando c=new Comando(Tipo.DAI_CARTA, carteComuni[3] );
		for(int i=0; i<oos.length; i++){
			if(disconnessi[i+1]!=2)
			try {
				oos[i].writeObject(c);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		puntataTotale=puntataTotale+puntata;
		puntata=0;
		
	}//turn
	
	public void river(ObjectOutputStream [] oos, int[] disconnessi){
		primaCarta--;
		carteComuni[4]=mazzo[primaCarta];
		Comando c=new Comando(Tipo.DAI_CARTA, carteComuni[4] );
		for(int i=0; i<oos.length; i++){
			if(disconnessi[i+1]!=2)
			try {
				oos[i].writeObject(c);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		puntataTotale=puntataTotale+puntata;
		puntata=0;
	}//river
	
	public void fold(int gioc){
		if(posPiccoloBuio()==gioc&&piatto[gioc]==0){
			g[gioc].setFiches(g[gioc].getFiches()-piccoloBuio);
			piatto[gioc]+=piccoloBuio;
		}
		if(gioc==posGrandeBuio()&&piatto[gioc]==0){
			g[gioc].setFiches(g[gioc].getFiches()-piccoloBuio*2);
			piatto[gioc]+=piccoloBuio*2;
		}
		g[gioc].setInGioco(false);
	}//fold
	
	public int posPiccoloBuio() {
		int i=(posD+1)%nGiocatori;
		while(!g[i].getInGioco()&&g[i].getFiches()==0){i=(i+1)%nGiocatori;}
		return i;
	}
	
	public int posGrandeBuio() {
		int i=(posPiccoloBuio()+1)%nGiocatori;
		while(!g[i].getInGioco()&&g[i].getFiches()==0){i=(i+1)%nGiocatori;}
		return i;
	}

	public void checkCall(int gioc){
		int daPuntare=puntata-(piatto[gioc]-puntataTotale);
		if(g[gioc].getFiches()<daPuntare){
			piatto[gioc]+=g[gioc].getFiches();
			g[gioc].setFiches(0);
		}		
		else{
			g[gioc].setFiches(g[gioc].getFiches()-daPuntare);
			piatto[gioc]+=daPuntare;
		}
	}//checkCall
	
	public int getpuntataTotale() {
		return puntataTotale;
	}

	public int[] getPiatto() {
		return piatto;
	}

	public void raise(int gioc, int fiches){
		g[gioc].setFiches(g[gioc].getFiches()-fiches);
		piatto[gioc]+=fiches;
		puntata=fiches;
	}//raise
	
	public int getPuntata(){
		return puntata;
	}

	public int getGrandeBuio() {
		return piccoloBuio*2;
	}
	
	private class ComparatorPiatto<T> implements java.util.Comparator<T>{

		@Override
		public int compare(T o1, T o2) {
			if(!(o1 instanceof Giocatore)||!(o1 instanceof Giocatore)){
				throw new ClassCastException();
			}
			Giocatore g1=(Giocatore)o1;
			Giocatore g2=(Giocatore)o2;
			return piatto[g1.getIndice()]-piatto[g2.getIndice()];
		}
		
	}//ComparatorPiatto

	public void fineMano(ObjectOutputStream[] oOS,GraficaPoker gp) {
		LinkedList<Giocatore []> vincitori=vincitoreMano();
		Comando vin=null;
		int quota=0;
		int vincita=0;
		int valPiatto=0;
		int piattoTmp[]=Arrays.copyOf(piatto, piatto.length);
		Giocatore[] vinc;
		valPiatto=getValPiatto();
		while(valPiatto>0){
			vinc=vincitori.remove();
			Arrays.sort(vinc, new ComparatorPiatto<Giocatore>());
			vincite=new int[g.length];
			for(int i=0;i<vinc.length;i++){
				vincita=0;
				quota=(piatto[vinc[i].getIndice()]/(vinc.length-i));
				for (int j = 0; j < g.length; j++) {
					if(piattoTmp[j]/vinc.length<quota){
						vincita+=piattoTmp[j]/(vinc.length-i);
						piattoTmp[j]-=piattoTmp[j]/(vinc.length-i);
					}
					else{
						vincita+=quota;
						piattoTmp[j]-=quota;
					}
				}
				valPiatto-=vincita;
				vincite[vinc[i].getIndice()]=vincita;
				vin=new Comando(null,vinc[i].getFiches()+vincita);
				gp.getGiocatore(vinc[i].getIndice()).setFiches(vinc[i].getFiches()+vincita);
				getG()[vinc[i].getIndice()].setFiches(vinc[i].getFiches()+vincita);
				if(vinc[i].getIndice()!=0){
					try {
						oOS[vinc[i].getIndice()-1].writeObject(vin);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				piatto[vinc[i].getIndice()]=0;
			}
			for (int i = 0; i < piattoTmp.length; i++) {
				piatto[i]=piattoTmp[i];
			}
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
			rimanenti[i]=g[i].getFiches()!=0;
		}
		return rimanenti;
		
	}//getRimanenti

	public int getValPiatto() {
		int val=0;
		for (int i = 0; i < piatto.length; i++) {
			val+=piatto[i];
		}
		return val;
	}//getValPiatto

	public void eliminati() {
		for(int i=0;i<g.length;i++)
			if(g[i].getFiches()==0)
				g[i].setInGioco(false);		
	}//eliminati

	public int[] getVincite() {
		return vincite;
	}//getVincite

}//Dealer
