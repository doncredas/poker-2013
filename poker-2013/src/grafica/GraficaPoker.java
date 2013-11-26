package grafica;

import grafica.Immaginitavolo.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;

import javax.swing.*;

public class GraficaPoker extends JFrame {
	
	FinestraPunteggio FP=new FinestraPunteggio();
	
	JButton Fold=new JButton("Fold");
    JButton Call=new JButton("Call");
    JButton Raise=new JButton("Raise");
    JButton AllIn=new JButton("All-In");
    JButton Invia=new JButton("Invia");
    JButton SegnaPunti=new JButton("Punti");
    
    JTextField Chat=new JTextField();
    JTextField ConsChat=new JTextField();
    JTextField Statistiche=new JTextField();
	
    //ICONE BOTTONI
    ImageIcon Foldpre=new ImageIcon("C:\\poo_eclipse\\immagini\\Bottoni\\Foldpre.png");
	ImageIcon Foldnot=new ImageIcon("C:\\poo_eclipse\\immagini\\Bottoni\\Fold.png");
	ImageIcon Callpre = new ImageIcon("C:\\poo_eclipse\\immagini\\Bottoni\\Callpre.png");
	ImageIcon Callnot = new ImageIcon("C:\\poo_eclipse\\immagini\\Bottoni\\Call.png");
	ImageIcon Raisepre = new ImageIcon("C:\\poo_eclipse\\immagini\\Bottoni\\Raisepre.png");
	ImageIcon Raisenot = new ImageIcon("C:\\poo_eclipse\\immagini\\Bottoni\\Raise.png");
	ImageIcon Allpre = new ImageIcon("C:\\poo_eclipse\\immagini\\Bottoni\\Allpre.png");
	ImageIcon Allnot = new ImageIcon("C:\\poo_eclipse\\immagini\\Bottoni\\All.png");
	ImageIcon Puntipre = new ImageIcon("C:\\poo_eclipse\\immagini\\Bottoni\\Puntipre.png");
	ImageIcon Puntinot = new ImageIcon("C:\\poo_eclipse\\immagini\\Bottoni\\Punti.png");
	ImageIcon Sendpre = new ImageIcon("C:\\poo_eclipse\\immagini\\Bottoni\\Sendpre.png");
	ImageIcon Sendnot = new ImageIcon("C:\\poo_eclipse\\immagini\\Bottoni\\Send.png");
	//SFONDO
	ImageIcon sfondo=new ImageIcon("C:\\poo_eclipse\\immagini\\tavolo.jpg");

	public GraficaPoker() 
	{
		// ============CREAZIONE FINESTRA============
			JFrame Poker = new JFrame("Poker-2013");
			Poker.setDefaultCloseOperation(EXIT_ON_CLOSE);  //DA ELIMINARE
			Poker.setResizable(false);
			Poker.setBounds(115, 15, 1024, 700); // j.setBounds(posiz(ori), posiz(ver), dim(ori),dim(ver))
        // =========FINE CREAZIONE FINESTRA==========
			
		// ============INSERIMENTO SFONDO============	
			Poker.setContentPane(new JLabel(sfondo));
			Poker.setSize(1224,700);
		    Poker.setSize(1224,701);
	    // ========FINE INSERIMENTO SFONDO===========
		    
		// ============CREAZIONE BOTTONI=============
		    Fold.setBorder(null); // Toglie bordo
		    Raise.setBorder(null);
		    AllIn.setBorder(null);
		    Call.setBorder(null);
		    SegnaPunti.setBorder(null);
		    Invia.setBorder(null);
		    
		    //CARICAMENTO ICONE
		      Fold.setIcon(Foldnot);
		      Fold.setPressedIcon(Foldpre);
		      Call.setIcon(Callnot);
		      Call.setPressedIcon(Callpre);
		      AllIn.setIcon(Allnot);
		      AllIn.setPressedIcon(Allpre);
		      Raise.setIcon(Raisenot);
		      Raise.setPressedIcon(Raisepre);
		      SegnaPunti.setIcon(Puntinot);
		      SegnaPunti.setPressedIcon(Puntipre);
		      Invia.setIcon(Sendnot);
		      Invia.setPressedIcon(Sendpre);
		      
		    
		    //POSIZIONE E DIMENSIONE BOTTONI
		    Fold.setBounds(150, 540, 90, 30); //pos(orizz),pos(vert),dim(oriz),dim(ver)
		    Call.setBounds(250, 540, 90, 30);
		    Raise.setBounds(350, 540, 90, 30);
		    AllIn.setBounds(450, 540, 90, 30);
		    SegnaPunti.setBounds(0,645,90,30);
		    Invia.setBounds(801,632,90,30);

		    
		    //LISTENER
		    SegnaPunti.addMouseListener(FP);
		    //Invia.addMouseListener(new InviaChat());
		    
		    
		    //CHAT E TEXT AREA SULLA DESTRA
		    Chat.setBounds(600, 530, 290,100);
		    Chat.setEditable(false);
		    ConsChat.setBounds(600,632,200,30);
		    Statistiche.setBounds(950,0,268,676);
		    Statistiche.setEditable(false);
		    
		    
		    //INSERIMENTO NELLA FINESTRA
		    Poker.add(Fold);
		    Poker.add(Call);
		    Poker.add(Raise);
		    Poker.add(AllIn);
		    Poker.add(Invia);
		    Poker.add(SegnaPunti);
		    Poker.add(Chat);
		    Poker.add(ConsChat);
		    Poker.add(Statistiche);

		    Poker.setVisible(true);
	}
	
	

	public static void main(String[] args) 
	{
		new GraficaPoker();
	}// main


	

}// GraficaPoker
