package grafica;

import javax.swing.JLabel;

public class Fiches {
	private int quanto;
	private JLabel chip,chip2,chip3,chip4,chip5;
	
	public Fiches(int quanto){
		switch(quanto){
		case 1: {this.quanto=quanto;chip.setIcon(Icone.uno1);break;}
		case 2: {this.quanto=quanto;chip.setIcon(Icone.uno2);break;}
		case 3: {this.quanto=quanto;chip.setIcon(Icone.uno3);break;}
		case 4: {this.quanto=quanto;chip.setIcon(Icone.uno4);break;}
		case 5: {this.quanto=quanto;chip.setIcon(Icone.uno5);break;}
		case 6: {this.quanto=quanto;chip.setIcon(Icone.uno6);break;}
		case 7: {this.quanto=quanto;chip.setIcon(Icone.uno7);break;}
		case 8: {this.quanto=quanto;chip.setIcon(Icone.uno8);break;}
		case 9: {this.quanto=quanto;chip.setIcon(Icone.uno9);break;}
		case 10:{this.quanto=quanto;chip2.setIcon(Icone.dieci1);break;}
		case 11:{this.quanto=quanto;chip.setIcon(Icone.uno1);chip2.setIcon(Icone.dieci1);break;}
		case 12:{this.quanto=quanto;chip.setIcon(Icone.uno2);chip2.setIcon(Icone.dieci1);break;}
		case 13:{this.quanto=quanto;chip.setIcon(Icone.uno3);chip2.setIcon(Icone.dieci1);break;}
		case 14:{this.quanto=quanto;chip.setIcon(Icone.uno4);chip2.setIcon(Icone.dieci1);break;}
		case 15:{this.quanto=quanto;chip.setIcon(Icone.uno5);chip2.setIcon(Icone.dieci1);break;}
		case 16:{this.quanto=quanto;chip.setIcon(Icone.uno6);chip2.setIcon(Icone.dieci1);break;}
		case 17:{this.quanto=quanto;chip.setIcon(Icone.uno7);chip2.setIcon(Icone.dieci1);break;}
		case 18:{this.quanto=quanto;chip.setIcon(Icone.uno8);chip2.setIcon(Icone.dieci1);break;}
		case 19:{this.quanto=quanto;chip.setIcon(Icone.uno9);chip2.setIcon(Icone.dieci1);break;}
		case 20:{this.quanto=quanto;chip2.setIcon(Icone.dieci2);break;}
		case 21:{this.quanto=quanto;chip.setIcon(Icone.uno1);chip2.setIcon(Icone.dieci2);break;}
		case 22:{this.quanto=quanto;chip.setIcon(Icone.uno2);chip2.setIcon(Icone.dieci2);break;}
		}//switch
		
	}//Fiches
	public void setQuanto(int quanto){
		Fiches nuovaFiches=new Fiches(quanto);
		this.quanto=nuovaFiches.quanto;
		this.chip=nuovaFiches.chip;
		this.chip2=nuovaFiches.chip2;
		this.chip3=nuovaFiches.chip3;
		this.chip4=nuovaFiches.chip4;
		this.chip5=nuovaFiches.chip5;
	}//setQuanto
	
	

}
