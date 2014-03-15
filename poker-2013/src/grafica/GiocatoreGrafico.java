package grafica;

import java.awt.Color;
import javax.swing.*;
import progettoPoker.Carta;

public class GiocatoreGrafico
{
	private JLabel etichetta=null;
	private JLabel nome=null;
	private JLabel fiches=null;
	private JLabel carta1=null;
	private JLabel carta2=null;
	private ImageIcon icona1=null;
	private ImageIcon icona2=null;
	


	public GiocatoreGrafico(JLabel eti,JLabel nome,JLabel fich, JLabel car1,JLabel car2)
	{
		this.etichetta=eti;
		this.nome=nome;
		this.fiches=fich;
		this.carta1=car1;
		this.carta2=car2;
		
		
	}//Costruttore
	
	
	public void reset() {
		this.carta1.setVisible(false);
		this.carta2.setVisible(false);
	}
	
    /**
     * Restituisce true se un giocatore è visibile,false altrimenti
     * 
     */
	public boolean isVisible()
	{
		if(this.etichetta.isVisible()&&this.nome.isVisible()&&this.fiches.isVisible())
			return true;
		return false;
				
	}
	
	/**
	 * Se settato a  true fa comparire etichetta,
	 * nome,fiches,e carte di un giocatore genericamente inizializzate.
	 * 
	 */
	public void setVisible(boolean flag)
	{
		this.etichetta.setVisible(flag);
		this.nome.setVisible(flag);
		this.fiches.setVisible(flag);
		this.carta1.setVisible(flag);
		this.carta2.setVisible(flag);
	}//setVisible
	
	    /**
	     * 
	     *
		 * Nel caso di fold nasconde le carte del giocatore.
		 * Se fold è a true, le carte scompaiono.
		 */
	public void setFold(boolean flag)
	{

		this.carta1.setVisible(!flag);
		this.carta2.setVisible(!flag);
	
	}//setFold
	
	public void elimina()
	{
		this.etichetta.setVisible(false);
		this.nome.setVisible(false);
		this.fiches.setVisible(false);
		this.carta1.setVisible(false);
		this.carta2.setVisible(false);
	
	}//setFold	
	
	/**
	 * Setta le fiches del giocatore,e nel caso in cui
	 * siano  <= 200 il colore cambia in rosso
	 */
	public void setFiches(int num)
	{

		if(num<=200)fiches.setForeground(Color.RED);
		this.fiches.setText(Integer.toString(num));;
	}//setFiches
	
	/**
	 * Setta il nome del giocatore
	 */
	public void setNome(String nome)
	{
		
		this.nome.setText(nome);
	}//setNome
	
	/**
	 * Setta l'icona delle carte del giocatore
	 */
	public void setCarte(Carta c1,int i)
	{
		if(i==1){ //se è la prima carta
		{
			this.icona1=Icone.getCarta(c1.getIndice());
		    carta1.setIcon(icona1);
		}
		    this.carta1.setVisible(true);
		}
		if(i==2){ //se è la seconda carta
		{
			this.icona2=Icone.getCarta(c1.getIndice());
			carta2.setIcon(icona2);
		}
			this.carta2.setVisible(true);
		}
	}//setCarte
	
	
	/**
	 * Gira le carte del giocatore, se coperte le scopre e viceversa
	 */
	public void giraCarte()
	{
		if((this.carta1.getIcon()==Icone.coperta)&&(this.carta2.getIcon()==Icone.coperta))
		{
		    
			this.carta1.setIcon(icona1);
			this.carta2.setIcon(icona2);
		}
		else 
		{
			this.carta1.setIcon(Icone.coperta);
			this.carta2.setIcon(Icone.coperta);
			
		}
	}//giraCarte()
	

}//GiocatoreGrafico
