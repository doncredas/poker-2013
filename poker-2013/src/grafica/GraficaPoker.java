package grafica;
import java.awt.*;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.*;

import progettoPoker.Carta;
import progettoPoker.Comando;
import progettoPoker.Dealer;

public class GraficaPoker extends JFrame {

	private static final long serialVersionUID = 1L;
	private static GiocatoreGrafico Giocatori[]=new GiocatoreGrafico[8];
	private static Comando com=null;
	private static int nGioc=0;

	public static int getnGioc() {
		return nGioc;
	}//getnGioc
	
	/**
	 * Quando viene chiamato da le carte ai giocatori graficamente
	 */
	public void daiCarteGioc()
	{
		for(int i=0;i<2;i++)
		{
		switch(nGioc)
		{
	
		    default: {
	                  if(i==0)
	                      if(getGiocatori()[7].isVisible())Movimento.muovi(Gioc8Car1,190,405);
	                  if(i==1)                                                         
	                      if(getGiocatori()[7].isVisible())Movimento.muovi(Gioc8Car2,260,405);
	                 }//default                                                        
		
		    case 7:  {
		              if(i==0)
		    	          if(getGiocatori()[6].isVisible())Movimento.muovi(Gioc7Car1,70,232);
		              if(i==1) 
		            	  if(getGiocatori()[6].isVisible())Movimento.muovi(Gioc7Car2,140,232);
		             }//case 7
		    
		    case 6:  {
		    	       if(i==0)
		    	           if(getGiocatori()[5].isVisible())Movimento.muovi(Gioc6Car1,190,60);
		    	       if(i==1)
		    	    	   if(getGiocatori()[5].isVisible())Movimento.muovi(Gioc6Car2,260,60);
		             }//case 6
		    
			case 5:  {
				       if(i==0)
				    	   if(getGiocatori()[4].isVisible()) Movimento.muovi(Gioc5Car1,512,5);
				       if(i==1)
                           if(getGiocatori()[4].isVisible())Movimento.muovi(Gioc5Car2,582,5);
                     }//case5
			
			case 4:  {
	                   if(i==0)
	   		               if(getGiocatori()[3].isVisible())Movimento.muovi(Gioc4Car1,842,60);
                       if(i==1)
	   		               if(getGiocatori()[3].isVisible()) Movimento.muovi(Gioc4Car2,912,60);
                     }//case4
			
			case 3:  {
				       if(i==0)
		                   if(getGiocatori()[2].isVisible())Movimento.muovi(Gioc3Car1,960,232);
		   		       if(i==1)
	                       if(getGiocatori()[2].isVisible())Movimento.muovi(Gioc3Car2,1030,232);
	                 }//case3
			
			case 2:  {
			           if(i==0)
			               if(getGiocatori()[1].isVisible())Movimento.muovi(Gioc2Car1,842,405);
			           if(i==1)
		                   if(getGiocatori()[1].isVisible())Movimento.muovi(Gioc2Car2,912,405);
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
		potVal.setText(Integer.toString(num)+"€");
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
		getGiocatori()[0].reset();
		for(int i=0;i<nGioc;i++)
			getGiocatori()[i].resetFiches(this);
	}
	/**
	 * Restituisce un Comando
	 */
	public Comando getComando()
	{
		return getCom();
	}
	/**
	 * Resetta il comando
	 */
	public void resetComando()
	{
		setCom(null);
	}
	
	/**
	 * Inserisce in una textField il valore attuale della call
	 * @param val
	 */
	public void setPuntataCall(int val){
		puCall.setText(""+val+" €");
	}
	
	/**
	 * Restituisce il valore della puntata attuale
	 * @return
	 */
	public int getPuntataCall(){
		StringTokenizer st=new StringTokenizer(puCall.getText()," "+"€",false);
		return Integer.parseInt(st.nextToken());
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

	/**Scrive un messaggio nella console delle statistiche
	 */ 
	public static void scriviStatistica(String messaggio)
	{
		if(messaggio.toLowerCase().equals("clear"))
			Statistiche.setText("");
		else  Statistiche.append(" - "+messaggio+"\n");
		Statistiche.setCaretPosition(Statistiche.getDocument().getLength());
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
		return getGiocatori();
	}
	/**
	 * Restituisce un GiocatoreGrafico in posizione: indice
	 */
	public GiocatoreGrafico getGiocatore(int indice)
	{
		return getGiocatori()[indice];
	}
	

	/**
	 * aggiunge un componente grafico al pannello principale
	 * @param jc
	 */
	public void aggiungiComp(JComponent jc){
		switch(nGioc)
		{
		case 7: principale.add(jc,55);break; //60 e poi 5 in meno a scendere
		case 6: principale.add(jc,50);break;
		case 5: principale.add(jc,45);break;
		case 4: principale.add(jc,40);break;
		case 3: principale.add(jc,35);break;
		case 2: principale.add(jc,30);break;
		case 1: principale.add(jc,25);break;
		default: principale.add(jc,60);break; //jc,65 se c'� la chat
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
		while(!getGiocatori()[da].isVisible())
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
	

	public void punta(int i, int puntata, GraficaPoker gp) {
		getGiocatori()[i-1].setPuntata(Fiches.punta(i, puntata, gp, getGiocatori()[i-1].getPuntata()));
	}
	
	public void eliminaBui(){
		Dealer.setVisible(false);
		SB.setVisible(false);
		BB.setVisible(false);
	}
	
	/**
	 * Crea l'orologio grafico
	 * @param tempo=tempo da cui iniziare
	 */
	public void creaOrol(int tempo){
		orologio=new Orologio(tempo);
		orol=new Thread(orologio);
		orol.start();
		Time = Orologio.getLabel();
	}//creaOrol
	
	/**
	 * ferma l'orologio
	 */
	public void stopOr(){
		orologio.stop();
	}//stopOr
	
	/**
	 * Fa ripartire l'orologio
	 */
	public void restartOr(){
		orologio.restart();
	}//restartOr

	/**
	 * restituisce il valore attuale del timer
	 * @return
	 */
	public int getTime(){
		return orologio.getVal();
	}
	public void resetGioc(Dealer d) {
		for(int i=0;i<nGioc;i++){
			getGiocatori()[i].setVisible(d.getG()[i].getInGioco());
		}
		
	}//resetGioc
	
	public void setAttivo(int index){
		for(int i=0;i<nGioc;i++)
			getGiocatori()[i].setAttivo(false);
		if(index!=-1)
		getGiocatori()[index].setAttivo(true);
	}//setAttivo
	
	/**
	 *Sposta le fiches al giocatore vincente della mano 
	 * @param ind indice del giocatore
	 * @param jc  componenti da spostare
	 */
	public void vincitoreMano(int ind,Fiches f){
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
		Fiches.reset(f, this);
	}//vincitoreMano

	FinestraPunteggio FP = new FinestraPunteggio(); // CREAZIONE FINESTRA PUTNEGGIO
	FinestraFiches FF=new FinestraFiches();
	Container principale = this.getContentPane();   //CREAZIONE DEL CONTAINER DOVE VIENE INSERITO TUTTO


	
	// CREAZIONE BOTTONI
	static JButton Fold = new JButton("Fold");
	static JButton Call = new JButton("Call");
	static JButton Raise = new JButton("Raise");
	static JButton AllIn = new JButton("All-In");
	       JButton SegnaPunti = new JButton("Punti");
	       JButton ValoreFiches = new JButton("Fiches"); 

	//PUNTATA DEL CALL
	JLabel vaCall=new JLabel("Valore del Call:");
	JTextField puCall=new JTextField("0 €");
	
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
	JLabel cartaT3 = new JLabel();
	JLabel cartaT4 = new JLabel();
	JLabel cartaT5 = new JLabel();
	
	//POT TOTALE
	JLabel pot =new JLabel("Pot:");
	JLabel potVal=new JLabel("0 €");

	//BARRA DEL RAISE
	JScrollBar  BarRaise=new JScrollBar();
	JTextField ConsRaise=new JTextField();

	// CREAZIONE STATISTICHE
	static JTextArea Statistiche = new JTextArea();
	JScrollPane ScrollStat = new JScrollPane(Statistiche);
	
	//OROLOGIO
	public Thread orol;
	public Orologio orologio;
	JLabel Time=Orologio.getLabel();
	
    //LISTENER E SFONDO
	Listener list = new Listener(BarRaise,ConsRaise,Gioc1Car1,Gioc1Car2,this);
	JLabel sfondo = new JLabel(Icone.getSfondo());
	

	// FONT
	Font fontnome = new Font("Comic Sans MS", Font.BOLD, 13); 
	Font fontfiches = new Font("Comic Sans MS", Font.PLAIN, 10);
	Font font = new Font("Comic Sans MS", Font.ROMAN_BASELINE, 12);
	
	
	
	
	public GraficaPoker(int numGioc) {
		
		super("Real Poker");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(115, 15, 1024, 700);
		this.setSize(1224, 700);
		this.setSize(1224, 701);
		this.setIconImage(Icone.getLogo().getImage().getScaledInstance(350, 300,Image.SCALE_SMOOTH));

		GraficaPoker.nGioc=numGioc;
		
		// SETTA IL LOGO DELLA FINESTRA PUNTEGGI
		FP.setIconImage(Icone.getLogo().getImage().getScaledInstance(350, 300,Image.SCALE_SMOOTH));
		FF.setIconImage(Icone.getLogo().getImage().getScaledInstance(350, 300,Image.SCALE_SMOOTH));
		
		//DEALER,SB,BB
		Dealer.setIcon(Icone.getDealer());
		SB.setIcon(Icone.getSB());
		BB.setIcon(Icone.getBB());
		principale.add(Dealer);
		principale.add(BB);
		principale.add(SB);
		
		//SCRITTA SULLA TEXTAREA DELLE STATISTICHE
		Font fontStat=null;
		try{
			fontStat=Font.createFont(Font.TRUETYPE_FONT, Icone.getSt());
		   }catch(IOException e){}
		    catch(FontFormatException e1){}
		JLabel StatScri=new JLabel("Statistiche");
		StatScri.setFont(fontStat.deriveFont(Font.BOLD,20));
		StatScri.setForeground(Color.GREEN);
		StatScri.setBounds(1030,500,100,100);
		
		//VALORE CALL
		vaCall.setBounds(760,545,100,100);
		puCall.setBounds(780,605,55,20);
		puCall.setEnabled(false);
		puCall.setDisabledTextColor(Color.black);
		vaCall.setForeground(Color.white);
		vaCall.setFont(fontStat.deriveFont(Font.PLAIN,16));
		puCall.setEditable(false);
		principale.add(vaCall);
		principale.add(puCall);

		//FONT TEXTAREA
		Font font2=null;
		try {
            font2=Font.createFont(Font.TRUETYPE_FONT,Icone.getFontArea());
			} catch (IOException e){}
		      catch(FontFormatException e1) {}
		Statistiche.setFont(font2.deriveFont(Font.BOLD,14));
		Statistiche.setAutoscrolls(true);
		
		// BOTTONI (FOLD,RAISE,CALL,ALL-IN,SEGNAPUNTI,VALOREFICHES)
		Fold.setIcon(Icone.getFoldnot());
		Fold.setPressedIcon(Icone.getFoldpre());
		Fold.setDisabledIcon(Icone.getFoldpre());
		Fold.setBounds(350, 600, 90, 30);
		Fold.setBorder(null);
		Call.setIcon(Icone.getCallnot());
		Call.setPressedIcon(Icone.getCallpre());
		Call.setDisabledIcon(Icone.getCallpre());
		Call.setBounds(445, 600, 90, 30);
		Call.setBorder(null);
		Raise.setIcon(Icone.getRaisenot());
		Raise.setPressedIcon(Icone.getRaisepre());
		Raise.setDisabledIcon(Icone.getRaisepre());
		Raise.setBounds(540, 600, 90, 30);
		Raise.setBorder(null);
		AllIn.setIcon(Icone.getAllnot());
		AllIn.setPressedIcon(Icone.getAllpre());
		AllIn.setDisabledIcon(Icone.getAllpre());
		AllIn.setBounds(635, 600, 90, 30);
		AllIn.setBorder(null);
		SegnaPunti.setIcon(Icone.getPuntinot());
		SegnaPunti.setPressedIcon(Icone.getPuntipre());
		SegnaPunti.setBounds(0, 645, 90, 30);
		SegnaPunti.setBorder(null);
		ValoreFiches.setIcon(Icone.getValFinot());
		ValoreFiches.setPressedIcon(Icone.getValFipre());
		ValoreFiches.setBounds(90, 645, 90, 30);
		ValoreFiches.setBorder(null);
		
		//POT E VALORE DEL POT
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
		cartaT1.setIcon(Icone.getCoperta());
		cartaT2.setIcon(Icone.getCoperta());
		cartaT3.setIcon(Icone.getCoperta());  
		cartaT4.setIcon(Icone.getCoperta());
		cartaT5.setIcon(Icone.getCoperta());
		mazzo.setIcon(Icone.getMazzo());
		mazzo.setBounds(730, 240, 100, 120);
       
		switch(numGioc)
		{
		
		   case 8:    
		   	          etiUtente8.setIcon(Icone.getEtichetta());
		   	          etiUtente8.setBounds(75, 460, 300, 55);
		   	          nome8.setFont(fontnome);               
		   	          nome8.setBounds(240, 449, 100, 60);
		   	          nome8.setForeground(Color.BLUE);
		   	          fiches8.setFont(fontfiches);
		   	          fiches8.setBounds(260, 466, 100, 60);
		   	          fiches8.setForeground(Color.BLACK);
		   	          Gioc8Car1.setIcon(Icone.getCoperta());
		   	          Gioc8Car2.setIcon(Icone.getCoperta());
			          GiocatoreGrafico Giocatore8=new GiocatoreGrafico(etiUtente8,nome8,fiches8,Gioc8Car1,Gioc8Car2);
		   	          getGiocatori()[7]=Giocatore8;
		              principale.add(nome8);
		              principale.add(fiches8);
		              principale.add(etiUtente8);
		              principale.add(Gioc8Car1);
		              principale.add(Gioc8Car2);
		   
		   case 7:    
		   	          etiUtente7.setIcon(Icone.getEtichetta());
		   	          etiUtente7.setBounds(-44, 287, 300, 55);  
		   	          nome7.setFont(fontnome);
		   	          nome7.setBounds(121, 276, 100, 60);
		   	          nome7.setForeground(Color.BLUE);
		   	          fiches7.setFont(fontfiches);
		   	          fiches7.setBounds(141, 293, 100, 60);
		   	          fiches7.setForeground(Color.BLACK);
		   	          Gioc7Car1.setIcon(Icone.getCoperta());
		   	          Gioc7Car2.setIcon(Icone.getCoperta());
			          GiocatoreGrafico Giocatore7=new GiocatoreGrafico(etiUtente7,nome7,fiches7,Gioc7Car1,Gioc7Car2);
		   	          getGiocatori()[6]=Giocatore7;
		              principale.add(nome7);
		              principale.add(fiches7);
		              principale.add(etiUtente7);
		              principale.add(Gioc7Car1);
		              principale.add(Gioc7Car2);
		   
		   case 6:    
				      etiUtente6.setIcon(Icone.getEtichetta());
		   	          etiUtente6.setBounds(75, 115, 300, 55);
		   	          nome6.setFont(fontnome);
		   	          nome6.setBounds(240, 104, 100, 60);
		   	          nome6.setForeground(Color.BLUE);
		   	          fiches6.setFont(fontfiches);
		   	          fiches6.setBounds(260, 121, 100, 60);
		   	          fiches6.setForeground(Color.BLACK);
		   	          Gioc6Car1.setIcon(Icone.getCoperta());
		   	          Gioc6Car2.setIcon(Icone.getCoperta());
			          GiocatoreGrafico Giocatore6=new GiocatoreGrafico(etiUtente6,nome6,fiches6,Gioc6Car1,Gioc6Car2);
		   	          getGiocatori()[5]=Giocatore6;
		              principale.add(nome6);
		              principale.add(fiches6);
		              principale.add(etiUtente6);
		              principale.add(Gioc6Car1);
		              principale.add(Gioc6Car2);
		   
		   case 5:    
		   	          etiUtente5.setIcon(Icone.getEtichetta());
		   	          etiUtente5.setBounds(400, 60, 300, 55);
		   	          nome5.setFont(fontnome);
		   	          nome5.setBounds(565, 49, 100, 60);
		   	          nome5.setForeground(Color.BLUE);
		   	          fiches5.setFont(fontfiches);
		   	          fiches5.setBounds(585, 66, 100, 60);
		   	          fiches5.setForeground(Color.BLACK);
		   	          Gioc5Car1.setIcon(Icone.getCoperta());
		   	          Gioc5Car2.setIcon(Icone.getCoperta());
			          GiocatoreGrafico Giocatore5=new GiocatoreGrafico(etiUtente5,nome5,fiches5,Gioc5Car1,Gioc5Car2);
		   	          getGiocatori()[4]=Giocatore5;
		              principale.add(nome5);
		              principale.add(fiches5);
		              principale.add(etiUtente5);
		              principale.add(Gioc5Car1);
		              principale.add(Gioc5Car2);
		   	        		              
		   case 4:   
		   	          etiUtente4.setIcon(Icone.getEtichetta());
		   	          etiUtente4.setBounds(730, 115, 300, 55);
		   	          nome4.setFont(fontnome);
		   	          nome4.setBounds(895, 104, 100, 60);
		   	          nome4.setForeground(Color.BLUE);
		   	          fiches4.setFont(fontfiches);
		   	          fiches4.setBounds(915, 121, 100, 60);
		   	          fiches4.setForeground(Color.BLACK);
		   	          Gioc4Car1.setIcon(Icone.getCoperta());
		   	          Gioc4Car2.setIcon(Icone.getCoperta());
			          GiocatoreGrafico Giocatore4=new GiocatoreGrafico(etiUtente4,nome4,fiches4,Gioc4Car1,Gioc4Car2);
		   	          getGiocatori()[3]=Giocatore4;
		              principale.add(nome4);
		              principale.add(fiches4);
		              principale.add(etiUtente4);
		              principale.add(Gioc4Car1);
		              principale.add(Gioc4Car2);
		              
		   case 3:	  
		   	          etiUtente3.setIcon(Icone.getEtichetta());
		              etiUtente3.setBounds(845, 287, 300, 55);
		              nome3.setFont(fontnome);
		              nome3.setBounds(1010, 276, 100, 60);
		              nome3.setForeground(Color.BLUE);
		              fiches3.setFont(fontfiches);
		              fiches3.setBounds(1030, 293, 100, 60);
		              fiches3.setForeground(Color.BLACK);
		              Gioc3Car1.setIcon(Icone.getCoperta());
		              Gioc3Car2.setIcon(Icone.getCoperta());			   
			          GiocatoreGrafico Giocatore3=new GiocatoreGrafico(etiUtente3,nome3,fiches3,Gioc3Car1,Gioc3Car2);
		              getGiocatori()[2]=Giocatore3;
		              principale.add(nome3);
		              principale.add(fiches3);
		              principale.add(etiUtente3);
		              principale.add(Gioc3Car1);
		              principale.add(Gioc3Car2);
		   	          
		   case 2:    
		   	          etiUtente2.setIcon(Icone.getEtichetta());
		   	          etiUtente2.setBounds(730, 460, 300, 55);
		   	          nome2.setFont(fontnome);
		   	          nome2.setBounds(895, 449, 100, 60);
		   	          nome2.setForeground(Color.BLUE);
		   	          fiches2.setFont(fontfiches);
		   	          fiches2.setBounds(915, 466, 100, 60);
		   	          fiches2.setForeground(Color.BLACK);
		   	          Gioc2Car1.setIcon(Icone.getCoperta());
		   	          Gioc2Car2.setIcon(Icone.getCoperta());
			          GiocatoreGrafico Giocatore2=new GiocatoreGrafico(etiUtente2,nome2,fiches2,Gioc2Car1,Gioc2Car2);
		   	          getGiocatori()[1]=Giocatore2;
		              principale.add(nome2);
		              principale.add(fiches2);
		              principale.add(etiUtente2);
		              principale.add(Gioc2Car1);
		              principale.add(Gioc2Car2);
		              
		   case 1:     
		              etiUtente1.setIcon(Icone.getEtichetta());
		              etiUtente1.setBounds(400, 520, 300, 55);
		              nome1.setFont(fontnome);
		              nome1.setBounds(565, 509, 100, 60); 
		              nome1.setForeground(Color.BLUE);
		              fiches1.setFont(fontfiches); 
		              fiches1.setBounds(585, 526, 100, 60); 
		              fiches1.setForeground(Color.BLACK); 
		              Gioc1Car1.setIcon(Icone.getCoperta());
		              Gioc1Car2.setIcon(Icone.getCoperta());

			          GiocatoreGrafico Giocatore1 = new GiocatoreGrafico(etiUtente1, nome1,fiches1, Gioc1Car1, Gioc1Car2);
		              getGiocatori()[0]=Giocatore1;
		              principale.add(nome1);
		              principale.add(fiches1);
		              principale.add(etiUtente1);
		              principale.add(Gioc1Car1);
		              principale.add(Gioc1Car2);
		        
		}

		//POSIZIONI AL MAZZO PER LE CARTE DEI GIOCATORI

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
		Fold.addActionListener(list);
		Raise.addActionListener(list);
		Call.addActionListener(list);
		AllIn.addActionListener(list);
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

		ScrollStat.setBounds(870,560,350,112);
		ScrollStat.setWheelScrollingEnabled(true);
		ScrollStat.setAutoscrolls(true);
		Statistiche.setEditable(false);
		Statistiche.setForeground(Color.BLACK);
		Statistiche.setFocusable(true);
		Time.setBounds(690,557, 500, 200);
		// INSERIMENTO NELLA FINESTRA		
		principale.add(Time);
		principale.add(StatScri);
		principale.add(ScrollStat);
		principale.add(BarRaise);
		principale.add(ConsRaise);
		principale.add(Fold);
		principale.add(Call);
		principale.add(Raise);
		principale.add(AllIn);
		principale.add(SegnaPunti);
		principale.add(ValoreFiches);
		principale.add(pot);
		principale.add(potVal);
		principale.add(cartaT1);
		principale.add(cartaT2);
		principale.add(cartaT3);
		principale.add(cartaT4);
		principale.add(cartaT5);
		principale.add(mazzo);
		
		principale.add(sfondo);	// lo sfondo va inserito per ultimo
		disableBottoni(true);
		this.setVisible(true);
	}
	public static void main(String[] args) throws InterruptedException{

		GraficaPoker gp=new GraficaPoker(8);
		//Giocatori[1].setVisible(false);
		//Giocatori[2].setVisible(false);
		//Giocatori[3].setVisible(false);
		//Giocatori[4].setVisible(false);
		//Giocatori[5].setVisible(false);
		//Giocatori[6].setVisible(false);
		//Giocatori[7].setVisible(false);
		setDealer(1);
		
		scriviStatistica("Ciao");
		scriviStatistica("come");
		scriviStatistica("va?");
		scriviStatistica("♥♦♣♠");
		
		gp.creaOrol(30);
		gp.restartOr();
		Thread.sleep(4000);
		
		Fiches f1=Fiches.punta(1, 99999, gp,null);
		Fiches f2=Fiches.punta(2, 99999, gp,null);

		Fiches f3=Fiches.punta(3, 99999, gp,null);
		Fiches f4=Fiches.punta(4, 99999, gp,null);
		Fiches f5=Fiches.punta(5, 99999, gp,null);
		Fiches f6=Fiches.punta(6, 99999, gp,null);
		Fiches f7=Fiches.punta(7, 99999, gp,null);
		Fiches f8=Fiches.punta(8, 2345, gp,null);
		
		gp.vincitoreMano(8,f8);
		gp.vincitoreMano(7,f7);
		gp.vincitoreMano(6,f6);
		gp.vincitoreMano(5,f5);
		gp.vincitoreMano(4,f4);
		gp.vincitoreMano(3,f3);
		gp.vincitoreMano(2,f2);
		gp.vincitoreMano(1,f1);
		
		gp.daiCarteGioc();

		
	}// main

	public static Comando getCom() {
		return com;
	}

	public static void setCom(Comando com) {
		GraficaPoker.com = com;
	}

	public static GiocatoreGrafico[] getGiocatori() {
		return Giocatori;
	}

	public static void setGiocatori(GiocatoreGrafico giocatori[]) {
		Giocatori = giocatori;
	}
}// GraficaPoker
