package grafica;
import java.awt.Color;

import javax.swing.JLabel;

/**
 * Implementa il timer nella finestra,e deve risettare un icona ad ogni
 * secondo che passa, per simulare i secondi che passano per un turno
 * 
 *  DA SISTEMARE TODO
 */

public class Orologio implements Runnable{
	private static JLabel time=new JLabel("60");
	private int i=60;

	
	public Orologio(){
		time.setText(Integer.toString(i));
		time.setBounds(675,557, 500, 200);
		time.setForeground(Color.white);
		this.i=60;
	}//Orologio
	
	/**
	 * resetta il timer
	 */
	public void restart(){
		time.setVisible(true);
		this.i=60;
		time.setText(Integer.toString(i));
	}//restart
	
	/**
	 * ritorna il numero attuale del timer
	 * @return
	 */
	public int getVal(){
		return i;
	}
	
	/**
	 * Ritorna la label
	 * @return
	 */
	public static JLabel getLabel(){
		return time;
	}//getLabel

	@Override
	public void run() {  
		for(this.i=60;i>=0;i--)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			if(this.i<=20)time.setForeground(Color.red);
			time.setText(Integer.toString(this.i));
			time.repaint();
		}//while
	}//run
	public void stop(){
		time.setVisible(false);
	}

}//Orologio
