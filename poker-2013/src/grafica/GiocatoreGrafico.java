package grafica;

import java.awt.Color;

import javax.swing.JLabel;

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
	
	public void setVisible(boolean flag)
	{
		/**
		 * Inizialmente i giocatori dovranno essere settati tutti a false tranne
		 * il primo. Appena un giocatore si connette si usa questo metodo per
		 * far comparire etichetta,le sue carte,nome e fiches.
		 * Oppure nel caso in cui un giocatore esce dalla partita si può
		 * settare il visible a false facendo sparire tutto quello
		 * che riguarda quel determinato giocatore
		 */
		etichetta.setVisible(flag);
		nome.setVisible(flag);
		fiches.setVisible(flag);
		carta1.setVisible(flag);
		carta2.setVisible(flag);
	}
	
	public void setFold(boolean flag)
	{
		/**
		 * Nel caso di fold nasconde le carte del giocatore.
		 * Se fold è a true, le carte scompaiono.
		 */
		carta1.setVisible(!flag);
		carta2.setVisible(!flag);
	
	}
	public void setFiches(int num)
	{
		/**
		 * setta le fiches del giocatore
		 */
		if(num<=200)fiches.setForeground(Color.RED);
		this.fiches.setText(Integer.toString(num));;
	}
	public void setNome(String nome)
	{
		/**
		 * Setta il nome del giocatore
		 */
		this.nome.setText(nome);
	}
	public void setCarte(/*Carta c1, Carta c2*/)
	{
		/**
		 * inserisce l'icona della prima carta del giocatore
		 */
	}

	

}
