package progettoPoker;

import java.util.Arrays;
import java.util.Collections;
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
	char seme='n';
	int [] carteUtili=new int[5];
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
		boolean maggiore=false;
		int cont=0;
		int c=0;
		int q=0;
		int f=0;
		int p=0;
		for(int i=0;i<7;i++){
			switch(this.c[i].getPalo()){
			case'c':c++;break;
			case'q':q++;break;
			case'f':f++;break;
			case'p':p++; 
			}
		}
		if(c>=5)seme='c';
		if(q>=5)seme='q';
		if(f>=5)seme='f';
		if(p>=5)seme='p';
		carteUtili=new int [5];
		if(this.c[5].getPalo()==seme)carteUtili[0]=this.c[5].getVal();
		if(this.c[6].getPalo()==seme&&this.c[6].getVal()>carteUtili[0])carteUtili[0]=this.c[6].getVal();
		if(carteUtili[0]!=0){
		for(int i=0; i<5; i++){
			if(this.c[i].getPalo()==seme)
				cont++;
			if(carteUtili[0]>this.c[i].getVal())
				maggiore=true;
		}
		if(cont==5 && !maggiore)
			carteUtili[0]=0;
		}
		if(seme!='n')return true;
		return false;
	}//colore
	
	private int[] copie(){
		carteUtili=new int [5];
		int cont=1;
		int[] cMax=new int[2];
		int[] rip=new int[7];
		Carta [] copy=Arrays.copyOf(c,c.length);
		Arrays.sort(copy,Collections.reverseOrder());
		int index=0;
		for(int i=0; i<copy.length-1; i++){
			if(copy[i+1].getVal()==copy[i].getVal())cont++;
			else{
				rip[index]=cont;
				cont=1;
				index++;
			}
		}rip[index]=cont;
		for(int i=0; i<rip.length; i++){
			if(rip[i]>cMax[0]){
				cMax[1]=cMax[0];
				cMax[0]=rip[i];
			}else
				if(rip[i]>cMax[1]){
					cMax[1]=rip[i];
				}
		}
		if(cMax[0]+cMax[1]>5)cMax[1]=5-cMax[0];
		
		boolean flag=false, flag2 = false;
		int somma=0;
		int indice=-1;
		for(int i=0; i<rip.length; i++){
			if(rip[i]==cMax[0]&&!flag){
				flag=true;
				for (int j = 0; j < cMax[0]; j++) {
					carteUtili[j]=copy[somma].getVal();
				}if(indice!=-1)break;
			}else{
				if(rip[i]>=cMax[1]&&!flag2){
					flag2=true;
					indice=somma;
					if(flag)break;
				}
			}
			somma+=rip[i];
		}
		for (int i = cMax[0]; i < cMax[0]+cMax[1]; i++) {
			carteUtili[i]=copy[indice].getVal();
		}
		if(cMax[0]+cMax[1]<5){
			int elim=copy[indice].getVal();
			for (int i = 0; i < copy.length; i++) {
				if(copy[i].getVal()==carteUtili[0]||copy[i].getVal()==elim)
					copy[i]=null;
			}
			for (int i = cMax[0]+cMax[1]; i < carteUtili.length; i++) {
				for (int j = 0; j < copy.length; j++) {
					if(copy[j]!=null){
						carteUtili[i]=copy[j].getVal();
						copy[j]=null;
						break;
					}
				}
			}
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
		Arrays.sort(copia,0,7);
		if(copia[0]==1) copia[7]=14;
		for(int i=0; i<copia.length-1; i++){
			if(copia[i+1]-copia[i] != 0){
				if(copia[i+1]-copia[i]==1) cont++;
				else cont=0;
			}
			if(cont>=4){
				carteUtili[0] = copia[i+1];
				scala = true;
			}
		}
		return scala;	
	}//scala
		
	private int calcolaVal() {
		boolean scala=scala();
		boolean colore=colore();
		int copie[]=copie();
		if(scala&&colore&&scalaReale())return val=SCALA_REALE;
		if(copie[0]==4)return val=POKER;
		if(copie[0]==3&&copie[1]==2)return val=FULL; 
		if(colore)return val=COLORE;
		if(scala)return val=SCALA;
		if(copie[0]==3)return val=TRIS;
		if(copie[0]==2&&copie()[1]==2)return val=DOPPIA_COPPIA;
		if(copie[0]==2)return val=COPPIA;
		return val=CARTA_ALTA;		
	}//calcolaVal
	
	private boolean scalaReale() {
		int [] copia = new int [8];
		int cont=0;
		for(int i=0; i<copia.length; i++)
			copia[i]=0;		
		for(int i=0; i<c.length; i++){
			if(seme==c[i].getPalo())
				copia[i]=c[i].getVal();
		}
		Arrays.sort(copia,0,7);
		if(copia[0]==1 || copia[1]==1 || copia[2]==1) copia[7]=14;
		
		for(int i=0; i<copia.length-1; i++){
			if(copia[i]==0) continue;
			if(copia[i+1]-copia[i]==1) cont++;
				else cont=0;
			if(cont>=4)
				return true;
		}
		
		return false;			
	}//
	
	
	public static LinkedList<Mano>  ManiMigliori(HashMap<Mano,Giocatore> mani){
		Set<Mano> man=mani.keySet();
		int tmp[]=new int[5];
		//Mano Migliore=null;
		for(Mano m:man){
			for(int i=0;i<m.carteUtili.length;i++){
				if(m.carteUtili[i]>tmp[i]){
					tmp=m.carteUtili;
					break;
				}
				if(m.carteUtili[i]<tmp[i]){
					if(m.carteUtili[i]==1){
						tmp=m.carteUtili;
					}
					break;
				}
			}
			/*if(m.carteUtili[0] == 1) m.carteUtili[0] = 14;
			if(m.carteUtili[0]>=tmp){//TODO rendere cartamax1 e cartaMax2 Carte
				tmp=m.carteUtili[0];		
				//Migliore=m;
				if(m.carteUtili[1]>tmp2){
					tmp2=m.carteUtili[1];	
				}
			}*/
			
		}
		LinkedList<Mano> maniPerdenti=new LinkedList<Mano>();
		for(Mano m:man){
			for(int i=0;i<m.carteUtili.length;i++){
				if(m.carteUtili[i]<tmp[i])maniPerdenti.add(m);
			}
			
		}
		for(Mano m:maniPerdenti){
			mani.remove(m);
		}
		return maniPerdenti;
	}

	public String getValString() {
		int v=getVal();
		switch(v){
		case 1:return "carta alta";
		case 2:return "coppia";
		case 3:return "doppia coppia";
		case 4:return "tris";
		case 5:return "scala";
		case 6:return "colore";
		case 7:return "full";
		case 8:return "scala reale";
		}
		return null;
	}
		
}//Mano
