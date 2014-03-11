package progettoPoker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class Mano {
	final int SCALA_REALE=9;
	final int POKER=8;
	final int FULL=7;
	final int COLORE=6;
	final int SCALA=5;
	final int TRIS=4;
	final int DOPPIA_COPPIA=3;
	final int COPPIA=2;
	final int CARTA_ALTA=1;
	int cartaMax1=0;
	int cartaMax2=0;
	Carta[] c=new Carta[7];
	int val=0;
	
	Mano(Carta[]c,Carta c1,Carta c2){
		this.c=Arrays.copyOf(c,this.c.length);
		this.c[5]=new Carta(c1);
		this.c[6]=new Carta(c2);
	}
	
	public int getVal(){
		if(val!=0)
		return val;
		return calcolaVal();
	}//getVal
	
	private boolean colore(){
		int c=0;
		int q=0;
		int f=0;
		int p=0;
		char seme='n';
		for(int i=0;i<7;i++){
			switch(this.c[i].getPalo()){
			case'c':c++;
			case'q':q++;
			case'f':f++;
			case'p':p++; 
			}
		}
		if(c>=5)seme='c';
		if(q>=5)seme='q';
		if(f>=5)seme='f';
		if(p>=5)seme='p';
		if(this.c[5].getPalo()==seme)cartaMax1=this.c[5].getVal();
		if(this.c[6].getPalo()==seme&&this.c[6].getVal()>cartaMax1)cartaMax1=this.c[6].getVal();
		if(seme!='n')return true;
		return false;
	}//colore
	
	private int[] copie(){//ho modificato il metodo in modo che ritorni le prime due ripetizioni
		int cont=1;
		int[] cMax={1,1};
		for(int i=0; i<c.length; i++){
			for(int j=i+1; j<c.length; j++){
				if(c[i].getVal()==c[j].getVal())
					cont++;					
			}
			if(cont>cMax[0]){
				cMax[1]=cMax[0];
				cMax[0]=cont;
				cartaMax1=cont;
			}else
				if(cont>cMax[1]){
					cMax[1]=cont;
					cartaMax2=cont;
				}
			cont=1;
			
		}
		return cMax;
	}//copie
	
	private boolean scala(){
		int[] copia = new int [8];
		boolean scala = false;
		int cont=0;
		for(int i=0; i<c.length; i++){
			copia[i]=c[i].getVal();
		}
		Arrays.sort(copia);
		if(copia[0]==1) copia[7]=14;
		for(int i=0; i<copia.length-1; i++){
			if(copia[i+1] != 0)
			if(copia[i]-copia[i+1]==-1) cont++;
			else cont=0;
			if(cont>=4){
				cartaMax1 = copia[i];
				scala = true;
			}
		}
		return scala;	
	}//scala
		
	private int calcolaVal() {
		if(scala()&&colore())return val=SCALA_REALE;
		if(copie()[0]==4)return val=POKER;
		if(copie()[0]==3&&copie()[1]==2)return val=FULL; 
		if(colore())return val=COLORE;
		if(scala())return val=SCALA;
		if(copie()[0]==3)return val=TRIS;
		if(copie()[0]==2&&copie()[1]==2)return val=DOPPIA_COPPIA;
		if(copie()[0]==2)return val=COPPIA;
		return val=CARTA_ALTA;		
	}//calcolaVal
	
	public static void ManiMigliori(HashMap<Mano,Giocatore> mani){
		Set<Mano> man=mani.keySet();
		int tmp = 0;
		int tmp2=0;
		//Mano Migliore=null;
		for(Mano m:man){
			if(m.cartaMax1 == 1) m.cartaMax1 = 14;
			if(m.cartaMax1>tmp){//TODO rendere cartamax1 e cartaMax2 Carte
				tmp=m.cartaMax1;		
				//Migliore=m;
			}
			if(m.cartaMax2!=0){
				if(m.cartaMax2>tmp){
					tmp=m.cartaMax2;	
				}
			}
		}
		LinkedList<Mano> maniPerdenti=new LinkedList<Mano>();
		for(Mano m:man){
			if(m.cartaMax1<tmp||m.cartaMax2<tmp2)maniPerdenti.add(m);
		}
		for(Mano m:maniPerdenti){
			mani.remove(m);
		}
	}
		
}//Mano
