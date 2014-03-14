package grafica;

import javax.swing.JLabel;

public class Fiches {
	private int quanto;
	private JLabel chip,chip2,chip3,chip4,chip5;
	
	
	/**
	 * viene creato un solo oggetto con più JLabel
	 * come se fosse un array di fiches. Creo la fiches
	 * inserendo il valore e quindi per ognugno setta le icone 
	 */
	public Fiches(int quanto){
		this.quanto=quanto;
		switch(quanto%10){
		case 1: {chip.setIcon(Icone.uno1);break;}
		case 2: {chip.setIcon(Icone.uno2);break;}
		case 3: {chip.setIcon(Icone.uno3);break;}
		case 4: {chip.setIcon(Icone.uno4);break;}
		case 5: {chip.setIcon(Icone.uno5);break;}
		case 6: {chip.setIcon(Icone.uno6);break;}
		case 7: {chip.setIcon(Icone.uno7);break;}
		case 8: {chip.setIcon(Icone.uno8);break;}
		case 9: {chip.setIcon(Icone.uno9);break;}
		}//switch quanto%10
		
		switch(quanto%100/10){
		case 1: {chip2.setIcon(Icone.dieci1);break;}
		case 2: {chip2.setIcon(Icone.dieci2);break;}
		case 3: {chip2.setIcon(Icone.dieci3);break;}
		case 4: {chip2.setIcon(Icone.dieci4);break;}
		case 5: {chip2.setIcon(Icone.dieci5);break;}
		case 6: {chip2.setIcon(Icone.dieci6);break;}
		case 7: {chip2.setIcon(Icone.dieci7);break;}
		case 8: {chip2.setIcon(Icone.dieci8);break;}
		case 9: {chip2.setIcon(Icone.dieci9);break;}
		}//switch quanto%100/10
		
		switch(quanto%1000/100){
		case 1: {chip3.setIcon(Icone.cento1);break;}
		case 2: {chip3.setIcon(Icone.cento2);break;}
		case 3: {chip3.setIcon(Icone.cento3);break;}
		case 4: {chip3.setIcon(Icone.cento4);break;}
		case 5: {chip3.setIcon(Icone.cento5);break;}
		case 6: {chip3.setIcon(Icone.cento6);break;}
		case 7: {chip3.setIcon(Icone.cento7);break;}
		case 8: {chip3.setIcon(Icone.cento8);break;}
		case 9: {chip3.setIcon(Icone.cento9);break;}
		}//switch quanto%1000/100
		
		switch(quanto%10000/1000){
		case 1: {chip4.setIcon(Icone.mille1);break;}
		case 2: {chip4.setIcon(Icone.mille2);break;}
		case 3: {chip4.setIcon(Icone.mille3);break;}
		case 4: {chip4.setIcon(Icone.mille4);break;}
		case 5: {chip4.setIcon(Icone.mille5);break;}
		case 6: {chip4.setIcon(Icone.mille6);break;}
		case 7: {chip4.setIcon(Icone.mille7);break;}
		case 8: {chip4.setIcon(Icone.mille8);break;}
		case 9: {chip4.setIcon(Icone.mille9);break;}
		}//switch quanto%10000/1000
		
		switch(quanto%100000/10000){
		case 1: {chip5.setIcon(Icone.diecimila1);break;}
		case 2: {chip5.setIcon(Icone.diecimila2);break;}
		case 3: {chip5.setIcon(Icone.diecimila3);break;}
		case 4: {chip5.setIcon(Icone.diecimila4);break;}
		case 5: {chip5.setIcon(Icone.diecimila5);break;}
		case 6: {chip5.setIcon(Icone.diecimila6);break;}
		case 7: {chip5.setIcon(Icone.diecimila7);break;}
		case 8: {chip5.setIcon(Icone.diecimila8);break;}
		case 9: {chip5.setIcon(Icone.diecimila9);break;}
		}//switch quanto%100000/10000
	}//Fiches
	
	public int getQuanto(){
		return this.quanto;
	}//getQuanto
	
	public JLabel getChip() {
		return chip;
	}

	public JLabel getChip2() {
		return chip2;
	}

	public JLabel getChip3() {
		return chip3;
	}

	public JLabel getChip4() {
		return chip4;
	}

	public JLabel getChip5() {
		return chip5;
	}

	
	

}
