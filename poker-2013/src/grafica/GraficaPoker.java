package grafica;


import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import progettoPoker.Carta;
import progettoPoker.Comando;
import progettoPoker.Dealer;



public class GraficaPoker extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //TODO
	public static GiocatoreGrafico Giocatori[]=new GiocatoreGrafico[8];
	public static int getnGioc() {
		return nGioc;
	}

	//public Socket s=null;
	static Comando com=null;
	static int nGioc=0;
	/**
	 * Quando viene chiamato dà le carte ai giocatori graficamente
	 */
	public void daiCarteGioc()
	{
		for(int i=0;i<2;i++)
		{
		switch(nGioc)
		{
	
		    default: {
	                  if(i==0)
	                      if(Giocatori[7].isVisible())Movimento.muovi(Gioc8Car1,190,405);
	                  if(i==1)                                                         
	                      if(Giocatori[7].isVisible())Movimento.muovi(Gioc8Car2,260,405);
	                 }//default                                                        
		
		    case 7:  {
		              if(i==0)
		    	          if(Giocatori[6].isVisible())Movimento.muovi(Gioc7Car1,70,232);
		              if(i==1) 
		            	  if(Giocatori[6].isVisible())Movimento.muovi(Gioc7Car2,140,232);
		             }//case 7
		    
		    case 6:  {
		    	       if(i==0)
		    	           if(Giocatori[5].isVisible())Movimento.muovi(Gioc6Car1,190,60);
		    	       if(i==1)
		    	    	   if(Giocatori[5].isVisible())Movimento.muovi(Gioc6Car2,260,60);
		             }//case 6
		    
			case 5:  {
				       if(i==0)
				    	   if(Giocatori[4].isVisible()) Movimento.muovi(Gioc5Car1,512,5);
				       if(i==1)
                           if(Giocatori[4].isVisible())Movimento.muovi(Gioc5Car2,582,5);
                     }//case5
			
			case 4:  {
	                   if(i==0)
	   		               if(Giocatori[3].isVisible())Movimento.muovi(Gioc4Car1,842,60);
                       if(i==1)
	   		               if(Giocatori[3].isVisible()) Movimento.muovi(Gioc4Car2,912,60);
                     }//case4
			
			case 3:  {
				       if(i==0)
		                   if(Giocatori[2].isVisible())Movimento.muovi(Gioc3Car1,960,232);
		   		       if(i==1)
	                       if(Giocatori[2].isVisible())Movimento.muovi(Gioc3Car2,1030,232);
	                 }//case3
			
			case 2:  {
			           if(i==0)
			               if(Giocatori[1].isVisible())Movimento.muovi(Gioc2Car1,842,405);
			           if(i==1)
		                   if(Giocatori[1].isVisible())Movimento.muovi(Gioc2Car2,912,405);
		             }//case2
		    
		    case 1: {
		    	       if(i==0)
		    	       {
		    	    	   Gioc1Car1.setVisible(true);
		    	    	   Movimento.muovi(Gioc1Car1,510,420);
		    	       }
		    	       if(i==1)
		    	       {
		    	    	   Gioc1Car2.setVisible(true);
			               Movimento.muovi(Gioc1Car2,582,420);
		    	       }
		            }//case1

			}//switch
		}//for
	}//daiCarteGioc
	
	/**
	 * Setta il pot generale del tavolo,passandogli un numero
	 */
	public void setPot(int num)
	{
		potVal.setText(Integer.toString(num));
	}
			
	/**	 Setta le prime 3 carte sul tavolo
	 */
	public void setFlop(Carta [] c,GraficaPoker gp)
	{
		this.cartaT1.setIcon(Icone.getCarta(c[0].getIndice()));
		this.cartaT1.setVisible(true);
		this.cartaT2.setIcon(Icone.getCarta(c[1].getIndice()));
		this.cartaT2.setVisible(true);
		this.cartaT3.setIcon(Icone.getCarta(c[2].getIndice()));
		this.cartaT3.setVisible(true);
	}
	/**
	 * Setta la 4a carta sul tavolo
	 */
	public void setTurn(Carta c1)
	{
		this.cartaT4.setIcon(Icone.getCarta(c1.getIndice()));
		this.cartaT4.setVisible(true);
	}
	/**
	 * Setta la 5a carta sul tavolo
	 */
	public void setRiver(Carta c1)
	{
		this.cartaT5.setIcon(Icone.getCarta(c1.getIndice()));
		this.cartaT5.setVisible(true);
	}
	public void reset() {
		this.cartaT1.setVisible(false);
		this.cartaT2.setVisible(false);
		this.cartaT3.setVisible(false);
		this.cartaT4.setVisible(false);
		this.cartaT5.setVisible(false);
		disableBottoni(true);
		Giocatori[0].reset();
		for(int i=0;i<nGioc;i++)
			Giocatori[i].resetFiches(this);
	}
	/**
	 * Restituisce un Comando
	 */
	public Comando getComando()
	{
		return com;
	}
	/**
	 * Resetta il comando
	 */
	public void resetComando()
	{
		com=null;
	}
	
	FinestraPunteggio FP = new FinestraPunteggio(); // CREAZIONE FINESTRA PUTNEGGIO
	FinestraFiches FF=new FinestraFiches();
	Container principale = this.getContentPane();   //CREAZIONE DEL CONTAINER DOVE VIENE INSERITO TUTTO

	/**
	 * Inserisce in una textField il valore attuale della call
	 * @param val
	 */
	public void setPuntataCall(int val){
		puCall.setText(""+val);
	}
	/**
	 * Setta il minimo della barra del raise
	 * @param min: minimo da cui iniziare
	 */
	public void setMinBar(int min){
		if(min>BarRaise.getMaximum())
		    BarRaise.setMinimum(BarRaise.getMaximum());
		else BarRaise.setMinimum(min);
	}

	/**
	 * Setta il massimo della barra del raise
	 * @param max: massimo della puntata
	 */
	public void setMaxBar(int max){
		BarRaise.setMaximum(max);
	}
	/**Scrive un messaggio in chat
	 */ 
	/*public static void scriviChat(String nick,String messaggio)
	{
		Chat.append(nick+": "+messaggio+"\n");
	}
	*/
	/**Scrive un messaggio nella console delle statistiche
	 */ 
	public static void scriviStatistica(String messaggio)
	{
		if(messaggio.toLowerCase().equals("clear"))
			Statistiche.setText("");
		else  Statistiche.append(" - "+messaggio+"\n");
	}
	/**Se settato a true disabilita il fold, settato a false lo riabilita
	 */ 
	public void disableFold(boolean flag)
	{
		Fold.setEnabled(!flag);
	}
	/**Se settato a true disabilita il Raise, settato a false lo riabilita
	 */ 
	public void disableRaise(boolean flag)
	{
		Raise.setEnabled(!flag);
	}
	/**
	 * Chiamato con true disabilita tutti i bottoni,con false li riabilita.
	 */
	public void disableBottoni(boolean flag)
	{
		Fold.setEnabled(!flag);
		Raise.setEnabled(!flag);
		Call.setEnabled(!flag);
		AllIn.setEnabled(!flag);
	}
	public boolean bottoniEnabled(){
		return (Fold.isEnabled()&&Call.isEnabled()&&Raise.isEnabled()&&AllIn.isEnabled());
	}
	/**
	 *  Restituisce un array di GiocatoriGrafici
	 */
	public GiocatoreGrafico[] getGiocatore()
	{
		return Giocatori;
	}
	/**
	 * Restituisce un GiocatoreGrafico in posizione: indice
	 */
	public GiocatoreGrafico getGiocatore(int indice)
	{
		return Giocatori[indice];
	}
	

	
	// CREAZIONE BOTTONI
	static JButton Fold = new JButton("Fold");
	static JButton Call = new JButton("Call");
	static JButton Raise = new JButton("Raise");
	static JButton AllIn = new JButton("All-In");
	       JButton SegnaPunti = new JButton("Punti");
	       JButton ValoreFiches = new JButton("Fiches"); 
	//static JButton VisChat=new JButton("Chat"); 
	//static JButton Invia = new JButton("Invia");
	//static JButton VisStat=new JButton("Stats");

	//PUNTATA DEL CALL
	JLabel vaCall=new JLabel("Valore del Call:");
	JTextField puCall=new JTextField("0");
	
	//DEALER,SB,BB
	static JLabel Dealer=new JLabel();
	static JLabel SB=new JLabel();
	static JLabel BB=new JLabel();
	
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
	JLabel Gioc3Car1 = new JLabel();
	JLabel Gioc3Car2 = new JLabel();
	
	// GIOCATORE 4

	JLabel etiUtente4 = new JLabel();
	JLabel nome4 = new JLabel("Giocatore 4");
	JLabel fiches4 = new JLabel("10000");
	JLabel Gioc4Car1 = new JLabel();
	JLabel Gioc4Car2 = new JLabel();
	
	// GIOCATORE 5

	JLabel etiUtente5 = new JLabel();
	JLabel nome5 = new JLabel("Giocatore 5");
	JLabel fiches5 = new JLabel("10000");
	JLabel Gioc5Car1 = new JLabel();
	JLabel Gioc5Car2 = new JLabel();

	// GIOCATORE 6

	JLabel etiUtente6 = new JLabel();
	JLabel nome6 = new JLabel("Giocatore 6");
	JLabel fiches6 = new JLabel("10000");
	JLabel Gioc6Car1 = new JLabel();
	JLabel Gioc6Car2 = new JLabel();
	

	// GIOCATORE 7

	JLabel etiUtente7 = new JLabel();
	JLabel nome7 = new JLabel("Giocatore 7");
	JLabel fiches7 = new JLabel("10000");
	JLabel Gioc7Car1 = new JLabel();
	JLabel Gioc7Car2 = new JLabel();
	
	// GIOCATORE 8
	JLabel etiUtente8 = new JLabel();
	JLabel nome8 = new JLabel("Giocatore 8");
	JLabel fiches8 = new JLabel("10000");
	JLabel Gioc8Car1 = new JLabel();
	JLabel Gioc8Car2 = new JLabel();
	
	// CREAZIONE CARTE SUL TAVOLO
	JLabel mazzo = new JLabel();
	JLabel cartaT1 = new JLabel();
	JLabel cartaT2 = new JLabel();
	JLabel cartaT3 = new JLabel(); // carte sul tavolo scoperte
	JLabel cartaT4 = new JLabel();
	JLabel cartaT5 = new JLabel();
	
	//POT TOTALE
	JLabel pot =new JLabel("Pot:");
	JLabel potVal=new JLabel("0");

	//BARRA DEL RAISE
	JScrollBar  BarRaise=new JScrollBar();
	JTextField ConsRaise=new JTextField();

	// CREAZIONE CHAT E STATISTICHE
	static JTextArea Statistiche = new JTextArea();
	JScrollPane ScrollStat = new JScrollPane(Statistiche);
	/*
	static JTextArea Chat = new JTextArea();
	public static JTextField ConsChat = new JTextField();
	JScrollPane ScrollChat = new JScrollPane(Chat);
	*/
	
	//OROLOGIO
	public Thread orologio;

	
	Listener list = new Listener(/*Chat, ConsChat,Statistiche,ScrollChat,ScrollStat,*/BarRaise,ConsRaise,Gioc1Car1,Gioc1Car2,this); // LISTENER
	JLabel sfondo = new JLabel(Icone.sfondo);
	

	// FONT
	Font fontnome = new Font("Comic Sans MS", Font.BOLD, 13);    //Palatino Linotype
	Font fontfiches = new Font("Comic Sans MS", Font.PLAIN, 10);
	Font font = new Font("Comic Sans MS", Font.ROMAN_BASELINE, 12);
	
	
	
	/**
	 * aggiunge un componente grafico al pannello principale
	 * @param jc
	 */
	public void aggiungiComp(JComponent jc){
		switch(nGioc)
		{
		//case 7: principale.add(jc,60);break;
		case 6: principale.add(jc,55);break;
		case 5: principale.add(jc,50);break;
		case 4: principale.add(jc,45);break;
		case 3: principale.add(jc,40);break;
		case 2: principale.add(jc,35);break;
		case 1: principale.add(jc,30);break;
		default: principale.add(jc,60);break; //jc,65 se c'è la chat
		}
	}//aggiungiComp
	
    public void rimuoviComp(JComponent jc){

		principale.add(jc);
		jc.validate();
	
	}

	/**
	 * Setta uno smallBlind in base al numero del giocatore
	 * @param numGioc= numero del giocatore che deve avere SmallBlind
	 */
	public static void setSmallBlind(int numGioc){ //a sinistra del dealer
		if(numGioc!=0)
			SB.setVisible(true);
			else
				SB.setVisible(false);
		switch(numGioc){
		case 8: SB.setBounds(316,475,50,50);break;
		case 7: SB.setBounds(198,302,50,50);break;
		case 6: SB.setBounds(316,129,50,50);break;
		case 5: SB.setBounds(641,75,50,50);break;
		case 4: SB.setBounds(972,130,50,50);break;
		case 3: SB.setBounds(1087,301,50,50);break;
		case 2: SB.setBounds(972,475,50,50);break;
		case 1: SB.setBounds(642,533,50,50);break;
		}//switch
	}//setSmallBlind
	
	/**
	 * Setta un bigBlind in base al numero del giocatore
	 * @param numGioc= numero del giocatore che deve avere bigBlind
	 */
	public static void setBigBlind(int numGioc){ //a sinistra del smallBlind
		if(numGioc!=0)
		BB.setVisible(true);
		else
			BB.setVisible(false);
		switch(numGioc){
		case 8: BB.setBounds(316,475,50,50);break;
		case 7: BB.setBounds(198,302,50,50);break;
		case 6: BB.setBounds(316,129,50,50);break;
		case 5: BB.setBounds(641,75,50,50);break;
		case 4: BB.setBounds(972,130,50,50);break;
		case 3: BB.setBounds(1087,301,50,50);break;
		case 2: BB.setBounds(972,475,50,50);break;
		case 1: BB.setBounds(642,533,50,50);break;
		}//switch
	}
	
	/**
	 * Restituisce l'indice del prossimo giocatore visibile
	 * @param da
	 * @return
	 */
	public static int getProsGioc(int da){
		da=da%nGioc;
		while(!Giocatori[da].isVisible())
			da=(da+1)%nGioc;
		return da+1;
	}
	/**
	 * setta il dealer (graficamente) al numero del giocatore che gli viene passato
	 * @param numGioc
	 * e setta anche Small Blind e Big Blind
	 * @return 
	 */
	public static int[] setDealer(int numGioc){ 
		Dealer.setVisible(true);
		
		int sb=numGioc;
		sb=getProsGioc(sb);
		if(sb==numGioc)            //setta lo smallBlind
			sb=0;
		setSmallBlind(sb);
		
		int bb=sb;
		bb=getProsGioc(bb);
		if(bb==numGioc || bb== sb)   //setta il bigBlind
			bb=0;    
		setBigBlind(bb);
		
		
		switch(numGioc)
		{
		case 1:Dealer.setBounds(642,533,50,50);break;
		case 2:Dealer.setBounds(972,475,50,50);break;
		case 3:Dealer.setBounds(1087,301,50,50);break;
		case 4:Dealer.setBounds(972,130,50,50);break;
		case 5:Dealer.setBounds(641,75,50,50);break;
		case 6:Dealer.setBounds(316,129,50,50);break;
		case 7:Dealer.setBounds(198,302,50,50);break;
		default: Dealer.setBounds(316,475,50,50);break;
		}
		int indici[]={numGioc,sb,bb};
		return indici;
	}//setDealer
	
	public GraficaPoker(int numGioc) {


		// DIMENSIONE E OPZIONI FINESTRA (completo)
		super("Real Poker");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // DA ELIMINARE
		this.setResizable(false);
		this.setBounds(115, 15, 1024, 700);
		this.setSize(1224, 700);
		this.setSize(1224, 701);
		this.setIconImage(Icone.logo.getImage().getScaledInstance(350, 300,Image.SCALE_SMOOTH));

		GraficaPoker.nGioc=numGioc;
		// SETTA IL LOGO DELLA FINESTRA PUNTEGGI
		FP.setIconImage(Icone.logo.getImage().getScaledInstance(350, 300,Image.SCALE_SMOOTH));
		FF.setIconImage(Icone.logo.getImage().getScaledInstance(350, 300,Image.SCALE_SMOOTH));
		
		//DEALER,SB,BB
		Dealer.setIcon(Icone.Dealer);
		SB.setIcon(Icone.SB);
		BB.setIcon(Icone.BB);
		principale.add(Dealer);
		principale.add(BB);
		principale.add(SB);
		
		//SCRITTA sulle STATISTICHE
		Font fontStat=null;
		try{
			fontStat=Font.createFont(Font.TRUETYPE_FONT, Icone.st);
		   }catch(IOException e){}
		    catch(FontFormatException e1){}
		JLabel StatScri=new JLabel("Statistiche");
		StatScri.setFont(fontStat.deriveFont(Font.BOLD,20));
		StatScri.setForeground(Color.GREEN);
		StatScri.setBounds(1030,500,100,100);
		
		//VALORE CALL
		vaCall.setBounds(760,545,100,100);
		puCall.setBounds(780,605,50,20);
		puCall.setEnabled(false);
		puCall.setDisabledTextColor(Color.black);
		vaCall.setForeground(Color.white);
		vaCall.setFont(fontStat.deriveFont(Font.PLAIN,16));
		puCall.setEditable(false);
		principale.add(vaCall);
		principale.add(puCall);
		//OROLOGIO
		
		//FONT OROLOGIO
		orologio=new Thread(new Orologio());  //TODO orologio
		Font orolox = null;
		try {
             orolox=Font.createFont(Font.TRUETYPE_FONT,Icone.orol);
			} catch (IOException e){}
		      catch(FontFormatException e1) {}
		JLabel Time = Orologio.getLabel();
		Time.setFont(orolox.deriveFont(Font.PLAIN,14));
		
		//FONT TEXTAREA
		Font font2=null;
		try {
            font2=Font.createFont(Font.TRUETYPE_FONT,Icone.fontArea);
			} catch (IOException e){}
		      catch(FontFormatException e1) {}
		Statistiche.setFont(font2.deriveFont(Font.BOLD,12));
		
		// BOTTONI (FOLD,RAISE,CALL,ALL-IN,INVIA,CHAT,STATS,SEGNAPUNTI,VALOREFICHES)
		Fold.setIcon(Icone.Foldnot);
		Fold.setPressedIcon(Icone.Foldpre);
		Fold.setDisabledIcon(Icone.Foldpre);
		Fold.setBounds(350, 600, 90, 30);
		Fold.setBorder(null);
		Call.setIcon(Icone.Callnot);
		Call.setPressedIcon(Icone.Callpre);
		Call.setDisabledIcon(Icone.Callpre);
		Call.setBounds(445, 600, 90, 30);
		Call.setBorder(null);
		Raise.setIcon(Icone.Raisenot);
		Raise.setPressedIcon(Icone.Raisepre);
		Raise.setDisabledIcon(Icone.Raisepre);
		Raise.setBounds(540, 600, 90, 30);
		Raise.setBorder(null);
		AllIn.setIcon(Icone.Allnot);
		AllIn.setPressedIcon(Icone.Allpre);
		AllIn.setDisabledIcon(Icone.Allpre);
		AllIn.setBounds(635, 600, 90, 30);
		AllIn.setBorder(null);
		SegnaPunti.setIcon(Icone.Puntinot);
		SegnaPunti.setPressedIcon(Icone.Puntipre);
		SegnaPunti.setBounds(0, 645, 90, 30);
		SegnaPunti.setBorder(null);
		ValoreFiches.setIcon(Icone.ValFinot);
		ValoreFiches.setPressedIcon(Icone.ValFipre);
		ValoreFiches.setBounds(90, 645, 90, 30);
		ValoreFiches.setBorder(null);
		/*
		VisChat.setIcon(Icone.VisualizzaChat); 
		VisChat.setBounds(1017, 512, 90, 30);
		VisChat.setBorder(null);
		*/
		
		//VisStat.setIcon(Icone.StatSelected);
		//VisStat.setBounds(927,512,90,30);
		//VisStat.setBorder(null);

		pot.setBounds(580,200,100,50);
		pot.setFont(fontnome);
		pot.setForeground(Color.WHITE.brighter());
		potVal.setBounds(610,201,100,50);
		potVal.setFont(font);
		potVal.setForeground(Color.WHITE.brighter());

		// LE 5 CARTE SUL TAVOLO + MAZZO

		cartaT1.setBounds(320, 250, 100, 100); 
		cartaT2.setBounds(395, 250, 100, 100);
		cartaT3.setBounds(470, 250, 100,100); 
		cartaT4.setBounds(545, 250, 100, 100); 
		cartaT5.setBounds(620, 250, 100,100); 
		
		cartaT1.setIcon(Icone.coperta);  //TODO da eliminare queste icone
		cartaT2.setIcon(Icone.coperta);
		cartaT3.setIcon(Icone.coperta);  
		cartaT4.setIcon(Icone.coperta);
		cartaT5.setIcon(Icone.coperta);

		mazzo.setIcon(Icone.Mazzo);
		mazzo.setBounds(730, 240, 100, 120);
       
		switch(numGioc)
		{
		
		   case 8:    
		   	          etiUtente8.setIcon(Icone.Etichetta);
		   	          etiUtente8.setBounds(75, 460, 300, 55);
		   	          nome8.setFont(fontnome);               
		   	          nome8.setBounds(240, 449, 100, 60);
		   	          nome8.setForeground(Color.BLUE);
		   	          fiches8.setFont(fontfiches);
		   	          fiches8.setBounds(260, 466, 100, 60);
		   	          fiches8.setForeground(Color.BLACK);
		   	          Gioc8Car1.setIcon(Icone.coperta);
		   	          Gioc8Car2.setIcon(Icone.coperta);
			          GiocatoreGrafico Giocatore8=new GiocatoreGrafico(etiUtente8,nome8,fiches8,Gioc8Car1,Gioc8Car2);
		   	          Giocatori[7]=Giocatore8;
		              principale.add(nome8);
		              principale.add(fiches8);
		              principale.add(etiUtente8);
		              principale.add(Gioc8Car1);
		              principale.add(Gioc8Car2);
		   
		   case 7:    
		   	          etiUtente7.setIcon(Icone.Etichetta);
		   	          etiUtente7.setBounds(-44, 287, 300, 55);  
		   	          nome7.setFont(fontnome);
		   	          nome7.setBounds(121, 276, 100, 60);
		   	          nome7.setForeground(Color.BLUE);
		   	          fiches7.setFont(fontfiches);
		   	          fiches7.setBounds(141, 293, 100, 60);
		   	          fiches7.setForeground(Color.BLACK);
		   	          Gioc7Car1.setIcon(Icone.coperta);
		   	          Gioc7Car2.setIcon(Icone.coperta);
			          GiocatoreGrafico Giocatore7=new GiocatoreGrafico(etiUtente7,nome7,fiches7,Gioc7Car1,Gioc7Car2);
		   	          Giocatori[6]=Giocatore7;
		              principale.add(nome7);
		              principale.add(fiches7);
		              principale.add(etiUtente7);
		              principale.add(Gioc7Car1);
		              principale.add(Gioc7Car2);
		   
		   case 6:    
				      etiUtente6.setIcon(Icone.Etichetta);
		   	          etiUtente6.setBounds(75, 115, 300, 55);
		   	          nome6.setFont(fontnome);
		   	          nome6.setBounds(240, 104, 100, 60);
		   	          nome6.setForeground(Color.BLUE);
		   	          fiches6.setFont(fontfiches);
		   	          fiches6.setBounds(260, 121, 100, 60);
		   	          fiches6.setForeground(Color.BLACK);
		   	          Gioc6Car1.setIcon(Icone.coperta);
		   	          Gioc6Car2.setIcon(Icone.coperta);
			          GiocatoreGrafico Giocatore6=new GiocatoreGrafico(etiUtente6,nome6,fiches6,Gioc6Car1,Gioc6Car2);
		   	          Giocatori[5]=Giocatore6;
		              principale.add(nome6);
		              principale.add(fiches6);
		              principale.add(etiUtente6);
		              principale.add(Gioc6Car1);
		              principale.add(Gioc6Car2);
		   
		   case 5:    
		   	          etiUtente5.setIcon(Icone.Etichetta);
		   	          etiUtente5.setBounds(400, 60, 300, 55);
		   	          nome5.setFont(fontnome);
		   	          nome5.setBounds(565, 49, 100, 60);
		   	          nome5.setForeground(Color.BLUE);
		   	          fiches5.setFont(fontfiches);
		   	          fiches5.setBounds(585, 66, 100, 60);
		   	          fiches5.setForeground(Color.BLACK);
		   	          Gioc5Car1.setIcon(Icone.coperta);
		   	          Gioc5Car2.setIcon(Icone.coperta);
			          GiocatoreGrafico Giocatore5=new GiocatoreGrafico(etiUtente5,nome5,fiches5,Gioc5Car1,Gioc5Car2);
		   	          Giocatori[4]=Giocatore5;
		              principale.add(nome5);
		              principale.add(fiches5);
		              principale.add(etiUtente5);
		              principale.add(Gioc5Car1);
		              principale.add(Gioc5Car2);
		   	        		              
		   case 4:   
		   	          etiUtente4.setIcon(Icone.Etichetta);
		   	          etiUtente4.setBounds(730, 115, 300, 55);
		   	          nome4.setFont(fontnome);
		   	          nome4.setBounds(895, 104, 100, 60);
		   	          nome4.setForeground(Color.BLUE);
		   	          fiches4.setFont(fontfiches);
		   	          fiches4.setBounds(915, 121, 100, 60);
		   	          fiches4.setForeground(Color.BLACK);
		   	          Gioc4Car1.setIcon(Icone.coperta);
		   	          Gioc4Car2.setIcon(Icone.coperta);
			          GiocatoreGrafico Giocatore4=new GiocatoreGrafico(etiUtente4,nome4,fiches4,Gioc4Car1,Gioc4Car2);
		   	          Giocatori[3]=Giocatore4;
		              principale.add(nome4);
		              principale.add(fiches4);
		              principale.add(etiUtente4);
		              principale.add(Gioc4Car1);
		              principale.add(Gioc4Car2);
		              
		   case 3:	  
		   	          etiUtente3.setIcon(Icone.Etichetta);
		              etiUtente3.setBounds(845, 287, 300, 55);
		              nome3.setFont(fontnome);
		              nome3.setBounds(1010, 276, 100, 60);
		              nome3.setForeground(Color.BLUE);
		              fiches3.setFont(fontfiches);
		              fiches3.setBounds(1030, 293, 100, 60);
		              fiches3.setForeground(Color.BLACK);
		              Gioc3Car1.setIcon(Icone.coperta);
		              Gioc3Car2.setIcon(Icone.coperta);			   
			          GiocatoreGrafico Giocatore3=new GiocatoreGrafico(etiUtente3,nome3,fiches3,Gioc3Car1,Gioc3Car2);
		              Giocatori[2]=Giocatore3;
		              principale.add(nome3);
		              principale.add(fiches3);
		              principale.add(etiUtente3);
		              principale.add(Gioc3Car1);
		              principale.add(Gioc3Car2);
		   	          
		   case 2:    
		   	          etiUtente2.setIcon(Icone.Etichetta);
		   	          etiUtente2.setBounds(730, 460, 300, 55);
		   	          nome2.setFont(fontnome);
		   	          nome2.setBounds(895, 449, 100, 60);
		   	          nome2.setForeground(Color.BLUE);
		   	          fiches2.setFont(fontfiches);
		   	          fiches2.setBounds(915, 466, 100, 60);
		   	          fiches2.setForeground(Color.BLACK);
		   	          Gioc2Car1.setIcon(Icone.coperta);
		   	          Gioc2Car2.setIcon(Icone.coperta);
			          GiocatoreGrafico Giocatore2=new GiocatoreGrafico(etiUtente2,nome2,fiches2,Gioc2Car1,Gioc2Car2);
		   	          Giocatori[1]=Giocatore2;
		              principale.add(nome2);
		              principale.add(fiches2);
		              principale.add(etiUtente2);
		              principale.add(Gioc2Car1);
		              principale.add(Gioc2Car2);
		              
		   case 1:     
		              etiUtente1.setIcon(Icone.Etichetta);
		              etiUtente1.setBounds(400, 520, 300, 55);
		              nome1.setFont(fontnome);
		              nome1.setBounds(565, 509, 100, 60); // +14,-10
		              nome1.setForeground(Color.BLUE);
		              fiches1.setFont(fontfiches); // rispetto a Utente1
		              fiches1.setBounds(585, 526, 100, 60); // +35,+10
		              fiches1.setForeground(Color.BLACK); 
		              Gioc1Car1.setIcon(Icone.coperta);
		              Gioc1Car2.setIcon(Icone.coperta);

			          GiocatoreGrafico Giocatore1 = new GiocatoreGrafico(etiUtente1, nome1,fiches1, Gioc1Car1, Gioc1Car2);
		              Giocatori[0]=Giocatore1;
		              principale.add(nome1);
		              principale.add(fiches1);
		              principale.add(etiUtente1);
		              principale.add(Gioc1Car1);
		              principale.add(Gioc1Car2);
		        
		}

		
        //GIOCATORE 8


		//POSIZIONI AL MAZZO

		Gioc1Car1.setBounds(735,240,100,100);
		Gioc1Car2.setBounds(735,240,100,100);
		Gioc2Car1.setBounds(736,240,100,100);
		Gioc2Car2.setBounds(736,240,100,100);
		Gioc3Car1.setBounds(736,240,100,100);
		Gioc3Car2.setBounds(736,240,100,100);
		Gioc4Car1.setBounds(736,240,100,100);
		Gioc4Car2.setBounds(736,240,100,100);
		Gioc5Car1.setBounds(736,240,100,100);
		Gioc5Car2.setBounds(736,240,100,100);
		Gioc6Car1.setBounds(736,240,100,100);
		Gioc6Car2.setBounds(736,240,100,100);
		Gioc7Car1.setBounds(736,240,100,100);
		Gioc7Car2.setBounds(736,240,100,100);
		Gioc8Car1.setBounds(736,240,100,100);
		Gioc8Car2.setBounds(736,240,100,100);
				
		
		// LISTENER
		SegnaPunti.addMouseListener(FP); // Apre la finestra dei Punteggi
		ValoreFiches.addMouseListener(FF);//Apre la finestra delle fiches
		
		//Invia.addActionListener(list); // Listener del bottone Invia (Chat)
		//ConsChat.addKeyListener(list); // Listener della console della Chat
		//VisChat.addActionListener(list); 
		Fold.addActionListener(list);
		Raise.addActionListener(list);
		Call.addActionListener(list);
		AllIn.addActionListener(list);
		
		
		
		//VisStat.addActionListener(list);
		
		BarRaise.addAdjustmentListener(list);
		ConsRaise.addKeyListener(list);
		
		Gioc1Car1.addMouseListener(list);   //listener per girare le carte del gioc1 al passaggio del mouse
		Gioc1Car2.addMouseListener(list);
		
		this.addMouseListener(list);
		
		//SCROLL DEL RAISE
		BarRaise.setOrientation(0); //MESSO IN ORIZZONTALE
		BarRaise.setBounds(450, 640,150, 20);
		ConsRaise.setBounds(602,640,55,20);
		ConsRaise.setText(new Integer(BarRaise.getValue()).toString());
		BarRaise.setMinimum(200);  
		BarRaise.setMaximum(10000);  //SETTA IL MASSIMO

		
		
		
		
		/* 
		ScrollChat.setBounds(927, 542, 290, 100);
		ScrollChat.setWheelScrollingEnabled(true);
		*/
		ScrollStat.setBounds(927,560,290,112);
		ScrollStat.setWheelScrollingEnabled(true);
		/*
		Chat.setEditable(false);
		Chat.setForeground(Color.BLUE);
		Chat.setFont(font);
		ConsChat.setVisible(false);
		ScrollChat.setVisible(false);
		Invia.setVisible(false);
		ConsChat.setFont(font);
		ConsChat.setBounds(927, 643, 200, 30);
		ConsChat.setForeground(Color.BLACK);
		Invia.setIcon(Icone.Sendnot);
		Invia.setPressedIcon(Icone.Sendpre);
		Invia.setBounds(1128, 643, 90, 30);
		Invia.setBorder(null);
		*/
		
		Statistiche.setEditable(false);
		Statistiche.setForeground(Color.BLACK);
		Statistiche.setFocusable(true);
		//Statistiche.setFont(font);
		
		// INSERIMENTO NELLA FINESTRA		
		principale.add(Time);
		
		//principale.add(ScrollChat);
		principale.add(StatScri);
		principale.add(ScrollStat);
		//principale.add(ConsChat);

		principale.add(BarRaise);
		principale.add(ConsRaise);
		
		principale.add(Fold);
		principale.add(Call);
		principale.add(Raise);
		principale.add(AllIn);
		//principale.add(Invia);
		principale.add(SegnaPunti);
		principale.add(ValoreFiches);
		//principale.add(VisStat);
		//principale.add(VisChat);
	
		principale.add(pot);
		principale.add(potVal);
		
		principale.add(cartaT1);
		principale.add(cartaT2);
		principale.add(cartaT3);
		principale.add(cartaT4);
		principale.add(cartaT5);
		principale.add(mazzo);
		
		principale.add(sfondo);

		// sfondo va inserito per ultimo

		disableBottoni(true);
		
		this.setVisible(true);
	}

	public void punta(int i, int puntata, GraficaPoker gp) {
		Giocatori[i-1].puntata=Fiches.punta(i, puntata, gp, Giocatori[i-1].puntata);
	}


	public static void main(String[] args) {

		GraficaPoker gp=new GraficaPoker(8);
		/*Giocatori[1].setVisible(false);
		Giocatori[2].setVisible(false);
		Giocatori[3].setVisible(false);
		Giocatori[4].setVisible(false);
		Giocatori[5].setVisible(false);
		Giocatori[6].setVisible(false);
		Giocatori[7].setVisible(false);
		setDealer(1);*/
		
		scriviStatistica("Ciao");
		scriviStatistica("come");
		scriviStatistica("va?");
		Fiches f1=Fiches.punta(1, 99999, gp,null);
		//Fiches f2=Fiches.punta(2, 99999, gp,null);
		//Fiches f3=Fiches.punta(3, 99999, gp,null);
		//Fiches f4=Fiches.punta(4, 99999, gp,null);
		//Fiches f5=Fiches.punta(5, 99999, gp,null);
		//Fiches f6=Fiches.punta(6, 99999, gp,null);
		Fiches f7=Fiches.punta(7, 99999, gp,null);
		Fiches f8=Fiches.punta(8, 2345, gp,null);
		
		gp.vincitoreMano(8,f8);
		gp.vincitoreMano(7,f7);
	
		gp.daiCarteGioc();

		
	}// main

	public void resetGioc(Dealer d) {
		for(int i=0;i<nGioc;i++){
			Giocatori[i].setVisible(d.getG()[i].getInGioco());
		}
		
	}//resetGioc
	
	public void setAttivo(int index){
		for(int i=0;i<nGioc;i++)
			Giocatori[i].setAttivo(false);
		if(index!=-1)
		Giocatori[index].setAttivo(true);
	}//setAttivo
	
	/**
	 *Sposta le fiches al giocatore vincente della mano 
	 * @param ind indice del giocatore
	 * @param jc  componenti da spostare
	 */
	public void vincitoreMano(int ind,Fiches f){ //TODO vincitore mano
		JLabel [] fiches=new JLabel[6];
		fiches[0]=f.getSiChip1();
		fiches[1]=f.getSiChip2();
		fiches[2]=f.getSiChip3();
		fiches[3]=f.getSiChip4();
		fiches[4]=f.getSiChip5();
		fiches[5]=f.getSiChip6();

		for(int i=0;i<fiches.length;i++){
			switch(ind){
			case 8:Movimento.muovi(fiches[i], 230, 445);break;
			case 7:Movimento.muovi(fiches[i], 140, 270);break;
			case 6:Movimento.muovi(fiches[i], 230, 100);break;
			case 5:Movimento.muovi(fiches[i], 550, 45);break;
			case 4:Movimento.muovi(fiches[i], 880, 100);break;
			case 3:Movimento.muovi(fiches[i], 1000, 270);break;
			case 2:Movimento.muovi(fiches[i], 850, 445);break;
			default:Movimento.muovi(fiches[i], 555, 505);break;
			}
		}
	}//vincitoreMano
	

}// GraficaPoker
