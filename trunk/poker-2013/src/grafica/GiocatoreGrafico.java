package grafica;

import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import progettoPoker.Carta;

public class GiocatoreGrafico
{
	private JLabel etichetta=null;
	private JLabel nome=null;
	private JLabel fiches=null;
	private JLabel carta1=null;
	private JLabel carta2=null;

	
	public GiocatoreGrafico(JLabel eti,JLabel nome,JLabel fich, JLabel car1,JLabel car2)
	{
		this.etichetta=eti;
		this.nome=nome;
		this.fiches=fich;
		this.carta1=car1;
		this.carta2=car2;
	}
	
	/**
	 * Se settato a  true fa comparire etichetta,
	 * nome,fiches,e carte di un giocatore genericamente inizializzate.
	 * 
	 */
	public void setVisible(boolean flag)
	{
		etichetta.setVisible(flag);
		nome.setVisible(flag);
		fiches.setVisible(flag);
		carta1.setVisible(flag);
		carta2.setVisible(flag);
	}
	
	    /**
	     * 
	     *
		 * Nel caso di fold nasconde le carte del giocatore.
		 * Se fold è a true, le carte scompaiono.
		 */
	public void setFold(boolean flag)
	{

		carta1.setVisible(!flag);
		carta2.setVisible(!flag);
	
	}
	
	/**
	 * Setta le fiches del giocatore,e nel caso in cui
	 * siano  <= 200 il colore cambia in rosso
	 */
	public void setFiches(int num)
	{

		if(num<=200)fiches.setForeground(Color.RED);
		this.fiches.setText(Integer.toString(num));;
	}
	
	/**
	 * Setta il nome del giocatore
	 */
	public void setNome(String nome)
	{
		
		this.nome.setText(nome);
	}
	
	/**
	 * Setta l'icona delle carte del giocatore
	 */
	public void setCarte(Carta c1,int i)
	{
		if(i==1){ //se è la prima carta
		    this.carta1.setIcon(Icone.getCarta(c1.getIndice()));
		    this.carta1.setVisible(true);
		}
		if(i==2){ //se è la seconda carta
			this.carta2.setIcon(Icone.getCarta(c1.getIndice()));
			this.carta2.setVisible(true);
		}
	}

	public void reset() {
		carta1.setVisible(false);
		carta2.setVisible(false);
	}

	

}
