package grafica;

import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.io.File;
//import java.net.*;

import javax.swing.*;

public class GraficaPoker extends JFrame {
	public GiocatoreGrafico Giocatore1=null;
	public GiocatoreGrafico Giocatore2=null;
	GiocatoreGrafico Giocatori[]=new GiocatoreGrafico[8];
	
	static File Immagini = new File("Immagini");
	File Bottoni = new File(Immagini.getAbsolutePath() + "\\Bottoni");
	File Carte = new File(Immagini.getAbsolutePath() + "\\Carte");

	FinestraPunteggio FP = new FinestraPunteggio(); // CREAZIONE FINESTRA PUTNEGGIO
	Container principale = this.getContentPane();
	
	/**
	 * 
	 * 
	 */
	public void Scrivi(String messaggio)
	{
		Chat.append(messaggio);
	}
	public void disableFold()
	{
		Fold.setEnabled(false);
	}
	public void disableRaise()
	{
		Raise.setEnabled(false);
	}
	/**
	 * Chiamato con true disabilita i bottoni.
	 * Chiamato con false abilita i bottoni.
	 */
	public void disableBottoni(boolean flag)
	{
		Fold.setEnabled(!flag);
		Raise.setEnabled(!flag);
		Call.setEnabled(!flag);
		AllIn.setEnabled(!flag);
	}

	// CREAZIONE BOTTONI
	static JButton Fold = new JButton("Fold");
	static JButton Call = new JButton("Call");
	static JButton Raise = new JButton("Raise");
	static JButton AllIn = new JButton("All-In");
	static JButton Invia = new JButton("Invia");
	static JButton SegnaPunti = new JButton("Punti");
	static JButton VisChat=new JButton("Chat");
	JButton Stats=new JButton("Stats");

	// GIOCATORE 1
	JLabel etiUtente1 = new JLabel();
	JLabel nome1 = new JLabel("Giocatore 1");
	JLabel fiches1 = new JLabel("10000");
	JLabel Gioc1Car1 = new JLabel();
	JLabel Gioc1Car2 = new JLabel();

	// GIOCATORE 2
	JLabel etiUtente2 = new JLabel();
	JLabel nome2 = new JLabel("Giocatore 2");
	JLabel fiches2 = new JLabel("10000");
	JLabel Gioc2Car1 = new JLabel();
	JLabel Gioc2Car2 = new JLabel();

	// GIOCATORE 3

	JLabel etiUtente3 = new JLabel();
	JLabel nome3 = new JLabel("Giocatore 3");
	JLabel fiches3 = new JLabel("10000");
	JButton carteUt3 = new JButton("CarteUt3");

	// GIOCATORE 4

	JLabel etiUtente4 = new JLabel();
	JLabel nome4 = new JLabel("Giocatore 4");
	JLabel fiches4 = new JLabel("10000");
	JButton carteUt4 = new JButton("CarteUt4");

	// GIOCATORE 5

	JLabel etiUtente5 = new JLabel();
	JLabel nome5 = new JLabel("Giocatore 5");
	JLabel fiches5 = new JLabel("10000");
	JButton carteUt5 = new JButton("CarteUt5");

	// GIOCATORE 6

	JLabel etiUtente6 = new JLabel();
	JLabel nome6 = new JLabel("Giocatore 6");
	JLabel fiches6 = new JLabel("10000");
	JButton carteUt6 = new JButton("CarteUt6");

	// GIOCATORE 7

	JLabel etiUtente7 = new JLabel();
	JLabel nome7 = new JLabel("Giocatore 7");
	JLabel fiches7 = new JLabel("10000");
	JButton carteUt7 = new JButton("CarteUt7");

	// GIOCATORE 8
	JLabel etiUtente8 = new JLabel();
	JLabel nome8 = new JLabel("Giocatore 8");
	JLabel fiches8 = new JLabel("10000");
	JButton carteUt8 = new JButton("CarteUt8");

	// CREAZIONE CARTE SUL TAVOLO
	JButton mazzo = new JButton("Mazzo");
	JButton cartaT1 = new JButton("Carta1");
	JButton cartaT2 = new JButton("Carta2");
	JButton cartaT3 = new JButton("Carta3"); // carte sul tavolo scoperte
	JButton cartaT4 = new JButton("Carta4");
	JButton cartaT5 = new JButton("Carta5");

	// CREAZIONE CHAT
	JTextArea Chat = new JTextArea();
	JTextField ConsChat = new JTextField();
	JScrollPane ScrollChat = new JScrollPane(Chat);
	
	Listener list = new Listener(Chat, ConsChat); // LISTENER

	// IMMAGINI: CARTE COPERTE
	ImageIcon ut2 = new ImageIcon(Carte.getAbsolutePath() + "\\ut2.png");
	ImageIcon ut5 = new ImageIcon(Carte.getAbsolutePath() + "\\Ut5.png");
	ImageIcon ut7 = new ImageIcon(Carte.getAbsolutePath() + "\\Ut7.png");
	ImageIcon ut8 = new ImageIcon(Carte.getAbsolutePath() + "\\Ut8.png");
	ImageIcon Mazzo = new ImageIcon(Carte.getAbsolutePath() + "\\mazzo.png");

	// IMMAGINI: BOTTONI
	ImageIcon Foldpre = new ImageIcon(Bottoni.getAbsolutePath()
			+ "\\Foldpre.png");
	ImageIcon Foldnot = new ImageIcon(Bottoni.getAbsolutePath() + "\\Fold.png");
	ImageIcon Callpre = new ImageIcon(Bottoni.getAbsolutePath()
			+ "\\Callpre.png");
	ImageIcon Callnot = new ImageIcon(Bottoni.getAbsolutePath() + "\\Call.png");
	ImageIcon Raisepre = new ImageIcon(Bottoni.getAbsolutePath()
			+ "\\Raisepre.png");
	ImageIcon Raisenot = new ImageIcon(Bottoni.getAbsolutePath()
			+ "\\Raise.png");
	ImageIcon Allpre = new ImageIcon(Bottoni.getAbsolutePath() + "\\Allpre.png");
	ImageIcon Allnot = new ImageIcon(Bottoni.getAbsolutePath() + "\\All.png");
	ImageIcon Puntipre = new ImageIcon(Bottoni.getAbsolutePath()
			+ "\\Puntipre.png");
	ImageIcon Puntinot = new ImageIcon(Bottoni.getAbsolutePath()
			+ "\\Punti.png");
	ImageIcon Sendpre = new ImageIcon(Bottoni.getAbsolutePath()
			+ "\\Sendpre.png");
	ImageIcon Sendnot = new ImageIcon(Bottoni.getAbsolutePath() + "\\Send.png");
	
	//BOTTONI PER LA CHAT
	ImageIcon VisualizzaChat=new ImageIcon(Bottoni.getAbsolutePath()+"\\VisChat.png");
	ImageIcon Statistics=new ImageIcon(Bottoni.getAbsolutePath()+"Stats.png");

	
	// IMMAGINI: ETICHETTA
	ImageIcon Etichetta = new ImageIcon(Bottoni.getAbsolutePath()
			+ "\\EtichettaR.png");

	// IMMAGINI: SFONDO

	ImageIcon sfon = new ImageIcon(Bottoni.getAbsolutePath()
			+ "\\tavolo-ovale.jpg");
	JLabel sfondo = new JLabel(sfon);
	
	//IMMAGINI: CARTE COPERTE
	ImageIcon coperta=new ImageIcon(Carte.getAbsolutePath()+"\\copProv.png");
	ImageIcon copertamezza=new ImageIcon(Carte.getAbsolutePath()+"\\copMez.png");

	// IMMAGINI: ICONE CARTE 
	ImageIcon uno = new ImageIcon(Carte.getAbsolutePath() + "\\1.png");// 2 di
																		// cuori
	ImageIcon due = new ImageIcon(Carte.getAbsolutePath() + "\\2.png");// 3 di
																		// cuori
	ImageIcon tre = new ImageIcon(Carte.getAbsolutePath() + "\\3.png");// 4 di
																		// cuori
	ImageIcon quattro = new ImageIcon(Carte.getAbsolutePath() + "\\4.png");// 5
																			// di
																			// cuori
	ImageIcon cinque = new ImageIcon(Carte.getAbsolutePath() + "\\5.png");// 6
																			// di
																			// cuori
	ImageIcon sei = new ImageIcon(Carte.getAbsolutePath() + "\\6.png");// 7 di
																		// cuori
	ImageIcon sette = new ImageIcon(Carte.getAbsolutePath() + "\\7.png");// 8 di
																			// cuori
	ImageIcon otto = new ImageIcon(Carte.getAbsolutePath() + "\\8.png");// 9 di
																		// cuori
	ImageIcon nove = new ImageIcon(Carte.getAbsolutePath() + "\\9.png");// 10 di
																		// cuori
	ImageIcon dieci = new ImageIcon(Carte.getAbsolutePath() + "\\10.png");// J
																			// di
																			// cuori
	ImageIcon undici = new ImageIcon(Carte.getAbsolutePath() + "\\11.png");// Q
																			// di
																			// cuori
	ImageIcon dodici = new ImageIcon(Carte.getAbsolutePath() + "\\12.png");// K
																			// di
																			// cuori
	ImageIcon tredici = new ImageIcon(Carte.getAbsolutePath() + "\\13.png");// Asso
																			// di
																			// cuori
	ImageIcon quattordici = new ImageIcon(Carte.getAbsolutePath() + "\\14.png"); // 2
																					// di
																					// quadri
	ImageIcon quindici = new ImageIcon(Carte.getAbsolutePath() + "\\15.png"); // 3
																				// di
																				// quadri
	ImageIcon sedici = new ImageIcon(Carte.getAbsolutePath() + "\\16.png"); // 4
																			// di
																			// quadri
	ImageIcon diciassette = new ImageIcon(Carte.getAbsolutePath() + "\\17.png");// 5
																				// di
																				// quadri
	ImageIcon diciotto = new ImageIcon(Carte.getAbsolutePath() + "\\18.png");// 6
																				// di
																				// quadri
	ImageIcon diciannove = new ImageIcon(Carte.getAbsolutePath() + "\\19.png");// 7
																				// di
																				// quadri
	ImageIcon venti = new ImageIcon(Carte.getAbsolutePath() + "\\20.png");// 8
																			// di
																			// quadri
	ImageIcon ventuno = new ImageIcon(Carte.getAbsolutePath() + "\\21.png");// 9
																			// di
																			// quadri
	ImageIcon ventidue = new ImageIcon(Carte.getAbsolutePath() + "\\22.png");// 10
																				// di
																				// quadri
	ImageIcon ventitre = new ImageIcon(Carte.getAbsolutePath() + "\\23.png");// J
																				// di
																				// quadri
	ImageIcon ventiquattro = new ImageIcon(Carte.getAbsolutePath() + "\\24.png");// Q
																					// di
																					// quadri
	ImageIcon venticinque = new ImageIcon(Carte.getAbsolutePath() + "\\25.png");// K
																				// di
																				// quadri
	ImageIcon ventisei = new ImageIcon(Carte.getAbsolutePath() + "\\26.png");// Asso
																				// di
																				// quadri
	ImageIcon ventisette = new ImageIcon(Carte.getAbsolutePath() + "\\27.png"); // 2
																				// di
																				// fiori
	ImageIcon ventotto = new ImageIcon(Carte.getAbsolutePath() + "\\28.png"); // 3
																				// di
																				// fiori
	ImageIcon ventinove = new ImageIcon(Carte.getAbsolutePath() + "\\29.png"); // 4
																				// di
																				// fiori
	ImageIcon trenta = new ImageIcon(Carte.getAbsolutePath() + "\\30.png");// 5
																			// di
																			// fiori
	ImageIcon trentuno = new ImageIcon(Carte.getAbsolutePath() + "\\31.png");// 6
																				// di
																				// fiori
	ImageIcon trentadue = new ImageIcon(Carte.getAbsolutePath() + "\\32.png");// 7
																				// di
																				// fiori
	ImageIcon trentatre = new ImageIcon(Carte.getAbsolutePath() + "\\33.png");// 8
																				// di
																				// fiori
	ImageIcon trentaquattro = new ImageIcon(Carte.getAbsolutePath()
			+ "\\34.png");// 9 di fiori
	ImageIcon trentacinque = new ImageIcon(Carte.getAbsolutePath() + "\\35.png");// 10
																					// di
																					// fiori
	ImageIcon trentasei = new ImageIcon(Carte.getAbsolutePath() + "\\36.png");// J
																				// di
																				// fiori
	ImageIcon trentasette = new ImageIcon(Carte.getAbsolutePath() + "\\37.png");// Q
																				// di
																				// fiori
	ImageIcon trentotto = new ImageIcon(Carte.getAbsolutePath() + "\\38.png");// K
																				// di
																				// fiori
	ImageIcon trentanove = new ImageIcon(Carte.getAbsolutePath() + "\\39.png");// Asso
																				// di
																				// fiori
	ImageIcon quaranta = new ImageIcon(Carte.getAbsolutePath() + "\\40.png"); // 2
																				// di
																				// picche
	ImageIcon quarantuno = new ImageIcon(Carte.getAbsolutePath() + "\\41.png"); // 3
																				// di
																				// picche
	ImageIcon quarantadue = new ImageIcon(Carte.getAbsolutePath() + "\\42.png"); // 4
																					// di
																					// picche
	ImageIcon quarantatre = new ImageIcon(Carte.getAbsolutePath() + "\\43.png");// 5
																				// di
																				// picche
	ImageIcon quarantaquattro = new ImageIcon(Carte.getAbsolutePath()
			+ "\\44.png");// 6 di picche
	ImageIcon quarantacinque = new ImageIcon(Carte.getAbsolutePath()
			+ "\\45.png");// 7 di picche
	ImageIcon quarantasei = new ImageIcon(Carte.getAbsolutePath() + "\\46.png");// 8
																				// di
																				// picche
	ImageIcon quarantasette = new ImageIcon(Carte.getAbsolutePath()
			+ "\\47.png");// 9 di picche
	ImageIcon quarantotto = new ImageIcon(Carte.getAbsolutePath() + "\\48.png");// 10
																				// di
																				// picche
	ImageIcon quarantanove = new ImageIcon(Carte.getAbsolutePath() + "\\49.png");// J
																					// di
																					// picche
	ImageIcon cinquanta = new ImageIcon(Carte.getAbsolutePath() + "\\50.png");// Q
																				// di
																				// picche
	ImageIcon cinquantuno = new ImageIcon(Carte.getAbsolutePath() + "\\51.png");// K
																				// di
																				// picche
	ImageIcon cinquantadue = new ImageIcon(Carte.getAbsolutePath() + "\\52.png");// Asso
																					// di
																					// picche

	// IMMAGINI: LOGO FRAME
	ImageIcon logo = new ImageIcon(Bottoni.getAbsolutePath() + "\\logo.jpg");

	// FONT
	Font fontnome = new Font("Comic Sans MS", Font.BOLD, 13);
	Font fontfiches = new Font("Comic Sans MS", Font.PLAIN, 10);
	Font font = new Font("Comic Sans MS", Font.ROMAN_BASELINE, 12);

	public GraficaPoker() {

		// DIMENSIONE E OPZIONI FINESTRA (completo)
		super("Real Poker");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // DA ELIMINARE
		this.setResizable(false);
		this.setBounds(115, 15, 1024, 700);
		this.setSize(1224, 700);
		this.setSize(1224, 701);
		this.setIconImage(logo.getImage().getScaledInstance(350, 300,
				Image.SCALE_SMOOTH));

		// SETTA IL LOGO DELLA FINESTRA PUNTEGGI
		FP.setIconImage(logo.getImage().getScaledInstance(350, 300,
				Image.SCALE_SMOOTH));

		// BOTTONI (FOLD,RAISE,CALL,ALL-IN,INVIA)
		Fold.setIcon(Foldnot);
		Fold.setPressedIcon(Foldpre);
		Fold.setBounds(350, 590, 90, 30);
		Fold.setBorder(null);
		//Fold.addActionListener(l);
		Call.setIcon(Callnot);
		Call.setPressedIcon(Callpre);
		Call.setBounds(450, 590, 90, 30);
		Call.setBorder(null);
		Raise.setIcon(Raisenot);
		Raise.setPressedIcon(Raisepre);
		Raise.setBounds(550, 590, 90, 30);
		Raise.setBorder(null);
		AllIn.setIcon(Allnot);
		AllIn.setPressedIcon(Allpre);
		AllIn.setBounds(650, 590, 90, 30);
		AllIn.setBorder(null);
		SegnaPunti.setIcon(Puntinot);
		SegnaPunti.setPressedIcon(Puntipre);
		SegnaPunti.setBounds(0, 645, 90, 30);
		SegnaPunti.setBorder(null);

		// LE 5 CARTE SUL TAVOLO + MAZZO (completo ma devono essere
		// modificabili)
		cartaT1.setBorder(null);
		cartaT1.setIcon(tredici);
		cartaT1.setBounds(240, 240, 70, 98); // cartaT1.setVisible(false);
		cartaT2.setBorder(null);
		cartaT2.setIcon(ventisei);
		cartaT2.setBounds(320, 240, 70, 98); // cartaT2.setVisible(false);
		cartaT3.setBorder(null);
		cartaT3.setIcon(trentanove);
		cartaT3.setBounds(400, 240, 70, 98); // cartaT3.setVisible(false);
		cartaT4.setBorder(null);
		cartaT4.setIcon(cinquantadue);
		cartaT4.setBounds(480, 240, 70, 98); // cartaT4.setVisible(false);
		cartaT5.setBorder(null);
		cartaT5.setIcon(uno);
		cartaT5.setBounds(560, 240, 70, 98); // cartaT5.setVisible(false);
		mazzo.setBorder(null);
		mazzo.setIcon(Mazzo);
		mazzo.setBounds(650, 240, 70, 100);

		// GIOCATORE 1 (CARTE SUL TAVOLO,ETICHETTA,NOME,FICHES)

		etiUtente1.setIcon(Etichetta);
		etiUtente1.setBounds(415, 500, 280, 55);
		nome1.setFont(fontnome);
		nome1.setBounds(429, 490, 100, 60); // +14,-10
		nome1.setForeground(Color.BLUE);
		fiches1.setFont(fontfiches); // rispetto a Utente1
		fiches1.setBounds(450, 510, 100, 60); // +35,+10
		fiches1.setForeground(Color.BLACK); 
		Gioc1Car1.setIcon(trenta);
		Gioc1Car1.setBounds(400, 400, 100, 100);
		Gioc1Car2.setIcon(quaranta);
		Gioc1Car2.setBounds(480, 400, 100, 100);
		GiocatoreGrafico Giocatore1 = new GiocatoreGrafico(etiUtente1, nome1,fiches1, Gioc1Car1, Gioc1Car2);
		Giocatore1.setNome("Giovanni");
		
		// GIOCATORE 2 (ICONA CARTE,ETICHETTA,NOME,FICHES)
		etiUtente2.setIcon(Etichetta);
		etiUtente2.setBounds(400, 70, 280, 55);
		nome2.setFont(fontnome);
		nome2.setBounds(565, 58, 100, 60);
		nome2.setForeground(Color.BLUE);
		fiches2.setFont(fontfiches);
		fiches2.setBounds(585, 75, 100, 60);
		fiches2.setForeground(Color.BLACK);
		Gioc2Car1.setIcon(copertamezza);
		Gioc2Car1.setBounds(512, -2, 100, 100);
		Gioc2Car2.setIcon(copertamezza);
		Gioc2Car2.setBounds(582, -2, 100, 100);
		GiocatoreGrafico Giocatore2=new GiocatoreGrafico(etiUtente2,nome2,fiches2,Gioc2Car1,Gioc2Car2);
		
		
		// GIOCATORE 3 (ICONA CARTE,ETICHETTA,NOME,FICHES)
		carteUt3.setBorder(null);
		carteUt3.setBounds(200, 155, 90, 80); // sopra a sinistra
		carteUt3.setIcon(ut2);
		etiUtente3.setIcon(Etichetta);
		etiUtente3.setBounds(70, 80, 280, 55);
		nome3.setFont(fontnome);
		nome3.setBounds(84, 70, 100, 60);
		nome3.setForeground(Color.BLUE);
		fiches3.setFont(fontfiches);
		fiches3.setBounds(105, 90, 100, 60);
		fiches3.setForeground(Color.BLACK);

		// GIOCATORE 4 (ICONA CARTE,ETICHETTA,NOME,FICHES)
		carteUt4.setBorder(null);
		carteUt4.setBounds(645, 155, 90, 80); // sopra a destra
		carteUt4.setIcon(ut2);
		etiUtente4.setIcon(Etichetta);
		etiUtente4.setBounds(760, 80, 280, 55);
		nome4.setFont(fontnome);
		nome4.setBounds(774, 70, 100, 60);
		nome4.setForeground(Color.BLUE);
		fiches4.setFont(fontfiches);
		fiches4.setBounds(795, 90, 100, 60);
		fiches4.setForeground(Color.BLACK);

		// GIOCATORE 5 (ICONA CARTE,ETICHETTA,NOME,FICHES)
		carteUt5.setBorder(null);
		carteUt5.setBounds(200, 348, 90, 80); // sotto a sinistra
		carteUt5.setIcon(ut5);
		etiUtente5.setIcon(Etichetta);
		etiUtente5.setBounds(70, 430, 280, 55);
		nome5.setFont(fontnome);
		nome5.setBounds(84, 420, 100, 60);
		nome5.setForeground(Color.BLUE);
		fiches5.setFont(fontfiches);
		fiches5.setBounds(105, 440, 100, 60);
		fiches5.setForeground(Color.BLACK);

		// GIOCATORE 6 (ICONA CARTE,ETICHETTA,NOME,FICHES)
		carteUt6.setBorder(null);
		carteUt6.setBounds(645, 348, 90, 80); // sotto a destra
		carteUt6.setIcon(ut5);
		etiUtente6.setIcon(Etichetta);
		etiUtente6.setBounds(760, 430, 280, 55);
		nome6.setFont(fontnome);
		nome6.setBounds(774, 420, 100, 60);
		nome6.setForeground(Color.BLUE);
		fiches6.setFont(fontfiches);
		fiches6.setBounds(795, 440, 100, 60);
		fiches6.setForeground(Color.BLACK);

		// GIOCATORE 7 (ICONA CARTE,ETICHETTA,NOME,FICHES)
		carteUt7.setBorder(null);
		carteUt7.setBounds(110, 240, 81, 80); // sinistra
		carteUt7.setIcon(ut7);
		etiUtente7.setIcon(Etichetta);
		etiUtente7.setBounds(0, 250, 280, 55);
		nome7.setFont(fontnome);
		nome7.setBounds(14, 240, 100, 60);
		nome7.setForeground(Color.BLUE);
		fiches7.setFont(fontfiches);
		fiches7.setBounds(35, 260, 100, 60);
		fiches7.setForeground(Color.BLACK);

		// GIOCATORE 8 (ICONA CARTE,ETICHETTA,NOME,FICHES)
		carteUt8.setBorder(null);
		carteUt8.setBounds(745, 240, 81, 80); // destra
		carteUt8.setIcon(ut8);
		etiUtente8.setIcon(Etichetta);
		etiUtente8.setBounds(860, 250, 280, 55);
		nome8.setFont(fontnome);
		nome8.setBounds(874, 240, 100, 60);
		nome8.setForeground(Color.BLUE);
		fiches8.setFont(fontfiches);
		fiches8.setBounds(895, 260, 100, 60);
		fiches8.setForeground(Color.BLACK);

		// LISTENER
		SegnaPunti.addMouseListener(FP); // Apre la finestra dei Punteggi
		Invia.addActionListener(list); // Listener del bottone Invia (Chat)
		ConsChat.addKeyListener(list); // Listener della console della Chat

		// CHAT
		ScrollChat.setBounds(927, 542, 290, 100);
		ScrollChat.setWheelScrollingEnabled(true);
		Chat.setEditable(false);
		Chat.setOpaque(false);
		Chat.setForeground(Color.BLUE);
		Chat.setFont(font);
		ConsChat.setFont(font);
		ConsChat.setBounds(927, 643, 200, 30);
		ConsChat.setForeground(Color.BLACK);
		Invia.setIcon(Sendnot);
		Invia.setPressedIcon(Sendpre);
		Invia.setBounds(1128, 643, 90, 30);
		Invia.setBorder(null);
		VisChat.setIcon(VisualizzaChat);
		VisChat.setBounds(950,510,90,30);
		Stats.setIcon(Statistics);
		Stats.setBounds(1050, 500, 90, 30);

		// INSERIMENTO NELLA FINESTRA

		principale.add(ScrollChat);
		principale.add(ConsChat);

		principale.add(Fold);
		principale.add(Call);
		principale.add(Raise);
		principale.add(AllIn);
		principale.add(Invia);
		principale.add(SegnaPunti);
		principale.add(Stats);
		principale.add(VisChat);

		principale.add(mazzo);

		principale.add(cartaT1);
		principale.add(cartaT2);
		principale.add(cartaT3);
		principale.add(cartaT4);
		principale.add(cartaT5);

		principale.add(nome1);
		principale.add(fiches1);
		principale.add(etiUtente1);// sfondo per ultimo
		principale.add(Gioc1Car1);
		principale.add(Gioc1Car2);

		principale.add(nome2);
		principale.add(fiches2);
		principale.add(etiUtente2);
		principale.add(Gioc2Car1);
		principale.add(Gioc2Car2);
		
		principale.add(nome3);
		principale.add(fiches3);
		principale.add(etiUtente3);
		principale.add(nome4);
		principale.add(fiches4);
		principale.add(etiUtente4);
		principale.add(nome5);
		principale.add(fiches5);
		principale.add(etiUtente5);
		principale.add(nome6);
		principale.add(fiches6);
		principale.add(etiUtente6);
		principale.add(nome7);
		principale.add(fiches7);
		principale.add(etiUtente7);
		principale.add(nome8);
		principale.add(fiches8);
		principale.add(etiUtente8);
		principale.add(sfondo);

		// sfondo va inserito per ultimo

		this.setVisible(true);
	}

	public static void main(String[] args) {
		new GraficaPoker();
	}// main

}// GraficaPoker
