package progettoPoker;

import java.util.Arrays;

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
	Carta[] c=new Carta[7];
	int val=0;
	
	Mano(Carta[]c,Carta c1,Carta c2){
		this.c=Arrays.copyOf(c,c.length);
		this.c[5]=new Carta(c1);
		this.c[6]=new Carta(c2);
	}
	
	public int getVal(){
		if(val!=0)
		return val;
		return calcolaVal();
	}
	
	private boolean colore(){
		int c=0;
		int q=0;
		int f=0;
		int p=0;
		for(int i=0;i<7;i++){
			switch(this.c[i].getPalo()){
			case'c':c++;
			case'q':q++;
			case'f':f++;
			case'p':p++; 
			}
		}
		if(c>=5||q>=5||f>=5||p>=5)return true;
		return false;
	}
	
	private int[] copie(){//ho modificato il metodo in modo che ritorni le prime due ripetizioni
		int cont=1;
		int[] cMax={1,1};
		for(int i=0; i<c.length; i++){
			for(int j=i+1; i<c.length; j++){
				if(c[i].getVal()==c[j].getVal())
					cont++;					
			}
			if(cont>cMax[1]){
				cMax[2]=cMax[1];
				cMax[1]=cont;
			}else
				if(cont>cMax[2]){
					cMax[2]=cont;
				}
			cont=1;
		}
		return cMax;
	}
	
	private int calcolaVal() {
		// TODO Auto-generated method stub
		return 0;
	}
}

