package grafica;

import javax.swing.JLabel;

public class Fiches {
	private int quanto;
	private JLabel chip1 = new JLabel(), chip2 = new JLabel(),
			chip3 = new JLabel(), chip4 = new JLabel(), chip5 = new JLabel(), chip6=new JLabel();

	
	/**
	 * viene creato un solo oggetto con pi� JLabel come se fosse un array di
	 * fiches. Creo la fiches inserendo il valore e quindi per ognugno setta le
	 * icone
	 */
	public Fiches(int quanto, int numGioc, GraficaPoker gp) {
		this.quanto = quanto;
		switch (numGioc) {

		case 1:
			chip1.setBounds(495, 505, 100, 100);
			chip2.setBounds(525, 505, 100, 100);
			chip3.setBounds(555, 505, 100, 100);
			chip4.setBounds(585, 505, 100, 100);
			chip5.setBounds(615, 505, 100, 100);
			chip6.setBounds(645,505,100,100);   
			break;
		case 2:
			chip1.setBounds(820, 445, 100, 100);
			chip2.setBounds(850, 445, 100, 100);
			chip3.setBounds(880, 445, 100, 100);
			chip4.setBounds(910, 445, 100, 100);
			chip5.setBounds(940, 445, 100, 100);
			chip6.setBounds(970,445,100,100);   
			break;
		case 3:
			chip1.setBounds(940, 270, 100, 100);
			chip2.setBounds(970, 270, 100, 100);
			chip3.setBounds(1000, 270, 100, 100);
			chip4.setBounds(1030, 270, 100, 100);
			chip5.setBounds(1060, 270, 100, 100);
			chip6.setBounds(1090,270,100,100);   
			break;
		case 4:
			chip1.setBounds(820, 100, 100, 100);
			chip2.setBounds(850, 100, 100, 100);
			chip3.setBounds(880, 100, 100, 100);
			chip4.setBounds(910, 100, 100, 100);
			chip5.setBounds(940, 100, 100, 100);
			chip6.setBounds(970,100,100,100);   
			break;
		case 5:
			chip1.setBounds(610, 45, 100, 100);
			chip2.setBounds(580, 45, 100, 100);
			chip3.setBounds(550, 45, 100, 100);
			chip4.setBounds(520, 45, 100, 100);
			chip5.setBounds(490, 45, 100, 100);
			chip6.setBounds(460,45,100,100);   
			break;
		case 6:
			chip1.setBounds(290, 100, 100, 100);
			chip2.setBounds(260, 100, 100, 100);
			chip3.setBounds(230, 100, 100, 100);
			chip4.setBounds(200, 100, 100, 100);
			chip5.setBounds(170, 100, 100, 100);
			chip6.setBounds(140,100,100,100);   
			break;
		case 7:
			chip1.setBounds(200, 270, 100, 100);
			chip2.setBounds(170, 270, 100, 100);
			chip3.setBounds(140, 270, 100, 100);
			chip4.setBounds(110, 270, 100, 100);
			chip5.setBounds(80, 270, 100, 100);
			chip6.setBounds(50,270,100,100);   
			break;
		case 8:
			chip1.setBounds(170, 445, 100, 100);
			chip2.setBounds(200, 445, 100, 100);
			chip3.setBounds(230, 445, 100, 100);
			chip4.setBounds(260, 445, 100, 100);
			chip5.setBounds(290, 445, 100, 100);
			chip6.setBounds(320,445,100,100);   
			break;

		}// switch 1

		switch (quanto % 10) {
		case 1: {
			chip1.setIcon(Icone.uno1);
			break;
		}
		case 2: {
			chip1.setIcon(Icone.uno2);
			break;
		}
		case 3: {
			chip1.setIcon(Icone.uno3);
			break;
		}
		case 4: {
			chip1.setIcon(Icone.uno4);
			break;
		}
		case 5: {
			chip1.setIcon(Icone.uno5);
			break;
		}
		case 6: {
			chip1.setIcon(Icone.uno6);
			break;
		}
		case 7: {
			chip1.setIcon(Icone.uno7);
			break;
		}
		case 8: {
			chip1.setIcon(Icone.uno8);
			break;
		}
		case 9: {
			chip1.setIcon(Icone.uno9);
			break;
		}
		}// switch quanto%10

		switch (quanto % 100 / 10) {
		case 1: {
			chip2.setIcon(Icone.dieci1);
			break;
		}
		case 2: {
			chip2.setIcon(Icone.dieci2);
			break;
		}
		case 3: {
			chip2.setIcon(Icone.dieci3);
			break;
		}
		case 4: {
			chip2.setIcon(Icone.dieci4);
			break;
		}
		case 5: {
			chip2.setIcon(Icone.dieci5);
			break;
		}
		case 6: {
			chip2.setIcon(Icone.dieci6);
			break;
		}
		case 7: {
			chip2.setIcon(Icone.dieci7);
			break;
		}
		case 8: {
			chip2.setIcon(Icone.dieci8);
			break;
		}
		case 9: {
			chip2.setIcon(Icone.dieci9);
			break;
		}
		}// switch quanto%100/10

		switch (quanto % 1000 / 100) {
		case 1: {
			chip3.setIcon(Icone.cento1);
			break;
		}
		case 2: {
			chip3.setIcon(Icone.cento2);
			break;
		}
		case 3: {
			chip3.setIcon(Icone.cento3);
			break;
		}
		case 4: {
			chip3.setIcon(Icone.cento4);
			break;
		}
		case 5: {
			chip3.setIcon(Icone.cento5);
			break;
		}
		case 6: {
			chip3.setIcon(Icone.cento6);
			break;
		}
		case 7: {
			chip3.setIcon(Icone.cento7);
			break;
		}
		case 8: {
			chip3.setIcon(Icone.cento8);
			break;
		}
		case 9: {
			chip3.setIcon(Icone.cento9);
			break;
		}
		}// switch quanto%1000/100

		switch (quanto % 10000 / 1000) {
		case 1: {
			chip4.setIcon(Icone.mille1);
			break;
		}
		case 2: {
			chip4.setIcon(Icone.mille2);
			break;
		}
		case 3: {
			chip4.setIcon(Icone.mille3);
			break;
		}
		case 4: {
			chip4.setIcon(Icone.mille4);
			break;
		}
		case 5: {
			chip4.setIcon(Icone.mille5);
			break;
		}
		case 6: {
			chip4.setIcon(Icone.mille6);
			break;
		}
		case 7: {
			chip4.setIcon(Icone.mille7);
			break;
		}
		case 8: {
			chip4.setIcon(Icone.mille8);
			break;
		}
		case 9: {
			chip4.setIcon(Icone.mille9);
			break;
		}
		}// switch quanto%10000/1000

		switch (quanto % 100000 / 10000) {
		case 1: {
			chip5.setIcon(Icone.diecimila1);
			break;
		}
		case 2: {
			chip5.setIcon(Icone.diecimila2);
			break;
		}
		case 3: {
			chip5.setIcon(Icone.diecimila3);
			break;
		}
		case 4: {
			chip5.setIcon(Icone.diecimila4);
			break;
		}
		case 5: {
			chip5.setIcon(Icone.diecimila5);
			break;
		}
		case 6: {
			chip5.setIcon(Icone.diecimila6);
			break;
		}
		case 7: {
			chip5.setIcon(Icone.diecimila7);
			break;
		}
		case 8: {
			chip5.setIcon(Icone.diecimila8);
			break;
		}
		case 9: {
			chip5.setIcon(Icone.diecimila9);
			break;
		}
		}// switch quanto%100000/10000

		gp.aggiungiComp(chip1);
		gp.aggiungiComp(chip2);
		gp.aggiungiComp(chip3);
		gp.aggiungiComp(chip4);
		gp.aggiungiComp(chip5);
		gp.aggiungiComp(chip6);

	}// Fiches costruttore

	public int getQuanto() {
		return this.quanto;
	}// getQuanto

	public JLabel getSiChip1() { // fornisce la singola chip
		return chip1;
	}// getSiChip1

	public JLabel getSiChip2() {
		return chip2;
	}

	public JLabel getSiChip3() {
		return chip3;
	}

	public JLabel getSiChip4() {
		return chip4;
	}

	public JLabel getSiChip5() {
		return chip5;
	}
	
	public JLabel getSiChip6(){
		return chip6;
	}

	/**
	 * 
	 * @param numGioc
	 *            = numero del giocatore che deve fare la puntata
	 * @param quanto
	 *            = quanto deve puntare
	 * 
	 *            crea una nuova fiches di quanto e la sposta in posizione di
	 *            puntata per ogni giocatore, il giocatore1 punter� le fiches in
	 *            posizione (495,350) ecc.
	 */
	public static Fiches punta(int numGioc, int quanto, GraficaPoker gp,
			Fiches g) {
		if (g == null) {
			g = new Fiches(quanto, numGioc, gp);
			muovi(numGioc, g);
		} else {
			Fiches f = new Fiches(quanto, numGioc, gp);
			muovi(numGioc, f);
			
			reset(g, gp);
			reset(f, gp);
			g = new Fiches(quanto + g.getQuanto(), numGioc, gp);
			setPosition(numGioc, g, gp);
		}
		return g;
	}// punta

	/**
	 * Posizione sul tavolo delle fiches
	 * @param numGioc
	 * @param g
	 * @param gp
	 */
	public static void setPosition(int numGioc, Fiches g, GraficaPoker gp) {
		switch (numGioc) {
		case 1: {
			g.getSiChip1().setBounds(495, 350, 100, 100);
			g.getSiChip2().setBounds(525, 350, 100, 100);
			g.getSiChip3().setBounds(555, 350, 100, 100);
			g.getSiChip4().setBounds(585, 350, 100, 100);
			g.getSiChip5().setBounds(615, 350, 100, 100);
			g.getSiChip6().setBounds(645, 350, 100, 100);
			break;
		}// case1
		case 2: {
			g.getSiChip1().setBounds(690, 340, 100, 100);
			g.getSiChip2().setBounds(720, 340, 100, 100);
			g.getSiChip3().setBounds(750, 340, 100, 100);
			g.getSiChip4().setBounds(780, 340, 100, 100);
			g.getSiChip5().setBounds(810, 340, 100, 100);
			g.getSiChip6().setBounds(840, 340, 100, 100);
			break;
		}// case 2
		case 3: {
			g.getSiChip1().setBounds(855, 230, 100, 100);
			g.getSiChip2().setBounds(855, 250, 100, 100);
			g.getSiChip3().setBounds(855, 270, 100, 100);
			g.getSiChip4().setBounds(855, 290, 100, 100);
			g.getSiChip5().setBounds(855, 310, 100, 100);
			g.getSiChip6().setBounds(855, 330, 100, 100);
			break;
		}// case 3
		case 4: {
			g.getSiChip1().setBounds(670, 160, 100, 100);
			g.getSiChip2().setBounds(700, 160, 100, 100);
			g.getSiChip3().setBounds(730, 160, 100, 100);
			g.getSiChip4().setBounds(760, 160, 100, 100);
			g.getSiChip5().setBounds(790, 160, 100, 100);
			g.getSiChip6().setBounds(820, 160, 100, 100);
			break;
		}// case 4
		case 5: {
			g.getSiChip1().setBounds(495, 140, 100, 100);
			g.getSiChip2().setBounds(525, 140, 100, 100);
			g.getSiChip3().setBounds(555, 140, 100, 100);
			g.getSiChip4().setBounds(585, 140, 100, 100);
			g.getSiChip5().setBounds(615, 140, 100, 100);
			g.getSiChip6().setBounds(645, 140, 100, 100);
			break;
		}// case 5
		case 6: {
			g.getSiChip1().setBounds(325, 160, 100, 100);
			g.getSiChip2().setBounds(355, 160, 100, 100);
			g.getSiChip3().setBounds(385, 160, 100, 100);
			g.getSiChip4().setBounds(415, 160, 100, 100);
			g.getSiChip5().setBounds(445, 160, 100, 100);
			g.getSiChip6().setBounds(475, 160, 100, 100);
			break;
		}// case 6
		case 7: {
			g.getSiChip1().setBounds(260, 230, 100, 100);
			g.getSiChip2().setBounds(260, 250, 100, 100);
			g.getSiChip3().setBounds(260, 270, 100, 100);
			g.getSiChip4().setBounds(260, 290, 100, 100);
			g.getSiChip5().setBounds(260, 310, 100, 100);
			g.getSiChip6().setBounds(260, 330, 100, 100);
			break;
		}// case 4
		default: {
			g.getSiChip1().setBounds(325, 355, 100, 100);
			g.getSiChip2().setBounds(355, 355, 100, 100);
			g.getSiChip3().setBounds(385, 355, 100, 100);
			g.getSiChip4().setBounds(415, 355, 100, 100);
			g.getSiChip5().setBounds(445, 355, 100, 100);
			g.getSiChip6().setBounds(475, 355, 100, 100);
			break;
		}// default
		}// switch

	}//setPosition

	protected static void reset(Fiches g, GraficaPoker gp) {
		gp.rimuoviComp(g.getSiChip1());
		gp.rimuoviComp(g.getSiChip2());
		gp.rimuoviComp(g.getSiChip3());
		gp.rimuoviComp(g.getSiChip4());
		gp.rimuoviComp(g.getSiChip5());
		gp.rimuoviComp(g.getSiChip6());
	}//reset

	private static void muovi(int numGioc, Fiches g) {
		switch (numGioc) {
		case 1: {
			Movimento.muovi(g.getSiChip1(), 495, 350);
			Movimento.muovi(g.getSiChip2(), 525, 350);
			Movimento.muovi(g.getSiChip3(), 555, 350);
			Movimento.muovi(g.getSiChip4(), 585, 350);
			Movimento.muovi(g.getSiChip5(), 615, 350);
			Movimento.muovi(g.getSiChip6(), 645, 350);
			break;
		}// case1
		case 2: {
			Movimento.muovi(g.getSiChip1(), 690, 340);
			Movimento.muovi(g.getSiChip2(), 720, 340);
			Movimento.muovi(g.getSiChip3(), 750, 340);
			Movimento.muovi(g.getSiChip4(), 780, 340);
			Movimento.muovi(g.getSiChip5(), 810, 340);
			Movimento.muovi(g.getSiChip6(), 840, 340);
			break;
		}// case 2
		case 3: {
			Movimento.muovi(g.getSiChip1(), 855, 230);
			Movimento.muovi(g.getSiChip2(), 855, 250);
			Movimento.muovi(g.getSiChip3(), 855, 270);
			Movimento.muovi(g.getSiChip4(), 855, 290);
			Movimento.muovi(g.getSiChip5(), 855, 310);
			Movimento.muovi(g.getSiChip6(), 855, 330);
			break;
		}// case 3
		case 4: {
			Movimento.muovi(g.getSiChip1(), 670, 160);
			Movimento.muovi(g.getSiChip2(), 700, 160);
			Movimento.muovi(g.getSiChip3(), 730, 160);
			Movimento.muovi(g.getSiChip4(), 760, 160);
			Movimento.muovi(g.getSiChip5(), 790, 160);
			Movimento.muovi(g.getSiChip6(), 820, 160);
			break;
		}// case 4
		case 5: {
			Movimento.muovi(g.getSiChip1(), 495, 140);
			Movimento.muovi(g.getSiChip2(), 525, 140);
			Movimento.muovi(g.getSiChip3(), 555, 140);
			Movimento.muovi(g.getSiChip4(), 585, 140);
			Movimento.muovi(g.getSiChip5(), 615, 140);
			Movimento.muovi(g.getSiChip6(), 645, 140);
			break;
		}// case 5
		case 6: {
			Movimento.muovi(g.getSiChip1(), 325, 160);
			Movimento.muovi(g.getSiChip2(), 355, 160);
			Movimento.muovi(g.getSiChip3(), 385, 160);
			Movimento.muovi(g.getSiChip4(), 415, 160);
			Movimento.muovi(g.getSiChip5(), 445, 160);
			Movimento.muovi(g.getSiChip6(), 475, 160);
			break;
		}// case 6
		case 7: {
			Movimento.muovi(g.getSiChip1(), 260, 230);
			Movimento.muovi(g.getSiChip2(), 260, 250);
			Movimento.muovi(g.getSiChip3(), 260, 270);
			Movimento.muovi(g.getSiChip4(), 260, 290);
			Movimento.muovi(g.getSiChip5(), 260, 310);
			Movimento.muovi(g.getSiChip6(), 260, 330);
			break;
		}// case 4
		default: {
			Movimento.muovi(g.getSiChip1(), 325, 355);
			Movimento.muovi(g.getSiChip2(), 355, 355);
			Movimento.muovi(g.getSiChip3(), 385, 355);
			Movimento.muovi(g.getSiChip4(), 415, 355);
			Movimento.muovi(g.getSiChip5(), 445, 355);
			Movimento.muovi(g.getSiChip6(), 475, 355);
			break;
		}// default
		}// switch

	}//muovi
}//Fiches
